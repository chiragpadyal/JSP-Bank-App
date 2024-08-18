package com.swabhav.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swabhav.exception.AccountException;
import com.swabhav.model.Account;
import com.swabhav.model.Transaction;
import com.swabhav.model.Transfer;
import com.swabhav.model.User;
import com.swabhav.operations.CustomAccountOperation;
import com.swabhav.operations.Operations;
import com.swabhav.operations.TransactionOperation;
import com.swabhav.operations.UserOperations;

/**
 * Servlet implementation class AddCustomerController
 */
@WebServlet("/create-account")
public class AddAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CustomAccountOperation accountOperations = new CustomAccountOperation();
    private Operations<Transaction> transactionOperations = new TransactionOperation();
    private Operations<User> userOperation = new UserOperations();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forUserId = request.getParameter("foruserid");
		String accountHolderName = request.getParameter("accountholdername");
		Account account = null;
		User user = null;
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		try {
			
			user = userOperation.get(Integer.parseInt(forUserId));
			
			account = new Account();
			account.setAccountName(accountHolderName);
			account.setAccountNumber(account.randomAccountNumber());
			account.setBalance(0d);
			account.setUser(user);
			
			accountOperations.add(account);
			response.sendRedirect("admin");
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().write("SQL ERROR");
			e.printStackTrace();
		} catch (AccountException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().write(e.getMessage());
		}
		
	}
}
