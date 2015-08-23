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

public class EProduct extends TableEntity {

	public EProduct(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port)
			throws Exception {
		super(locator, dbConnectionManager, port);
	}

	public EProduct(EntityReferenceLocator locator, DatabaseConnectionManager dbConnectionManager, int port,
			Properties prop, Properties aliasProp) throws Exception {
		super(locator, dbConnectionManager, port, prop, aliasProp);
	}

	@Override
	public EntityResult insert(Hashtable keyValues, int sessionId, Connection con) throws Exception {
		if (keyValues.containsKey("productImageName") && keyValues.containsKey("productImage")) {
			try {
				((ServerLocator) locator).saveImage((String) keyValues.get("productImageName"),
						(BytesBlock) keyValues.remove("productImage"));
			} catch (Exception e) {
				e.printStackTrace();
//				throw new Exception();
			}
		}
		return super.insert(keyValues, sessionId, con);

	}

	@Override
	public EntityResult query(Hashtable keysValues, Vector attributes, int sessionId, Connection con) throws Exception {

		EntityResult result = super.query(keysValues, attributes, sessionId, con);

		EntityResult res = new EntityResult(getColumns());
		for (int i = 0; i < result.calculateRecordNumber(); i++) {
			Hashtable valProd = result.getRecordValues(i);
			String prodImg = (String) valProd.get("productImageName");

			if (prodImg != null) {
				try {
					BytesBlock image = ((ServerLocator) locator).getImage(prodImg);
					valProd.put("productImage", image);
				} catch (Exception e) {
					e.printStackTrace();
//					throw new Exception();
				}
			}

			res.addRecord(valProd);
		}

		return res;
	}

}
