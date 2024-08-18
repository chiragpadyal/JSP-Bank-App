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
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>


</head>

<body>

	<div class="flex flex-row ">
	<jsp:include page='SideBar.jsp'></jsp:include>

<div class="flex w-full overflow-x-auto flex-col">
	<table class="table-hover table">
		<thead>
			<tr>
				<th>id</th>
				<th>sender account number</th>
				<th>receiver account number</th>
				<th>Transaction Amount</th>
				<th>Date</th>
				<th>Type</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="transaction" items="${table_transaction}" varStatus="status">
				<tr>
					<th>${status.index}</th>
					<td>${transaction.sender_account.accountNumber}</td>
					<td>${transaction.receiver_account.accountNumber}</td>
					<td>${transaction.transactionAmount}</td>
					<td>${transaction.date}</td>
					<td>${transaction.transactionType.name}</td>
				</tr>
			</c:forEach>
			
	</table>
	<c:set var="currentPage" value="${param.page != null ? param.page : 0}" />
<div class="pagination w-full justify-center p-3">
    <!-- Previous Button -->
    <button class="btn <c:if test="${currentPage == 0}">btn-disabled</c:if>">
        <a href="?page=<c:out value="${currentPage - 1}"/>">
            <svg width="18" height="18" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M12.2574 5.59165C11.9324 5.26665 11.4074 5.26665 11.0824 5.59165L7.25742 9.41665C6.93242 9.74165 6.93242 10.2667 7.25742 10.5917L11.0824 14.4167C11.4074 14.7417 11.9324 14.7417 12.2574 14.4167C12.5824 14.0917 12.5824 13.5667 12.2574 13.2417L9.02409 9.99998L12.2574 6.76665C12.5824 6.44165 12.5741 5.90832 12.2574 5.59165Z" fill="#969696" />
            </svg>
        </a>
    </button>
    
    <!-- Page Number Buttons -->
    <c:forEach var="i" begin="1" end="${pages}">
        <button class="btn <c:if test="${i - 1 == currentPage}">btn-active</c:if>">
            <a href="?page=${i - 1}"><c:out value="${i}"/></a>
        </button>
    </c:forEach>
    <c:out value="${currentPage}"></c:out>
    <!-- Next Button -->
    <button class="btn <c:if test="${currentPage >= pages}">btn-disabled</c:if>">
        <a href="?page=<c:out value="${currentPage + 1}"/>">
            <svg width="18" height="18" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M7.74375 5.2448C7.41875 5.5698 7.41875 6.0948 7.74375 6.4198L10.9771 9.65314L7.74375 12.8865C7.41875 13.2115 7.41875 13.7365 7.74375 14.0615C8.06875 14.3865 8.59375 14.3865 8.91875 14.0615L12.7437 10.2365C13.0687 9.91147 13.0687 9.38647 12.7437 9.06147L8.91875 5.23647C8.60208 4.9198 8.06875 4.9198 7.74375 5.2448Z" fill="#969696" />
            </svg>
        </a>
    </button>
</div>
</div>
	</div>
	</div>
</body>

</html>
