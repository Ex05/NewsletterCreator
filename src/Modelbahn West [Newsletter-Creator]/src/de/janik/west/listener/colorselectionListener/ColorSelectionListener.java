package de.janik.west.listener.colorselectionListener;

import java.awt.Color;

import de.janik.west.NewsletterCreator;
import de.janik.west.listener.BasicListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class ColorSelectionListener extends BasicListener implements I_ColorSelectionListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public ColorSelectionListener(final NewsletterCreator controller)
	{
		super(controller);
	}

	// <- Abstract ->

	// <- Object ->
	public void onColorSelection(final Color color)
	{
		controller.setSelectedColor(color);
	}

	// <- Getter & Setter ->
	// <- Static ->
}
