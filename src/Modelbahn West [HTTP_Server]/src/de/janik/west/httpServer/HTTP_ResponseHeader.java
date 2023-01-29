package de.janik.west.httpServer;

import java.util.LinkedList;
import java.util.List;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class HTTP_ResponseHeader
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final HTTP_StatusCode	statusCode;

	private final List<String>		responses;

	// <- Static ->

	// <- Constructor ->
	public HTTP_ResponseHeader(final HTTP_StatusCode statusCode)
	{
		this.statusCode = statusCode;
		responses = new LinkedList<>();
	}

	// <- Abstract ->

	// <- Object ->
	public void add(final String key, final Object value)
	{
		responses.add(key + " " + value.toString());
	}

	public String construct()
	{
		final StringBuilder b = new StringBuilder();

		b.append("HTTP/1.1 ");
		b.append(statusCode.getStatusCode());
		b.append(" ");
		b.append(statusCode.getStatus());
		b.append("\r\n");

		responses.forEach(e -> {
			b.append(e);
			b.append("\r\n");
		});

		b.append("\r\n");

		return b.toString();
	}

	// <- Getter & Setter ->
	public HTTP_StatusCode getStatusCode()
	{
		return statusCode;
	}

	// <- Static ->
}
