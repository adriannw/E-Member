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
	 	function changeButtonBackToGlow()
        {
            document.getElementById( "buttonBackId" ).src = '${path}/include/images/bt_back-ro.gif';
        }
        function changeButtonBackToNormal()
        {
            document.getElementById( "buttonBackId" ).src = '${path}/include/images/bt_back.gif';
        }
 		function changeButtonSaveAddToGlow()
        {
            document.getElementById( "buttonSaveAddId" ).src = '${path}/include/images/bt_saveadd-ro.gif';
        }
        function changeButtonSaveAddToNormal()
        {
            document.getElementById( "buttonSaveAddId" ).src = '${path}/include/images/bt_saveadd.gif';
        }
		function changeButtonSaveToGlow()
        {
            document.getElementById( "buttonSaveId" ).src = '${path}/include/images/bt_save-ro.gif';
        }
        function changeButtonSaveToNormal()
        {
            document.getElementById( "buttonSaveId" ).src = '${path}/include/images/bt_save.gif';
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
            if( document.forms[ 0 ].theEvent.value == 'onPressButtonSaveAdd' )
            {
                document.getElementById('targetParam').name = CEPR01020202JSP;
                document.getElementById('formpost').submit();
            }else if( document.forms[ 0 ].theEvent.value == 'onPressButtonBack' )
            {
                document.getElementById('targetParam').name = CEPR01020201JSP;
                document.getElementById('formpost').submit();
            }else if( document.forms[ 0 ].theEvent.value == 'onPressButtonSaveGroup' )
            {
                document.getElementById('targetParam').name = CEPR01020202JSP;
                document.getElementById('formpost').submit();
            }else if( document.forms[ 0 ].theEvent.value == 'onSelectRow' )
            {
            	var isCheckEvent = document.getElementById( 'isCheckEvent' ).value;
            	if( isCheckEvent != 'true' )
            	{
                	document.getElementById('targetParam').name = CEPR01020202JSP;
                	document.getElementById('formpost').submit();
                }
                document.getElementById( 'isCheckEvent' ).value = 'false';
            }
        }
        
        function change_bgcolor( obj, color )
        {
            obj.style.backgroundColor = color;
        }
		
		function copy()
        {
        	var checkbox = document.forms[ 0 ].delAllRow;
        	var checkboxes = document.forms[ 0 ].chkArr;
        	
        	if(checkbox.checked == 1){
				for(i = 0; i < checkboxes.length; i++){
				checkboxes[i].checked = 1;
				}
			}else{
				for(i = 0; i < checkboxes.length; i++){
				checkboxes[i].checked = 0;
				}
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
		<a href="http://serverdmtm1:8080/PlanSim" onmouseover="changeButtonLogOffToGlow();" onmouseout="changeButtonLogOffToNormal();">
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
		<TD height="40" colspan="9" class="fontLabel1 style2">Maintain Access Group</TD>
	    <TD ROWSPAN=4 style="background-image:url(${path}/include/images/web_proposal_15.jpg); ">&nbsp;			</TD>
	</TR>
	<TR>
	  <TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; "><p>&nbsp;</p>
	  <table width=100% cellpadding="3">
	  	<tr>
	  		<td align="left" class="fontForm" colspan="4">
        		<b>${groupNameLabel}</b>
        	</td>
	  	</tr>
        <tr>
            <td align="left" Class="fontForm" style="width: 23%">
                <form:select path="cepr01020202Form.lsbsId" cssClass="fontForm" 
                     			items="${allBusiness}" itemLabel="label"
                     			itemValue="value" />
			</td>
			<td style="width: 12%">
            	<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonSaveAdd'; checkEvent();" onmouseover="changeButtonSaveAddToGlow();" onmouseout="changeButtonSaveAddToNormal();">
					<img src="${path}/include/images/bt_saveadd.gif" alt="" style="border: 0;" id="buttonSaveAddId">
				</a>
			</td>
			<td>
				<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonSaveGroup'; checkEvent();" onmouseover="changeButtonSaveToGlow();" onmouseout="changeButtonSaveToNormal();">
					<img src="${path}/include/images/bt_save.gif" alt="" style="border: 0;" id="buttonSaveId">
				</a>
            </td>
			<td align="right">
				<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonBack'; checkEvent();" onmouseover="changeButtonBackToGlow();" onmouseout="changeButtonBackToNormal();">
					<img src="${path}/include/images/bt_back.gif" alt="" style="border: 0;" id="buttonBackId">
				</a>
			</td>
        </tr> 
        <tr>
        		<td align="left" class="fontForm" style="color: red;" colspan="4">
        		${keterangan}
        		</td>
        	</tr>  
    </table>
      
		  
	      <table id="thetable" width=100% cellPadding=2 cellSpacing=1 class="nobottom" style="table-LAYOUT: fixed;" >
        <colgroup>
			<col style="width: 30px;">
            <col >
            
            <!--<col style="text-align: center; width: 100%">-->
            <tbody>
                <tr>
					<td class="thead"><input type="checkbox" name="delAllRow" value="delAllRow" onclick="copy()" ></td>
                    <td class="thead" ><a href="#" onclick="document.getElementById( 'sortRow' ).value = 'sortedById'; document.forms[ 0 ].theEvent.value='onPressButtonSearch'; " ><font color="white">Business</font></a></td>
                </tr>
                <tr>
                    <td colspan="2" style="height: 1px; background-color: red; padding: 0"/>
                </tr>
		
		<c:set var="backGroundColor" value="#EAEAEA"/>
        <c:set var="valueControl" value="0"/>
        <c:set var="colorControl" value="0"/>
        <c:set var="valueTemp" value="0"/>
		<c:forEach var="ViewListBusiness" items="${allList}" varStatus="xt">

    		<c:choose> 
                <c:when test='${"0" == valueControl }'>
                	<c:set var="backGroundColor" value="#EAEAEA"/>
                </c:when>
                <c:otherwise>
                    <c:choose> 
                    	<c:when test='${valueTemp eq ViewListBusiness.lsbsName}'>
                    		<c:set var="backGroundColor" value="#FFFFFF"/>
                    	</c:when>
                    	<c:otherwise>
                    		<c:set var="backGroundColor" value="#EAEAEA"/>
                    	</c:otherwise>
                    </c:choose>
                 </c:otherwise>
           	</c:choose>
    		
           <tr class="rowClass" style="background-color: ${backGroundColor};"  onMouseOver="change_bgcolor( this, '#FFD6CA' );" onMouseOut="change_bgcolor(this, '${backGroundColor}')" valign="top" >
				<td align="center">
					<c:choose> 
						<c:when test='${"checked" == ViewListBusiness.flag}'>
							<c:choose> 
								<c:when test='${"#EAEAEA" == backGroundColor }'>
									<input type="checkbox" checked="checked" name="chkArr" value="${ViewListBusiness.lsbsId}x${ViewListBusiness.lsdbsNumber}" onclick="document.getElementById( 'isCheckEvent' ).value = true" >
								</c:when>
								<c:otherwise>
									<input type="checkbox" checked="checked" name="chkArr" value="${ViewListBusiness.lsdbsNumber}@${ViewListBusiness.lsbsId}" onclick="document.getElementById( 'isCheckEvent' ).value = true" >
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose> 
								<c:when test='${"#EAEAEA" == backGroundColor }'>
									<input type="checkbox" checked="checked" name="chkArr" value="${ViewListBusiness.lsbsId}x${ViewListBusiness.lsdbsNumber}" onclick="document.getElementById( 'isCheckEvent' ).value = true" >
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="chkArr" value="${ViewListBusiness.lsdbsNumber}@${ViewListBusiness.lsbsId}" onclick="document.getElementById( 'isCheckEvent' ).value = true" >
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
                </td>
                
                <td class="fontForm">
                	<c:choose> 
                		<c:when test='${"0" == valueControl }'>
                    		<label for="ViewListBusiness[${xt.index}].lsbsName" >
                        		${ViewListBusiness.lsbsName}
                    		</label>
                    		<c:set var="valueControl" value="1"/>
                    		<c:set var="valueTemp" value="${ViewListBusiness.lsbsName}"/>
                    	</c:when>
                    	<c:otherwise>
                    		<c:choose> 
                    			<c:when test='${valueTemp eq ViewListBusiness.lsbsName}'>
                    				<label for="ViewListBusiness[${xt.index}].lsdbsName" >
                        				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ViewListBusiness.lsdbsName}&nbsp;(${ViewListBusiness.lsbsId}~X${ViewListBusiness.lsdbsNumber})
                    				</label>
                    				<c:set var="valueTemp" value="${ViewListBusiness.lsbsName}"/>
                    			</c:when>
                    			<c:otherwise>
                    				<label for="ViewListBusiness[${xt.index}].lsbsName" >
                        				${ViewListBusiness.lsbsName}
                    				</label>
                    				<c:set var="valueTemp" value="${ViewListBusiness.lsbsName}"/>
                    			</c:otherwise>
                    		</c:choose>
                    	</c:otherwise>
                    </c:choose>
                </td>
          </tr>
          <c:choose> 
             <c:when test='${"#EAEAEA" == backGroundColor }'>
             	<c:set var="backGroundColor" value="#FFFFFF"/>
             	<tr class="rowClass" style="background-color: ${backGroundColor};"  onMouseOver="change_bgcolor( this, '#FFD6CA' );" onMouseOut="change_bgcolor(this, '${backGroundColor}')" valign="top" >
					<td align="center">
                		<c:choose> 
						<c:when test='${"checked" == ViewListBusiness.flag}'>
							<input type="checkbox" checked="checked" name="chkArr" value="${ViewListBusiness.lsdbsNumber}@${ViewListBusiness.lsbsId}" onclick="document.getElementById( 'isCheckEvent' ).value = true" >
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="chkArr" value="${ViewListBusiness.lsdbsNumber}@${ViewListBusiness.lsbsId}" onclick="document.getElementById( 'isCheckEvent' ).value = true" >
						</c:otherwise>
						</c:choose>
                	</td>
                	<td class="fontForm">
                		<label for="ViewListBusiness[${xt.index}].lsdbsName" >
                    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ViewListBusiness.lsdbsName}&nbsp;(${ViewListBusiness.lsbsId}~X${ViewListBusiness.lsdbsNumber})
                    	</label>
               		</td>
                </tr>
                <c:set var="backGroundColor" value="#EAEAEA"/>
             </c:when>
          </c:choose>
        </c:forEach>
                
    </table>
	  
	  
      </TD>
	</TR>
	<TR>
		
        <input type="hidden" name="sortRow" id="sortRow"/>
        <input type="hidden" name="selectedMsagId" id="selectedMsagId"/>
		<input type="hidden" name="selectedGroupId" id="selectedGroupId" value='${groupId}'/>
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
		<TD>
			<IMG SRC="${path}/include/images/spacer.gif" WIDTH=177 HEIGHT=1 ALT=""></TD>
    </TR>
</TABLE>
</form:form>


</BODY>
</HTML>