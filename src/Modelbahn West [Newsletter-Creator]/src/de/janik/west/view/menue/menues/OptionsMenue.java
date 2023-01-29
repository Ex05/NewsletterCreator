package de.janik.west.view.menue.menues;

import de.janik.west.listener.BasicListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class OptionsMenue extends BasicMenue
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	// <- Static ->

	// <- Constructor ->
	public OptionsMenue()
	{
		super("Options");

		setMnemonic(getText().charAt(0));
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
