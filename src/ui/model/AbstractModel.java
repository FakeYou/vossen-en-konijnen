package ui.model;

import java.util.*;
import ui.view.*;

public abstract class AbstractModel 
{
	private List<AbstractView> views;
	
	public AbstractModel() 
	{
		views=new ArrayList<AbstractView>();
	}
	
	public void addView(AbstractView view) 
	{
		views.add(view);
	}
	
	public void notifyViews() 
	{
		for(AbstractView v: views) v.updateView();
	}
}