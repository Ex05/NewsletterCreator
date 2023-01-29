package de.janik;

import de.janik.devils.updateManager.server.UpdateServer;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Main_Server
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	private Main_Server()
	{
		throw new IllegalStateException("Do not instantiate !~!");
	}

	// <- Abstract ->
	// <- Object ->
	// <- Getter & Setter ->

	// <- Static ->
	public static void main(final String[] args)
	{
		final UpdateServer server = new UpdateServer(System.getProperty("user.dir"), 4868, 2, 100);
		server.start();
	}
}
