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
                document.getElementById('targetParam').name = CEPR01030103JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonCancel' )
            {
                document.getElementById('targetParam').name = CEPR01030103JSP;
                document.getElementById('formpost').submit();
            }
			
        }
		
		function clearRowContent( rowIdx )
        {
            document.getElementById( 'cepr01030106Form.participantVOList[' + rowIdx + '].name' ).value = '';
            document.getElementById( '_cepr01030106Form.participantVOList[' + rowIdx + '].birthDay' ).value = '__/__/____';
            document.getElementById( 'cepr01030106Form.participantVOList[' + rowIdx + '].age' ).value = '';
        }
		
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="formpost" commandName="cepr01030000HoldingForm">
<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>
  <!--DWLayoutTable-->
	<TR>
		<TD width="178" height="38" valign="top" style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;</TD>
		<TD COLSPAN=3 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><div align="center">
		  <p class="fontUp">WELCOME TO SINARMASMSIGLIFE E-PROPOSAL [${sessionScope.deebee}]</p>
		</div></TD>
		<TD COLSPAN=4 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;			</TD>
	  <TD style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;">
		  <div align="right"><a href="${path}/wepr00000001.htm" target="_top" onmouseover="changeButtonHomeToGlow();" onmouseout="changeButtonHomeToNormal();">
	      <img src="${path}/include/images/bt_home.gif" alt="" align="absbottom" id="buttonHomeId" style="border: 0;">
		    </a>
	    </div></TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><span class="style5"> |			</span></TD>
		<TD COLSPAN=2 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;">
		<a href="http://serverdmtm1:8080/PlanSim" onmouseover="changeButtonLogOffToGlow();" onmouseout="changeButtonLogOffToNormal();">
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
	<TR>
		<TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
		  
		  <table>
        

        <tr>
                <td style="text-align: center; font-family:Arial; font-size:11px">
                    No
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
                <td>&nbsp;
                    
                </td>
                <td class="tdFormError">&nbsp;
                    
                </td>
            </tr>

        <c:forEach var="participantVO" items="${cepr01030000HoldingForm.cepr01030106Form.participantVOList}" varStatus="xt">
            <tr>
                <td style="text-align: right; " class="fontForm">
                    ${xt.index + 1}
                </td>
                <td>
                    <form:input path="cepr01030106Form.participantVOList[${xt.index}].name" cssClass="fontForm" disabled="${disabledAddition}" />
                </td>
                <td>
                    <spring:bind path="cepr01030000HoldingForm.cepr01030106Form.participantVOList[${xt.index}].birthDay">
                        <script type="text/javascript">inputDateWithAge('${status.expression}', '${status.value}', false, '', 9, 'cepr01030106Form.participantVOList[${xt.index}].age', '' );</script>
                    </spring:bind>
                </td>
                <td>
                    <form:input path="cepr01030106Form.participantVOList[${xt.index}].age" cssClass="fontForm" cssStyle="width: 20px"/>
                </td>
                <td> 
                	<a href="#" onclick="clearRowContent( '${xt.index}' );" onmouseover="changeButtonBersihkanToGlow( '${xt.index}' );" onmouseout="changeButtonBersihkanToNormal( '${xt.index}' );">
					<img src="${path}/include/images/bt_bersihkan.gif"   alt="" style="border: 0;" id="buttonBersihkanId${xt.index}"> 
					</a>
				</td>
                <td class="tdFormError">
                    <form:errors path="cepr01030106Form.participantVOList[${xt.index }].age"/>
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
	<TR>
		<TD height="19" style="background-image:url(${path}/include/images/web_proposal_38.jpg); ">&nbsp;			</TD>
		<TD COLSPAN=9 style="background-image:url(${path}/include/images/web_proposal_39.jpg); ">&nbsp;			</TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_40.jpg); ">&nbsp;			</TD>
    </TR>
	<TR>
		<TD height="100" COLSPAN=13 style="background-image:url(${path}/include/images/web_proposal_41.jpg); ">			<div align="center" class="fontBot">Copyright &copy; 2008 PT. Asuransi Jiwa Sinarmas. All Right Reserved.<br>
		  Head Office: Wisma Eka Jiwa Lantai 8  <br>
		Jl. Mangga Dua Raya, Jakarta 10730  <br>
		Telp: (021) 6257808 (Hunting), Fax: (021) 6257837  <br>
		Customer Service: (021) 6257838, 26508300  <br>
		Website: www.sinarmaslife.co.id  
		</div></TD>
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
</form:form>


</BODY>
</HTML>