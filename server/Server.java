package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Server extends HttpServlet {
	private String requestName = null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        validateRequestName(request, response);
        PrintWriter writer = response.getWriter();
        
        writer.println("GET has received: " + requestName); // FIXME delete
    }
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		validateRequestName(request, response);
	}

	private void validateRequestName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestName = request.getParameter("requestName");
		if (requestName == null) {
			response.getWriter().println("ERROR: Parameter 'requestName' not found!");
			return;
		}
	}
	
	// FIXME Delete this
    private String writeTableEntry(HttpServletRequest request) {
    	String s = parWrap("Query string: " + request.getQueryString())
    	+ parWrap("request URI: " + request.getRequestURI())
    	+ parWrap("request URL: " + request.getRequestURL())
    	+ parWrap("---Parameter set: ---");
    	for (Entry<String, String[]> params : request.getParameterMap().entrySet()) {
    		String r = params.getKey() + ": ";
    		for (String param : params.getValue()) {
    			r += param + ", ";
    		}
    		
    		s += parWrap(r);
    	}
    					
    	s += parWrap("-----------");
    	
    	return s;
    }

    // FIXME delete this
	private String parWrap(String s) {
    	return "<p>" + s + "</p>\n";
    }
}
