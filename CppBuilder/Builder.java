package CppBuilder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Display.Display;
import Display.panel;

public class Builder {
	public static String filePath;
	public static int AmountOflines;
	public static boolean include = false;
    //https://help.vexcodingstudio.com/#pro
    public static void buildTemplate(String template) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath = "build\\Autonomous.cpp")));
        PrintWriter vex = new PrintWriter(new BufferedWriter(new FileWriter("build\\vex.h")));
        PrintWriter methods = new PrintWriter(new BufferedWriter(new FileWriter("build\\methods.h")));
        methods.append("void turn(turnType dir) { Drivetrain.turnFor(dir, 90, degrees); }\r\n"
        		+ "void drive(double dist) { Drivetrain.driveFor(f, dist, i); }\r\n"
        		+ "void println(const char *args) {\r\n"
        		+ "  Brain.Screen.print(args);\r\n"
        		+ "  Brain.Screen.newLine();\r\n"
        		+ "}\r\n"
        		+ "void driveFor(double distance, distanceUnits units, double velocity, velocityUnits units_v){\r\n"
        		+ "  Drivetrain.driveFor(distance, units, velocity, units_v);\r\n"
        		+ "}\r\n"
        		+ "void driveFor(directionType direction, double distance, distanceUnits units) {\r\n"
        		+ "  Drivetrain.driveFor(direction, distance, units);\r\n"
        		+ "}\r\n"
        		+ "void driveFor(double distance, distanceUnits units, double velocity){\r\n"
        		+ "  Drivetrain.driveFor(distance, units, velocity);\r\n"
        		+ "}\r\n"
        		+ "void printALL(const char* message){\r\n"
        		+ "  Brain.Screen.print(message);\r\n"
        		+ "  Brain.Screen.newLine();\r\n"
        		+ "  Controller1.Screen.print(message);\r\n"
        		+ "  Controller1.Screen.newLine();\r\n"
        		+ "}");
        vex.append("/*----------------------------------------------------------------------------*/\r\n"
        		+ "/*                                                                            */\r\n"
        		+ "/*    Module:       vex.h                                                     */\r\n"
        		+ "/*    Author:       Vex Robotics                                              */\r\n"
        		+ "/*    Created:      1 Feb 2019                                                */\r\n"
        		+ "/*    Description:  Default header for V5 projects                            */\r\n"
        		+ "/*                                                                            */\r\n"
        		+ "/*----------------------------------------------------------------------------*/\r\n"
        		+ "//\r\n"
        		+ "#include <math.h>\r\n"
        		+ "#include <stdio.h>\r\n"
        		+ "#include <stdlib.h>\r\n"
        		+ "#include <string.h>\r\n"
        		+ "\r\n"
        		+ "#include \"v5.h\"\r\n"
        		+ "#include \"v5_vcs.h\"\r\n"
        		+ "\r\n"
        		+ "#include \"robot-config.h\"\r\n"
        		+ "\r\n"
        		+ "#define waitUntil(condition)                                                   \\\r\n"
        		+ "  do {                                                                         \\\r\n"
        		+ "    wait(5, msec);                                                             \\\r\n"
        		+ "  } while (!(condition))\r\n"
        		+ "\r\n"
        		+ "#define repeat(iterations)                                                     \\\r\n"
        		+ "  for (int iterator = 0; iterator < iterations; iterator++)");
        out.append(template);
        vex.flush();
        out.flush();
    }
    public static void append(String toBeAppended, boolean last, PrintWriter pw) {
    	if(last) {
    		pw.append(toBeAppended);
    		pw.append('}');
            pw.flush();
            pw.close();
    	}
    }
    public static void appendToCpp(String toBeAppended, PrintWriter pw) {
    		pw.append(toBeAppended);
    		pw.flush();
    		
    }
    public static String getFileData() {
    	    
        try {
			BufferedReader br = new BufferedReader(new FileReader("build\\Autonomous.cpp"));
			String fileData = "";
			while(br.ready()) {
				fileData += br.readLine() + "\n";
				AmountOflines++;
				if(fileData.contains("#include") && !fileData.contains("using")) {
					include = true;
				}
				else {
					include = false;
				}
			}
			return fileData;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    
    
   
    
}
