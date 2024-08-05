package step01;

import java.io.IOException;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//web.xml
//http://127.0.0.1:8080/step11_web/fisa
public class FirstServlet extends HttpServlet {
	//실행되는 현 클래스에 로그 기록 적용
	Logger logger = Logger.getLogger(getClass());
   
    public FirstServlet() {
    	logger.debug("FirstServlet debug() ***");
    	logger.info("FirstServlet info() ***");
    	logger.warn("FirstServlet warn() ***");
    	logger.error("FirstServlet error() ***");
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("FirstServlet doGet debug() ***");
    	logger.info("FirstServlet doGet info() ***");
    	logger.warn("FirstServlet doGet warn() ***");
    	logger.error("FirstServlet doGet error() ***");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("FirstServlet doPost debug() ***");
    	logger.info("FirstServlet doPost info() ***");
    	logger.warn("FirstServlet doPost warn() ***");
    	logger.error("FirstServlet doPost error() ***");
	}

}
