package com.sxt.udig.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Pager<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3301245499720353750L;
	
	private int pageSize;
	
	private int currentPage;
	
	private long totalRecord;
	
	private int totalPage;
	
	private List<T> dataList;
	
	public Pager(int pageSize, int currentPage, long totalRecord,List<T> dataList) {
		this(pageSize,currentPage,totalRecord);
		this.dataList = dataList;
	}
	
	public Pager(int pageSize, int currentPage, long totalRecord) {
		super();
		this.totalRecord = totalRecord;
		if(totalRecord<pageSize){
			pageSize = (int) totalRecord;
		}
		if(pageSize <=0){
			pageSize=2;
		}
		this.pageSize = pageSize;
		this.totalPage = (int) (totalRecord/pageSize);
//		System.out.println(totalRecord%pageSize+":"+totalRecord+":"+pageSize);
		if(totalRecord%pageSize!=0 ){
			this.totalPage+=1;
		}
		if(this.totalPage==0)
		{
			this.totalRecord =1;
		}
		if(currentPage<1)
			currentPage = 1;
		else if(currentPage>this.totalPage){
			currentPage=this.totalPage;
		}
		this.currentPage = currentPage;
	}

	public Pager() {
		super();
	}

	public Pager(int pageSize, int currentPage, int totalRecord, int totalPage,
			List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Pager [pageSize=" + pageSize + ", currentPage=" + currentPage
				+ ", totalRecord=" + totalRecord + ", totalPage=" + totalPage
				+ "]"+"\n"+StringUtils.join(dataList,"\n");
		
		
	}
	
	
	
	
}
