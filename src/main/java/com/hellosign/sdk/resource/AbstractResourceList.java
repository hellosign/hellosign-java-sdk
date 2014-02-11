package com.hellosign.sdk.resource;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;

public abstract class AbstractResourceList<E> extends AbstractResource implements Iterable<E> {

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
		try {
			return listInfo.getInt(PAGE);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Integer getNumPages() {
		try {
			return listInfo.getInt(NUM_PAGES);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Integer getNumResults() {
		try {
			return listInfo.getInt(NUM_RESULTS);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Integer getPageSize() {
		try {
			return listInfo.getInt(PAGE_SIZE);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Iterator<E> iterator() {
		List<E> list = null;
		try {
			list = getCurrentPageList();
		} catch (HelloSignException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.iterator();
	}
	
	/**
	 * Returns the current page of results for this list object.
	 * @return List<E>
	 * @throws HelloSignException
	 */
	public List<E> getCurrentPageList() 
			throws HelloSignException {
		return filterCurrentPageBy(null, null);
	}
	
	/**
	 * Filters the current page of results by the given column and value. 
	 * @param columnName String column name to filter by
	 * @param filterValue Serializable matching value
	 * @return List<E> results
	 */
	public List<E> filterCurrentPageBy(String columnName, Serializable filterValue) 
			throws HelloSignException {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		@SuppressWarnings("unchecked")
		Class<E> clazz = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
		return getList(clazz, listKey, filterValue, columnName);
	}
}
