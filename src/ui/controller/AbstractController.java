package ui.controller;

import ui.model.*;
import javax.swing.*;

public abstract class AbstractController extends JPanel 
{
	private static final long serialVersionUID = 4941730006940737729L;
	protected Model model;
	
	public AbstractController(Model model) 
	{
		this.model=model;
	}
}
