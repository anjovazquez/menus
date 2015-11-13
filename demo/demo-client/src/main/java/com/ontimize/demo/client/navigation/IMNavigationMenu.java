package com.ontimize.demo.client.navigation;

import com.ontimize.gui.BasicInteractionManager;
import com.ontimize.gui.Form;
import com.ontimize.gui.InteractionManager;
import com.ontimize.gui.manager.IFormManager;

/**
 * This class let manage the interaction with the "navigation menu" component and it also implements some common functions. For that fact, the it
 * extends the {@link BasicInteractionManager} Moreover, additional operations are added, such as, insert a custom renderer for the navigation menu
 * 
 * @author Imatia S.L.
 * 
 */
public class IMNavigationMenu extends InteractionManager {

	// protected MenuItemRenderer menuItemRenderer = new CustomNavigationMenuRenderer();

	@Override
	public void registerInteractionManager(Form form, IFormManager formManager) {
		super.registerInteractionManager(form, formManager);

		// NavigationMenu menu = (NavigationMenu) this.managedForm.getElementReference("menu");
		// menu.setMenuItemRenderer(this.menuItemRenderer);
		// if (menu instanceof CustomNavigatorMenuGUI) {
		// menu.setOpaque(false);
		// MenuFooterRenderer menuFooterRenderer = new DefaultMenuFooterRenderer();
		// ((CustomNavigatorMenuGUI) menu).setMenuFooterRenderer(menuFooterRenderer);
		// }
	}
}
