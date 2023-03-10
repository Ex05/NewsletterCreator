package de.janik.west.view.panel;

// <- Import ->
// <- Static_Import ->
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import de.janik.west.listener.BasicListener;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public class FontPanel extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long		serialVersionUID	= 1;

	private final GridBagLayout		gbl;

	private final JComboBox<String>	comboBox_font;
	private final JComboBox<String>	comboBox_fontType;
	private final JSpinner			spinner_font_Size;

	// <- Static ->

	// <- Constructor ->
	public FontPanel()
	{
		super();

		gbl = new GridBagLayout();
		setOpaque(false);

		setLayout(gbl);

		String[] fonts = new String[] { "Verdana", "Arial", "Times New Roman" };
		comboBox_font = new JComboBox<String>(fonts);
		((JLabel) comboBox_font.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		String[] fontTypes = new String[] { "Plain", "Bold", "Italic" };
		comboBox_fontType = new JComboBox<>(fontTypes);

		SpinnerModel model = new SpinnerNumberModel(12, 0, 48, 1);
		spinner_font_Size = new JSpinner(model);
		spinner_font_Size.setFont(new Font("Verdana", Font.BOLD, 11));

		JComponent c = spinner_font_Size.getEditor();
		if (c instanceof JSpinner.DefaultEditor)
			((JSpinner.DefaultEditor) c).getTextField().setHorizontalAlignment(SwingConstants.CENTER);

		add(comboBox_font, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, HORIZONTAL, new Insets(0, 0, 0, 4), 0, 0));
		add(comboBox_fontType, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, CENTER, NONE, new Insets(0, 0, 0, 4), 0, 0));
		add(spinner_font_Size, new GridBagConstraints(2, 0, 1, 1, 0.0, 1.0, CENTER, NONE, new Insets(0, 0, 0, 2), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void addListener(final BasicListener listener)
	{
		// TODO Auto-generated method stub

	}
	// <- Getter & Setter ->
	// <- Static ->
}
