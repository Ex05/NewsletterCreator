package de.janik.west.view.menue;

import javax.swing.JMenuBar;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.menueListener.FileMenueListener;
import de.janik.west.listener.menueListener.OptionsMenueListener;
import de.janik.west.listener.menueListener.ProjectMenueListener;
import de.janik.west.view.I_ViewComponent;
import de.janik.west.view.menue.menues.FileMenue;
import de.janik.west.view.menue.menues.HelpMenue;
import de.janik.west.view.menue.menues.OptionsMenue;
import de.janik.west.view.menue.menues.ProjectMenue;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class Menue extends JMenuBar implements I_ViewComponent
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	private final FileMenue		menueFile;
	private final OptionsMenue	menueOptions;
	private final ProjectMenue	menueProject;
	private final HelpMenue		menueHelp;

	// <- Static ->

	// <- Constructor ->
	public Menue()
	{
		menueFile = new FileMenue();
		menueOptions = new OptionsMenue();
		menueProject = new ProjectMenue();
		menueHelp = new HelpMenue();

		add(menueFile);
		add(menueOptions);
		add(menueProject);
		add(menueHelp);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof FileMenueListener)
			menueFile.addListener(listener);
		else
			if (listener instanceof OptionsMenueListener)
				menueOptions.addListener(listener);
			else
				if (listener instanceof ProjectMenueListener)
					menueProject.addListener(listener);
	}

	// <- Getter & Setter ->
	// <- Static ->
}
