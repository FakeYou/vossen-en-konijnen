package ui;

import java.awt.*;
import javax.swing.*;

class View extends JFrame {
    //Constants
	
    //Components
    
    private Model m_model;
    
    /** Constructor */
    View(Model model) {
        //Set up the logic
        m_model = model;
        
        //Initialize components
        
        //Layout the components.      
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        
        //finalize layout
        this.pack();
        
        this.setTitle("Simple MVC");
        // The window closing event should probably be passed to the 
        // Controller in a real program, but this is a short example.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
      
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}