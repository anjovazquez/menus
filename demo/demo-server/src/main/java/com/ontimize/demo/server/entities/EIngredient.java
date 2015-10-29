package com.ontimize.demo.server.entities;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.ontimize.db.DatabaseConnectionManager;
import com.ontimize.db.EntityResult;
import com.ontimize.db.TableEntity;
import com.ontimize.demo.server.ServerLocator;
import com.ontimize.demo.server.dto.Ingredient;
import com.ontimize.demo.server.ws.bedca.BedcaWSHelper;
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
	
	@Override
	public EntityResult query(Hashtable keysValues, Vector attributes,
			int sessionId, Connection con) throws Exception {
		EntityResult result = super.query(keysValues, attributes, sessionId, con);
		if(result.calculateRecordNumber()==0){
			saveIngredients(requestIngredientsWS(), sessionId, con);
		}		
		return super.query(keysValues, attributes, sessionId, con);
	}
	
	private List<Ingredient> requestIngredientsWS() throws Exception {
		return BedcaWSHelper.getInstance().getIngredients();
	}
	
	private void saveIngredients(List<Ingredient> ingredients, int sessionId, Connection con) throws Exception {
		for(Ingredient ing: ingredients){
			Hashtable kv = new Hashtable();
			kv.put("idIngredient", ing.getIdIngredient());
			kv.put("ingredientName", ing.getIngredientName());
			
			EntityResult result = super.insert(kv, sessionId, con);
			if(result.getCode()==EntityResult.OPERATION_WRONG){
				throw new Exception("M_ERROR_PROCESSING_INGREDIENTS");
			}
		}
	}

}
