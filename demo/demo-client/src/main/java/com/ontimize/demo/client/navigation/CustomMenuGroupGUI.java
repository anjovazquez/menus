package com.ontimize.demo.client.navigation;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ontimize.gui.field.NavigationMenu.MenuGroup;
import com.ontimize.gui.field.NavigatorMenuGUI.MenuGroupGUI;
import com.ontimize.gui.i18n.Internationalization;
import com.ontimize.gui.images.ImageManager;

public class CustomMenuGroupGUI extends MenuGroupGUI {

	/**
	 *
	 */

	private static final Logger		logger					= LoggerFactory.getLogger(CustomMenuGroupGUI.class);

	protected Image					leftImage;
	protected Image					centerImage;
	protected Image					rightImage;

	protected final static int		DEFAULT_FOOTER_HEIGHT	= 18;

	protected int					footerHeight			= CustomMenuGroupGUI.DEFAULT_FOOTER_HEIGHT;

	protected MenuFooterRenderer	menuFooterRenderer;

	public CustomMenuGroupGUI(Hashtable parameters) {
		super(parameters);
		this.separator = true;
		Object value = parameters.get("footerheight");
		if (value != null) {
			this.footerHeight = Integer.parseInt((String) value);
		}
		Rectangle bounds = this.getBounds();
		this.setMenuGroupBounds(bounds.x, bounds.y, bounds.width, bounds.height);

	}

	public void setMenuFooterRenderer(MenuFooterRenderer menuFooterRenderer) {
		this.menuFooterRenderer = menuFooterRenderer;
	}

	@Override
	public final void setMenuGroupBounds(int dimx, int dimy, int width, int height) {
		super.setMenuGroupBounds(dimx, dimy, width, height);
		if (this.footerHeight > 0) {
			height = height + this.footerHeight;
			this.setBounds(dimx, dimy, width, height);
		}
	}

	@Override
	public void reBound() {
		super.reBound();
		if (this.footerHeight > 0) {
			int height = this.getHeight() + this.footerHeight;
			this.setBounds(this.getX(), this.getY(), this.getWidth(), height);
		}
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if ((this.footerHeight > 0) && (this.menuFooterRenderer != null)) {
			this.menuFooterRenderer.setResourceBundle(this.bundle);
			Component comp = this.menuFooterRenderer.getMenuFooterRendererComponent(this);
			Insets insets = this.getInsets();
			comp.setSize(this.getWidth() - insets.left - insets.right, this.getHeaderHeight());
			comp.paint(graphics);
		}

	}

	public int getFooterHeight() {
		return this.footerHeight;
	}

	public void setFooterHeight(int footerHeight) {
		this.footerHeight = footerHeight;
	}

	/**
	 * Interface MenuHeaderRenderer.
	 * 
	 * @author Imatia Innovation.
	 * 
	 */
	public interface MenuFooterRenderer extends Internationalization {

		/**
		 * This method returns the renderer to the Header of the MenuGroup.
		 * 
		 * @param group
		 *            The MenuGroup where the Header is contained.
		 * @return a <code>Component</code>.
		 */
		Component getMenuFooterRendererComponent(MenuGroup group);

	}

	public static class DefaultMenuFooterRenderer extends JComponent implements MenuFooterRenderer {

		/**
		 * 9c0909
		 */
		private static final long	serialVersionUID	= -368238700898037780L;
		public static final String	LEFT_IMAGE			= "leftimage";
		public static final String	CENTER_IMAGE		= "centerimage";
		public static final String	RIGHT_IMAGE			= "rightimage";

		public final static String	LEFTIMAGE_URL		= "com/imatia/cec/management/client/images/navigatormenu/redversion/menuGroupFootLeft.png";
		public final static String	CENTERIMAGE_URL		= "com/imatia/cec/management/client/images/navigatormenu/redversion/menuGroupFootCenter.png";
		public final static String	RIGHTIMAGE_URL		= "com/imatia/cec/management/client/images/navigatormenu/redversion/menuGroupFootRight.png";

		protected Image				leftImage;
		protected Image				centerImage;
		protected Image				rightImage;

		public DefaultMenuFooterRenderer() {
			this.setOpaque(false);

			ImageIcon imageIcon = ImageManager.getIcon(DefaultMenuFooterRenderer.LEFTIMAGE_URL);
			if (imageIcon != null) {
				this.leftImage = imageIcon.getImage();
			}

			imageIcon = ImageManager.getIcon(DefaultMenuFooterRenderer.CENTERIMAGE_URL);
			if (imageIcon != null) {
				this.centerImage = imageIcon.getImage();
			}

			imageIcon = ImageManager.getIcon(DefaultMenuFooterRenderer.RIGHTIMAGE_URL);
			if (imageIcon != null) {
				this.rightImage = imageIcon.getImage();
			}

		}

		/**
		 * The ResourceBundle of the Header renderer.
		 */
		protected ResourceBundle	bundle	= null;

		protected MenuGroup			menuGroup;

		public Component getMenuFooterRendererComponent(MenuGroup group) {
			this.menuGroup = group;
			return this;
		}

		@Override
		protected void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);

			if (this.menuGroup != null) {
				Hashtable images = this.getImagesForMenuGroupFooter();
				if ((images != null) && !images.isEmpty()) {
					Image left = (Image) images.get(DefaultMenuFooterRenderer.LEFT_IMAGE);
					Image center = (Image) images.get(DefaultMenuFooterRenderer.CENTER_IMAGE);
					Image right = (Image) images.get(DefaultMenuFooterRenderer.RIGHT_IMAGE);

					int leftWidth = left.getWidth(this);
					int rightWidth = right.getWidth(this);
					int centerWidth = this.getWidth() - (leftWidth + rightWidth + this.menuGroup.getInsets().right);

					int dimy = this.menuGroup.getHeight() - ((CustomMenuGroupGUI) this.menuGroup).getFooterHeight();
					graphics.drawImage(left, this.menuGroup.getInsets().left, dimy, left.getWidth(this), left.getHeight(this), this);
					graphics.drawImage(center, left.getWidth(this), dimy, centerWidth, center.getHeight(this), this);
					graphics.drawImage(right, leftWidth + centerWidth, dimy, right.getWidth(this), right.getHeight(this), this);
				}
			}
		}

		protected Hashtable getImagesForMenuGroupFooter() {

			Hashtable images = new Hashtable();
			try {

				if (this.leftImage != null) {
					images.put(DefaultMenuFooterRenderer.LEFT_IMAGE, this.leftImage);
				}
				if (this.centerImage != null) {
					images.put(DefaultMenuFooterRenderer.CENTER_IMAGE, this.centerImage);
				}
				if (this.rightImage != null) {
					images.put(DefaultMenuFooterRenderer.RIGHT_IMAGE, this.rightImage);
				}
				return images;
			} catch (Exception excp) {
				CustomMenuGroupGUI.logger.debug(excp.getMessage());
			}
			return null;
		}

		// @Override
		public Vector getTextsToTranslate() {
			return null;
		}

		// @Override
		public void setComponentLocale(Locale locale) {
			// Do nothing
		}

		/**
		 * This method establishes the ResourceBundle to the renderer.
		 */
		// @Override
		public void setResourceBundle(ResourceBundle bundle) {
			this.bundle = bundle;
		}

	}

}
