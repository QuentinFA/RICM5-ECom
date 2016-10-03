<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
</head>
<body>

</body>
</html>

<table cellpadding="2" cellspacing="2" border="1">
	<tr>
		<tr> product_id </tr>
		<tr> user_id	</tr>
		<tr> price </tr>
		<tr> type  </tr>
		<tr> title </tr>
		<tr> description</tr>
	</tr>
	<c:forEach var="product" items="${listProduct}">
	<tr> 
			<td> ${product.productId}</td>
			<td> ${product.userId}</td>
			<td> ${product.price}</td>
			<td> ${product.type}</td>
			<td> ${product.title}</td>
			<td> ${product.description}</td>
	
	</tr>
	</c:forEach>
</table>   

