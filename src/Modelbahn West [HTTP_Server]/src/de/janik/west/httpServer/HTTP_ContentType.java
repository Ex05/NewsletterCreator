package de.janik.west.httpServer;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public enum HTTP_ContentType
{
	// <- Public ->
	IMAGE_JPEG("image/jpeg"),
	IMAGE_PNG("image/png"),
	IMAGE_GIF("image/gif"),
	TEXT_HTML("text/html"),
	TEXT_PLAIN("text/plain"),
	//IMAGE_ICO("image/vnd.microsoft.icon");
	 IMAGE_ICO("image/x-icon");
	// <- Protected ->

	// <- Private->

	private final String	contentType;

	// <- Static ->

	// <- Constructor ->
	private HTTP_ContentType(final String contentType)
	{
		this.contentType = contentType;
	}

	// <- Abstract ->
	// <- Object ->

	// <- Getter & Setter ->

	public String getContentType()
	{
		return contentType;
	}
	// <- Static ->
}

