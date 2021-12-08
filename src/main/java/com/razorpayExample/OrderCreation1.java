package com.razorpayExample;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;
import com.razorpay.*;

/**
 *implementation class OrderCreation
 */
@WebServlet("/OrderCreation")
public class OrderCreation1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCreation1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RazorpayClient client = null;
		String orderId = null;
		
		try {
			 client = new RazorpayClient("rzp_test_577h02zBHnXglJ","5OkLdvp5UI8v4eF9onN5qviG");
			
			JSONObject options = new JSONObject();
			options.put("amount", "100");
			options.put("currency", "INR");
			options.put("receipt", "zxr456");
			options.put("payment_capture", true);
			Order order = client.Orders.create(options); 
			orderId = order.get("id");
			
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append(orderId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RazorpayClient client = null;
		try {
			client = new RazorpayClient("rzp_test_577h02zBHnXglJ","5OkLdvp5UI8v4eF9onN5qviG");
			JSONObject options = new JSONObject();
			options.put("razorpay_payment_id", request.getParameter("razorpay_payment_id"));
			options.put("razorpay_order_id",request.getParameter("razorpay_order_id"));
			options.put("razorpay_signature", request.getParameter("razorpay_signature"));
			boolean SigRes = Utils.verifyPaymentSignature(options, "5OkLdvp5UI8v4eF9onN5qviG");
			if(SigRes) {
				response.getWriter().append("Payment successful and Signature Verified");
			}else {
				response.getWriter().append("Payment failed and Signature not Verified");
			}
			
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
