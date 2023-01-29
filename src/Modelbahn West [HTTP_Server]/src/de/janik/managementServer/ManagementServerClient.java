package de.janik.managementServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// <- Import ->
import java.net.Socket;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class ManagementServerClient implements Runnable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final Socket		socket;

	private ObjectOutputStream	oos;
	private ObjectInputStream	ois;

	private volatile boolean	running	= true;

	// <- Static ->

	// <- Constructor ->
	public ManagementServerClient(final Socket socket)
	{
		this.socket = socket;

		try
		{
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void run()
	{

	}

	private synchronized void send(final Packet p)
	{
		try
		{
			oos.writeObject(p);
		}
		catch (IOException e)
		{
			
		}
	}

	public void initialize(final int numConnectedClients)
	{
		send(new UpdateAllPacket(numConnectedClients));
	}

	public void updateNumberConnectedClients(final int numConnectedClients)
	{
		send(new UpdateClientCountPaket(numConnectedClients));
	}

	public void updateCache(final CacheObjectInformationObject[] cacheObjects, final long capacity, final long cacheSize)
	{
		send(new UpdateCachePacket(cacheObjects, capacity, cacheSize));
	}

	// <- Getter & Setter ->
	// <- Static ->
}
