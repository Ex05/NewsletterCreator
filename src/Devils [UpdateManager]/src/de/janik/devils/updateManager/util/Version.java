package de.janik.devils.updateManager.util;

import java.io.Serializable;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Version implements Comparable<Version>, Serializable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final int			version;
	private final int			release;
	private final int			update;

	// <- Static ->

	// <- Constructor ->
	public Version(final int version, final int release, final int update)
	{
		this.version = version;
		this.release = release;
		this.update = update;
	}

	// <- Abstract ->

	// <- Object ->
	@Override
	public String toString()
	{
		return String.format("%d.%d.%d", version, release, update);
	}

	// <- Getter & Setter ->
	public int getVersion()
	{
		return version;
	}

	public int getRelease()
	{
		return release;
	}

	public int getUpdate()
	{
		return update;
	}

	@Override
	public int compareTo(final Version o)
	{
		if (this.getVersion() < o.getVersion())
			return -1;

		if (this.getVersion() > o.getVersion())
			return 1;

		if (this.getVersion() == o.getVersion())
		{
			if (this.getRelease() < o.getRelease())
				return -1;

			if (this.getVersion() > o.getVersion())
				return 1;

			if (this.getVersion() == o.getVersion())
			{
				if (this.getUpdate() < o.getUpdate())
					return -1;

				if (this.getUpdate() > o.getUpdate())
					return 1;
			}
		}

		return 0;
	}
	// <- Static ->
}
