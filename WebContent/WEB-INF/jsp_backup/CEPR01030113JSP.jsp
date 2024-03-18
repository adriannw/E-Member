<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<HEAD>
<link href="${path}/include/css/general.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
.style2 {font-size: 20px}
.style3 {color: #FF9900}
.style5 {color: #FFFFFF}
-->
</style>

 <script type="text/javascript">

      
         function changeButtonOkToGlow()
        {
            document.getElementById( "buttonOkId" ).src = '${path}/include/images/bt_ok-ro.gif';
        }
        function changeButtonOkToNormal()
        {
            document.getElementById( "buttonOkId" ).src = '${path}/include/images/bt_ok.gif';
        }
		function changeButtonCancelToGlow()
        {
            document.getElementById( "buttonCancelId" ).src = '${path}/include/images/bt_batal-ro.gif';
        }
        function changeButtonCancelToNormal()
        {
            document.getElementById( "buttonCancelId" ).src = '${path}/include/images/bt_batal.gif';
        }
		function changeButtonBersihkanToGlow( index )
        {
            document.getElementById( "buttonBersihkanId".concat(index) ).src = '${path}/include/images/bt_bersihkan-ro.gif';
        }
        function changeButtonBersihkanToNormal( index )
        {
            document.getElementById( "buttonBersihkanId".concat(index) ).src = '${path}/include/images/bt_bersihkan.gif';
        }
		function changeButtonHomeToGlow()
        {
            document.getElementById( "buttonHomeId" ).src = '${path}/include/images/bt_home-ro.gif';
        }
        function changeButtonHomeToNormal()
        {
            document.getElementById( "buttonHomeId" ).src = '${path}/include/images/bt_home.gif';
        }
		function changeButtonLogOffToGlow()
        {
            document.getElementById( "buttonLogOffId" ).src = '${path}/include/images/bt_logoff-ro.gif';
        }
        function changeButtonLogOffToNormal()
        {
            document.getElementById( "buttonLogOffId" ).src = '${path}/include/images/bt_logoff.gif';
        }
		
		
		function checkEvent()
        {
            
            
			var theEvent = document.forms[ 0 ].theEvent.value;
            
			
            if( theEvent == 'onPressButtonOk' )
            {
         
            	  var plan = document.getElementById('plan').value;
            	if(plan=='214' ){
            		 document.getElementById('targetParam').name = CEPR01030102JSP;
                	 document.getElementById('formpost').submit();
            	}else{
            	 	document.getElementById('targetParam').name = CEPR01030103JSP;
                	document.getElementById('formpost').submit();
            	}
            }
            else if( theEvent == 'onPressButtonCancel' )
            {
          	   var plan = document.getElementById('plan').value;
            	if(plan=='214' ){
            		 document.getElementById('targetParam').name = CEPR01030102JSP;
                	 document.getElementById('formpost').submit();
            	}else{
            	 	document.getElementById('targetParam').name = CEPR01030103JSP;
                	document.getElementById('formpost').submit();
            	}
            }
			
        }
		
		function clearRowContent( rowIdx )
        {                      
            document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].name' ).value = '';
            document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].birthDay' ).value = '';
           // document.getElementById( '_cepr01030113Form.participantVOList[' + rowIdx + '].birthDay' ).value = '__/__/____';
            document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].age' ).value = '';
            document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].lsre_id').selectedIndex = 0;
            document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].sexCd').selectedIndex = 0;
            document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].medicalPlusRbFlag').checked = '';          
          
        }
		
		function calculateAge(formSource,formTarget){
		
        // untuk format dd/mm/yyyy
        	var form = document.forms[ 0 ];
        	var date_of_birth;
        	var age = 0;
        	var pass = 1;
        	var fix_number = 6;// untuk bulan
        	var currentDate = new Date();
        	// inputan
        	date_of_birth = document.getElementById(formSource).value;
        	// split nilai
        	var day = currentDate.getDate();
			var month = currentDate.getMonth()+1;
			var year = currentDate.getFullYear();
			var Dob=date_of_birth.split('/');// split dengan '/'
			var Dob_day=Dob[0];
			var Dob_month=Dob[1];
			var Dob_year =Dob[2];
			// logic
			var agey = year - Dob_year;//+
			var agem = month - Dob_month;//+-
			var aged = day - Dob_day;//+-
			var li_month = 0;
			var li_add = 0;	
			
			document.getElementById(formTarget).value = '';
			if (Dob_year != year)
			{
				if (month >= Dob_month)
				{
					age = year - Dob_year;
				}else{
					age = (year - Dob_year) - 1;
					li_add = 12;
				}
				li_month = month + li_add - Dob_month;
				if (li_month >= 6)
				{
					if (li_month==6)
					{
						if ((day  - Dob_day) >= 0)
						{
							age = age+1;
						}
					}else{
						age = age+1;
					}
					
				}
			}
			if (age<0){
				age=0;
			}
			
			
			/* var agem_flag = agem;
			if(agem < 0){
				agem = (12-Dob_month) + month;
			}
			
			if(aged > 0 ) // kasus : dob:16 nov ; now:15 dec (< 1 bulan)
			{
				
			}else if(aged <= 0 ){ // kasus : dob:15 nov ; now:16 dec (=> 1 bulan)
				// dilewatkan
				agem = agem - 1;
			}
			
			if(agem_flag < 0)
			{
				agey = agey - 1;	
			}else if(agem_flag > 0)
			{
				// dilewatkan
			}
			if(agem < fix_number)
				{
					agey = agey;
			}else if(agem >= fix_number)
				{
					agey = agey + 1;
				}
			
			if(agey < 0) agey = 0;
			age = agey;	 */
			// validasi
			if(isNaN(age)){
				alert('format tanggal salah');	
			}else{
				//result
        		document.getElementById(formTarget).value = age;
        	}
        //tambahan(optional)
        	if( document.getElementById(formTarget).value == '0' )
            {
            	if(!isNaN(age)){
               		document.getElementById(formTarget).value = age;
               	}
            }
        }
		
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="formpost" commandName="cepr01030000HoldingForm">
<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>
  <!--DWLayoutTable-->
  <c:if test="${cepr01030000HoldingForm.credentialsVO.msagId != '999993'}">
	<TR>
		<TD width="178" height="38" valign="top" style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;</TD>
		<TD COLSPAN=3 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><div align="center">
		  <p class="fontUp">WELCOME TO SINARMASMSIGLIFE E-PROPOSAL [${sessionScope.deebee}]</p>
		</div></TD>
		<TD COLSPAN=4 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;			</TD>
	  <TD style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;">
		  <%-- <div align="right"><a href="${path}/wepr00000001.htm" target="_top" onmouseover="changeButtonHomeToGlow();" onmouseout="changeButtonHomeToNormal();">
	      <img src="${path}/include/images/bt_home.gif" alt="" align="absbottom" id="buttonHomeId" style="border: 0;">
		    </a>
	    </div> --%></TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><span class="style5"> 			</span></TD>
		<TD COLSPAN=2 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;">
		<a href="${path}/wepr01010102.htm" target="_top" onmouseover="changeButtonLogOffToGlow();" onmouseout="changeButtonLogOffToNormal();">
		<img src="${path}/include/images/bt_logoff.gif" alt="" align="absbottom" id="buttonLogOffId" style="border: 0;">
		</a> 
		</TD>
		<TD width="100%" valign="top" style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;</TD>
    </TR>
	<TR>
	  <TD ROWSPAN=6>&nbsp;</TD>
	  <TD height="19" style="background-image:url(${path}/include/images/web_proposal_09.jpg); ">&nbsp;			</TD>
		<TD COLSPAN=9 style="background-image:url(${path}/include/images/web_proposal_10.jpg); background-repeat:repeat-x; ">&nbsp;			</TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_11.jpg); ">&nbsp;			</TD>
        <TD ROWSPAN=6>&nbsp;</TD>
	</TR>
	<TR>
	  <TD ROWSPAN=4 style="background-image:url(${path}/include/images/web_proposal_12.jpg); ">&nbsp;			</TD>
		<TD height="40" colspan="9" class="fontLabel1 style2"><fmt:message key="words.participant"/>			</TD>
	    <TD ROWSPAN=4 style="background-image:url(${path}/include/images/web_proposal_15.jpg); ">&nbsp;			</TD>
	</TR>
	</c:if>
	<TR>
		<TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
		  
		  <table>
        

        <tr>
                <td style="text-align: center; font-family:Arial; font-size:11px">
                </td>
                <td style="text-align: center; font-family:Arial; font-size:11px">
                    Nama
                </td>
                <td style="text-align: center; font-family:Arial; font-size:11px">
                    Tgl Lahir
                </td>
                <td style="text-align: center; font-family:Arial; font-size:11px">
                    Usia
                </td>                
                <td style="text-align: center; font-family:Arial; font-size:11px">
                    Gender
                </td> 
                <td>
                   &nbsp;&nbsp;
                </td>             
                <td>&nbsp;
                    
                </td>                
                <td class="tdFormError">&nbsp;
                    
                </td>
            </tr>
	<input type="hidden" value="${cepr01030000HoldingForm.cepr01030102Form.leftPartOfBusinessCd}" id="plan">
        <c:forEach var="participantVO" items="${cepr01030000HoldingForm.cepr01030113Form.participantVOList}" varStatus="xt">
            <tr>
                <td style="text-align: right; " class="fontForm">
                    ${participantVO.status}
                </td>
                <td>
                    <form:input path="cepr01030113Form.participantVOList[${xt.index}].name" cssClass="fontForm" disabled="${disabledAddition}" />
                </td>
                <td>
                    
                  
        		  <form:input path="cepr01030113Form.participantVOList[${xt.index}].birthDay" size="10" maxlength="10"  title="Tanggal Lahir [format: dd/mm/yyyy (d=date, m=month, y=year)]" onblur="calculateAge('cepr01030113Form.participantVOList[${xt.index}].birthDay','cepr01030113Form.participantVOList[${xt.index}].age');"  />
                  
                </td>
                <td>
                    <form:input path="cepr01030113Form.participantVOList[${xt.index}].age" cssClass="fontForm" cssStyle="width: 20px" readonly="false"/>
                </td>
             
                <td>
                 <form:select path="cepr01030113Form.participantVOList[${xt.index}].sexCd" cssClass="fontForm">                      
                        <form:options items="${participantVO.genderList}" 
                            itemLabel="label" itemValue="value"/>
                    </form:select>
                </td>
             
               <c:if test="${xt.index == 0}">	                
				     <c:choose>
	        		 <c:when test="${cepr01030000HoldingForm.cepr01030101Form.insuredSexCd eq 'L'}">
	        			 <td class="fontForm"> 
					     <form:checkbox path="cepr01030113Form.participantVOList[${xt.index}].medicalPlusRbFlag" cssClass="fontForm" value="true"
					      onclick="" id="cepr01030113Form.participantVOList[${xt.index}].medicalPlusRbFlag" /> <fmt:message key="words.medicalPlusRb"/>      
					     &nbsp;
					     </td>
	        		</c:when>
	        		<c:otherwise>
	        			 <td class="fontForm"> 
					     <form:checkbox path="cepr01030113Form.participantVOList[${xt.index}].medicalPlusRbFlag" cssClass="fontForm" value="true"
					      onclick="" id="cepr01030113Form.participantVOList[${xt.index}].medicalPlusRbFlag" disabled="true"/> <fmt:message key="words.medicalPlusRb"/>      
					     &nbsp;
					     </td>
	        		</c:otherwise>
	        		</c:choose>	        	
			    </c:if>
			    <c:if test="${xt.index != 0}">
			    	<td class="fontForm"> 
			   		  &nbsp; &nbsp; &nbsp; 
			   	  	</td>
			     </c:if>
                <td> 
                	<a href="#" onclick="clearRowContent( '${xt.index}' );" onmouseover="changeButtonBersihkanToGlow( '${xt.index}' );" onmouseout="changeButtonBersihkanToNormal( '${xt.index}' );">
					<img src="${path}/include/images/bt_bersihkan.gif"   alt="" style="border: 0;" id="buttonBersihkanId${xt.index}"> 
					</a>
				</td>
                <td class="tdFormError">
                    <form:errors path="cepr01030113Form.participantVOList[${xt.index }].age"/>
                </td>
            </tr>
        </c:forEach>


        <tr>
            <td colspan="5" style="text-align: center;">
                <br/>
                <input type="hidden" id="targetParam"/>
                <input type="hidden" name="theEvent" id="theEvent"/>
                
            </td>
        </tr>

    </table>
      
	    </TD>
	</TR>
	<TR>
		
        
        
		
		<TD  ><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonOk'; checkEvent()" onmouseover="changeButtonOkToGlow();" onmouseout="changeButtonOkToNormal();">
		<img src="${path}/include/images/bt_ok.gif"  alt="" style="border: 0;" id="buttonOkId"> 
		</a>
		</div></TD>
		
		<TD COLSPAN=2><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonCancel'; checkEvent()" onmouseover="changeButtonCancelToGlow();" onmouseout="changeButtonCancelToNormal();">
		<img src="${path}/include/images/bt_batal.gif"  alt="" style="border: 0;" id="buttonCancelId"> 
		</a>
	    </div></TD>
		<TD><div align="center">
		
		</div></TD>
		<TD COLSPAN=5>&nbsp;			</TD>
    </TR>
	
	<TR>
	  <TD  colspan="9">&nbsp;
	  	
		</TD>
  </TR>
  <c:if test="${cepr01030000HoldingForm.credentialsVO.msagId != '999993'}">
	<TR>
		<TD height="19" style="background-image:url(${path}/include/images/web_proposal_38.jpg); ">&nbsp;			</TD>
		<TD COLSPAN=9 style="background-image:url(${path}/include/images/web_proposal_39.jpg); ">&nbsp;			</TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_40.jpg); ">&nbsp;			</TD>
    </TR>
	<TR>
		<TD height="100" COLSPAN=13 style="background-image:url(${path}/include/images/web_proposal_41.jpg); ">			<div align="center" class="fontBot">Copyright &copy; 2008 PT. Asuransi Jiwa Sinarmas MSIG. All Right Reserved.<br>
		  Head Office: Wisma Eka Jiwa Lantai 8  <br>
		Jl. Mangga Dua Raya, Jakarta 10730  <br>
		Telp: (021) 6257808 (Hunting), Fax: (021) 6257837  <br>
		Customer Service: (021) 6257838, 26508300  <br>
		Website: www.sinarmasmsiglife.com  
		</div></TD>
	</TR>
	</c:if>
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
</form:form>


</BODY>
</HTML>