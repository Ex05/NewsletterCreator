package de.janik.managementServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
// <- Import ->
import java.net.Socket;
import java.net.UnknownHostException;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public abstract class ManagementClient implements Runnable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private Socket				socket;

	private ObjectInputStream	ois;

	@SuppressWarnings("unused")
	private ObjectOutputStream	oos;

	private Thread				thread;
	private volatile boolean	running	= false;

	// <- Static ->

	// <- Constructor ->
	public ManagementClient(final int port)
	{
		try
		{
			socket = new Socket(InetAddress.getByName("29a.no-ip.org"), port);

			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// <- Abstract ->
	public abstract void updateAll(final UpdateAllPacket p);

	public abstract void updateClientCount(final UpdateClientCountPaket p);

	public abstract void updateCachePacket(final UpdateCachePacket p);

	// <- Object ->
	public void run()
	{
		try
		{
			while (running)
			{
				final Packet p = receive();

				if (p instanceof UpdateAllPacket)
					updateAll((UpdateAllPacket) p);
				else
					if (p instanceof UpdateClientCountPaket)
						updateClientCount((UpdateClientCountPaket) p);
					else
						if (p instanceof UpdateCachePacket)
							updateCachePacket((UpdateCachePacket) p);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch (IOException e)
			{

				e.printStackTrace();
			}
		}
	}

	private Packet receive() throws ClassNotFoundException, IOException, IllegalStateException
	{
		Object o = ois.readObject();

		if (o instanceof Packet)
			return (Packet) o;

		throw new IllegalArgumentException("ERROR: Did not receive an Object of type 'Packet'.");
	}

	public void openConnection()
	{
		if (running)
			return;

		running = true;

		thread = new Thread(this, "ManagementClient");
		thread.start();
	}

	// <- Getter & Setter ->
	// <- Static ->
}
