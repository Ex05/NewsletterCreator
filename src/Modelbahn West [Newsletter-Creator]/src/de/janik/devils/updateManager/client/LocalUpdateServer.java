package de.janik.devils.updateManager.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
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
 * @author Jan.Marcel.Janik [�2014]
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

		return null;
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
		if (oos == null)
			return;

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
		if (socket == null)
			return null;

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
		if (socket == null)
			return null;

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
		if (socket == null)
			return null;

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

	private static boolean	available	= false;

	private static Socket Socket(final String inetAdress, final int port)
	{
		final Thread thread = Thread.currentThread();

		final Thread t1 = new Thread(() -> {
			if (!Thread.currentThread().isInterrupted())
				try
				{
					final Socket socket = new Socket();
					socket.setSoTimeout(100);
					socket.connect(new InetSocketAddress(inetAdress, port));
					socket.close();
				}
				catch (ConnectException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

			available = true;

			synchronized (thread)
			{
				thread.notify();
			}
		});
		t1.setDaemon(true);
		t1.start();

		@SuppressWarnings("deprecation")
		final Thread t2 = new Thread(() -> {
			try
			{
				Thread.sleep(400);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			t1.stop();

			if (!available)
				synchronized (thread)
				{
					thread.notify();
				}
		});
		t2.setDaemon(true);
		t2.start();

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

		Socket socket = null;
		if (available)
			try
			{
				socket = new Socket(inetAdress, port);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		return socket;
	}
}
