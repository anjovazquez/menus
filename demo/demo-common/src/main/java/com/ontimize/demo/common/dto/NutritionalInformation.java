package com.ontimize.demo.common.dto;

import java.io.Serializable;

public class NutritionalInformation implements Serializable {
	
	private String componentId;
	private String componentName;
	private String comments;
	private String catDesc;
	private String unitDescription;
	private String unitId;
	private String measureUnitDesc;
	private String quantity;
	
	public NutritionalInformation() {
	}	

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public String getUnitDescription() {
		return unitDescription;
	}

	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getMeasureUnitDesc() {
		return measureUnitDesc;
	}

	public void setMeasureUnitDesc(String measureUnitDesc) {
		this.measureUnitDesc = measureUnitDesc;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "NutritionalInformation [componentId=" + componentId
				+ ", componentName=" + componentName + ", comments=" + comments
				+ ", catDesc=" + catDesc + ", unitDescription="
				+ unitDescription + ", unitId=" + unitId + ", measureUnitDesc="
				+ measureUnitDesc + ", quantity=" + quantity + "]";
	}
	
	

}
