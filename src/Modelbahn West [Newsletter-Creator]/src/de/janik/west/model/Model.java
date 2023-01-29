package de.janik.west.model;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.janik.west.view.dragObject.DragObject;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.panel.editor.DragEditor;
import de.janik.west.view.panel.editor.Editor;
// import de.janik.west.view.panel.editor.WebView;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class Model
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private List<DragObject>		dragObjects;

	private Color					selectedColor;

	private List<DragEditorPanel>	dragEditorPanel;

	private Editor					selectedEditor;

	private List<String>			testMailRecipients;

	private File					lastSavedTMP_Directory;

	// <- Static ->

	// <- Constructor ->
	public Model()
	{
		selectedColor = Color.BLACK;
		dragObjects = new ArrayList<>(0);
		dragEditorPanel = new LinkedList<>();
		testMailRecipients = new LinkedList<>();
	}

	// <- Abstract ->

	// <- Object ->
	public void addTestMailRecipient(final String mail)
	{
		testMailRecipients.add(mail);
	}

	public void removeTestMailRecipient(final String mail)
	{
		testMailRecipients.remove(mail);
	}

	public void addDragEditorPanel(final DragEditorPanel panel)
	{
		dragEditorPanel.add(panel);
	}

	public void addDragEditorPanel(final int index, final DragEditorPanel panel)
	{
		dragEditorPanel.add(index, panel);
	}

	public void removeDragEditorPanel(final int index)
	{
		dragEditorPanel.remove(index);
	}

	public void removeDragEditorPanel(final DragEditorPanel panel)
	{
		dragEditorPanel.remove(panel);
	}

	// <- Getter & Setter ->
	public List<DragEditorPanel> getDragEditorPanel()
	{
		return dragEditorPanel;
	}

	public Color getSelectedColor()
	{
		return selectedColor;
	}

	public void setSelectedColor(Color selectedColor)
	{
		this.selectedColor = selectedColor;
	}

	public List<DragObject> getDragObjects()
	{
		return dragObjects;
	}

	public void setDragObjects(final List<DragObject> dragObjects)
	{
		this.dragObjects = dragObjects;
	}

	public boolean isDragEditorSelected()
	{
		return selectedEditor instanceof DragEditor;
	}

	public boolean isHTML_EditorSelected()
	{
		// TODO:
		return false;
	}

	public boolean isWebViewSelected()
	{
		// return selectedEditor instanceof WebView;
		return false;
	}

	public boolean isCSS_EditorSelected()
	{
		// TODO:
		return false;
	}

	public void setSelectedEditor(final Editor editor)
	{
		selectedEditor = editor;

	}

	public List<String> getTestMailRecipients()
	{
		return testMailRecipients;
	}

	public boolean tmpAvailable()
	{
		return lastSavedTMP_Directory == null;
	}

	public File getLastSavedTMP_Directory()
	{
		return lastSavedTMP_Directory;
	}

	public void setLastSavedTMP_Directory(final File lastSavedTMP_Directory)
	{
		this.lastSavedTMP_Directory = lastSavedTMP_Directory;
	}

	// <- Static ->

}
