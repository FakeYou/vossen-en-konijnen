package ui;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import ui.view.SimulatorView;

import hanze.vossen.Simulator;

public class Main
{
	public static Display display;
	public static UI ui;
	public static Shell shell;
	
	public static final String TITLE = "Vossen en konijnen 0.1";
	
	public static final int FIELD_WIDTH = 80;
	public static final int FIELD_HEIGHT = 60;
	
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;
	
	public static Simulator simulator;
	
	private static final int updateSpeed = 0;
	private static int ticksSinceLastUpdate = 0;
	
	public Main()
	{
		shell = new Shell(display);
		shell.setText(TITLE);
		shell.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

		FormLayout layout = new FormLayout();
		shell.setLayout(layout);
		
		UI.mainscreen(shell);
		
		shell.open();
		
		simulator = new Simulator(FIELD_WIDTH, FIELD_HEIGHT);

		long tick = System.nanoTime();
		
		while(!shell.isDisposed())
		{
			float delta = (System.nanoTime() - tick) / 1000000.0f;
			tick = System.nanoTime();
			
			ticksSinceLastUpdate += delta;
			
			if(ticksSinceLastUpdate > updateSpeed)
			{
				simulator.simulateOneStep();
	
				//GC gc = new GC(UI.canvas);
				//UI.canvas.redraw();
				//SimulatorView.g2dpaint(UI.canvas);
				//gc.dispose();
				
				ticksSinceLastUpdate = 0;
			}
			
			if(!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		display = new Display();
		new Main();
		display.dispose();
	}
}
