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
        function changeButtonPreviewCoverToGlow()
        {
            document.getElementById( "buttonPreviewCoverId" ).src = '${path}/include/images/bt_lihatsampul-ro.gif';
        }
        function changeButtonPreviewCoverToNormal()
        {
            document.getElementById( "buttonPreviewCoverId" ).src = '${path}/include/images/bt_lihatsampul.gif';
        }
		function changeButtonBackToGlow()
        {
            document.getElementById( "buttonBackId" ).src = '${path}/include/images/bt_kembali-ro.gif';
        }
		function changeButtonBackToNormal()
        {
            document.getElementById( "buttonBackId" ).src = '${path}/include/images/bt_kembali.gif';
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
			
            if( theEvent == 'onPressButtonPreviewCover' )
            {
                document.getElementById('targetParam').name = CEPR01030401JSP;
                document.getElementById('cepr01030401FormId').submit();
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonBack' )
            {
                document.getElementById('targetParam').name = CEPR01030101JSP;
                document.getElementById('cepr01030401FormId').submit();
            }
			
        }
		
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="cepr01030401FormId" commandName="cepr01030000HoldingForm">
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
		<TD height="40" colspan="9" class="fontLabel1 style2"><fmt:message key="words.createCover"/>			</TD>
	    <TD ROWSPAN=4 style="background-image:url(${path}/include/images/web_proposal_15.jpg); ">&nbsp;			</TD>
	</TR>
	<TR>
		<TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
      
	  <table>


<tr>
    <td class="tdFormLabel">
        <fmt:message key="words.customerName1"/>
        :
    </td>
    <td>
        <form:input path="cepr01030401Form.customerName" cssClass="fontForm"/>
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030401Form.customerName"/>
    </td>
</tr>
<tr>
    <td class="tdFormLabel">
        <fmt:message key="words.agentName"/>
        :
    </td>
    <td>
        <form:input path="cepr01030401Form.agentName" cssClass="fontForm"/>
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030401Form.agentName"/>
    </td>
</tr>
<tr>
    <td class="tdFormLabel">
        <fmt:message key="words.regionalOrBranch"/>
        :
    </td>
    <td>
        <form:input path="cepr01030401Form.branchName" cssClass="fontForm"/>
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030401Form.branchName"/>
    </td>
</tr>
<tr>
    <td class="tdFormLabel">
        <fmt:message key="words.homePhoneNo"/>
        :
    </td>
    <td>
        <form:input path="cepr01030401Form.homePhoneNo" cssClass="fontForm"/>
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030401Form.homePhoneNo"/>
    </td>
</tr>
<tr>
    <td class="tdFormLabel">
        <fmt:message key="words.mobilePhoneNo"/>
        :
    </td>
    <td>
        <form:input path="cepr01030401Form.mobilePhoneNo" cssClass="fontForm"/>
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030401Form.mobilePhoneNo"/>
    </td>
</tr>

<tr>
    <td colspan="3">
        <br/>
        <input type="hidden" id="targetParam"/>
        <input type="hidden" name="theEvent" id="theEvent"/>
        <form:hidden path="cepr01030401Form.downloadFlag"/>
        
    </td>
</tr>




</table>
	  
	  </TD>
	</TR>
	<TR>
		
        
        
		
		<TD ><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonPreviewCover'; checkEvent()" onmouseover="changeButtonPreviewCoverToGlow();" onmouseout="changeButtonPreviewCoverToNormal();">
		<img src="${path}/include/images/bt_lihatsampul.gif"  alt="" style="border: 0;" id="buttonPreviewCoverId"> 
		</a>
		</div></TD>
		
		<TD COLSPAN=2><div align="left">
		<a href="#" onmouseover="changeButtonResetToGlow();" onmouseout="changeButtonResetToNormal();">
		<img src="${path}/include/images/bt_reset.gif"  alt="" style="border: 0;" id="buttonResetId">
		</a>
	    </div></TD>
		<TD><div align="left">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonBack'; checkEvent()" onmouseover="changeButtonBackToGlow();" onmouseout="changeButtonBackToNormal();">
		<img src="${path}/include/images/bt_kembali.gif"  alt="" style="border: 0;" id="buttonBackId">
		</a>
		</div></TD>
		<TD COLSPAN=5>&nbsp;			</TD>
    </TR>
	
	<TR>
	  <TD  colspan="9" style="font-size: 11px; font-family:Arial;">
	  <spring:hasBindErrors name="cepr01030000HoldingForm">
            
            <b>
                <fmt:message key="error.formNotCompleted"/>
            </b>
        </spring:hasBindErrors>
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

<script type="text/javascript">
    var downloadFlag = document.getElementById('cepr01030401Form.downloadFlag').value;
    if( downloadFlag == 'newPage' )
    {
        document.getElementById('cepr01030401Form.downloadFlag').value = '';
        document.getElementById('cepr01030401FormId').target = '_blank';
        document.getElementById('targetParam').name = CEPR00000000DownloadDocumentJSP;
        document.getElementById('cepr01030401FormId').submit();
        document.getElementById('cepr01030401FormId').target = '_self';
        document.getElementById('targetParam').name = CEPR01030401JSP;
        document.getElementById('cepr01030401FormId').submit();
    }
    if( downloadFlag == 'samePage' )
    {
        document.getElementById('cepr01030401Form.downloadFlag').value = '';
        document.getElementById('cepr01030401FormId').action='${downloadUrlSession}';
        document.getElementById('cepr01030401FormId').submit();
        document.getElementById('cepr01030401FormId').target = '_self';
    }
</script>
</BODY>
</HTML>