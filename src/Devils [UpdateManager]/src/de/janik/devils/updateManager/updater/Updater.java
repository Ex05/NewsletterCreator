package de.janik.devils.updateManager.updater;

import java.io.File;

import de.janik.devils.updateManager.client.LocalUpdateServer;
import de.janik.devils.updateManager.util.Application;
import de.janik.devils.updateManager.util.Zipper;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Updater
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final int		DEFAULT_PORT;

	private final Application		application;

	private final LocalUpdateServer	server;

	// <- Static ->
	static
	{
		DEFAULT_PORT = 4868;
	}

	// <- Constructor ->
	public Updater(final Application application)
	{
		this.application = application;
		server = new LocalUpdateServer("29a.no-ip.org", DEFAULT_PORT);
	}

	// <- Abstract ->

	// <- Object ->
	public void update()
	{
		final File zip = server.getUpdate(application);

		// System.out.println("Update Received: " + application);

		System.out.println(zip.getAbsolutePath());

		Zipper.GetInstance().unZip(zip.getAbsoluteFile());

		zip.delete();
	}
	// <- Getter & Setter ->
	// <- Static ->
}
