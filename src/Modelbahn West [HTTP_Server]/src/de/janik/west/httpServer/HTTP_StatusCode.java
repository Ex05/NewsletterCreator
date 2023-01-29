package de.janik.west.httpServer;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public enum HTTP_StatusCode
{
	// <- Public ->
	_100_CONTINUE(100, "Continue"),
	_200_OK(200, "OK"),
	_401_UNAUTHORIZED(401, "Unauthorized"),
	_403_FORBIDDEN(403, "Forbidden"),
	_404_NOT_FOUND(402, "Not Found"),
	_501_NOT_IMPLEMENTED(501, "Not Implemented");

	// <- Protected ->

	// <- Private->
	private final int		statusCode;

	private final String	status;

	// <- Static ->

	// <- Constructor ->
	private HTTP_StatusCode(final int statusCode, final String status)
	{
		this.statusCode = statusCode;
		this.status = status;
	}

	// <- Abstract ->
	// <- Object ->

	// <- Getter & Setter ->
	public int getStatusCode()
	{
		return statusCode;
	}

	public String getStatus()
	{
		return status;
	}
	// <- Static ->
}
