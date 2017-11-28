function showInfoItem(item, id){
	$('#divButton'+item).show();
	$('#divWithCO'+item).show();
//	hideElements(item);
	changeImgButton(id);
}

function closeInfoItem(item,id){
	$('#divButton'+item).hide();
	$('#divWithCO'+item).hide();
	$('#divWithoutCO'+item).hide();
	changeImgButton(id);
}

function showOrgDetail(item,org,id){
	$('.detalle'+item+org).show();
	changeImgButton(id);
}

function closeOrgDetail(item,org,id){
	$('.detalle'+item+org).hide();
	changeImgButton(id);
}

function hideElements(item){
	$("[class^='detalle"+item+"']").hide();
	$("[class^='tr'").hide();
}

function changeImgButton(id){
	var idStr = id;
	if(idStr.indexOf("Plus") >= 0){
		$('#'+id).hide();
		var idHide = idStr.substring(0,idStr.indexOf("Plus")) + "Minus";
		$('#'+idHide).show();
	}else{
		$('#'+id).hide();
		var idShow = idStr.substring(0,idStr.indexOf("Minus")) + "Plus";
		$('#'+idShow).show();
	}
}

function showTabItems(items){
	if(items == null){
		$('#itemsContainer').hide();
	}else{
		$('#itemsContainer').hide();
	}
}

function showHeader(){
	var existItem = $('#existItems').text()
	if(existItem == "N"){
		$('#divHeaderTable').hide();
	}else{
		$('#divHeaderTable').show();
	}
}

window.onload = showHeader();

function showCO(item){
	$('#divWithoutCO'+item).hide();
	$('#divWithCO'+item).show();
}

function showNCO(item){
	$('#divWithCO'+item).hide();
	$('#divWithoutCO'+item).show();
}

function showApprovalNames(item, org, seq){
	$("#span"+item+org+seq).dialog({
		title: "People who can approve",
		modal: true,
		resizable: false,
		buttons: {
			"OK": function() {
				$(this).dialog("close");
			}
		}
	});
}