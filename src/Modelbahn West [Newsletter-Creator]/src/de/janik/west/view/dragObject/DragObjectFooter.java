package de.janik.west.view.dragObject;

import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.util.Resources;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelFooter;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragObjectFooter extends DragObject
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	// <- Static ->

	// <- Constructor ->
	public DragObjectFooter(final DragObjectPanelListener listener)
	{
		super("Footer", Resources.IMAGEICON_PREVIEW_FOOTER, listener);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public DragEditorPanel getDragEditorPanel()
	{
		return new DragEditorPanelFooter(listener);
	}
	// <- Getter & Setter ->
	// <- Static ->
}
