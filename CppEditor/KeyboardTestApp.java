package CppEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import CppBuilder.Builder;
import Display.Display;

public class KeyboardTestApp implements ActionListener {

	private Thread thread = new Thread("TextAppThread#1");
	private Display display = new Display("Autonomous Builder C++");
	private JTextArea textarea = new JTextArea();
	private Font font = new Font("Montserrat Thin", Font.BOLD, 35);
    private JScrollPane scrollpane = new JScrollPane(textarea);
	private JMenuBar mb = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem open = new JMenuItem("Open");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem exit = new JMenuItem("Exit");

	public void KeyBoardTestApp() throws InterruptedException {
		thread.start();
		println(thread.getName() + " Has Started");
		textarea.setBackground(new Color(57, 59, 58));
		textarea.setLineWrap(true);  
        textarea.setFont(font);
        textarea.setForeground(Color.white);
        String fileData = Builder.getFileData();
        System.out.println();
        textarea.setText(fileData);
		scrollpane.setPreferredSize(display.display.getSize());
		display.display.setSize(700,700);
		display.display.setLocationRelativeTo(null);
		display.display.add(scrollpane);
		display.display.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		display.display.setVisible(true);
		display.display.setJMenuBar(mb);
        //Menu Bar Section
        mb.setBackground(Color.white);
        mb.add(fileMenu);
        mb.setVisible(true);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(exit);
        open.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);

        //Menu Bar Section

        println(thread.getName() + " Is Going To Sleep");
        thread.join();
	}
	
	public void println(String args) throws InterruptedException {
		System.out.println(args);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == open) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(Builder.filePath));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
			fileChooser.setFileFilter(filter);
			
			int response = fileChooser.showOpenDialog(null);
			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				Scanner fileIn = null;
				
				try {
					fileIn = new Scanner(file);
					if(file.isFile()) {
						while(fileIn.hasNextLine()) {
							String line = fileIn.nextLine()+"\n";
							textarea.append(line);
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				finally {
					fileIn.close();
				}
			}
			
			
		}
		if(e.getSource() == save) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("Desktop"));
			
			int respone = fileChooser.showSaveDialog(null);
			
			if(respone == JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter fileOut = null;
				
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					fileOut = new PrintWriter(file);
					fileOut.println(textarea.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				finally {
					fileOut.close();
				}
				
			}
		}
		if(e.getSource() == exit) {
			System.exit(0);
	
	
		}
	}
	
}
