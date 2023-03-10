package de.janik.west.view.toolbar.toolbars;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JToolBar;

import de.janik.util.searchBar.SearchBar;
import de.janik.west.listener.BasicListener;
import de.janik.west.view.I_ViewComponent;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class SearchToolBar extends JToolBar implements I_ViewComponent
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	private final SearchBar		searchBar;

	// <- Static ->

	// <- Constructor ->
	public SearchToolBar()
	{
		setRollover(true);
		setFloatable(false);
		setBorderPainted(false);
		setOpaque(false);

		searchBar = new SearchBar("Settings");
		searchBar.setInputFieldBorder(null);
		searchBar.setInputFieldFont(new Font("Verdana", Font.PLAIN, 12));
		searchBar.addSearchBarListener(e -> Toolkit.getDefaultToolkit().beep());
		add(searchBar);

		searchBar.addSearchBarListener(e -> {
			System.out.println(e.getUserInput());
			searchBar.clearInput();
		});
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->

	// <- Static ->
}
