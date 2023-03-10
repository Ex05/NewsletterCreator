package de.janik.west;

import static de.janik.util.database.Command.INSERT;
import static de.janik.util.database.Command.SELECT;
import static de.janik.west.util.Resources.DATABASE_WEST;
import static de.janik.west.util.Resources.DB_USER_READONLY;
import static de.janik.west.util.Resources.IMAGEICON_COPY;
import static de.janik.west.util.Resources.IMAGEICON_CUT;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_BUY_NOW;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_CLOSE;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_CLOSE_MOUSEOVER;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_CROSS;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_CROSS_MOUSEOVER;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_DOWN;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_DOWN_MOUSEOVER;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_HEADER;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_NO_IMAGE;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_NO_IMAGE_SMALL;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_UP;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_EDITOR_UP_MOUSEOVER;
import static de.janik.west.util.Resources.IMAGEICON_DRAG_N_NDROP;
import static de.janik.west.util.Resources.IMAGEICON_INTERNAL_VIEW_CLOSE;
import static de.janik.west.util.Resources.IMAGEICON_INTERNAL_VIEW_CLOSE_MOUSEOVER;
import static de.janik.west.util.Resources.IMAGEICON_NEW_PROJECT;
import static de.janik.west.util.Resources.IMAGEICON_OPEN_EXTERN;
import static de.janik.west.util.Resources.IMAGEICON_PASTE;
import static de.janik.west.util.Resources.IMAGEICON_PREVIEW_FOOTER;
import static de.janik.west.util.Resources.IMAGEICON_PREVIEW_HEADER;
import static de.janik.west.util.Resources.IMAGEICON_PREVIEW_IMAGE;
import static de.janik.west.util.Resources.IMAGEICON_PREVIEW_IMAGE_SMALL_WITH_TITLE;
import static de.janik.west.util.Resources.IMAGEICON_PREVIEW_IMAGE_WITH_TITLE;
import static de.janik.west.util.Resources.IMAGEICON_PREVIEW_TEXT;
import static de.janik.west.util.Resources.IMAGEICON_PREVIEW_TEXT_WITH_TITLE;
import static de.janik.west.util.Resources.IMAGEICON_PRINT;
import static de.janik.west.util.Resources.IMAGEICON_REDO;
import static de.janik.west.util.Resources.IMAGEICON_RELOAD;
import static de.janik.west.util.Resources.IMAGEICON_SAVE;
import static de.janik.west.util.Resources.IMAGEICON_SAVE_ALL;
import static de.janik.west.util.Resources.IMAGEICON_SENDMAIL;
import static de.janik.west.util.Resources.IMAGEICON_UNDO;
import static de.janik.west.util.Resources.IMAGE_LAUNCHER_LOGO;
import static de.janik.west.util.Resources.IMAGE_VIEW_ICON;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import de.janik.devils.updateManager.client.UpdateManager;
import de.janik.devils.updateManager.util.Application;
import de.janik.devils.updateManager.util.Version;
import de.janik.util.imageUtil.ImageLoader;
import de.janik.util.launcher.I_LaunchableComponent;
import de.janik.util.launcher.LaunchEvent;
import de.janik.west.htmlParser.HTML_Parser;
import de.janik.west.listener.colorselectionListener.ColorSelectionListener;
import de.janik.west.listener.dragObjectPanelListener.DragObjectPanelListener;
import de.janik.west.listener.dragObjectSearchListener.DragObjectSearchListener;
import de.janik.west.listener.editorChangeListener.EditorChangeListener;
import de.janik.west.listener.internalView.sendMailView.SendMailViewListener;
import de.janik.west.listener.menueListener.FileMenueListener;
import de.janik.west.listener.menueListener.OptionsMenueListener;
import de.janik.west.listener.menueListener.ProjectMenueListener;
import de.janik.west.listener.toolbarListener.ProjectToolbarListener;
import de.janik.west.listener.toolbarListener.ReloadAndOpenToolbarListener;
import de.janik.west.listener.toolbarListener.SendMailToolbarListener;
import de.janik.west.listener.webviewURL_Listener.WebViewURL_SearchListener;
import de.janik.west.mail.JavaMail;
import de.janik.west.model.Model;
import de.janik.west.newsletter.Newsletter;
import de.janik.west.newsletter.NewsletterBuilder;
import de.janik.west.view.InternalView;
import de.janik.west.view.View;
import de.janik.west.view.dragObject.DragObject;
import de.janik.west.view.dragObject.DragObjectFooter;
import de.janik.west.view.dragObject.DragObjectHeader;
import de.janik.west.view.dragObject.DragObjectImage;
import de.janik.west.view.dragObject.DragObjectImageSmallWithTitle;
import de.janik.west.view.dragObject.DragObjectImageWithTitle;
import de.janik.west.view.dragObject.DragObjectText;
import de.janik.west.view.dragObject.DragObjectTextWithTitle;
import de.janik.west.view.dragObject.editorPanel.DragEditorPanel;
import de.janik.west.view.internalView.SendMailView;
import de.janik.west.view.panel.editor.Editor;
// import de.janik.west.view.panel.editor.WebView;
// <- Import ->
// <- Static_Import ->

