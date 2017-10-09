<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Item WF Status ${region}</title>
<spring:url value="/resources/core/css/mdmvalidation.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
<body>
	<header>
		<img src="<c:url value="/resources/core/images/logo.png" />"/>
		<div class = "titulo">
			<h1>${region} - Oracle PIM Item Workflow Status</h1>
		</div>
		<div class="header">
			<form method ="post" action="ValidateItems">
			    <input type="text" name="items" placeholder="Type Item(s) separate with commas...">
			    <input type ="submit" class="button" name ="B1" value="Search">
			    <input type ="reset" class="button" value="Clear All">
			</form>
		</div>
		<p class="statusGreen"><img src="<c:url value="/resources/core/images/green.png" />" class="imgStatus"> Close Change Order</p>
		<p class="statusRed"><img src="<c:url value="/resources/core/images/red.png" />" class="imgStatus"> Open Change Order</p>
	</header>
	<div id="tabs">
	  <ul>
	    <li><a href="#tabs-1">With Change Order</a></li>
	    <li><a href="#tabs-2">Without Change Order</a></li>
	  </ul>
	  <div id="tabs-1">
	    <p></p>
	  </div>
	  <div id="tabs-2">
	    <p></p>
	  </div>
	</div>
	<footer>
			Copyright Sherwin-Williams - Developed by MDM International - All rights reserved
	</footer>
</body>
</html>