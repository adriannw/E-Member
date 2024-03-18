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
		function changeButtonComputeTotalToGlow()
        {
            document.getElementById( "buttonComputeTotalId" ).src = '${path}/include/images/bt_hitungtotal-ro.gif';
        }
		function changeButtonComputeTotalToNormal()
        {
            document.getElementById( "buttonComputeTotalId" ).src = '${path}/include/images/bt_hitungtotal.gif';
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
		
		function handleCheckBox( checkBoxId, isChecked, idx )
        {
            var topupAmountId = 'cepr01030104Form.topupDrawVOList[' + idx + '].topupAmount';
            var drawAmountId = 'cepr01030104Form.topupDrawVOList[' + idx + '].drawAmount';

            document.getElementById(checkBoxId).value = isChecked ? 'true' : null;

            disableForm(topupAmountId, !isChecked);
            disableForm(drawAmountId, !isChecked);

            if( !isChecked )
            {
        
                document.getElementById(topupAmountId).value = '0.00';
     			document.getElementById(drawAmountId).value = '0.00';
                
                checkForm( 'cepr01030104Form.checkAllFlag', false );
            }

        }


        function checkAll( isChecked )
        {
            var id;
            var topupAmountId;
            var drawAmountId;
            var topupDefaultAmount;
            var drawDefaultAmount;

            if( isChecked )
            {
                topupDefaultAmount = document.getElementById( 'cepr01030104Form.topupDefaultAmount' ).value;
                drawDefaultAmount = document.getElementById( 'cepr01030104Form.drawDefaultAmount' ).value;
            }
            else
            {
                topupDefaultAmount = '0.00';
                drawDefaultAmount = '0.00';
            }

            for( var idx = 0; idx < 50; idx++ )
            {
                id = 'cepr01030104Form.topupDrawVOList[' + idx + '].yearFlag';
                topupAmountId = 'cepr01030104Form.topupDrawVOList[' + idx + '].topupAmount';
                drawAmountId = 'cepr01030104Form.topupDrawVOList[' + idx + '].drawAmount';

                checkForm(id, isChecked);

                document.getElementById( topupAmountId ).value = topupDefaultAmount;
                if(document.getElementById('namaPlan').value != 'Eduvest')
                {
                	document.getElementById( drawAmountId ).value = drawDefaultAmount;
                }
                document.getElementById( topupAmountId ).disabled = !isChecked;
                document.getElementById( drawAmountId ).disabled = !isChecked;
            }
        }
		
		function checkEvent()
        {
            
			var theEvent = document.forms[ 0 ].theEvent.value;
            
            if( theEvent == 'onPressButtonOk' )
            {
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonComputeTotal' )
            {
                document.getElementById('targetParam').name = CEPR01030104JSP;
                document.getElementById('formpost').submit();
            }
			
        }
		
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="formpost" commandName="cepr01030000HoldingForm" method="post">
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
		<TD height="40" colspan="9" class="fontLabel1 style2"><fmt:message key="words.topUpOrDraw"/>			</TD>
	    <TD ROWSPAN=4 style="background-image:url(${path}/include/images/web_proposal_15.jpg); ">&nbsp;			</TD>
	</TR>
	<TR>
		<TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
      
	  <table>
        

        <tr>
            <td class="fontForm">
                <fmt:message key="words.topUpDefault"/>
            </td>
            <td>
                <form:input path="cepr01030104Form.topupDefaultAmount" cssClass="fontForm" onfocus="showForm( 'topupDefaultAmountHelper', 'true' );" onblur="showForm( 'topupDefaultAmountHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('topupDefaultAmountHelper', this.value);"/>
            </td>
            <td>
                <input type="text" id="topupDefaultAmountHelper" disabled="disabled" style="display: none; font-family:Arial; font-size:11px;" tabindex="-1"/>
            </td>
            <td>&nbsp;
                
            </td>
            <td class="tdFormError">
                <form:errors path="cepr01030104Form.topupDefaultAmount"/>
            </td>
        </tr>

        <tr>
            <td class="fontForm">
                <fmt:message key="words.defaultDraw"/>
            </td>
            <td>
                <form:input path="cepr01030104Form.drawDefaultAmount" cssClass="fontForm" onfocus="showForm( 'drawDefaultAmountHelper', 'true' );" onblur="showForm( 'drawDefaultAmountHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('drawDefaultAmountHelper', this.value);"/>
            </td>
            <td>
                <input type="text" id="drawDefaultAmountHelper" disabled="disabled" style="display: none; font-family:Arial; font-size:11px;" tabindex="-1"/>
            </td>
            <td>&nbsp;
                
            </td>
            <td class="tdFormError">
                <form:errors path="cepr01030104Form.drawDefaultAmount"/>
            </td>
        </tr>

        <tr><td colspan="5">&nbsp;</td></tr>

        <tr>
            <td colspan="5" class="fontForm">
                <form:checkbox path="cepr01030104Form.checkAllFlag" value="true" onclick="checkAll( this.checked );" />
                <label for="cepr01030104Form.checkAllFlag"><fmt:message key="words.checkAll"/></label>
                <hr/>
            </td>
        </tr>

        <tr>
            <td style="text-align: right; font-family:Arial; font-size:11px;">
                <b><fmt:message key="words.year"/></b>
            </td>
            <td style="font-family:Arial; font-size:11px;">
                <b><fmt:message key="words.topupTotal"/></b>
            </td>
            <td style="font-family:Arial; font-size:11px;">
            <c:choose>
            <c:when test="${plan eq 'Eduvest'}">
                <b><fmt:message key="words.drawPercentage"/></b>
            </c:when>
            <c:otherwise>
                <b><fmt:message key="words.drawTotal"/></b>
                </c:otherwise>
            </c:choose>
                
            </td>
            <td colspan="2">&nbsp;</td>
        </tr>

        <c:forEach var="topupDrawVO" items="${cepr01030000HoldingForm.cepr01030104Form.topupDrawVOList}" varStatus="xt">
            <tr>
                <c:set var="disabledAddition" value=""/>
                <c:if test="${'true' != topupDrawVO.yearFlag}">
                    <c:set var="disabledAddition" value="true"/>
                </c:if>
                <td style="text-align: right; font-family:Arial; font-size:11px;">
                    <label for="cepr01030104Form.topupDrawVOList[${xt.index}].yearFlag">
                        ${topupDrawVO.yearNo}
                    </label>
                    <form:checkbox path="cepr01030104Form.topupDrawVOList[${xt.index }].yearFlag" cssClass="fontForm" value="true" onclick="handleCheckBox( 'cepr01030104Form.topupDrawVOList[${xt.index }].yearFlag', this.checked, ${xt.index} );" id="cepr01030104Form.topupDrawVOList[${xt.index }].yearFlag"/>
                </td>
                <td>
                    <form:input path="cepr01030104Form.topupDrawVOList[${xt.index}].topupAmount" cssClass="fontForm" disabled="${disabledAddition}" onfocus="showForm( 'topupAmountHelper${xt.index}', 'true' );" onblur="showForm( 'topupAmountHelper${xt.index}', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('topupAmountHelper${xt.index}', this.value);"/>
                </td>
                <td>
                    <form:input path="cepr01030104Form.topupDrawVOList[${xt.index}].drawAmount" cssClass="fontForm" disabled="${disabledAddition}" onfocus="showForm( 'drawAmountHelper${xt.index}', 'true' );" onblur="showForm( 'drawAmountHelper${xt.index}', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('drawAmountHelper${xt.index}', this.value);" readonly="${drawAmountReadonlyAddition}"/>
                </td>
                <td>
                    <input type="text" id="topupAmountHelper${xt.index}" disabled="disabled" style="display: none; font-family:Arial ; font-size:11px;" tabindex="-1"/>
                    <input type="text" id="drawAmountHelper${xt.index}" disabled="disabled" style="display: none; font-family:Arial ; font-size:11px;" tabindex="-1"/>
                </td>
                <td class="tdFormError">
                    <form:errors path="cepr01030104Form.topupDrawVOList[${xt.index }].topupAmount"/>
                    <form:errors path="cepr01030104Form.topupDrawVOList[${xt.index }].drawAmount"/>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td class="fontForm">
                <b><fmt:message key="words.total"/></b>
            </td>
            <td>
                <form:input path="cepr01030104Form.totalTopupAmount" cssClass="fontForm"/>
            </td>
            <td>
                        <c:choose>
            <c:when test="${plan eq 'Eduvest'}">
            </c:when>
            <c:otherwise>
                 <form:input path="cepr01030104Form.totalDrawAmount" cssClass="fontForm"/>
                </c:otherwise>
            </c:choose>
              
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>

        <tr>
            <td colspan="4" style="text-align: center;" >
                <input type="hidden" name="theEvent" id="theEvent"/>
                <input type="hidden" id="targetParam"/>
                <input type="text" id="namaPlan" value="${plan}"/>
            </td>
            <td>&nbsp;
                
            </td>
        </tr>

        
    </table>
	  
	  </TD>
	</TR>
	<TR>
		
        
        
		
		<TD  ><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonOk'; checkEvent()" onmouseover="changeButtonOkToGlow();" onmouseout="changeButtonOkToNormal();">
		<img src="${path}/include/images/bt_ok.gif"   alt="" style="border: 0;" id="buttonOkId"> 
		</a>
		</div></TD>
		
		<TD COLSPAN=2><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonComputeTotal'; checkEvent()" onmouseover="changeButtonComputeTotalToGlow();" onmouseout="changeButtonComputeTotalToNormal();">
		<img src="${path}/include/images/bt_hitungtotal.gif"  alt="" style="border: 0;" id="buttonComputeTotalId">
		</a>
	    </div></TD>
		<TD><div align="center">
		
		</div></TD>
		<TD COLSPAN=5>&nbsp;			</TD>
    </TR>
	
	<TR>
	  <TD  colspan="9" class="fontForm">
	  <b>
	  <fmt:message key="words.clickInYear"/>
	  </b>
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
		Website: www.sinarmasmsiglife.com  
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