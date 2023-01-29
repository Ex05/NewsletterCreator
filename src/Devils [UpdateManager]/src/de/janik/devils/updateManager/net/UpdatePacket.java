package de.janik.devils.updateManager.net;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class UpdatePacket extends Packet
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final byte[]		buffer;

	// <- Static ->

	// <- Constructor ->
	public UpdatePacket(final byte[] buffer)
	{
		this.buffer = buffer;
	}

	// <- Abstract ->
	// <- Object ->

	// <- Getter & Setter ->
	public byte[] getBuffer()
	{
		return buffer;
	}

	// <- Static ->
}
