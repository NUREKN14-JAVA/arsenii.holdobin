<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="user" class="kn_14_5_Holdobin.User" scope="session" />
<html>
<head>
<title>User Management. Details</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/details" method= "post">
		First Name: ${user.firstName } <br/>
		Last Name ${user.lastName}<br/>
		Date of Birth <fmt:formatDate value="${user.dateOfBirth}" type="date" dateStyle="medium"/><br/>
		<input type="submit" name="cancelButton" value="Cancel">
	</form>
</body>
</html>