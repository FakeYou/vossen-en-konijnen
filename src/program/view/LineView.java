package program.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import program.Vossen;

/**
 * draws a line with the line term simulation data, providing a quick view in trends of the simulation
 * 
 * @author FakeYou
 * @version 2012-11-15
 */
public class LineView extends JPanel
{
	private static final long serialVersionUID = 1546220782559888898L;
	private ChartPanel chartPanel;
	private DefaultCategoryDataset dataset;
	
	/**
	 * Constructor, make a linechart from the JFreeChart library and assign it the long term simulator data
	 */
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
	
	/**
	 * updates the dataset used in the linechart
	 * 
	 * @param g Graphics opbject
	 */
	public void paint(Graphics g)
	{
		super.paint(g);		
		dataset = Vossen.getStats();
		chartPanel.revalidate();
	}
}
