package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileEncryptor {
	//private int[] privateKey = {81,77,45,52,67,29,673,453,76,659,435,73,58,29,24,97,10,22,38,69,203,78,675,56,43,62,39,90,14,32,657,120,54,35,63,36,59,37};
	private int[] privateKey = {26,27,28};
	private int increasemin = 37;
	public FileEncryptor(String fileLoc, String message) {
		// TODO Auto-generated constructor stub
		writeToFile(fileLoc, encrypt(message));
	}
	
	private String encrypt(String message) {
		String encryptBuild = "";
		int j = 0;
		for (int i = 0; i < message.length(); i++) {
			int curChar = (int)message.charAt(i);
			System.out.println(curChar);
			System.out.println(curChar+increasemin+privateKey[j]);
			int charReplaceVal = curChar+increasemin+privateKey[j];
			char c = (char) charReplaceVal;
			System.out.println(c);
			System.out.println();
			System.out.println((int) c);
			System.out.println();
			encryptBuild+=c;
			j++;
			if(j>=privateKey.length) {
				j=0;
			}
		}
		System.out.println(encryptBuild);
		return encryptBuild;
	}
	public static void main(String[] args) {
		new FileEncryptor("src/_02_File_Encrypt_Decrypt/data.txt", JOptionPane.showInputDialog("Message To Encrypt:"));
	}
	
	public void writeToFile(String loc, String encryption) {
		try {
			FileWriter fw = new FileWriter(loc);
			
			/*
			NOTE: To append to a file that already exists, add true as a second parameter when calling the
			      FileWriter constructor.
			      (e.g. FileWriter fw = new FileWriter("src/_00_Intro_To_File_Input_and_Output/test2.txt", true);)
			*/
			
			fw.write(encryption);
				
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Encryption is the process of encoding a message or information
	 * in such a way that only authorized parties can access it and
	 * those who are not authorized cannot.
	 *
	 * A simple shift cipher works by shifting the letters of a message
	 * down based on a key. If our key is 4 then:
	 * 
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 * 
	 * becomes:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 * 
	 * "Hello World" changes to "Lipps Asvph"
	 *
	 * Create a program that takes a message and a key from the user.
	 * Use the key to shift each letter in the users input and save the final result to a file.
	 */
}
