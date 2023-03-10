package de.janik.west.view.panel.editor;

// <- Import ->
// <- Static_Import ->
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.NORTH;
import static java.awt.GridBagConstraints.SOUTH;
import static java.awt.GridBagConstraints.VERTICAL;
import static java.awt.GridBagConstraints.WEST;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import de.janik.west.listener.BasicListener;
import de.janik.west.util.Resources;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragEditor extends Editor
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JScrollPane	pane;

	private final JPanel		panelOuter;
	private final JPanel		panelInner;
	private final JPanel		panelDragEditors;
	private final JPanel		panelControlls;

	private final JLabel		labelDragN_Drop;

	private final JPanel		panelFiller;

	// <- Static ->

	// <- Constructor ->
	public DragEditor()
	{
		// setBackground(new Color(200, 221, 242));
		setBackground(Color.GRAY);

		panelOuter = new JPanel();
		panelOuter.setLayout(new GridBagLayout());
		panelOuter.setOpaque(false);

		pane = new JScrollPane(panelOuter);
		pane.getVerticalScrollBar().setUnitIncrement(12);
		pane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		pane.getViewport().setOpaque(false);
		pane.setOpaque(false);

		panelInner = new JPanel();
		panelInner.setLayout(new GridBagLayout());
		panelInner.setOpaque(false);
		panelInner.setPreferredSize(new Dimension(720, 0));

		panelDragEditors = new JPanel();
		panelDragEditors.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		panelDragEditors.setLayout(new GridBagLayout());
		panelDragEditors.setBackground(Color.WHITE);
		panelDragEditors.setPreferredSize(new Dimension(700, 0));

		panelControlls = new JPanel();
		panelControlls.setLayout(new GridBagLayout());
		panelControlls.setOpaque(false);
		panelControlls.setPreferredSize(new Dimension(20, 0));

		panelFiller = new JPanel();
		panelFiller.setOpaque(false);

		labelDragN_Drop = new JLabel(Resources.IMAGEICON_DRAG_N_NDROP);

		panelDragEditors.add(labelDragN_Drop, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, SOUTH, NONE, new Insets(0, 0, 0, 0), 0, 0));

		panelInner.add(panelDragEditors, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, WEST, VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
		panelInner.add(panelControlls, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, WEST, VERTICAL, new Insets(2, 0, 2, 0), 0, 0));

		panelOuter.add(panelInner, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, VERTICAL, new Insets(50, 90, 50, 90), 0, 0));

		add(pane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{

	}

	// <- Getter & Setter ->
	public void setDragEditorPanel(final List<DragEditorPanel> dragEditorPanel, final boolean scrol)
	{
		panelDragEditors.removeAll();
		panelControlls.removeAll();

		int length = dragEditorPanel.size();
		int height = 0;

		for (int i = 0; i < length; i++)
		{
			DragEditorPanel panel = dragEditorPanel.get(i);

			int minHeight = 63 - (int) panel.getPreferredSize().getHeight();
			minHeight = minHeight <= 0 ? 0 : minHeight;

			height += panel.getPreferredSize().getHeight() + minHeight;

			JPanel panelControlls = panel.getControllsPanel();
			panelControlls.setPreferredSize(new Dimension(0, (int) panel.getPreferredSize().getHeight()));

			panelDragEditors.add(panel, new GridBagConstraints(0, i, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0), 0, minHeight));
			this.panelControlls.add(panelControlls, new GridBagConstraints(0, i, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(0, 0, 0, 0),
					0, minHeight));
		}

		height += labelDragN_Drop.getPreferredSize().getHeight();
		panelDragEditors.add(labelDragN_Drop, new GridBagConstraints(0, length + 1, 1, 1, 1.0, 1.0, SOUTH, NONE, new Insets(0, 0, 0, 0), 0,
				0));
		panelControlls.add(panelFiller, new GridBagConstraints(0, length + 1, 1, 1, 1.0, 1.0, SOUTH, BOTH, new Insets(0, 0, 0, 0), 0, 0));

		panelInner.setPreferredSize(new Dimension(720, height));

		panelOuter.revalidate();
		panelOuter.repaint();

		if (scrol)
			SwingUtilities.invokeLater(() -> {
				JScrollBar scroolBar_vertical = this.pane.getVerticalScrollBar();
				scroolBar_vertical.setValue(scroolBar_vertical.getMaximum());
			});
	}
	// <- Static ->

}
