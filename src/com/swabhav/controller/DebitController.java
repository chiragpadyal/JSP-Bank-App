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
import com.swabhav.model.Debit;
import com.swabhav.model.Transaction;
import com.swabhav.operations.AccountOperations;
import com.swabhav.operations.Operations;
import com.swabhav.operations.TransactionOperation;

/**
 * Servlet implementation class TransactionController
 */
@WebServlet("/debit")
public class DebitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Operations<Account> accountOperations = new AccountOperations();
    private Operations<Transaction> transactionOperations = new TransactionOperation();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DebitController() {
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
		double amount = Double.parseDouble(request.getParameter("amount"));
		int accountId = Integer.parseInt(request.getParameter("accountid"));
		Account account = null;
		Account toAccount = null;
		Transaction transaction = null;
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		try {
			account = accountOperations.get(accountId);
			if(account == null) throw new AccountException("Your account id not found");
			transaction = new Transaction();
			account.debit(amount);
			transaction.setDate(new Date(System.currentTimeMillis()));
			transaction.setSender_account(account);
			transaction.setReceiver_account(toAccount);
			transaction.setTransactionAmount(amount);
			transaction.setTransactionType(new Debit());
			transactionOperations.add(transaction);
			accountOperations.update(accountId, account);
			response.sendRedirect("dashboard");
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
