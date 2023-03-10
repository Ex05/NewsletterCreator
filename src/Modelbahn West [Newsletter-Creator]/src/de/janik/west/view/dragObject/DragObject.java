package de.janik.west.view.dragObject;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.NORTHWEST;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.RootPaneContainer;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.panel.ViewPanel;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class DragObject extends ViewPanel
{
	// <- Public ->

	// <- Protected ->
	protected final DragObjectPanelListener	listener;

	// <- Private->
	private static final long				serialVersionUID	= 1l;

	private static final Font				FONT_TITLE;

	private final JLabel					labelTitle;
	private final JLabel					labelImage;

	private final String					title;
	// <- Static ->
	static
	{
		FONT_TITLE = new Font("Verdana", Font.BOLD, 14);
	}

	// <- Constructor ->
	public DragObject(final String title, final ImageIcon icon, final DragObjectPanelListener listener)
	{
		super();

		this.title = title;
		this.listener = listener;

		setOpaque(false);

		labelTitle = new JLabel("<html><u>" + getTitle() + "</u></html>");
		labelTitle.setForeground(Color.BLACK);
		labelTitle.setFont(FONT_TITLE);

		labelImage = new JLabel(icon);
		labelImage.setName(title);
		// labelImage.setPreferredSize(new Dimension(240, 80));

		add(labelTitle, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, NORTHWEST, NONE, new Insets(0, 0, 1, 0), 0, 0));
		add(labelImage, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, NORTHWEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		labelImage.addMouseListener(listener);

		labelImage.addMouseListener(new MouseAdapter()
		{
			private Cursor	cursor	= new Cursor(Cursor.DEFAULT_CURSOR);

			@Override
			public void mouseEntered(MouseEvent e)
			{
				RootPaneContainer root = (RootPaneContainer) labelImage.getTopLevelAncestor();
				cursor = root.getGlassPane().getCursor();
				root.getGlassPane().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				RootPaneContainer root = (RootPaneContainer) labelImage.getTopLevelAncestor();
				root.getGlassPane().setCursor(cursor);
			}
		});
	}

	// <- Abstract ->
	public abstract DragEditorPanel getDragEditorPanel();

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->
	public String getTitle()
	{
		return title;
	}

	// <- Static ->
}
