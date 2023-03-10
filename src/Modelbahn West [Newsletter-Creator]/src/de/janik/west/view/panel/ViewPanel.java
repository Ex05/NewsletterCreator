package de.janik.west.view.panel;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import de.janik.west.listener.BasicListener;
import de.janik.west.view.I_ViewComponent;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class ViewPanel extends JPanel implements I_ViewComponent
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	private final GridBagLayout	gbl;

	// <- Static ->

	// <- Constructor ->
	public ViewPanel()
	{
		gbl = new GridBagLayout();

		setLayout(gbl);
	}

	// <- Abstract ->
	public abstract void addListener(final BasicListener listener);

	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
