package de.janik.west.view.menue.menues;

import static java.awt.event.InputEvent.ALT_MASK;
// <- Import ->
// <- Static_Import ->
import static java.awt.event.KeyEvent.VK_F4;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.menueListener.FileMenueListener;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class FileMenue extends BasicMenue
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JMenuItem		menueItemExit;

	// <- Static ->

	// <- Constructor ->
	public FileMenue()
	{
		super("File");

		setMnemonic(getText().charAt(0));

		menueItemExit = new JMenuItem("Exit");
		menueItemExit.setName("exit");
		menueItemExit.setActionCommand("exit");
		menueItemExit.setMnemonic(menueItemExit.getText().charAt(0));
		menueItemExit.setAccelerator(KeyStroke.getKeyStroke(VK_F4, ALT_MASK));

		add(menueItemExit);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof FileMenueListener)
			menueItemExit.addActionListener((FileMenueListener) listener);
	}

	// <- Getter & Setter ->
	// <- Static ->
}
