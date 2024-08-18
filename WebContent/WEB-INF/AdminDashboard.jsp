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
		<jsp:include page='AdminSideBar.jsp'></jsp:include>

		<div class="  p-3 flex w-full overflow-x-auto flex-col">

			<div>
				<div>
					<div class="block">
						<nav class="flex gap-6" aria-label="Tabs"> <a
							href="admin?type=users"
							class="shrink-0 rounded-lg p-2 text-sm font-medium 
						              ${type == 'users' ? 'text-sky-600 bg-sky-100' : 'text-gray-500 hover:bg-gray-50 hover:text-gray-700'}"
							aria-current="${type == 'users' ? 'page' : ''}"> User </a> <a
							href="admin?type=accounts"
							class="shrink-0 rounded-lg p-2 text-sm font-medium 
						              ${type == 'accounts' ? 'text-sky-600 bg-sky-100' : 'text-gray-500 hover:bg-gray-50 hover:text-gray-700'}"
							aria-current="${type.equals('accounts') ? 'page' : ''}">
							Accounts </a> <a href="admin?type=transactions"
							class="shrink-0 rounded-lg p-2 text-sm font-medium 
						              ${type == 'transactions' ? 'text-sky-600 bg-sky-100' : 'text-gray-500 hover:bg-gray-50 hover:text-gray-700'}"
							aria-current="${type == 'transactions' ? 'page' : ''}">
							Transactions </a> <label class="btn btn-solid-success"
							for="adduser-modal"> Add User </label> </nav>
					</div>
				</div>
				<form class="p-3 relative flex items-center space-x-2"
					action="admin" method="GET">
					<input value="${type}" name="type" class="hidden" />
					<!-- Dropdown Box -->
					<div class="relative">
						<select id="searchCategory" name="sortby"
							class="w-32 rounded-md border-gray-200 py-2.5 px-3 shadow-sm sm:text-sm">
							<c:forEach var="column" items="${columns}">
								<option value="${column}">${column}</option>
							</c:forEach>
						</select>
					</div>

					<!-- Search Input -->
					<div class="relative flex-1">
						<label for="Search" class="sr-only ">Search</label> <input
							type="text" id="Search" name="search"
							placeholder="  Search for..."
							class="w-full rounded-md border-gray-200 p-2.5 pe-10 shadow-sm sm:text-sm" />
						<span
							class="absolute p-2 inset-y-0 right-0 flex items-center pr-3">
							<button type="submit" class="text-gray-600 hover:text-gray-700">
								<span class="sr-only">Search</span>
								<svg xmlns="http://www.w3.org/2000/svg" fill="none"
									viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
									class="h-4 w-4"> <path stroke-linecap="round"
									stroke-linejoin="round"
									d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z" />
								</svg>
							</button>
						</span>
					</div>
				</form>

				<table class="table-hover table">
					<thead>
						<tr>
							<c:forEach var="column" items="${columns}">
								<th>${column}</th>
							</c:forEach>
							<c:if test="${type != 'transactions'}">
							<th>Operation</th>
							</c:if>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="account" items="${rows}">
							<tr>
								<c:forEach var="column" items="${columns}" varStatus="status">
									<td>${account[columns[status.index]]}</td>
								</c:forEach>
								<c:if test="${type == 'users'}">
									<td>
										<button class="btn btn-outline-primary"
											id="addaccount-modal-button">Add Account</button>
									</td>
								</c:if>
								<c:if test="${type == 'accounts'}">
									<td>
										<button class="btn btn-outline-error"
											id="update-account-button">
											Update Account</button>
										<button class="btn btn-outline-error"
											id="delete-account-button">
											Delete Account</button>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<c:set var="currentPage"
					value="${param.page != null ? param.page : 0}" />
				<div class="pagination w-full justify-center p-3">
					<!-- Previous Button -->
					<button
						class="btn <c:if test="${currentPage == 0}">btn-disabled</c:if>">
						<a
							href="admin?type=transactions&page=<c:out value="${currentPage - 1}"/>">
							<svg width="18" height="18" viewBox="0 0 20 20" fill="none"
								xmlns="http://www.w3.org/2000/svg"> <path
								fill-rule="evenodd" clip-rule="evenodd"
								d="M12.2574 5.59165C11.9324 5.26665 11.4074 5.26665 11.0824 5.59165L7.25742 9.41665C6.93242 9.74165 6.93242 10.2667 7.25742 10.5917L11.0824 14.4167C11.4074 14.7417 11.9324 14.7417 12.2574 14.4167C12.5824 14.0917 12.5824 13.5667 12.2574 13.2417L9.02409 9.99998L12.2574 6.76665C12.5824 6.44165 12.5741 5.90832 12.2574 5.59165Z"
								fill="#969696" /> </svg>
						</a>
					</button>

					<!-- Next Button -->
					<button
						class="btn <c:if test="${currentPage >= pages}">btn-disabled</c:if>">
						<a
							href="admin?type=transactions&page=<c:out value="${currentPage + 1}"/>">
							<svg width="18" height="18" viewBox="0 0 20 20" fill="none"
								xmlns="http://www.w3.org/2000/svg"> <path
								fill-rule="evenodd" clip-rule="evenodd"
								d="M7.74375 5.2448C7.41875 5.5698 7.41875 6.0948 7.74375 6.4198L10.9771 9.65314L7.74375 12.8865C7.41875 13.2115 7.41875 13.7365 7.74375 14.0615C8.06875 14.3865 8.59375 14.3865 8.91875 14.0615L12.7437 10.2365C13.0687 9.91147 13.0687 9.38647 12.7437 9.06147L8.91875 5.23647C8.60208 4.9198 8.06875 4.9198 7.74375 5.2448Z"
								fill="#969696" /> </svg>
						</a>
					</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page='AddAccountModal.jsp'></jsp:include>
	<jsp:include page='AddUserModal.jsp'></jsp:include>
	
