package de.janik.managementServer;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class UpdateCachePacket extends Packet
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long						serialVersionUID	= 1L;

	private final CacheObjectInformationObject[]	cacheObjects;

	private final long								capacity;
	private final long								cacheSize;

	// <- Static ->

	// <- Constructor ->

	public UpdateCachePacket(final CacheObjectInformationObject[] cacheObjects, final long capacity, final long cacheSize)
	{
		this.cacheObjects = cacheObjects;
		this.capacity = capacity;
		this.cacheSize = cacheSize;
	}

	// <- Abstract ->
	// <- Object ->

	// <- Getter & Setter ->
	public CacheObjectInformationObject[] getCacheObjects()
	{
		return cacheObjects;
	}

	public long getCapacity()
	{
		return capacity;
	}

	public long getCacheSize()
	{
		return cacheSize;
	}

	// <- Static ->
}
