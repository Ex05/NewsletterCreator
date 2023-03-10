package de.janik.west.view;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.NORTHEAST;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
// <- Import ->
// <- Static_Import ->
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.internalView.InternalViewListener;
import de.janik.west.util.Resources;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public abstract class InternalView extends JInternalFrame implements I_ViewComponent
{
	// <- Public ->

	// <- Protected ->
	protected final JPanel				panelInner;

	// <- Private->
	private static final long			serialVersionUID	= 1L;

	private final JLabel				labelTitle;
	private final JLabel				labelClose;

	@SuppressWarnings("unused")
	private final InternalViewListener	listener;

	private final GridBagLayout			gbl;

	// <- Static ->

	// <- Constructor ->
	public InternalView(final String title, final int width, final int height, final InternalViewListener listener)
	{
		super(title, false, true, false, false);

		gbl = new GridBagLayout();

		this.listener = listener;

		setLayout(gbl);

		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setBackground(new Color(26, 120, 200));
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(20, 90, 155)));

		panelInner = new JPanel();
		panelInner.setLayout(gbl);
		panelInner.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(20, 90, 155)));

		labelTitle = new JLabel(title);
		labelTitle.setFont(new Font("Arial", Font.PLAIN, 16));
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

		labelTitle.setCursor(new Cursor(Cursor.MOVE_CURSOR));

		labelTitle.addMouseMotionListener(new MouseMotionAdapter()
		{
			int	mouseX, mouseY;

			@Override
			public void mouseMoved(final MouseEvent e)
			{
				mouseX = e.getX();
				mouseY = e.getY();
			}

			@Override
			public void mouseDragged(final MouseEvent e)
			{
				int x = (int) getLocation().getX() - mouseX + e.getX();
				int y = (int) getLocation().getY() - mouseY + e.getY();

				x = x >= 0 ? x : 0;
				y = y >= 0 ? y : 0;

				Component glassPane = ((RootPaneContainer) getTopLevelAncestor()).getGlassPane();

				x = x >= glassPane.getWidth() - getWidth() ? glassPane.getWidth() - getWidth() : x;
				y = y >= glassPane.getHeight() - getHeight() ? glassPane.getHeight() - getHeight() : y;

				setLocation(x, y);
			}
		});

		labelClose = new JLabel(Resources.IMAGEICON_INTERNAL_VIEW_CLOSE);
		labelClose.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(final MouseEvent e)
			{
				labelClose.setIcon(Resources.IMAGEICON_INTERNAL_VIEW_CLOSE_MOUSEOVER);
			}

			@Override
			public void mouseExited(final MouseEvent e)
			{
				labelClose.setIcon(Resources.IMAGEICON_INTERNAL_VIEW_CLOSE);
			}

			@Override
			public void mouseClicked(final MouseEvent e)
			{
				listener.buttonClosePressed(InternalView.this);
			}

		});

		add(labelTitle, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 28 - (int) labelTitle.getPreferredSize().getHeight()));
		add(labelClose, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, NORTHEAST, NONE, new Insets(3, 0, 0, 3), 0, 0));
		add(panelInner, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 6, 6, 6), 0, 0));

		setSize(width + 14, height + 36);
	}

	// <- Abstract ->
	@Override
	public abstract void addListener(final BasicListener listener);

	// <- Object ->
	public void setFocus(final boolean inFocus)
	{
		if (inFocus)
			SwingUtilities.invokeLater(() -> {
				setBackground(new Color(26, 120, 200));
				setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(20, 90, 155)));
				panelInner.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(20, 90, 155)));

			});
		else
			SwingUtilities.invokeLater(() -> {
				setBackground(new Color(235, 235, 235));
				setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(218, 218, 218)));
				panelInner.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(218, 218, 218)));
			});
	}

	// <- Getter & Setter ->
	// <- Static ->
}
