package de.janik.west.util;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import de.janik.affine.AffineChiffre;
import de.janik.affine.Alphabet;
import de.janik.util.database.Database;
import de.janik.util.database.user.DB_User;
import de.janik.util.database.user.Password;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class Resources
{
	// <- Public ->
	public static BufferedImage			IMAGE_LAUNCHER_LOGO;
	public static BufferedImage			IMAGE_VIEW_ICON;

	public static ImageIcon				IMAGEICON_NEW_PROJECT;
	public static ImageIcon				IMAGEICON_SAVE;
	public static ImageIcon				IMAGEICON_SAVE_ALL;
	public static ImageIcon				IMAGEICON_PRINT;
	public static ImageIcon				IMAGEICON_UNDO;
	public static ImageIcon				IMAGEICON_REDO;
	public static ImageIcon				IMAGEICON_CUT;
	public static ImageIcon				IMAGEICON_COPY;
	public static ImageIcon				IMAGEICON_PASTE;
	public static ImageIcon				IMAGEICON_RELOAD;
	public static ImageIcon				IMAGEICON_OPEN_EXTERN;
	public static ImageIcon				IMAGEICON_SENDMAIL;
	public static ImageIcon				IMAGEICON_DRAG_N_NDROP;
	public static ImageIcon				IMAGEICON_PREVIEW_HEADER;
	public static ImageIcon				IMAGEICON_PREVIEW_FOOTER;
	public static ImageIcon				IMAGEICON_PREVIEW_TEXT;
	public static ImageIcon				IMAGEICON_PREVIEW_TEXT_WITH_TITLE;
	public static ImageIcon				IMAGEICON_PREVIEW_IMAGE;
	public static ImageIcon				IMAGEICON_PREVIEW_IMAGE_WITH_TITLE;
	public static ImageIcon				IMAGEICON_PREVIEW_IMAGE_SMALL_WITH_TITLE;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_HEADER;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_NO_IMAGE;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_NO_IMAGE_SMALL;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_BUY_NOW;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_UP;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_UP_MOUSEOVER;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_DOWN;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_DOWN_MOUSEOVER;
	public static ImageIcon				IMAGEICON_INTERNAL_VIEW_CLOSE;
	public static ImageIcon				IMAGEICON_INTERNAL_VIEW_CLOSE_MOUSEOVER;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_CLOSE;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_CLOSE_MOUSEOVER;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_CROSS;
	public static ImageIcon				IMAGEICON_DRAG_EDITOR_CROSS_MOUSEOVER;

		// 
	public static final Database		DATABASE_WEST		= new Database("localhost", 3306, "west");

	private static final AffineChiffre	CHIFFRE				= new AffineChiffre(1, 4, Alphabet.UNICODE);

	public static final DB_User			DB_USER_READONLY	= new DB_User("root", new Password(new byte[] {114, 109, 103, 105, 36, 120, 118, 125, 36, 101, 119, 119, 108, 115, 112, 105}, CHIFFRE));

	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	private Resources()
	{
		throw new IllegalStateException("Do not instanciate !~!");
	}

	// <- Abstract ->
	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
