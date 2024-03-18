package id.co.sinarmaslife.eproposal.report.jfree;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040201PerformanceDollarJfree
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 21, 2008 1:31:23 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.renderers.JCommonDrawableRenderer;

public class Cepr01040201PerformanceDollarJfree extends JRDefaultScriptlet
{
    /**
     *
     */
    public void afterReportInit() throws JRScriptletException
    {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart( dataset );

        /* */
        this.setVariableValue( "Chart", new JCommonDrawableRenderer( chart ) );
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private CategoryDataset createDataset()
    {

        // row keys...
        String series1 = "Excellink Secure Dollar";
        String series2 = "Excellink Dynamic Dollar";

        // column keys...
        String type1 = "6/30/2006";
        String type2 = "12/31/2006";
        String type3 = "6/30/2007";
        String type4 = "12/31/2007";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Excellink Secure Dollar
        dataset.addValue( 1.0061, series1, type1 );
        dataset.addValue( 1.0061, series1, type2 );
        dataset.addValue( 1.0067, series1, type3 );
        dataset.addValue( 1.0110, series1, type4 );

        // Excellink Dynamic Dollar
        dataset.addValue( 0.9884, series2, type1 );
        dataset.addValue( 1.0105, series2, type2 );
        dataset.addValue( 1.0332, series2, type3 );
        dataset.addValue( 1.0570, series2, type4 );

        return dataset;

    }
    

    /**
     * Creates a sample chart.
     *
     * @param dataset a dataset.
     * @return The chart.
     */
    private JFreeChart createChart( final CategoryDataset dataset )
    {

        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
                "KINERJA DOLLAR FUND",       // chart title
                "BULAN",                    // domain axis label
                "NAV",                   // range axis label
                dataset,                   // data
                PlotOrientation.VERTICAL,  // orientation
                true,                      // include legend
                true,                      // tooltips
                false                      // urls
        );

        chart.setBackgroundPaint( Color.white );

        CategoryPlot plot = ( CategoryPlot ) chart.getPlot();
        plot.setBackgroundPaint( Color.white );
        plot.setRangeGridlinePaint( Color.lightGray );

        CategoryAxis axis = plot.getDomainAxis();
        Font font  = new Font( "arial", Font.PLAIN, 8 );
        axis.setTickLabelFont( font );

        // customise the range axis...
        NumberAxis rangeAxis = ( NumberAxis ) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits( NumberAxis.createStandardTickUnits() );
        rangeAxis.setAutoRangeIncludesZero( false );

        // customise the renderer...
        LineAndShapeRenderer renderer = ( LineAndShapeRenderer ) plot.getRenderer();

        // if true then all have point like triagles, circle etc
        renderer.setSeriesShapesVisible( 0, true );
        renderer.setSeriesShapesVisible( 1, true );
        renderer.setSeriesShapesVisible( 2, true );

        return chart;
    }

}