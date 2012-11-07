package ui;

//Handles user interaction with listeners.
//Calls View and Model as needed.

public class Controller {
    //The Controller needs to interact with both the Model and View.
    private Model m_model;
    private View  m_view;
    
    /** Constructor */
    Controller(Model model, View view) {
        m_model = model;
        m_view  = view;
        
        //Add listeners to the view.
    }
}