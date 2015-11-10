package com.ontimize.demo.client.modules.customer;

import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.manager.IFormManager;


public class IFMFilterCustomerRequest extends BasicInteractionManager {

	public IFMFilterCustomerRequest() {
	}

	public IFMFilterCustomerRequest(boolean update) {
		super(update);
	}

	public IFMFilterCustomerRequest(boolean update, boolean detailForm) {
		super(update, detailForm);
	}

	@Override
	public void registerInteractionManager(Form form, IFormManager formManager) {
		super.registerInteractionManager(form, formManager);
	}

	@Override
	public void setQueryInsertMode() {
		super.setQueryInsertMode();
		this.setUpdateMode();
	}

	@Override
	public void setUpdateMode() {
		super.setUpdateMode();
		this.managedForm.enableDataFields();
	}

}
