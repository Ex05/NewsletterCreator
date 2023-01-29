package de.janik.managementServer;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class UpdateAllPacket extends Packet
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final int			numConnectedClients;

	// <- Static ->

	// <- Constructor ->
	public UpdateAllPacket(final int numConnectedClients)
	{
		this.numConnectedClients = numConnectedClients;
	}

	// <- Abstract ->

	// <- Object ->
	public int getNumConnectedClients()
	{
		return numConnectedClients;
	}

	// <- Getter & Setter ->
	// <- Static ->
}
