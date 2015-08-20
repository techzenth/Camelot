/**
 * 
 */
package org.techzenth.Models;

import java.util.Random;

/**
 * @author Andre_Bonner
 *
 */
public class TextEditModel {
	private Random gen = new Random();
	private int textedit;
	private String AppName;
			
	public TextEditModel(){
		this.textedit= gen.nextInt(1000);
	}
	
	public TextEditModel(int t){
		this.textedit= gen.nextInt(1000*t);
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
