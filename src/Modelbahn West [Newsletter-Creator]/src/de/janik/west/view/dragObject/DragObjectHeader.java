package de.janik.west.view.dragObject;

import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.util.Resources;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelHeader;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragObjectHeader extends DragObject
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	// <- Static ->

	// <- Constructor ->
	public DragObjectHeader(final DragObjectPanelListener listener)
	{
		super("Header", Resources.IMAGEICON_PREVIEW_HEADER, listener);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public DragEditorPanel getDragEditorPanel()
	{
		return new DragEditorPanelHeader(listener);
	}
	// <- Getter & Setter ->
	// <- Static ->
}
