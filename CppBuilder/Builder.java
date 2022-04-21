package CppBuilder;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Builder {
	public static String filePath;
	public static int AmountOflines;
	public static boolean include = false;
    //https://help.vexcodingstudio.com/#pro
    public static void buildTemplate(String template) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath = "build\\Autonomous.cpp")));
        PrintWriter vex = new PrintWriter(new BufferedWriter(new FileWriter("build\\vex.h")));
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
		vex.close();
		out.close();

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
			br.close();
			return fileData;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }

    
    
   
    
}
