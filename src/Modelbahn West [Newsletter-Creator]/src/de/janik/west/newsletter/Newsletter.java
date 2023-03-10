package de.janik.west.newsletter;

// <- Import ->
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
// <- Static_Import ->

import de.janik.fileServer.FilePacket;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class Newsletter
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final HashMap<String, BufferedImage>	imageLocations;

	private String									newsletter;

	// <- Static ->

	// <- Constructor ->
	public Newsletter()
	{
		imageLocations = new HashMap<>();
	}

	// <- Abstract ->

	// <- Object ->
	public void addImage(final String relativePath, final BufferedImage img)
	{
		imageLocations.put(relativePath, img);
	}

	public String toString()
	{
		return newsletter;
	}

	public File saveTemp(final File tmp)
	{
		if (!tmp.exists())
			tmp.mkdirs();

		try
		{
			FileWriter fr = new FileWriter(new File(tmp.getAbsolutePath() + File.separator + "index.html"));
			fr.append(newsletter);
			fr.flush();
			fr.close();

			final Iterator<Map.Entry<String, BufferedImage>> it = imageLocations.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry<String, BufferedImage> entry = it.next();

				final BufferedImage img = entry.getValue();
				final String key = entry.getKey();

				final File image = new File(tmp.getAbsolutePath() + File.separator + key);
				image.createNewFile();

				ImageIO.write(img, "png", image);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return tmp;
	}

	public void upload(final File tmp, final String uploadDir)
	{
		try
		{
			final File index = new File(tmp.getAbsolutePath() + File.separator + "index.html");
			FileWriter fr = new FileWriter(index);

			fr.append(newsletter);
			fr.flush();
			fr.close();

			final byte[] b = new byte[(int) index.length()];
			final FileInputStream fs = new FileInputStream(index);
			fs.read(b);
			fs.close();

			final FilePacket p = new FilePacket(new File(uploadDir + File.separator + "index.html"), b);
			final Socket s = new Socket("29a.no-ip.org", 17174);
			final ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			os.writeObject(p);
			s.close();

			final Iterator<Map.Entry<String, BufferedImage>> it = imageLocations.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry<String, BufferedImage> entry = it.next();

				final String key = entry.getKey();

				final File image = new File(tmp.getAbsolutePath() + File.separator + key);
				image.createNewFile();

				// ImageIO.write(img, "png", image);
				final byte[] buffer = new byte[(int) image.length()];

				final FileInputStream fis = new FileInputStream(image);

				fis.read(buffer);
				fis.close();

				final FilePacket packet = new FilePacket(new File(uploadDir + File.separator + key), buffer);

				final Socket socket = new Socket("29a.no-ip.org", 17174);

				final ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(packet);
				socket.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// <- Getter & Setter ->
	public void setHTML(final String html)
	{
		this.newsletter = html;
	}

	public String getHTML_Source()
	{
		return newsletter;
	}

	// <- Static ->
}
