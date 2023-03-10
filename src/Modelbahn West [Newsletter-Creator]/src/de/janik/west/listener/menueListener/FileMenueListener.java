package de.janik.west.listener.menueListener;

import java.awt.event.ActionEvent;

import de.janik.west.NewsletterCreator;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class FileMenueListener extends MenueListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public FileMenueListener(final NewsletterCreator controller)
	{
		super(controller);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void actionPerformed(final ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "exit":
			{
				controller.dispose();

				break;
			}

			default:
			{

			}
		}
	}

	// <- Getter & Setter ->
	// <- Static ->

}
