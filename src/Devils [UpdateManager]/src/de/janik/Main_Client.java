package de.janik;

import de.janik.devils.updateManager.client.UpdateManager;
import de.janik.devils.updateManager.util.Application;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Main_Client
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	private Main_Client()
	{
		throw new IllegalStateException("Do not instantiate !~!");
	}

	// <- Abstract ->
	// <- Object ->
	// <- Getter & Setter ->

	// <- Static ->
	public static void main(final String[] args)
	{
		final Application self = new Application("exit", 0, 0, 1);
		final UpdateManager manager = new UpdateManager(self, "29a.no-ip.org");

		manager.checkUpdates();
	}
}
