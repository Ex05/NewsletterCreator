package de.janik.west.view.panel.editor;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import de.janik.west.listener.BasicListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class HTML_Editor extends Editor
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JScrollPane	pane;

	private final JTextArea		textAreaText;

	// <- Static ->

	// <- Constructor ->
	public HTML_Editor()
	{
		textAreaText = new JTextArea();
		textAreaText.setEditable(false);
		textAreaText.setFont(new Font("Verdana", Font.BOLD, 14));
		textAreaText.setForeground(Color.BLACK);

		pane = new JScrollPane(textAreaText);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(pane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->
	public void setText(final String text)
	{
		textAreaText.setText(text);
		SwingUtilities.invokeLater(() -> pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getMinimum()));
	}

	// <- Static ->
}
