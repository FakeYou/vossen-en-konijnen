package program.view;

import java.awt.BorderLayout;
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

import program.Vossen;

public class LineView extends JPanel
{
	private static final long serialVersionUID = 1546220782559888898L;
	private ChartPanel chartPanel;
	private DefaultCategoryDataset dataset;
	
	public LineView()
	{
		super();
		
		dataset = Vossen.getStats();
		
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
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));
        
        this.add(chartPanel);
        this.validate();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);		
		dataset = Vossen.getStats();
		chartPanel.revalidate();
	}
}
