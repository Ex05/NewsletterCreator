package de.janik.managementServer;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class UpdateClientCountPaket extends Packet
{

	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1L;

	private final int			numConnectedClients;

	// <- Static ->

	// <- Constructor ->
	public UpdateClientCountPaket(final int numConnectedClients)
	{
		this.numConnectedClients = numConnectedClients;
	}

	// <- Abstract ->
	// <- Object ->

	// <- Getter & Setter ->
	public int getNumConnectedCLients()
	{
		return numConnectedClients;
	}

	// <- Static ->
}
