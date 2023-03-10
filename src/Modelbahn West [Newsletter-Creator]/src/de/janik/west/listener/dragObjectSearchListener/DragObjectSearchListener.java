package de.janik.west.listener.dragObjectSearchListener;

import de.janik.util.searchBar.event.SearchBarInputEvent;
import de.janik.util.searchBar.listener.SearchBarInputListener;
import de.janik.util.searchBar.listener.SearchBarListener;
import de.janik.west.NewsletterCreator;
import de.janik.west.listener.BasicListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public class DragObjectSearchListener extends BasicListener implements SearchBarInputListener, SearchBarListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public DragObjectSearchListener(final NewsletterCreator controller)
	{
		super(controller);

	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void onButtonPressed(final SearchBarInputEvent e)
	{		
		controller.onDragObjectSearchBarButtonPressed();
	}

	@Override
	public void onUserInput(final SearchBarInputEvent e)
	{
		controller.onDragObjectSearchBarInput(e.getUserInput());
	}

	// <- Getter & Setter ->
	// <- Static ->
}
