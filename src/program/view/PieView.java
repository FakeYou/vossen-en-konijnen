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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.CategoryToPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.jfree.util.TableOrder;

import program.Vossen;

public class PieView extends JPanel
{
	private static final long serialVersionUID = 6549863187908510082L;
	private ChartPanel chartPanel;
	private DefaultCategoryDataset dataset;
	private SlidingCategoryDataset slide;
	private JFreeChart chart;

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
	
	public void paint(Graphics g)
	{
		super.paint(g);		
		chart.setNotify(true);
		dataset = Vossen.getSingleStats();
		chart.setNotify(false);
	}
}
