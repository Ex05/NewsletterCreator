package de.janik.west.httpServer.cache;

// <- Import ->
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.janik.managementServer.ManagementServer;
import de.janik.west.httpServer.GZipCompressor;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Cache
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static Cache					Cache;

	private final long						size;

	private final ArrayList<CacheObject>	cache;

	private ManagementServer				managementServer;

	private long							capacity;

	// <- Static ->

	// <- Constructor ->
	public Cache(final long size)
	{
		this.size = size;
		cache = new ArrayList<>();
		capacity = size;
	}

	// <- Abstract ->

	// <- Object ->
	public synchronized CacheObject get(final File file)
	{
		final CacheObject object = cache.stream().filter(o -> o.getFile().equals(file)).findFirst().orElse(null);

		if (object == null)
			return load(file, true);
		else
			if (object.getData().length != file.length())
				return load(file, true);
			else
				return object;
	}

	public synchronized CacheObject load(final File file, final boolean compressed)
	{
		if (file.exists())
		{
			final long length = file.length();

			final byte[] b = new byte[(int) length];

			try
			{
				InputStream is = new FileInputStream(file);
				int i = is.read(b);
				is.close();

				if (i < length)
					throw new RuntimeException("Failed to load file: " + file.getAbsolutePath() + " file-size exceeds " + (Integer.MAX_VALUE - 5) + "bytes.");

				if (compressed)
				{
					final byte[] compressedFile = GZipCompressor.GetInstance().compress(b);

					final CacheObject o = new CacheObject(file, compressedFile);

					if (getCapacity() < compressedFile.length)
						free(compressedFile.length);

					capacity -= compressedFile.length;
					cache.add(o);

					managementServer.updateCache(cache, getCapacity(), getSize());

					return o;
				}
				else
					return new CacheObject(file, b);

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	private synchronized void free(final long memory)
	{
		// TODO: Intelligent memory freeing aldada.
		Iterator<CacheObject> it = cache.iterator();

		List<CacheObject> toRemove = new LinkedList<>();

		while (it.hasNext())
		{
			final CacheObject o = it.next();

			capacity += o.getData().length;
			toRemove.add(o);

			if (capacity > memory)
				break;
		}

		cache.removeAll(toRemove);
	}

	// <- Getter & Setter ->
	public long getCapacity()
	{
		return capacity;
	}

	public long getSize()
	{
		return size;
	}

	// <- Static ->
	public static Cache GetInstance()
	{
		if (Cache == null)
			Cache = new Cache((1_000 * 1_000) * 50);

		return Cache;
	}

	public void addManagementServer(final ManagementServer managementServer)
	{
		this.managementServer = managementServer;
	}
}
