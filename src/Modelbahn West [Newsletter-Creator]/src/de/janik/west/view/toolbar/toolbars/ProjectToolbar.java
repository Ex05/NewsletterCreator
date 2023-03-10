package de.janik.west.view.toolbar.toolbars;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.toolbarListener.ProjectToolbarListener;
import de.janik.west.util.Resources;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class ProjectToolbar extends BasicSubToolbar
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JButton		buttonNewProject;
	private final JButton		buttonSave;
	private final JButton		buttonSaveAll;
	private final JButton		buttonPrint;

	// <- Static ->

	// <- Constructor ->
	public ProjectToolbar()
	{
		buttonNewProject = new JButton("newProject", Resources.IMAGEICON_NEW_PROJECT);
		buttonSave = new JButton("save", Resources.IMAGEICON_SAVE);
		buttonSaveAll = new JButton("saveAll", Resources.IMAGEICON_SAVE_ALL);
		buttonPrint = new JButton("print", Resources.IMAGEICON_PRINT);

		add(buttonNewProject);
		addSeparator(1);
		add(buttonSave);
		addSeparator(1);
		add(buttonSaveAll);
		addSeparator(1);
		add(buttonPrint);
	}

	// <- Abstract ->

	// <- Object ->
	public void addListener(final BasicListener listener)
	{		
		if (listener instanceof ProjectToolbarListener)
		{			
			buttonNewProject.addActionListener((ProjectToolbarListener) listener);
			buttonSave.addActionListener((ProjectToolbarListener) listener);
			buttonSaveAll.addActionListener((ProjectToolbarListener) listener);
			buttonPrint.addActionListener((ProjectToolbarListener) listener);
		}
	};

	// <- Getter & Setter ->
	public void setButtonNewProjectEnabled(final boolean enabled)
	{
		buttonNewProject.setEnabled(enabled);
	}

	public void setButtonSaveEnabled(final boolean enabled)
	{
		buttonSave.setEnabled(enabled);
	}

	public void setButtonSaveAllEnabled(final boolean enabled)
	{
		buttonSaveAll.setEnabled(enabled);
	}

	public void setButtonPrintEnabled(final boolean enabled)
	{
		buttonPrint.setEnabled(enabled);
	}
	// <- Static ->
}
