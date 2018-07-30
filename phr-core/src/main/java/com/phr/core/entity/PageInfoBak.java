package com.phr.core.entity;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class PageInfoBak<T> implements Serializable {

	private int pageNo = 1;
	private int pageSize = 10;
	private int total = 0;
	private List<T> rows;

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		if(pageSize <1){
			pageSize=1;
		}else if(pageSize >100){
//			pageSize =100;
		}
		this.pageSize = pageSize;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getStart() {
		return (this.pageNo - 1) * this.pageSize;
	}

}
