package _02_File_Encrypt_Decrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileDecryptor {
	private int[] privateKey = {26,27,28};
	private int increasemin = 37;
	public FileDecryptor(String fileLoc) {
		// TODO Auto-generated constructor stub
		System.out.println(decrypt(readFile(fileLoc)));
	}
	
	private String decrypt(String message) {
		System.out.println(message);
		String decryptBuild = "";
		int j = 0;
		for (int i = 0; i < message.length(); i++) {
			int charReplaceVal = ((int)message.charAt(i))-(increasemin+privateKey[j]);
			char c = (char) (charReplaceVal);
			decryptBuild+=c;
			j++;
			if(j>=privateKey.length) {
				j=0;
			}
			System.out.println(((int)message.charAt(i))-(increasemin+privateKey[j]));
			System.out.println(c);
			System.out.println();
		}
		System.out.println(decryptBuild);
		System.out.println(Character.getNumericValue(decryptBuild.charAt(0)));
		return decryptBuild;
	}
	public static void main(String[] args) {
		new FileDecryptor("src/_02_File_Encrypt_Decrypt/data.txt");
	}
	
	public String readFile(String loc) {
		String output = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(loc));
			
			String line = br.readLine();
			while(line != null){
				output+=line;
				line = br.readLine();
			}
			
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	/*
	 * Decryption is the process of taking encoded or encrypted text or other data
	 * and converting it back into text that you or the computer can read and understand.
	 *
	 * The shift cipher is decrypted by using using the key and shifting back up,
	 * at the end of our encryption example we had our alphabet as:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 *
	 * If we shift it back up by 4 based on the key we used the alphabet is decrypted:
	 *
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 *
	 * "Lipps Asvph" returns to "Hello World"
	 * 
	 * Create a program that opens the file created by FileEncryptor and decrypts
	 * the message, then display it to the user in a JOptionPane.
	 */
}
