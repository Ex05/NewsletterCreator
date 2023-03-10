package de.janik.west.view.panel;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.NORTHEAST;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JSplitPane;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.colorselectionListener.ColorSelectionListener;
import de.janik.west.listener.dragObjectSearchListener.DragObjectSearchListener;
import de.janik.west.listener.editorChangeListener.EditorChangeListener;
import de.janik.west.listener.toolbarListener.ToolbarListener;
import de.janik.west.listener.webviewURL_Listener.WebViewURL_SearchListener;
import de.janik.west.view.dragObject.DragObject;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.toolbar.Toolbar;
import de.janik.west.view.toolbar.toolbars.SearchToolBar;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class Workspace extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JSplitPane	paneLeft;
	private final JSplitPane	paneRight;

	private final LeftPanel		panelLeft;
	private final CenterPanel	panelCenter;
	private final RightPanel	panelRight;

	private final Toolbar		toolbar;

	private final SearchToolBar	searchToolBar;

	// <- Static ->

	// <- Constructor ->
	public Workspace(final int width, final int height)
	{
		setLayout(new GridBagLayout());

		panelLeft = new LeftPanel();
		panelCenter = new CenterPanel();
		panelRight = new RightPanel();

		paneRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panelCenter, panelRight);
		paneRight.setDividerLocation(width - 510);
		paneRight.setDividerSize(7);
		paneRight.setResizeWeight(1.0);
		paneRight.setEnabled(false);

		paneLeft = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, panelLeft, paneRight);
		paneLeft.setDividerLocation(200);
		paneLeft.setDividerSize(7);
		paneLeft.setResizeWeight(0.0);
		paneLeft.setEnabled(false);

		toolbar = new Toolbar();
		searchToolBar = new SearchToolBar();

		// TODO: Fix Hacky-Wacky_Spaghetticode for SearchBar
		add(searchToolBar, new GridBagConstraints(0, 0, 0, 0, 0.0, 0.0, NORTHEAST, NONE, new Insets(18, 0, 0, 1), 100, 0));
		add(toolbar, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		add(paneLeft, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	public void openURL(final String url)
	{
		panelCenter.openURL(url);
	}

	public void clearDragObjectSearchBar()
	{
		panelRight.clearDragObjectSearchBar();
	}

	@Override
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof ToolbarListener)
			toolbar.addListener(listener);
		else
			if (listener instanceof ColorSelectionListener)
				panelRight.addListener(listener);
			else
				if (listener instanceof DragObjectSearchListener)
					panelRight.addListener(listener);
				else
					if (listener instanceof WebViewURL_SearchListener)
						panelCenter.addListener(listener);
					else
						if (listener instanceof EditorChangeListener)
							panelCenter.addListener(listener);
	}

	// <- Getter & Setter ->
	public void setVisibleDragObjects(final List<DragObject> dragObjects)
	{
		panelRight.setVisibleDragObjects(dragObjects);
	}

	public void setButtonSendMailEnabled(final boolean enabled)
	{
		toolbar.setButtonSendMailEnabled(enabled);
	}

	public void setButtonReloadEnabled(final boolean enabled)
	{
		toolbar.setButtonReloadEnabled(enabled);
	}

	public void setButtonOpenExternEnabled(final boolean enabled)
	{
		toolbar.setButtonOpenExternEnabled(enabled);
	}

	public void setButtonCutEnalbed(final boolean enabled)
	{
		toolbar.setButtonCutEnalbed(enabled);
	}

	public void setButtonCopyEnalbed(final boolean enabled)
	{
		toolbar.setButtonCopyEnalbed(enabled);
	}

	public void setButtonPasteEnalbed(final boolean enabled)
	{
		toolbar.setButtonPasteEnalbed(enabled);
	}

	public void setButtonRedoEnabled(final boolean enabled)
	{
		toolbar.setButtonRedoEnabled(enabled);
	}

	public void setButtonUndoEnabled(final boolean enabled)
	{
		toolbar.setButtonUndoEnabled(enabled);
	}

	public void setButtonNewProjectEnabled(final boolean enabled)
	{
		toolbar.setButtonNewProjectEnabled(enabled);
	}

	public void setButtonSaveEnabled(final boolean enabled)
	{
		toolbar.setButtonSaveEnabled(enabled);
	}

	public void setButtonSaveAllEnabled(final boolean enabled)
	{
		toolbar.setButtonSaveAllEnabled(enabled);
	}

	public void setButtonPrintEnabled(final boolean enabled)
	{
		toolbar.setButtonPrintEnabled(enabled);
	}

	public void setSelectedColor(final Color color)
	{
		panelRight.setSelectedColor(color);
	}

	public void setDragEditorPanel(final List<DragEditorPanel> dragEditorPanel, final boolean scrol)
	{
		panelCenter.setDragEditorPanel(dragEditorPanel, scrol);
	}

	public CenterPanel getCenterPanel()
	{
		return panelCenter;
	}

	// <- Static ->

}
