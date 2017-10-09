<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>MDM System</title>

<spring:url value="/resources/core/css/mdmvalidation.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<header>
	<img src="<c:url value="/resources/core/images/logo.png" />"/>
</header>

<body>
	<div class = "login">
		<form method="post" action="ItemWFValidation">
			<p>Select Region</p>
			<select name="region">
				<option value="LATAM">LATAM</option>
			</select> 
			<button>Ir</button>
		</form>		
	</div>
<spring:url value="/resources/core/js/mdmvalidation.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>