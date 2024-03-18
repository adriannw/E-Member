<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<HEAD>
<link href="${path}/include/css/general.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${path}/include/js/overlib.js"></script>
<script type="text/javascript" src="${path}/include/js/general.js"></script>
<script type="text/javascript" src="${path}/include/js/jquery.min.js"></script>
<script type="text/javascript" src="${path}/include/js/top/cepr01030101JSP_top.js"></script> 
<script type="text/javascript">
var path = '${path}';
var jn_bank = '${sessionScope.jn_bank}';
function ajax(param, tipe, jn_bank ){
	$("#namaAdminBCList").empty();
	$("#namaAdminBCList").append("<option value=''>Loading</option>");
	
       $.ajax({
		//url: url+"main/module/lead/get_data/",
		url: "${path}/api/proposal/cariAdminBC",
		data:{'cariNamaAdminBC':param, 'type':tipe, 'jn_bank':jn_bank},
	    dataType : 'json',
		success: function(map){
			if(map.result=="sukses"){
    			$("#namaAdminBCList").empty();
    			$("#namaAdminBCList").append("<option value=''></option>");
    			for(var i=0; i<map.hasil.length; i++){
        			var agen = map.hasil[i];
        			var kode = agen.KODE_AGENT;
        			if(agen.KODE_AGENT == null) {
        				kode = "";
        			}
        			var selected = "";
        			if(tipe == "nama") {
        				if(param.toUpperCase() == agen.NAMA) {
	        				selected = " selected='selected'";
	        			}
        			} else {
        				if(param == agen.KODE_AGENT) {
	        				selected = " selected='selected'";
	        			}
        			}
        			
        			$("#namaAdminBCList").append("<option value='" +kode+"'" + selected + ">" + agen.NAMA + "</option>");
    			
    			}         			
			
			}																        			
			
		}
	});	
}
</script>

