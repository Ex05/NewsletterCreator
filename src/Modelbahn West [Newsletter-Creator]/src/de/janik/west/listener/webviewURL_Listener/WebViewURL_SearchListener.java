package de.janik.west.listener.webviewURL_Listener;

import de.janik.util.searchBar.event.SearchBarInputEvent;
import de.janik.util.searchBar.listener.SearchBarListener;
import de.janik.west.NewsletterCreator;
import de.janik.west.listener.BasicListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public class WebViewURL_SearchListener extends BasicListener implements SearchBarListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public WebViewURL_SearchListener(final NewsletterCreator controller)
	{
		super(controller);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void onButtonPressed(final SearchBarInputEvent e)
	{
		controller.onURL_SearchListenerButtonPressed(e.getUserInput());
	}

	// <- Getter & Setter ->
	// <- Static ->
}
