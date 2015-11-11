package com.ontimize.demo.server.entities;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Properties;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.TableEntity;
import com.ontimize.demo.server.notification.NotificationManager;
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
	public EntityResult insert(Hashtable kv, int sessionId, Connection con) throws Exception {
		EntityResult result = super.insert(kv, sessionId, con);
		
		String token = (String)kv.get("token");
		String jsonData = (String)kv.get("data");
		NotificationManager.getInstance().sendNotificationToUser(token, jsonData);

		return result;
	}

}
