package com.ontimize.demo.server.entities;

import java.util.Vector;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.SQLEntity;
import com.ontimize.gui.ApplicationManager;
import com.ontimize.locator.EntityReferenceLocator;

public class ESQLQueries extends SQLEntity {

	public ESQLQueries(EntityReferenceLocator locator, DatabaseConnectionManager dbManager, int port) throws Exception {
		super(locator, dbManager, port);
	}

	@Override
	public EntityResult execute(String sql, int sessionId) throws Exception {
		// Override to execute more than one sentence in the same remote call.
		// Sentences separated by ;
		Vector vQueries = ApplicationManager.getTokensAt(sql, ";");
		EntityResult res = null;
		for (int i = 0; i < vQueries.size(); i++) {
			res = super.execute(sql, sessionId);
		}
		return res;
	}

}