/**
 * @author Jan.Marcel.Janik [�2014]
 *
 */
public final class NewsletterCreator implements I_LaunchableComponent
{
	// <- Public ->
	// <- Protected ->

	// <- Private->
	private final Model							model;

	// <InternalViews>
	private SendMailView						sendMailView;

	// <Listener>
	private final FileMenueListener				listenerFileMenue;
	private final OptionsMenueListener			listenerOptionsMenue;
	private final ProjectMenueListener			listenerProjectMenue;
	private final ProjectToolbarListener		listenerProjectToolbar;
	private final SendMailToolbarListener		listenerSendMailToolbar;
	private final ColorSelectionListener		listenerColorSelection;
	private final DragObjectSearchListener		listenerSearchDragObjetcs;
	private final WebViewURL_SearchListener		listenerURL_Search;
	private final DragObjectPanelListener		listenerDragObjectPanel;
	private final EditorChangeListener			listenerEditorChange;
	private final SendMailViewListener			listenerSendMailView;
	private final ReloadAndOpenToolbarListener	listenerReloadAndOpenToolbar;

	private final HTML_Parser					htmlParser;

	private final List<CloseListener>			listener;
	private View								view;

	// <- Static ->
	static
	{
		IMAGE_LAUNCHER_LOGO = ImageLoader.GetInstance().setInputFile("./res/img/launcher/west.png").load().resize(425, 60).getImage();
	}

	// <- Constructor ->
	public NewsletterCreator()
	{
		model = new Model();

		listener = new ArrayList<>(1);

		listenerFileMenue = new FileMenueListener(this);
		listenerOptionsMenue = new OptionsMenueListener(this);
		listenerProjectMenue = new ProjectMenueListener(this);
		listenerProjectToolbar = new ProjectToolbarListener(this);
		listenerSendMailToolbar = new SendMailToolbarListener(this);
		listenerColorSelection = new ColorSelectionListener(this);
		listenerSearchDragObjetcs = new DragObjectSearchListener(this);
		listenerURL_Search = new WebViewURL_SearchListener(this);
		listenerDragObjectPanel = new DragObjectPanelListener(this);
		listenerEditorChange = new EditorChangeListener(this);
		listenerSendMailView = new SendMailViewListener(this);
		listenerReloadAndOpenToolbar = new ReloadAndOpenToolbarListener(this);

		htmlParser = new HTML_Parser();
	}

	// <- Abstract ->

	// <- Object ->
	public void buttonSavePressed()
	{
		final Newsletter newsletter = NewsletterBuilder.GetInstance().parseToHTML(model.getDragEditorPanel(), "./", htmlParser);

		final File tmp = newsletter.saveTemp(new File(System.getProperty("user.dir") + File.separator + "/save").getAbsoluteFile());

		model.setLastSavedTMP_Directory(tmp.getAbsoluteFile());

		setButtonOpenExternEnabled(true);
		setButtonReloadEnabled(true);

		view.openURL(new File(model.getLastSavedTMP_Directory().getAbsolutePath() + File.separator + "index.html").toURI().toString());

		view.getWorkspace().getCenterPanel().getHtml_Editor().setText(newsletter.getHTML_Source());

		setButtonSendMailEnabled(true);
	}

