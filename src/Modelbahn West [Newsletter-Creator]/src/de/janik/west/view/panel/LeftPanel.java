package de.janik.west.view.panel;

import java.awt.Color;

import de.janik.west.listener.BasicListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class LeftPanel extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	// <- Static ->

	// <- Constructor ->
	public LeftPanel()
	{
		super();

		setBackground(Color.PINK);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{
		// TODO Auto-generated method stub

	}

	// <- Getter & Setter ->
	// <- Static ->
}
