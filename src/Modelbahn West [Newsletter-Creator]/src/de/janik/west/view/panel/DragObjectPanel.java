package de.janik.west.view.panel;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.NORTH;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.janik.west.listener.BasicListener;
import de.janik.west.view.dragObject.DragObject;
// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragObjectPanel extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	private final JScrollPane	scrollPane;

	private final JPanel		objectPanel;

	// <- Static ->

	// <- Constructor ->
	public DragObjectPanel()
	{
		setOpaque(false);

		objectPanel = new JPanel();
		objectPanel.setOpaque(false);
		objectPanel.setLayout(new GridBagLayout());

		scrollPane = new JScrollPane(objectPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	public void setVisibleDragObjects(final List<DragObject> dragObjects)
	{
		objectPanel.removeAll();

		int length = dragObjects.size();

		for (int i = 0; i < length; i++)
		{
			if (i == length - 1)
				objectPanel.add(dragObjects.get(i), new GridBagConstraints(0, i, 1, 1, 1.0, 1.0, NORTH, NONE, new Insets(10, 0, 10, 0), 0,
						0));
			else
				objectPanel.add(dragObjects.get(i), new GridBagConstraints(0, i, 1, 1, 1.0, 0.0, CENTER, NONE, new Insets(10, 0, 0, 0), 0,
						0));
		}

		revalidate();
		repaint();
	}

	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->
	// <- Static ->
}
