/**
 * 
 */
package org.techzenth;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.techzenth.Controllers.TextEditController;
import org.techzenth.Models.TextEditModel;
import org.techzenth.Views.TextEditView;

/**
 * @author Andre_Bonner
 *
 */
public class Camelot {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Camelot!!!");
		try{
			
			String cn = UIManager.getSystemLookAndFeelClassName();
			System.out.println(cn);
			UIManager.setLookAndFeel(cn);
		}
		catch(Exception ex){}
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				TextEditModel model = new TextEditModel();
				TextEditView view = new TextEditView("TextEditPad");
				TextEditController controller = new TextEditController(model,view);
				controller.control();
			}
		});
	}

}
