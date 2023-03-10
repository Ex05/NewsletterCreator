package de.janik.west.view.toolbar;

import javax.swing.JToolBar;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.toolbarListener.ProjectToolbarListener;
import de.janik.west.listener.toolbarListener.ReloadAndOpenToolbarListener;
import de.janik.west.listener.toolbarListener.SendMailToolbarListener;
import de.janik.west.view.I_ViewComponent;
import de.janik.west.view.toolbar.toolbars.CutCopyPasteToolbar;
import de.janik.west.view.toolbar.toolbars.ProjectToolbar;
import de.janik.west.view.toolbar.toolbars.ReloadAndOpenToolbar;
import de.janik.west.view.toolbar.toolbars.SendMailToolbar;
import de.janik.west.view.toolbar.toolbars.UndoRedoToolbar;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class Toolbar extends JToolBar implements I_ViewComponent
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long			serialVersionUID	= 1l;

	private final ProjectToolbar		projectToolbar;
	private final UndoRedoToolbar		undoRedoToolbar;
	private final CutCopyPasteToolbar	cutCopyPasteToolbar;
	private final ReloadAndOpenToolbar	reloadAndOpenToolbar;
	private final SendMailToolbar		sendMailToolbar;

	// <- Static ->

	// <- Constructor ->
	public Toolbar()
	{
		setRollover(true);
		setFloatable(false);

		projectToolbar = new ProjectToolbar();
		add(projectToolbar);

		undoRedoToolbar = new UndoRedoToolbar();
		add(undoRedoToolbar);

		cutCopyPasteToolbar = new CutCopyPasteToolbar();
		add(cutCopyPasteToolbar);

		reloadAndOpenToolbar = new ReloadAndOpenToolbar();
		add(reloadAndOpenToolbar);

		sendMailToolbar = new SendMailToolbar();
		add(sendMailToolbar);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof ProjectToolbarListener)
			projectToolbar.addListener(listener);
		else
			if (listener instanceof SendMailToolbarListener)
				sendMailToolbar.addListener(listener);
			else
				if (listener instanceof ReloadAndOpenToolbarListener)
					reloadAndOpenToolbar.addListener(listener);
	}

	// <- Getter & Setter ->
	public void setButtonSendMailEnabled(final boolean enabled)
	{
		sendMailToolbar.setButtonSendMailEnabled(enabled);
	}

	public void setButtonReloadEnabled(final boolean enabled)
	{
		reloadAndOpenToolbar.setButtonReloadEnabled(enabled);
	}

	public void setButtonOpenExternEnabled(final boolean enabled)
	{
		reloadAndOpenToolbar.setButtonOpenExternEnabled(enabled);
	}

	public void setButtonCutEnalbed(final boolean enabled)
	{
		cutCopyPasteToolbar.setButtonCutEnalbed(enabled);
	}

	public void setButtonCopyEnalbed(final boolean enabled)
	{
		cutCopyPasteToolbar.setButtonCopyEnalbed(enabled);
	}

	public void setButtonPasteEnalbed(final boolean enabled)
	{
		cutCopyPasteToolbar.setButtonPasteEnalbed(enabled);
	}

	public void setButtonRedoEnabled(final boolean enabled)
	{
		undoRedoToolbar.setButtonRedoEnabled(enabled);
	}

	public void setButtonUndoEnabled(final boolean enabled)
	{
		undoRedoToolbar.setButtonUndoEnabled(enabled);
	}

	public void setButtonNewProjectEnabled(final boolean enabled)
	{
		projectToolbar.setButtonNewProjectEnabled(enabled);
	}

	public void setButtonSaveEnabled(final boolean enabled)
	{
		projectToolbar.setButtonSaveEnabled(enabled);
	}

	public void setButtonSaveAllEnabled(final boolean enabled)
	{
		projectToolbar.setButtonSaveAllEnabled(enabled);
	}

	public void setButtonPrintEnabled(final boolean enabled)
	{
		projectToolbar.setButtonPrintEnabled(enabled);
	}
	// <- Static ->
}
