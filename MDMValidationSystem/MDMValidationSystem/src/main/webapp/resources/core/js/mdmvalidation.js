function showInfoItem(item, id){
	$(document.getElementById("divButton" + item)).show();
	$(document.getElementById("divWithCO" + item)).show();
	changeImgButton(id);
}

function closeInfoItem(item,id){;
	$(document.getElementById("divButton" + item)).hide();
	$(document.getElementById("divWithCO" + item)).hide();
	$(document.getElementById("divWithoutCO" + item)).hide();
	changeImgButton(id);
}

function showOrgDetail(item,org,id){
	var clase = "detalle" + item + org;
	$(document.getElementsByClassName(clase)).show();
	changeImgButton(id);
}

function closeOrgDetail(item,org,id){
	$(document.getElementsByClassName("detalle"+item+org)).hide();
	changeImgButton(id);
}

function changeImgButton(id){
	if(id.indexOf("Plus") >= 0){
		$(document.getElementById(id)).hide();
		var idHide = id.substring(0,id.indexOf("Plus")) + "Minus";
		$(document.getElementById(idHide)).show();
	}else{
		$(document.getElementById(id)).hide();
		var idShow = id.substring(0,id.indexOf("Minus")) + "Plus";
		$(document.getElementById(idShow)).show();
	}
}

function showHeader(){
	var existItem = $('#existItems').text();
	var region = $('#region').text();
	if(existItem == "N"){
		$('#divHeaderTable').hide();
	}else{
		$('#divHeaderTable').show();
	}
	if (region != 'LACG'){
		if(region == 'EMEA'){
			$("body").removeClass('lacg').addClass('emea');
		}else{
			$("body").removeClass('lacg').addClass('apac');
		}
	}
}

window.onload = showHeader();

function showCO(item){
	$(document.getElementById("divWithoutCO" + item)).hide();
	$(document.getElementById("divWithCO" + item)).show();
}

function showNCO(item){
	$(document.getElementById("divWithCO" + item)).hide();
	$(document.getElementById("divWithoutCO" + item)).show();
}

function showApprovalNames(item, org, seq){
	$(document.getElementById("span"+item+org+seq)).dialog({
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

$(function(){
	$("form[name='SearchForm'").validate({
		rules: {
			items: "required"
		},
		messages: {
			items: "Please enter an item"
		},
		errorElement : 'div',
		errorLabelContainer:'.errorSearch',
		submitHandler: function(form){
			form.submit();
		}
	})
})