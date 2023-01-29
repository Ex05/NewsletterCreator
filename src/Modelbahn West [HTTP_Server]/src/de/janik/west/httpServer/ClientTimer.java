package de.janik.west.httpServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class ClientTimer
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final ClientTimer	Timer;

	private final List<Client>			clients;

	private final Thread				deamon;

	// <- Static ->
	static
	{
		Timer = new ClientTimer();
	}

	// <- Constructor ->
	private ClientTimer()
	{
		clients = Collections.synchronizedList(new ArrayList<>());

		deamon = new Thread(() -> {

			long deltaSleep = 0;

			while (true)
			{
				final long timeT1 = System.nanoTime();

				synchronized (clients)
				{
					for (int i = 0; i < clients.size(); i++)
						clients.get(i).tick();
				}
				synchronized (clients)
				{
					Iterator<Client> it = clients.iterator();
					while (it.hasNext())
					{
						final Client c = it.next();

						if (c.getTimer() == 0)
						{
							toBeClosed.add(c);
							it.remove();
						}
					}
				}
				toBeClosed.forEach(c -> c.closeConnection());
				toBeClosed.clear();

				try
				{
					final long deltaT = 1_000 - ((System.nanoTime() - timeT1) / 1_000_000);

					Thread.sleep((Math.max(Math.min(deltaT, 1_000) - deltaSleep, 0)));
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				deltaSleep = 1_000 - ((System.nanoTime() - timeT1) / 1_000_000);
			}

		}, "ClientConnectionTimer-Deamon");
		deamon.setDaemon(true);
		deamon.start();
	}

	// <- Abstract ->

	// <- Object ->
	public void add(final HTTP_Client client, final int timeOut)
	{
		clients.add(new Client(client, timeOut));
	}

	// <- Getter & Setter ->

	// <- Static ->
	public static ClientTimer GetInstance()
	{
		return Timer;
	}

	private final List<Client>	toBeClosed	= new ArrayList<>();

	private class Client
	{
		private final I_ClosableConnection	callback;

		private int							timer;

		private Client(final I_ClosableConnection callback, final int timer)
		{
			this.callback = callback;
			this.timer = timer;
		}

		private int getTimer()
		{
			return timer;
		}

		private void tick()
		{
			timer--;
		}

		private void closeConnection()
		{
			callback.closeConnection();
		}
	}
}
