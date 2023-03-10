package de.janik.west.view.dragObject.editorPanel;

import static java.awt.GridBagConstraints.BOTH;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.view.InternalView;
// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragEditorPanelFooter extends DragEditorPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JTextPane		textPane;

	// <- Static ->

	// <- Constructor ->
	public DragEditorPanelFooter(final DragObjectPanelListener listener)
	{
		super(listener);

		textPane = new JTextPane()
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

		textPane.setFont(new Font("Verdana", Font.PLAIN, 9));
		textPane.setOpaque(false);
		textPane.setEditable(false);
		textPane.setEnabled(false);
		textPane.setForeground(Color.BLACK);
		textPane.setDisabledTextColor(Color.BLACK);
		textPane.setTransferHandler(null);
		// textPane.setContentType("text/html");

		textPane.setText("Wir freuen uns, wenn Sie unseren Newsletter an Freunde, Arbeitskollegen und Bekannte weiterleiten.\n"
				+ "Falls Sie diesen Newsletter als Weiterleitung bekommen haben, k�nnen Sie diesen auf unserer \n"
				+ "Internetseite www.modellbahn-west.de kostenlos abonnieren.");

		for (MouseListener m : getMouseListeners())
			textPane.addMouseListener(m);

		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		add(textPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	public InternalView getPopUp()
	{
		return new PopUpFooter();
	}

	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->
	public String getFooter()
	{
		return textPane.getText();
	}
	
	// <- Static ->

	private class PopUpFooter extends InternalView
	{
		private static final long	serialVersionUID	= 1L;

		private final JPanel		panel;

		private final JScrollPane	scrollPane;
		private final JTextArea		textPane;

		private final JButton		buttonSave;
		private final JButton		buttonCancel;

		public PopUpFooter()
		{
			super("PopUp-Footer", 700, 250, listener);

			panel = new JPanel();
			panel.setLayout(new GridBagLayout());

			panelInner.add(panel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));

			textPane = new JTextArea(DragEditorPanelFooter.this.textPane.getText())
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
				DragEditorPanelFooter.this.textPane.setText(textPane.getText());
				listener.buttonClosePressed(this);
			});

			panelButtons.add(buttonCancel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, EAST, NONE, new Insets(0, 4, 4, 4), 0, 0));
			panelButtons.add(buttonSave, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, CENTER, NONE, new Insets(0, 4, 4, 4), 0, 0));

			scrollPane = new JScrollPane(textPane);

			panel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(4, 4, 4, 4), 0, 0));
			panel.add(panelButtons, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, CENTER, BOTH, new Insets(0, 4, 4, 4), 0, 0));
		}

		@Override
		public void addListener(BasicListener listener)
		{

		}
	}
}
