package de.janik.west.listener.toolbarListener;

import java.awt.event.ActionEvent;

import de.janik.west.NewsletterCreator;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public class SendMailToolbarListener extends ToolbarListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public SendMailToolbarListener(final NewsletterCreator controller)
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
			case "sendMail":
			{
				controller.buttonSendMailPressed();
				break;
			}

			default:
				break;
		}
	}
	// <- Getter & Setter ->
	// <- Static ->
}
