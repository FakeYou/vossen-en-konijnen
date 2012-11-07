package ui;

public class Main {

	public Main() {
		
	}
	
	//Create model, view, and controller.  They are
    //created once here and passed to the parts that
    //need them so there is only one copy of each.
	public static void main(String[] args) {
		
        Model      model      = new Model();
        View       view       = new View(model);
        Controller controller = new Controller(model, view);
        
        view.setVisible(true);
	}
}