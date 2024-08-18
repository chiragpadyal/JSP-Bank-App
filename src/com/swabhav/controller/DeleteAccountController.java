package com.swabhav.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.security.auth.login.AccountException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swabhav.operations.AccountOperations;

/**
 * Servlet implementation class DeleteAccountController
 */
@WebServlet("/delete-account")
public class DeleteAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountOperations accountOps = new AccountOperations();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendError(404);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer accountId = null;
		if(request.getParameter("id") != null) {
			accountId = Integer.parseInt(request.getParameter("id"));
		}
		
		try {
			if(accountId == null) throw new AccountException("account can't be null");
			accountOps.delete(accountId);
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().write("Account Not Found");
		} catch (AccountException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().write("Account Not Found");
		}
		
		
	}

}
