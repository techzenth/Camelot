/**
 * 
 */
package org.techzenth.Views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.undo.UndoManager;

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
	private JLabel lblStatusBar;
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	
	private UndoManager umanager;

	// Find Next Dialog
	private JDialog findDialog;
	private JPanel dPanel;
	private JLabel lblFind;
	private JTextField txtFind;
	private JButton btnFind;
	private JButton btnCancel;
	private JCheckBox chkMatch;
	private JRadioButton rdUp,rdDown;
	private ButtonGroup btnGrp;
	private JPanel gPanel;
	
	// Replace Dialog
	private JDialog replaceDialog;
	private JPanel drPanel;
	private JLabel lblFindR;
	private JTextField txtFindR;
	private JLabel lblReplace;
	private JTextField txtReplace;
	private JButton btnFindR;
	private JButton btnReplace;
	private JButton btnReplaceAll;
	private JButton btnCancelR;
	private JCheckBox chkMatchR;
	
	// Goto Dialog
	private JDialog gotoDialog;
	private JPanel dgPanel;
	private JLabel lblLineN;
	private JTextField txtLineN;
	private JButton btnGoto;
	private JButton btnCancelG;
	
	// Font Dialog
	private JDialog fontDialog;
	private JPanel dfPanel;
	private JLabel lblFont;
	private JTextField txtFont;
	@SuppressWarnings("rawtypes")
	private JList lstFont;
	private JLabel lblFontStyle;
	private JTextField txtFontStyle;
	@SuppressWarnings("rawtypes")
	private JList lstFontStyle;
	private JLabel lblFontSize;
	private JTextField txtFontSize;
	@SuppressWarnings("rawtypes")
	private JList lstFontSize;
	private JPanel panSample;
	private JLabel lblSample;
	private JButton btnOkF;
	private JButton btnCancelF;
	
	
	// About Dialog
	private JDialog aboutDialog;
	private JPanel panAbout;
	private JLabel lblAboutInfo;
	private JButton btnOkAbout;
	
	
	private String currentFile = "Untitled";
	private boolean changed = false;

	public TextEditView(String title) {
		teModel = new TextEditModel();
		teModel.setAppName(title);
		frame = new JFrame(currentFile + " - " + teModel.getAppName());
		frame.setSize(800, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
		frame.setVisible(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initGUI() {
		// TODO - Initialize GUI
		area.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		JScrollPane scroll = new JScrollPane(area,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scroll, BorderLayout.CENTER);
		
		umanager = new UndoManager();
		area.getDocument().addUndoableEditListener(umanager);

		// Find Dialog
		findDialog = new JDialog(frame,"Find");
		findDialog.setSize(370, 150);
		findDialog.setResizable(false);
		findDialog.setLocationRelativeTo(null);
		dPanel = new JPanel();
		findDialog.getContentPane().add(dPanel);
		dPanel.setLayout(null);
		lblFind = new JLabel("Find what:");
		lblFind.setBounds(10, 10, 80, 25);
		txtFind = new JTextField();
		txtFind.setBounds(90, 10, 160, 23);
		btnFind = new JButton("Find Next");
		btnFind.setBounds(270, 10, 80, 23);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(270, 50, 80, 23);
		chkMatch = new JCheckBox("Match case");
		chkMatch.setBounds(10, 90, 80, 25);
		rdUp = new JRadioButton("Up");
		rdDown = new JRadioButton("Down");
		rdDown.setSelected(true);
		btnGrp = new ButtonGroup();
		gPanel = new JPanel();
		gPanel.setBounds(120, 50, 130, 40);
		gPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		dPanel.add(lblFind);
		dPanel.add(txtFind);
		dPanel.add(btnFind);
		dPanel.add(btnCancel);
		dPanel.add(chkMatch);
		dPanel.add(gPanel);
		btnGrp.add(rdUp);
		btnGrp.add(rdDown);
		gPanel.add(rdUp);
		gPanel.add(rdDown);
		
		// Replace Dialog
		replaceDialog = new JDialog(frame,"Replace...");
		replaceDialog.setSize(400, 210);
		replaceDialog.setResizable(false);
		replaceDialog.setLocationRelativeTo(null);
		drPanel = new JPanel();
		replaceDialog.getContentPane().add(drPanel);
		drPanel.setLayout(null);
		lblFindR = new JLabel("Find what:");
		lblFindR.setBounds(10, 10, 80, 25);
		txtFindR = new JTextField();
		txtFindR.setBounds(90, 10, 160, 30);
		btnFindR = new JButton("Find Next");
		btnFindR.setBounds(270, 10, 100, 30);
		lblReplace = new JLabel("Replace with:");
		lblReplace.setBounds(10, 50, 80, 25);
		txtReplace = new JTextField();
		txtReplace.setBounds(90, 50, 160, 30);
		btnReplace = new JButton("Replace");
		btnReplace.setBounds(270, 50, 100, 30);
		btnReplaceAll = new JButton("Replace All");
		btnReplaceAll.setBounds(270, 90, 100, 30);
		btnCancelR = new JButton("Cancel");
		btnCancelR.setBounds(270, 130, 100, 30);
		chkMatchR = new JCheckBox("Match case");
		chkMatchR.setBounds(10, 130, 80, 30);
		drPanel.add(lblFindR);
		drPanel.add(txtFindR);
		drPanel.add(btnFindR);
		drPanel.add(lblReplace);
		drPanel.add(txtReplace);
		drPanel.add(btnReplace);
		drPanel.add(btnReplaceAll);
		drPanel.add(btnCancelR);
		drPanel.add(chkMatchR);
		
		// Goto Dialog
		gotoDialog = new JDialog(frame,"Go To Line");
		gotoDialog.setSize(300, 160);
		gotoDialog.setResizable(false);
		gotoDialog.setLocationRelativeTo(null);
		dgPanel = new JPanel();
		gotoDialog.getContentPane().add(dgPanel);
		dgPanel.setLayout(null);
		lblLineN = new JLabel("Line number:");
		lblLineN.setBounds(10,10,80,25);
		txtLineN = new JTextField();
		txtLineN.setBounds(10, 40, 270, 30);
		btnGoto = new JButton("Go To");
		btnGoto.setBounds(100, 90, 80, 30);
		btnCancelG = new JButton("Cancel");
		btnCancelG.setBounds(200, 90, 80, 30);
		dgPanel.add(lblLineN);
		dgPanel.add(txtLineN);
		dgPanel.add(btnGoto);
		dgPanel.add(btnCancelG);
		
		// Font Dialog
		final String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); 
		final String[] fontStyles = {"Regular","Italic","Bold","Bold Italic"};
		final String[] fontSizes = {"8","9","10","11","12","13","14","15","16","17","18","20","22"};
		fontDialog = new JDialog(frame,"Font");
		fontDialog.setSize(440,440);
		fontDialog.setResizable(false);
		fontDialog.setLocationRelativeTo(null);
		dfPanel = new JPanel();
		fontDialog.getContentPane().add(dfPanel);
		dfPanel.setLayout(null);
		lblFont = new JLabel("Font:");
		lblFont.setBounds(10, 10, 100, 30);
		txtFont = new JTextField();
		txtFont.setBounds(10, 40, 150, 30);
		lstFont = new JList(fontNames);
		lstFont.setVisibleRowCount(5);
		lstFont.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lstFont.setLayoutOrientation(JList.VERTICAL);
		lstFont.setBounds(10, 80, 150, 170);
		lblFontStyle = new JLabel("Font style:");
		lblFontStyle.setBounds(180, 10, 100, 30);
		txtFontStyle = new JTextField();
		txtFontStyle.setBounds(180, 40, 130, 30);
		lstFontStyle = new JList(fontStyles);
		lstFontStyle.setVisibleRowCount(5);
		lstFontStyle.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lstFontStyle.setLayoutOrientation(JList.VERTICAL);
		lstFontStyle.setBounds(180, 80, 130, 170);
		lblFontSize = new JLabel("Size:");
		lblFontSize.setBounds(340, 10, 60, 30);
		txtFontSize = new JTextField();
		txtFontSize.setBounds(340, 40, 80, 30);
		lstFontSize = new JList(fontSizes);
		lstFontSize.setVisibleRowCount(5);
		lstFontSize.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lstFontSize.setBounds(340, 80, 80, 140);
		panSample = new JPanel();
		panSample.setBorder(BorderFactory.createEtchedBorder());
		panSample.setLayout(new BorderLayout());
		panSample.setBounds(180,270,230, 60);
		lblSample = new JLabel("AaBbYyZz");
		panSample.add(lblSample,BorderLayout.CENTER);
		btnOkF = new JButton("OK");
		btnOkF.setBounds(240, 360, 80, 25);
		btnCancelF = new JButton("Cancel");
		btnCancelF.setBounds(340, 360, 80, 25);
		dfPanel.add(lblFont);
		dfPanel.add(txtFont);
		//dfPanel.add(lstFont);
		dfPanel.add(lblFontStyle);
		dfPanel.add(txtFontStyle);
		dfPanel.add(lstFontStyle);
		dfPanel.add(lblFontSize);
		dfPanel.add(txtFontSize);
		dfPanel.add(lstFontSize);
		dfPanel.add(panSample);
		dfPanel.add(btnOkF);
		dfPanel.add(btnCancelF);
		dfPanel.add(new JScrollPane(lstFont), BorderLayout.CENTER);
		//dfPanel.add(new JScrollPane(lstFontStyle));
		//dfPanel.add(new JScrollPane(lstFontSize));
		
		// About Dialog
		aboutDialog = new JDialog(frame,"About "+teModel.getAppName());
		aboutDialog.setSize(300, 200);
		aboutDialog.setResizable(false);
		aboutDialog.setLocationRelativeTo(null);
		panAbout = new JPanel();
		aboutDialog.getContentPane().add(panAbout);
		panAbout.setLayout(null);
		lblAboutInfo = new JLabel();
		lblAboutInfo.setText("This product is licensed under the Software License Terms");
		lblAboutInfo.setBounds(10, 10, 280, 180);
		btnOkAbout = new JButton("OK");
		btnOkAbout.setBounds(170, 120, 100, 30);
		panAbout.add(lblAboutInfo, BorderLayout.CENTER);
		panAbout.add(btnOkAbout, BorderLayout.PAGE_END);
		
		
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
		lblStatusBar = new JLabel(" Statusbar");

		lblStatusBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        frame.add(lblStatusBar, BorderLayout.SOUTH);
		
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
		
		edit.getItem(0).setEnabled(false);
		edit.getItem(2).setEnabled(false);
		edit.getItem(3).setEnabled(false);
		edit.getItem(4).setEnabled(false);
		edit.getItem(5).setEnabled(false);
		
		Save.setEnabled(false);
		SaveAs.setEnabled(false);
		
		WordWrap.setSelected(true);
		StatusBar.setSelected(true);
		
		area.setWrapStyleWord(true);

	}

	public boolean getChangeValue() {
		return changed;
	}

	public void setChangeValue(boolean c) {
		changed = c;
		if(umanager.canUndo()){
			Undo.setEnabled(true);
		}else{
			Undo.setEnabled(false);
		}
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
	
	public JDialog getFindDialog(){
		return findDialog;
	}
	
	public JDialog getReplaceDialog(){
		return replaceDialog;
	}
	
	public JDialog getGotoDialog(){
		return gotoDialog;
	}
	
	public JDialog getFontDialog(){
		return fontDialog;
	}
	
	public JDialog getaboutDialog(){
		return aboutDialog;
	}
	
	public JTextField getFindTxt(){
		return txtFind;
	}
	
	public JCheckBox getMatchCaseChk(){
		return chkMatch;
	}
	
	public ButtonGroup getRadioDirGrp(){
		return btnGrp;
	}
	
	public String getFindDirection(){
		String direction = "Down";
		if(rdUp.isSelected()){
			direction = "Up";
		}
		if(rdDown.isSelected()){
			direction = "Down";
		}
		return direction;

	}
	
	public JButton getFindBtn(){
		return btnFind;
	}
	
	public JButton getCancelBtn(){
		return btnCancel;
	}
	
	public JButton getFindRBtn(){
		return btnFindR;
	}
	
	public JButton getReplaceBtn(){
		return btnReplace;
	}
	
	public JButton getReplaceAllBtn(){
		return btnReplaceAll;
	}
	
	public JButton getCancelRBtn(){
		return btnCancelR;
	}
	
	public JButton getGotoBtn(){
		return btnGoto;
	}
	
	public JButton getCancelGBtn(){
		return btnCancelG;
	}
	
	public JButton getOkFBtn(){
		return btnOkF;
	}
	
	public JButton getCancelFBtn(){
		return btnCancelF;
	}
	
	public JButton getOkABtn(){
		return btnOkAbout;
	}
	
	public JTextArea getArea() {
		return area;
	}
	
	public UndoManager getUndoMan(){
		
		return umanager;
	}
	
	//public JDialog getfoChooser(){
		
	//}

	public JLabel getStatusbar(){
		return lblStatusBar;
	}
	
	public void setCurrentFile(String file) {
		currentFile = file;
	}

	public String getCurrentFile() {
		return currentFile;
	}

	
}
