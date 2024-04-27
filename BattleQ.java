package battlebackend;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findbattle")
public class BattleQ extends HttpServlet {
	public Object lock = new Object();
	public int connections  = 0;
	

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    	resp.addHeader("Access-Control-Allow-Origin", "*");
    	System.out.println("here");
    	if (connections < 2) {
    		try {
    			lock.wait();
    		} catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
    		}
        }
    	else {
    		lock.notifyAll();
    		connections = 0;
    		 try {
                 resp.setContentType("text/plain");
                 resp.getWriter().write("Starting Battle...");
             } catch (IOException e) {
                 e.printStackTrace();
             }
    	}
    }

}
