package com.example.demo.Dto;

import paging.Criteria;
import paging.PaginationInfo;


public class CommonDto extends Criteria {

	/** 페이징 정보 */
	private PaginationInfo paginationInfo;

	public PaginationInfo getPaginationInfo() {
		return paginationInfo;
	}

	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}
	
	
}
