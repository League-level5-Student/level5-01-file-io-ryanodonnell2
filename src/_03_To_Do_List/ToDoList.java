package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 *
	 * When add task is clicked:
	 * 		Create a JOptionPane to ask the user for a task and add it to an ArrayList
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		Create a JOptionPane to prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Create a JOptionPane to Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list. 
	 */
	
	JFrame frame;
	JPanel panel;
	JButton addTask;
	JButton viewTask;
	JButton removeTask;
	JButton saveList;
	JButton loadList;
	ArrayList<String> list;
	
	public static void main(String[] args) {
		new ToDoList().setup();
	}
	
	public void setup() {
		list = new ArrayList<String>();
		frame = new JFrame();
		panel = new JPanel();
		
		addTask = new JButton("Add Task");
		addTask.addActionListener(this);
		panel.add(addTask);

		viewTask = new JButton("View Task");
		viewTask.addActionListener(this);
		panel.add(viewTask);

		removeTask = new JButton("Remove Task");
		removeTask.addActionListener(this);
		panel.add(removeTask);

		saveList = new JButton("Save List");
		saveList.addActionListener(this);
		panel.add(saveList);

		loadList = new JButton("Load List");
		loadList.addActionListener(this);
		panel.add(loadList);
		
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		String initLoc = getInitialLoc();
		if(initLoc!=null) {
			loadArray(initLoc);
		}
	}
	
	public void loadArray(String Loc){
		try {
			BufferedReader br = new BufferedReader(new FileReader(Loc));
			
			String line = br.readLine();
			while(line != null){
				System.out.println(line);
				list.add(line);
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
	}
	
	public String getInitialLoc() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/_03_To_Do_List/dataLoc.txt"));
			
			String loc = br.readLine();
			br.close();
			return loc;
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String listToString(ArrayList<String> s) {
		String build = "";
		for (int i = 0; i < s.size(); i++) {
			build += ""+(i+1)+". "+s.get(i)+"\r\n";
		}
		return build;
	}
	
	public String fileToList(ArrayList<String> s) {
		String build = "";
		for (int i = 0; i < s.size(); i++) {
			build += ""+s.get(i)+"\r\n";
		}
		return build;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addTask) {
			list.add(JOptionPane.showInputDialog("What task do you want to add?"));
		}
		else if(e.getSource()==viewTask) {
			JOptionPane.showMessageDialog(null, "Tasks: \r\n"+listToString(list));
		}
		else if(e.getSource()==removeTask) {
			list.remove(Integer.parseInt(JOptionPane.showInputDialog("What item number is the task?"))-1);
		}
		else if(e.getSource()==saveList) {
			JFileChooser jfc = new JFileChooser();
			int returnVal = jfc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();
				writeToFile(fileName, fileToList(list));
				writeToFile("src/_03_To_Do_List/dataLoc.txt", fileName);
			}
		}
		else if(e.getSource()==loadList) {
			JFileChooser jfc = new JFileChooser();
			int returnVal = jfc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();
				list.clear();
				loadArray(fileName);
				writeToFile("src/_03_To_Do_List/dataLoc.txt", fileName);
			}
		}
	}
}
