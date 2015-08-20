/**
 * 
 */
package org.techzenth.Controllers;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.text.DefaultEditorKit;

import org.techzenth.Models.TextEditModel;
import org.techzenth.Views.TextEditView;

/**
 * @author Andre_Bonner
 *
 */
public class TextEditController {

	private TextEditModel teModel;
	private TextEditView teView;

	private KeyListener keyListener;
	private Action Open;
	private Action Save;
	private Action SaveAs;
	private Action Quit;
	private Action Cut;
	private Action Copy;
	private Action Paste;
	private Action PageSetup;
	private Action Print;
	private Action Undo;
	private Action Delete;
	private Action Find;
	private Action FindNext;
	private Action Replace;
	private Action GoTo;
	private Action SelectAll;
	private Action TimeDate;
	private Action WordWrap;
	private Action FontA;
	private Action StatusBar;
	private Action ViewHelp;
	private Action About;
	private ActionMap m;
	
	// Dialog ActionListeners
	private ActionListener FindNClick;
	private ActionListener FindNRClick;
	private ActionListener ReplaceClick;
	private ActionListener ReplaceAllClick;
	private ActionListener CancelClick;
	private ActionListener CancelRClick;
	private ActionListener GotoClick;
	private ActionListener CancelGClick;
	private ActionListener OkFClick;
	private ActionListener CancelFClick;
	private ActionListener OkAboutClick;
	
	
	private SimpleDateFormat sdfDate;
	private Date now;
	private static int wordCount, lastIndex;

	public TextEditController(TextEditModel teM, TextEditView teV) {
		this.teModel = teM;
		this.teView = teV;
	}

