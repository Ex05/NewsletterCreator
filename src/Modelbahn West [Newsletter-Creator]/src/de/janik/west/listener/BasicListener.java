package de.janik.west.listener;

import de.janik.west.NewsletterCreator;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class BasicListener
{
	// <- Public ->

	// <- Protected ->
	protected final NewsletterCreator	controller;

	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public BasicListener(final NewsletterCreator controller)
	{
		this.controller = controller;
	}

	// <- Abstract ->
	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
