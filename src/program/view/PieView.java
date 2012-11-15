package program.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;
import org.jfree.util.TableOrder;

import program.Vossen;

/**
 * draws a piechart with the short term simulation data, providing a quick view in the ratios of the entities
 * 
 * @author FakeYou
 * @version 2012-11-15
 */
public class PieView extends JPanel
{
	private static final long serialVersionUID = 6549863187908510082L;
	private ChartPanel chartPanel;
	private DefaultCategoryDataset dataset;
	private SlidingCategoryDataset slide;
	private JFreeChart chart;
	
	/**
	 * Constructor, make a piechart from the JFreeChart library and assign it the short term simulator data
	 */
	public PieView()
	{
		super();
		
		dataset = Vossen.getSingleStats();
		
		chart = ChartFactory.createMultiplePieChart(
			"",
			dataset,
            TableOrder.BY_COLUMN, 
            true,
            true,
            false
	    );
        
		chart.setNotify(false);
		
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));
        
        this.add(chartPanel);
        this.validate();
	}
	
	/**
	 * updates the dataset used in the piechart
	 * 
	 * @param g Graphics opbject
	 */	
	public void paint(Graphics g)
	{
		super.paint(g);		
		chart.setNotify(true);
		dataset = Vossen.getSingleStats();
		chart.setNotify(false);
	}
}
