package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class UI
{
	public static Canvas canvas;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void mainscreen(Shell shell)
	{
		Button btnStartSimulatie = new Button(shell, SWT.NONE);
		FormData fd_btnStartSimulatie = new FormData();
		fd_btnStartSimulatie.left = new FormAttachment(0, 10);
		fd_btnStartSimulatie.top = new FormAttachment(0, 10);
		fd_btnStartSimulatie.right = new FormAttachment(0, 100);
		btnStartSimulatie.setLayoutData(fd_btnStartSimulatie);
		btnStartSimulatie.setText("Start simulatie");
		
		canvas = new Canvas(shell, SWT.BORDER);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_canvas = new FormData();
		fd_canvas.top = new FormAttachment(0, 10);
		fd_canvas.left = new FormAttachment(btnStartSimulatie, 6, SWT.RIGHT);
		fd_canvas.bottom = new FormAttachment(100, -10);
		fd_canvas.right = new FormAttachment(100, -10);
		canvas.setLayoutData(fd_canvas);
	}
}
