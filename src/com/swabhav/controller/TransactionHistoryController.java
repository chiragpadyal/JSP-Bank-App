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

import com.swabhav.Logger;
import com.swabhav.model.Account;
import com.swabhav.model.CustomerRole;
import com.swabhav.model.Transaction;
import com.swabhav.model.User;
import com.swabhav.operations.CustomTransactionOperation;

/**
 * Servlet implementation class TransactionHistoryController
 */
@WebServlet("/transactions")
public class TransactionHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CustomTransactionOperation customTransactionOperation = new CustomTransactionOperation();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionHistoryController() {
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
			List<Account> accounts = (List<Account>) request.getSession().getAttribute("accounts");
			Logger.getInstance().log("accounts" + accounts);
			int id = 0;
			int pageNumber = 0;
			if(request.getParameter("id") != null) id = Integer.parseInt(request.getParameter("id"));
			if(request.getParameter("page") != null) pageNumber = Integer.parseInt(request.getParameter("page"));
			int currentAccountID = accounts.get(id).getAccountID();
			List<Transaction> transactions = new ArrayList<Transaction>();
			int numberOfPages = 1;
			try {
				transactions = customTransactionOperation.getChildrenTransactionsPaginatedAndFilter(currentAccountID, 10, 10 * pageNumber, "transaction_id");
				numberOfPages = customTransactionOperation.getNumberOfPages(currentAccountID, 10);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			request.setAttribute("table_transaction", transactions);
			request.setAttribute("pages", numberOfPages);
			Logger.getInstance().log("pages " + numberOfPages);
		}
		request.getRequestDispatcher("/WEB-INF/History.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
