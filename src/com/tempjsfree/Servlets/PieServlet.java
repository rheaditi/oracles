package com.tempjsfree.Servlets;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by SG0226685 on 023, 23 Oct 2016.
 */
public class PieServlet extends HttpServlet {

    public static final boolean DEFAULT_LEGEND = true;
    public static final boolean DEFAULT_TOOLTIPS = true;
    public static final boolean DEFAULT_URLS = false;
    public static final String EXAMPLE_TITLE = "Pie Chart Example";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream out = response.getOutputStream(); /* Get the output stream from the response object */
        try {

            response.setContentType("image/png");
            Hashtable paramData = getQueryParams(request);
            DefaultPieDataset pieChartData = createPieDataset(paramData);
            JFreeChart pieChart = createPieChart(pieChartData, EXAMPLE_TITLE, DEFAULT_LEGEND, DEFAULT_TOOLTIPS, DEFAULT_URLS);
            ChartUtilities.writeChartAsPNG(out, pieChart, 500, 500);/* Write the data to the output stream */
        } catch (Exception e) {
            System.err.print(e.getMessage());
            response.sendError(400,e.getMessage());
        }
    }

    private Hashtable getQueryParams(HttpServletRequest request) throws Exception {
        Hashtable queryParams = new Hashtable<String, String[]>();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            double value;
            try {
                value = Double.parseDouble(paramValues[0]);
                queryParams.put(paramName, value);
            } catch (Exception e) {
                throw new Exception("Invalid quantity/value for pie chart section", null);
            }
        }
        return queryParams;
    }

    private JFreeChart createPieChart(PieDataset myServletPieChart, String title, boolean legend, boolean tooltips, boolean urls) {
        return ChartFactory.createPieChart(title, myServletPieChart, legend, tooltips, urls);
    }

    private DefaultPieDataset createPieDataset(Hashtable data) {
        DefaultPieDataset pieChart = new DefaultPieDataset();
        Enumeration keys = data.keys();
        while(keys.hasMoreElements()){
            String key = (String) keys.nextElement();
            pieChart.setValue(key, (double) data.get(key));
        }
        return pieChart;
    }
}

