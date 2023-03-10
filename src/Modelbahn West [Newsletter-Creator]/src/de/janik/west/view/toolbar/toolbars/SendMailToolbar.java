package de.janik.west.view.toolbar.toolbars;

import java.awt.Font;

import javax.swing.SwingConstants;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.toolbarListener.SendMailToolbarListener;
import de.janik.west.util.Resources;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class SendMailToolbar extends BasicSubToolbar
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private static final Font	FONT_BUTTON_SENDMAIL;

	private final JButton		buttonSendMail;

	// <- Static ->
	static
	{
		FONT_BUTTON_SENDMAIL = new Font("Arial", Font.BOLD, 12);
	}

	// <- Constructor ->
	public SendMailToolbar()
	{
		buttonSendMail = new JButton("sendMail", Resources.IMAGEICON_SENDMAIL);
		buttonSendMail.setText("Send Mail");
		buttonSendMail.setFont(FONT_BUTTON_SENDMAIL);
		buttonSendMail.setIconTextGap(8);
		buttonSendMail.setHorizontalTextPosition(SwingConstants.LEFT);

		add(buttonSendMail);
	}

	// <- Abstract ->

	// <- Object ->
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof SendMailToolbarListener)
			buttonSendMail.addActionListener((SendMailToolbarListener) listener);
	};

	// <- Getter & Setter ->
	public void setButtonSendMailEnabled(final boolean enabled)
	{
		buttonSendMail.setEnabled(enabled);
	}

	// <- Static ->
}
