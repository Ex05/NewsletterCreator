package de.janik.west.view.toolbar.toolbars;

import de.janik.west.listener.BasicListener;
import de.janik.west.util.Resources;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class CutCopyPasteToolbar extends BasicSubToolbar
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	private final JButton		buttonCut;
	private final JButton		buttonCopy;
	private final JButton		buttonPaste;

	// <- Static ->

	// <- Constructor ->
	public CutCopyPasteToolbar()
	{
		super();

		buttonCut = new JButton("cut", Resources.IMAGEICON_CUT);
		buttonCopy = new JButton("copy", Resources.IMAGEICON_COPY);
		buttonPaste = new JButton("paste", Resources.IMAGEICON_PASTE);

		add(buttonCut);
		addSeparator(1);
		add(buttonCopy);
		addSeparator(1);
		add(buttonPaste);
	}

	// <- Abstract ->

	// <- Object ->
	public void addListener(final BasicListener listener)
	{};

	// <- Getter & Setter ->
	public void setButtonCutEnalbed(final boolean enabled)
	{
		buttonCut.setEnabled(enabled);
	}

	public void setButtonCopyEnalbed(final boolean enabled)
	{
		buttonCopy.setEnabled(enabled);
	}

	public void setButtonPasteEnalbed(final boolean enabled)
	{
		buttonPaste.setEnabled(enabled);
	}

	// <- Static ->
}
