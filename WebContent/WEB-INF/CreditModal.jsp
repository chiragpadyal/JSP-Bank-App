<%@page import="com.swabhav.Logger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <article>
        <input class="modal-state" id="credit-modal" type="checkbox" />
        <div class="modal">
            <label class="modal-overlay" for="credit-modal"></label>
            <div class="modal-content flex w-full flex-col gap-5 p-7">
                <label for="credit-modal" class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">X</label>
                <div class="flex flex-col gap-2">
                    <h2 class="text-center text-2xl font-semibold">Credit Amount</h2>
                </div>

                <form id="credit-form" action="credit" method="POST">
                    <section>
                        <div class="form-group">
                            <div class="form-field">
                                <label class="form-label">Amount Number</label>
                                <input placeholder="Type here" type="text" name="amount" class="input max-w-full" />
                                <input name="accountid" class="hidden" value="${sessionScope.accounts[globalId].accountID}" />
                                <label class="form-label">
                                    <span class="form-label-alt">Please enter the amount to credit.</span>
                                </label>
                            </div>
                            <div class="form-field pt-5">
                                <div class="form-control justify-between">
                                    <button class="btn btn-primary w-full" type="submit">Credit</button>
                                </div>
                            </div>
                        </div>
                    </section>
                </form>
            </div>
        </div>
    </article>

    <script>
        $(document).ready(function() {
            $('#credit-form').on('submit', function(event) {
            	 <c:import url="formajax.js" />
            });
        });
    </script>