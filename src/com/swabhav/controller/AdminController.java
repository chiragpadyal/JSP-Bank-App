package com.swabhav.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swabhav.Logger;
import com.swabhav.exception.AccountException;
import com.swabhav.model.Account;
import com.swabhav.model.AdminRole;
import com.swabhav.model.CustomerRole;
import com.swabhav.model.Transaction;
import com.swabhav.model.User;
import com.swabhav.operations.AccountOperations;
import com.swabhav.operations.CustomAccountOperation;
import com.swabhav.operations.CustomTransactionOperation;
import com.swabhav.operations.CustomUserOperation;
import com.swabhav.operations.Operations;
import com.swabhav.operations.TransactionOperation;
import com.swabhav.operations.UserOperations;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomAccountOperation accountOperations = new CustomAccountOperation();
	private CustomTransactionOperation transactionOperations = new CustomTransactionOperation();
	private CustomUserOperation userOperations = new CustomUserOperation();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		// Check if the user object is not null
		if (user != null) {
			if (!user.getRole().getName().equals(new AdminRole().getName())) {
				response.sendError(404);
				return;
			}
			List<Account> accounts = new ArrayList<Account>();
			List<Transaction> transactions = new ArrayList<Transaction>();
			List<User> users = new ArrayList<User>();

			String type = request.getParameter("type");
			String sortBy = request.getParameter("sort");
			String search = request.getParameter("search");
			Integer pageNumber = 0;
			if (request.getParameter("page") != null)
				pageNumber = Integer.parseInt(request.getParameter("page"));

			if (type == null)
				type = "accounts";

			request.setAttribute("type", type);
			List<String> accountsCol = Arrays.asList("accountID", "user", "accountNumber", "balance", "accountName", "isActive");
			List<String> usersCol = Arrays.asList("userId", "email", "role");
			List<String> transactionCol = Arrays.asList("transactionId", "sender_account", "receiver_account",
					"transactionAmount", "date", "transactionType");

			try {
				accounts = accountOperations.getAllAccountSortAndFilter(sortBy, search);
				transactions = transactionOperations.getAllTransactionSortedAndFiltered(sortBy, search, 10,
						10 * pageNumber);
				Logger.getInstance().log("transactions" + transactions);
				users = userOperations.getAllUserSortedAndFiltered(sortBy, search);
				switch (type) {
				case "users":
					request.setAttribute("columns", usersCol);
					request.setAttribute("rows", users);
					break;
				case "accounts":
					Logger.getInstance().log("here is accounts");
					request.setAttribute("columns", accountsCol);
					request.setAttribute("rows", accounts);
					break;
				case "transactions":
					request.setAttribute("columns", transactionCol);
					request.setAttribute("rows", transactions);
					break;
				default:
					request.setAttribute("columns", usersCol);
					request.setAttribute("rows", users);
					break;
				}

			} catch (SQLException e) {
				Logger.getInstance().log(e.getMessage());
			} catch (AccountException e) {
				Logger.getInstance().log(e.getMessage());

			}
			request.setAttribute("currentPage", pageNumber);

			request.getRequestDispatcher("/WEB-INF/AdminDashboard.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
