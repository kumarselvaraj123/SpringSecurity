<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<jsp:include page="welcome.jsp" />
<body>
	<h3 style="color: red;">send Mail</h3>

	<div id="sendMail">
		<form:form action="/saveMail" method="post"
			 modelAttribute="cus">
			<p>
				<label>Mail To</label>
 <select name="users">
            <c:forEach items="${users}" var="listValue">
                <option value="${listValue.username}">
              
                    ${listValue.username}
                </option>
            </c:forEach>
        </select>
        
   			</p>
			<p>
				<label>Message</label>
				<form:textarea path="email" />
			</p>
			
			<input type="SUBMIT" value="Submit" />
		</form:form>
	</div>
</body>
</html>
