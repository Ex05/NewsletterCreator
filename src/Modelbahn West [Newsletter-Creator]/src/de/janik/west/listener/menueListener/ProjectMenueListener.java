package de.janik.west.listener.menueListener;

import java.awt.event.ActionEvent;

import de.janik.west.NewsletterCreator;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class ProjectMenueListener extends MenueListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public ProjectMenueListener(final NewsletterCreator controller)
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
			case "newProject":
			{
				controller.buttonNewProjectPressed();

				break;
			}

			default:
			{
				break;
			}
		}
	}

	// <- Getter & Setter ->
	// <- Static ->

}
