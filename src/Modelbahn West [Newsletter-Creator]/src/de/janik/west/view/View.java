package de.janik.west.view;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import de.janik.util.imageUtil.ImageLoader;
import de.janik.west.listener.BasicListener;
import de.janik.west.listener.colorselectionListener.ColorSelectionListener;
import de.janik.west.listener.dragObjectSearchListener.DragObjectSearchListener;
import de.janik.west.listener.editorChangeListener.EditorChangeListener;
import de.janik.west.listener.menueListener.MenueListener;
import de.janik.west.listener.toolbarListener.ToolbarListener;
import de.janik.west.listener.webviewURL_Listener.WebViewURL_SearchListener;
import de.janik.west.view.dragObject.DragObject;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.listener.ViewCloseListener;
import de.janik.west.view.menue.Menue;
import de.janik.west.view.panel.Workspace;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class View implements I_ViewComponent
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final String				TITLE;

	private final JFrame					frame;

	private final List<ViewCloseListener>	closeListener;

	private final Menue						menue;

	private final Workspace					workspace;

	private final JDesktopPane				desktopPane;
	// <- Static ->
	static
	{
		TITLE = "Modelbahn West [Newsletter-Creator] ";
	}

	// <- Constructor ->
	public View(final int width, final int height)
	{
		frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setSize(width, height);
		setTitle("");

		closeListener = new ArrayList<>(1);

		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				closeListener.forEach(listener -> listener.close());
			}
		});

		menue = new Menue();
		frame.setJMenuBar(menue);

		workspace = new Workspace(width, height);

		frame.add(workspace, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);

		frame.setGlassPane(desktopPane);
		desktopPane.setVisible(true);

		final KeyboardFocusManager m = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		m.addPropertyChangeListener("focusOwner", e -> {
			if (e.getNewValue() != null)
			{
				for (Component c : desktopPane.getComponents())
					if (c instanceof InternalView)
						((InternalView) c).setFocus(true);
			}
			else
				for (Component c : desktopPane.getComponents())
					if (c instanceof InternalView)
						((InternalView) c).setFocus(false);
		});
	}

	// <- Abstract ->

	// <- Object ->
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof MenueListener)
			menue.addListener(listener);
		else
			if (listener instanceof ToolbarListener)
				workspace.addListener(listener);
			else
				if (listener instanceof ColorSelectionListener)
					workspace.addListener(listener);
				else
					if (listener instanceof DragObjectSearchListener)
						workspace.addListener(listener);
					else
						if (listener instanceof WebViewURL_SearchListener)
							workspace.addListener(listener);
						else
							if (listener instanceof EditorChangeListener)
								workspace.addListener(listener);
	};

	public void dispose()
	{
		frame.dispose();
	}

	public View registerCloseEvent(final ViewCloseListener listener)
	{
		closeListener.add(listener);

		return this;
	}

	public View center()
	{
		frame.setLocationRelativeTo(null);

		return this;
	}

	public View requestFocus()
	{
		frame.requestFocus();

		return this;
	}

	public void clearDragObjectSearchBar()
	{
		workspace.clearDragObjectSearchBar();
	}

	public void openURL(final String url)
	{
		workspace.openURL(url);
	}

	public void show(final InternalView internalView)
	{
		try
		{
			desktopPane.add(internalView);
			internalView.setVisible(true);
			internalView.setSelected(true);
			internalView.setLocation((getWidth() / 2) - internalView.getWidth() / 2, (getHeight() / 2) - (int) (internalView.getHeight() / 2));
		}
		catch (PropertyVetoException e)
		{
			e.printStackTrace();
		}
	}

	public void hide(final InternalView internalView)
	{
		internalView.setVisible(false);
		desktopPane.remove(internalView);
	}

	// <- Getter & Setter ->
	public void setVisibleDragObjects(final List<DragObject> dragObjects)
	{
		workspace.setVisibleDragObjects(dragObjects);
	}

	public int getWidth()
	{
		return frame.getWidth();
	}

	public int getHeight()
	{
		return frame.getHeight();
	}

	public View setMinimumSize(final int width, final int height)
	{
		frame.setMinimumSize(new Dimension(width, height));

		return this;
	}

	public View setTitle(final String title)
	{
		frame.setTitle(TITLE + title);

		return this;
	}

	public View setSize(final int width, final int height)
	{
		frame.setSize(width, height);

		Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

		if (rect.getWidth() <= width || rect.getHeight() <= height)
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		return this;
	}

	public View setVisible(final boolean visible)
	{
		frame.setVisible(visible);

		return this;
	}

	public void setIcon(final BufferedImage img)
	{
		frame.setIconImages(GetIconImages(img));
	}

	public void setButtonSendMailEnabled(final boolean enabled)
	{
		workspace.setButtonSendMailEnabled(enabled);
	}

	public void setButtonReloadEnabled(final boolean enabled)
	{
		workspace.setButtonReloadEnabled(enabled);
	}

	public void setButtonOpenExternEnabled(final boolean enabled)
	{
		workspace.setButtonOpenExternEnabled(enabled);
	}

	public void setButtonCutEnalbed(final boolean enabled)
	{
		workspace.setButtonCutEnalbed(enabled);
	}

	public void setButtonCopyEnalbed(final boolean enabled)
	{
		workspace.setButtonCopyEnalbed(enabled);
	}

	public void setButtonPasteEnalbed(final boolean enabled)
	{
		workspace.setButtonPasteEnalbed(enabled);
	}

	public void setButtonRedoEnabled(final boolean enabled)
	{
		workspace.setButtonRedoEnabled(enabled);
	}

	public void setButtonUndoEnabled(final boolean enabled)
	{
		workspace.setButtonUndoEnabled(enabled);
	}

	public void setButtonNewProjectEnabled(final boolean enabled)
	{
		workspace.setButtonNewProjectEnabled(enabled);
	}

	public void setButtonSaveEnabled(final boolean enabled)
	{
		workspace.setButtonSaveEnabled(enabled);
	}

	public void setButtonSaveAllEnabled(final boolean enabled)
	{
		workspace.setButtonSaveAllEnabled(enabled);
	}

	public void setButtonPrintEnabled(final boolean enabled)
	{
		workspace.setButtonPrintEnabled(enabled);
	}

	public void setSelectedColor(final Color color)
	{
		workspace.setSelectedColor(color);
	}

	public void setDragEditorPanel(final List<DragEditorPanel> dragEditorPanel, final boolean scrol)
	{
		workspace.setDragEditorPanel(dragEditorPanel, scrol);
	}

	public Workspace getWorkspace()
	{
		return workspace;
	}
	
	// <- Static ->
	private static List<Image> GetIconImages(final BufferedImage img)
	{
		List<Image> list = new ArrayList<>(5);

		for (int i = 0; i < 5; i++)
			list.add(ImageLoader.Resize(img, 16 << i, 16 << i));

		return list;
	}
}
