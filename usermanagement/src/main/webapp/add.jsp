<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="user" class="kn_14_5_Holdobin.User" scope="session" />
<html>
<head>
<title>User Management. Add</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/add" method= "post">
		First Name <input type ="text" name="firstName">
		Last Name <input type ="text" name="lastName">
		Date of Birth <input type ="text" name="dateOfBirth">
		<input type="submit" name="okButton" value="Ok">
		<input type="submit" name="cancelButton" value="Cancel">
	</form>
<c:if test="${requestScope.error != null}">
	<script>
		alert('${request.Scope.error}');
	</script>
</c:if>
</body>
</html>