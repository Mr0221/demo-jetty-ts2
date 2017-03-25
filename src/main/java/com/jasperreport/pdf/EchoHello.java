package com.jasperreport.pdf;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class EchoHello extends HttpServlet {

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
         try {
            final ServletOutputStream servletOutputStream = response.getOutputStream();
            final InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream("report1.jasper");
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,new HashMap(), new JREmptyDataSource());
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (final JRException e) {
            e.printStackTrace();
        }
    }

}
