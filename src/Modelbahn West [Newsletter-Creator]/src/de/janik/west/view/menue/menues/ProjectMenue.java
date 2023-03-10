package de.janik.west.view.menue.menues;

import static java.awt.event.InputEvent.CTRL_MASK;
import static java.awt.event.KeyEvent.VK_N;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.menueListener.ProjectMenueListener;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class ProjectMenue extends BasicMenue
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JMenuItem		itemNewProject;

	// <- Static ->

	// <- Constructor ->
	public ProjectMenue()
	{
		super("Project");

		setMnemonic(getText().charAt(0));

		itemNewProject = new JMenuItem("New Project");
		itemNewProject.setName("newProject");
		itemNewProject.setActionCommand("newProject");
		itemNewProject.setMnemonic(itemNewProject.getText().charAt(0));
		itemNewProject.setAccelerator(KeyStroke.getKeyStroke(VK_N, CTRL_MASK));

		add(itemNewProject);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof ProjectMenueListener)
			itemNewProject.addActionListener((ProjectMenueListener) listener);
	}

	// <- Getter & Setter ->
	// <- Static ->
}
