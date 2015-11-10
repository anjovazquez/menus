package com.ontimize.demo.client.modules.customer;

import com.ontimize.gui.BasicInteractionManager;


public class IFMCustomerRequest extends BasicInteractionManager {

	public IFMCustomerRequest() {
	}

	public IFMCustomerRequest(boolean update) {
		super(update);
	}

	public IFMCustomerRequest(boolean update, boolean detailForm) {
		super(update, detailForm);
	}

}
