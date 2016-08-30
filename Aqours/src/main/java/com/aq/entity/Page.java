package com.aq.entity;

import java.util.ArrayList;
import java.util.List;

public class Page {

	private int pageNo; //当前页
	private int pageSize; //每页大小
	private int totalPage; //总页数
	private int totalRecord; //总条数
	private Integer[] slide; //控制分页
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (this.totalRecord + this.pageSize -1)/this.pageSize;
		if(this.pageNo > this.totalPage){
			this.pageNo = 1;
		}
		this.setSlide();
	}
	public Integer[] getSlide() {
		return slide;
	}
	public void setSlide(Integer[] slide) {
		this.slide = slide;
	}
	public void setSlide(){
		List<Integer> lst = new ArrayList <Integer>();
		if(this.totalPage<=20){
			for(int i=1;i<=this.totalPage;i++){
				lst.add(i);
			}
		}else{
			if(this.pageNo<10){
				for(int i=1;i<=10;i++){
					lst.add(i);
				}
				
				lst.add(0);
				
				for(int i=this.totalPage-2;i<=this.totalPage;i++){
					lst.add(i);
				}
			}else if(this.pageNo>this.totalPage-9){
				for(int i=1;i<=3;i++){
					lst.add(i);
				}
				
				lst.add(0);
				
				for(int i=this.totalPage-9;i<=this.totalPage;i++){
					lst.add(i);
				}
			}else{
				for(int i=1;i<=3;i++){
					lst.add(i);
				}
				
				lst.add(0);
				
				for(int i=0;i<10;i++){
					lst.add((this.pageNo-4+i));
				}
				
				lst.add(0);
				
				for(int i=this.totalPage-2;i<=this.totalPage;i++){
					lst.add(i);
				}
			}
		}
		this.slide = (Integer[]) lst.toArray(new Integer[lst.size()]);
	}
	
	public Page(int pageNo, int pageSize, int totalPage, int totalRecord, Integer[] slide) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.totalRecord = totalRecord;
		this.slide = slide;
	}
	
	public Page(){}
}
