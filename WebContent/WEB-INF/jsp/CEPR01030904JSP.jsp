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
 var CEPR01030901JSP = '_target18';
        function changeButtonPreviewCoverToGlow()
        {
            document.getElementById( "buttonPreviewCoverId" ).src = '${path}/include/images/bt_lihatproposal.gif';
        }
        function changeButtonPreviewCoverToNormal()
        {
            document.getElementById( "buttonPreviewCoverId" ).src = '${path}/include/images/bt_lihatproposal.gif';
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
			
            if( theEvent == 'onPressButtonPreviewDPLK' )
            {
                document.getElementById('targetParam').name = CEPR01030901JSP;
                document.getElementById('cepr01030901FormId').submit();
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonBack' )
            {
                document.getElementById('targetParam').name = CEPR01030101JSP;
                document.getElementById('cepr01030901FormId').submit();
            }
			
        }
		
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="cepr01030901FormId" commandName="cepr01030000HoldingForm">
<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>
  <!--DWLayoutTable-->
	<TR>
		<TD width="178" height="38" valign="top" style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;</TD>
		<TD COLSPAN=3 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><div align="center">
		  <p class="fontUp">WELCOME TO SINARMASMSIGLIFE E-PROPOSAL [${sessionScope.deebee}]</p>
		</div></TD>
		<TD COLSPAN=4 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;			</TD>
	  <TD style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;">
		 <%--  <div align="right"><a href="${path}/wepr00000001.htm" target="_top" onmouseover="changeButtonHomeToGlow();" onmouseout="changeButtonHomeToNormal();">
	      <img src="${path}/include/images/bt_home.gif" alt="" align="absbottom" id="buttonHomeId" style="border: 0;">
		    </a>
	    </div> --%></TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><span class="style5"> |			</span></TD>
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
		<TD height="40" colspan="9" class="fontLabel1 style2"><fmt:message key="words.updateProduct"/>			</TD>
	    <TD ROWSPAN=4 style="background-image:url(${path}/include/images/web_proposal_15.jpg); ">&nbsp;			</TD>
	</TR>
	<TR>
		<TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
      
	  <table>
<tr>
							<th>
							</th>
							<td>

								<table class="displaytag" style="width: auto;">
									<caption>Silahkan Pilih File:</caption>
									<tr>
										<th>File Name</th>
										<th>Description</th>
										<th>Last Modified</th>
									</tr>
									<c:forEach var="dok" items="${daftarFile}">
										<tr>
											<td style="text-align: left;"><a href="${path}/update_proposal/update.htm?window=proposal&download=${dok.key}">${dok.key}</a></td>
											<td style="text-align: left;">${dok.desc}</td>
											<td style="text-align: left;">${dok.value}</td>
										</tr>
									</c:forEach>
								</table>
								
								<br/>
								<strong>Terdapat dua pilihan Untuk melakukan update ini yaitu:</strong>
								<p>
									Pilihan pertama, Langsung menjalankan file update setelah selesai download. Berikut petunjuknya:
									<ul>
										<li>Tunggu beberapa saat sampai muncul kotak dialog untuk mendownload file.</li>
										<li>Setelah muncul kotak dialog, klik tombol OPEN.</li>
										<li>Tunggu sampai download file selesai (100% completed), tekan tombol UNZIP untuk memulai update.</li>
									</ul>
								</p>
								
								<p>
									Pilihan kedua, Simpan file update hasil download untuk arsip lalu jalankan file tsb untuk update. Berikut petunjuknya:
									<ul>
										<li>Tunggu beberapa saat sampai muncul kotak dialog untuk mendownload file.</li>
										<li>Setelah muncul kotak dialog, klik tombol SAVE.</li>
										<li>Tunggu sampai download file selesai (100% completed).</li>
										<li>Jalankan Windows Explorer, cari file update_XXXXXX.exe atau upas_XXXXXX.exe atau update_absenXXXX.exe (sesuai kebutuhan) yang baru saja disave.</li>
										<li>Jalankan file tersebut.</li>
										<li>Setelah file dijalankan, tekan tombol UNZIP untuk memulai update.</li>
									</ul>

								</p>
								
								<p>
									<strong>Catatan Tambahan</strong>
									<ul>
										<li>Kami sarankan pilihan pertama untuk melakukan update ini.</li> 
										<li>Cara untuk mengupdate proposal Agency System adalah sama dengan cara mengupdate Proposal Regional, cuma link nya saja yang berbeda.</li>
										<li>Jika sebelumnya adalah regional, maka tetap menggunakan update regional</li>
									</ul>
								</p>							

								<p>
									<strong>Cara Instalasi Proposal Khusus Bank Sinarmas</strong>
									<ol>
										<li>Download dan Install versi full nya terlebih dahulu (FULL REGIONAL.zip)</li> 
										<li>Download dan Install update versi terbaru (UPBSM_YYMMDD.exe dimana YYMMDD adalah tanggal update terbaru)</li>
									</ol>
								</p>							
							</td>
						</tr>
<tr>
    <td colspan="3">
        <br/>
        <input type="hidden" id="targetParam"/>
        <input type="hidden" name="theEvent" id="theEvent"/>
        <form:hidden path="cepr01030901Form.downloadFlag"/>
        
    </td>
</tr>




</table>
	  
	  </TD>
	</TR>
	<TR>
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
		<TD height="100" COLSPAN=13 style="background-image:url(${path}/include/images/web_proposal_41.jpg); ">			<div align="center" class="fontBot">Copyright &copy; 2023 PT MSIG Life Insurance Indonesia Tbk All Right Reserved.<br>
		 Head Office: Sinarmas MSIG Tower Lantai 6  <br>
		Jl. Jend. Sudirman Kav. 21, Jakarta 12920  <br>
		Telp: (021) 5060 9999 atau (021) 2650 8300  <br>	
		Website: www.msiglife.co.id
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
    var downloadFlag = document.getElementById('cepr01030901Form.downloadFlag').value;
    if( downloadFlag == 'newPage' )
    {
        document.getElementById('cepr01030901Form.downloadFlag').value = '';
        document.getElementById('cepr01030901FormId').target = '_blank';
        document.getElementById('targetParam').name = CEPR00000000DownloadDocumentJSP;
        document.getElementById('cepr01030901FormId').submit();
        document.getElementById('cepr01030901FormId').target = '_self';
        document.getElementById('targetParam').name = CEPR01030901JSP;
        document.getElementById('cepr01030901FormId').submit();
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