package de.janik.util.searchBar.event;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class SearchBarInputEvent
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final String	input;

	// <- Static ->

	// <- Constructor ->
	public SearchBarInputEvent(final String input)
	{
		this.input = input;
	}

	// <- Abstract ->
	// <- Object ->

	// <- Getter & Setter ->
	public String getUserInput()
	{
		return input;
	}

	// <- Static ->
}
