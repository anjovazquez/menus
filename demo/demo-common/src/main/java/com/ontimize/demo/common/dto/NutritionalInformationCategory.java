package com.ontimize.demo.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NutritionalInformationCategory implements Serializable {

	private String category;
	
	private List<NutritionalInformation> nutritionalInformationList;

	public NutritionalInformationCategory(String category,
			List<NutritionalInformation> nutritionalInformationList) {
		super();
		this.category = category;
		this.nutritionalInformationList = nutritionalInformationList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<NutritionalInformation> getNutritionalInformationList() {
		return nutritionalInformationList;
	}

	public void setNutritionalInformationList(
			ArrayList<NutritionalInformation> nutritionalInformationList) {
		this.nutritionalInformationList = nutritionalInformationList;
	}

	@Override
	public String toString() {
		return "NutritionalInformationCategory [category=" + category
				+ ", nutritionalInformationList=" + nutritionalInformationList
				+ "]";
	} 	
	
}
