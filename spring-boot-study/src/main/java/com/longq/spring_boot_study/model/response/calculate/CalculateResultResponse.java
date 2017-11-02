package com.longq.spring_boot_study.model.response.calculate;

import java.util.List;

import com.longq.spring_boot_study.model.response.BaseResponse;

public class CalculateResultResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Integer> result;

	public List<Integer> getResult() {
		return result;
	}

	public void setResult(List<Integer> result) {
		this.result = result;
	}
	
	

}
