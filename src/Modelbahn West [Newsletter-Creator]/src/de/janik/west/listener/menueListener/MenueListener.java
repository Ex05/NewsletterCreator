package de.janik.west.listener.menueListener;

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
public abstract class MenueListener extends BasicListener implements ActionListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public MenueListener(final NewsletterCreator controller)
	{
		super(controller);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public abstract void actionPerformed(final ActionEvent e);

	// <- Getter & Setter ->
	// <- Static ->
}
