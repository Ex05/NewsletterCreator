package de.janik.devils.updateManager.server;

// <- Import ->
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.janik.devils.updateManager.util.Application;
import de.janik.devils.updateManager.util.Version;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class UpdateServer implements Runnable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final File					dir;

	private final int					clientThreadStackSize;

	private final int					clientConnectionBackLog;

	private final int					port;

	private final ServerThreadFactory	threadFactory;

	private final ExecutorService		threadPool;

	private final ServerSocket			serverSocket;

	private Thread						thread;

	private volatile boolean			running	= false;

	// <- Static ->

	// <- Constructor ->
	public UpdateServer(final String path, final int port, final int clientThreadStackSize, final int clientConnectionBackLog)
	{
		this.dir = new File(path);
		this.port = port;
		this.clientThreadStackSize = clientThreadStackSize;
		this.clientConnectionBackLog = clientConnectionBackLog;

		threadFactory = new ServerThreadFactory("ServerClients", this.clientThreadStackSize, "ServerClient");
		threadPool = Executors.newCachedThreadPool(threadFactory);

		ServerSocket serverSocket = null;

		try
		{
			serverSocket = new ServerSocket(port, clientConnectionBackLog);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		this.serverSocket = serverSocket;

		if (!this.dir.exists())
			try
			{
				throw new FileNotFoundException();
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();

				System.exit(1);
			}
	}

	// <- Abstract ->

	// <- Object ->
	public synchronized Version getLastestVersion(final String application)
	{
		final File updateLog = new File(dir.getAbsolutePath() + File.separator + application + File.separator + "updates.txt");

		if (updateLog.exists())
		{
			BufferedReader br = null;

			try
			{
				br = new BufferedReader(new FileReader(updateLog));

				final String[] data = br.readLine().trim().split("\\.");

				final int version = Integer.parseInt(data[0]);
				final int release = Integer.parseInt(data[1]);
				final int update = Integer.parseInt(data[2]);

				return new Version(version, release, update);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public synchronized void start()
	{
		System.out.println("Starting Server: [Path=" + dir.getAbsolutePath() + ", Port=" + port + ", ClientBackLog=" + clientConnectionBackLog + "]");

		if (running)
			return;

		running = true;
		thread = new Thread(this, "HTTP_Server");
		thread.start();
	}

	public synchronized void stop()
	{
		if (!running)
			return;

		running = false;

		thread.interrupt();
	}

	@Override
	public void run()
	{
		while (running && !thread.isInterrupted())
			try
			{
				final ServerClient client = accept();

				threadPool.execute(client);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
	}

	private ServerClient accept() throws IOException
	{
		return new ServerClient(this, serverSocket.accept());
	}

	// <- Getter & Setter ->
	public String getRootDiretory()
	{
		return dir.getAbsolutePath();
	}

	public String getServerTime()
	{
		return ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
	}

	public byte[] getUpdate(final Application application)
	{
		final Version version = application.getVersion();
		final File file = new File("./" + application.getName() + "/" + version.getVersion() + "." + version.getRelease() + "." + version.getUpdate() + ".zip");

		if (!file.exists())
			throw new RuntimeException("ERROR: No update found for " + application);
		else
		{
			try
			{
				final byte[] buf = new byte[(int) file.length()];

				final FileInputStream fis = new FileInputStream(file);

				final int length = fis.read(buf, 0, buf.length);

				fis.close();

				if (length != buf.length)
					throw new RuntimeException("ERROR: Failed to read all bytes !~!");

				return buf;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}
	// <- Static ->

}
