<%@page import="com.swabhav.Logger"%>
<%@page import="com.swabhav.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sm:w-full sm:max-w-[18rem]">
	<input type="checkbox" id="sidebar-mobile-fixed"
		class="sidebar-state" /> <label for="sidebar-mobile-fixed"
		class="sidebar-overlay"></label>
	<aside
		class="sidebar sidebar-fixed-left sidebar-mobile h-full justify-start max-sm:fixed max-sm:-translate-x-full">
	<a href="dashboard">
	<section class="sidebar-title items-center p-4"> <svg
		fill="none" height="42" viewBox="0 0 32 32" width="42"
		xmlns="http://www.w3.org/2000/svg"> <rect height="100%"
		rx="16" width="100%"></rect> <path clip-rule="evenodd"
		d="M17.6482 10.1305L15.8785 7.02583L7.02979 22.5499H10.5278L17.6482 10.1305ZM19.8798 14.0457L18.11 17.1983L19.394 19.4511H16.8453L15.1056 22.5499H24.7272L19.8798 14.0457Z"
		fill="currentColor" fill-rule="evenodd"></path> </svg>
	<div class="flex flex-col">
		<span> <c:out
				value="${sessionScope.accounts[globalId].accountName}" />
		</span> <span class="text-xs font-normal text-content2"> <c:out
				value="Saving Account" />
		</span>
	</div>
	</section>
	</a>
	<div class="divider my-0"></div>
	<section class="sidebar-content"> <nav
		class="menu rounded-md"> <section class="menu-section px-4">
	<span class="menu-title">Main menu</span>
	<ul class="menu-items">
		<li><input type="checkbox" id="menu-1" class="menu-toggle" />
			<label class="menu-item justify-between" for="menu-1">
				<div class="flex gap-2">
					<svg xmlns="http://www.w3.org/2000/svg"
						class="h-5 w-5 opacity-75" fill="none" viewBox="0 0 24 24"
						stroke="currentColor" stroke-width="2"> <path
						stroke-linecap="round" stroke-linejoin="round"
						d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
					</svg>
					<span>Switch Account</span>
				</div> <span class="menu-icon"> <svg
						xmlns="http://www.w3.org/2000/svg" class="h-5 w-5"
						viewBox="0 0 20 20" fill="currentColor"> <path
						fill-rule="evenodd"
						d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
						clip-rule="evenodd" /> </svg>
			</span>
		</label>

			<div class="menu-item-collapse">
				<div class="min-h-0">

					<label class="menu-item menu-item-disabled ml-6">Current
						Account</label>
					<c:forEach var="account" items="${sessionScope.accounts}"
						varStatus="status">
						<a class="menu-item ml-6" href="?id=${status.index}">${account.accountNumber}</a>
					</c:forEach>
				</div>
			</div></li>
		<a href="transactions">
		<li class="menu-item">
		<svg xmlns="http://www.w3.org/2000/svg"
				class="h-5 w-5 opacity-75" fill="none" viewBox="0 0 24 24"
				stroke="currentColor" stroke-width="2">
		 <path
				stroke-linecap="round" stroke-linejoin="round"
				d="M3 10h18M7 15h1m4 0h1m-7 4h12a3 3 0 003-3V8a3 3 0 00-3-3H6a3 3 0 00-3 3v8a3 3 0 003 3z" />
			</svg> <span>Transaction History</span>
		</li>
		</a>
	</ul>
	</section> </nav> </section>
	<section class="sidebar-footer justify-end bg-gray-2 pt-2">
				<div class="divider my-0"></div>
				<a href="logout">
				<div class="z-50 flex h-fit w-full cursor-pointer hover:bg-gray-4">
					<label class="whites mx-2 flex h-fit w-full cursor-pointer p-0 hover:bg-gray-4" tabindex="0">
						<div class="flex flex-row gap-4 p-4">
							<div class="avatar-square avatar avatar-md">
							<svg width="24px" height="24px" viewBox="0 -1.29 512 512" xmlns="http://www.w3.org/2000/svg" fill="#000000"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <defs> <style>.cls-1{fill:none;stroke:#000000;stroke-linecap:round;stroke-linejoin:round;stroke-width:20px;}</style> </defs> <g data-name="Layer 2" id="Layer_2"> <g data-name="E430, Logout, multimedia, Ui" id="E430_Logout_multimedia_Ui"> <line class="cls-1" x1="291.14" x2="502" y1="234.62" y2="234.62"></line> <polyline class="cls-1" points="437.83 181.15 502 234.62 437.83 288.1"></polyline> <polyline class="cls-1" points="10 459.25 10 10 331.31 10 331.31 459.25 230.9 459.25"></polyline> <polygon class="cls-1" points="230.9 499.41 10 459.25 10 10 230.9 50.16 230.9 499.41"></polygon> <polygon class="cls-1" points="180.69 178.1 60.2 158.02 60.2 107.82 180.69 127.9 180.69 178.1"></polygon> </g> </g> </g></svg>
							</div>
							<div class="flex flex-col">
								<span>Logout</span>
							</div>
						</div>
					</label>
				</div>
				</a>
			</section> 
	</aside>
</div>