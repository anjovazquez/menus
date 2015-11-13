package com.ontimize.demo.client.navigation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;

import com.ontimize.gui.field.NavigationMenu.DefaultMenuItemRenderer;
import com.ontimize.gui.field.NavigationMenu.MenuGroup;
import com.ontimize.gui.field.NavigationMenu.MenuItem;

/**
 * Renderer which extends the {@link DefaultMenuItemRenderer} in order to be able to change some renderer parameters in the navigation menu, such as,
 * the painted text
 * 
 * @author Imatia S.L.
 * 
 */
@SuppressWarnings("serial")
public class CustomNavigationMenuRenderer extends DefaultMenuItemRenderer {

	private static final int	LEFT						= 20;

	static Color				selectionColor				= new Color(0x000099);
	static Color				selectedFontColorSelected	= Color.GRAY;
	static Color				selectedBackgroundColor		= Color.WHITE;
	static Color				backgroundColor				= Color.GRAY;

	protected boolean			selected;

	public CustomNavigationMenuRenderer() {
		super();
	}

	@Override
	public Component getMenuItemRendererComponent(MenuGroup menuGroup, MenuItem menuItem, boolean selected, boolean enable) {
		this.selected = selected;

		super.getMenuItemRendererComponent(menuGroup, menuItem, selected, enable);
		this.setOpaque(false);
		this.setBorder(null);
		// this.setBorder(BorderFactory.createEmptyBorder(0, CustomNavigationMenuRenderer.LEFT, 0, 0));
		// this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, new Color(0x000099)));
		this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, new Color(0x9c0909)));

		if (selected) {
			this.setForeground(CustomNavigationMenuRenderer.selectedFontColorSelected);

			// ImageIcon icon = new ImageIcon(menuItem.getIcon().getDescription().substring(0, menuItem.getIcon().getDescription().lastIndexOf(".")) +
			// "_selected.png");
			// menuItem.setIcon(icon);
		}

		return this;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		Rectangle rect = this.getBounds();
		if (!this.selected) {
			graphics.setColor(CustomNavigationMenuRenderer.backgroundColor);
		} else {
			graphics.setColor(CustomNavigationMenuRenderer.selectedBackgroundColor);
		}
		graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
		super.paintComponent(graphics);
	}

	@Override
	public Color getSelectionColor() {
		return CustomNavigationMenuRenderer.selectionColor;
	}

	public static void setSelectionColor(Color selectionColor) {
		CustomNavigationMenuRenderer.selectionColor = selectionColor;
	}

	public static Color getSelectedFontColorSelected() {
		return CustomNavigationMenuRenderer.selectedFontColorSelected;
	}

	public static void setSelectedFontColorSelected(Color selectedFontColorSelected) {
		CustomNavigationMenuRenderer.selectedFontColorSelected = selectedFontColorSelected;
	}

	public static Color getSelectedBackgroundColor() {
		return CustomNavigationMenuRenderer.selectedBackgroundColor;
	}

	public static void setSelectedBackgroundColor(Color selectedBackgroundColor) {
		CustomNavigationMenuRenderer.selectedBackgroundColor = selectedBackgroundColor;
	}

	public static Color getBackgroundColor() {
		return CustomNavigationMenuRenderer.backgroundColor;
	}

	public static void setBackgroundColor(Color backgroundColor) {
		CustomNavigationMenuRenderer.backgroundColor = backgroundColor;
	}

}
