package com.ontimize.demo.server.entities;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.TableEntity;
import com.ontimize.locator.EntityReferenceLocator;

public class EProductIngredient extends TableEntity {

	public EProductIngredient(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port)
			throws Exception {
		super(locator, dbConnectionManager, port);
	}

	public EProductIngredient(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port,
			Properties prop, Properties aliasProp) throws Exception {
		super(locator, dbConnectionManager, port, prop, aliasProp);
	}
	
	@Override
	public EntityResult query(Hashtable keyValues, Vector attrValues, int sessionId, Connection con)
			throws Exception {		
		return super.query(keyValues, attrValues, sessionId, con);
	}

}
