package de.janik.west.view.panel.editor;

import de.janik.west.listener.BasicListener;
import de.janik.west.view.panel.ViewPanel;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class Editor extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	// <- Static ->
	// <- Constructor ->

	// <- Abstract ->
	@Override
	public abstract void addListener(final BasicListener listener);

	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
