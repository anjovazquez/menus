package com.ontimize.demo.client;

import java.net.URL;
import java.util.Hashtable;

import com.ontimize.locator.PermissionReferenceLocator;

public class MenusReferenceLocator extends PermissionReferenceLocator {

	public MenusReferenceLocator(Hashtable params) {
		super(params);

	}

	public MenusReferenceLocator(boolean local, String entityPackageOrRMIServer, String entityReferenceLocatorName, int port, URL dbPropertiesUrl, URL autonumericalUrl) {
		super(local, entityPackageOrRMIServer, entityReferenceLocatorName, port, dbPropertiesUrl, autonumericalUrl);

	}

	public MenusReferenceLocator(boolean local, String entityPackageOrRMIServer, String entityReferenceLocatorName, int port, URL dbPropertiesUrl, URL autonumericalUrl,
			String loginEntity) {
		super(local, entityPackageOrRMIServer, entityReferenceLocatorName, port, dbPropertiesUrl, autonumericalUrl, loginEntity);

	}

	public MenusReferenceLocator(boolean local, String entityPackageOrRMIServer, String entityReferenceLocatorName, int port, URL dbPropertiesUrl, URL autonumericalUrl,
			String permissionEntity, String clientPermissionColumn) {
		super(local, entityPackageOrRMIServer, entityReferenceLocatorName, port, dbPropertiesUrl, autonumericalUrl, permissionEntity, clientPermissionColumn);

	}

	public MenusReferenceLocator(boolean local, String entityPackageOrRMIServer, String entityReferenceLocatorName, int port, URL dbPropertiesUrl, URL autonumericalUrl,
			String loginEntity, String permissionEntity, String clientPermissionColumn) {
		super(local, entityPackageOrRMIServer, entityReferenceLocatorName, port, dbPropertiesUrl, autonumericalUrl, loginEntity, permissionEntity, clientPermissionColumn);

	}

}
