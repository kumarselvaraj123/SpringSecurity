<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
<title>Show Customers</title>
</head>
<body>
	<jsp:include page="welcome.jsp" />
	      
	<h3 style="color: red;">Show All mails</h3>
	<ul>
		<c:forEach var="listValue" items="${email}">
			<li>${listValue.message}</li>
		</c:forEach>
	</ul>

		
		
</body>
</html>