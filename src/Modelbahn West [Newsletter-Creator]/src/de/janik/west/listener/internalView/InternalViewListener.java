package de.janik.west.listener.internalView;

import de.janik.west.NewsletterCreator;
import de.janik.west.listener.BasicListener;
import de.janik.west.view.InternalView;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class InternalViewListener extends BasicListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public InternalViewListener(final NewsletterCreator controller)
	{
		super(controller);
	}

	// <- Abstract ->
	public abstract void buttonClosePressed(final InternalView view);

	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
