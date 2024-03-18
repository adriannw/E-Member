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
        function changeButtonPreviewLetterToGlow()
        {
            document.getElementById( "buttonPreviewLetterId" ).src = '${path}/include/images/bt_lihatsurat-ro.gif';
        }
        function changeButtonPreviewLetterToNormal()
        {
            document.getElementById( "buttonPreviewLetterId" ).src = '${path}/include/images/bt_lihatsurat.gif';
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
            document.getElementById( "buttonResetId" ).src = '${path}/include/images/bt_resetisisurat-ro.gif';
        }
        function changeButtonResetToNormal()
        {
            document.getElementById( "buttonResetId" ).src = '${path}/include/images/bt_resetisisurat.gif';
        }
		
		
		function checkEvent()
        {
            
			var theEvent = document.forms[ 0 ].theEvent.value;
            
			
            if( theEvent == 'onPressButtonPreviewLetter' )
            {
                document.getElementById('targetParam').name = CEPR01030301JSP;
                document.getElementById('cepr01030301FormId').submit();
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonResetLetterContent' )
            {
                //document.getElementById('targetParam').name = CEPR01030301JSP;
                document.getElementById('targetParam').name = CEPR01020202JSP;

                
                document.getElementById('cepr01030301FormId').submit();
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonBack' )
            {
                document.getElementById('targetParam').name = CEPR01030101JSP;
                document.getElementById('cepr01030301FormId').submit();
            }
			
        }
		
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="cepr01030301FormId" commandName="cepr01030000HoldingForm">
<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>
  <!--DWLayoutTable-->
	
	<TR>
	  <TD ROWSPAN=7>&nbsp;</TD>
		<TD COLSPAN=9 style="background-image:url(${path}/include/images/web_proposal_10.jpg); background-repeat:repeat-x; ">&nbsp;			</TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_11.jpg); ">&nbsp;			</TD>
        <TD ROWSPAN=7>&nbsp;</TD>
	</TR>
	
	
	<TR>
		<TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
      	
		<table>



<tr>
   
    <td class="tdFormError">
        <form:errors path="cepr01030301Form.content"/>
    </td>
</tr>

<tr>
    <td colspan="3">
        <br/>
        <input type="hidden" id="targetParam"/>
        <input type="hidden" name="theEvent" id="theEvent"/>
        <form:hidden path="cepr01030301Form.downloadFlag"/>
        
    </td>
</tr>

ID&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id" id="id" value="${id}" readonly="readonly">
</input>
<br>
</br>
Nama<input type="text" name="nama" id="nama" value="${nama}">
</input>
<br>
</br>
No Hp<input type="text" name="noHp" id="noHp" value="${noHp}">
</input>
<br>
</br>
Tgl Lahir<input type="text" name="tglLahir" id="tglLahir" value="${tglLahir}">
</input>
<br>
</br>
Email&nbsp;&nbsp;<input type="text" name="email" id="email" value="${email}">
</input>
<br>
</br>
No KTP<input type="text" name="noKtp" id="noKtp" value="${noKtp}">
</input>
<br>
</br>
Foto<input type="text" name="foto" id="foto" value="${foto}">
</input>
</table>
		
	    </TD>
	</TR>
	<TR>
		
        
        
		
			
		<TD COLSPAN=2><div align="center">
		  <input type="button" name="btncari223" value="Update Member"
													onclick="document.forms[ 0 ].theEvent.value='onPressButtonResetLetterContent'; checkEvent()">
													
	
	    </div></TD>
		<TD><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonBack'; checkEvent()" onmouseover="changeButtonBackToGlow();" onmouseout="changeButtonBackToNormal();">
		<img src="${path}/include/images/bt_kembali.gif"   alt="" style="border: 0;" id="buttonBackId">
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
		<TD COLSPAN=9 style="background-image:url(${path}/include/images/web_proposal_39.jpg); ">&nbsp;			</TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_40.jpg); ">&nbsp;			</TD>
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
    var downloadFlag = document.getElementById('cepr01030301Form.downloadFlag').value;
    if( downloadFlag == 'newPage' )
    {
        document.getElementById('cepr01030301Form.downloadFlag').value = '';
        document.getElementById('cepr01030301FormId').target = '_blank';
        document.getElementById('targetParam').name = CEPR00000000DownloadDocumentJSP;
        document.getElementById('cepr01030301FormId').submit();
        document.getElementById('cepr01030301FormId').target = '_self';
        document.getElementById('targetParam').name = CEPR01030301JSP;
        document.getElementById('cepr01030301FormId').submit();
    }
    if( downloadFlag == 'samePage' )
    {
        document.getElementById('cepr01030301Form.downloadFlag').value = '';
        document.getElementById('cepr01030301FormId').action='${downloadUrlSession}';
        document.getElementById('cepr01030301FormId').submit();
        document.getElementById('cepr01030301FormId').target = '_self';
    }
</script>



</BODY>
</HTML>