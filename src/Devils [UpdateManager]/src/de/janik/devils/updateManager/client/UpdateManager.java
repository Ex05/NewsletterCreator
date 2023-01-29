package de.janik.devils.updateManager.client;

// <- Import ->
// <- Static_Import ->
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.WEST;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.janik.calc.view.AnimatedButton;
import de.janik.devils.process.ProcessLauncher;
import de.janik.devils.updateManager.util.Application;
import de.janik.devils.updateManager.util.Version;

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class UpdateManager
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final int		DEFAULT_PORT;

	private final LocalUpdateServer	updateServer;

	private final Application		application;

	private Version					latest;

	// <- Static ->
	static
	{
		DEFAULT_PORT = 4868;
	}

	// <- Constructor ->
	public UpdateManager(final Application application, final String inetAddress)
	{
		this.application = application;

		updateServer = new LocalUpdateServer(inetAddress, DEFAULT_PORT);
	}

	// <- Abstract ->

	// <- Object ->
	public void startUpdate(final Application application)
	{
		final File dir = new File(System.getProperty("user.dir")).getAbsoluteFile().getParentFile();
		final String[] commands = new String[] {
				System.getProperty("java.home") + File.separator + "bin" + File.separator + "java",
				"-jar",
				"updater.jar",
				application.getName(),
				application.getVersion().getVersion() + "",
				application.getVersion().getRelease() + "",
				application.getVersion().getUpdate() + ""
				};

		final ProcessLauncher launcher = new ProcessLauncher(dir.getAbsolutePath(), p -> {}, commands);
		launcher.start();
		
		System.exit(0);
	}

	public boolean checkUpdates()
	{
		latest = updateServer.checkUpdates(application);

		if (latest != null)
		{
			final int val = application.getVersion().compareTo(latest);

			if (val == -1)
				return true;
		}

		return false;
	}

	private static boolean	ret	= false;

	public boolean showOptionDialog(final Version version)
	{
		try
		{
			final Thread thread = Thread.currentThread();

			final BufferedImage img = ImageIO.read(new File("./res/img/updater/button.png"));
			final BufferedImage imgMouseOver = ImageIO.read(new File("./res/img/updater/buttonMouseOver.png"));

			final JFrame frame = new JFrame();
			frame.setType(Type.UTILITY);
			frame.setAlwaysOnTop(true);
			frame.setLayout(new GridBagLayout());
			frame.setTitle("Update available !~!");

			final JLabel label1 = new JLabel("<html>There is an update available for you software.</html>");
			label1.setFont(new Font("Verdana", Font.PLAIN, 14));
			label1.setForeground(Color.BLACK);

			final JLabel label2 = new JLabel("<html>Version: <b>" + getLatestVersion() + "</b></html>");
			label2.setFont(new Font("Verdana", Font.PLAIN, 14));
			label2.setForeground(Color.BLACK);

			final JLabel label3 = new JLabel("<html>Currently: <b>" + version + "</b></html>");
			label3.setFont(new Font("Verdana", Font.PLAIN, 14));
			label3.setForeground(Color.BLACK);

			final JLabel label4 = new JLabel("<html>Do you want to install it?</html>");
			label4.setFont(new Font("Verdana", Font.PLAIN, 14));
			label4.setForeground(Color.BLACK);

			final AnimatedButton buttonOk = new AnimatedButton("yes", img, imgMouseOver, 56, 28, 3, 3);
			buttonOk.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			buttonOk.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(final MouseEvent e)
				{
					ret = true;

					synchronized (thread)
					{
						thread.notifyAll();
					}
				}
			});

			final AnimatedButton buttonCancle = new AnimatedButton("no", img, imgMouseOver, 56, 28, 3, 3);
			buttonCancle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			buttonCancle.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(final MouseEvent e)
				{
					ret = false;

					synchronized (thread)
					{
						thread.notifyAll();
					}
				}
			});

			frame.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(final WindowEvent e)
				{
					ret = false;

					synchronized (thread)
					{
						thread.notifyAll();
					}
				}
			});

			frame.add(label1, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, CENTER, NONE, new Insets(0, 2, 5, 2), 0, 0));

			frame.add(label2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, EAST, NONE, new Insets(0, 0, 0, 5), 0, 0));
			frame.add(label3, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, WEST, NONE, new Insets(0, 5, 0, 0), 0, 0));

			frame.add(label4, new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0, CENTER, NONE, new Insets(5, 0, 0, 0), 0, 0));

			frame.add(buttonOk, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0, EAST, NONE, new Insets(10, 0, 5, 5), 0, 0));
			frame.add(buttonCancle, new GridBagConstraints(1, 3, 1, 1, 1.0, 1.0, WEST, NONE, new Insets(10, 5, 5, 0), 0, 0));

			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			synchronized (thread)
			{
				try
				{
					thread.wait();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			frame.dispose();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return ret;
	}

	// <- Getter & Setter ->
	public Version getLatestVersion()
	{
		return latest;
	}

	public LocalUpdateServer getUpdateServer()
	{
		return updateServer;
	}

	// <- Static ->
}
