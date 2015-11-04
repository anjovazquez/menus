package com.ontimize.demo.common;


import java.util.List;

import com.ontimize.demo.common.dto.NutritionalInformationCategory;
import com.ontimize.locator.EntityReferenceLocator;

public interface IBenMeSabeReferenceLocator extends EntityReferenceLocator {

	List<NutritionalInformationCategory> getNutritionalInformation(String ingredientId) throws Exception;
	
}
