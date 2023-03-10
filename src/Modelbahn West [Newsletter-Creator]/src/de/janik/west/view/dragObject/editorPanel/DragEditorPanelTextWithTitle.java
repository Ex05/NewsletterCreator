package de.janik.west.view.dragObject.editorPanel;

import static java.awt.GridBagConstraints.BOTH;
// <- Import ->
// <- Static_Import ->
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.view.InternalView;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragEditorPanelTextWithTitle extends DragEditorPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JLabel		labelTitle;
	private final JTextArea		textPane;

	// <- Static ->

	// <- Constructor ->
	public DragEditorPanelTextWithTitle(final DragObjectPanelListener listener)
	{
		super(listener);

		textPane = new JTextArea()
		{
			private static final long	serialVersionUID	= 1L;

			@Override
			public void paintComponent(Graphics graphics)
			{
				Graphics2D g = (Graphics2D) graphics;
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				super.paintComponent(g);
			}
		};

		for (MouseListener m : getMouseListeners())
			textPane.addMouseListener(m);

		textPane.setFont(new Font("Verdana", Font.BOLD, 13));
		textPane.setEditable(false);
		textPane.setEnabled(false);
		textPane.setForeground(Color.BLACK);
		textPane.setDisabledTextColor(Color.BLACK);
		textPane.setTransferHandler(null);

		textPane.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr,\nsed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat,\ned diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.,\nStet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\nLorem ipsum dolor sit amet, consetetur sadipscing elitr,\nsed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat,\nsed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.\nStet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
		labelTitle = new JLabel("Title !~!");
		labelTitle.setFont(new Font("Verdana", Font.BOLD, 18));
		labelTitle.setForeground(Color.BLACK);

		add(labelTitle, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, HORIZONTAL, new Insets(2, 5, 0, 0), 0, 0));
		add(textPane, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(5, 5, 5, 5), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	public InternalView getPopUp()
	{
		return new PopUpTextWithTitle();
	}

	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->
	public String getTitle()
	{
		return labelTitle.getText();
	}

	public String getText()
	{
		return textPane.getText();
	}

	// <- Static ->

	private class PopUpTextWithTitle extends InternalView
	{
		private static final long	serialVersionUID	= 1L;

		private final JPanel		panel;

		private final JScrollPane	scrollPane;
		private final JTextArea		textPane;

		private final JTextField	textFieldTitle;

		private final JButton		buttonSave;
		private final JButton		buttonCancel;

		public PopUpTextWithTitle()
		{
			super("PopUp-Text + Title", 700, 225, listener);

			panel = new JPanel();
			panel.setLayout(new GridBagLayout());

			panelInner.add(panel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));

			textPane = new JTextArea(DragEditorPanelTextWithTitle.this.textPane.getText())
			{
				private static final long	serialVersionUID	= 1L;

				@Override
				public void paintComponent(Graphics graphics)
				{
					Graphics2D g = (Graphics2D) graphics;
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					super.paintComponent(g);
				}
			};
			textPane.setFont(new Font("Verdana", Font.BOLD, 13));
			textPane.setForeground(Color.BLACK);
			textPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

			JPanel panelButtons = new JPanel();
			panelButtons.setLayout(new GridBagLayout());
			panelButtons.setOpaque(false);

			buttonCancel = new JButton("cancel");
			buttonCancel.setFocusPainted(false);
			buttonCancel.addActionListener(e -> listener.buttonClosePressed(this));

			buttonSave = new JButton("save");
			buttonSave.setFocusPainted(false);
			buttonSave.addActionListener(e -> {
				DragEditorPanelTextWithTitle.this.textPane.setText(textPane.getText());
				listener.buttonClosePressed(this);
			});

			panelButtons.add(buttonCancel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, EAST, NONE, new Insets(0, 4, 4, 4), 0, 0));
			panelButtons.add(buttonSave, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, CENTER, NONE, new Insets(0, 4, 4, 4), 0, 0));

			scrollPane = new JScrollPane(textPane);

			textFieldTitle = new JTextField(labelTitle.getText());
			textFieldTitle.setForeground(Color.BLACK);
			textFieldTitle.setFont(new Font("Verdana", Font.BOLD, 16));

			panel.add(textFieldTitle, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
			panel.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 4, 4, 4), 0, 0));
			panel.add(panelButtons, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, CENTER, BOTH, new Insets(0, 4, 4, 4), 0, 0));
		}

		@Override
		public void addListener(BasicListener listener)
		{

		}
	}
}