<script type="text/javascript">


      function checkEvent()
      {
    	 var x= document.forms[ 0 ].theEvent.value;
    	 var id= document.getElementById('selectedSmcId').value;
    	//  alert(x);
    	  
    	
            if( document.forms[ 0 ].theEvent.value == 'onSelectRow2' )
            {
            	//alert('tes');
                document.getElementById('targetParam').name = CEPR01020202JSP;
                document.getElementById('formpost').submit();
            }
            if( document.forms[ 0 ].theEvent.value == 'onSelectRow3' )
            {
            //	alert('tes');
                document.getElementById('targetParam').name = CEPR01030101JSP;
                document.getElementById('formpost').submit();
            }  
            if( document.forms[ 0 ].theEvent.value == 'onSelectRow4' )
            {
            	//alert('tes');/wepr00000000.htm
                document.getElementById('targetParam').name = CEPR01030101JSP;
                document.getElementById('formpost').submit();
            }   
           	else if( document.forms[ 0 ].theEvent.value == 'onChangeLtmId' )
            {
            	document.forms[ 0 ].ins.value = '';
                document.getElementById('targetParam').name = CMED01030202JSP;
                document.getElementById('myform').submit();
            }    
        	 else if( document.forms[ 0 ].theEvent.value == 'onPressButtonPrev' )
            {
            	document.forms[ 0 ].ins.value = '';
                document.getElementById('targetParam').name = CMED01030201JSP;
                document.getElementById('myform').submit();
            }    
        	else if( document.forms[ 0 ].theEvent.value == 'onPressButtonCancel' )
            {
                document.getElementById('targetParam').name = CMED00000000JSP;
                document.getElementById('myform').submit();
            }   
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonNext' )
            {
        		document.getElementById('formpost').action='wepr00000000.htm';        	
        		document.getElementById('formpost').submit();
        		document.getElementById('formpost').target = '_self';
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonNext2' )
            {
            	document.getElementById('docFrame').src='${path}/wepr00000001.htm?foto='+id;
        	//	document.getElementById('formpost').action='wepr00000001.htm';        	
        	//	document.getElementById('formpost').submit();
        		document.getElementById('formpost').target = '_self';
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonNextPage3' )
            {                   
              //  var flagProv= document.getElementById('cmed01030202Form.jenisProviderOrNotCd').value;
              //  var namaRs=document.getElementById('cmed01030202Form.inputProvider').value;
              //  if (flagProv == null || flagProv == '')
              //  {
              //    alert("Jenis RS harus diisi"); 
              //  }else if (flagProv == '0' && (namaRs.trim() == '' || namaRs.trim() == null))
              //  {
              //    alert("NonProvider: Nama RS harus diisi");
              //  } else
                  document.getElementById('targetParam').name =CMED01030203JSP;
                  document.getElementById('myform').submit();
             // }   
            }   
         	else if( document.forms[ 0 ].theEvent.value == 'onPressButtonResetPage2' )
            {
            	document.forms[ 0 ].ins.value = '';
                document.getElementById('targetParam').name = CMED01030202JSP;
                document.getElementById('myform').submit();
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonRefresh' )
            {
            	document.forms[ 0 ].ins.value = '';
                document.getElementById('targetParam').name = CMED01030202JSP;
                document.getElementById('myform').submit();
            }
           	else if( document.forms[ 0 ].theEvent.value == 'onPressButtonUpdateLtmId' )
            {        
                //var flagProv= document.getElementById('cmed01030202Form.jenisProviderOrNotCd').value;
                //var namaRs=document.getElementById('cmed01030202Form.inputProvider').value;
                //if (flagProv == null || flagProv == '')
               // {
               //   alert("Jenis RS harus diisi"); 
               // }else if (flagProv == '0' && (namaRs.trim() == '' || namaRs.trim() == null))
               // {
               //   alert("NonProvider: Nama RS harus diisi");
              //  } else
              //  {
                  document.getElementById('targetParam').name = CMED01030205JSP;
                  document.getElementById('myform').submit();
              //}                              
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonProvider' )
            {
            	document.forms[ 0 ].ins.value = '';
                document.getElementById('targetParam').name = CMED01030202JSP;
                document.getElementById('myform').submit();
            }
           else if( document.forms[ 0 ].theEvent.value == 'onChangeJenis' )
            {
           
            	document.forms[ 0 ].ins.value = '';
                document.getElementById('targetParam').name = CMED01030202JSP;
                document.getElementById('myform').submit();
            }
 

            
      }      
 </script>

<!--
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
-->
<style type="text/css">
<!--
.style2 {font-size: 20px}
.style3 {color: #FF9900; font-size: 12px;}
.style5 {color: #FFFFFF}
-->
</style>
 

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="formpost" commandName="cepr01030000HoldingForm" name="">
<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>
  <!--DWLayoutTable-->
  
	<TR>
		<TD height="10" colspan="9" style="background-image:url(${path}/include/images/bg_form2.jpg); background-repeat:repeat-x; ">
          <p>&nbsp;</p>
       
	</TR>
	
       
          </table>
          
	  
	    
	    <td height="10" colspan="5" style="background-image:url(${path}/include/images/bg_form3.jpg); background-repeat:repeat-x; ">
		
	    </td>
          
	</TR>
	<TR>
		<TD height="5" class="fontLabel1"><fmt:message key="words.presentedBy"/></TD>
		<TD COLSPAN=8>&nbsp;			</TD>
    </TR>
	<TR>
	
       <table id="thetable" width=100% cellPadding=3 cellSpacing=1 class="nobottom" style="table-LAYOUT: fixed;" >
      
		
		<c:set var="backGroundColor" value="#EAEAEA"/>
                		
		<c:forEach var="ViewListGroup" items="${allList}" varStatus="xt">
    
        	<c:choose>
        		<c:when test='${"#EAEAEA" == backGroundColor}'>
        			<c:set var="backGroundColor" value="#FFFFFF"/>
        		</c:when>
        		<c:otherwise>
            		<c:set var="backGroundColor" value="#EAEAEA"/>
        		</c:otherwise>
    		</c:choose>
    
           <tr class="rowClass" style="background-color: ${backGroundColor};" onClick="document.getElementById( 'selectedGroupId' ).value = '${ViewListGroup.groupId}'; document.getElementById('theEvent').value='onSelectRow'; checkEvent()" onMouseOver="change_bgcolor( this, '#FFD6CA' );" onMouseOut="change_bgcolor(this, '${backGroundColor}')" valign="top" >
				<td align="center">
                		
              	
              	<input type="button"  width="28" height="18"  border="0" onClick="document.getElementById( 'selectedSmcId' ).value = '${ViewListGroup.id}' ;
				        document.getElementById( 'theEvent' ).value ='onSelectRow2'; checkEvent();" title="EDIT" value="EDIT"/>

					<input type="button"  width="28" height="18"  border="0" onClick="document.getElementById( 'selectedSmcId' ).value = '${ViewListGroup.id}';
				        document.getElementById( 'theEvent' ).value ='onSelectRow4'; checkEvent();" title="DELETE"  value="DEL"/>
				        
				        <input type="button"  width="28" height="18"  border="0" onClick="document.getElementById( 'selectedSmcId' ).value = '${ViewListGroup.foto}' ;
				        document.getElementById( 'theEvent' ).value ='onPressButtonNext2'; checkEvent();" title="VIEW" value="VIEW"/>

				 </td>
				
                <td class="fontForm" width="4">
                    <label for="ViewListGroup[${xt.index}].groupId" >
                        ${ViewListGroup.id}
                    </label>
                </td>
                <td class="fontForm">
                    <label for="ViewListGroup[${xt.index}].groupName" >
                        ${ViewListGroup.nama}
                    </label>
                </td>
                <td class="fontForm">
                    <label for="ViewListGroup[${xt.index}].groupName" >
                        ${ViewListGroup.nomor_hp}
                    </label>
                </td>
                  <td class="fontForm">
                    <label for="ViewListGroup[${xt.index}].groupName" >
                        ${ViewListGroup.tgl_lahir}
                    </label>
                </td>
                 <td class="fontForm">
                    <label for="ViewListGroup[${xt.index}].groupName" >
                        ${ViewListGroup.email}
                    </label>
                </td>
                 <td class="fontForm">
                    <label for="ViewListGroup[${xt.index}].groupName" >
                        ${ViewListGroup.no_ktp}
                    </label>
                </td>
                 <td class="fontForm">
                    <label for="ViewListGroup[${xt.index}].groupName" >
                        ${ViewListGroup.foto}
                    </label>
                </td>
          </tr>
        </c:forEach>
   
                
    </table>
	
	  <iframe name="docFrame" src="" id="docFrame" width="100%" height="400"> Please Wait....</iframe>
        
	
	
		<TD height="7" colspan="9" style="background-image:url(${path}/include/images/bg_form4.jpg); background-repeat:repeat-x; ">
          <p>&nbsp;</p>
        
         
          
         
          
	  </TD>
	</TR>
	<TR>
		
        <input type="hidden" id="targetParam"/>
      
        
		<TD ><div align="center
		
		  <input type="button" name="btncari23" value="create Xls"
													onclick="document.getElementById('theEvent').value='onSelectRow3'; checkEvent()">
		<br>			        
			<input type="button" name="btncari22" value="create Xls"
													onclick="document.getElementById('theEvent').value='onSelectRow3'; checkEvent()">	        
		<input type="button" name="btncari22" value="download Xls"
													onclick="document.getElementById('theEvent').value='onPressButtonNext'; checkEvent()">
		
    </TR>

	<TR>
		<TD height="19" style="background-image:url(${path}/include/images/web_proposal_38.jpg); ">&nbsp;			</TD>
		<TD COLSPAN=9 style="background-image:url(${path}/include/images/web_proposal_39.jpg); ">&nbsp;			</TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_40.jpg); ">&nbsp;			</TD>
    </TR>
	<TR>
		<TD height="100" COLSPAN=13 style="background-image:url(${path}/include/images/web_proposal_41.jpg); ">			
		
	</TR>
	
	<TR>
		<TD height="1">
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=178 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=38 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=135 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=71 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=68 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=150 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=64 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=223 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=79 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=7 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=50 HEIGHT=1 ALT=""></TD>
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=40 HEIGHT=1 ALT=""></TD>
		<TD></TD>
    </TR>
</TABLE>

<form:hidden path="cepr01030101Form.downloadFlag"/>
<input type="hidden" name="flag" value="0" />
<input type="hidden" name="selectedNamaPlan" id="selectedNamaPlan"/>
<input type="hidden" name="selectedSmcId" id="selectedSmcId" />
<input type="hidden" name="theEvent" id="theEvent" />


</form:form>
<!-- End ImageReady Slices -->
<script type="text/javascript">
var downloadUrlSession = '${downloadUrlSession}';
</script>
<script type="text/javascript" src="${path}/include/js/bottom/cepr01030101JSP_bottom.js"></script>

</BODY>
</HTML>