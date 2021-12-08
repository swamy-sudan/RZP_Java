<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.bttnStyle{
background-color: lightblue;
border-radius: 0.50rem;
border: 1px solid transperent;
}
</style>

<script>
var xhttp = new XMLHttpRequest();
var RazorpayOrderId;
function CreateOrderID(){
	xhttp.open("GET","http://localhost:8080/RZP_Java/OrderCreation",false);
	xhttp.send();
	RazorpayOrderId = xhttp.responseText;
	OpenCheckout();
</script>



<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>
function OpenCheckout(){
var options = {
    "key": "rzp_test_577h02zBHnXglJ", // Enter the Key ID generated from the Dashboard
    "amount": "100", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
    "currency": "INR",
    "name": "Acme Corp",
    "description": "Test Transaction",
    "image": "https://example.com/your_logo",
    "order_id": RazorpayOrderId , //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
    /* "handler": function (response){
        alert(response.razorpay_payment_id);
        alert(response.razorpay_order_id);
        alert(response.razorpay_signature)
    }, */
    "callback_url":"http://localhost:8080/RZP_Java/OrderCreation",
    "prefill": {
        "name": "Gaurav Kumar",
        "email": "gaurav.kumar@example.com",
        "contact": "9999999999"
    },
    "notes": {
        "address": "Razorpay Corporate Office"
    },
    "theme": {
        "color": "#3399cc"
    }
};
var rzp1 = new Razorpay(options);
rzp1.on('payment.failed', function (response){
    alert(response.error.code);
    alert(response.error.description);
    alert(response.error.source);
    alert(response.error.step);
    alert(response.error.reason);
    alert(response.error.metadata.order_id);
    alert(response.error.metadata.payment_id);
});
    rzp1.open();
    e.preventDefault();
}
</script>







<title>Razorpay Java</title>
</head>
<body>
<div id="order_id">

</div>

<center>
<button id="payButton" onclick="CreateOrderID()" class="bttnStyle">Pay Now</button>
</center>

</body>
</html>