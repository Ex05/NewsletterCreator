package de.janik.west.view.menue.menues;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.menueListener.FileMenueListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class HelpMenue extends BasicMenue
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	// <- Static ->

	// <- Constructor ->
	public HelpMenue()
	{
		super("Help");

		setMnemonic(getText().charAt(0));
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof FileMenueListener)
			;
	}

	// <- Getter & Setter ->
	// <- Static ->
}
