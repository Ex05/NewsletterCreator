package de.janik.fileServer;

import java.io.File;
import java.io.Serializable;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class FilePacket implements Serializable
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final File			file;

	private final byte[]		data;

	// <- Static ->

	// <- Constructor ->
	public FilePacket(final File file, final byte[] data)
	{
		this.file = file;
		this.data = data;
	}

	// <- Abstract ->
	// <- Object ->

	// <- Getter & Setter ->
	public File getFile()
	{
		return file;
	}

	public byte[] getData()
	{
		return data;
	}
	// <- Static ->
}
