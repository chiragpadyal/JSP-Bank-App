event.preventDefault(); // Prevent the default form submission
var formData = $(this).serialize();
$.ajax({
                    url: $(this).attr('action'),
                    type: $(this).attr('method'),
                    data: formData,
                    success: function(response) {
                        // Handle success response (e.g., show a success message or redirect)
               			Toastify({
               				text: "Transfer operation was successful",
               				duration: 3000,  
               				close: true,
	               			 style: {
	               			    background: "#C1E95D",
	               			  },
               			  position: "center", // `left`, `center` or `right`
               				}).showToast();
                        // Optionally, close the modal or reset the form
                        $('#transfer-modal').prop('checked', false);
                        window.location.reload(true);
                        
                    },
                    error: function(jqXHR) {
                        var errorMessage = 'An unexpected error occurred.';
               			if (jqXHR.responseText) {
                            errorMessage = jqXHR.responseText; // Extract the error message from responseText
                        };
               			Toastify({
               				text: errorMessage,
               				duration: 3000,  
               				close: true,
	               			 style: {
	               			    background: "#F43452",
	               			  },
               			  position: "center", // `left`, `center` or `right`
               				}).showToast();
                    }
                });