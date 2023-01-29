package de.janik.west.view.panel;

import static java.awt.GridBagConstraints.BOTH;
// <- Import ->
// <- Static_Import ->
import static java.awt.GridBagConstraints.CENTER;
import static javax.swing.JTabbedPane.WRAP_TAB_LAYOUT;
import static javax.swing.SwingConstants.TOP;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

import de.janik.west.listener.BasicListener;
import de.janik.west.listener.editorChangeListener.EditorChangeListener;
import de.janik.west.listener.webviewURL_Listener.WebViewURL_SearchListener;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.panel.editor.DragEditor;
import de.janik.west.view.panel.editor.Editor;
import de.janik.west.view.panel.editor.HTML_Editor;
// import de.janik.west.view.panel.editor.WebView;

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class CenterPanel extends ViewPanel
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private static final long	serialVersionUID	= 1l;

	private final JTabbedPane	tabbedPane;

	private final DragEditor	dragEditor;

	// private final WebView		webView;

	private final HTML_Editor	htmlEditor;

	// <- Static ->

	// <- Constructor ->
	public CenterPanel()
	{
		super();

		tabbedPane = new JTabbedPane(TOP, WRAP_TAB_LAYOUT);

		dragEditor = new DragEditor();
		// webView = new WebView();
		htmlEditor = new HTML_Editor();

		tabbedPane.addTab("Drag-Editor", null, dragEditor, "Let's you edit your newsletter.:");
		// tabbedPane.addTab("Web-View", null, webView, "See what your newsletter will look like:");
		tabbedPane.addTab("HTML-Editor", null, htmlEditor, "Have a look at your newsletter's HTML-Source-Code:");
		tabbedPane.addTab("CSS-Editor", null, new Editor()
		{
			private static final long	serialVersionUID	= 1L;

			@Override
			public void addListener(BasicListener listener)
			{
				// TODO Auto-generated method stub
			}
		}, "Tipp:");

		add(tabbedPane, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(0, 0, 0, 0), 0, 0));
	}

	// <- Abstract ->

	// <- Object ->
	public void openURL(final String url)
	{
		// webView.openURL(url);
	}

	@Override
	public void addListener(final BasicListener listener)
	{
		if (listener instanceof WebViewURL_SearchListener)
		{
			
		}
			// webView.addListener(listener);
		else
			if (listener instanceof EditorChangeListener)
			{
				tabbedPane.addChangeListener((EditorChangeListener) listener);
				((EditorChangeListener) listener).stateChanged(new ChangeEvent(tabbedPane));
			}
	}

	// <- Getter & Setter ->
	public HTML_Editor getHtml_Editor()
	{
		return htmlEditor;
	}

	public void setDragEditorPanel(final List<DragEditorPanel> dragEditorPanel, final boolean scrol)
	{
		dragEditor.setDragEditorPanel(dragEditorPanel, scrol);
	}

	// <- Static ->
}
