package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import CppBuilder.Builder;
import Display.Display;
import Display.panel;

public class main {
    static final String cppTemplate = "#include \"vex.h\"\n #include \"methods.h\" \n using namespace vex; \n int main() {\nvexcodeInit(); ";
    public static boolean skillsComp;
    public static Display display = new Display();
    public static JButton Skills = new JButton("Skills"); 
    public static JButton Comp = new JButton("Competition");
    public static JLabel welcome = new JLabel("Welcome to the Vex V5 Autonomous Builder");
    
    public static void main(String[] args) {
        try {
        	display.initDisplay();
            display.setSize(new Dimension(1080, 1920));
            
            display.display.getContentPane().setBackground(Color.decode("#1d1d1d"));

      
            
            // Skills comp = true means skills false = comp
            Comp.addActionListener(e -> {
                setSkillsComp(false);
                System.out.println(getSkillsComp());
            });
            Skills.addActionListener(e -> {
                setSkillsComp(true);
                 System.out.println(getSkillsComp());
                
            });
            Skills.setPreferredSize(new Dimension(100,100));
            Comp.setPreferredSize(new Dimension(125,100)); 
            Builder.buildTemplate(cppTemplate);
            display.add(new panel());

       
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		public static boolean getSkillsComp(){
			return skillsComp;
		}
		public static void setSkillsComp(boolean b) {
			skillsComp = b;
		}
}