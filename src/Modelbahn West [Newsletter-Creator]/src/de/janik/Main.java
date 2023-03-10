package de.janik;

import de.janik.util.launcher.Launcher;
import de.janik.west.NewsletterCreator;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class Main
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

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
		final NewsletterCreator creator = new NewsletterCreator();
		new Launcher(creator).launch();

		final Thread thread = Thread.currentThread();

		creator.onExit(() -> {
			synchronized (thread)
			{
				thread.notifyAll();
			}
		});

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
	}
}
