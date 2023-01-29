package de.janik.devils.updateManager.util;

import java.io.Serializable;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Application implements Versioned, Serializable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final String		name;

	private final Version		version;

	// <- Static ->

	// <- Constructor ->
	public Application(final String name, final int version, final int release, final int update)
	{
		this(name, new Version(version, release, update));
	}

	public Application(final String name, final Version version)
	{
		this.name = name;
		this.version = version;
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public String toString()
	{
		return "Application [name=" + name + ", version= '" + version + "']";
	}

	// <- Getter & Setter ->
	public String getName()
	{
		return name;
	}

	@Override
	public Version getVersion()
	{
		return version;
	}
	// <- Static ->
}
