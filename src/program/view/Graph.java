package program.view;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import program.Main;

public class Graph extends JPanel
{
	private static final long serialVersionUID = 6549863187908510082L;
	private DefaultPieDataset dataset;
	private int i;
	private ChartPanel chartPanel;

	public Graph()
	{
		super();
		
		dataset = new DefaultPieDataset();

		JFreeChart chart = ChartFactory.createPieChart3D(
				"",          			// chart title
	            dataset,                // data
	            true,                   // include legend
	            true,
	            false
	    );
		
		/*PiePlot3D plot = (PiePlot3D) chart.getPlot();
		
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);*/
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(409, 278));
        
        this.add(chartPanel);
        this.validate();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		HashMap<String, Integer> count = Main.simulator.countEntities();
		
		Iterator<Entry<String, Integer>> it = count.entrySet().iterator();
		
		while(it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			
			dataset.setValue(entry.getKey().toString(), (Number) entry.getValue());
		}
	}
}
