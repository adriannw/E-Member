<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<HEAD>
<link href="${path}/include/css/general.css" rel="stylesheet" type="text/css" >



<style type="text/css">
<!--
.style2 {font-size: 20px}
.style3 {color: #FF9900}
.style5 {color: #FFFFFF}
-->
</style>

 <script type="text/javascript">
 		function changeButtonChangeToGlow()
        {
            document.getElementById( "buttonChangeId" ).src = '${path}/include/images/bt_changeusergroup-ro.gif';
        }
        function changeButtonChangeToNormal()
        {
            document.getElementById( "buttonChangeId" ).src = '${path}/include/images/bt_changeusergroup.gif';
        }
 		function changeButtonBackToGlow()
        {
            document.getElementById( "buttonBackId" ).src = '${path}/include/images/bt_back-ro.gif';
        }
        function changeButtonBackToNormal()
        {
            document.getElementById( "buttonBackId" ).src = '${path}/include/images/bt_back.gif';
        }
 		function changeButtonAddToGlow()
        {
            document.getElementById( "buttonAddId" ).src = '${path}/include/images/bt_add-ro.gif';
        }
        function changeButtonAddToNormal()
        {
            document.getElementById( "buttonAddId" ).src = '${path}/include/images/bt_add.gif';
        }
		function changeButtonDeleteToGlow()
        {
            document.getElementById( "buttonDeleteId" ).src = '${path}/include/images/bt_delete-ro.gif';
        }
        function changeButtonDeleteToNormal()
        {
            document.getElementById( "buttonDeleteId" ).src = '${path}/include/images/bt_delete.gif';
        }
        function changeButtonSearchToGlow()
        {
            document.getElementById( "buttonSearchId" ).src = '${path}/include/images/bt_search-ro.gif';
        }
        function changeButtonSearchToNormal()
        {
            document.getElementById( "buttonSearchId" ).src = '${path}/include/images/bt_search.gif';
        }
		function changeButtonGoToGlow()
        {
            document.getElementById( "buttonGoId" ).src = '${path}/include/images/bt_go-ro.gif';
        }
        function changeButtonGoToNormal()
        {
            document.getElementById( "buttonGoId" ).src = '${path}/include/images/bt_go.gif';
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
            if( document.getElementById('theEvent').value == 'onPressButtonSearch' )
            {
                document.getElementById('targetParam').name = CEPR01020401JSP;
                document.getElementById('formpost').submit();
            }else if( document.getElementById('theEvent').value == 'onPressButtonBack' )
            {
                document.getElementById('targetParam').name = CEPR01020101JSP;
                document.getElementById('formpost').submit();
            }else if( document.getElementById('theEvent').value == 'onPressButtonKick' )
            {
                document.getElementById('targetParam').name = CEPR01020401JSP;
                document.getElementById('formpost').submit();
            }else if( document.getElementById('theEvent').value == 'onPressButtonKickAllOffUsers' )
            {
                document.getElementById('targetParam').name = CEPR01020401JSP;
                document.getElementById('formpost').submit();
            }else if( document.getElementById('theEvent').value == 'onPressButtonPingIp' )
            {
                document.getElementById('targetParam').name = CEPR01020401JSP;
                document.getElementById('formpost').submit();
            }
            
            
        }
        
        function change_bgcolor( obj, color )
        {
            obj.style.backgroundColor = color;
        }
        
        function onEnter(){
			if(event.keyCode == 13) {
				document.getElementById('btnEnter').click();
			}
		}
		
		
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="formpost" commandName="cepr01020000HoldingForm" method="post">
<TABLE WIDTH=1280 BORDER=0 CELLPADDING=0 CELLSPACING=0>
  <!--DWLayoutTable-->
	<TR>
		<TD width="178" height="38" valign="top" style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;</TD>
		<TD COLSPAN=3 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><div align="center">
		  <p class="fontUp">WELCOME TO SINARMASMSIGLIFE E-PROPOSAL [${sessionScope.deebee}]</p>
		</div></TD>
		<TD COLSPAN=4 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;			</TD>
	  <TD style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;">
		  <div align="right"><a href="${path}/wepr01020000.htm" onmouseover="changeButtonHomeToGlow();" onmouseout="changeButtonHomeToNormal();">
	      <img src="${path}/include/images/bt_home.gif" alt="" align="absbottom" id="buttonHomeId" style="border: 0;"/>
		    </a>
	    </div></TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;" /><span class="style5"> |			</span></TD>
		<TD COLSPAN=2 style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;">
		<a href="${path}/wepr01010102.htm" target="_top" onmouseover="changeButtonLogOffToGlow();" onmouseout="changeButtonLogOffToNormal();">
		<img src="${path}/include/images/bt_logoff.gif" alt="" align="absbottom" id="buttonLogOffId" style="border: 0;"/>
		</a> 
		</TD>
		<TD width="177" valign="top" style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;</TD>
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
		<TD height="40" colspan="9" class="fontLabel1 style2">Console</TD>
	    <TD ROWSPAN=4 style="background-image:url(${path}/include/images/web_proposal_15.jpg); ">&nbsp;			</TD>
	</TR>
	<TR>
	  <TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
	      
		  <table width=100% cellPadding=3 cellSpacing=1 class="nobottom" style="background-color:#FFFFFF">
                    <tbody>
                        <tr>
                            <td width="20%" class="fontForm" align="left" style="background-color:#CCCCCC;"><b>Kode Agent</b></td>
                            <td width="28%" align="left" style="background-color:#EAEAEA;">
                     			<form:input path="cepr01020401Form.msagId" onkeypress="onEnter();" cssClass="fontForm"/>
                        	</td>
                            <td width="22%" class="fontForm" align="left" style="background-color:#CCCCCC;"><b>Nama Agent</b></td>
                            <td width="22%" align="left" style="background-color:#EAEAEA;">
                            	<form:input onkeypress="onEnter();" path="cepr01020401Form.mclFirst" cssClass="fontForm"/>
                            </td>
                            <td width="8%">&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="fontForm" align="left" style="background-color:#CCCCCC;"><b>Nama Cabang</b></td>
                            <td align="left" style="background-color:#EAEAEA;">
                     			<form:input onkeypress="onEnter();" path="cepr01020401Form.lcaNama" cssClass="fontForm"/>
                        	</td>
                            <td class="fontForm" align="left" style="background-color:#CCCCCC;"><b>Nama Wakil</b></td>
                            <td align="left" style="background-color:#EAEAEA;">
                     			<form:input onkeypress="onEnter();" path="cepr01020401Form.lwkNama" cssClass="fontForm"/>
                        	</td>
							<td>&nbsp;
                                
                            </td>
                        </tr>
						<tr>
                            <td class="fontForm" align="left" style="background-color:#CCCCCC;"><b>Nama Group</b></td>
                            <td align="left" style="background-color:#EAEAEA;">
                     			<form:input onkeypress="onEnter();" path="cepr01020401Form.groupName" cssClass="fontForm"/>
                        	</td>
                            <td class="fontForm" align="left" style="background-color:#CCCCCC;"></td>
                            <td align="left" style="background-color:#EAEAEA;">
                     			
                        	</td>
                            <td>
                                <a href="#" id="btnEnter" onclick="document.getElementById('theEvent').value='onPressButtonSearch'; checkEvent();" onmouseover="changeButtonSearchToGlow();" onmouseout="changeButtonSearchToNormal();">
								<img src="${path}/include/images/bt_search.gif" alt="" style="border: 0;" id="buttonSearchId">
								</a>
                            </td>
                        </tr>
						
                    </tbody>
        </table>
		  <br style="font-size: 7px">
		  <table width=100% cellpadding="3">
        <tr>
            <td align="left" class="fontForm" style="width: 11%">
                <input type="button" name="kickAllOffUser" class="fontForm" value="KICK ALL OFFLINE USERS" onclick="document.getElementById('theEvent').value='onPressButtonKickAllOffUsers'; document.getElementById('lusIdUser').value='${user.lus_id}'; checkEvent();"/>
            </td>
            <td>
            	
            </td>
            <td align="right" class="fontForm">
                <a href="#" onclick="document.getElementById('theEvent').value='onPressButtonBack'; checkEvent();" onmouseover="changeButtonBackToGlow();" onmouseout="changeButtonBackToNormal();">
					<img src="${path}/include/images/bt_back.gif" alt="" style="border: 0;" id="buttonBackId">
				</a>
            </td>
        </tr>  
        <tr >
        	<td align="left" class="fontForm" style="color: red;" colspan="3">
       		${keterangan}        	
       		</td></tr>  
    </table>
      <br style="font-size: 7px">
		  
	      <table id="thetable" width=100% cellPadding=3 cellSpacing=1 class="nobottom" style="table-LAYOUT: fixed;" >
        <colgroup>
        	<col style="width: 80px;">
			<col style="width: 30px;">
            <col >
            <col >
            <col >
            <col >
            <col style="width: 80px;">
            <col style="width: 80px;">
            <col style="width: 70px;">
            <tbody>
                <tr>
                	<td class="thead"><font color="white">Fungsi</font></td>
					<td class="thead"><font color="white">ST.</font></td>
                    <td class="thead" ><a href="#" onclick="document.getElementById( 'sortRow' ).value = 'sortedByMsag'; document.getElementById('theEvent').value='onPressButtonSearch'; checkEvent()" ><font color="white">Kode Agent</font></a></td>
					<td class="thead" ><a href="#" onclick="document.getElementById( 'sortRow' ).value = 'sortedByAgent'; document.getElementById('theEvent').value='onPressButtonSearch'; checkEvent()" ><font color="white">Nama Agent</font></a></td>
					<td class="thead" ><a href="#" onclick="document.getElementById( 'sortRow' ).value = 'sortedByCabang'; document.getElementById('theEvent').value='onPressButtonSearch'; checkEvent()" ><font color="white">Nama Cabang</font></a></td>
					<td class="thead" ><a href="#" onclick="document.getElementById( 'sortRow' ).value = 'sortedByWakil'; document.getElementById('theEvent').value='onPressButtonSearch'; checkEvent()" ><font color="white">Nama Wakil</font></a></td>
                    <td class="thead" ><a href="#" onclick="document.getElementById( 'sortRow' ).value = 'sortedByGroup'; document.getElementById('theEvent').value='onPressButtonSearch'; checkEvent()" ><font color="white">Nama Group</font></a></td>
                    <td class="thead" ><font color="white">IP Address</font></td>
                    <td class="thead" ><font color="white">Login Time</font></td>
                </tr>
                <tr>
                    <td colspan="9" style="height: 1px; background-color: red; padding: 0"/>
                </tr>
		
		<c:set var="backGroundColor" value="#EAEAEA"/>
                		
		<c:forEach var="user" items="${daftarUser}" varStatus="xt">
    
        	<c:choose>
        		<c:when test='${"#EAEAEA" == backGroundColor}'>
        			<c:set var="backGroundColor" value="#FFFFFF"/>
        		</c:when>
        		<c:otherwise>
            		<c:set var="backGroundColor" value="#EAEAEA"/>
        		</c:otherwise>
    		</c:choose>
    
           <tr class="rowClass" style="background-color: ${backGroundColor};" onMouseOver="change_bgcolor( this, '#FFD6CA' );" onMouseOut="change_bgcolor(this, '${backGroundColor}')" valign="top" >
				<td class="fontForm">
					<input type="button" name="kickUser" class="fontForm" value="KICK" onclick="document.getElementById('theEvent').value='onPressButtonKick'; document.getElementById('lusIdUser').value='${user.lus_id}'; checkEvent();"/>
					<input type="button" name="pingUser" class="fontForm" value="PING" onclick="document.getElementById('theEvent').value='onPressButtonPingIp'; document.getElementById('ipAddressUser').value='${user.ip}'; checkEvent();"/>
                </td>
				<td class="fontForm" align="center">
					<c:choose>
						<c:when test="${user.online eq 1}">
							<span style="color: green;">ON</span>
						</c:when>
						<c:otherwise>
							<span style="color: red;">OFF</span>
						</c:otherwise>
					</c:choose>
				</td>
                <td class="fontForm">
                    <label for="user[${xt.index}].lus_id" >
                        ${user.lus_id}
                    </label>
                </td>
				<td class="fontForm">
                    <label for="user[${xt.index}].name" >
                        ${user.name}
                    </label>
                </td>
				<td class="fontForm">
                    <label for="user[${xt.index}].cabang" >
                        ${user.cabang}
                    </label>
                </td>
				<td class="fontForm">
                    <label for="user[${xt.index}].wakil" >
                        ${user.wakil}
                    </label>
                </td>
                <td class="fontForm">
                    <label for="user[${xt.index}].groupName" >
                        ${user.groupName}
                    </label>
                </td>
                <td class="fontForm">
                    <label for="user[${xt.index}].ip" >
                        ${user.ip}
                    </label>
                </td>
                <td class="fontForm">
                    <label for="user[${xt.index}].loginTime" >
                        <fmt:formatDate value="${user.loginTime}" pattern="dd/MM/yyyy (hh:mm:ss)"/>
                    </label>
                </td>
          </tr>
        </c:forEach>
                
    </table>
    <c:forEach var="userStatusResult" items="${pingIpResultList}" varStatus="xt">
	  	${userStatusResult}
	</c:forEach>
	  <table width="100%" cellpadding="2">
        
        <tr>
            
        </tr>
    </table>
      </TD>
	</TR>
	<TR>
		
        <input type="hidden" name="sortRow" id="sortRow"/>
        <input type="hidden" name="lusIdUser" id="lusIdUser"/>
        <input type="hidden" name="ipAddressUser" id="ipAddressUser"/>
        <input type="hidden" name="ipAddressUserTemp" id="ipAddressUserTemp" value="${pingIpResult}"/>
        <input type="hidden" name="selectedMsagId" id="selectedMsagId"/>
		<input type="hidden" name="selectedGroupId" id="selectedGroupId"/>
        <input type="hidden" name="theEvent" id="theEvent"/>
		<input type="hidden" id="isCheckEvent"/>
        <input type="hidden" name="targetParam" id="targetParam"/>
		
		<TD  ></TD>
		
		<TD COLSPAN=2></TD>
		<TD><div align="center">
		
		</div></TD>
		<TD COLSPAN=5>&nbsp;			</TD>
    </TR>
	
	<TR>
	  <TD  colspan="9" class="fontForm">
	  <b>
	  
	  </b>
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
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=177 HEIGHT=1 ALT=""></TD>
    </TR>
</TABLE>
<script type="text/javascript">
	if( document.getElementById('ipAddressUserTemp').value != '' )
    {
    	alert(document.getElementById('ipAddressUserTemp').value);
    }
</script>
</form:form>

</BODY>
</HTML>