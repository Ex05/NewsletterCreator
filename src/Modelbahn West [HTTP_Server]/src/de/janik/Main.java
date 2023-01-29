package de.janik;

import java.io.File;

// <- Import ->
import de.janik.west.httpServer.HTTP_Server;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Main
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final String	DIR;

	// <- Static ->
	static
	{
		DIR = System.getProperty("user.dir");
	}

	// <- Constructor ->
	private Main()
	{
		throw new IllegalStateException("Do not instanciate !~!");
	}

	// <- Abstract ->

	// <- Object ->
	// <- Getter & Setter ->

	// <- Static ->
	public static void main(final String[] args)
	{
		final HTTP_Server server = new HTTP_Server(DIR + File.separator + "server", 80, 10, 0, 100, 50_000_000);
		server.start();
	}
}
