package com.ontimize.demo.client.modules.products;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ontimize.annotation.FormComponent;
import com.ontimize.gui.ApplicationManager;
import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.button.Button;
import com.ontimize.gui.manager.IFormManager;
import com.ontimize.gui.table.Table;
import com.ontimize.gui.table.TableButton;

public class IFMCompanyOrder extends BasicInteractionManager {

	@FormComponent(attr = "EOrder")
	Table tOrder;

	@FormComponent(attr = "EOrderLine")
	Table tOrderLine;

	@FormComponent(attr = "bSearch")
	Button bSearch;

	@Override
	public void registerInteractionManager(Form form, IFormManager formManager) {
		super.registerInteractionManager(form, formManager);

		this.tOrder.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (IFMCompanyOrder.this.tOrder.getSelectedRow() != -1) {
					IFMCompanyOrder.this.managedForm.setDataFieldValue(
							"idOrder",
							IFMCompanyOrder.this.tOrder.getRowData(
									IFMCompanyOrder.this.tOrder
											.getSelectedRow()).get("idOrder"));
					IFMCompanyOrder.this.tOrderLine.refresh();
				}
			}
		});

		this.bSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				IFMCompanyOrder.this.tOrder.refresh();
			}
		});

		TableButton notificationButton = new TableButton(
				ApplicationManager
						.getIcon("com/ontimize/demo/client/images/mail arrow.png"));
		notificationButton.setToolTipText("Mensaje a cliente");
		notificationButton.addActionListener(new MessageListener());
		tOrder.addButtonToControls(notificationButton);
	}

	private class MessageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if(tOrder.getSelectedRow()!=-1){
				int selRow = tOrder.getSelectedRow();
				Hashtable orderData = tOrder.getRowData(selRow);
			Form form = managedForm.getFormManager().getFormCopy(
					"formsendtocustomer.xml");
			if(orderData.get("customerToken")!=null)
				form.setDataFieldValue("token", orderData.get("customerToken"));
			JDialog jDialog = form.putInModalDialog(managedForm);
			jDialog.pack();
			jDialog.setVisible(true);
			}
		}

	}

	@Override
	public void setInitialState() {
		super.setInitialState();
		this.setUpdateMode();
	}

	@Override
	public void setUpdateMode() {
		super.setUpdateMode();
		this.managedForm.enableTables();
		this.managedForm.enableButton(this.bSearch.getKey());
	}

}
