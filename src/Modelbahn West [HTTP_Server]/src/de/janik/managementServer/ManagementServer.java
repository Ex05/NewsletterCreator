package de.janik.managementServer;

// <- Import ->
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.janik.west.httpServer.HTTP_Server;
import de.janik.west.httpServer.cache.CacheObject;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class ManagementServer implements Runnable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final ExecutorService				threadPool;

	private final List<ManagementServerClient>	clients;

	private final HTTP_Server					server;

	private ServerSocket						socket;

	private Thread								thread;

	private volatile boolean					running	= false;

	// <- Static ->

	// <- Constructor ->
	public ManagementServer(final HTTP_Server server, final int port)
	{
		this.server = server;

		threadPool = Executors.newCachedThreadPool();

		clients = new ArrayList<>();

		try
		{
			socket = new ServerSocket(port);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// <- Abstract ->

	// <- Object ->
	public void updateNumberConnectedClients(final int numConnectedClients)
	{
		clients.forEach(c -> c.updateNumberConnectedClients(numConnectedClients));
	}

	public void run()
	{
		try
		{
			while (running)
			{
				final ManagementServerClient client = new ManagementServerClient(socket.accept());

				client.initialize(server.getNumConnectedClients());
				clients.add(client);

				threadPool.execute(client);
			}

			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public synchronized void start()
	{
		if (running)
			return;

		running = true;

		thread = new Thread(this, "ManagementServer");
		thread.start();

	}

	public void updateCache(final List<CacheObject> cache, final long capacity, final long cacheSize)
	{
		final CacheObjectInformationObject[] cacheObjects = new CacheObjectInformationObject[cache.size()];

		for (int i = 0; i < cacheObjects.length; i++)
			cacheObjects[i] = new CacheObjectInformationObject(cache.get(i));

		clients.forEach(c -> c.updateCache(cacheObjects, capacity, cacheSize));
	}

	// <- Getter & Setter ->
	// <- Static ->
}
