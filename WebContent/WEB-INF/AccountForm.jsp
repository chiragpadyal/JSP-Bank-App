<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>The Online Payment-Page</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>

<body>
	<div class="container mx-auto mt-10">
		<form action="#"
			class="max-w-xl mx-auto 
								bg-white p-8 rounded 
								shadow-lg">
			<div class="grid grid-cols-1 
						md:grid-cols-2 gap-6">
				<div>
					<h3 class="text-lg font-semibold 
							text-red-600 mb-4">
						Billing Address</h3>
					<div class="mb-4">
						<label for="name"
							class="block text-sm 
									font-medium text-gray-700">
							Full Name: </label> <input type="text" id="name"
							placeholder="Enter your full name" required
							class="mt-1 block w-full 
									border-gray-300 rounded-md 
									shadow-sm focus:ring-red-500
									focus:border-red-500">
					</div>
					<div class="mb-4">
						<label for="email"
							class="block text-sm font-medium
									text-gray-700">
							Email: </label> <input type="email" id="email"
							placeholder="Enter your email ID" required
							class="mt-1 block w-full 
									border-gray-300
									rounded-md shadow-sm 
									focus:ring-red-500 
									focus:border-red-500">
					</div>
					<div class="mb-4">
						<label for="address"
							class="block text-sm font-medium
									text-gray-700">
							Address: </label> <input type="text" id="address"
							placeholder="Enter your Address" required
							class="mt-1 block w-full border-gray-300
									rounded-md shadow-sm 
									focus:ring-red-500 
									focus:border-red-500">
					</div>
					<div class="mb-4">
						<label for="city"
							class="block text-sm font-medium 
									text-gray-700">
							City: </label> <input type="text" id="city" placeholder="Enter your city"
							required
							class="mt-1 block w-full 
									border-gray-300 rounded-md
									shadow-sm focus:ring-red-500
									focus:border-red-500">
					</div>
					<div class="mb-4">
						<label for="state"
							class="block text-sm font-medium
									text-gray-700">
							State: </label> <input type="text" id="state"
							placeholder="Enter your state" required
							class="mt-1 block w-full 
									border-gray-300
									rounded-md shadow-sm
									focus:ring-red-500 
									focus:border-red-500">
					</div>
					<div class="mb-4">
						<label for="zip"
							class="block text-sm font-medium
									text-gray-700">
							Zip code: </label> <input type="text" id="zip"
							placeholder="Enter your zip code" required
							class="mt-1 block w-full 
									border-gray-300 rounded-md 
									shadow-sm focus:ring-red-500
									focus:border-red-500">
					</div>
				</div>
				<div>
					<h3 class="text-lg font-semibold 
							text-red-600 mb-4">
						Payment</h3>
					<div class="mb-4">
						<label for="cardName"
							class="block text-sm font-medium
									text-gray-700">
							Name On Card: </label> <input type="text" id="cardName"
							placeholder="Enter card name" required
							class="mt-1 block w-full 
									border-gray-300 rounded-md 
									shadow-sm focus:ring-red-500
									focus:border-red-500">
					</div>
					<div class="mb-4">
						<label for="cardNumber"
							class="block text-sm font-medium 
									text-gray-700">
							Credit Card Number: </label> <input type="text" id="cardNumber"
							placeholder="Enter card number" required
							class="mt-1 block w-full 
									border-gray-300 rounded-md
									shadow-sm focus:ring-red-500
									focus:border-red-500">
					</div>
					<div class="flex justify-between mb-4">
						<div class="w-1/2 mr-2">
							<label for="expMonth"
								class="block text-sm font-medium
										text-gray-700">
								Expiration Month: </label> <input type="text" id="expMonth"
								placeholder="MM" required
								class="mt-1 block w-full 
										border-gray-300 rounded-md
										shadow-sm focus:ring-red-500
										focus:border-red-500">
						</div>
						<div class="w-1/2 ml-2">
							<label for="expYear"
								class="block text-sm 
										font-medium 
										text-gray-700">
								Expiration Year: </label> <input type="text" id="expYear"
								placeholder="YYYY" required
								class="mt-1 block w-full 
										border-gray-300 
										rounded-md shadow-sm
										focus:ring-red-500 
										focus:border-red-500">
						</div>
					</div>
					<div class="mb-4">
						<label for="cvv"
							class="block text-sm 
									font-medium 
									text-gray-700">
							CVV: </label> <input type="text" id="cvv" placeholder="Enter CVV"
							required
							class="mt-1 block w-full 
									border-gray-300 rounded-md
									shadow-sm focus:ring-red-500
									focus:border-red-500">
					</div>
				</div>
			</div>
			<input type="submit" value="Proceed to Checkout" id="checkoutBtn"
				class="mt-6 px-4 py-2 bg-red-600 text-white
						rounded-md hover:bg-red-700">
		</form>
	</div>
	<script>
		document.getElementById('checkoutBtn').addEventListener('click',
				function(event) {
					event.preventDefault();
					alert('Proceeding to checkout.');
				});
	</script>
</body>

</html>