package de.janik.west.view.panel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import de.janik.west.listener.BasicListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public class ColorSelectionPanel extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	private static final Border	BORDER;

	private Color				color;

	// <- Static ->
	static
	{
		BORDER = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	}

	// <- Constructor ->
	public ColorSelectionPanel()
	{
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createCompoundBorder(BORDER, BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY)));
	}

	// <- Abstract ->

	// <- Object ->
	public Color getSelectedColor()
	{
		return color;
	}

	public void setSelectedColor(final Color color)
	{
		this.color = color;

		setBackground(color);
	}

	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->
	// <- Static ->
}
