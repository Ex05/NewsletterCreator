package de.janik;

import de.janik.devils.updateManager.updater.Updater;
import de.janik.devils.updateManager.util.Application;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Main_Updater
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	private Main_Updater()
	{
		throw new IllegalStateException("Do not instantiate !~!");
	}

	// <- Abstract ->
	// <- Object ->
	// <- Getter & Setter ->

	// <- Static ->
	public static void main(final String[] args)
	{
		if (args.length < 4)
			throw new IllegalArgumentException("ERROR: Not enough arguments !~!");
		else
		{
			final String applicationName = args[0];
			final int version = Integer.parseInt(args[1]);
			final int release = Integer.parseInt(args[2]);
			final int update = Integer.parseInt(args[3]);

			final Application application = new Application(applicationName, version, release, update);

			new Updater(application).update();
		}
		// final Application application = new Application("exit", 0, 0, 1);
		//
		// new Updater(application).update();
	}
}
