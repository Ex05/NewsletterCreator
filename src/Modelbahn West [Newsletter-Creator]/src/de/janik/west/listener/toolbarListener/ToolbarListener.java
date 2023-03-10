package de.janik.west.listener.toolbarListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.janik.west.NewsletterCreator;
import de.janik.west.listener.BasicListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class ToolbarListener extends BasicListener implements ActionListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public ToolbarListener(final NewsletterCreator controller)
	{
		super(controller);
	}

	// <- Abstract ->
	@Override
	public abstract void actionPerformed(final ActionEvent e);

	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
