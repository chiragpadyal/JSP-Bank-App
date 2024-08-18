package com.swabhav.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swabhav.Logger;
import com.swabhav.model.Account;
import com.swabhav.model.CustomerRole;
import com.swabhav.model.Transaction;
import com.swabhav.model.User;
import com.swabhav.operations.CustomAccountOperation;
import com.swabhav.operations.CustomTransactionOperation;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomAccountOperation customAccountOperation = new CustomAccountOperation();
    CustomTransactionOperation customTransactionOperation = new CustomTransactionOperation();
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		// Check if the user object is not null
		if (user != null) {
			if(!user.getRole().getName().equals(new CustomerRole().getName())) {
				response.sendError(404);
				return;
			}
		    // Retrieve the user ID
		    int userId = user.getUserId();

		    // Now you can use the userId to get accounts
		    try {
				List<Account> accounts = customAccountOperation.getAccountsByUserId(userId);
				HttpSession session=request.getSession();

				List<List<Transaction>> allAccountsTransactions = new ArrayList<>();
		        
		        for (Account account : accounts) {
		        	List<Transaction> transactions = customTransactionOperation.getChildrenTransactions(account.getAccountID());					
		        	allAccountsTransactions.add(transactions);
		        }
		        
		        session.setAttribute("accounts",accounts);
		        session.setAttribute("numberOfAccounts", accounts.size());
		        session.setAttribute("transactions", allAccountsTransactions);
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("/WEB-INF/Dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
