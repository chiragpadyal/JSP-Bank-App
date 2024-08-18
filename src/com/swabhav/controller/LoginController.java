package com.swabhav.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swabhav.model.AdminRole;
import com.swabhav.model.User;
import com.swabhav.operations.CustomUserOperation;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomUserOperation customUserOperation = new CustomUserOperation();
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		User user = new User();
		user.setEmail(email);
		user.setPasswordHash(passwd);
		try {
			user = customUserOperation.validateCredentails(user);
		} catch (SQLException e) {
			user = null;
			e.printStackTrace();
		}
		if(user != null) {
			HttpSession session=request.getSession();
	        session.setAttribute("user",user);
	        if(user.getRole().getName().equals(new AdminRole().getName())) {
		        response.sendRedirect("admin");
				return;
	        }
	        response.sendRedirect("dashboard");
			return;
		}
		request.setAttribute("fail", "true");
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

}
