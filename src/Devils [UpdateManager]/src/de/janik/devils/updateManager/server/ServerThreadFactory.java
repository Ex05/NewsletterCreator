package de.janik.devils.updateManager.server;

import java.util.concurrent.ThreadFactory;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public class ServerThreadFactory implements ThreadFactory
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final ThreadGroup	group;

	private final String		prefix;

	private final int			stackSize;

	private int					counter;

	// <- Static ->

	// <- Constructor ->
	public ServerThreadFactory(final String groupName, final int stackSize, final String prefix)
	{
		group = new ThreadGroup(groupName);
		this.stackSize = stackSize < 0 ? 0 : stackSize;
		this.prefix = prefix;
		counter = 0;
	}

	public ServerThreadFactory(final String groupName, final String prefix)
	{
		this(groupName, 0, prefix);
	}

	public ServerThreadFactory(final String prefix)
	{
		this(prefix, 0, prefix);
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public Thread newThread(Runnable r)
	{
		return new Thread(group, r, prefix + "[" + counter++ + "]", stackSize);
	}
	// <- Getter & Setter ->
	// <- Static ->
}
