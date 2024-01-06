<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>付款</title>
</head>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.maoyan.util.AlipayConfig"%>
<%@ page import="com.alipay.api.*"%>
<%@ page import="com.alipay.api.request.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setContentType("text/html; charset=utf-8");
	request.setCharacterEncoding("utf-8");
	//获得初始化的AlipayClient
	AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	
	//设置请求参数
	AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	alipayRequest.setReturnUrl(AlipayConfig.return_url);
	alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

	Date date = new Date();//获取当前的日期
	SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	String order_id = df2.format(date);  //生成订单id

	//商户订单号，商户网站订单系统中唯一订单号，必填
	//String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	//付款金额，必填
	String total_amount = new String(request.getParameter("price").getBytes("ISO-8859-1"),"UTF-8");
	//订单名称，必填
	//String subject = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");
	String subject = "电影票";
	//商品描述，可空
	String body = request.getParameter("body");
	
	alipayRequest.setBizContent("{\"out_trade_no\":\""+ order_id +"\","
			+ "\"total_amount\":\""+ total_amount +"\","
			+ "\"subject\":\""+ subject +"\"," 
			+ "\"body\":\""+ body +"\","
			+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	

	
	//请求
	String result = alipayClient.pageExecute(alipayRequest).getBody();
	
	//输出
	out.println(result);
%>
<body>
</body>
</html>