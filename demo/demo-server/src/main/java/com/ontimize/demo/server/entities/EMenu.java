package com.ontimize.demo.server.entities;

import java.util.Properties;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.TableEntity;
import com.ontimize.locator.EntityReferenceLocator;

public class EMenu extends TableEntity {

	public EMenu(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port)
			throws Exception {
		super(locator, dbConnectionManager, port);
	}

	public EMenu(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port,
			Properties prop, Properties aliasProp) throws Exception {
		super(locator, dbConnectionManager, port, prop, aliasProp);
	}

}
