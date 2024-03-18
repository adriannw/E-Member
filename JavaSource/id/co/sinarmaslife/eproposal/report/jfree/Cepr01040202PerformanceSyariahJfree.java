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
 * Program Name   		: Cepr01040202PerformanceSyariahJfree
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 25, 2008 2:52:39 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.renderers.JCommonDrawableRenderer;

public class Cepr01040202PerformanceSyariahJfree extends JRDefaultScriptlet
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

        String series1 = "Aggressive";
        String series2 = "Dynamic Income";

        // column keys...
        String type1 = "6/30/2006";
        String type2 = "12/31/2006";
        String type3 = "6/30/2007";
        String type4 = "12/31/2007";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Aggressive
        dataset.addValue( null, series1, type1 );
        dataset.addValue( null, series1, type2 );
        dataset.addValue( 1012.2250, series1, type3 );
        dataset.addValue( 1135.4230, series1, type4 );

        // Dynamic Income
        dataset.addValue( 1003.5346, series2, type1 );
        dataset.addValue( 1014.1646, series2, type2 );
        dataset.addValue( 1079.2265, series2, type3 );
        dataset.addValue( 1306.2800, series2, type4 );

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
                "KINERJA SYARIAH FUND",       // chart title
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
