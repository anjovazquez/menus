package com.ontimize.demo.client.modules.sql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.ontimize.db.DirectSQLQueryEntity;
import com.ontimize.db.EntityResult;
import com.ontimize.gui.EntityResultViewer;
import com.ontimize.gui.Form;
import com.ontimize.gui.InteractionManager;
import com.ontimize.gui.ValueChangeListener;
import com.ontimize.gui.ValueEvent;
import com.ontimize.gui.button.Button;
import com.ontimize.gui.field.MemoDataField;
import com.ontimize.gui.field.ReferenceExtDataField;
import com.ontimize.gui.manager.IFormManager;
import com.ontimize.gui.table.Table;
import com.ontimize.locator.EntityReferenceLocator;

/**
 * This class let manage the interaction with the "SQL queries" form and
 * implement their functions. For that fact, the it extends the
 * {@link InteractionManager}
 *
 * @author Imatia S.L.
 *
 */
public class IFMSQLQueries extends InteractionManager {

	protected ReferenceExtDataField selectQueryField = null;

	protected Button bExecute = null;

	protected MemoDataField memoQuery = null;

	protected EntityResultViewer ervResults = null;

	protected Table ervResultsDynamicTable = null;

	@Override
	public void registerInteractionManager(Form f, IFormManager fm) {
		super.registerInteractionManager(f, fm);

		// get general references of this form/screen:
		this.memoQuery = (MemoDataField) this.managedForm.getDataFieldReference("query");
		this.ervResults = (EntityResultViewer) this.managedForm.getDataFieldReference("results");
		this.ervResultsDynamicTable = (Table) this.managedForm.getDataFieldReference("resultsDynamicTable");

		// execute button listener which must execute the query when it is
		// pressed:
		this.bExecute = this.managedForm.getButton("execute");
		if (this.bExecute != null) {
			this.bExecute.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					IFMSQLQueries.this.executeQuery();
					if (IFMSQLQueries.this.selectQueryField != null) {
						IFMSQLQueries.this.selectQueryField.deleteData();
					}
				}
			});
		}

		// listener for the cross button which must select a stored query to
		// execute if it is pressed:
		if (this.selectQueryField != null) {
			this.selectQueryField.addValueChangeListener(new ValueChangeListener() {

				@Override
				public void valueChanged(ValueEvent e) {
					if (e.getType() == ValueEvent.USER_CHANGE) {
						IFMSQLQueries.this.selectQueryToExecute();
					}
				}
			});
		}

		if (this.memoQuery != null) {
			InputMap inMap = this.memoQuery.getDataField().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
			ActionMap actMap = this.memoQuery.getDataField().getActionMap();
			KeyStroke ksExecute = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, true);
			inMap.put(ksExecute, "Execute");
			actMap.put("Execute", new ExecuteAction());
		}
	}

	protected void selectQueryToExecute() {
		Object value = this.selectQueryField.getValue();
		if (value != null) {
			Hashtable codeValues = this.selectQueryField.getCodeValues(value);
			if (codeValues.containsKey("SQLQUERY")) {
				String queryText = (String) codeValues.get("SQLQUERY");
				if (this.memoQuery.getValue() != null) {
					boolean deleteCurrentQuery = this.managedForm.question("DELETE_CURRENT_QUERY");
					if (!deleteCurrentQuery) {
						return;
					}
				}
				this.memoQuery.setValue(queryText);
			}
		}
	}

	protected void executeQuery() {
		String sql = (String) this.memoQuery.getValue();
		if (sql != null) {
			try {
				EntityReferenceLocator locator = this.formManager.getReferenceLocator();
				DirectSQLQueryEntity eSQL = (DirectSQLQueryEntity) locator.getEntityReference("ESQLQueries");
				EntityResult res = eSQL.execute(sql, locator.getSessionId());
				if (res.getCode() == EntityResult.OPERATION_WRONG) {
					if (this.ervResults != null) {
						this.ervResults.deleteData();
					}
					this.ervResultsDynamicTable.deleteData();
					this.managedForm.message("Query error. " + res.getMessage(), Form.ERROR_MESSAGE);
					return;
				}
				if (res.isEmpty()) {
					this.managedForm.message("No data found", Form.WARNING_MESSAGE);
				}
				if (this.ervResults != null) {
					this.ervResults.setValue(res);
				}
				this.ervResultsDynamicTable.setValue(res);
			} catch (Exception ex) {}
		} else {
			this.managedForm.message("Write a new SQL Query to execute", Form.WARNING_MESSAGE);
		}
	}

	@Override
	public void setQueryInsertMode() {
		this.setUpdateMode();
	}

	@Override
	public void setUpdateMode() {
		super.setUpdateMode();
		this.managedForm.enableButtons();
		this.managedForm.enableDataFields();
	}

	protected class ExecuteAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			IFMSQLQueries.this.executeQuery();
		}

	}

}

