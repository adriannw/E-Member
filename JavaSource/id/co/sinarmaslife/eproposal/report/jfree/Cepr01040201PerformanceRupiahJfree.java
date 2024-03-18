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
 * Program Name   		: Cepr01040201PerformanceRupiahJfree
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 17, 2008 3:46:18 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.renderers.JCommonDrawableRenderer;


public class Cepr01040201PerformanceRupiahJfree extends JRDefaultScriptlet
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

        String series2 = "Fix Income";
        String series3 = "Dynamic Income";
        String series1 = "Aggressive";

        // column keys...
        String type1 = "6/30/2003";
        String type2 = "12/31/2003";
        String type3 = "6/30/2004";
        String type4 = "12/31/2004";
        String type5 = "6/30/2005";
        String type6 = "12/31/2005";
        String type7 = "6/30/2006";
        String type8 = "12/31/2006";
        String type9 = "6/30/2007";
        String type10 = "12/31/2007";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //            Aggressive Income
        dataset.addValue( null, series1, type1 );
        dataset.addValue( null, series1, type2 );
        dataset.addValue( 990.790, series1, type3 );
        dataset.addValue( 1096.913, series1, type4 );
        dataset.addValue( 1205.963, series1, type5 );
        dataset.addValue( 1168.788, series1, type6 );
        dataset.addValue( 1295.941, series1, type7 );
        dataset.addValue( 1669.797, series1, type8 );
        dataset.addValue( 1826.123, series1, type9 );
        dataset.addValue( 2216.210, series1, type10 );

        // Fix Income
        dataset.addValue( 1036.788, series2, type1 );
        dataset.addValue( 1099.750, series2, type2 );
        dataset.addValue( 1153.091, series2, type3 );
        dataset.addValue( 1195.838, series2, type4 );
        dataset.addValue( 1234.977, series2, type5 );
        dataset.addValue( 1279.058, series2, type6 );
        dataset.addValue( 1340.594, series2, type7 );
        dataset.addValue( 1406.471, series2, type8 );
        dataset.addValue( 1536.630, series2, type9 );
        dataset.addValue( 1610.010, series2, type10 );

//            Dynamic Income
        dataset.addValue( null, series1, type1 );
        dataset.addValue( 1108.036, series3, type2 );
        dataset.addValue( 1141.937, series3, type3 );
        dataset.addValue( 1291.235, series3, type4 );
        dataset.addValue( 1399.706, series3, type5 );
        dataset.addValue( 1433.524, series3, type6 );
        dataset.addValue( 1512.006, series3, type7 );
        dataset.addValue( 1729.068, series3, type8 );
        dataset.addValue( 1919.584, series3, type9 );
        dataset.addValue( 2197.030, series3, type10 );


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
                "KINERJA RUPIAH FUND",       // chart title
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
        axis.setMaximumCategoryLabelWidthRatio( 4 );

        // customise the range axis...
        NumberAxis rangeAxis = ( NumberAxis ) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits( NumberAxis.createStandardTickUnits() );
        rangeAxis.setAutoRangeIncludesZero( true );

        // customise the renderer...
        LineAndShapeRenderer renderer = ( LineAndShapeRenderer ) plot.getRenderer();

        // if true then all have point like triagles, circle etc
        renderer.setSeriesShapesVisible( 0, true );
        renderer.setSeriesShapesVisible( 1, true );
        renderer.setSeriesShapesVisible( 2, true );

        return chart;
    }

}