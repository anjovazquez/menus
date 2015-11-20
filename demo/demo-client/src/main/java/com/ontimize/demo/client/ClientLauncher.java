package com.ontimize.demo.client;

import com.ontimize.gui.ApplicationLauncher;
import com.ontimize.gui.ApplicationToolBar;
import com.ontimize.permission.PermissionButton;
import com.ontimize.util.math.MathExpressionParser;
import com.ontimize.util.math.MathExpressionParserFactory;

public class ClientLauncher {

	private static final String	PERMISSION_BUTTON_PROPERTY	= "com/ontimize/demo/client/conf/PermissionButton.properties";

	public static void main(String[] args) {

		PermissionButton.setPropertyFile(ClientLauncher.PERMISSION_BUTTON_PROPERTY);
		ApplicationToolBar.DEFAULT_BUTTON_SIZE=30;
		System.setProperty(MathExpressionParserFactory.MATH_EXPRESSION_PARSER_PROPERTY, MathExpressionParser.MESP);
		ApplicationLauncher.main(args);
	}
}