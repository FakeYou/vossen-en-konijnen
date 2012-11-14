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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.CategoryToPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.jfree.util.TableOrder;

import program.Vossen;

public class PieView extends JPanel
{
	private static final long serialVersionUID = 6549863187908510082L;
	private int i;
	private ChartPanel chartPanel;
	private DefaultCategoryDataset dataset;
	private PieDataset d;
	private CategoryToPieDataset piedataset;

	public PieView()
	{
		super();
		
		JFreeChart chart = ChartFactory.createPieChart(
				"",          			// chart title
	            piedataset, 
	            true,                   // include legend
	            true,
	            false
	    );
		
		/*PiePlot3D plot = (PiePlot3D) chart.getPlot();
		
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);*/
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));
        
        this.add(chartPanel);
        this.validate();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);		
		
		try
		{
			dataset = (DefaultCategoryDataset) Vossen.getStats().clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		
		piedataset = new CategoryToPieDataset(dataset, TableOrder.BY_ROW, dataset.getRowCount() - 1);
		
		chartPanel.revalidate();
	}
}
