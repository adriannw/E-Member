var ctxPath="E-Proposal";var CEPR01030101JSP="_target0";var CEPR01030102JSP="_target1";var CEPR01030301JSP="_target2";var CEPR01030401JSP="_target3";var CEPR00000000DownloadDocumentJSP="_target4";var CEPR01030103JSP="_target5";var CEPR01030104JSP="_target6";var CEPR01030105JSP="_target7";var CEPR01030106JSP="_target8";var CEPR01030501JSP="_target9";var CEPR01030502JSP="_target10";var CEPR01030701JSP="_target11";var CEPR01030107JSP="_target12";var CEPR01030108JSP="_target13";var CEPR01030109JSP="_target14";var CEPR01030110JSP="_target15";var CEPR01030111JSP="_target16";var CEPR01030112JSP="_target17";var CEPR01030901JSP="_target18";var CEPR01030904JSP="_target19";var CEPR01030113JSP="_target20";var CEPR01030114JSP="_target21"; var CEPR01020101JSP="_target0";var CEPR01020201JSP="_target1";var CEPR01020202JSP="_target2";var CEPR01020301JSP="_target3";var CEPR01020401JSP="_target4";var is_gecko=/gecko/i.test(navigator.userAgent);var is_ie=/MSIE/.test(navigator.userAgent);String.prototype.trim=function(){return this.replace(/^\s*/,"").replace(/\s*$/,"")};function formatCurrency(num){num=num.toString().replace(/\,/g,"");if(isNaN(num)){num="0"}sign=(num==(num=Math.abs(num)));num=Math.floor(num*100+0.50000000001);cents=num%100;num=Math.floor(num/100).toString();if(cents<10){cents="0"+cents}for(var i=0;i<Math.floor((num.length-(1+i))/3);i++){num=num.substring(0,num.length-(4*i+3))+","+num.substring(num.length-(4*i+3))}return(((sign)?"":"-")+num+"."+cents)}function showFormatCurrency(paramName,value){document.getElementById(paramName).value=formatCurrency(value)}function formatInteger(num){num=num.toString().replace(/\,/g,"");if(isNaN(num)){num="0"}sign=(num==(num=Math.abs(num)));num=Math.floor(num*100+0.50000000001);cents=num%100;num=Math.floor(num/100).toString();if(cents<10){cents="0"+cents}for(var i=0;i<Math.floor((num.length-(1+i))/3);i++){num=num.substring(0,num.length-(4*i+3))+","+num.substring(num.length-(4*i+3))}return(((sign)?"":"-")+num)}function showFormatInteger(paramName,value){document.getElementById(paramName).value=formatInteger(value)}function inputDate(nama,nilai,aktif,onchg,tabidx){var nilai2="";if(!nilai||nilai==""){nilai="__/__/____"}else{nilai2=nilai}if(!onchg){onchg=""}var disabled="";if(aktif){disabled=" disabled "}if(!tabidx){tabidx=""}else{tabidx='tabindex="'+tabidx+'"'}document.write("<input "+disabled+'type="text" '+tabidx+' name="_'+nama+'" id="_'+nama+'" value="'+nilai+'" maxlength="10" size="11" style="text-align: center; font-size:11px; "  onmouseup="return checkDate(\''+nama+"', this, event, '"+escape(onchg)+"')\" onkeydown=\"return checkDate('"+nama+"', this, event, '"+escape(onchg)+"')\">\n<input "+disabled+'type="hidden" name="'+nama+'" id="'+nama+'" value="'+nilai2+'">\n<img '+disabled+'src="/'+ctxPath+'/include/images/calendar.jpg" align="baseline" title="Calendar" border="0" id="img_'+nama+'">');function chg(cal){var date=cal.date;document.getElementById(nama).value=date.print("%d/%m/%Y");eval(unescape(onchg))}Calendar.setup({inputField:"_"+nama,ifFormat:"%d/%m/%Y",button:"img_"+nama,align:"Tl",onUpdate:chg})}function setSelectionRange(input,start,end){if(is_gecko){input.setSelectionRange(start,end)}else{var range=input.createTextRange();range.collapse(true);range.moveStart("character",start);range.moveEnd("character",end-start);range.select()}}function getSelectionStart(input){if(is_gecko){return input.selectionStart}var range=document.selection.createRange();var isCollapsed=range.compareEndPoints("StartToEnd",range)==0;if(!isCollapsed){range.collapse(true)}var b=range.getBookmark();return b.charCodeAt(2)-2}function getSelectionEnd(input){if(is_gecko){return input.selectionEnd}var range=document.selection.createRange();var isCollapsed=range.compareEndPoints("StartToEnd",range)==0;if(!isCollapsed){range.collapse(false)}var b=range.getBookmark();return b.charCodeAt(2)-2}function addZero(vNumber){return((vNumber<10)?"0":"")+vNumber}function formatDate(vDate,vFormat){var vDay=addZero(vDate.getDate());var vMonth=addZero(vDate.getMonth()+1);var vYearLong=addZero(vDate.getFullYear());var vYearShort=addZero(vDate.getFullYear().toString().substring(3,4));var vYear=(vFormat.indexOf("yyyy")>-1?vYearLong:vYearShort);var vHour=addZero(vDate.getHours());var vMinute=addZero(vDate.getMinutes());var vSecond=addZero(vDate.getSeconds());var vDateString=vFormat.replace(/dd/g,vDay).replace(/MM/g,vMonth).replace(/y{1,4}/g,vYear);vDateString=vDateString.replace(/hh/g,vHour).replace(/mm/g,vMinute).replace(/ss/g,vSecond);return vDateString}function inputDateWithAge(nama,nilai,aktif,onchg,tabidx,formAgeName,additionalJsCommand){var nilai2="";if(!nilai||nilai==""){nilai="__/__/____"}else{nilai2=nilai}if(!onchg){onchg=""}var disabled="";if(aktif){disabled=" disabled "}if(!tabidx){tabidx=""}else{tabidx='tabindex="'+tabidx+'"'}document.write("<input "+disabled+'type="text" '+tabidx+' name="_'+nama+'" id="_'+nama+'" value="'+nilai+'" maxlength="10" size="11" style="text-align: center; font-size:11px; "  onmouseup="return checkDate(\''+nama+"', this, event, '"+escape(onchg)+"')\" onkeydown=\"return checkDate('"+nama+"', this, event, '"+escape(onchg)+"')\" onchange=\"document.getElementById('"+formAgeName+"' ).value = getAge(this.value) == null? '':getAge(this.value);"+additionalJsCommand+'" onblur="document.getElementById(\''+formAgeName+"' ).value = getAge(this.value) == null? '':getAge(this.value);"+additionalJsCommand+'">\n<input '+disabled+'type="hidden" name="'+nama+'" id="'+nama+'" value="'+nilai2+'">\n<img '+disabled+'src="/'+ctxPath+'/include/images/calendar.jpg" align="baseline" title="Calendar" border="0" id="img_'+nama+'">');function chg(cal){var date=cal.date;document.getElementById(nama).value=date.print("%d/%m/%Y");eval(unescape(onchg))}Calendar.setup({inputField:"_"+nama,ifFormat:"%d/%m/%Y",button:"img_"+nama,align:"Tl",onUpdate:chg})}function getLastDateOfMonth(Year,Month){return(new Date((new Date(Year,Month+1,1))-1))}function getAgeRider(str){var date_of_birth;var fix_number=6;var currentDate=new Date();date_of_birth=str;var day=currentDate.getDate();var month=currentDate.getMonth()+1;var year=currentDate.getFullYear();var Dob=date_of_birth.split("/");var Dob_day=Dob[0];var Dob_month=Dob[1];var Dob_year=Dob[2];var agey=year-Dob_year;var agem=month-Dob_month;var aged=day-Dob_day;alert("aged_"+aged);alert("agem_"+agem);var agem_flag=agem;if(agem<0){agem=(12-Dob_month)+month}if(agey==0){if(agem>0){if(agem==1){if(aged<0){}}else{agey=agey+1}}else{if(aged>15){agey=agey+1}}}else{if(aged>0){agem=agem-1}else{if(aged<=0){}}if(agem_flag<0){agey=agey-1}else{if(agem_flag>0){}}if(agem<fix_number){agey=agey}else{if(agem>=fix_number){agey=agey+1}}if(agey<0){agey=0}}age=agey;return age}function checkDate(nama,elm,evt,onchg){keyCode=evt.keyCode;awal=getSelectionStart(elm);akhir=getSelectionEnd(elm);nilai=elm.value;var result=false;is_date_field=true;if(keyCode==13||keyCode==9){result=true}else{if(awal!=akhir){var a=0;if(awal>=6){a=6}else{if(awal>=3){a=3}}setSelectionRange(elm,a,a)}else{if(keyCode==37||keyCode==39){result=true}else{if(keyCode==8){if(awal==3||awal==6){evt.keyCode=37;result=true}else{if(awal==0){}else{elm.value=nilai.substring(0,awal-1)+"0"+nilai.substring(awal);var kurang=1;if(awal==4||awal==7){kurang=2}setSelectionRange(elm,awal-kurang,awal-kurang)}}}else{if(awal==2||awal==5){setSelectionRange(elm,awal+1,awal+1)}else{if(awal==10){}else{if(keyCode==0||!keyCode){if(awal==2||awal==5){setSelectionRange(elm,awal+1,awal+1)}}else{if((keyCode>=48&&keyCode<=57)||(keyCode>=96&&keyCode<=105)){if(keyCode>=96){keyCode-=48}keyChar=String.fromCharCode(keyCode);elm.value=nilai.substring(0,awal)+keyChar+nilai.substring(awal+1);var tambah=1;if(awal==1||awal==4){tambah=2}setSelectionRange(elm,awal+tambah,awal+tambah)}}}}}}}}if(elm.value=="__/__/____"||elm.value=="00/00/0000"){document.getElementById(nama).value=""}else{document.getElementById(nama).value=elm.value}if(elm.value.indexOf("_")==-1){eval(unescape(onchg))}else{document.getElementById(nama).value=""}return result}function dateExists(str){str=String(str);var re=new RegExp("^(\\d{2})/(\\d{2})/(\\d{4})$");if(!re.test(str)){return false}var matches=str.match(re);var d=Number(matches[1]);var m=Number(matches[2]);var y=Number(matches[3]);var isLeapYear=(y%4==0);if(m>12){return false}if(d>31||d<1){return false}m--;var daysInMonth=[31,28,31,30,31,30,31,31,30,31,30,31];if(isLeapYear){daysInMonth[1]++}if(!(m in daysInMonth)){return false}if(d>daysInMonth[m]){return false}return true}function getAge(str){var age=0;var currentDate=new Date();var day=currentDate.getDate();var month=currentDate.getMonth()+1;var year=currentDate.getFullYear();var Dob=str.split("/");var Dob_day=Dob[0];var Dob_month=Dob[1];var Dob_year=Dob[2];var li_month=0;var li_add=0;if(Dob_year!=year){if(month>=Dob_month){age=year-Dob_year}else{age=(year-Dob_year)-1;li_add=12}li_month=month+li_add-Dob_month;if(li_month>=6){if(li_month==6){if((day-Dob_day)>=0){age=age+1}}else{age=age+1}}}if(age<0){age=0}return age}function showForm(paramsId,isShowed){if(isShowed=="true"){document.getElementById(paramsId).style.display=""}else{document.getElementById(paramsId).style.display="none"}}function disableForm(id,isDisabled){document.getElementById(id).disabled=isDisabled}function checkForm(id,isChecked){document.getElementById(id).checked=isChecked}function isCheckboxArray(obj){return((typeof obj.type!="string")&&(obj.length>0)&&(obj[0]!=null)&&(obj[0].type=="checkbox"))}function isIPad(){return navigator.platform=="iPad"};