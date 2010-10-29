package org.n0pe.mojo.examples.war;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DumbServlet
        extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter out = response.getWriter();
        try {
            out.println( "<html>" );
            out.println( "<head>" );
            out.println( "<title>Servlet DumbServlet</title>" );
            out.println( "</head>" );
            out.println( "<body>" );
            out.println( "<h1>Servlet DumbServlet at " + request.getContextPath() + "</h1>" );
            out.println( "<p>Random Integer: " + new Random().nextInt() + "</p>" );
            out.println( "</body>" );
            out.println( "</html>" );
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        response.sendError( response.SC_FORBIDDEN );
    }

}
