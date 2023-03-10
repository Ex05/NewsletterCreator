package de.janik.west.listener.internalView.sendMailView;

import de.janik.util.searchBar.event.SearchBarInputEvent;
import de.janik.util.searchBar.listener.SearchBarListener;
import de.janik.west.NewsletterCreator;
import de.janik.west.listener.internalView.InternalViewListener;
import de.janik.west.view.InternalView;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class SendMailViewListener extends InternalViewListener implements SearchBarListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public SendMailViewListener(final NewsletterCreator controller)
	{
		super(controller);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void buttonClosePressed(final InternalView view)
	{
		controller.hideSendMailView();
	}

	@Override
	public void onButtonPressed(final SearchBarInputEvent e)
	{
		controller.onSendMailViewButtonAddPressed(e.getUserInput());
	}

	public void removeTestMailRecipient(final String mail)
	{
		controller.removeTestMailRecipient(mail);
	}

	public void buttonContinuePressed()
	{
		System.out.println("Continue !~!");
	}

	public void buttonSendTestMailPressed()
	{
		controller.sendTestMail();
	}

	// <- Getter & Setter ->
	// <- Static ->

}
