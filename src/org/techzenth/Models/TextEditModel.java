/**
 * 
 */
package org.techzenth.Models;

/**
 * @author Andre_Bonner
 *
 */
public class TextEditModel {
	private int textedit;
	private String AppName;
		
	public TextEditModel(){
		
	}
	
	public TextEditModel(int t){
		this.textedit=t;
	}
	
	public int getTextEditValue(){
		return this.textedit;
	}
	
	public void setAppName(String aN){
		this.AppName = aN;
	}
	
	public String getAppName(){
		return AppName;
	}
	}
