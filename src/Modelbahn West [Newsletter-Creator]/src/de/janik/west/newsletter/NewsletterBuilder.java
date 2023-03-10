package de.janik.west.newsletter;

import java.util.List;

import de.janik.west.htmlParser.HTML_Parser;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelFooter;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelHeader;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelImage;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelImageSmallWithTitle;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelImageWithTitle;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelText;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanelTextWithTitle;

// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class NewsletterBuilder
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final NewsletterBuilder	Builder;

	// <- Static ->
	static
	{
		Builder = new NewsletterBuilder();
	}

	// <- Constructor ->
	private NewsletterBuilder()
	{

	}

	// <- Abstract ->

	// <- Object ->
	public synchronized Newsletter parseToHTML(final List<DragEditorPanel> panel, final String imagePath, final HTML_Parser parser)
	{
		int imageID = 0;

		final Newsletter newsletter = new Newsletter();

		String html = parser.getSnippet("Newsletter");

		final StringBuilder b = new StringBuilder(panel.size());

		for (DragEditorPanel p : panel)
			if (p instanceof DragEditorPanelHeader)
			{
				String header = parser.getSnippet("Header");
				final String url = ((DragEditorPanelHeader) p).getURL();
				header = header.replace("<!--#URL #-->", (url.equals("") ? "#" : url));
				header = header.replace("<!--#Image #-->", "<img src=\"" + imagePath + "image_" + imageID + ".png\" alt=\"header\">");
				newsletter.addImage("image_" + imageID++ + ".png", ((DragEditorPanelHeader) p).getHeader());

				html = html.replace("<!--#Header#-->", header);
			}
			else
				if (p instanceof DragEditorPanelTextWithTitle)
				{
					String text = parser.getSnippet("Text + Title");

					b.append(text.replace("<!--#Title #-->", ((DragEditorPanelTextWithTitle) p).getTitle()));

					StringBuilder bb = new StringBuilder();

					String[] lines = ((DragEditorPanelTextWithTitle) p).getText().split("\n");

					b.append("<span style=\"font-size: 12pt;\">\n");
					b.append("\t" + lines[0] + "\n");
					b.append("</span>\n");

					for (int i = 1; i < lines.length; i++)
					{
						bb.append("<div>\n");
						bb.append("\t<span style=\"font-size: 12pt;\">\n");
						bb.append("\t\t" + lines[i] + "\n");
						bb.append("\t</span>\n");
						bb.append("</div>\n");
					}

					text = text.replace("<!--#Text_Line-->", bb.toString());

					b.append(text);
					b.append(parser.getSnippet("Divider"));
				}
				else
					if (p instanceof DragEditorPanelText)
					{
						String text = parser.getSnippet("Text");

						StringBuilder bb = new StringBuilder();

						String[] lines = ((DragEditorPanelText) p).getText().split("\n");

						b.append("<span style=\"font-size: 12pt;\">\n");
						b.append("\t" + lines[0] + "\n");
						b.append("</span>\n");

						for (int i = 1; i < lines.length; i++)
						{
							bb.append("<div>\n");
							bb.append("\t<span style=\"font-size: 12pt;\">\n");
							bb.append("\t\t" + lines[i] + "\n");
							bb.append("\t</span>\n");
							bb.append("</div>\n");
						}

						text = text.replace("<!--#Text_Line-->", bb.toString());

						b.append(text);
						b.append(parser.getSnippet("Divider"));
					}
					else
						if (p instanceof DragEditorPanelImageWithTitle)
						{
							String imageWithTitle = parser.getSnippet("Image + Title");
							imageWithTitle = imageWithTitle.replace("<!--#Title #-->", ((DragEditorPanelImageWithTitle) p).getTitle());

							final String url = ((DragEditorPanelImageWithTitle) p).getURL();
							imageWithTitle = imageWithTitle.replace("<!--#URL #-->", (url.equals("") ? "#" : url));
							imageWithTitle = imageWithTitle.replace("<!--#Image#-->", "<img src=\"" + imagePath + "image_" + imageID
									+ ".png\" align=\"left\" border=\"0\" alt=\"\" title=\"\" class=\"\" style=\"\" hspace=\"5\" vspace=\"5\" width=\"650\">");
							newsletter.addImage("image_" + imageID++ + ".png", ((DragEditorPanelImageWithTitle) p).getImage());

							b.append(imageWithTitle);
							b.append(parser.getSnippet("Divider"));
						}
						else
							if (p instanceof DragEditorPanelImage)
							{
								String image = parser.getSnippet("Image + Title");

								final String url = ((DragEditorPanelImage) p).getURL();
								image = image.replace("<!--#URL #-->", (url.equals("") ? "#" : url));

								image = image.replace("<!--#Image#-->", "<img src=\"" + imagePath + "image_" + imageID
										+ ".png\" align=\"left\" border=\"0\" alt=\"\" title=\"\" class=\"\" style=\"\" hspace=\"5\" vspace=\"5\" width=\"650\">");
								newsletter.addImage("image_" + imageID++ + ".png", ((DragEditorPanelImage) p).getImage());

								b.append(image);
								b.append(parser.getSnippet("Divider"));
							}
							else
								if (p instanceof DragEditorPanelImageSmallWithTitle)
								{
									String imageSmall = parser.getSnippet("Image(Small) + Title");

									final String url = ((DragEditorPanelImageSmallWithTitle) p).getURL();
									imageSmall = imageSmall.replaceAll("<!--#URL #-->", (url.equals("") ? "#" : url));

									imageSmall = imageSmall.replace("<!--#Title#-->", ((DragEditorPanelImageSmallWithTitle) p).getTitle());
									imageSmall = imageSmall.replace("<!--#Price_UVP#-->", ((DragEditorPanelImageSmallWithTitle) p).getPriceUVP());
									imageSmall = imageSmall.replace("<!--#Price_New#-->", ((DragEditorPanelImageSmallWithTitle) p).getPriceNew());
									imageSmall = imageSmall.replace("<!--#DeliveryTime#-->", ((DragEditorPanelImageSmallWithTitle) p).getDeliveryTime());
									imageSmall = imageSmall.replace("<!--#Image#-->", "<img src=\"" + imagePath + "image_" + imageID
											+ ".png\" align=\"\" border=\"0\" alt=\"\" title=\"\" class=\"\" style=\"\" hspace=\"5\" vspace=\"5\" width=\"250\">");

									newsletter.addImage("image_" + imageID++ + ".png", ((DragEditorPanelImageSmallWithTitle) p).getImage());

									b.append(imageSmall);
									b.append(parser.getSnippet("Divider"));
								}
								else
									if (p instanceof DragEditorPanelFooter)
										b.append(parser.getSnippet("Footer").replace("<!--#Header#-->", ((DragEditorPanelFooter) p).getFooter()));

		html = html.replace("<!--#Content#-->", b.toString());

		newsletter.setHTML(html);

		return newsletter;
	}

	// <- Getter & Setter ->

	// <- Static ->

	public static NewsletterBuilder GetInstance()
	{
		return Builder;
	}

}
