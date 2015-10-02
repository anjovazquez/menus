package com.ontimize.demo.server.entities;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.TableEntity;
import com.ontimize.demo.server.ServerLocator;
import com.ontimize.locator.EntityReferenceLocator;
import com.ontimize.util.remote.BytesBlock;

public class EIngredient extends TableEntity {

	public EIngredient(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port)
			throws Exception {
		super(locator, dbConnectionManager, port);
	}

	public EIngredient(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port,
			Properties prop, Properties aliasProp) throws Exception {
		super(locator, dbConnectionManager, port, prop, aliasProp);
	}

}
