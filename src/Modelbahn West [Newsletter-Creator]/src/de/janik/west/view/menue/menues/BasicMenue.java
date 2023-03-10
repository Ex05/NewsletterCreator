package de.janik.west.view.menue.menues;

import javax.swing.JMenu;

import de.janik.west.listener.BasicListener;
import de.janik.west.view.I_ViewComponent;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class BasicMenue extends JMenu implements I_ViewComponent
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	private static final long	serialVersionUID	= 1l;

	// <- Static ->

	// <- Constructor ->
	public BasicMenue(String title)
	{
		super(title);
	}

	// <- Abstract ->
	@Override
	public abstract void addListener(final BasicListener listener);

	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
