package de.janik.west.httpServer;

// <- Import ->
import java.util.HashMap;

// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public abstract class HTTP_RequestHeader
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private HashMap<String, String>	map;

	// <- Static ->

	// <- Constructor ->
	public HTTP_RequestHeader()
	{
		map = new HashMap<>();
	}

	// <- Abstract ->
	public void add(final String key, final Object value)
	{
		map.put(key, value.toString());
	}

	// <- Object ->
	@Override
	public String toString()
	{
		final StringBuilder b = new StringBuilder();

		map.keySet().forEach(key -> b.append(key + " " + map.get(key) + "\n"));

		return b.toString();
	}

	// <- Getter & Setter ->
	public String get(final String key)
	{
		final String value = map.get(key);

		return value == null ? "" : value;
	}

	// <- Static ->
	public static class GET extends HTTP_RequestHeader
	{
		public GET()
		{
			super();
		}
	}

	public static class HEAD extends HTTP_RequestHeader
	{
		public HEAD()
		{
			super();
		}
	}

	public static class POST extends HTTP_RequestHeader
	{
		public POST()
		{
			super();
		}
	}
}
