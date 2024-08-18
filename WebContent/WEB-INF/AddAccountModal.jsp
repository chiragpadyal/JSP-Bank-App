<%@page import="com.swabhav.Logger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article>
	<input class="modal-state" id="addaccount-modal" type="checkbox" />
	<div class="modal">
		<label class="modal-overlay" for="addaccount-modal"></label>
		<div class="modal-content flex w-full flex-col gap-5 p-7">
			<label for="addaccount-modal" class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">X</label>
			<div class="flex flex-col gap-2">
				<h2 class="text-center text-2xl font-semibold">AddAccount Amount</h2>
			</div>

              <form id="addaccount-form" action="create-account" method="POST">
                    <section>
                        <div class="form-group">
                            <div class="form-field">
                                <label class="form-label">Account Holder Name</label>
                                <input name="accountholdername" placeholder="Type here" type="text" name="amount" class="input max-w-full" />
                                <input name="foruserid" class="hidden" id="foruserid-input" />
                                <label class="form-label">
                                    <span class="form-label-alt">Please enter the new account holder name.</span>
                                </label>
                            </div>
                            <div class="form-field pt-5">
                                <div class="form-control justify-between">
                                    <button class="btn btn-primary w-full" type="submit">AddAccount</button>
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
            $('#addaccount-form').on('submit', function(event) {
                <c:import url="formajax.js" />
            });
        });
    </script>