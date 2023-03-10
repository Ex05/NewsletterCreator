package de.janik.west.view.toolbar.toolbars;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.toolbarListener.ReloadAndOpenToolbarListener;
import de.janik.west.util.Resources;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class ReloadAndOpenToolbar extends BasicSubToolbar
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	private final JButton		buttonReload;
	private final JButton		buttonOpenExtern;

	// <- Static ->

	// <- Constructor ->
	public ReloadAndOpenToolbar()
	{
		super();

		buttonReload = new JButton("reload", Resources.IMAGEICON_RELOAD);
		buttonOpenExtern = new JButton("openExtern", Resources.IMAGEICON_OPEN_EXTERN);

		add(buttonReload);
		addSeparator(1);
		add(buttonOpenExtern);
	}

	// <- Abstract ->

	// <- Object ->
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof ReloadAndOpenToolbarListener)
		{
			buttonReload.addActionListener((ReloadAndOpenToolbarListener) listener);
			buttonOpenExtern.addActionListener((ReloadAndOpenToolbarListener) listener);
		}

	};

	// <- Getter & Setter ->
	public void setButtonReloadEnabled(final boolean enabled)
	{
		buttonReload.setEnabled(enabled);
	}

	public void setButtonOpenExternEnabled(final boolean enabled)
	{
		buttonOpenExtern.setEnabled(enabled);
	}
	// <- Static ->
}
