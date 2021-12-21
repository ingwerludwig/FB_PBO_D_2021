package com.main;

import java.awt.CardLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.panel.GamePanel;
import com.panel.HomePanel;
import com.panel.EndPanel;

public class Play extends JFrame{
	private static final long serialVersionUID = 1L;
    private HomePanel panel1;
    private GamePanel panel2;
    private EndPanel panel3;
    
    private void displayGUI(){
        JFrame frame = new JFrame("Stress Ball Game");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(
        BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());
        panel1 = new HomePanel(contentPane);
        panel2 = new GamePanel(contentPane);
        panel3 = new EndPanel(contentPane);
        contentPane.add(panel1, "Panel 1");
//        contentPane.add(panel2, "Panel 2");
//        contentPane.add(panel3, "Panel 3");
        frame.setContentPane(contentPane);
        frame.pack();   
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public static void main(String... args){
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Play().displayGUI();
            }
        });
    }
    
}