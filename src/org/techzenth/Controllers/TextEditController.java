/**
 * 
 */
package org.techzenth.Controllers;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
			}
		};
		teView.getFindItem().addActionListener(Find);

		FindNext = new AbstractAction("Find Next") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// find next
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
				teView.getArea().setWrapStyleWord(true);
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
			}
		};
		teView.getStatusBarItem().addActionListener(StatusBar);

		ViewHelp = new AbstractAction("View Help") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// view help
			}
		};
		teView.getViewHelpItem().addActionListener(ViewHelp);

		About = new AbstractAction("About") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// about dialog
			}
		};
		teView.getAboutItem().addActionListener(About);
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
