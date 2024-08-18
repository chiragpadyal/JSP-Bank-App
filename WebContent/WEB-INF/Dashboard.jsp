<%@page import="com.swabhav.Logger"%>
<%@page import="com.swabhav.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<c:if test="${empty sessionScope.user}">
	<c:redirect url="login" />
</c:if>
<%
	int idValue = 0; // Default value

	String idParam = request.getParameter("id");
	if (idParam != null && !idParam.isEmpty()) {
		try {
			idValue = Integer.parseInt(idParam);
		} catch (NumberFormatException e) {
			// Handle the case where the id is not a valid integer
			idValue = 0;
		}
	}

	Integer max = (Integer) session.getAttribute("numberOfAccounts");
	if (max == null || idValue > max) {
		idValue = 0;
	}
	// Store the computed value as a request attribute
	request.setAttribute("globalId", idValue);
%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/rippleui@1.12.1/dist/css/styles.css" />
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>

</head>

<body>

	<div class="flex flex-row ">
	<jsp:include page='SideBar.jsp'></jsp:include>
		<div class="flex w-full flex-col">
			<div
				class="flex flex-col  p-6 w-full my-2 bg-gray-100 dark:bg-gray-900 rounded-lg shadow-md transition duration-500 ease-in-out overflow-y-auto">
				<div class="flex flex-col sm:flex-row justify-between mb-4">
					<!-- User Info -->
					<!-- User Info -->
						<div class="flex items-start justify-between">
							<label for="sidebar-mobile-fixed"
								class="btn sm:hidden">
								<svg width="32px" height="32px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path d="M20 7L4 7" stroke="#1C274C" stroke-width="1.5" stroke-linecap="round"></path> <path d="M20 12L4 12" stroke="#1C274C" stroke-width="1.5" stroke-linecap="round"></path> <path d="M20 17L4 17" stroke="#1C274C" stroke-width="1.5" stroke-linecap="round"></path> </g></svg>
							</label>
						<a href="#"
							class="text-gray-800 dark:text-white text-xl font-semibold">
							Hi, <c:out value="${sessionScope.user.email}" />
						</a>
						</div>

					<!-- Button Container -->
					<div class="flex space-x-4 ml-auto sm:items-center justify-center sm:justify-end w-full p-5">
						<label class="btn btn-solid-success" for="credit-modal">Credit</label >
						<label class="btn btn-solid-error" for="debit-modal">Debit</label>
						<label class="btn btn-solid-primary" for="transfer-modal">Transfer</label>
					</div>


				</div>

				<div
					class="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-inner mb-6">
					<!-- Balance Section -->
					<span class="block text-gray-600 dark:text-gray-300 text-sm mb-1">Balance</span>
					<span
						class="block text-4xl font-bold text-gray-900 dark:text-gray-100">
						$ <c:out value="${sessionScope.accounts[globalId].balance}" /> 
					</span>

				</div>

				<div class="p-4 bg-white dark:bg-gray-800 rounded-lg shadow-md">
					<!-- Account Details -->
					<div class="flex flex-col space-y-3">
						<div
							class="flex items-center justify-between text-gray-700 dark:text-gray-300">
							<span class="font-medium text-sm">Account Number:</span> <span
								class="text-sm font-semibold"><c:out
									value="${sessionScope.accounts[globalId].accountNumber}" /></span>
						</div>
						<div
							class="flex items-center justify-between text-gray-700 dark:text-gray-300">
							<span class="font-medium text-sm">Account Name:</span> <span
								class="text-sm font-semibold"><c:out
									value="${sessionScope.accounts[globalId].accountName}" /></span>
						</div>
						<div
							class="flex items-center justify-between text-gray-700 dark:text-gray-300">
							<span class="font-medium text-sm">Account Type:</span> <span
								class="text-sm font-semibold"><c:out value="Saving" /></span>
						</div>
					</div>
				</div>


				<div class="mt-12 flex items-center">
					<!-- Payments -->
					<span>Payments</span>
					<button class="ml-2 focus:outline-none">
						<svg class="h-5 w-5 fill-current" viewBox="0 0 256 512"> <path
							d="M224.3 273l-136 136c-9.4 9.4-24.6 9.4-33.9
							0l-22.6-22.6c-9.4-9.4-9.4-24.6
							0-33.9l96.4-96.4-96.4-96.4c-9.4-9.4-9.4-24.6 0-33.9L54.3
							103c9.4-9.4 24.6-9.4 33.9 0l136 136c9.5 9.4 9.5 24.6.1
							34z"></path>
						</svg>
					</button>
				</div>
				<c:forEach var="transaction" items="${sessionScope.transactions[globalId]}">
    <c:set var="bgColor"
        value="${transaction.transactionType.ID == 2 ? 'red'  : 'green'}" />
    
    <!-- Determine the message to display -->
    <c:choose>
        <c:when test="${transaction.receiver_account.accountNumber == sessionScope.accounts[globalId].accountNumber}">
            <c:set var="message" value="Received money from ${transaction.receiver_account.accountNumber}" />
        </c:when>
        <c:when test="${transaction.sender_account.accountNumber == sessionScope.accounts[globalId].accountNumber && transaction.receiver_account.accountNumber != null}">
            <c:set var="message" value="Send money to ${transaction.receiver_account.accountNumber}" />
        </c:when>
        <c:when test="${transaction.receiver_account.accountNumber == null}">
            <c:choose>
                <c:when test="${transaction.transactionType.name == 'Debit'}">
                    <c:set var="message" value="Debited money from bank" />
                </c:when>
                <c:when test="${transaction.transactionType.name == 'Credit'}">
                    <c:set var="message" value="Credited money from bank" />
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <!-- Default message or empty -->
            <c:set var="message" value="Transaction details not available" />
        </c:otherwise>
    </c:choose>
    
    <!-- Display the transaction -->
    <a href="#" class="mt-2 p-4 flex justify-between bg-${bgColor}-300 rounded-lg font-semibold capitalize">
        <div class="flex">
            <img class="h-10 w-10 rounded-full object-cover"
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1RZ5sKLtFG-Q2xfXlLa5DbFsmF52Gc-C49B4s63CtSxLkzQY&s"
                alt="transaction profile" />
            <div class="flex flex-col ml-4">
                <span>${message}</span>
                <span class="text-sm text-gray-600">${transaction.transactionType.name}</span>
            </div>
        </div>
        <span>$ ${transaction.transactionAmount}</span>
    </a>
</c:forEach>
				


			<div class="mt-4 flex justify-center capitalize text-blue-600">
				<a href="#">see all</a>
			</div>

		</div>

	</div>
	</div>
	<jsp:include page='CreditModal.jsp'></jsp:include>
	<jsp:include page='TransferModal.jsp'></jsp:include>
	<jsp:include page='DebitModal.jsp'></jsp:include>
</body>

</html>