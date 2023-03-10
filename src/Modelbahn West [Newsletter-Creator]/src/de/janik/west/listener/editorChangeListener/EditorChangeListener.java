package de.janik.west.listener.editorChangeListener;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.janik.west.NewsletterCreator;
import de.janik.west.listener.BasicListener;
import de.janik.west.view.panel.editor.Editor;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class EditorChangeListener extends BasicListener implements ChangeListener
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	public EditorChangeListener(final NewsletterCreator controller)
	{
		super(controller);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void stateChanged(final ChangeEvent e)
	{
		JTabbedPane pane = (JTabbedPane) e.getSource();

		controller.setSelectedEditor((Editor) pane.getSelectedComponent());

	}
	// <- Getter & Setter ->
	// <- Static ->
}
