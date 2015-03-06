/**
 * 
 */
package org.techzenth.Views;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.techzenth.Models.TextEditModel;

/**
 * @author Andre_Bonner
 *
 */
public class TextEditView {

	private TextEditModel teModel;
	private JFrame frame;
	private JMenuBar JMB;
	private JMenu file;
	private JMenu edit;
	private JMenu format;
	private JMenu view;
	private JMenu help;
	private JMenuItem New;
	private JMenuItem Open;
	private JMenuItem Save;
	private JMenuItem SaveAs;
	private JMenuItem PageSetup;
	private JMenuItem Print;
	private JMenuItem Quit;
	private JMenuItem Undo;
	private JMenuItem Cut;
	private JMenuItem Copy;
	private JMenuItem Paste;
	private JMenuItem Delete;
	private JMenuItem Find;
	private JMenuItem FindNext;
	private JMenuItem Replace;
	private JMenuItem GoTo;
	private JMenuItem SelectAll;
	private JMenuItem TimeDate;
	private JCheckBoxMenuItem WordWrap;
	private JMenuItem FontM;
	private JCheckBoxMenuItem StatusBar;
	private JMenuItem ViewHelp;
	private JMenuItem About;
	
	private JTextArea area = new JTextArea(20, 120);
	private JFileChooser dialog = new JFileChooser(
			System.getProperty("user.dir"));

	private String currentFile = "Untitled";
	private boolean changed = false;

	public TextEditView(String title) {
		teModel = new TextEditModel();
		teModel.setAppName(title);
		frame = new JFrame(currentFile + " - " + teModel.getAppName());
		frame.setSize(800, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		initGUI();
	}

	private void initGUI() {
		// TODO - Initialize GUI
		area.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane scroll = new JScrollPane(area,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scroll, BorderLayout.CENTER);

		JMB = new JMenuBar();
		frame.setJMenuBar(JMB);
		file = new JMenu("File");
		edit = new JMenu("Edit");
		format = new JMenu("Format");
		view = new JMenu("View");
		help = new JMenu("Help");
		New = new JMenuItem();
		Open = new JMenuItem();
		Save = new JMenuItem();
		SaveAs = new JMenuItem();
		PageSetup = new JMenuItem();
		Print = new JMenuItem();
		Quit = new JMenuItem();
		Undo = new JMenuItem();
		Cut = new JMenuItem();
		Copy = new JMenuItem();
		Paste = new JMenuItem();
		Delete = new JMenuItem();
		Find = new JMenuItem();
		FindNext = new JMenuItem();
		Replace = new JMenuItem();
		GoTo = new JMenuItem();
		SelectAll = new JMenuItem();
		TimeDate = new JMenuItem();
		WordWrap = new JCheckBoxMenuItem();
		FontM = new JMenuItem();
		StatusBar = new JCheckBoxMenuItem();
		ViewHelp = new JMenuItem();
		About = new JMenuItem();

		JMB.add(file);
		JMB.add(edit);
		JMB.add(format);
		JMB.add(view);
		JMB.add(help);

		file.add(New);
		file.add(Open);
		file.add(Save);
		file.add(SaveAs);
		file.addSeparator();
		file.add(PageSetup);
		file.add(Print);
		file.addSeparator();
		file.add(Quit);

		edit.add(Undo);
		edit.addSeparator();
		edit.add(Cut);
		edit.add(Copy);
		edit.add(Paste);
		edit.add(Delete);
		edit.addSeparator();
		edit.add(Find);
		edit.add(FindNext);
		edit.add(Replace);
		edit.add(GoTo);
		edit.addSeparator();
		edit.add(SelectAll);
		edit.add(TimeDate);
		
		format.add(WordWrap);
		format.add(FontM);
		
		view.add(StatusBar);
		
		help.add(ViewHelp);
		help.addSeparator();
		help.add(About);

		file.getItem(0).setText("New");
		file.getItem(1).setText("Open");
		file.getItem(2).setText("Save");
		file.getItem(3).setText("Save As");
		file.getItem(5).setText("Page Setup");
		file.getItem(6).setText("Print ...");
		file.getItem(8).setText("Quit");
		
		edit.getItem(0).setText("Undo");
		edit.getItem(0).setEnabled(false);
		edit.getItem(2).setText("Cut");
		edit.getItem(3).setText("Copy");
		edit.getItem(4).setText("Paste");
		edit.getItem(5).setText("Delete");
		edit.getItem(7).setText("Find");
		edit.getItem(8).setText("Find Next");
		edit.getItem(9).setText("Replace");
		edit.getItem(10).setText("Go To");
		edit.getItem(12).setText("Select All");
		edit.getItem(13).setText("Time/Date");
		
		format.getItem(0).setText("WordWrap");
		format.getItem(1).setText("Font ...");
		
		view.getItem(0).setText("StatusBar ...");
		
		help.getItem(0).setText("View Help");
		help.getItem(2).setText("About "+ teModel.getAppName());
		
		Save.setEnabled(false);
		SaveAs.setEnabled(false);

	}

	public boolean getChangeValue() {
		return changed;
	}

	public void setChangeValue(boolean c) {
		changed = c;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JMenuItem getNewItem() {
		return New;
	}

	public JMenuItem getOpenItem() {
		return Open;
	}

	public JMenuItem getSaveItem() {
		return Save;
	}

	public JMenuItem getSaveAsItem() {
		return SaveAs;
	}

	public JMenuItem getPageSetupItem(){
		return PageSetup;
	}
	
	public JMenuItem getPrintItem(){
		return Print;
	}
	
	public JMenuItem getQuitItem() {
		return Quit;
	}
	
	public JMenuItem getUndoItem(){
		return Undo;
	}
	
	public JMenuItem getCutItem(){
		return Cut;
	}
	
	public JMenuItem getCopyItem(){
		return Copy;
	}
	
	public JMenuItem getPasteItem(){
		return Paste;
	}
	
	public JMenuItem getDeleteItem(){
		return Delete;
	}
	
	public JMenuItem getFindItem(){
		return Find;
	}
	
	public JMenuItem getFindNextItem(){
		return FindNext;
	}
	
	public JMenuItem getReplaceItem(){
		return Replace;
	}
	
	public JMenuItem getGoToItem(){
		return GoTo;
	}
	
	public JMenuItem getSelectAllItem(){
		return SelectAll;
	}
	
	public JMenuItem getTimeDate(){
		return TimeDate;
	}
	
	public JCheckBoxMenuItem getWordWrapItem(){
		return WordWrap;
	}
	
	public JMenuItem getFontMItem(){
		return FontM;
	}
	
	public JCheckBoxMenuItem getStatusBarItem(){
		return StatusBar;
	}
	
	public JMenuItem getViewHelpItem(){
		return ViewHelp;
	}
	
	public JMenuItem getAboutItem(){
		return About;
	}
	
	public JFileChooser getfChooser() {
		return dialog;
	}

	public void setCurrentFile(String file) {
		currentFile = file;
	}

	public String getCurrentFile() {
		return currentFile;
	}

	public JTextArea getArea() {
		return area;
	}
}
