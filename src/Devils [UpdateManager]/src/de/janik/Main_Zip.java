package de.janik;

import java.io.File;

import de.janik.devils.updateManager.util.Zipper;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [©2014]
 *
 */
public final class Main_Zip
{
	// <- Public ->
	// <- Protected ->
	// <- Private->
	// <- Static ->

	// <- Constructor ->
	private Main_Zip()
	{
		throw new IllegalStateException("Do not instantiate !~!");
	}

	// <- Abstract ->
	// <- Object ->
	// <- Getter & Setter ->

	// <- Static ->
	public static void main(final String[] args)
	{
//		Zipper.GetInstance().zip(new File("0.0.0"), new File("0.0.0.zip"));
//
//		new File("0.0.0").delete();

		Zipper.GetInstance().unZip(new File("0.0.0.zip"));

		new File("0.0.0.zip").delete();
	}
}
