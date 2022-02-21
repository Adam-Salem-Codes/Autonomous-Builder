package Display;


import java.awt.event.WindowEvent;
import CppBuilder.Builder;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Display {
    public static JFrame display;
    public static boolean WindowClosed;
    public static boolean isClosing;
    public static boolean isActiveWindow;
    public static boolean windowOpened;
    public static final int DO_NOTHING_ON_CLOSE = 0;
    public static final int HIDE_ON_CLOSE = 1;
    public static final int DISPOSE_ON_CLOSE = 2;
    public static final int EXIT_ON_CLOSE = 3;

    public Display(String args) {
    	 
        display = new JFrame(args);
        display.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                windowOpened = true;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            	isClosing = true;
            	 PrintWriter out = null;
         		try {
         			out = new PrintWriter(new BufferedWriter(new FileWriter("build\\Autonomous.cpp")));
                	Builder.append("", true, out);
         		} catch (IOException e1) {
         			e1.printStackTrace();
         		}
                e.getWindow().dispose();           
            }

            @Override
            public void windowClosed(WindowEvent e) {
                    WindowClosed = true;
            }

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {
                    isActiveWindow = true;
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                    isActiveWindow = false;                
            }
        });
    }
    public Display()  {
        display = new JFrame();
        PrintWriter out;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter("build\\Autonomous.cpp")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        display.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                windowOpened = true;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            	isClosing = true;
            	PrintWriter out = null;
         		try {
         			out = new PrintWriter(new BufferedWriter(new FileWriter("build\\Autonomous.cpp", true)));
                	Builder.append("", true, out);
         		} catch (IOException e1) {
         			e1.printStackTrace();
         		}
                e.getWindow().dispose();           
            }

            @Override
            public void windowClosed(WindowEvent e) {
                    WindowClosed = true;
            }

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {
                    isActiveWindow = true;
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                    isActiveWindow = false;                
            }
        });
    }
    public void initDisplay() {
        setExitOnClose(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    public void pack() {display.pack();}
    public void setName(String name) {display.setName(name);}
    public String getName() {return display.getName();}
    public void setVisible(boolean args) {display.setVisible(args);}
    public void setExitOnClose(int status) {display.setDefaultCloseOperation(status);}
    public static void exit() {System.exit(1);}
    public void exitDisplay() {display.dispose();}
    public void add(Component args) {
    	display.add(args);
    }
    public void remove(Component args) {display.remove(args);}
    public void setSize(Dimension d){display.setSize(d);}
    public void setLayout(LayoutManager manager) {display.getContentPane().setLayout(manager);}
    public void paint(Graphics g) {
        if (!display.isOpaque()) {
            Graphics gg = g.create();
            try {
                if (gg instanceof Graphics2D) {
                    gg.setColor(display.getBackground());
                    ((Graphics2D)gg).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
                    gg.fillRect(0, 0, display.getWidth(), display.getHeight());
                }
            } finally {
                gg.dispose();
            }
        }
    }
}