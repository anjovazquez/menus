package com.ontimize.demo.server.dto;

public class Ingredient {

	private Number idIngredient;
	private String ingredientName;	
	
	public Ingredient(){}
	
	public Ingredient(Number idIngredient, String ingredientName) {
		super();
		this.idIngredient = idIngredient;
		this.ingredientName = ingredientName;
	}
	
	public Number getIdIngredient() {
		return idIngredient;
	}
	
	public void setIdIngredient(Number idIngredient) {
		this.idIngredient = idIngredient;
	}
	
	public String getIngredientName() {
		return ingredientName;
	}
	
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	@Override
	public String toString() {
		return "Ingredient [idIngredient=" + idIngredient + ", ingredientName="
				+ ingredientName + "]";
	}
	
}
