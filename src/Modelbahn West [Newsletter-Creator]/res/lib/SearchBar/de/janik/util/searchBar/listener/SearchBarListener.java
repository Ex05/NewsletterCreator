package de.janik.util.searchBar.listener;

import de.janik.util.searchBar.event.SearchBarInputEvent;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
@FunctionalInterface
public interface SearchBarListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->
	// <- Constructor ->

	// <- Abstract ->
	public abstract void onButtonPressed(final SearchBarInputEvent e);

	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
