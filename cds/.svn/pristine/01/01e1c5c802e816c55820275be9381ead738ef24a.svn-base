package com.hixapi.web.framework.ui.jqgrid;


//Copyright (C) 2014 Codemerx

//Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
//to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
//and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

//The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
//DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
//OR OTHER DEALINGS IN THE SOFTWARE.

/**
 * JQGridDataFrame stores all the data send back as a response to jqGrid AJAX
 * data request.
 * <p>
 * Create an instance of this class providing all the data required by the
 * constructors. Then use the {@link JQGridDataFrame#GetJSON} method to get the
 * data back in JSON format. The resulting string can then be sent back as a
 * reply to jqGri AJAX data request.
 */
public class JQGridDataFrame<T> {

	/**
	 * <p>
	 * Use this to change the JSON serialization settings before calling
	 * {@link JQGridDataFrame#GetJSON}
	 */

	/**
	 * The number of data page to display in jqGrid.
	 */
	private int page;

	/**
	 * The total number of data records currently bound to jqGrid.
	 */
	private int records;

	/**
	 * An array of {@link JSONGridRow} objects. Each <code>JSONGridRow</code>
	 * object represents a single row of data displayed by jqGrid.
	 */
	private JQGridRow<T>[] rows;

	/**
	 * The total number of pages in jqGrid.
	 */
	private int total;

	/**
	 * Used to send custom data together with the response. jqGrid will not
	 * automatically display that data. It could be accessed by custom javascrpt
	 * code on the client side, though.
	 */
	private Object userdata;

	/**
	 * Initializes an instance of <code>JQGridDataFrame</code>
	 * 
	 * @param pageNumber
	 *            The number of data page to display in jqGrid
	 * @param allRowsCount
	 *            The total number of data records currently bound to jqGrid.
	 * @param pagesCount
	 *            The total number of pages in jqGrid.
	 * @param data
	 *            An array of {@link JSONGridRow} objects. Each
	 *            {@link JSONGridRow} object represents a single row of data
	 *            displayed by jqGrid.
	 * @param customData
	 *            Used to send custom data together with the response. jqGrid
	 *            will not automatically display that data. It could be accessed
	 *            by custome javascrpt code on the client side, though.
	 */
	public JQGridDataFrame(int pageNumber, int allRowsCount, int pagesCount, JQGridRow<T>[] data, Object customData) {
		this.userdata = customData;
		this.page = pageNumber;
		this.total = pagesCount;
		this.records = allRowsCount;
		this.rows = data;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the records
	 */
	public int getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(int records) {
		this.records = records;
	}

	/**
	 * @return the rows
	 */
	public JQGridRow<T>[] getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(JQGridRow<T>[] rows) {
		this.rows = rows;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the userdata
	 */
	public Object getUserdata() {
		return userdata;
	}

	/**
	 * @param userdata the userdata to set
	 */
	public void setUserdata(Object userdata) {
		this.userdata = userdata;
	}

	/**
	 * Initializes an instance of <code>JQGridDataFrame</code>
	 * 
	 * @param pageNumber
	 *            The number of data page to display in jqGrid
	 * @param allRowsCount
	 *            The total number of data records currently bound to jqGrid.
	 * @param pagesCount
	 *            The total number of pages in jqGrid.
	 * @param data
	 *            An array of {@link JSONGridRow} objects. Each
	 *            {@link JSONGridRow} object represents a single row of data
	 *            displayed by jqGrid.
	 * @param customData
	 *            Used to send custom data together with the response. jqGrid
	 *            will not automatically display that data. It could be accessed
	 *            by custome javascrpt code on the client side, though.
	 * @param customSerializer
	 *            A {@link JavaScriptSerializer} used to do the JSON
	 *            serialization. Using this parameter custom serialization
	 *            properties can be provided.
	 */

	

	
}
