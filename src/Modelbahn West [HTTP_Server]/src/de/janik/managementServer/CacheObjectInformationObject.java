package de.janik.managementServer;

import java.io.Serializable;

// <- Import ->
import de.janik.west.httpServer.cache.CacheObject;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class CacheObjectInformationObject implements Serializable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final String		path;

	private final long			hits;

	private final int			length;

	// <- Static ->

	// <- Constructor ->
	public CacheObjectInformationObject(final CacheObject o)
	{
		this.path = o.getFile().getAbsolutePath();
		this.hits = o.getHits();
		this.length = o.getDataLength();
	}

	// <- Abstract ->
	// <- Object ->

	// <- Getter & Setter ->
	public String getPath()
	{
		return path;
	}

	public long getHits()
	{
		return hits;
	}

	public int getLength()
	{
		return length;
	}

	// <- Static ->
}