</body>
<script>
	$(document).on("click", "#addaccount-modal-button", function() {
		var firstTdText = $(this).closest("tr").find("td").first().text();
		$('#foruserid-input').val(firstTdText);
		$('#addaccount-modal').prop('checked', true);
	});
	
	$(document).on("click", "#adduser-modal-button", function() {
		$('#adduser-modal').prop('checked', true);
	});
	
	$(document).on("click", "#delete-account-button", function() {
		var firstTdText = $(this).closest("tr").find("td").first().text();

		// use ajax to post on 'delete-tranasaction'
		// with form body of id = firstTdText 
		$.ajax({
			url : 'delete-account', // URL to send the request to
			type : 'POST', // HTTP method
			data : {
				id : firstTdText
			}, // Data to send to the server
			success : function(response) {
				$(this).closest("tr").remove();
				Toastify({
					text : "Transfer operation was successful",
					duration : 3000,
					close : true,
					style : {
						background : "#C1E95D",
					},
					position : "center", // `left`, `center` or `right`
				}).showToast();
			},
			error : function(jqXHR) {
				var errorMessage = 'An unexpected error occurred.';
				if (jqXHR.responseText) {
					errorMessage = jqXHR.responseText; // Extract the error message from responseText
				};
				Toastify({
					text : errorMessage,
					duration : 3000,
					close : true,
					style : {
						background : "#F43452",
					},
					position : "center", // `left`, `center` or `right`
				}).showToast();
			}
		});
	});
	
	$(document).on("click", "#delete-transaction-button", function() {
		var firstTdText = $(this).closest("tr").find("td").first().text();

		// use ajax to post on 'delete-tranasaction'
		// with form body of id = firstTdText 
		$.ajax({
			url : 'delete-transaction', // URL to send the request to
			type : 'POST', // HTTP method
			data : {
				id : firstTdText
			}, // Data to send to the server
			success : function(response) {
				$(this).closest("tr").remove();
				Toastify({
					text : "Transfer operation was successful",
					duration : 3000,
					close : true,
					style : {
						background : "#C1E95D",
					},
					position : "center", // `left`, `center` or `right`
				}).showToast();
			},
			error : function(jqXHR) {
				var errorMessage = 'An unexpected error occurred.';
				if (jqXHR.responseText) {
					errorMessage = jqXHR.responseText; // Extract the error message from responseText
				}
				;
				Toastify({
					text : errorMessage,
					duration : 3000,
					close : true,
					style : {
						background : "#F43452",
					},
					position : "center", // `left`, `center` or `right`
				}).showToast();
			}
		});
	});
</script>
</html>
