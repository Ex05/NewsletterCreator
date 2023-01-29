package de.janik.util.searchBar.button;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public class SearchBarButton extends JButton
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	// <- Static ->

	// <- Constructor ->
	public SearchBarButton(final String text)
	{
		super(text);

		setFocusPainted(false);
	}

	public SearchBarButton(final ImageIcon icon)
	{
		super(icon);

		setFocusPainted(false);
	}

	public SearchBarButton(final String text, final ImageIcon icon, final SearchBarButtonTextPosition textPosition)
	{
		super(text);

		if (icon != null)
			setIcon(icon);

		if (textPosition != null)
			if (textPosition == SearchBarButtonTextPosition.TOP)
			{
				setVerticalTextPosition(SwingConstants.TOP);
				setHorizontalTextPosition(SwingConstants.CENTER);
			}
			else
				if (textPosition == SearchBarButtonTextPosition.BOTTOM)
				{
					setVerticalTextPosition(SwingConstants.BOTTOM);
					setHorizontalTextPosition(SwingConstants.CENTER);
				}
				else
					if (textPosition == SearchBarButtonTextPosition.RIGHT)
					{
						setVerticalTextPosition(SwingConstants.CENTER);
						setHorizontalTextPosition(SwingConstants.RIGHT);
					}
					else
						if (textPosition == SearchBarButtonTextPosition.LEFT)
						{
							setVerticalTextPosition(SwingConstants.CENTER);
							setHorizontalTextPosition(SwingConstants.LEFT);
						}
						else
							if (textPosition == SearchBarButtonTextPosition.OVER)
							{
								setVerticalTextPosition(SwingConstants.CENTER);
								setHorizontalTextPosition(SwingConstants.CENTER);
							}
		setFocusPainted(false);
	}

	// <- Abstract ->
	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
