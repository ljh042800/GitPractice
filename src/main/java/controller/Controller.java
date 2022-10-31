package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MessagesDAO;
import MessageDTO.DTO;

@WebServlet("*.git")
public class Controller extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		
		try {
			if(uri.equals("/input.git")) {
				String name = request.getParameter("name");
				String msg = request.getParameter("msg");
				
				int result = MessagesDAO.getInstance().insert(name, msg);
				response.sendRedirect("/index.jsp");
				
			}else if(uri.equals("/outputForm.git")) {
				MessagesDAO dao = MessagesDAO.getInstance();
				
				List<DTO> list = dao.selectAll();
				request.setAttribute("list", list);
				
				request.getRequestDispatcher("/outputForm.jsp").forward(request, response);
				
			}
				
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
