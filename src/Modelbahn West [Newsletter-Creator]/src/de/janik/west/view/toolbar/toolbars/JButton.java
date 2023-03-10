package de.janik.west.view.toolbar.toolbars;

import javax.swing.ImageIcon;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
class JButton extends javax.swing.JButton
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	// <- Static ->

	// <- Constructor ->
	public JButton(final String name, final ImageIcon icon)
	{
		super(icon);

		setFocusPainted(false);
		setName(name);
		setActionCommand(name);
	}

	// <- Abstract ->
	// <- Object ->
	// <- Getter & Setter ->
	// <- Static ->
}
