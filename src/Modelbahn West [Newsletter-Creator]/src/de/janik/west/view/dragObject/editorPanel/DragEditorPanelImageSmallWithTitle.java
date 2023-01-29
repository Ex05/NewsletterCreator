package de.janik.west.view.dragObject.editorPanel;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.NORTH;
import static java.awt.GridBagConstraints.NORTHEAST;
import static java.awt.GridBagConstraints.NORTHWEST;
import static java.awt.GridBagConstraints.SOUTHWEST;
import static java.awt.GridBagConstraints.WEST;
import static javax.swing.BorderFactory.createCompoundBorder;
import static javax.swing.BorderFactory.createEtchedBorder;
import static javax.swing.BorderFactory.createMatteBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import de.janik.util.imageUtil.ImageLoader;
import de.janik.util.searchBar.SearchBar;
import de.janik.west.listener.BasicListener;
import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.util.Resources;
// <- Import ->
// <- Static_Import ->
import de.janik.west.view.InternalView;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragEditorPanelImageSmallWithTitle extends DragEditorPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JLabel		labelTitle;

	private final JLabel		labelImage;
	private final JPanel		panelText;

	private final JLabel		labelUvp;
	private final JLabel		labelUvpPrice;
	private final JLabel		labelOnly;
	private final JLabel		labelPrice;
	private final JLabel		labelEuro;
	private final JLabel		labelDeliveryCost;
	private final JLabel		labelDeliveryTime;
	private final JLabel		labelBuyNow;

	private String				url;

	// <- Static ->

	// <- Constructor ->
	public DragEditorPanelImageSmallWithTitle(final DragObjectPanelListener listener)
	{
		super(listener);

		url = "";

		labelTitle = new JLabel("Enter Title...");
		labelTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		labelTitle.setForeground(Color.BLACK);

		labelImage = new JLabel(Resources.IMAGEICON_DRAG_EDITOR_NO_IMAGE_SMALL);

		panelText = new JPanel();
		panelText.setLayout(new GridBagLayout());
		panelText.setOpaque(false);

		Font font = new Font("Verdana", Font.BOLD, 13);

		labelUvp = new JLabel("Statt UVP:");
		labelUvp.setForeground(Color.BLACK);
		labelUvp.setFont(font);

		labelUvpPrice = new JLabel("<html><s>00,00 EUR</s></html>");
		labelUvpPrice.setForeground(Color.GRAY);
		labelUvpPrice.setFont(font.deriveFont(11.0f));

		labelOnly = new JLabel("Nur");
		labelOnly.setFont(font.deriveFont(18.0f));
		labelOnly.setForeground(Color.RED);

		labelPrice = new JLabel("00,00");
		labelPrice.setForeground(Color.RED);
		labelPrice.setFont(font.deriveFont(28.0f));

		labelEuro = new JLabel("<html><sub>EUR</sub></html>");
		labelEuro.setForeground(Color.RED);
		labelEuro.setFont(font.deriveFont(20.0f));

		labelDeliveryCost = new JLabel("inkl. 19% MwSt. zzgl. Versandkosten");
		labelDeliveryCost.setForeground(Color.GRAY);
		labelDeliveryCost.setFont(font.deriveFont(10.0f));

		labelDeliveryTime = new JLabel("Lieferzeit: ca. 00 Tage");
		labelDeliveryTime.setForeground(Color.BLACK);
		labelDeliveryTime.setFont(font.deriveFont(10.0f));

		labelBuyNow = new JLabel(Resources.IMAGEICON_DRAG_EDITOR_BUY_NOW);

		panelText.add(labelUvp, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 10, 0, 5), 0, 0));
		panelText.add(labelUvpPrice, new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0, CENTER, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		panelText.add(labelOnly, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, EAST, NONE, new Insets(0, 10, 0, 5), 0, 0));
		panelText.add(labelPrice, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(0, 0, 0, 0), 0, 0));
		panelText.add(labelEuro, new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, WEST, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		panelText.add(labelDeliveryCost, new GridBagConstraints(0, 2, 3, 1, 0.0, 1.0, CENTER, HORIZONTAL, new Insets(5, 15, 5, 0), 0, 0));
		panelText.add(labelDeliveryTime, new GridBagConstraints(0, 3, 3, 1, 0.0, 1.0, CENTER, HORIZONTAL, new Insets(0, 15, 10, 0), 0, 0));

		panelText.add(labelBuyNow, new GridBagConstraints(0, 4, 3, 1, 1.0, 1.0, SOUTHWEST, NONE, new Insets(0, 10, 30, 0), 0, 0));

		add(labelTitle, new GridBagConstraints(0, 0, 0, 1, 0.0, 0.0, NORTHEAST, HORIZONTAL, new Insets(5, 15, 10, 0), 0, 0));

		add(labelImage, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0, CENTER, BOTH, new Insets(0, 30, 10, 5), 0, 0));
		add(panelText, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 5, 10, 10), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	public InternalView getPopUp()
	{
		return new PopUpIageSmallWithTitle();
	}

	@Override
	public void addListener(final BasicListener listener)
	{
		// TODO Auto-generated method stub

	}

	// <- Getter & Setter ->
	public String getURL()
	{
		return url;
	}

	public String getTitle()
	{
		return labelTitle.getText();
	}

	public String getPriceNew()
	{
		return labelPrice.getText();
	}

	public String getPriceUVP()
	{
		return labelUvpPrice.getText().split("<html><s>")[1].split(" EUR</s></html>")[0];
	}

	public String getDeliveryTime()
	{
		return labelDeliveryTime.getText();
	}

	public BufferedImage getImage()
	{
		BufferedImage img = new BufferedImage(labelImage.getIcon().getIconWidth(), labelImage.getIcon().getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		labelImage.getIcon().paintIcon(null, img.getGraphics(), 0, 0);
		img.getGraphics().drawImage(img, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);

		return img;
	}

	// <- Static ->

	private class PopUpIageSmallWithTitle extends InternalView
	{
		private static final long	serialVersionUID	= 1L;

		private final JPanel		panel;
		private final JPanel		panelImageAndContent;
		private final JPanel		panelImage;
		private final JPanel		panelContent;
		private final JPanel		panelUVP;
		private final JPanel		panelPrice;
		private final JPanel		panelDeliveryTime;

		private final JTextField	textFieldTitle;

		private final JLabel		labelOpen;
		private final JLabel		labelImage;
		private final JLabel		labelUvp;
		private final JLabel		labelNur;
		private final JLabel		labelEUR;
		private final JLabel		labelDeliveryCost;
		private final JLabel		labelDeliveryTime;
		private final JLabel		labelDays;
		private final JLabel		labelBuyNow;

		private final JTextField	textFieldUVP;
		private final JTextField	textFieldPrice;

		private final JSpinner		spinnerDeliveryDays;

		private final JButton		buttonSave;
		private final JButton		buttonCancel;

		private final SearchBar		searchBar;
		private final SearchBar		searchBarURL;

		public PopUpIageSmallWithTitle()
		{
			super("PopUp-Image(Small) + Title", 500, 300, listener);

			Font font = new Font("Verdana", Font.BOLD, 13);

			panel = new JPanel();
			panel.setLayout(new GridBagLayout());

			panelInner.add(panel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));

			textFieldTitle = new JTextField(labelTitle.getText());
			textFieldTitle.setForeground(Color.BLACK);
			textFieldTitle.setFont(new Font("Verdana", Font.BOLD, 16));

			labelOpen = new JLabel(Resources.IMAGEICON_DRAG_EDITOR_CROSS);
			labelOpen.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseEntered(final MouseEvent e)
				{
					labelOpen.setIcon(Resources.IMAGEICON_DRAG_EDITOR_CROSS_MOUSEOVER);
				}

				@Override
				public void mouseExited(final MouseEvent e)
				{
					labelOpen.setIcon(Resources.IMAGEICON_DRAG_EDITOR_CROSS);
				}

				@Override
				public void mouseClicked(final MouseEvent e)
				{
					load();
				}
			});

			searchBar = new SearchBar("Paste your image's URL here.", null, true);
			searchBar.addSearchBarListener(e -> load());
			searchBar.addSearchBarInputListener(e -> load());

			panelImageAndContent = new JPanel();
			panelImageAndContent.setLayout(new GridBagLayout());

			panelImage = new JPanel();
			panelImage.setLayout(new GridBagLayout());

			labelImage = new JLabel(DragEditorPanelImageSmallWithTitle.this.labelImage.getIcon());
			labelImage.setBorder(createCompoundBorder(createMatteBorder(1, 1, 1, 1, Color.BLACK), createEtchedBorder(EtchedBorder.LOWERED)));

			panelImage.add(labelImage, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, NONE, new Insets(4, 4, 4, 4), 0, 0));

			panelContent = new JPanel();
			panelContent.setLayout(new GridBagLayout());

			panelUVP = new JPanel();
			panelUVP.setLayout(new GridBagLayout());

			labelUvp = new JLabel("Statt UVP:");
			labelUvp.setForeground(Color.BLACK);
			labelUvp.setFont(font);

			textFieldUVP = new JTextField(labelUvpPrice.getText().split("<html><s>")[1].split(" EUR</s></html>")[0]);
			textFieldUVP.setFont(font.deriveFont(14.0f));
			textFieldUVP.setHorizontalAlignment(SwingConstants.CENTER);

			panelUVP.add(labelUvp, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, WEST, NONE, new Insets(4, 4, 4, 4), 0, 0));
			panelUVP.add(textFieldUVP, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, WEST, NONE, new Insets(4, 0, 4, 4), 20, 0));

			panelPrice = new JPanel();
			panelPrice.setLayout(new GridBagLayout());

			labelNur = new JLabel("Nur");
			labelNur.setFont(font.deriveFont(18.0f));
			labelNur.setForeground(Color.RED);

			labelEUR = new JLabel("EUR");
			labelEUR.setFont(font.deriveFont(18.0f));
			labelEUR.setForeground(Color.RED);

			textFieldPrice = new JTextField(labelPrice.getText());
			textFieldPrice.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPrice.setFont(font.deriveFont(18.0f));
			textFieldPrice.setForeground(Color.RED);

			panelPrice.add(labelNur, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, EAST, NONE, new Insets(0, 0, 4, 4), 0, 0));
			panelPrice.add(textFieldPrice, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 4, 0), 0, 0));
			panelPrice.add(labelEUR, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, WEST, NONE, new Insets(0, 4, 4, 0), 0, 0));

			labelDeliveryCost = new JLabel("inkl. 19% MwSt. zzgl. Versandkosten");
			labelDeliveryCost.setForeground(Color.GRAY);
			labelDeliveryCost.setFont(font.deriveFont(10.0f));

			panelDeliveryTime = new JPanel();
			panelDeliveryTime.setLayout(new GridBagLayout());

			labelDeliveryTime = new JLabel("Lieferzeit: ca.");
			labelDeliveryTime.setForeground(Color.BLACK);
			labelDeliveryTime.setFont(font.deriveFont(10.0f));

			spinnerDeliveryDays = new JSpinner(new SpinnerNumberModel(Integer.parseInt(DragEditorPanelImageSmallWithTitle.this.labelDeliveryTime.getText()
					.split("Lieferzeit: ca. ")[1].split(" Tage")[0]), 0, 99, 1));
			spinnerDeliveryDays.setFont(font.deriveFont(12.0f));
			((JSpinner.DefaultEditor) spinnerDeliveryDays.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
			((JSpinner.DefaultEditor) spinnerDeliveryDays.getEditor()).getTextField().setForeground(Color.BLACK);

			labelDays = new JLabel("Tage");
			labelDays.setForeground(Color.BLACK);
			labelDays.setFont(font.deriveFont(10.0f));

			panelDeliveryTime.add(labelDeliveryTime, new GridBagConstraints(0, 0, 1, 1, 0.4, 0.0, EAST, NONE, new Insets(0, 0, 0, 4), 0, 0));
			panelDeliveryTime.add(spinnerDeliveryDays, new GridBagConstraints(1, 0, 1, 1, 0.2, 0.0, CENTER, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			panelDeliveryTime.add(labelDays, new GridBagConstraints(2, 0, 1, 1, 0.4, 0.0, WEST, NONE, new Insets(0, 4, 0, 0), 0, 0));

			labelBuyNow = new JLabel(Resources.IMAGEICON_DRAG_EDITOR_BUY_NOW);

			panelContent.add(panelUVP, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(4, 0, 0, 0), 0, 0));
			panelContent.add(panelPrice, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(4, 0, 0, 0), 0, 0));
			panelContent.add(labelDeliveryCost, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(4, 10, 4, 8), 0, 0));
			panelContent.add(panelDeliveryTime, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(4, 0, 4, 0), 0, 0));
			panelContent.add(labelBuyNow, new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0, NORTHWEST, NONE, new Insets(12, 0, 8, 0), 0, 0));

			panelImageAndContent.add(panelImage, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, CENTER, NONE, new Insets(0, 0, 0, 4), 0, 0));
			panelImageAndContent.add(panelContent, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, CENTER, NONE, new Insets(0, 4, 0, 0), 0, 0));

			JPanel panelButtons = new JPanel();
			panelButtons.setLayout(new GridBagLayout());
			panelButtons.setOpaque(false);

			buttonCancel = new JButton("cancel");
			buttonCancel.setFocusPainted(false);
			buttonCancel.addActionListener(e -> listener.buttonClosePressed(this));

			searchBarURL = new SearchBar(url.equals("") ? "Paste your link's URL here." : url, null, true);
			
			buttonSave = new JButton("save");
			buttonSave.setFocusPainted(false);
			buttonSave.addActionListener(e -> {
				labelTitle.setText(textFieldTitle.getText());
				labelUvpPrice.setText("<html><s>" + textFieldUVP.getText().replace(".", ",") + " EUR</s></html>");
				labelPrice.setText(textFieldPrice.getText().replace(".", ","));
				DragEditorPanelImageSmallWithTitle.this.labelDeliveryTime.setText("Lieferzeit: ca. "
						+ (((int) spinnerDeliveryDays.getValue()) <= 9 ? "0" + spinnerDeliveryDays.getValue() : spinnerDeliveryDays.getValue()) + " Tage");
				url = searchBarURL.getUserInput().equals("") ? url : searchBarURL.getUserInput();
				DragEditorPanelImageSmallWithTitle.this.labelImage.setIcon(labelImage.getIcon());
				listener.buttonClosePressed(this);
			});

			panelButtons.add(buttonCancel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, EAST, NONE, new Insets(0, 4, 4, 4), 0, 0));
			panelButtons.add(buttonSave, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, CENTER, NONE, new Insets(0, 4, 4, 4), 0, 0));

			panel.add(textFieldTitle, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0, NORTH, HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
			panel.add(panelImageAndContent, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
			panel.add(searchBarURL, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 4, 0, 4), 0, 0));
			panel.add(searchBar, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, CENTER, HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
			panel.add(labelOpen, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, EAST, NONE, new Insets(4, 0, 4, 4), 0, 0));
			panel.add(panelButtons, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, EAST, NONE, new Insets(0, 0, 0, 0), 0, 0));

			pack();
		}

		private void load()
		{
			if (searchBar.getUserInput().equals(new String()))
			{
				final JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
				FileFilter filter = new FileFilter()
				{
					@Override
					public String getDescription()
					{
						return "'.png', '.jpg'";
					}

					@Override
					public boolean accept(File f)
					{
						return f.getAbsolutePath().endsWith(".png") || f.getAbsolutePath().endsWith(".jpg") || f.isDirectory();
					}
				};
				chooser.setFileFilter(filter);

				int value = chooser.showOpenDialog(this);

				if (value == JFileChooser.APPROVE_OPTION)
				{
					File file = chooser.getSelectedFile();
					searchBar.getTextField().setText(file.getAbsolutePath());
					labelImage.setIcon(ImageLoader.GetInstance().setInputFile(file).load().resize(250, 183).getImageIcon());
				}
			}
			else
				if (searchBar.getUserInput().startsWith("http://") || searchBar.getUserInput().startsWith("https://"))
					labelImage.setIcon(ImageLoader.GetInstance().setInputURL(searchBar.getUserInput()).load().resize(250, 183).getImageIcon());

			SwingUtilities.invokeLater(() -> {
				searchBar.clearInput();
				searchBar.getTextField().setFocusable(false);
				pack();
				searchBar.getTextField().setFocusable(true);
			});
		}

		@Override
		public void addListener(BasicListener listener)
		{
			// TODO Auto-generated method stub

		}

	}
}
