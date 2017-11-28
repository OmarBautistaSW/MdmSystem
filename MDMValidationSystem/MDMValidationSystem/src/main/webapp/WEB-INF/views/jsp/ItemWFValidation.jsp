<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="co" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Item WF Status ${sessionScope.region}</title>
<spring:url value="/resources/core/css/mdmvalidation.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" /> 
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
<link href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/themes/ui-darkness/jquery-ui.min.css" rel="stylesheet">
<link href="<c:url value="/resources/core/images/logosw.ico" />" rel="shortcut icon" type="image/x-icon" />
</head>
<body>
	<header>
		<table>
			<tr>
				<td width="20%"><img src="<c:url value="/resources/core/images/logo.png" />"/></td>
				<td width="60%" class="center"><h1>LACG - Oracle PIM Item Workflow Status</h1></td>
				<td width="20%" class="tdStatus"><img src="<c:url value="/resources/core/images/green.png" />" class="imgStatus">Complete Change Order</td>
			</tr>
			<tr>
				<td><span id="existItems" style="display:none">${existItems }</span></td>
				<td class="center">
					<form method ="post" action="ValidateItems">
						<input type="text" name="items" placeholder="Type Item(s) separate with commas...">
						<input type ="submit" class="button" value="Search">
						<input type ="reset" class="button" value="Clear All">
					</form>
				</td>
				<td class="tdStatus"><img src="<c:url value="/resources/core/images/red.png" />" class="imgStatus">Incomplete Change Order</td>
			</tr>
		</table>
	</header>
	<div class="error">
		<c:forEach items="${error }" var="error">
			<p>The item: <b>${error }</b> doesn't exist. Please verify it.</p><br>
		</c:forEach>
	</div>
	<div id="itemsContainer">
		<div id="divHeaderTable" style="display:none">
			<table>
				<tr>
					<th class="center" width="5%">Detail</th>
	  				<th width="5%">Status</th>
	  				<th width="15%">Item</th>
	  				<th width="20%">Description</th>
	  				<th width="10%">ICC</th>
	  				<th width="15%" class="center">Template</th>
	  				<th class="center" width="10%">Status</th>
	  				<th class="center" width="10%">Type</th>
	  				<th width="10%">Created By</th>
	  			</tr>			
			</table>
		</div>
		<c:forEach var="item" items="${listItems}">
			<div id="div${item.item }">
				<table class="tabInfoItem">
					<tr>
						<td width="5%" class="center">
							<img id="img${item.item}GralPlus" class="imgDetail" src="<c:url value="/resources/core/images/plus.png" />" onclick="showInfoItem('${item.item}', this.id)">
							<img id="img${item.item}GralMinus" class="imgDetail" src="<c:url value="/resources/core/images/minus.png" />" onclick="closeInfoItem('${item.item}', this.id)" style="display:none">
						</td>
				  		<c:choose>
				  			<c:when test="${item.isComplete == \"S\"  }">
				  				<td width="5%"><img class="imgStatus" src="<c:url value="/resources/core/images/green.png" />"></td>
				  			</c:when>
				  			<c:otherwise>
				  				<td width="5%"><img class="imgStatus" src="<c:url value="/resources/core/images/red.png" />"></td>
				  			</c:otherwise>
				  		</c:choose>
						<td width="15%">${item.item }</td>
				  		<td width="20%">${item.description }</td>
				  		<td width="10%">${item.ICC }</td>
				  		<td width="15%" class="center">${item.template }</td>
				  		<td width="10%" class="center">${item.status }</td>
				  		<td width="10%" class="center">${item.type }</td>
				  		<td width="10%">${item.createdBy }</td>
					</tr>
				</table>
				<div id="divButton${item.item }" style="display:none">
					<table class="tabButtons">
						<tr>
					  		<td width="10%" class="center"><input type ="submit" class="button" value="With CO" onclick="showCO('${item.item}')"></td>
					  		<td width="10%"><input type ="submit" class="button" value="Wtihout CO" onclick="showNCO('${item.item}')"></td>
					  		<td width="10%"></td>
					  		<td width="10%"></td>
					  		<td width="10%"></td>
					  		<td width="10%"></td>
					  		<td width="10%"></td>
					  		<td width="10%"></td>
					  		<td width="20%"></td>
						</tr>
					</table>
				</div>
				<div id="divWithCO${item.item }" style="display:none">
					<table class="tabItemDetail">
						<tr class="headDetailItem">
					  		<th width="5%" class="center">Detail</th>
					  		<th width="5%" class="center">Status</th>
					  		<th width="10%">Organization Code</th>
					  		<th width="15%">Organization Name</th>
					  		<th width="10%">Change Order</th>
					  		<th width="15%">Created By</th>
					  		<th width="10%">Creation Date</th>
					  		<th width="10%"></th>
					  		<th width="10%"></th>						
						</tr>
						<c:forEach items="${item.orgMap }" var="orgMap">
							<c:choose>
								<c:when test="${orgMap.value.name != \"WITHOUT CHANGE ORDER\" }">
									<tr>
										<td width="5%" class="center">
											<img class="imgDetail" id="img${item.item }${orgMap.value.orgCode}Plus" onclick="showOrgDetail('${item.item}','${orgMap.value.orgCode }',this.id)" src="<c:url value="/resources/core/images/plus.png" />">
											<img class="imgDetail" id="img${item.item }${orgMap.value.orgCode}Minus" style="display:none" onclick="closeOrgDetail('${item.item}','${orgMap.value.orgCode }',this.id)" src="<c:url value="/resources/core/images/minus.png" />">
										</td>
							  			<c:choose>
							  				<c:when test="${orgMap.value.orgComplete == \"S\" }">
							  					<td width="5%" class="center"><img class="imgDetail" src="<c:url value="/resources/core/images/green.png" />"></td>
							  				</c:when>
							  				<c:otherwise>
							  					<td width="5%" class="center"><img class="imgDetail" src="<c:url value="/resources/core/images/red.png" />"></td>
							  				</c:otherwise>
							  			</c:choose>	
										<td width="10%" class="center">${orgMap.value.orgCode }</td>
										<td width="15%">${orgMap.value.orgName }</td>
										<td width="10%">${orgMap.value.name }</td>
										<td width="15%">${orgMap.value.createdBy }</td>
										<td width="10%">${orgMap.value.creationDate }</td>
										<td width="15%"></td>
										<td width="15%"></td>
									</tr>
									<tr class="detalle${item.item }${orgMap.value.orgCode }" style="display:none">
										<th></th>
										<th></th>
										<th>Step Seq Num</th>
										<th>Workflow Status</th>
										<th>Group Assigned</th>
										<th>Step Start Date</th>
										<th>Step Reply Date</th>
										<th>Step Processed Days</th>
										<th>Workflow Processed Days</th>
									</tr>
									<c:forEach items="${orgMap.value.mapDetail }" var="mapDetail">
										<tr class="detalle${item.item }${orgMap.value.orgCode }" style="display:none">
											<td></td>
											<td></td>
											<td>${mapDetail.value.seqNum }</td>
											<td>${mapDetail.value.wokflowStatus }</td>
											<c:choose>
												<c:when test="${mapDetail.value.wokflowStatus != \"TIME_OUT\" }">
													<c:choose>
														<c:when test="${mapDetail.value.stepReplyDate != null }">
															<td>${mapDetail.value.groupAssigned }</td>
														</c:when>
														<c:otherwise>
															<td><a href="javascript:void(0)" onclick="showApprovalNames('${item.item }','${orgMap.value.orgCode }','${mapDetail.value.seqNum }' )">${mapDetail.value.groupAssigned }</a><span id="span${item.item }${orgMap.value.orgCode }${mapDetail.value.seqNum }" style="display:none">${mapDetail.value.approveNames }</span></td>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<td>${mapDetail.value.groupAssigned }</td>
												</c:otherwise>
											</c:choose>
											<td>${mapDetail.value.stepStartDate }
											<td>${mapDetail.value.stepReplyDate }</td>
											<td>${mapDetail.value.stepProcessedDays }</td>
											<td>${mapDetail.value.workflowProcessedDays }</td>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:forEach>
					</table>
				</div>
				<div id="divWithoutCO${item.item }" style="display:none">
					<table class="tabItemDetail">
						<tr class="headDetailItem">
							<th width="15%" class="center">Organization Code</th>
							<th width="25%">Organization Name</th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach items="${item.orgMap }" var="orgMap">
							<c:choose>
								<c:when test="${orgMap.value.name == \"WITHOUT CHANGE ORDER\" }">
									<tr>
										<td width="15%" class="center">${orgMap.value.orgCode }</td>
										<td width="15%">${orgMap.value.orgName }</td>
										<td width="15%">${orgMap.value.creationDate }</td>
										<td width="15%">${orgMap.value.createdBy }</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</c:when>
							</c:choose>
						</c:forEach>
					</table>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="footer">Copyright Sherwin-Williams - Developed by MDM International - All rights reserved</div>
</body>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>
<spring:url value="/resources/core/js/mdmvalidation.js" var="mdmvalidationJs"/>
<script src="${mdmvalidationJs }"></script>
</body>
</html>