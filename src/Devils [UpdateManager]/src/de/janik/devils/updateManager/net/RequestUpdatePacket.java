package de.janik.devils.updateManager.net;

import de.janik.devils.updateManager.util.Application;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class RequestUpdatePacket extends Packet
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final Application	application;

	// <- Static ->

	// <- Constructor ->
	public RequestUpdatePacket(final Application application)
	{
		this.application = application;
	}

	// <- Abstract ->
	// <- Object ->

	// <- Getter & Setter ->
	public Application getApplication()
	{
		return application;
	}
	// <- Static ->
}
