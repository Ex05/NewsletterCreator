package de.janik.west.view.dragObject;

import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.util.Resources;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelImage;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragObjectImage extends DragObject
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	// <- Static ->

	// <- Constructor ->
	public DragObjectImage(final DragObjectPanelListener listener)
	{
		super("Image", Resources.IMAGEICON_PREVIEW_IMAGE, listener);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public DragEditorPanel getDragEditorPanel()
	{
		return new DragEditorPanelImage(listener);
	}
	// <- Getter & Setter ->
	// <- Static ->
}
