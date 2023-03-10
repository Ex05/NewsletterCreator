package de.janik.west.listener.toolbarListener;

import java.awt.event.ActionEvent;

import de.janik.west.NewsletterCreator;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public class ReloadAndOpenToolbarListener extends ToolbarListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public ReloadAndOpenToolbarListener(final NewsletterCreator controller)
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
			case "openExtern":
			{
				controller.buttonOpenExternPressed();
				break;
			}
			case "reload":
			{
				controller.buttonReloadPressed();
				break;
			}

			default:
				break;
		}
	}
	// <- Getter & Setter ->
	// <- Static ->
}
