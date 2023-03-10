package de.janik.west.view.panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.colorselectionListener.I_ColorSelectionListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public class ColorPanel extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long						serialVersionUID	= 1l;

	private static final Border						BORDER;

	private final Color								color;

	private final List<I_ColorSelectionListener>	colorListener;

	// <- Static ->
	static
	{
		BORDER = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	}

	// <- Constructor ->
	public ColorPanel(final Color color)
	{
		this.color = color;

		colorListener = new ArrayList<>(1);

		setBackground(color);
		setBorder(BORDER);

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(final MouseEvent e)
			{
				colorListener.forEach(listener -> listener.onColorSelection(color));
			}
		});
	}

	// <- Abstract ->

	// <- Object ->
	public void addColorSelectionListener(final I_ColorSelectionListener listener)
	{
		colorListener.add(listener);
	}

	public Color getColor()
	{
		return color;
	}

	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->
	// <- Static ->
}
