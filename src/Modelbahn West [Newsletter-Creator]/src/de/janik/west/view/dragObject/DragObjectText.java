package de.janik.west.view.dragObject;

import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.util.Resources;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelText;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragObjectText extends DragObject
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	// <- Static ->

	// <- Constructor ->
	public DragObjectText(final DragObjectPanelListener listener)
	{
		super("Text", Resources.IMAGEICON_PREVIEW_TEXT, listener);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public DragEditorPanel getDragEditorPanel()
	{
		return new DragEditorPanelText(listener);
	}
	// <- Getter & Setter ->
	// <- Static ->
}
