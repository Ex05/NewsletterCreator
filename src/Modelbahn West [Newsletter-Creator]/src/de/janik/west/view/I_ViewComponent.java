package de.janik.west.view;

import de.janik.west.listener.BasicListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
@FunctionalInterface
public interface I_ViewComponent
{
	public void addListener(final BasicListener listener);
}
