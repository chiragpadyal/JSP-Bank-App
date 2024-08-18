<%@page import="com.swabhav.Logger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article>
	<input class="modal-state" id="adduser-modal" type="checkbox" />
	<div class="modal">
		<label class="modal-overlay" for="adduser-modal"></label>
		<div class="modal-content flex w-full flex-col gap-5 p-7">
			<label for="adduser-modal" class="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">X</label>
			<div class="flex flex-col gap-2">
				<h2 class="text-center text-2xl font-semibold">AddUser Amount</h2>
			</div>

              <form id="adduser-form" action="create-user" method="POST">
                    <section>
                        <div class="form-group">
                            <div class="form-field">
                                <label class="form-label">User Name (Email): </label>
                                <input name="email" placeholder="Type here" type="email" class="input max-w-full" />
                                <label class="form-label">
                                    <span class="form-label-alt">Please enter the email.</span>
                                </label>
                                <label class="form-label">User Password: </label>

                                <input name="password" placeholder="Type here" type="password" class="input max-w-full" />
                                <label class="form-label">
                                    <span class="form-label-alt">Please enter the password.</span>
                                </label>
                               <label class="form-label">isAdmin: </label>
								<input type="checkbox" value="0" name="role" class="switch switch-sm" />
                                
                            </div>
                            <div class="form-field pt-5">
                                <div class="form-control justify-between">
                                    <button class="btn btn-primary w-full" type="submit">AddUser</button>
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
            $('#adduser-form').on('submit', function(event) {
                <c:import url="formajax.js" />
            });
        });
    </script>