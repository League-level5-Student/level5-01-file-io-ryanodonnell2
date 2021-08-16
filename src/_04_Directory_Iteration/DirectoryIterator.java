package _04_Directory_Iteration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class DirectoryIterator {
	public static void main(String[] args) {
		/* 
		 * The following is an example of how to list all of the files in a directory.
		 * Once the program is running, the directory is chosen using the JFileChooser.
		 */
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File directory = jfc.getSelectedFile();
			File[] files = directory.listFiles();
			if(files != null) {
				for(File f : files) {
				  if(f.getAbsolutePath().endsWith(".java")) {
					  System.out.println(f.getAbsolutePath());
					  try {
							FileWriter fw = new FileWriter(f.getAbsolutePath(), true);
							
							fw.write("//Copyright © 2021 Ryan O'Donnell");
								
							fw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				  }
				}
				
					
				
			}
		}
		
		/*
		 * Your task is to write a program that iterates through the src folder of this current Java Project. 
		 * For every .java file it finds, the program will add a (non-legally binding) copyright statement at the bottom.
		 * Be aware of possible directories inside of directories.
		 * (e.g //Copyright Â© 2019 FirstName LastName)
		 */
	}
}

//C:\Users\rpodo\git\level5-01-file-io-ryanodonnell2\src\_04_Directory_Iteration

//Copyright © 2021 Ryan O'Donnell