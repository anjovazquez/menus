package com.ontimize.demo.client.navigation;

import javax.swing.ImageIcon;

import com.ontimize.gui.field.NavigationMenu.MenuItem;


public class CustomMenuItem extends MenuItem {

	public CustomMenuItem(String manager, ImageIcon icon) {
		super(manager, icon);
	}

	public CustomMenuItem(String manager, ImageIcon icon, boolean visible, boolean enabled) {
		super(manager, icon, visible, enabled);
	}

}
