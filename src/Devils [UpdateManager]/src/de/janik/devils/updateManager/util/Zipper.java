package de.janik.devils.updateManager.util;

// <- Import ->
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Zipper
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static Zipper	Compressor;

	// <- Static ->

	// <- Constructor ->
	private Zipper()
	{
		// ((void*)0)
	}

	// <- Abstract ->

	// <- Object ->
	public void zip(final File root, final File zip)
	{
		ZipOutputStream zout = null;
		try
		{
			zout = new ZipOutputStream(new FileOutputStream(zip.getAbsoluteFile()));

			System.out.println("Creating: " + zip.getAbsolutePath());

			ZipAddDirectory(root, zout);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				zout.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private static void ZipAddDirectory(final File root, final ZipOutputStream zout) throws IOException
	{
		final File[] files = root.listFiles();
		byte[] buf = new byte[1024];

		for (File file : files)
			if (file.isDirectory())
				ZipAddDirectory(file, zout);
			else
			{
				final FileInputStream in = new FileInputStream(file.getPath());
				System.out.println(">> Adding: " + file.getPath());
				zout.putNextEntry(new ZipEntry(file.getPath()));

				int len;
				while ((len = in.read(buf)) > 0)
					zout.write(buf, 0, len);

				zout.closeEntry();
				in.close();
			}
	}

	public void unZip(final File file)
	{
		byte[] buffer = new byte[1024];

		try
		{
			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null)
			{
				String fileName = ze.getName();
				File newFile = new File(fileName).getAbsoluteFile();

				System.out.println("file unzip : " + newFile.getAbsoluteFile());

				if (ze.isDirectory())
				{
					newFile.mkdir();

					ze = zis.getNextEntry();
					continue;
				}

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0)
				{
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			System.out.println("Done");

		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	// <- Getter & Setter ->

	// <- Static ->
	public static Zipper GetInstance()
	{
		if (Compressor == null)
			Compressor = new Zipper();

		return Compressor;
	}
}
