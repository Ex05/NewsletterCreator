package de.janik.west.httpServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
// <- Import ->
import java.net.Socket;

import de.janik.west.httpServer.cache.Cache;
import de.janik.west.httpServer.cache.CacheObject;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class HTTP_Client implements Runnable, I_ClosableConnection
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final HTTP_Server	server;

	private final Socket		socket;

	private volatile boolean	running	= true;

	// <- Static ->

	// <- Constructor ->
	public HTTP_Client(final HTTP_Server server, final Socket socket)
	{
		this.server = server;
		this.socket = socket;

		System.out.println("New Client: " + socket.getInetAddress() + " >> " + socket.getInetAddress().getHostName());
	}

	// <- Abstract ->

	// <- Object ->
	public void run()
	{
		ClientTimer.GetInstance().add(this, server.getClientConnectionTImeOut());

		while (running)
		{
			final HTTP_RequestHeader header = accept();

			if (header == null)
				break;

			final boolean closeConnection = header.get("Connection:").equals("close");

			if (header instanceof HTTP_RequestHeader.GET)
			{
				final File file = new File(retrievFile(header));

				if (header.get("Accept-Encoding:").contains("gzip"))
				{
					final CacheObject o = Cache.GetInstance().get(file);
					if (o != null)
						if (!sendCompressed(o, closeConnection))
							break;
						else
							; // TODO: Send ERROR_Page.
				}
				else
				{
					final CacheObject o = Cache.GetInstance().load(file, false);
					if (o != null)
						if (sendUncompressed(o, closeConnection))
							break;
						else
							; // TODO: Send ERROR_Page.
				}
			}
			else
				if (header instanceof HTTP_RequestHeader.HEAD)
				{
					System.out.println("HEAD-Request: ");
					System.out.print(header);
				}
				else
					if (header instanceof HTTP_RequestHeader.POST)
					{
						System.out.println("POST-Request: ");
						System.out.print(header);
					}

			if (closeConnection)
				break;
		}

		closeConnection();
	}

	private String retrievFile(final HTTP_RequestHeader header)
	{
		String file = header.get("GET").split(" ", 2)[0];

		if (file.endsWith("/") || file.equals("index") || file.equals(" ") || file.equals(""))
			file = "/index.html";

		return server.getRootDiretory() + file;
	}

	private boolean sendCompressed(final CacheObject o, final boolean closeConnection)
	{
		final HTTP_ResponseHeader header = new HTTP_ResponseHeader(HTTP_StatusCode._200_OK);
		header.add("Server:", "HTTP_Web-Server by Jan.Marcel.Janik <janmarcel2@web.de> [alpha v0.1]");
		header.add("Date:", server.getServerTime());

		if (closeConnection)
			header.add("Connection:", "close");

		header.add("Content-Encoding:", "gzip");

		final byte[] b = o.getData();

		header.add("Content-Type:", o.getContentType());
		header.add("Content-Length:", b.length);

		try
		{
			final OutputStream os = socket.getOutputStream();
			final PrintWriter out = new PrintWriter(os);

			out.print(header.construct());
			out.flush();

			os.write(b);

			os.write("\r\n".getBytes());
			os.flush();
		}
		catch (IOException e)
		{
			return false;
		}

		return true;
	}

	private boolean sendUncompressed(final CacheObject o, final boolean closeConnection)
	{
		final HTTP_ResponseHeader header = constructHeader(o, closeConnection);

		try
		{
			final OutputStream os = socket.getOutputStream();
			final PrintWriter out = new PrintWriter(os);

			out.print(header.construct());
			out.flush();

			final byte[] b = o.getData();

			os.write(b);

			os.write("\r\n".getBytes());
			os.flush();
		}
		catch (IOException e)
		{
			return false;
		}

		return true;
	}

	private HTTP_ResponseHeader constructHeader(final CacheObject o, final boolean closeRequested)
	{
		final HTTP_ResponseHeader header = new HTTP_ResponseHeader(HTTP_StatusCode._200_OK);
		header.add("Server:", "HTTP_Web-Server by Jan.Marcel.Janik <janmarcel2@web.de> [alpha v0.1]");
		header.add("Date:", server.getServerTime());

		if (closeRequested)
			header.add("Connection:", "close");

		header.add("Content-Type:", o.getContentType());
		header.add("Content-Length:", o.getData().length);

		return header;
	}

	private void stop()
	{
		running = false;
	}

	private HTTP_RequestHeader accept()
	{
		HTTP_RequestHeader header = null;

		try
		{
			final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String line = br.readLine();

			if (line == null)
				return header;

			switch (line.split(" ", 2)[0])
			{
				case "GET":
				{
					header = new HTTP_RequestHeader.GET();
					break;
				}
				case "HEAD":
				{
					header = new HTTP_RequestHeader.HEAD();
					break;
				}
				case "POST":
				{
					header = new HTTP_RequestHeader.POST();
					break;
				}
				default:
					return header;
			}

			do
			{ // Handle Connections that are not in the proper format;
				String[] lineSplitt = line.split(" ", 2);
				header.add(lineSplitt[0], lineSplitt[1]);
			}
			while ((line = br.readLine()) != null && !line.equals(""));

		}
		catch (IOException e)
		{
			// ERROR:
		}
		return header;
	}

	@Override
	public synchronized void closeConnection()
	{
		if (!running)
			return;

		try
		{
			socket.close();

			stop();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		server.checkOut();
	}
	// <- Getter & Setter ->

	// <- Static ->
}
