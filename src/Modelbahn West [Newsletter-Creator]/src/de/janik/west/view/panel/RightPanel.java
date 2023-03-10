package de.janik.west.view.panel;

// <- Import ->
// <- Static_Import ->
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.HORIZONTAL;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import de.janik.util.searchBar.SearchBar;
import de.janik.west.listener.BasicListener;
import de.janik.west.listener.colorselectionListener.ColorSelectionListener;
import de.janik.west.listener.dragObjectSearchListener.DragObjectSearchListener;
import de.janik.west.view.dragObject.DragObject;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class RightPanel extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long		serialVersionUID	= 1l;

	private final FontPanel			fontPanel;

	private final ColorChooserPanel	colorChooser;

	private final SearchBar			searchBar;

	private final DragObjectPanel	dragObjectPanel;

	// <- Static ->

	// <- Constructor ->
	public RightPanel()
	{
		super();
		// setBackground(Color.PINK);

		fontPanel = new FontPanel();
		colorChooser = new ColorChooserPanel();
		searchBar = new SearchBar("Search", "OK", true);
		dragObjectPanel = new DragObjectPanel();

		add(fontPanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZONTAL, new Insets(1, 2, 1, 2), 0, 0));
		add(colorChooser, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, CENTER, HORIZONTAL, new Insets(2, 0, 1, 0), 0, 42));
		add(searchBar, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, CENTER, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		add(dragObjectPanel, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	public void clearDragObjectSearchBar()
	{
		searchBar.clearInput();
	}

	@Override
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof ColorSelectionListener)
			colorChooser.addListener(listener);
		else
			if (listener instanceof DragObjectSearchListener)
			{
				searchBar.addSearchBarListener((DragObjectSearchListener) listener);
				searchBar.addSearchBarInputListener((DragObjectSearchListener) listener);
			}
	}

	// <- Getter & Setter ->
	public void setVisibleDragObjects(final List<DragObject> dragObjects)
	{
		dragObjectPanel.setVisibleDragObjects(dragObjects);
	}

	public void setSelectedColor(final Color color)
	{
		colorChooser.setSelectedColor(color);
	}

	// <- Static ->
}
