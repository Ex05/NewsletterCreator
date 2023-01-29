package de.janik.west.httpServer;

// <- Import ->
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class GZipCompressor
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static GZipCompressor	Compressor;

	// <- Static ->

	// <- Constructor ->
	private GZipCompressor()
	{

	}

	// <- Abstract ->

	// <- Object ->
	public byte[] compress(final byte[] b)
	{
		byte[] buffer = null;

		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream(b.length);
			GZIPOutputStream gzip = new GZIPOutputStream(baos, b.length);

			gzip.write(b);
			gzip.flush();
			gzip.close();

			buffer = baos.toByteArray();
			baos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return buffer;
	}

	// <- Getter & Setter ->

	// <- Static ->
	public static GZipCompressor GetInstance()
	{
		if (Compressor == null)
			Compressor = new GZipCompressor();

		return Compressor;
	}
}
