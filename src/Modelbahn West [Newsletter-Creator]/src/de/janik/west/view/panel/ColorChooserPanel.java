package de.janik.west.view.panel;

// <- Import ->
// <- Static_Import ->
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.colorselectionListener.ColorSelectionListener;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class ColorChooserPanel extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final ColorPanel[]	panel;

	private final ColorSelectionPanel	selectedColorPanel;

	// <- Static ->

	// <- Constructor ->
	public ColorChooserPanel()
	{
		super();

		setOpaque(false);

		panel = new ColorPanel[20];

		panel[0] = new ColorPanel(new Color(0, 0, 0));
		panel[1] = new ColorPanel(new Color(255, 255, 255));

		panel[2] = new ColorPanel(new Color(127, 127, 127));
		panel[3] = new ColorPanel(new Color(195, 195, 195));

		panel[4] = new ColorPanel(new Color(136, 0, 21));
		panel[5] = new ColorPanel(new Color(185, 122, 87));

		panel[6] = new ColorPanel(new Color(237, 28, 36));
		panel[7] = new ColorPanel(new Color(255, 174, 201));

		panel[8] = new ColorPanel(new Color(255, 127, 39));
		panel[9] = new ColorPanel(new Color(255, 242, 0));

		panel[10] = new ColorPanel(new Color(255, 201, 14));
		panel[11] = new ColorPanel(new Color(239, 228, 176));

		panel[12] = new ColorPanel(new Color(34, 177, 76));
		panel[13] = new ColorPanel(new Color(181, 230, 29));

		panel[14] = new ColorPanel(new Color(63, 72, 204));
		panel[15] = new ColorPanel(new Color(112, 146, 190));

		panel[16] = new ColorPanel(new Color(0, 162, 232));
		panel[17] = new ColorPanel(new Color(153, 217, 234));

		panel[18] = new ColorPanel(new Color(163, 73, 164));
		panel[19] = new ColorPanel(new Color(200, 191, 231));

		selectedColorPanel = new ColorSelectionPanel();

		add(selectedColorPanel, new GridBagConstraints(0, 0, 1, 2, 1.0, 1.0, CENTER, BOTH, new Insets(0, 1, 0, 2), 16, 0));

		for (int i = 0; i < 10; i++)
		{
			add(panel[i * 2], new GridBagConstraints(1 + i * 2, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
			add(panel[i * 2 + 1], new GridBagConstraints(1 + i * 2, 1, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof ColorSelectionListener)
			for (ColorPanel panel : this.panel)
				panel.addColorSelectionListener((ColorSelectionListener) listener);
	}

	// <- Getter & Setter ->
	public void setSelectedColor(final Color color)
	{
		selectedColorPanel.setSelectedColor(color);
	}
	
	// <- Static ->
}