	public void control() {
		this.teModel = new TextEditModel();

		keyListener = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				teView.setChangeValue(true);
				teView.getSaveItem().setEnabled(true);
				teView.getSaveAsItem().setEnabled(true);
			}
		};
		teView.getArea().addKeyListener(keyListener);

		// MenuItem ActionListeners
		Open = new AbstractAction("Open") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				saveOld();
				if (teView.getfChooser().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					readInFile(teView.getfChooser().getSelectedFile()
							.getAbsolutePath());
				}
				teView.getSaveAsItem().setEnabled(true);
			}
		};
		teView.getOpenItem().addActionListener(Open);

		Save = new AbstractAction("Save") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (!teView.getCurrentFile().equals("Untitled"))
					saveFile(teView.getCurrentFile());
				else
					saveFileAs();
			}
		};
		teView.getSaveItem().addActionListener(Save);

		SaveAs = new AbstractAction("Save as...") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		};
		teView.getSaveAsItem().addActionListener(SaveAs);

		PageSetup = new AbstractAction("Page Setup") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				try {
					PrinterJob job = PrinterJob.getPrinterJob();
					PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
					PageFormat pf = job.pageDialog(aset);
					System.out.println(pf.toString());
					// job.setPrintable(teView.getArea(),pf);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		teView.getPageSetupItem().addActionListener(PageSetup);

		Print = new AbstractAction("Print") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				if (job.printDialog()) {
					try {
						job.print();
					} catch (PrinterException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		teView.getPrintItem().addActionListener(Print);

		Quit = new AbstractAction("Quit") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				saveOld();
				System.out.println("Exit with value = "+ teModel.getTextEditValue());
				System.exit(0);
			}
		};
		teView.getQuitItem().addActionListener(Quit);

		Undo = new AbstractAction("Undo") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// undo
				teView.getUndoMan().undo();
			}
		};
		teView.getUndoItem().addActionListener(Undo);

		m = teView.getArea().getActionMap();
		Cut = m.get(DefaultEditorKit.cutAction);
		Copy = m.get(DefaultEditorKit.copyAction);
		Paste = m.get(DefaultEditorKit.pasteAction);

		teView.getCutItem().addActionListener(Cut);
		teView.getCopyItem().addActionListener(Copy);
		teView.getPasteItem().addActionListener(Paste);

		Delete = new AbstractAction("Delete") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// delete
				teView.getArea().replaceSelection("");
			}
		};
		teView.getDeleteItem().addActionListener(Delete);

		Find = new AbstractAction("Find") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// find
				if(teView.getFindTxt().getText().isEmpty())
					teView.getFindDialog().setVisible(true);
			}
		};
		teView.getFindItem().addActionListener(Find);

		FindNext = new AbstractAction("Find Next") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// TODO - find next
				
			}
		};
		teView.getFindNextItem().addActionListener(FindNext);

		Replace = new AbstractAction("Replace") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// replace
				teView.getReplaceDialog().setVisible(true);
			}
		};
		teView.getReplaceItem().addActionListener(Replace);

		GoTo = new AbstractAction("Go To") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// go to
				teView.getGotoDialog().setVisible(true);
			}
		};
		teView.getGoToItem().addActionListener(GoTo);

		SelectAll = new AbstractAction("Select All") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// select all
				teView.getArea().selectAll();
			}
		};
		teView.getSelectAllItem().addActionListener(SelectAll);

		TimeDate = new AbstractAction("Time/Date") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// time / date
				now = new Date();
				sdfDate = new SimpleDateFormat("hh:mm a MM/dd/yyyy");
				String str1;
				str1 = sdfDate.format(now);
				System.out.println(str1);
				teView.getArea().insert(str1, teView.getArea().getSelectionStart());
			}
		};
		teView.getTimeDate().addActionListener(TimeDate);

		WordWrap = new AbstractAction("Word Wrap") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// word wrap
				if(teView.getArea().getWrapStyleWord()==false){
					teView.getWordWrapItem().setSelected(true);
					teView.getArea().setWrapStyleWord(true);
					teView.getGoToItem().setEnabled(false);
				}else{
					teView.getWordWrapItem().setSelected(false);
					teView.getArea().setWrapStyleWord(false);
					teView.getGoToItem().setEnabled(true);
				}
			}
		};
		teView.getWordWrapItem().addActionListener(WordWrap);

		FontA = new AbstractAction("Font...") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// font dialog
				teView.getFontDialog().setVisible(true);
			}
		};
		teView.getFontMItem().addActionListener(FontA);

		StatusBar = new AbstractAction("Status Bar") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// status bar
				if(teView.getStatusbar().isVisible()){
					System.out.println("Selected");
					teView.getStatusbar().setVisible(false);
					teView.getStatusBarItem().setSelected(false);
				}else{
					System.out.println("Not Selected");
					teView.getStatusbar().setVisible(true);
					teView.getStatusBarItem().setSelected(true);					
				}
			}
		};
		teView.getStatusBarItem().addActionListener(StatusBar);

		ViewHelp = new AbstractAction("View Help") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// TODO - view help
			}
		};
		teView.getViewHelpItem().addActionListener(ViewHelp);

		About = new AbstractAction("About") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// TODO - about dialog
				teView.getaboutDialog().setVisible(true);
			}
		};
		teView.getAboutItem().addActionListener(About);
		
		// Dialog Action Listeners
		FindNClick = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO - FindNext
				findNext(teView.getFindTxt().getText(), teView.getFindDirection(), teView.getMatchCaseChk().isSelected());
			}
		};
		teView.getFindBtn().addActionListener(FindNClick);
		
		FindNRClick = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO - FindNext
			}
		};
		teView.getFindRBtn().addActionListener(FindNRClick);
		
		CancelClick = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO - Cancel
				teView.getFindDialog().setVisible(false);
			}
		};
		teView.getCancelBtn().addActionListener(CancelClick);
		
		CancelRClick = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO - Cancel
				teView.getReplaceDialog().setVisible(false);
			}
		};
		teView.getCancelRBtn().addActionListener(CancelRClick);
		
		ReplaceClick = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// TODO - Replace
			}
		};
		teView.getReplaceBtn().addActionListener(ReplaceClick);
		
		ReplaceAllClick = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// TODO - Replace All
			}
		};
		teView.getReplaceAllBtn().addActionListener(ReplaceAllClick);
	
		GotoClick = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// TODO - Goto
			}
		};
		teView.getGotoBtn().addActionListener(GotoClick);
		
		CancelGClick = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Cancel
				teView.getGotoDialog().setVisible(false);
			}
		};
		teView.getGotoBtn().addActionListener(CancelGClick);
		
		OkFClick = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// TODO - Ok
				
			}
		};
		teView.getOkFBtn().addActionListener(OkFClick);
		
		CancelFClick = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Cancel
				teView.getFontDialog().setVisible(false);
			}
		};
		teView.getCancelFBtn().addActionListener(CancelFClick);
		
		OkAboutClick = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// OK
				teView.getaboutDialog().setVisible(false);
			}
		};
		teView.getOkABtn().addActionListener(OkAboutClick);
		
	}
	
	private void findNext(String search, String direction, boolean matchCase){
		String areaText = new String(teView.getArea().getText());
		if(matchCase){
			// must be either upper or lower case
		}
		
			lastIndex = teView.getArea().getText().indexOf(search,lastIndex);
			teView.getArea().select(lastIndex, lastIndex + search.length());
			if(lastIndex!=-1){
				wordCount++;
				lastIndex += search.length();
			}else{
				JOptionPane.showMessageDialog(null, "Cannot find \"" + search + "\"", teModel.getAppName(), JOptionPane.INFORMATION_MESSAGE);
			}
		
		//System.out.println(wordCount);
		System.out.println(lastIndex);
		System.out.println(direction);
	}
	
	private void saveFileAs() {
		if (teView.getfChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			saveFile(teView.getfChooser().getSelectedFile().getAbsolutePath());
	}

	private void saveOld() {
		if (teView.getChangeValue()) {
			if (JOptionPane.showConfirmDialog(teView.getFrame(),
					"Would you like to save " + teView.getCurrentFile() + " ?",
					"Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				saveFile(teView.getCurrentFile());
		}
	}

	private void readInFile(String fileName) {
		try {
			FileReader r = new FileReader(fileName);
			teView.getArea().read(r, null);
			r.close();
			teView.setCurrentFile(fileName);
			teView.getFrame().setTitle(
					teView.getCurrentFile() + " - " + teModel.getAppName());
			teView.setChangeValue(false);
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(teView.getFrame(),
					"Editor can't find the file called " + fileName);
		}
	}

	private void saveFile(String fileName) {
		try {
			FileWriter w = new FileWriter(fileName);
			teView.getArea().write(w);
			w.close();
			teView.setCurrentFile(fileName);
			teView.getFrame().setTitle(
					teView.getCurrentFile() + " - " + teModel.getAppName());
			teView.setChangeValue(false);
			teView.getSaveItem().setEnabled(false);
		} catch (IOException e) {
		}
	}
}
