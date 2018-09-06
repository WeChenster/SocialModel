package com.zhuoan.ssh.bean;


public class PageUtil {

	private int pageIndex; // 当前页号
	private int pageSize; // 每页多少条记录
	private long totalCount; // 记录总数
	private int pageCount; // 总页数
	private String url; // 请求地址
	
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
	
	
	/**
	 * 格式化分页参数
	 * @param indexPage 当前页码
	 * @param pageSize 每页大小
	 * @param defIndex 默认页码
	 * @param defPageSize 默认每页大小
	 * @return
	 */
	public static PageUtil formatPage(String indexPage, String pageSize, int defIndex, int defPageSize){
		
		PageUtil page = new PageUtil();
		
		if(indexPage!=null && !"".equals(indexPage)){
			
			page.setPageIndex(Integer.valueOf(indexPage));
		}else{
			page.setPageIndex(defIndex);
		}
		
		if(pageSize!=null && !"".equals(pageSize)){
			
			page.setPageSize(Integer.valueOf(pageSize));
		}else{
			page.setPageSize(defPageSize);
		}
		
		return page;
	}
	
	
	/**
	 * 初始化分页参数
	 * @param indexPage
	 * @param pageSize
	 * @return
	 */
	public static PageUtil setPage( int indexPage, int pageSize){
		
		PageUtil page = new PageUtil();
		page.setPageIndex(indexPage);
		page.setPageSize(pageSize);
		return page;
	}
	
}
