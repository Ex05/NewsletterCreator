package de.janik.west.httpServer.cache;

import static de.janik.west.httpServer.HTTP_ContentType.IMAGE_GIF;
import static de.janik.west.httpServer.HTTP_ContentType.IMAGE_ICO;
import static de.janik.west.httpServer.HTTP_ContentType.IMAGE_JPEG;
import static de.janik.west.httpServer.HTTP_ContentType.IMAGE_PNG;
import static de.janik.west.httpServer.HTTP_ContentType.TEXT_HTML;
import static de.janik.west.httpServer.HTTP_ContentType.TEXT_PLAIN;

import java.io.File;

import de.janik.west.httpServer.HTTP_ContentType;
// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class CacheObject
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final File		file;

	private final long		initialTime;

	private final byte[]	data;

	private int				hits		= 0;

	private long			lastTime	= 0l;

	// <- Static ->

	// <- Constructor ->
	public CacheObject(final File file, final byte[] data)
	{
		this.file = file;
		this.initialTime = System.nanoTime();
		this.data = data;
	}

	// <- Abstract ->

	// <- Object ->
	public void hit()
	{
		hits++;
		lastTime = System.nanoTime();
	}

	public String getContentType()
	{
		HTTP_ContentType contentType = TEXT_PLAIN;

		switch (file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.')))
		{
			case ".html":
				contentType = TEXT_HTML;
				break;

			case ".txt":
				contentType = TEXT_PLAIN;
				break;

			case ".jpg":
				contentType = IMAGE_JPEG;
				break;

			case ".png":
				contentType = IMAGE_PNG;
				break;

			case ".gif":
				contentType = IMAGE_GIF;
				break;

			case ".ico":
				contentType = IMAGE_ICO;
				break;

			default:
				contentType = TEXT_PLAIN;
				break;
		}

		return contentType.getContentType();
	}

	// <- Getter & Setter ->
	public int getDataLength()
	{
		return data.length;
	}

	public int getHits()
	{
		return hits;
	}

	public long getLastHit()
	{
		return lastTime;
	}

	public void setLastHit(long lastHit)
	{
		this.lastTime = lastHit;
	}

	public File getFile()
	{
		return file;
	}

	public long getInitialTime()
	{
		return initialTime;
	}

	public byte[] getData()
	{
		return data;
	}

	// <- Static ->
}
