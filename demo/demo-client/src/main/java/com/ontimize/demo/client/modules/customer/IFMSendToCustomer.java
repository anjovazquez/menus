package com.ontimize.demo.client.modules.customer;

import com.ontimize.annotation.FormComponent;
import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.button.Button;
import com.ontimize.gui.manager.IFormManager;

public class IFMSendToCustomer extends BasicInteractionManager {
	
	@FormComponent(attr="bSend")
	private Button bSend;
	
	@Override
	public void registerInteractionManager(Form form, IFormManager formManager) {
		super.registerInteractionManager(form, formManager);
		bSend.addActionListener(new InsertListener());
	}
	
	@Override
	public void setQueryInsertMode() {
		super.setQueryInsertMode();
		setInsertMode();
	}
	
	@Override
	public void setInsertMode() {
		super.setInsertMode();
		managedForm.enableButton(bSend.getKey());
	}
	

}
