package com.ontimize.demo.client.navigation;

import java.util.Hashtable;

import com.ontimize.demo.client.navigation.CustomMenuGroupGUI.MenuFooterRenderer;
import com.ontimize.gui.field.NavigatorMenuGUI;

public class CustomNavigatorMenuGUI extends NavigatorMenuGUI {

	protected MenuFooterRenderer	menuFooterRenderer;

	public CustomNavigatorMenuGUI(Hashtable params) throws Exception {
		super(params);
	}

	@Override
	public void init(Hashtable arguments) throws Exception {
		String definition = System.getProperty("com.ontimize.gui.lafstyle");
		if (definition != null) {
			arguments.put("src", "com/ontimize/demo/client/navigation/navigation.xml");
		}
		super.init(arguments);
	}

	public void setMenuFooterRenderer(MenuFooterRenderer menuFooterRenderer) {
		this.menuFooterRenderer = menuFooterRenderer;

		for (int i = 0; i < this.menuList.size(); i++) {
			Object oMG = this.menuList.get(i);
			if (oMG instanceof CustomMenuGroupGUI) {
				((CustomMenuGroupGUI) oMG).setMenuFooterRenderer(menuFooterRenderer);
			}
		}
	}

}
