//context path
var path='';
//change locale
function locale(requestUri, locale){
	window.location = requestUri + '?locale=' + locale;
	return false;
}
//submit form without submit buttons
function submit_form(nama, btn){
	var frm = document.getElementById(nama);
	if(frm){
		btn.disabled=true;
		frm.submit();
	}
	return false;
}
//check if enter is pressed on inputs
function checkEnter(e, nama){ // event object
	var characterCode; // literal character code
	if(e && e.which){ // NN4
		e = e;
		characterCode = e.which;
	} else{ // IE
		e = event;
		characterCode = e.keyCode;
	}
	if(characterCode == 13){ // enter
		var frm = document.getElementById(nama);
		if(frm) frm.submit(); //submit
		return false;
	} else{
		return true;
	}
}
//popup window with a given height & weight
function popWin(href, width, height) {
	var vWin;
	vWin = window.open(href,'','height='+height+',width='+width+	',toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,modal=yes,status=yes'+
		',left='+((screen.availWidth-width)/2)+
		',top='+((screen.availHeight-height)/2));
	vWin.opener = self;
} 
//limit string length in textareas
function textCounter(field, maxlimit) {
    if (field.value.length > maxlimit)
    field.value = field.value.substring(0, maxlimit);
}