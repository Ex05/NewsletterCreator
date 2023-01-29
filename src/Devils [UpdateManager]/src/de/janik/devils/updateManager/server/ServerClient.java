package de.janik.devils.updateManager.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import de.janik.devils.updateManager.net.Packet;
import de.janik.devils.updateManager.net.RequestUpdatePacket;
import de.janik.devils.updateManager.net.RequestVersionPacket;
import de.janik.devils.updateManager.net.UpdatePacket;
import de.janik.devils.updateManager.net.VersionPacket;
import de.janik.devils.updateManager.util.Application;
import de.janik.devils.updateManager.util.Version;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class ServerClient implements Runnable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final UpdateServer			server;

	private final Socket				socket;

	private final ObjectInputStream		ois;
	private final ObjectOutputStream	oos;

	// <- Static ->

	// <- Constructor ->
	public ServerClient(final UpdateServer server, final Socket socket)
	{
		this.server = server;
		this.socket = socket;

		ois = ObjectInputStream(socket);
		oos = ObjectOutputStream(socket);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public void run()
	{
		final Packet p = receive();

		if (p instanceof RequestVersionPacket)
		{
			final RequestVersionPacket packet = (RequestVersionPacket) p;

			final Version latest = server.getLastestVersion(packet.getApplication().getName());

			if (latest == null)
				throw new RuntimeException("ERROR: No such application found !~!");

			send(new VersionPacket(new Application(packet.getApplication().getName(), latest)));

			closeConnection();
		}
		else
			if (p instanceof RequestUpdatePacket)
			{
				final RequestUpdatePacket packet = (RequestUpdatePacket) p;

				send(new UpdatePacket(server.getUpdate(packet.getApplication())));

				closeConnection();
			}
			else
				throw new RuntimeException("ERROR: Wrong Packet !~! " + p.getClass().getSimpleName());
	}

	private void closeConnection()
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

	private void send(final Packet p)
	{
		try
		{
			oos.writeObject(p);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private Packet receive()
	{
		Object o;
		try
		{
			o = ois.readObject();

			if (o instanceof Packet)
				return (Packet) o;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	// <- Getter & Setter ->

	// <- Static ->
	private static ObjectInputStream ObjectInputStream(final Socket socket)
	{
		ObjectInputStream ois = null;

		try
		{
			ois = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return ois;
	}

	private static ObjectOutputStream ObjectOutputStream(final Socket socket)
	{
		ObjectOutputStream ois = null;

		try
		{
			ois = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return ois;
	}
}
