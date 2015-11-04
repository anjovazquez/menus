package com.ontimize.demo.common;

import java.rmi.Remote;

public interface IProductIngredient extends Remote {
	
	public void getIngredientsByProduct(Object productId) throws Exception;
	
}
