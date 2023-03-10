package de.janik.west.view.toolbar.toolbars;

import de.janik.west.listener.BasicListener;
import de.janik.west.util.Resources;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public class UndoRedoToolbar extends BasicSubToolbar
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JButton		buttonUndo;
	private final JButton		buttonRedo;

	// <- Static ->

	// <- Constructor ->
	public UndoRedoToolbar()
	{
		super();

		buttonUndo = new JButton("undo", Resources.IMAGEICON_UNDO);
		buttonRedo = new JButton("redo", Resources.IMAGEICON_REDO);

		add(buttonUndo);
		addSeparator(1);
		add(buttonRedo);
	}

	// <- Abstract ->

	// <- Object ->
	public void addListener(final BasicListener listener)
	{};

	// <- Getter & Setter ->
	public void setButtonRedoEnabled(final boolean enabled)
	{
		buttonRedo.setEnabled(enabled);
	}

	public void setButtonUndoEnabled(final boolean enabled)
	{
		buttonUndo.setEnabled(enabled);
	}

	// <- Static ->
}
