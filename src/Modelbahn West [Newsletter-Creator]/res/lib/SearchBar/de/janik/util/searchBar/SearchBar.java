package de.janik.util.searchBar;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.janik.util.searchBar.button.SearchBarButton;
import de.janik.util.searchBar.button.SearchBarButtonTextPosition;
import de.janik.util.searchBar.event.SearchBarInputEvent;
import de.janik.util.searchBar.listener.SearchBarInputListener;
import de.janik.util.searchBar.listener.SearchBarListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class SearchBar extends JPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long					serialVersionUID	= 1L;

	private static final Font					FONT_INPUT;

	private final String						defaultText;

	private final List<SearchBarInputListener>	inputListener;
	private final List<SearchBarListener>		searchBarListener;

	private final SearchBarButton				buttonOK;

	private final JTextField					textFildInput;

	private final Handler						handler;

	private boolean								fireEvent			= false;

	// <- Static ->
	static
	{
		FONT_INPUT = new Font("Verdana", Font.BOLD, 13);
	}

	// <- Constructor ->
	public SearchBar(final String defaultText)
	{
		this(defaultText, null, false);
	}

	public SearchBar(final String defaultText, final boolean filLVertical)
	{
		this(defaultText, null, filLVertical);
	}

	public SearchBar(final String defaultText, final String buttonText, final boolean fillVertical)
	{
		this(defaultText, buttonText, null, null, fillVertical);
	}

	public SearchBar(final String defaultText, final String buttonText, final ImageIcon icon,
			final SearchBarButtonTextPosition textPosition, final boolean fillVertical)
	{
		this(defaultText, buttonText, icon, textPosition, 8, 2, fillVertical);
	}

	public SearchBar(final String defaultText, final String buttonText, final ImageIcon icon,
			final SearchBarButtonTextPosition textPosition, final int iconTextGap, final int inputButtonGap, final boolean fillVertical)
	{
		this.defaultText = defaultText;

		setLayout(new GridBagLayout());
		setOpaque(false);

		handler = new Handler();

		inputListener = new ArrayList<>(1);
		searchBarListener = new ArrayList<>(1);

		if (buttonText == null)
			buttonOK = null;
		else
		{
			buttonOK = new SearchBarButton(buttonText, icon, textPosition);
			buttonOK.setIconTextGap(iconTextGap);
			buttonOK.addActionListener(e -> {
				if (fireEvent)
					searchBarListener.forEach(listener -> listener.onButtonPressed(new SearchBarInputEvent(textFildInput.getText())));
			});
		}
		textFildInput = new JTextField(defaultText);
		textFildInput.addFocusListener(handler);
		textFildInput.getDocument().addDocumentListener(handler);
		textFildInput.setForeground(Color.GRAY);
		textFildInput.setFont(FONT_INPUT);
		textFildInput.addActionListener(e -> {
			textFildInput.setFocusable(false);
			if (fireEvent)
				searchBarListener.forEach(listener -> listener.onButtonPressed(new SearchBarInputEvent(textFildInput.getText())));
			textFildInput.setFocusable(true);
		});

		add(textFildInput, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, fillVertical ? BOTH : HORIZONTAL, new Insets(0, 0, 0, 0),
				0, 0));
		if (buttonOK != null)
			add(buttonOK, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, CENTER, NONE, new Insets(0, inputButtonGap, 0, 0), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	public SearchBar setInputFieldFont(final Font font)
	{
		textFildInput.setFont(font);

		return this;
	}

	public SearchBar setInputFieldBorder(final Border border)
	{
		textFildInput.setBorder(border);

		return this;
	}

	public SearchBar clearInput()
	{
		fireEvent = false;

		textFildInput.setForeground(Color.GRAY);
		textFildInput.setText(defaultText);

		fireEvent = false;

		return this;
	}

	public SearchBar addSearchBarListener(final SearchBarListener listener)
	{
		searchBarListener.add(listener);

		return this;
	}

	public SearchBar addSearchBarInputListener(final SearchBarInputListener listener)
	{
		inputListener.add(listener);

		return this;
	}

	// <- Getter & Setter ->
	public JTextField getTextField()
	{
		return textFildInput;
	}

	public String getUserInput()
	{
		return textFildInput.getText().equals(defaultText) ? new String() : textFildInput.getText();
	}

	// <- Static ->

	private class Handler implements FocusListener, DocumentListener
	{
		@Override
		public void focusGained(final FocusEvent e)
		{
			textFildInput.setForeground(Color.BLACK);

			if (!fireEvent)
				if (textFildInput.getText().equals(defaultText))
				{
					fireEvent = false;

					textFildInput.setText(new String());

					fireEvent = true;
				}
		}

		@Override
		public void focusLost(final FocusEvent e)
		{
			if (textFildInput.getText().equals(new String()))
			{
				fireEvent = false;

				textFildInput.setForeground(Color.GRAY);
				textFildInput.setText(defaultText);

				fireEvent = false;
			}
		}

		@Override
		public void changedUpdate(final DocumentEvent e)
		{

		}

		@Override
		public void insertUpdate(final DocumentEvent e)
		{
			if (fireEvent)
				inputListener.forEach(listener -> listener.onUserInput(new SearchBarInputEvent(textFildInput.getText())));

			fireEvent = true;
		}

		@Override
		public void removeUpdate(final DocumentEvent e)
		{
			if (fireEvent)
				inputListener.forEach(listener -> listener.onUserInput(new SearchBarInputEvent(textFildInput.getText())));
		}
	}
}
