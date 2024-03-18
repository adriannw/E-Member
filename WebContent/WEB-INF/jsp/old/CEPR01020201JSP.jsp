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
            if( document.forms[ 0 ].theEvent.value == 'onPressButtonSearch' )
            {
                document.getElementById('targetParam').name = CEPR01020201JSP;
                document.getElementById('formpost').submit();
            }else if( document.forms[ 0 ].theEvent.value == 'onPressButtonBack' )
            {
                document.getElementById('targetParam').name = CEPR01020101JSP;
                document.getElementById('formpost').submit();
            }else if( document.forms[ 0 ].theEvent.value == 'onPressButtonAddGroup' )
            {
                document.getElementById('targetParam').name = CEPR01020201JSP;
                document.getElementById('formpost').submit();
            }else if( document.forms[ 0 ].theEvent.value == 'onPressButtonDeleteGroup' )
            {
                document.getElementById('targetParam').name = CEPR01020201JSP;
                document.getElementById('formpost').submit();
            }else if( document.forms[ 0 ].theEvent.value == 'onPressButtonChange' )
            {
                document.getElementById('targetParam').name = CEPR01020201JSP;
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
        
        function onEnter(){
			if(event.keyCode == 13) {
				document.getElementById('btnEnter').click();
			}
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
	  <TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
	      
		  <table width=100% cellPadding=3 cellSpacing=1 class="nobottom" style="background-color:#FFFFFF">
                    <tbody>
                        <tr>
                            <td width="20%" class="fontForm" align="left" style="background-color:#CCCCCC;"><b>Nama Group</b></td>
                            <td width="28%" align="left" style="background-color:#EAEAEA;">
                     			<form:input path="cepr01020201Form.groupName" onkeypress="onEnter();" cssClass="fontForm"/>
                        	</td>
                            <td width="22%" class="fontForm" align="left" style="background-color:#CCCCCC;"><b>Records/Page</b></td>
                            <td width="22%" align="left" style="background-color:#EAEAEA;">
                            	<form:select path="cepr01020201Form.noRow" cssClass="fontForm" onchange="document.forms[ 0 ].theEvent.value='onPressButtonSearch'; checkEvent();"
                     				items="${cepr01020000HoldingForm.cepr01020201Form.listRecords}" itemLabel="label"
                     				itemValue="value" />
                            </td>
                            <td width="8%"></td>
                        </tr>
                        <tr>
                            <td class="fontForm" align="left" style="background-color:#CCCCCC;">&nbsp;</td>
                            <td align="left" style="background-color:#EAEAEA;">&nbsp;
                     			
                        	</td>
                            <td class="fontForm" align="left" style="background-color:#CCCCCC;">&nbsp;</td>
                            <td style="background-color:#EAEAEA;">&nbsp;
                            </td>
                            <td>
                                <a href="#" id="btnEnter" onclick="document.forms[ 0 ].theEvent.value='onPressButtonSearch'; checkEvent();" onmouseover="changeButtonSearchToGlow();" onmouseout="changeButtonSearchToNormal();">
								<img src="${path}/include/images/bt_search.gif" alt="" style="border: 0;" id="buttonSearchId">
								</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
		  <br style="font-size: 7px">
		  <table width=100% cellpadding="3">
        	<tr>
            <td align="left" class="fontForm" style="width: 8%">
                Group Name
            </td>
            <td style="width: 17%">
            	<form:input path="cepr01020201Form.addGroup" cssClass="fontForm"/>
            </td>
            <td style="width: 12%">
            	<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonAddGroup'; checkEvent();" onmouseover="changeButtonAddToGlow();" onmouseout="changeButtonAddToNormal();">
					<img src="${path}/include/images/bt_add.gif" alt="" style="border: 0;" id="buttonAddId">
				</a>
			</td>
			<td>
				<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonDeleteGroup'; checkEvent();" onmouseover="changeButtonDeleteToGlow();" onmouseout="changeButtonDeleteToNormal();">
					<img src="${path}/include/images/bt_delete.gif" alt="" style="border: 0;" id="buttonDeleteId">
				</a>
            </td>
            <td align="left" class="fontForm" style="width: 60px">
                <a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonBack'; checkEvent();" onmouseover="changeButtonBackToGlow();" onmouseout="changeButtonBackToNormal();">
					<img src="${path}/include/images/bt_back.gif" alt="" style="border: 0;" id="buttonBackId">
				</a>
            </td>
        	</tr>
        	<tr>
        		<td align="left" class="fontForm" style="color: red;" colspan="5">
        		${keterangan}
        		</td>
        	</tr>  
        	
    	</table>
      <br style="font-size: 7px">
		  
	      <table id="thetable" width=100% cellPadding=3 cellSpacing=1 class="nobottom" style="table-LAYOUT: fixed;" >
        <colgroup>
			<col style="width: 30px;">
            <col >
            <col >
            <!--<col style="text-align: center; width: 100%">-->
            <tbody>
                <tr>
					<td class="thead"><input type="checkbox" name="delAllRow" value="delAllRow" onclick="copy()" ></td>
                    <td class="thead" ><a href="#" onclick="document.getElementById( 'sortRow' ).value = 'sortedById'; document.forms[ 0 ].theEvent.value='onPressButtonSearch'; " ><font color="white">ID</font></a></td>
                    <td class="thead" ><a href="#" onclick="document.getElementById( 'sortRow' ).value = 'sortedByGroup'; document.forms[ 0 ].theEvent.value='onPressButtonSearch';" ><font color="white">Nama Group</font></a></td>
                </tr>
                <tr>
                    <td colspan="3" style="height: 1px; background-color: red; padding: 0"/>
                </tr>
		
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
    
           <tr class="rowClass" style="background-color: ${backGroundColor};" onClick="document.getElementById( 'selectedGroupId' ).value = '${ViewListGroup.groupId}'; document.forms[ 0 ].theEvent.value='onSelectRow'; checkEvent()" onMouseOver="change_bgcolor( this, '#FFD6CA' );" onMouseOut="change_bgcolor(this, '${backGroundColor}')" valign="top" >
				<td align="center">
                	<input type="checkbox" name="chkArr" value="${ViewListGroup.groupId}" onclick="document.getElementById( 'isCheckEvent' ).value = true" >
                </td>
                <td class="fontForm">
                    <label for="ViewListGroup[${xt.index}].groupId" >
                        ${ViewListGroup.groupId}
                    </label>
                </td>
                <td class="fontForm">
                    <label for="ViewListGroup[${xt.index}].groupName" >
                        ${ViewListGroup.groupName}
                    </label>
                </td>
          </tr>
        </c:forEach>
                
    </table>
	  
	  <table width="100%" cellpadding="2">
        
        <tr>
            <td width="60%" class="fontForm">Page ${currPage} of ${cepr01020000HoldingForm.cepr01020201Form.lastPage} </td>
            <td width= align="right" class="fontForm">Go
                <input name="cPage" type="text" style="width:45px" maxlength="4" class="fontForm"/>
			<td>
                <a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonSearch'; checkEvent();" onmouseover="changeButtonGoToGlow();" onmouseout="changeButtonGoToNormal();">
				<img src="${path}/include/images/bt_go.gif" alt="" style="border: 0;" id="buttonGoId">
				</a>
			</td>
			<td style="font-family: Arial; font-size: 11px;">

                <c:choose>
        			<c:when test="${currPage eq '1'}">
        				<font color="gray">First|Prev|</font> 
        			</c:when>
        			<c:otherwise>
               			<a href="#"  onclick="document.forms[ 0 ].cPage.value='${cepr01020000HoldingForm.cepr01020201Form.firstPage}'; document.forms[ 0 ].theEvent.value='onPressButtonSearch'; checkEvent()">First</a> | 
                		<a href="#"  onclick="document.forms[ 0 ].cPage.value='${cepr01020000HoldingForm.cepr01020201Form.previousPage}'; document.forms[ 0 ].theEvent.value='onPressButtonSearch'; checkEvent()">Prev</a> | 
                	</c:otherwise>
                </c:choose>
                
                
                
                <c:choose>
        			<c:when test="${currPage eq cepr01020000HoldingForm.cepr01020201Form.lastPage}">
        				<font color="gray">Next|Last</font>
        			</c:when>
        			<c:otherwise>
               			<a href="#"  onclick="document.forms[ 0 ].cPage.value='${cepr01020000HoldingForm.cepr01020201Form.nextPage}'; document.forms[ 0 ].theEvent.value='onPressButtonSearch'; checkEvent()">Next</a> | 
                		 <a href="#"  onclick="document.forms[ 0 ].cPage.value='${cepr01020000HoldingForm.cepr01020201Form.lastPage}'; document.forms[ 0 ].theEvent.value='onPressButtonSearch'; checkEvent()">Last</a> 
                	</c:otherwise>
                </c:choose>
                
                
               
                </td>
        </tr>
    </table>
	     </TD>
	</TR>
	<TR>
		
        <input type="hidden" name="sortRow" id="sortRow"/>
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