	public void buttonReloadPressed()
	{
		buttonSavePressed();
	}

	public void buttonOpenExternPressed()
	{
		try
		{
			final File file = new File(model.getLastSavedTMP_Directory().getAbsolutePath() + File.separator + "index.html");

			Desktop.getDesktop().browse(file.toURI());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void onSendMailViewButtonAddPressed(final String mail)
	{
		model.addTestMailRecipient(mail);

		sendMailView.setButtonSendTextMailEnabled(true);
	}

	public void removeTestMailRecipient(final String mail)
	{
		model.removeTestMailRecipient(mail);

		if (model.getTestMailRecipients().size() <= 0)
			sendMailView.setButtonSendTextMailEnabled(false);
	}

	public void hideSendMailView()
	{
		view.hide(sendMailView);
	}

	public void showSendMailView()
	{
		if (sendMailView == null)
			sendMailView = new SendMailView(listenerSendMailView);

		if (!sendMailView.isVisible())
			view.show(sendMailView);
	}

	public void showPopUp(final InternalView popUp)
	{
		if (!popUp.isVisible())
			view.show(popUp);
	}

	public void hidePopUp(final InternalView popUp)
	{
		view.hide(popUp);
		view.setDragEditorPanel(model.getDragEditorPanel(), false);
	}

	public void dragObjectClicked(final DragObject o)
	{
		if (model.isDragEditorSelected())
			addDragEditorPanel(o.getDragEditorPanel());
	}

	public void dispose()
	{
		view.setVisible(false);

		view.dispose();

		listener.forEach(l -> l.onCLose());
	}

	public void onExit(final CloseListener listener)
	{
		this.listener.add(listener);
	}

	public void onURL_SearchListenerButtonPressed(final String url)
	{
		if (url.startsWith("https://") || url.startsWith("http://"))
			view.openURL(url);
		else
			view.openURL("https://www.google.de/search?q=" + url + "&oq=" + url);
	}

	@Override
	public void launch()
	{
		view = new View(1435, 900);

		view.setMinimumSize(900, 700).center()
		// .setSize(1435, 700)
		;
		view.registerCloseEvent(() -> dispose());

		view.addListener(listenerFileMenue);
		view.addListener(listenerOptionsMenue);
		view.addListener(listenerProjectMenue);
		view.addListener(listenerProjectToolbar);
		view.addListener(listenerSendMailToolbar);
		view.addListener(listenerColorSelection);
		view.addListener(listenerSearchDragObjetcs);
		view.addListener(listenerURL_Search);
		view.addListener(listenerEditorChange);
		view.addListener(listenerReloadAndOpenToolbar);

		view.setIcon(IMAGE_VIEW_ICON);

		// setButtonSaveEnabled(false);
		setButtonSaveAllEnabled(false);
		setButtonPrintEnabled(false);

		setButtonUndoEnabled(false);
		setButtonRedoEnabled(false);

		setButtonCutEnalbed(false);
		setButtonCopyEnalbed(false);
		setButtonPasteEnalbed(false);

		setButtonReloadEnabled(false);
		setButtonOpenExternEnabled(false);

		setButtonSendMailEnabled(false);

		List<DragObject> dragObjects = new ArrayList<>(2);
		dragObjects.add(new DragObjectHeader(listenerDragObjectPanel));
		dragObjects.add(new DragObjectTextWithTitle(listenerDragObjectPanel));
		dragObjects.add(new DragObjectText(listenerDragObjectPanel));
		dragObjects.add(new DragObjectImageWithTitle(listenerDragObjectPanel));
		dragObjects.add(new DragObjectImageSmallWithTitle(listenerDragObjectPanel));
		dragObjects.add(new DragObjectImage(listenerDragObjectPanel));
		dragObjects.add(new DragObjectFooter(listenerDragObjectPanel));

		model.setDragObjects(dragObjects);

		setVisibleDragObjects(model.getDragObjects());

		view.setVisible(true);
		view.requestFocus();
	}

	public void moveDragEditorPanelUp(final DragEditorPanel panel)
	{
		int index = model.getDragEditorPanel().indexOf(panel) - 1;

		model.removeDragEditorPanel(panel);
		model.addDragEditorPanel(index < 0 ? 0 : index, panel);

		view.setDragEditorPanel(model.getDragEditorPanel(), false);
	}

	public void moveDragEditorPanelDown(final DragEditorPanel panel)
	{
		int index = model.getDragEditorPanel().indexOf(panel) + 1;
		int lastIndex = model.getDragEditorPanel().size() - 1;

		model.removeDragEditorPanel(panel);
		model.addDragEditorPanel(index >= lastIndex ? lastIndex : index, panel);

		view.setDragEditorPanel(model.getDragEditorPanel(), false);
	}

	public void addDragEditorPanel(final DragEditorPanel panel)
	{
		model.addDragEditorPanel(panel);
		view.setDragEditorPanel(model.getDragEditorPanel(), true);
	}

	public void addDragEditorPanel(final int index, final DragEditorPanel panel)
	{
		model.addDragEditorPanel(index, panel);
		view.setDragEditorPanel(model.getDragEditorPanel(), true);
	}

	public void removeDragEditorPanel(final int index)
	{
		model.removeDragEditorPanel(index);
		view.setDragEditorPanel(model.getDragEditorPanel(), false);
	}

	public void removeDragEditorPanel(final DragEditorPanel panel)
	{
		model.removeDragEditorPanel(panel);
		view.setDragEditorPanel(model.getDragEditorPanel(), false);
	}

	public void setVisibleDragObjects(final List<DragObject> dragObjects)
	{
		view.setVisibleDragObjects(dragObjects);
	}

	public void buttonNewProjectPressed()
	{
		// TODO:
	}

	public void buttonSendMailPressed()
	{
		showSendMailView();
	}

	public void sendTestMail()
	{
		final List<String> recipients = model.getTestMailRecipients();

		if (recipients.size() <= 0)
			JOptionPane.showMessageDialog(null, "You have to add atleast 1 recipient, in order to send a test-mail !~!", "No recipeints specified.", JOptionPane.WARNING_MESSAGE);
		else
		{
			// new Thread(() -> {
			final Newsletter newsletter = NewsletterBuilder.GetInstance().parseToHTML(model.getDragEditorPanel(), "http://29a.no-ip.org/newsletter/", htmlParser);
			newsletter.upload(new File(System.getProperty("user.dir") + File.separator + "save"), "/newsletter");

			final JavaMail mail = new JavaMail(newsletter.getHTML_Source().replaceAll("\n", "").replaceAll("\t", ""), "Test_Mail", model.getTestMailRecipients().toArray(
					new String[model.getTestMailRecipients().size()]));
			mail.sendMail();

			hideSendMailView();
			// }).start();
		}
	}

	public void onDragObjectSearchBarButtonPressed()
	{
		view.setVisibleDragObjects(model.getDragObjects());
		view.clearDragObjectSearchBar();
	}

	public void onDragObjectSearchBarInput(final String input)
	{
		if (input.equals(new String()))
			view.setVisibleDragObjects(model.getDragObjects());
		else
			view.setVisibleDragObjects(model.getDragObjects().stream().filter(o -> o.getTitle().toLowerCase().contains(input.toLowerCase())).collect(Collectors.toList()));
	}

	// <- Getter & Setter ->
	public void setSelectedColor(final Color color)
	{
		model.setSelectedColor(color);
		view.setSelectedColor(getSelectedColor());
	}

	public Color getSelectedColor()
	{
		return model.getSelectedColor();
	}

	@Override
	public List<LaunchEvent> getLaunchActions()
	{
		ImageLoader loader = ImageLoader.GetInstance();

		List<LaunchEvent> launchEvents = new LinkedList<>();

		launchEvents.add(new LaunchEvent(() -> {
			final UpdateManager updateManager = new UpdateManager(new Application("Modelbahn West [NewsLetter Creator]", new Version(0, 0, 0)), "29a.no-ip.org");
			if (updateManager.checkUpdates())
				if (updateManager.showOptionDialog(new Version(0, 0, 0)))
					updateManager.startUpdate(new Application("Modelbahn West [NewsLetter Creator]", updateManager.getLatestVersion()));

		}, "Checking for Updates...", 2));

		launchEvents.add(new LaunchEvent(() -> IMAGE_VIEW_ICON = loader.setInputFile("./res/img/icon_256.png").load().getImage(), "Loading: Image[res/img/icon_256.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_NEW_PROJECT = loader.setInputFile("./res/img/toolbar/newProject.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/newProject.png", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_SAVE = loader.setInputFile("./res/img/toolbar/save.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/save.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_SAVE_ALL = loader.setInputFile("./res/img/toolbar/saveAll.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/saveAll.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_PRINT = loader.setInputFile("./res/img/toolbar/print.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/print.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_UNDO = loader.setInputFile("./res/img/toolbar/undo.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/undo.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_REDO = loader.setInputFile("./res/img/toolbar/redo.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/redo.png]", 1));
		launchEvents
				.add(new LaunchEvent(() -> IMAGEICON_CUT = loader.setInputFile("./res/img/toolbar/cut.png").load().getImageIcon(), "Loading: Image[res/img/toolbar/cut.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_COPY = loader.setInputFile("./res/img/toolbar/copy.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/copy.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_PASTE = loader.setInputFile("./res/img/toolbar/paste.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/paste.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_RELOAD = loader.setInputFile("./res/img/toolbar/reload.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/reload.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_OPEN_EXTERN = loader.setInputFile("./res/img/toolbar/openExtern.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/openExtern.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_SENDMAIL = loader.setInputFile("./res/img/toolbar/sendMail.png").load().getImageIcon(),
				"Loading: Image[res/img/toolbar/sendMail.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_PREVIEW_HEADER = loader.setInputFile("./res/img/preview/header.png").load().getImageIcon(),
				"Loading: Image[res/img/preview/header.png]", 21));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_PREVIEW_FOOTER = loader.setInputFile("./res/img/preview/footer.png").load().getImageIcon(),
				"Loading: Image[res/img/preview/footer.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_PREVIEW_TEXT = loader.setInputFile("./res/img/preview/text.png").load().getImageIcon(),
				"Loading: Image[res/img/preview/text.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_PREVIEW_TEXT_WITH_TITLE = loader.setInputFile("./res/img/preview/textWithTitle.png").load().getImageIcon(),
				"Loading: Image[res/img/preview/textWithTitle.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_PREVIEW_IMAGE = loader.setInputFile("./res/img/preview/image.png").load().getImageIcon(),
				"Loading: Image[res/img/preview/image.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_PREVIEW_IMAGE_SMALL_WITH_TITLE = loader.setInputFile("./res/img/preview/imageSmallWithTitle.png").load().getImageIcon(),
				"Loading: Image[res/img/preview/imageSmallWithTitle.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_PREVIEW_IMAGE_WITH_TITLE = loader.setInputFile("./res/img/preview/imageWithTitle.png").load().getImageIcon(),
				"Loading: Image[res/img/preview/imageWithTitle.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_N_NDROP = loader.setInputFile("./res/img/dragEditor/dragN_Drop.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/dragN_Drop.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_HEADER = loader.setInputFile("./res/img/dragEditor/header.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/header.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_NO_IMAGE = loader.setInputFile("./res/img/dragEditor/noImage.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/noImage.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_NO_IMAGE_SMALL = loader.setInputFile("./res/img/dragEditor/noImageSmall.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/noImageSmall.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_BUY_NOW = loader.setInputFile("./res/img/dragEditor/buyNow.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/buyNow.png]", 2));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_INTERNAL_VIEW_CLOSE = loader.setInputFile("./res/img/internalView/close.png").load().getImageIcon(),
				"Loading: Image[res/img/internalView/close.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_INTERNAL_VIEW_CLOSE_MOUSEOVER = loader.setInputFile("./res/img/internalView/closeMouseOver.png").load().getImageIcon(),
				"Loading: Image[res/img/internalView/closeMouseOver.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_UP = loader.setInputFile("./res/img/dragEditor/up.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/up.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_UP_MOUSEOVER = loader.setInputFile("./res/img/dragEditor/upMouseOver.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/upMouseOver.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_DOWN = loader.setInputFile("./res/img/dragEditor/down.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/down.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_DOWN_MOUSEOVER = loader.setInputFile("./res/img/dragEditor/downMouseOver.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/downMouseOver.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_CLOSE = loader.setInputFile("./res/img/dragEditor/close.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/close.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_CLOSE_MOUSEOVER = loader.setInputFile("./res/img/dragEditor/closeMouseOver.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/closeMouseOver.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_CROSS = loader.setInputFile("./res/img/dragEditor/cross.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/cross.png]", 1));
		launchEvents.add(new LaunchEvent(() -> IMAGEICON_DRAG_EDITOR_CROSS_MOUSEOVER = loader.setInputFile("./res/img/dragEditor/crossMouseOver.png").load().getImageIcon(),
				"Loading: Image[res/img/dragEditor/crossMouseOver.png]", 1));
		launchEvents.add(new LaunchEvent(() -> htmlParser.loadSnippets(), "Loading [HTML-Snippets]", 2));
		launchEvents.add(new LaunchEvent(() -> {
			DATABASE_WEST.addUser(DB_USER_READONLY, SELECT);
			DATABASE_WEST.addUser(DB_USER_READONLY, INSERT);
			// DATABASE_WEST.testConnections();
			}, "Initialising: [Database connection]", 4));
		//launchEvents.add(new LaunchEvent(() -> WebView.PlatformImplementationStartUp(), "Loading: Javafx[Browser,WebEngine & Scene] ", 8));

		return launchEvents;
	}

	@Override
	public BufferedImage getLauncherIcon()
	{
		return IMAGE_LAUNCHER_LOGO;
	}

	public void setButtonSendMailEnabled(final boolean enabled)
	{
		view.setButtonSendMailEnabled(enabled);
	}

	public void setButtonReloadEnabled(final boolean enabled)
	{
		view.setButtonReloadEnabled(enabled);
	}

	public void setButtonOpenExternEnabled(final boolean enabled)
	{
		view.setButtonOpenExternEnabled(enabled);
	}

	public void setButtonCutEnalbed(final boolean enabled)
	{
		view.setButtonCutEnalbed(enabled);
	}

	public void setButtonCopyEnalbed(final boolean enabled)
	{
		view.setButtonCopyEnalbed(enabled);
	}

	public void setButtonPasteEnalbed(final boolean enabled)
	{
		view.setButtonPasteEnalbed(enabled);
	}

	public void setButtonRedoEnabled(final boolean enabled)
	{
		view.setButtonRedoEnabled(enabled);
	}

	public void setButtonUndoEnabled(final boolean enabled)
	{
		view.setButtonUndoEnabled(enabled);
	}

	public void setButtonNewProjectEnabled(final boolean enabled)
	{
		view.setButtonNewProjectEnabled(enabled);
	}

	public void setButtonSaveEnabled(final boolean enabled)
	{
		view.setButtonSaveEnabled(enabled);
	}

	public void setButtonSaveAllEnabled(final boolean enabled)
	{
		view.setButtonSaveAllEnabled(enabled);
	}

	public void setButtonPrintEnabled(final boolean enabled)
	{
		view.setButtonPrintEnabled(enabled);
	}

	public void setSelectedEditor(final Editor editor)
	{
		model.setSelectedEditor(editor);
	}

	// <- Static ->
	@FunctionalInterface
	public static interface CloseListener
	{
		public abstract void onCLose();
	}
}
