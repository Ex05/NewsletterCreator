package de.janik.calc.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public class AnimatedButton extends JLabel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private static final Font	FONT_DEFAULT;

	private final ButtonGraphic	button;
	private final ButtonGraphic	buttonMouseOver;

	private Thread				animator1;
	private Thread				animator2;

	private float				opacity				= 0.0f;
	// <- Static ->
	static
	{
		FONT_DEFAULT = new Font(Font.MONOSPACED, Font.BOLD, 26);
	}

	// <- Constructor ->
	public AnimatedButton(final String title, final BufferedImage img, final BufferedImage imgMouseOver, final int size, final int offset)
	{
		this(title, img, imgMouseOver, size, size, offset, offset);
	}

	public AnimatedButton(final String title, final BufferedImage img, final BufferedImage imgMouseOver, final int size, final int offset, final Color color)
	{
		this(title, img, imgMouseOver, size, size, offset, offset);

		setForeground(color);
	}

	public AnimatedButton(final String title, final BufferedImage img, final BufferedImage imgMouseOver, final int width, final int height, final int xOffset, final int yOffset)
	{
		super(title);

		button = new ButtonGraphic(img, xOffset, yOffset);
		buttonMouseOver = new ButtonGraphic(imgMouseOver, xOffset, yOffset);

		setPreferredSize(new Dimension(width, height));
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(FONT_DEFAULT);
		setForeground(Color.BLACK);

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(final MouseEvent e)
			{
				if (animator2 != null)
					animator2.interrupt();

				animator1 = new Thread(() -> {
					while (!animator1.isInterrupted() && opacity < 1.0f)
					{
						opacity += 0.5f;
						repaint();
						try
						{
							Thread.sleep(50);
						}
						catch (Exception exc)
						{
							animator1.interrupt();
						}
					}
					}, "Animator1");
				animator1.setDaemon(true);
				animator1.start();
			}

			@Override
			public void mouseExited(final MouseEvent e)
			{
				animator1.interrupt();

				animator2 = new Thread(() -> {
					while (!animator2.isInterrupted() && opacity > 0.0f)
					{
						opacity -= 0.065f;

						repaint();
						try
						{
							Thread.sleep(50);
						}
						catch (Exception exc)
						{
							animator2.interrupt();
						}
					}
					}, "Animator2");
				animator2.setDaemon(true);
				animator2.start();

				repaint();
			}
		});
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	protected void paintComponent(final Graphics graphics)
	{
		final Graphics2D g = (Graphics2D) graphics;

		button.paintComponent(g, getWidth(), getHeight());

		final Composite c = g.getComposite();

		opacity = (opacity <= 1.0f ? (opacity >= 0.0f ? opacity : 0.0f) : 1.0f);

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

		buttonMouseOver.paintComponent(g, getWidth(), getHeight());

		g.setComposite(c);

		g.translate(1, -2);
		super.paintComponent(g);
	}

	// <- Getter & Setter ->

	// <- Static ->
	private static class ButtonGraphic
	{
		private final int			xOffset, yOffset;

		private final BufferedImage	imgUpperLeft;
		private final BufferedImage	imgUpperRight;
		private final BufferedImage	imgLowerLeft;
		private final BufferedImage	imgLowerRight;

		private final BufferedImage	imgLeft;
		private final BufferedImage	imgRight;
		private final BufferedImage	imgTop;
		private final BufferedImage	imgBottom;

		private final BufferedImage	imgCenter;

		private ButtonGraphic(final BufferedImage img, final int xOffset, final int yOffset)
		{
			this.xOffset = xOffset;
			this.yOffset = yOffset;

			final int width = img.getWidth() - xOffset;
			final int height = img.getHeight() - yOffset;

			imgUpperLeft = img.getSubimage(0, 0, xOffset, yOffset);
			imgUpperRight = img.getSubimage(width, 0, xOffset, yOffset);
			imgLowerLeft = img.getSubimage(0, height, xOffset, yOffset);
			imgLowerRight = img.getSubimage(width, height, xOffset, yOffset);

			imgLeft = img.getSubimage(0, yOffset, xOffset, height - yOffset);
			imgRight = img.getSubimage(width, yOffset, xOffset, height - yOffset);
			imgTop = img.getSubimage(xOffset, 0, width - xOffset, yOffset);
			imgBottom = img.getSubimage(xOffset, height, width - xOffset, yOffset);

			imgCenter = img.getSubimage(xOffset, yOffset, width - xOffset, height - yOffset);
		}

		private void paintComponent(final Graphics graphics, int width, int height)
		{
			final Graphics2D g = (Graphics2D) graphics;

			width -= xOffset;
			height -= yOffset;

			g.drawImage(imgUpperLeft, 0, 0, null);
			g.drawImage(imgTop, xOffset, 0, width - xOffset, yOffset, null);
			g.drawImage(imgUpperRight, width, 0, null);
			g.drawImage(imgRight, width, yOffset, xOffset, height - yOffset, null);
			g.drawImage(imgLowerRight, width, height, null);
			g.drawImage(imgBottom, xOffset, height, width - xOffset, yOffset, null);
			g.drawImage(imgLowerLeft, 0, height, null);
			g.drawImage(imgLeft, 0, yOffset, xOffset, height - yOffset, null);

			g.drawImage(imgCenter, xOffset, yOffset, width - xOffset, height - yOffset, null);
		}
	}
}
