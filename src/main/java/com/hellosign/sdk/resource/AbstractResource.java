package com.hellosign.sdk.resource;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.Warning;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A nice place to put code that is common to all HelloSign resource classes.
 */
public abstract class AbstractResource {

    protected JSONObject dataObj;
    protected List<Warning> warnings = new ArrayList<>();

    protected AbstractResource() {
        dataObj = new JSONObject();
    }

    protected AbstractResource(JSONObject json, String optionalKey) throws HelloSignException {
        try {
            dataObj = json;
            if (json.has(optionalKey) && !json.isNull(optionalKey)) {
                Object obj = json.get(optionalKey);
                if (obj instanceof JSONObject) {
                    dataObj = (JSONObject) obj;
                } else if (obj instanceof String) {
                    // This is to handle the case where we're returning a
                    // stringified JSON object (and should handle strings OK
                    // too.
                    dataObj = new JSONObject((String) obj);
                } else {
                    throw new HelloSignException("Cannot convert response to JSONObject: " + obj);
                }
            }
            if (json.has("warnings")) {
                JSONArray ws = json.getJSONArray("warnings");
                for (int i = 0; i < ws.length(); i++) {
                    warnings.add(new Warning(ws.getJSONObject(i)));
                }
            }
        } catch (JSONException ex) {
            throw new HelloSignException(ex);
        }
    }

    protected static boolean hasString(String s) {
        return s != null && !s.equals("");
    }

    public JSONObject getJSONObject() {
        return dataObj;
    }

    protected boolean has(String key) {
        return dataObj.has(key) && !dataObj.isNull(key);
    }

    protected void set(String key, Object value) {
        try {
            if (value instanceof Date) {
                dataObj.put(key, ((Date) value).toInstant().getEpochSecond());
            } else {
                dataObj.put(key, value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected String getString(String key) {
        if (dataObj.has(key) && !dataObj.isNull(key)) {
            try {
                return dataObj.get(key).toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    protected Boolean getBoolean(String key) {
        if (dataObj.has(key) && !dataObj.isNull(key)) {
            try {
                return dataObj.getBoolean(key);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    protected Integer getInteger(String key) {
        if (dataObj.has(key) && !dataObj.isNull(key)) {
            try {
                return dataObj.getInt(key);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    protected Object get(String key) {
        if (dataObj.has(key) && !dataObj.isNull(key)) {
            try {
                return dataObj.get(key);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    protected Date getDate(String key) {
        if (dataObj.has(key) && !dataObj.isNull(key)) {
            try {
                // Handle as a Unix timestamp (in seconds vs. milliseconds)
                return new Date(dataObj.getLong(key) * 1000L);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    protected Long getLong(String key) {
        if (dataObj.has(key) && !dataObj.isNull(key)) {
            try {
                return dataObj.getLong(key);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    protected <T> List<T> getList(Class<T> clazz, String key) {
        return getList(clazz, key, null, null);
    }

    /**
     * Get Type castable object.
     * @param Class Pass Class type to be type casted.
     * @param obj  Provide Object needs to be type casted.
     * @param key Key for Object.
     * @param <T> Object
     */
    protected <T> Object getObject(Class<T> Class, Object obj, String key){

        Constructor<?> constructor = getConstructor(Class, obj.getClass());

        @SuppressWarnings("unchecked")
        T newItem = null;
        try {
            newItem = (T) constructor.newInstance(dataObj.get(key));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return newItem;
    }

    protected <T> List<T> getList(Class<T> clazz, String key, Serializable filterValue,
        String filterColumnName) {
        List<T> returnList = new ArrayList<>();
        if (dataObj.has(key)) {
            try {
                JSONArray array = dataObj.getJSONArray(key);
                if (array.length() == 0) {
                    return returnList;
                }
                Constructor<?> constructor = getConstructor(clazz, array.get(0).getClass());
                if (constructor == null) {
                    return returnList;
                }
                for (int i = 0; i < array.length(); i++) {
                    Object obj = array.get(i);
                    // Suppress the warning for the cast, since we checked in
                    // getConstructor()
                    @SuppressWarnings("unchecked")
                    T newItem = (T) constructor.newInstance(obj);
                    if (filterColumnName == null && filterValue == null) {
                        // If we have no filter, add the item
                        returnList.add(newItem);
                    } else if (filterColumnName != null && filterValue != null) {
                        // If we have a filter, test for column and value
                        if (obj instanceof JSONObject) {
                            JSONObject testObj = (JSONObject) obj;
                            if (testObj.has(filterColumnName)) {
                                Object columnObj = testObj.get(filterColumnName);
                                if (filterValue.equals(columnObj)) {
                                    returnList.add(newItem);
                                }
                            }
                        }
                    } else if (filterValue instanceof String
                        && newItem instanceof String) {
                        // If we have a filter value, but no column name,
                        // test for String equality
                        if (filterValue.equals(newItem)) {
                            returnList.add(newItem);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return returnList;
    }

    protected void clearList(String key) throws HelloSignException {
        try {
            dataObj.put(key, new JSONArray());
        } catch (JSONException ex) {
            ex.printStackTrace();
            throw new HelloSignException("Cannot clear list " + key);
        }
    }

    protected void addToList(String key, AbstractResource listItem) throws HelloSignException {
        if (!dataObj.has(key)) {
            throw new HelloSignException(
                "Invalid key " + key + " for list of type " + listItem.getClass().getName());
        }
        try {
            JSONArray currentList = dataObj.getJSONArray(key);
            currentList.put(listItem.getJSONObject());
        } catch (JSONException ex) {
            ex.printStackTrace();
            throw new HelloSignException("Cannot add item to list " + key);
        }
    }

    /**
     * Returns the first constructor that has exactly one parameter of the provided paramClass
     * type.
     *
     * @param clazz Class whose constructors we are checking
     * @param paramClass Class Parameter class that the constructor should take
     * @return Constructor
     */
    protected Constructor<?> getConstructor(Class<?> clazz, Class<?> paramClass) {
        for (Constructor<?> c : clazz.getConstructors()) {
            Class<?>[] paramTypes = c.getParameterTypes();
            if (paramTypes.length != 1) {
                continue;
            }
            if (paramTypes[0].equals(paramClass)) {
                return c;
            }
        }
        return null;
    }

    protected <T> void add(String key, T item) {
        try {
            JSONArray array;
            if (dataObj.has(key)) {
                array = dataObj.getJSONArray(key);
            } else {
                array = new JSONArray();
                dataObj.put(key, array);
            }
            if (item instanceof String) {
                array.put(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return toString(2);
    }

    public String toString(int spacesToIndent) {
        String retStr = null;
        try {
            if (spacesToIndent > 0) {
                retStr = dataObj.toString(spacesToIndent);
            } else {
                retStr = dataObj.toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return retStr;
    }

    public List<Warning> getWarnings() {
        return warnings;
    }
}
