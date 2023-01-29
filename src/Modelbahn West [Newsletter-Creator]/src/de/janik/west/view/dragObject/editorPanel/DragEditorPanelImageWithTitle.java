package de.janik.west.view.dragObject.editorPanel;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.NONE;
import static javax.swing.BorderFactory.createCompoundBorder;
import static javax.swing.BorderFactory.createEtchedBorder;
import static javax.swing.BorderFactory.createMatteBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// <- Import ->
// <- Static_Import ->
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import de.janik.util.imageUtil.ImageLoader;
import de.janik.util.searchBar.SearchBar;
import de.janik.west.listener.BasicListener;
import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.util.Resources;
import de.janik.west.view.InternalView;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class DragEditorPanelImageWithTitle extends DragEditorPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final JLabel		labelTitle;
	private final JLabel		labelImage;

	private String				url;

	// <- Static ->

	// <- Constructor ->
	public DragEditorPanelImageWithTitle(final DragObjectPanelListener listener)
	{
		super(listener);

		url = "";

		labelImage = new JLabel(Resources.IMAGEICON_DRAG_EDITOR_NO_IMAGE)
		{
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void paintComponent(Graphics g)
			{
				BufferedImage img = new BufferedImage(labelImage.getIcon().getIconWidth(), labelImage.getIcon().getIconHeight(), BufferedImage.TYPE_INT_ARGB);
				labelImage.getIcon().paintIcon(null, img.getGraphics(), 0, 0);
				g.drawImage(img, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);
			}
		};

		addMouseListener(new MouseAdapter()
		{
			private Cursor	cursor	= new Cursor(Cursor.DEFAULT_CURSOR);

			@Override
			public void mouseEntered(MouseEvent e)
			{
				RootPaneContainer root = (RootPaneContainer) getTopLevelAncestor();
				cursor = root.getGlassPane().getCursor();
				root.getGlassPane().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				RootPaneContainer root = (RootPaneContainer) getTopLevelAncestor();
				root.getGlassPane().setCursor(cursor);
			}
		});

		labelTitle = new JLabel("Title !~!");
		labelTitle.setFont(new Font("Verdana", Font.BOLD, 18));
		labelTitle.setForeground(Color.BLACK);

		add(labelTitle, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, HORIZONTAL, new Insets(2, 5, 0, 0), 0, 0));
		add(labelImage, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	public InternalView getPopUp()
	{
		return new PopUImageWithTitle();
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

	public BufferedImage getImage()
	{
		BufferedImage img = new BufferedImage(labelImage.getIcon().getIconWidth(), labelImage.getIcon().getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		labelImage.getIcon().paintIcon(null, img.getGraphics(), 0, 0);
		img.getGraphics().drawImage(img, 0, 0, labelImage.getWidth(), labelImage.getHeight(), null);

		return img;
	}

	// <- Static ->

	private class PopUImageWithTitle extends InternalView
	{
		private static final long	serialVersionUID	= 1L;

		private final JPanel		panel;

		private final JLabel		labelImage;
		private final JLabel		labelOpen;

		private final SearchBar		searchBar;
		private final SearchBar		searchBarURL;

		private final JButton		buttonSave;
		private final JButton		buttonCancel;

		private final JTextField	textFieldTitle;

		private Boolean				doubleClicked		= false;

		public PopUImageWithTitle()
		{
			super("PopUp-Image + Title", 400, 300, listener);

			panel = new JPanel();
			panel.setLayout(new GridBagLayout());

			labelImage = new JLabel(DragEditorPanelImageWithTitle.this.labelImage.getIcon());
			labelImage.setBorder(createCompoundBorder(createMatteBorder(1, 1, 1, 1, Color.BLACK), createEtchedBorder(EtchedBorder.LOWERED)));

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

			JPanel panelButtons = new JPanel();
			panelButtons.setLayout(new GridBagLayout());
			panelButtons.setOpaque(false);

			buttonCancel = new JButton("cancel");
			buttonCancel.setFocusPainted(false);
			buttonCancel.addActionListener(e -> listener.buttonClosePressed(this));

			searchBarURL = new SearchBar(url.equals("") ? "Paste your link's URL here." : url, null, true);
			
			textFieldTitle = new JTextField(labelTitle.getText());
			textFieldTitle.setForeground(Color.BLACK);
			textFieldTitle.setFont(new Font("Verdana", Font.BOLD, 16));
			
			buttonSave = new JButton("save");
			buttonSave.setFocusPainted(false);
			buttonSave.addActionListener(e -> {
				DragEditorPanelImageWithTitle.this.labelImage.setIcon(labelImage.getIcon());
				labelTitle.setText(textFieldTitle.getText());
				url = searchBarURL.getUserInput().equals("") ? url : searchBarURL.getUserInput();
				listener.buttonClosePressed(this);
			});

			panelButtons.add(buttonCancel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, EAST, NONE, new Insets(0, 4, 4, 4), 0, 0));
			panelButtons.add(buttonSave, new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, CENTER, NONE, new Insets(0, 4, 4, 4), 0, 0));

			panel.add(textFieldTitle, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, CENTER, HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
			panel.add(labelImage, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, CENTER, BOTH, new Insets(4, 4, 4, 4), 0, 0));
			panel.add(searchBarURL, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(4, 4, 4, 4), 0, 0));
			panel.add(searchBar, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0, CENTER, HORIZONTAL, new Insets(0, 4, 4, 4), 0, 0));
			panel.add(labelOpen, new GridBagConstraints(1, 3, 1, 1, 0.0, 1.0, EAST, NONE, new Insets(0, 0, 4, 4), 0, 0));
			panel.add(panelButtons, new GridBagConstraints(0, 4, 2, 1, 1.0, 1.0, CENTER, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

			panelInner.add(panel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));

			addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					synchronized (doubleClicked)
					{
						if (!doubleClicked)
						{
							doubleClicked = true;

							new Thread(() -> {
								try
								{
									Thread.sleep(400);
								}
								catch (Exception exc)
								{
									exc.printStackTrace();
								}

								doubleClicked = false;

							}, "Double-Click_Timer").start();
						}
						else
							load();
					}
				}
			});

			setFocusTraversalPolicy(new FocusTraversalPolicy()
			{
				@Override
				public Component getLastComponent(Container c)
				{
					return searchBar.getTextField();
				}

				@Override
				public Component getFirstComponent(Container c)
				{
					return buttonCancel;
				}

				@Override
				public Component getDefaultComponent(Container c)
				{
					return buttonCancel;
				}

				@Override
				public Component getComponentBefore(Container c, Component component)
				{
					if (component == searchBar.getTextField())
						return buttonSave;
					else
						if (component == buttonSave)
							return getFirstComponent(c);
						else
							return getLastComponent(c);
				}

				@Override
				public Component getComponentAfter(Container c, Component component)
				{
					if (component == buttonCancel)
						return buttonSave;
					else
						if (component == buttonSave)
							return getLastComponent(c);
						else
							return getFirstComponent(c);
				}
			});

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
					labelImage.setIcon(ImageLoader.GetInstance().setInputFile(file).load().resizeToFitWidth(700).getImageIcon());
				}
			}
			else
				if (searchBar.getUserInput().startsWith("http://") || searchBar.getUserInput().startsWith("https://"))
					labelImage.setIcon(ImageLoader.GetInstance().setInputURL(searchBar.getUserInput()).load().resizeToFitWidth(700).getImageIcon());

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
