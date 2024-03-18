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
		function changeButtonResetToGlow()
        {
            document.getElementById( "buttonResetId" ).src = '${path}/include/images/bt_reset-ro.gif';
        }
        function changeButtonResetToNormal()
        {
            document.getElementById( "buttonResetId" ).src = '${path}/include/images/bt_reset.gif';
        }
		function changeButtonParticipantToGlow()
        {
            document.getElementById( "buttonParticipantId" ).src = '${path}/include/images/bt_peserta-ro.gif';
        }
        function changeButtonParticipantToNormal()
        {
            document.getElementById( "buttonParticipantId" ).src = '${path}/include/images/bt_peserta.gif';
        }
		
        function changeButtonClassToGlow()
        {
            document.getElementById( "buttonClassId" ).src = '${path}/include/images/bt_kelas-ro.gif';
        }
        function changeButtonClassToNormal()
        {
            document.getElementById( "buttonClassId" ).src = '${path}/include/images/bt_kelas.gif';
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
		function changeButtonResetToGlow()
        {
            document.getElementById( "buttonResetId" ).src = '${path}/include/images/bt_reset-ro.gif';
        }
        function changeButtonResetToNormal()
        {
            document.getElementById( "buttonResetId" ).src = '${path}/include/images/bt_reset.gif';
        }
		
		
		function checkEvent()
        {
			var theEvent = document.forms[ 0 ].theEvent.value;
			
            if( theEvent == 'onPressButtonOk' )
            {
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonClass' )
            {
                document.getElementById('targetParam').name = CEPR01030105JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonParticipant' )
            {
                document.getElementById('targetParam').name = CEPR01030106JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonReset' )
            {
                document.getElementById('formpost').reset();
            }
			
        }
		
		function handleClickPaFlag( isChecked )
        {
            var isDisabled = !isChecked;
            disableForm( 'cepr01030103Form.paRiskCd', isDisabled );
            disableForm( 'cepr01030103Form.paClassCd', isDisabled );
            disableForm( 'cepr01030103Form.paUnitQtyCd', isDisabled );
            disableForm( 'buttonClassId', isDisabled );

            var stat = 'true';
            if( isChecked ) stat = 'false';
            document.getElementById('cepr01030103Form.paRiskListIsDisabled').value = stat;
            document.getElementById('cepr01030103Form.paClassListIsDisabled').value = stat;
            document.getElementById('cepr01030103Form.paUnitQtyListIsDisabled').value = stat;
            document.getElementById('cepr01030103Form.buttonClassIsDisabled').value = stat;
        }

        function handleClickHcpFlag( isChecked )
        {
            var isDisabled = !isChecked;
            disableForm( 'cepr01030103Form.hcpCd', isDisabled );

            var stat = 'true';
            if( isChecked ) stat = 'false';
            document.getElementById('cepr01030103Form.hcpListIsDisabled').value = stat;
        }

        function handleClickHcpFamilyFlag( isChecked )
        {
            var isDisabled = !isChecked;
            disableForm( 'cepr01030103Form.hcpFamilyCd', isDisabled );
            disableForm( 'buttonParticipantId', isDisabled );

            var stat = 'true';
            if( isChecked ) stat = 'false';
            document.getElementById('cepr01030103Form.hcpFamilyListIsDisabled').value = stat;
            document.getElementById('cepr01030103Form.buttonParticipantIsDisabled').value = stat;
        }
        
         function handleClickHcpLadiesFlag( isChecked )
        {
            var isDisabled = !isChecked;
            disableForm( 'cepr01030103Form.hcpLadiesCd', isDisabled );
            disableForm( 'buttonParticipantId', isDisabled );

            var stat = 'true';
            if( isChecked ) stat = 'false';
            document.getElementById('cepr01030103Form.hcpLadiesListIsDisabled').value = stat;
            document.getElementById('cepr01030103Form.buttonParticipantHcpLadiesIsDisabled').value = stat;
        }

        function handleClickTermRiderFlag( isChecked )
        {
            var isDisabled = !isChecked;
            disableForm( 'cepr01030103Form.termRiderAmount', isDisabled );

            var stat = 'true';
            if( isChecked ) stat = 'false';
            document.getElementById('cepr01030103Form.termRiderAmountIsDisabled').value = stat;
        }
		
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="formpost" commandName="cepr01030000HoldingForm">

<form:hidden path="cepr01030103Form.paRiskListIsDisabled" />
<form:hidden path="cepr01030103Form.paClassListIsDisabled" />
<form:hidden path="cepr01030103Form.paUnitQtyListIsDisabled" />
<form:hidden path="cepr01030103Form.hcpListIsDisabled" />
<form:hidden path="cepr01030103Form.buttonClassIsDisabled" />
<form:hidden path="cepr01030103Form.termRiderAmountIsDisabled" />

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
		<TD height="40" colspan="9" class="fontLabel1 style2"><fmt:message key="words.riders"/>			</TD>
	    <TD ROWSPAN=4 style="background-image:url(${path}/include/images/web_proposal_15.jpg); ">&nbsp;			</TD>
	</TR>
	<TR>
		<TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
      
	  <table>


<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.paDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.paFlag" cssClass="fontForm" id="cepr01030103Form.paFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.paFlagIsDisabled}" onclick="handleClickPaFlag(this.checked);"/>
        <label for="cepr01030103Form.paFlag">
        	<c:choose>
        		<c:when test="${cepr01030000HoldingForm.credentialsVO.msagId eq '999976' || cepr01030000HoldingForm.credentialsVO.type eq 'bsm'}">
        			<fmt:message key="words.personalAccident_0"/>
        		</c:when>
        		<c:otherwise>
        			<fmt:message key="words.personalAccident"/>
        		</c:otherwise>
        	</c:choose>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">&nbsp;
        
        
    </td>
    <td>&nbsp;
        
    </td>
</tr>
<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.paDisplay}">
    <td align="right" class="fontForm">
        <fmt:message key="words.risk"/>&nbsp;
    </td>
    <td style="text-align: left;">
        
        <form:select path="cepr01030103Form.paRiskCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030103Form.paRiskList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030103Form.paRiskListIsDisabled}"
                />
        
        <c:set var="buttonDisabledAddition" value=""/>
        <c:if test="${cepr01030000HoldingForm.cepr01030103Form.buttonClassIsDisabled=='true'}">
            <c:set var="buttonDisabledAddition" value="disabled"/>
        </c:if>
        
		
    </td>
    <td style="text-align: left;"><div>
	<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonClass'; checkEvent()" onmouseover="changeButtonClassToGlow();" onmouseout="changeButtonClassToNormal();">
		<img src="${path}/include/images/bt_kelas.gif"  alt="" style="border: 0; " id="buttonClassId"> 
		</a>
			</div>
	</td>
    <td style="text-align: left;" class="fontForm">
	<form:select path="cepr01030103Form.paClassCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030103Form.paClassList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030103Form.paClassListIsDisabled}"
                />
        &nbsp;
        <fmt:message key="words.unitAmount"/>
        <form:select path="cepr01030103Form.paUnitQtyCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030103Form.paUnitQtyList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030103Form.paUnitQtyListIsDisabled}"
                /> 
        &nbsp;
	</td>
    <td>
        <form:errors path="cepr01030103Form.paFlag" cssClass="comErrorColor"/>
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.hcpDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.hcpFlag" cssClass="fontForm" id="cepr01030103Form.hcpFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.hcpFlagIsDisabled}" onclick="handleClickHcpFlag(this.checked);"/>
        <label for="cepr01030103Form.hcpFlag">
            <fmt:message key="words.hospitalCashPlanHCP"/>
        </label>
    </td>
    <td style="text-align: left;">
        <form:select path="cepr01030103Form.hcpCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030103Form.hcpList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030103Form.hcpListIsDisabled}"
                />
        
        
    </td>
    <td colspan="2" style="text-align: left;">
		<form:errors path="cepr01030103Form.hcpFlag" cssClass="comErrorColor"/>
	</td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.hcpFamilyDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.hcpFamilyFlag" cssClass="fontForm" id="cepr01030103Form.hcpFamilyFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.hcpFamilyFlagIsDisabled}" onclick="handleClickHcpFamilyFlag(this.checked);"/>
        <label for="cepr01030103Form.hcpFamilyFlag">
        	<c:choose>
        		<c:when test="${cepr01030000HoldingForm.credentialsVO.msagId eq '999976' || cepr01030000HoldingForm.credentialsVO.type eq 'bsm'}">
        			<fmt:message key="words.hcpFamily_0"/>
        		</c:when>
        		<c:otherwise>
        			<fmt:message key="words.hcpFamily"/>
        		</c:otherwise>
        	</c:choose>
        </label>
    </td>
    <td style="text-align: left;">
        <form:hidden path="cepr01030103Form.hcpFamilyListIsDisabled" />
        <form:hidden path="cepr01030103Form.buttonParticipantIsDisabled" />
        <form:select path="cepr01030103Form.hcpFamilyCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030103Form.hcpFamilyList}"
                     itemLabel="label" itemValue="value"

                     disabled="${cepr01030000HoldingForm.cepr01030103Form.hcpFamilyListIsDisabled}"
                />
        <c:set var="buttonDisabledAddition" value=""/>
        <c:if test="${cepr01030000HoldingForm.cepr01030103Form.buttonParticipantIsDisabled=='true'}">
            <c:set var="buttonDisabledAddition" value="disabled"/>
        </c:if>
        
        
    </td>
    <td style="text-align: left;"><div>
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonParticipant'; checkEvent()" onmouseover="changeButtonParticipantToGlow();" onmouseout="changeButtonParticipantToNormal();">
		<img src="${path}/include/images/bt_peserta.gif"  alt="" style="border: 0;" id="buttonParticipantId"> 
		</a>
			</div>
        </td>
    <td style="text-align: left;">
		<form:errors path="cepr01030103Form.hcpFamilyFlag" cssClass="comErrorColor"/>
	
	&nbsp;
	</td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.hcpLadiesDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.hcpLadiesFlag" cssClass="fontForm" id="cepr01030103Form.hcpLadiesFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.hcpLadiesFlagIsDisabled}" onclick="handleClickHcpLadiesFlag(this.checked);"/>
        <label for="cepr01030103Form.hcpLadiesFlag">
            <fmt:message key="words.hcpLadies"/>
        </label>
    </td>
    <td style="text-align: left;">
        <form:hidden path="cepr01030103Form.hcpLadiesListIsDisabled" />
        <form:hidden path="cepr01030103Form.buttonParticipantIsDisabled" />
        <form:select path="cepr01030103Form.hcpLadiesCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030103Form.hcpLadiesList}"
                     itemLabel="label" itemValue="value"

                     disabled="${cepr01030000HoldingForm.cepr01030103Form.hcpLadiesListIsDisabled}"
                />
        <c:set var="buttonDisabledAddition" value=""/>
        <c:if test="${cepr01030000HoldingForm.cepr01030103Form.buttonParticipantIsDisabled=='true'}">
            <c:set var="buttonDisabledAddition" value="disabled"/>
        </c:if>
        
        
    </td>
    <td style="text-align: left;"><div>
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonParticipant'; checkEvent()" onmouseover="changeButtonParticipantToGlow();" onmouseout="changeButtonParticipantToNormal();">
		<img src="${path}/include/images/bt_peserta.gif"  alt="" style="border: 0;" id="buttonParticipantId"> 
		</a>
			</div>
        </td>
    <td style="text-align: left;">
		<form:errors path="cepr01030103Form.hcpLadiesFlag" cssClass="comErrorColor"/>
	
	&nbsp;
	</td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.payor5Tpd10TpdDeathDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.payor5Tpd10TpdDeathFlag" cssClass="fontForm" id="cepr01030103Form.payor5Tpd10TpdDeathFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.payor5Tpd10TpdDeathFlagIsDisabled}"/>
        <label for="cepr01030103Form.payor5Tpd10TpdDeathFlag">
            <fmt:message key="words.payor5Tpd10TpdDeath"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.payorTpdDeathFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.payorTpdDeathDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.payorTpdDeathFlag" cssClass="fontForm" id="cepr01030103Form.payorTpdDeathFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.payorTpdDeathFlagIsDisabled}"/>
        <label for="cepr01030103Form.payorTpdDeathFlag">
            <fmt:message key="words.payorTpdDeath"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.payorTpdDeathFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.payor5Ci10CiDeathDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.payor5Ci10CiDeathFlag" cssClass="fontForm" id="cepr01030103Form.payor5Ci10CiDeathFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.payor5Ci10CiDeathFlagIsDisabled}"/>
        <label for="cepr01030103Form.payor5Ci10CiDeathFlag">
            <fmt:message key="words.payor5Ci10CiDeath"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.payorCiDeathFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.payorCiDeathDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.payorCiDeathFlag" cssClass="fontForm" id="cepr01030103Form.payorCiDeathFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.payorCiDeathFlagIsDisabled}"/>
        <label for="cepr01030103Form.payorCiDeathFlag">
            <fmt:message key="words.payorCiDeath"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.payorCiDeathFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.payorCiDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.payorCiFlag" cssClass="fontForm" id="cepr01030103Form.payorCiFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.payorCiFlagIsDisabled}"/>
        <label for="cepr01030103Form.payorCiFlag">
            <fmt:message key="words.payorCi"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.payorCiFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.payorSpouseTpdDeathDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.payorSpouseTpdDeathFlag" cssClass="fontForm" id="cepr01030103Form.payorSpouseTpdDeathFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.payorSpouseTpdDeathFlagIsDisabled}"/>
        <label for="cepr01030103Form.payorSpouseTpdDeathFlag">
            <fmt:message key="words.payorSpouseTpdDeath"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.payorSpouseTpdDeathFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.waiver5Tpd10TpdDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.waiver5Tpd10TpdFlag" cssClass="fontForm" id="cepr01030103Form.waiver5Tpd10TpdFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.waiver5Tpd10TpdFlagIsDisabled}"/>
        <label for="cepr01030103Form.waiver5Tpd10TpdFlag">
            <fmt:message key="words.waiver5Tpd10Tpd"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.waiverTpdFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.waiverTpdDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.waiverTpdFlag" cssClass="fontForm" id="cepr01030103Form.waiverTpdFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.waiverTpdFlagIsDisabled}"/>
        <label for="cepr01030103Form.waiverTpdFlag">
            <fmt:message key="words.waiverTpd"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.waiverTpdFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.waiver5Ci10CiDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.waiver5Ci10CiFlag" cssClass="fontForm" id="cepr01030103Form.waiver5Ci10CiFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.waiver5Ci10CiFlagIsDisabled}"/>
        <label for="cepr01030103Form.waiver5Ci10CiFlag">
            <fmt:message key="words.waiver5Ci10Ci"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.waiverCiFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.waiverCiDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.waiverCiFlag" cssClass="fontForm" id="cepr01030103Form.waiverCiFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.waiverCiFlagIsDisabled}"/>
        <label for="cepr01030103Form.waiverCiFlag">
            <fmt:message key="words.waiverCi"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.waiverCiFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.tpdDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.tpdFlag" cssClass="fontForm" id="cepr01030103Form.tpdFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.tpdFlagIsDisabled}"/>
        <label for="cepr01030103Form.tpdFlag">
            <fmt:message key="words.totalPermanentDisabilityTpd"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.tpdFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.ciDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.ciFlag" cssClass="fontForm" id="cepr01030103Form.ciFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.ciFlagIsDisabled}"/>
        <label for="cepr01030103Form.ciFlag">
            <fmt:message key="words.criticalIllnessEng"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:errors path="cepr01030103Form.ciFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>























