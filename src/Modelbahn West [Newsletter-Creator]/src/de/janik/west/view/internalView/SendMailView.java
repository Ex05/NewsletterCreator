package de.janik.west.view.internalView;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.NORTH;
import static java.awt.GridBagConstraints.SOUTH;
import static java.awt.GridBagConstraints.WEST;

import java.awt.Color;
import java.awt.Font;
// <- Import ->
// <- Static_Import ->
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import de.janik.util.searchBar.SearchBar;
import de.janik.util.searchBar.event.SearchBarInputEvent;
import de.janik.west.listener.BasicListener;
import de.janik.west.listener.internalView.sendMailView.SendMailViewListener;
import de.janik.west.view.InternalView;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class SendMailView extends InternalView
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long				serialVersionUID	= 1L;

	private final JPanel					panelLeft;
	private final JPanel					panelRight;

	private final JLabel					labelRecipients;

	private final JList<String>				listRecipients;
	private final DefaultListModel<String>	model;

	private final SearchBar					searchBarEnterMail;

	private final JPanel					panelButtons;

	private final JButton					buttonSend;
	private final JButton					buttonContinue;
	private final JButton					buttonCancel;

	private final JLabel					labelText;

	private final JCheckBox					checkBoxShowAgain;

	// <- Static ->

	// <- Constructor ->
	public SendMailView(final SendMailViewListener listener)
	{
		super("Send test-mail ?", 550, 300, listener);

		panelInner.setLayout(null);

		panelLeft = new JPanel();
		panelLeft.setLayout(new GridBagLayout());
		panelRight = new JPanel();
		panelRight.setLayout(new GridBagLayout());

		labelRecipients = new JLabel("Recipients:");
		labelRecipients.setFont(new Font("Verdana", Font.BOLD, 12));
		labelRecipients.setHorizontalAlignment(SwingConstants.CENTER);

		model = new DefaultListModel<>();

		listRecipients = new JList<>(model);
		listRecipients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		((DefaultListCellRenderer) (listRecipients.getCellRenderer())).setHorizontalAlignment(SwingConstants.CENTER);
		listRecipients.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		listRecipients.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(final MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					// TODO: Remove unchecked suppressing.
					@SuppressWarnings("unchecked")
					JList<String> list = (JList<String>) e.getSource();
					int index = list.locationToIndex(e.getPoint());

					if (list.getSelectedIndex() != -1)
					{
						listener.removeTestMailRecipient(((DefaultListModel<String>) list.getModel()).get(index));
						((DefaultListModel<String>) list.getModel()).remove(index);
					}
				}
			}
		});

		searchBarEnterMail = new SearchBar("Add Recipient !!!", "+", true);
		searchBarEnterMail.addSearchBarListener(e -> {
			searchBarEnterMail.clearInput();
			listener.onButtonPressed(new SearchBarInputEvent(e.getUserInput()));
			model.addElement(e.getUserInput());
		});

		panelLeft.add(labelRecipients, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 4, 0, 4), 0, 0));
		panelLeft.add(listRecipients, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 4, 0, 4), 0, 0));
		panelLeft.add(searchBarEnterMail, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, CENTER, HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));

		panelButtons = new JPanel();
		panelButtons.setOpaque(false);
		panelButtons.setLayout(new GridBagLayout());

		buttonSend = new JButton("send");
		buttonSend.setActionCommand("send");
		buttonSend.setName("send");
		buttonSend.setFocusPainted(false);
		buttonSend.setEnabled(false);
		buttonSend.addActionListener(e -> listener.buttonSendTestMailPressed());

		buttonContinue = new JButton("continue");
		buttonContinue.setActionCommand("continue");
		buttonContinue.setName("continue");
		buttonContinue.setFocusPainted(false);
		buttonContinue.addActionListener(e -> listener.buttonContinuePressed());

		buttonCancel = new JButton("cancel");
		buttonCancel.setActionCommand("cancel");
		buttonCancel.setName("cancel");
		buttonCancel.setFocusPainted(false);
		buttonCancel.addActionListener(e -> listener.buttonClosePressed(SendMailView.this));

		panelButtons.add(buttonSend, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, WEST, NONE, new Insets(0, 4, 4, 0), 0, 0));
		panelButtons.add(buttonCancel, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, CENTER, NONE, new Insets(0, 0, 4, 0), 0, 0));
		panelButtons.add(buttonContinue, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, EAST, NONE, new Insets(0, 0, 4, 4), 0, 0));

		String text = "<html><head></head><body>" + "<b>Info:</b>" + "<br>" + "Add recipients to the list on the left, to send them a <b>test-mail</b>." + "<br>"
				+ "If no recipients are entered, you can directly continue to sending the newsletter." + "<br>" + "<br>" + "<b>Tip:</b>" + "<br>"
				+ "Double-clicking an entry, will remove it from the list." + "<body></html>";

		labelText = new JLabel(text);
		labelText.setFont(labelText.getFont().deriveFont(Font.PLAIN, 13));
		labelText.setForeground(Color.BLACK);

		JPanel panelText = new JPanel();
		panelText.setLayout(new GridBagLayout());
		panelText.setOpaque(false);
		panelText.setBorder(new LineBorder(Color.BLACK, 1, true));

		panelText.add(labelText, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(2, 4, 4, 4), 0, 0));

		checkBoxShowAgain = new JCheckBox("Don't show me this window again!");
		checkBoxShowAgain.setHorizontalTextPosition(SwingConstants.LEFT);
		checkBoxShowAgain.setFocusPainted(false);

		panelRight.add(panelText, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, NORTH, HORIZONTAL, new Insets(16, 4, 4, 4), 0, 0));
		panelRight.add(checkBoxShowAgain, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, EAST, NONE, new Insets(0, 0, 16, 4), 0, 0));
		panelRight.add(panelButtons, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, SOUTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		panelLeft.setSize(250, 300);
		panelLeft.setLocation(0, 0);
		panelInner.add(panelLeft);

		panelRight.setSize(300, 300);
		panelRight.setLocation(250, 0);
		panelInner.add(panelRight);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->
	public void setButtonSendTextMailEnabled(final boolean enabled)
	{
		buttonSend.setEnabled(enabled);
	}
	// <- Static ->
}
