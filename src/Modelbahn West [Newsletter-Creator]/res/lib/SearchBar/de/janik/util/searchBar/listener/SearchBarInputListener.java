package de.janik.util.searchBar.listener;

import de.janik.util.searchBar.event.SearchBarInputEvent;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
@FunctionalInterface
public interface SearchBarInputListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->
	// <- Constructor ->

	// <- Abstract ->
	public abstract void onUserInput(final SearchBarInputEvent e);

	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