<tr style="display: ${cepr01030000HoldingForm.cepr01030103Form.termRiderDisplay}">
    <td class="fontForm">
        <form:checkbox path="cepr01030103Form.termRiderFlag" cssClass="fontForm" id="cepr01030103Form.termRiderFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030103Form.termRiderFlagIsDisabled}" onclick="handleClickTermRiderFlag(this.checked);"/>
        <label for="cepr01030103Form.termRiderFlag">
            <fmt:message key="words.termRider"/>
        </label>
    </td>
    <td colspan="3" style="text-align: left;">
        <form:input path="cepr01030103Form.termRiderAmount" cssClass="fontForm" disabled="${cepr01030000HoldingForm.cepr01030103Form.termRiderAmountIsDisabled}" onfocus="showForm( 'termRiderAmountHelper', 'true' );" onblur="showForm( 'termRiderAmountHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('termRiderAmountHelper', this.value);" />
        <input type="text" id="termRiderAmountHelper" disabled="disabled" style="display: none; font-family:Arial; font-size:11px" tabindex="-1"/>
        <form:errors path="cepr01030103Form.termRiderFlag" cssClass="comErrorColor"/>
    </td>
    <td>&nbsp;
        
    </td>
</tr>

<tr>
    <td colspan="5">
        <br/>
        <input type="hidden" id="targetParam"/>
        <input type="hidden" name="theEvent" id="theEvent"/>
        
    </td>
</tr>

</table>
	  
	  </TD>
	</TR>
	<TR>   
		
		<TD ><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonOk'; checkEvent()" onmouseover="changeButtonOkToGlow();" onmouseout="changeButtonOkToNormal();">
		<img src="${path}/include/images/bt_ok.gif"  alt="" style="border: 0;" id="buttonOkId"> 
		</a>
		</div></TD>
		
		<TD COLSPAN=2><div align="center">
		<a href="#" onclick="" onmouseover="changeButtonResetToGlow();" onmouseout="changeButtonResetToNormal();">
		<img src="${path}/include/images/bt_reset.gif"  alt="" style="border: 0;" id="buttonResetId">
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