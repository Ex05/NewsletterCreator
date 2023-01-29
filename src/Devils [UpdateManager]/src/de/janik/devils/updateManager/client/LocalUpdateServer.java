package de.janik.devils.updateManager.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import de.janik.devils.updateManager.net.Packet;
import de.janik.devils.updateManager.net.RequestVersionPacket;
import de.janik.devils.updateManager.net.RequestUpdatePacket;
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
public final class LocalUpdateServer
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final Socket				socket;

	private final ObjectInputStream		ois;
	private final ObjectOutputStream	oos;

	// <- Static ->

	// <- Constructor ->
	public LocalUpdateServer(final String inetAdress, final int port)
	{
		socket = Socket(inetAdress, port);

		try
		{
			socket.setSoTimeout(200);
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}

		oos = ObjectOutputStream(socket);
		ois = ObjectInputStream(socket);
	}

	// <- Abstract ->

	// <- Object ->
	public Version checkUpdates(final Application application)
	{
		send(new RequestVersionPacket(application));

		final Packet p = receive();

		if (p instanceof VersionPacket)
		{
			final VersionPacket packet = (VersionPacket) p;

			if (packet.getApplication().getName().equals(application.getName()))
				return packet.getApplication().getVersion();
			else
				throw new RuntimeException("ERROR: Non matching application namens !~!");
		}
		else
			throw new RuntimeException("ERROR: Wrong Packet !~!");
	}

	public File getUpdate(final Application application)
	{
		send(new RequestUpdatePacket(application));

		Packet p = receive();

		if (p instanceof UpdatePacket)
		{
			final UpdatePacket packet = (UpdatePacket) p;

			final Version version = application.getVersion();

			try
			{
				final File zip = new File("./" + version.getVersion() + "." + version.getRelease() + "." + version.getUpdate() + ".zip");
				final FileOutputStream fr = new FileOutputStream(zip);

				fr.write(packet.getBuffer());
				fr.close();

				return zip;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
			throw new RuntimeException("ERRORR: Non UpdatePacket received !~!");

		return null;
	}

	public void closeConnection()
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

	private static Socket Socket(final String inetAdress, final int port)
	{
		Socket socket = null;
		try
		{
			socket = new Socket(inetAdress, port);
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return socket;
	}

}
