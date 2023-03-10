package de.janik.west.view.dragObject;

import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.util.Resources;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelImageWithTitle;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragObjectImageWithTitle extends DragObject
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	// <- Static ->

	// <- Constructor ->
	public DragObjectImageWithTitle(final DragObjectPanelListener listener)
	{
		super("Image + Title", Resources.IMAGEICON_PREVIEW_IMAGE_WITH_TITLE, listener);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public DragEditorPanel getDragEditorPanel()
	{
		return new DragEditorPanelImageWithTitle(listener);
	}
	// <- Getter & Setter ->
	// <- Static ->
}
