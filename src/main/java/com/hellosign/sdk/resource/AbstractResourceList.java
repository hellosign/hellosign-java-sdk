package com.hellosign.sdk.resource;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2015 hellosign.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosign.sdk.HelloSignException;

public abstract class AbstractResourceList<E> extends AbstractResource implements Iterable<E> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractResourceList.class);

    public static final String LIST_INFO = "list_info";
    public static final String PAGE = "page";
    public static final String NUM_PAGES = "num_pages";
    public static final String NUM_RESULTS = "num_results";
    public static final String PAGE_SIZE = "page_size";

    private JSONObject listInfo;
    private String listKey;

    protected AbstractResourceList(JSONObject json, String listKey) throws HelloSignException {
        super(json, null);
        this.listKey = listKey;
        try {
            listInfo = dataObj.getJSONObject(LIST_INFO);
        } catch (JSONException e) {
            throw new HelloSignException(e);
        }
    }

    public Integer getPage() {
        return getListData(PAGE);
    }

    public Integer getNumPages() {
        return getListData(NUM_PAGES);
    }

    public Integer getNumResults() {
        return getListData(NUM_RESULTS);
    }

    public Integer getPageSize() {
        return getListData(PAGE_SIZE);
    }

    private Integer getListData(String key) {
        Integer val = null;
        try {
            val = listInfo.getInt(key);
        } catch (JSONException e) {
            logger.error("Unable to retrieve list data for key " + key + ": " + e.getMessage(), e);
        }
        return val;
    }

    public Iterator<E> iterator() {
        Iterator<E> iter = null;
        try {
            iter = getCurrentPageList().iterator();
        } catch (HelloSignException e) {
            logger.error(e.getMessage(), e);
        }
        return iter;
    }

    /**
     * Returns the current page of results for this list object.
     * 
     * @return List
     * @throws HelloSignException thrown if the list cannot be generated
     */
    public List<E> getCurrentPageList() throws HelloSignException {
        return filterCurrentPageBy(null, null);
    }

    /**
     * Filters the current page of results by the given column and value.
     * 
     * @param columnName String column name to filter by
     * @param filterValue Serializable matching value
     * @return List results
     * @throws HelloSignException thrown if the column name is invalid
     */
    public List<E> filterCurrentPageBy(String columnName, Serializable filterValue) throws HelloSignException {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        @SuppressWarnings("unchecked")
        Class<E> clazz = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
        return getList(clazz, listKey, filterValue, columnName);
    }
}
