package de.janik.west.view.toolbar.toolbars;

import java.awt.Dimension;

import javax.swing.JToolBar;

import de.janik.west.listener.BasicListener;
import de.janik.west.view.I_ViewComponent;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class BasicSubToolbar extends JToolBar implements I_ViewComponent
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	// <- Static ->

	// <- Constructor ->
	public BasicSubToolbar()
	{
		setRollover(false);
		setFloatable(true);
	}

	// <- Abstract ->
	public abstract void addListener(final BasicListener listener);

	// <- Object ->
	protected void addSeparator(final int width)
	{
		addSeparator(new Dimension(width, 0));
	}

	// <- Getter & Setter ->
	// <- Static ->
}
