package de.janik.west.view.dragObject.editorPanel;

import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.NORTH;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.util.Resources;
import de.janik.west.view.InternalView;
import de.janik.west.view.panel.ViewPanel;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class DragEditorPanel extends ViewPanel
{
	// <- Public ->

	// <- Protected ->
	protected final DragObjectPanelListener	listener;

	// <- Private->
	private static final long				serialVersionUID	= 1l;

	private volatile Boolean				doubleCLicked		= false;

	// <- Static ->

	// <- Constructor ->
	public DragEditorPanel(final DragObjectPanelListener listener)
	{
		this.listener = listener;

		setBackground(Color.WHITE);
		setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				synchronized (doubleCLicked)
				{
					if (!doubleCLicked)
					{
						doubleCLicked = true;

						new Thread(() -> {
							try
							{
								Thread.sleep(400);
							}
							catch (Exception exc)
							{
								exc.printStackTrace();
							}

							doubleCLicked = false;

						}, "Double-Click_Timer").start();
					}
					else
						listener.showPopUp(DragEditorPanel.this);
				}
			}
		});
	}

	// <- Abstract ->
	public abstract InternalView getPopUp();

	@Override
	public abstract void addListener(final BasicListener listener);

	// <- Object ->
	public JPanel getControllsPanel()
	{
		JLabel labeUp = new JLabel(Resources.IMAGEICON_DRAG_EDITOR_UP);
		labeUp.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(final MouseEvent e)
			{
				labeUp.setIcon(Resources.IMAGEICON_DRAG_EDITOR_UP_MOUSEOVER);
			}

			@Override
			public void mouseExited(final MouseEvent e)
			{
				labeUp.setIcon(Resources.IMAGEICON_DRAG_EDITOR_UP);
			}

			public void mouseClicked(final MouseEvent e)
			{
				listener.buttonUpPressed(DragEditorPanel.this);
			};
		});

		JLabel labeDown = new JLabel(Resources.IMAGEICON_DRAG_EDITOR_DOWN);
		labeDown.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(final MouseEvent e)
			{
				labeDown.setIcon(Resources.IMAGEICON_DRAG_EDITOR_DOWN_MOUSEOVER);
			}

			@Override
			public void mouseExited(final MouseEvent e)
			{
				labeDown.setIcon(Resources.IMAGEICON_DRAG_EDITOR_DOWN);
			}

			public void mouseClicked(final MouseEvent e)
			{
				listener.buttonDownPressed(DragEditorPanel.this);
			};
		});

		JLabel labelClose = new JLabel(Resources.IMAGEICON_DRAG_EDITOR_CLOSE);
		labelClose.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(final MouseEvent e)
			{
				labelClose.setIcon(Resources.IMAGEICON_DRAG_EDITOR_CLOSE_MOUSEOVER);
			}

			@Override
			public void mouseExited(final MouseEvent e)
			{
				labelClose.setIcon(Resources.IMAGEICON_DRAG_EDITOR_CLOSE);
			}

			@Override
			public void mouseClicked(final MouseEvent e)
			{
				listener.buttonClosePressed(DragEditorPanel.this);
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setOpaque(false);

		panel.add(labelClose, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, NORTH, NONE, new Insets(1, 1, 1, 0), 0, 0));
		panel.add(labeUp, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, NORTH, NONE, new Insets(1, 1, 1, 0), 0, 0));
		panel.add(labeDown, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, NORTH, NONE, new Insets(1, 1, 1, 0), 0, 0));

		return panel;
	}

	// <- Getter & Setter ->
	// <- Static ->
}
