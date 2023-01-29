package de.janik.west.httpServer;

// <- Import ->
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.janik.managementServer.ManagementServer;
import de.janik.west.httpServer.cache.Cache;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class HTTP_Server implements Runnable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final File					path;

	private final int					clientConnectionTimeout;

	private final int					clientThreadStackSize;

	private final int					clientConnectionBackLog;

	private final int					port;

	private final long					serverCacheSize;

	private final ServerThreadFactory	threadFactory;

	private final ExecutorService		threadPool;

	private final ServerSocket			serverSocket;

	private Integer						connectedClients	= new Integer(0);

	private Thread						thread;

	private volatile boolean			running				= false;

	private final ManagementServer		managementServer;

	// <- Static ->

	// <- Constructor ->
	public HTTP_Server(final String path, final int port, final int clientConnectionTimeout, final int clientThreadStackSize, final int clientConnectionBackLog,
			final long serverCacheSize)
	{
		this.path = new File(path);
		this.port = port;
		this.clientConnectionTimeout = clientConnectionTimeout;
		this.clientThreadStackSize = clientThreadStackSize;
		this.clientConnectionBackLog = clientConnectionBackLog;
		this.serverCacheSize = serverCacheSize;

		threadFactory = new ServerThreadFactory("HTTP_ServerClients", this.clientThreadStackSize, "HTTP_ServerClient");
		threadPool = Executors.newCachedThreadPool(threadFactory);

		managementServer = new ManagementServer(this, 17_800);

		Cache.GetInstance().addManagementServer(managementServer);

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

		if (!this.path.exists())
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
	public synchronized void start()
	{
		System.out.println("Starting Server: [Path=" + path.getAbsolutePath() + ", Port=" + port + ", ConnectionTimeout=" + clientConnectionTimeout + ", ClientBackLog="
				+ clientConnectionBackLog + ", CachSize=" + (serverCacheSize / 1_000 / 1_000) + "MB]");

		if (running)
			return;

		running = true;
		thread = new Thread(this, "HTTP_Server");
		thread.start();
		managementServer.start();
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
		System.out.println("Server started !~!");

		while (running && !thread.isInterrupted())
		{
			try
			{
				final HTTP_Client client = accept();

				add();

				threadPool.execute(client);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private HTTP_Client accept() throws IOException
	{
		return new HTTP_Client(this, serverSocket.accept());
	}

	private void add()
	{
		synchronized (connectedClients)
		{
			connectedClients++;
		}

		managementServer.updateNumberConnectedClients(connectedClients);
	}

	public void checkOut()
	{
		synchronized (connectedClients)
		{
			connectedClients--;
		}

		managementServer.updateNumberConnectedClients(connectedClients);
	}

	// <- Getter & Setter ->
	public String getRootDiretory()
	{
		return path.getAbsolutePath();
	}

	public int getClientConnectionTImeOut()
	{
		return clientConnectionTimeout;
	}

	public int getNumConnectedClients()
	{
		return connectedClients < 0 ? 0 : connectedClients;
	}

	public String getServerTime()
	{
		return ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
	}

	// <- Static ->

}
