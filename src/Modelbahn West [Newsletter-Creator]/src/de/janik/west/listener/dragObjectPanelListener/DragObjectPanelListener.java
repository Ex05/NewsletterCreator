package de.janik.west.listener.dragObjectPanelListener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import de.janik.west.NewsletterCreator;
import de.janik.west.listener.internalView.InternalViewListener;
import de.janik.west.view.InternalView;
import de.janik.west.view.dragObject.DragObject;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragObjectPanelListener extends InternalViewListener implements MouseListener
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private volatile Boolean	doubleClick	= false;

	// <- Static ->

	// <- Constructor ->
	public DragObjectPanelListener(final NewsletterCreator controller)
	{
		super(controller);
	}

	// <- Abstract ->

	// <- Object ->
	public void showPopUp(final DragEditorPanel panel)
	{
		controller.showPopUp(panel.getPopUp());
	}

	public void buttonUpPressed(final DragEditorPanel panel)
	{
		controller.moveDragEditorPanelUp(panel);
	}

	public void buttonDownPressed(final DragEditorPanel panel)
	{
		controller.moveDragEditorPanelDown(panel);
	}

	public void buttonClosePressed(final DragEditorPanel panel)
	{
		controller.removeDragEditorPanel(panel);
	}

	@Override
	public void mouseClicked(final MouseEvent e)
	{
		synchronized (doubleClick)
		{
			if (!doubleClick)
			{
				doubleClick = true;

				new Thread(() -> {
					try
					{
						Thread.sleep(400);
					}
					catch (Exception exc)
					{
						exc.printStackTrace();
					}

					doubleClick = false;

				}, "Double-Click_Timer").start();
			}
			else
			{
				Component c = (Component) e.getSource();
				DragObject o = (DragObject) c.getParent();

				switch (c.getName())
				{
					case "Header":
					case "Text + Title":
					case "Text":
					case "Image + Title":
					case "Image(Small) + Title":
					case "Image":
					case "Footer":
						controller.dragObjectClicked(o);
						break;

					default:
						break;
				}
			}
		}
	}

	@Override
	public void mouseEntered(final MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(final MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(final MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(final MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void buttonClosePressed(final InternalView view)
	{
		controller.hidePopUp(view);
	}

	// <- Getter & Setter ->
	// <- Static ->
}
