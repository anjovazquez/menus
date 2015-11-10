package com.ontimize.demo.server.entities;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Properties;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.TableEntity;
import com.ontimize.locator.EntityReferenceLocator;

public class ENotification extends TableEntity {

	public ENotification(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port)
			throws Exception {
		super(locator, dbConnectionManager, port);
	}

	public ENotification(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port,
			Properties prop, Properties aliasProp) throws Exception {
		super(locator, dbConnectionManager, port, prop, aliasProp);
	}

	@Override
	public EntityResult insert(Hashtable attributesValues, int sessionId, Connection con) throws Exception {
		EntityResult result = super.insert(attributesValues, sessionId, con);

		// NotificationManager notificac

		return result;
	}

}
