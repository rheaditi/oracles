package com.tempjsfree.Servlets;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by SG0226685 on 023, 23 Oct 2016.
 */
public class PieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream out = response.getOutputStream(); /* Get the output stream from the response object */
        try {
            DefaultPieDataset myServletPieChart = new DefaultPieDataset();
            myServletPieChart.setValue("Maths", 74);
            myServletPieChart.setValue("Physics", 87);
            myServletPieChart.setValue("Chemistry", 62);
            myServletPieChart.setValue("Biology", 92);
            myServletPieChart.setValue("English", 51);
            JFreeChart mychart = ChartFactory.createPieChart("Programming - Colored Pie Chart Example", myServletPieChart, true, true, false);
            response.setContentType("image/png"); /* Set the HTTP Response Type */
            ChartUtilities.writeChartAsPNG(out, mychart, 400, 300);/* Write the data to the output stream */
        } catch (Exception e) {
            System.err.println(e.toString()); /* Throw exceptions to log files */
        } finally {
            out.close();/* Close the output stream */
        }
    }
}

