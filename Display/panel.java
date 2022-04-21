package Display;
import CppBuilder.Builder;
import CppBuilder.turnType;
import CppEditor.KeyboardTestApp;
import main.Main;

import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.geom.AffineTransform;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
@SuppressWarnings("unused")
public class panel extends JPanel {
	JLabel balance = new JLabel("balance board");
	boolean nintey;
	int i = 0;

	public static final int[] field = {
			Display.frame.getWidth() / 4, // X
			Display.frame.getHeight() / 4, // Y
			Display.frame.getWidth() / 2, // WIDTH
			Display.frame.getHeight() / 2 // HEIGHT
	};
	public panel(){
		this.setPreferredSize(new Dimension(Display.frame.getWidth(), Display.frame.getHeight()));
		this.add(Main.Comp);
		this.add(Main.Skills);
		this.add(Main.welcome);
		createUI();
		
	}
	public void createUI() {
		JButton Exit = new JButton("Exit Program");
		Exit.setPreferredSize(new Dimension(200, 100));
		this.add(Exit);
		Exit.addActionListener(e -> {
			try {
				addCpp("", true);
			    JOptionPane.showMessageDialog(Display.frame, "C++ program has been created in"
			    		+ " the build folder. Exiting");
				Display.exit();
				System.exit(0);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		JButton forward = new JButton("Move Drivetrain ");
		forward.setPreferredSize(new Dimension(200, 100));
		this.add(forward);
		forward.addActionListener(e -> {
			try {
				String z = JOptionPane.showInputDialog(this, "Enter direction(forward, backwards (same thing), forward):");  
				String x = JOptionPane.showInputDialog(this,"Enter Amount(Feet): ");  
				double y = Double.parseDouble(x);
				y /= 0.0833;
				switch(z) {
					case "reverse":
						addCpp("\n Drivetrain.driveFor(reverse,"+y+", i);\n", false);
						break;

					case "backwards":
						addCpp("\n Drivetrain.driveFor(reverse,"+y+", i);\n", false);
						break;
					
					case "forward":
						addCpp("\n Drivetrain.driveFor(forward,"+y+", i);\n", false);
						break;
				}
				
				
				paintLine(this.getGraphics(), (int)Math.round(y), turnType.forward,field);
				i++;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		JButton turn = new JButton("Turn Drivetrain");
		turn.setPreferredSize(new Dimension(200, 100));
		this.add(turn);
		turn.addActionListener(e -> {
			try {
				
				String x = JOptionPane.showInputDialog(this,"Enter Turn (degrees): ");  
				double y = Double.parseDouble(x);
				
				if(y == 90)
					nintey = true;
				else
					nintey = false;
				addCpp("\n Drivetrain.turnFor("+y+", degrees);\n", false);
				paintLine(this.getGraphics(), (int)Math.round(y), turnType.forward,field);		
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		JButton button = new JButton("open C++ editor");
		this.add(button);
		button.addActionListener(e -> {
			CppEditor();
		});
	}
	public void CppEditor() {
		KeyboardTestApp cppEditor = new KeyboardTestApp();	
		try {
			cppEditor.KeyBoardTestApp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void addCpp(String toAdd, boolean last) throws IOException {
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("build\\Autonomous.cpp", true)));
		Builder.appendToCpp(toAdd, out);
		if(last)
			Builder.appendToCpp("}", out);
	}
	 @Override
	    public void paint(Graphics g) {
	    		Graphics2D g2D = (Graphics2D) g;
	    		
	    		
	    		g2D.setFont(new Font("Dosis", Font.BOLD, 20));
	    		g2D.drawString("Vex V5 2021-2022 Tipping point Field",
	    				field[0] + 20,
	    				field[1] + 20
	    		);
	    		g2D.drawString("12350B Gear Grinders",
	    				field[0] ,
	    				field[1] 
	    		);
	    		g2D.setFont(new Font("Dosis", Font.BOLD, 15));

	    		
	    		
	    		g2D.drawRect(
	    			field[0],
	    			field[1],
	    			field[2],
	    			field[3]
	    			);
	    		g2D.setPaint(new Color(255,255,204));
	    		g2D.fillOval(
	    				field[0] * 2 +- 25,
	    				field[1] * 2 +- 25,//Middle
	    				field[2] / 10,
	    				field[3] / 10);
	    		g2D.setPaint(Color.gray);
	    		g2D.fillOval(
	    				field[0] * 2 +- 25  / 3,
	    				field[1] * 2 +- 25 / 3,//Middle
	    				field[2] / 35,
	    				field[3] / 35);
	    		g2D.setPaint(new Color(255,255,204));
	    		//
	    		g2D.fillOval(
	    				field[0] * 2 +- 25,
	    				field[1] * 2 + 140,//Middle
	    				field[2] / 10,
	    				field[3] / 10);
	    		g2D.setPaint(Color.gray);
	    		g2D.fillOval(
	    				field[0] * 2 +- 25 / 3 + 3,
	    				field[1] * 2 + 140 + 23,//Middle
	    				field[2] / 35,
	    				field[3] / 35);
	    		g2D.setPaint(new Color(255,255,204));
	    		//
	    		g2D.fillOval(
	    				field[0] * 2 +- 25,
	    				field[1] / 2 + 220,//Middle
	    				field[2] / 10,
	    				field[3] / 10);
	    		g2D.setPaint(Color.gray);
	    		g2D.fillOval(
	    				field[0] * 2 +- 25 / 2 + 6,
	    				field[1] / 2 + 235,//Middle
	    				field[2] / 35,
	    				field[3] / 35);
	    		int offset = 25;
	    		g2D.setPaint(Color.red);
	    			g2D.drawRect(
		    				field[0] + offset,
		    				field[0] * 2 - 50 / 2,
		    				field[0] / 3 ,
		    				field[0] / 2);
	    			g2D.drawString("Balance Board", field[0] + offset, field[0] * 2 + 65 / 2);
		    		g2D.setPaint(Color.blue);
	    			g2D.drawRect(
		    				field[0] * 3 - offset * 5 + 15,
		    				field[0] * 2 - 50 / 2,
		    				field[0] / 3 ,
		    				field[0] / 2);	
	    			g2D.drawString("Balance Board",
	    					field[0] * 3 - offset * 5 + 15,
	    					field[0] * 2 - 50 / 6);
		    		g2D.setPaint(Color.blue);
	    			
	    			g2D.fillOval(
	    					field[0] * 3 - offset * 5 + 25,
		    				field[0] * 2 + 60,
		    				field[0] / 5,
		    				field[0] / 5);
	    			g2D.setPaint(Color.gray);
		    		g2D.fillOval(
		    				field[0] * 3 - offset * 5 + 45,
		    				field[1] * 2 + 70,
		    				field[2] / 35,
		    				field[3] / 35);
		    		g2D.setPaint(Color.red);
		    		g2D.fillOval(
	    					field[0] / 3 - offset / 5 + 25 * 10 - 12,
		    				field[0] * 2 - 50,
		    				field[0] / 5,
		    				field[0] / 5);
	    			g2D.setPaint(Color.gray);
		    		g2D.fillOval(
		    				field[0] / 3 - offset / 5 + 45 * 6 - 12,
		    				field[1] * 2 - 45,
		    				field[2] / 35,
		    				field[3] / 35);
		    		g2D.setPaint(Color.decode("#5D3FD3"));
		    		g2D.fillOval(
		    				field[0] * 2 - 5,
		    				field[1] * 2 + 35,
		    				field[2] / 35,
		    				field[3] / 35);
		    		g2D.fillOval(
		    				field[0] * 2 - 5,
		    				field[1] * 2 + 70,
		    				field[2] / 35,
		    				field[3] / 35);
		    		g2D.fillOval(
		    				field[0] * 2- 5,
		    				field[1] * 2 + 105,
		    				field[2] / 35,
		    				field[3] / 35);
		    		//Middle * * - *
		    		g2D.fillOval(
		    				field[0] * 2 - 5,
		    				field[1] * 2 - 50,
		    				field[2] / 35,
		    				field[3] / 35);
		    		g2D.fillOval(
		    				field[0] * 2 - 5,
		    				field[1] * 2 - 85,
		    				field[2] / 35,
		    				field[3] / 35);
		    		g2D.fillOval(
		    				field[0] * 2- 5,
		    				field[1] * 2 - 120,
		    				field[2] / 35,
		    				field[3] / 35);
		    		
	    		balance.setAlignmentX(field[0] + offset);
	    		balance.setAlignmentY(field[0] * 2 - 50 /2);
	    }
	 
	 public void paintLine(Graphics g, int dist, turnType t, int[] field) {
		 Graphics2D g2D = (Graphics2D) g;
		 int v = 0;
		 
		 int[] startingPos = {
				field[0],
				field[1] * 3 - 12 
		 };
		 if(!t.equals(turnType.forward)) {
			 //code
			 return;
		 }

		
			//draw shape/image (will be rotated)
		AffineTransform old = g2D.getTransform();		 
		 if(i >= 1) {
			String x = JOptionPane.showInputDialog(this,"How much do you want to go forward after turn?(feet): ");  
			double y = Double.parseDouble(x);
			y /= 0.0833;
			System.out.print("nintey");
			g2D.rotate(Math.toRadians((dist * 4 - 36)));
			g2D.drawLine(
			field[0] + (dist * 4 - 36),
			startingPos[1],
			startingPos[1] + (int)Math.round(y),
			field[0] + (dist * 4 - 36)
			);
			v++;
			return;
		 }
		 g2D.setTransform(old);
		 g2D.drawLine(
			field[0],
			startingPos[1],
			field[0] + (dist * 4 - 36),
			startingPos[1]);
		
		
	 		 
		 System.out.println(startingPos[0]);
		 v++;
	 }
	 
	

}
