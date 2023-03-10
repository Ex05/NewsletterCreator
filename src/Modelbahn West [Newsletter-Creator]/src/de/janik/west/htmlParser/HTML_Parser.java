package de.janik.west.htmlParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class HTML_Parser
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private HashMap<String, String>	snippets;

	// <- Static ->

	// <- Constructor ->
	public HTML_Parser()
	{
		snippets = new HashMap<>();
	}

	// <- Abstract ->

	// <- Object ->
	private String load(final String file)
	{
		String s = "";
		String line = "";

		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./res/html/" + file))));

			while ((line = br.readLine()) != null)
				s += line + "\n";

			br.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return s;
	}

	public void loadSnippets()
	{
		snippets.put("Newsletter", load("newsletter.html"));
		snippets.put("Header", load("header.html"));
		snippets.put("Footer", load("footer.html"));
		snippets.put("Divider", load("divider.html"));
		snippets.put("Feedback", load("feedback.html"));
		snippets.put("Text", load("text.html"));
		snippets.put("Text + Title", load("textWithTitle.html"));
		snippets.put("Image", load("image.html"));
		snippets.put("Image + Title", load("imageWithTitle.html"));
		snippets.put("Image(Small) + Title", load("image(small)WithTitle.html"));
		snippets.put("Forward", load("forward.html"));
		snippets.put("Unsubscribe", load("unsubscribe.html"));
	}

	// <- Getter & Setter ->
	public String getSnippet(final String name)
	{
		return snippets.get(name);
	}

	// <- Static ->
}
