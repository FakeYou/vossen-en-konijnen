package program.view;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import program.Main;

public class LineGraph extends JPanel
{
	private static final long serialVersionUID = 1546220782559888898L;
	private DefaultCategoryDataset dataset;
	private ChartPanel chartPanel;
	
	public LineGraph()
	{
		super();
		
		dataset = new DefaultCategoryDataset();
		
		JFreeChart chart = ChartFactory.createLineChart(
				"", 
				"Steps", 
				"Aantal",
				dataset, 
				PlotOrientation.VERTICAL, 
				true, 
				true, 
				false
		);
		
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
			
			dataset.addValue((Number) entry.getValue(), entry.getKey().toString(), Main.simulator.step);
		}
		
		if(Main.simulator.step > 50 && false)
		{
			dataset.removeColumn(0);
		}
	}
}
