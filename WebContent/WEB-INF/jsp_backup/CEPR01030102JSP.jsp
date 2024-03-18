<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<HEAD>
<link href="${path}/include/css/general.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${path}/include/js/jquery.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
<style type="text/css">
<!--
.style2 {font-size: 20px}
.style3 {color: #FF9900}
.style5 {color: #FFFFFF}
-->
</style>

 <script type="text/javascript">
  var CEPR01030107JSP = '_target12';
  var CEPR01030113JSP = '_target20';
   
        function changeButtonPreviewProposalToGlow()
        {
            document.getElementById( "buttonPreviewProposalId" ).src = '${path}/include/images/bt_lihatproposal-ro.gif';
        }
        function changeButtonPreviewProposalToNormal()
        {
            document.getElementById( "buttonPreviewProposalId" ).src = '${path}/include/images/bt_lihatproposal.gif';
        }
		function changeButtonBackToGlow()
        {
            document.getElementById( "buttonBackId" ).src = '${path}/include/images/bt_kembali-ro.gif';
        }
		function changeButtonBackToNormal()
        {
            document.getElementById( "buttonBackId" ).src = '${path}/include/images/bt_kembali.gif';
        }
        function changeButtonParticipantEkaSehatToGlow()
        {
            document.getElementById( "buttonParticipantEkaSehatId" ).src = '${path}/include/images/bt_peserta-ro.gif';
        }
         function changeButtonParticipantEkaSehatToNormal()
        {
            document.getElementById( "buttonParticipantEkaSehatId" ).src = '${path}/include/images/bt_peserta.gif';
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
		function changeButtonCountPremiumToGlow()
        {
            document.getElementById( "buttonCountPremiumId" ).src = '${path}/include/images/bt_hitung-ro.gif';
        }
        function changeButtonCountPremiumToNormal()
        {
            document.getElementById( "buttonCountPremiumId" ).src = '${path}/include/images/bt_hitung.gif';
        }
		function changeButtonRiderToGlow()
        {
            document.getElementById( "buttonRiderId" ).src = '${path}/include/images/bt_rider-ro.gif';
        }
        function changeButtonRiderToNormal()
        {
            document.getElementById( "buttonRiderId" ).src = '${path}/include/images/bt_rider.gif';
        }
		function changeButtonTopupToGlow()
        {
            document.getElementById( "buttonTopupId" ).src = '${path}/include/images/bt_topuppenarikan-ro.gif';
        }
        function changeButtonTopupToNormal()
        {
            document.getElementById( "buttonTopupId" ).src = '${path}/include/images/bt_topuppenarikan.gif';
        }
		function changeButtonCountTotalToGlow()
        {
            document.getElementById( "buttonCountTotalId" ).src = '${path}/include/images/bt_hitung-ro.gif';
        }
        function changeButtonCountTotalToNormal()
        {
            document.getElementById( "buttonCountTotalId" ).src = '${path}/include/images/bt_hitung.gif';
        }
		
		function checkEvent()
        {          
            
			var theEvent = document.forms[ 0 ].theEvent.value;
            if( theEvent == 'onPressButtonPreviewProposal' )
            {
            	document.body.style.cursor = "wait";
            	
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onChangesmileLadiesPackageCd' )
            {
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
              else if( theEvent == 'onChangeSmilePensionPackageCd' )
            {
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
               else if( theEvent == 'onChangesecondPackageCd' || theEvent == 'onChangeEducationPackageCd' || theEvent == 'onChangeProtectorPackageCd' || theEvent == 'onChangeJenisCd')
            {
            
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonFax' )
            {
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonEmail' )
            {
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onChangeAssurancePlanCd' || theEvent == 'onChangeCurrencyCd' )
            {
                document.getElementById('cepr01030102Form.downloadFlag').value = '';
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonCountPremium' ||
                     theEvent == 'onPressButtonCountTotalSumInsured' ||
                     theEvent == 'onChangePaymentModeCd' )
            {
            	document.body.style.cursor = "wait";
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressChangeBasePremium' )
            {
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonBack' )
            {
                document.getElementById('targetParam').name = CEPR01030101JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonParticipantEkaSehat' )
            {
                document.getElementById('targetParam').name = CEPR01030107JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonParticipantMedicalPlus' )
            {
                document.getElementById('targetParam').name = CEPR01030113JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonParticipantHcpProvider' )
            {
                document.getElementById('targetParam').name = CEPR01030109JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonRider' )
            {
                document.getElementById('targetParam').name = CEPR01030103JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onPressButtonTopup' )
            {
                document.getElementById('targetParam').name = CEPR01030104JSP;
                document.getElementById('formpost').submit();
            }
            else if( theEvent == 'onChangeTotalSumInsured' )
            {
            	var totalSumInsuredOptionIsToRefreshPage = document.getElementById( 'cepr01030102Form.totalSumInsuredOptionIsToRefreshPage' ).value;
            	if( totalSumInsuredOptionIsToRefreshPage == 'true' )
            	{
            		document.getElementById( 'targetParam' ).name = CEPR01030102JSP;
                	document.getElementById( 'formpost' ).submit();
                }
            }
            else if( theEvent == 'onChangePremium' )
            {
            	var premiumOptionIsToRefreshPage = document.getElementById( 'cepr01030102Form.premiumOptionIsToRefreshPage' ).value;
            	if( premiumOptionIsToRefreshPage == 'true' )
            	{
            		document.getElementById( 'targetParam' ).name = CEPR01030102JSP;
                	document.getElementById( 'formpost' ).submit();
                }
            }
			
        }
        
    var no_proposal = '${no_proposal}';
    var lead = '${lead}';
    var credential = '${credential}';
    //var url = 'https://crm.sinarmasmsiglife.co.id/index.php/';
    var url = '${hostname}';
   // alert(credential +' '+ lead);
    
    if(no_proposal != ''){
    
    	//alert(typeof $);  
	    var cred = '${credential}';
				//alert(cred);
				$.ajax({
	        		url: url+"external/update_proposal_bac",
	        		data:{'no_proposal':no_proposal,'lead_id':lead,'credential':credential},
	        	    dataType : 'json',
	        		success: function(result){
					//	alert(result);
	        		}
	        	});
    	alert('NO PROPOSAL : '+no_proposal);
    }
    
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">
<form:form id="formpost" commandName="cepr01030000HoldingForm" name="">
<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>
  <!--DWLayoutTable-->
   <c:if test="${cepr01030000HoldingForm.credentialsVO.msagId != '999993'}">
	<tr>
		<TD width="144" height="38" valign="top" style="background-image:url(${path}/include/images/web_proposal_03.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;</TD>
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
    </tr>
	<tr>
	  <TD ROWSPAN=11>&nbsp;</TD>
	  <TD height="19" style="background-image:url(${path}/include/images/web_proposal_09.jpg); ">&nbsp;			</TD>
		<TD COLSPAN=9 style="background-image:url(${path}/include/images/web_proposal_10.jpg); background-repeat:repeat-x; ">&nbsp;			</TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_11.jpg); ">&nbsp;			</TD>
        <TD ROWSPAN=11>&nbsp;</TD>
	</tr>
	<tr>
	  <TD ROWSPAN=9 style="background-image:url(${path}/include/images/web_proposal_12.jpg); ">&nbsp;			</TD>
		<TD height="40" colspan="9" class="fontLabel1 style2"><fmt:message key="words.proposalDetail"/>			</TD>
	    <TD ROWSPAN=9 style="background-image:url(${path}/include/images/web_proposal_15.jpg); ">&nbsp;			</TD>
	</tr>
	</c:if>
	<tr>
	  <TD colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
      
	  
	  <table>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.assurancePlanListDisplay}">
    <td  class="tdFormLabel" width="160">
        <fmt:message key="words.assurancePlan"/>
        :
    </td>
    <td colspan="3" class="fontForm">
        <!-- <form:select path="cepr01030102Form.assurancePlanCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.assurancePlanList}"
                     itemLabel="label" itemValue="value"
                     onchange="document.forms[ 0 ].theEvent.value='onChangeAssurancePlanCd'; checkEvent()"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.assurancePlanListIsDisabled}"
                />-->
                
                 <spring:bind path="cepr01030000HoldingForm.cepr01030102Form.assurancePlanCd">
                	<select name="cepr01030102Form.assurancePlanCd" class="fontForm" onchange="document.forms[ 0 ].theEvent.value='onChangeAssurancePlanCd'; checkEvent()">
                     	<c:forEach items="${cepr01030000HoldingForm.cepr01030102Form.assurancePlanList}" var="pl">
                     	<c:choose>
                     	 <c:when test="${cepr01030000HoldingForm.credentialsVO.msagId == '999998' || cepr01030000HoldingForm.credentialsVO.msagId == '999987' || cepr01030000HoldingForm.credentialsVO.msagId == '999986' || cepr01030000HoldingForm.credentialsVO.msagId == '999979' || cepr01030000HoldingForm.credentialsVO.msagId == '999985' || cepr01030000HoldingForm.credentialsVO.msagId == '999984' || cepr01030000HoldingForm.credentialsVO.msagId == '999980' }">	
                     		<c:choose>
                     		<c:when test="${pl.flag_aktif eq '0'}">
                     		  <option value="${pl.value}" style="background-color: #F99" <c:if test="${plan eq pl.value}">selected</c:if>
                     		  >${pl.label}</option>                     		
                     		</c:when>
                     		<c:when test="${pl.flag_aktif eq '1'}">
                     		<option value="${pl.value}" style="background-color: lightgreen" <c:if test="${plan eq pl.value}">selected</c:if>
                     		>${pl.label}</option> 
                     		</c:when>
                     		<c:otherwise>
                     		<option value="${pl.value}" <c:if test="${plan eq pl.value}">selected</c:if>>${pl.label}</option> 
                     		</c:otherwise>
                     		</c:choose> 
                     	</c:when>
                     	<c:otherwise>                     	
<!--                 <c:choose> 
               		  <c:when test="${pl.flag_aktif eq '1'}">
                     		 <option value="${pl.value}" <c:if test="${plan eq pl.value}">selected</c:if>>${pl.label}</option>                     		
                       </c:when>
                      </c:choose> -->	
                       <option value="${pl.value}" <c:if test="${plan eq pl.value}">selected</c:if>>${pl.label}</option>       
                     	</c:otherwise>
                     	</c:choose>
                   		</c:forEach>
                    </select>
                </spring:bind>
        <c:if test="${cepr01030000HoldingForm.cepr01030102Form.leftPartOfBusinessCd!=null}">
            (${cepr01030000HoldingForm.cepr01030102Form.assurancePlanCd})
             <c:if test="${cepr01030000HoldingForm.credentialsVO.msagId == '999998' || cepr01030000HoldingForm.credentialsVO.msagId == '999987' || cepr01030000HoldingForm.credentialsVO.msagId == '999986' || cepr01030000HoldingForm.credentialsVO.msagId == '999979' || cepr01030000HoldingForm.credentialsVO.msagId == '999985' || cepr01030000HoldingForm.credentialsVO.msagId == '999984' || cepr01030000HoldingForm.credentialsVO.msagId == '999980' }">	 
            * Warna <font color=green>Hijau</font>/Hitam: Aktif - <font color=red>Merah</font>: Non-Aktif
       		</c:if>
        </c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <form:errors path="cepr01030102Form.assurancePlanCd" cssClass="tdFormError"/>
    </td>
    
  
</tr>

  <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.smileLadiesPackageListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.smileLadiesPackage"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.smileLadiesPackageCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.smileLadiesPackageList}"
                     itemLabel="label" itemValue="value"
                     onchange="document.forms[ 0 ].theEvent.value='onChangesmileLadiesPackageCd'; checkEvent()"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.smileLadiesPackageListIsDisabled}"
                />
    </td>
     <td colspan="2" class="tdFormError">
        <form:errors path="cepr01030102Form.smileLadiesPackageCd"/>
    </td>
</tr>

  <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.smilePensionPackageListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.smilePensionPackage"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.smilePensionPackageCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.smilePensionPackageList}"
                     itemLabel="label" itemValue="value"
                     onchange="document.forms[ 0 ].theEvent.value='onChangeSmilePensionPackageCd'; checkEvent()"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.smilePensionPackageListIsDisabled}"
                />
    </td>
     <td colspan="2" class="tdFormError">
        <form:errors path="cepr01030102Form.smilePensionPackageCd"/>
    </td>
</tr>

 <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.secondPackageListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.ibadahPackage"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.secondPackageCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.secondPackageList}"
                     itemLabel="label" itemValue="value"
                     onchange="document.forms[ 0 ].theEvent.value='onChangesecondPackageCd'; checkEvent()"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.secondPackageListIsDisabled}"
                />
    </td>
     <td colspan="2" class="tdFormError">
        <form:errors path="cepr01030102Form.secondPackageCd"/>
    </td>
</tr>


 <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.providerTypeListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.providerType"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.providerTypeCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.providerTypeList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.providerTypeListIsDisabled}"
                />
    </td>
     <td colspan="2" class="tdFormError">
        <form:errors path="cepr01030102Form.providerTypeCd"/>
    </td>
</tr>


 <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.educationPackageListDisplay}">
    <td class="tdFormLabel">
      Paket 
      <c:choose>
      <c:when test="${cepr01030000HoldingForm.cepr01030102Form.leftPartOfBusinessCd == '120' && (cepr01030000HoldingForm.cepr01030102Form.rightPartOfBusinessCd == '11' || cepr01030000HoldingForm.cepr01030102Form.rightPartOfBusinessCd == '23')}">
      SIMPOL Education
        :
      </c:when>   
      <c:otherwise>
      <fmt:message key="words.smileEducation"/>
        :      
      </c:otherwise>
     </c:choose> 
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.educationPackageCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.educationPackageList}"
                     itemLabel="label" itemValue="value"
                     onchange="document.forms[ 0 ].theEvent.value='onChangeEducationPackageCd'; checkEvent()"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.educationPackageListIsDisabled}"
                />
    </td>
     <td colspan="2" class="tdFormError">
        <form:errors path="cepr01030102Form.educationPackageCd"/>
    </td>
</tr>

 <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.protectorPackageListDisplay}">
    <td class="tdFormLabel">     
      <fmt:message key="words.simpolProtector"/>
        :  
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.protectorPackageCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.protectorPackageList}"
                     itemLabel="label" itemValue="value"
                     onchange="document.forms[ 0 ].theEvent.value='onChangeProtectorPackageCd'; checkEvent()"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.protectorPackageListIsDisabled}"
                />
    </td>
     <td colspan="2" class="tdFormError">
        <form:errors path="cepr01030102Form.protectorPackageCd"/>
    </td>
</tr>


<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.currencyListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.currency"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.currencyCd" items="${cepr01030000HoldingForm.cepr01030102Form.currencyList}"
                     itemLabel="label" itemValue="value" cssClass="fontForm"
                     onchange="document.forms[ 0 ].theEvent.value='onChangeCurrencyCd'; checkEvent()"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.currencyListIsDisabled}"
                />
    </td>
    <td colspan="2" class="tdFormError">
        <form:errors path="cepr01030102Form.currencyCd"/>
    </td>
</tr>
<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.clazzListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.class"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.clazzCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.clazzList}" itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.clazzListIsDisabled}"
                />
    </td>
    <td colspan="2" class="tdFormError">
        <form:errors path="cepr01030102Form.clazzCd"/>
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.premiumDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.premium"/>
        :
    </td>
    <td colspan="2">
        <form:input path="cepr01030102Form.premium" cssClass="fontForm" disabled="${cepr01030000HoldingForm.cepr01030102Form.premiumIsDisabled}" onfocus="showForm( 'premiumHelper', 'true' ); showForm( 'premiumListDisplayHelper', 'true' );" onblur="showForm( 'premiumHelper', 'false' );" onchange="this.value=formatCurrency( this.value ); document.forms[ 0 ].theEvent.value='onPressButtonCountTotalSumInsured'; checkEvent();" onkeyup="showFormatCurrency('premiumHelper', this.value);" readonly="${cepr01030000HoldingForm.cepr01030102Form.premiumReadOnly}"/>
        <span id="premiumListDisplayHelper" style="display: none;">
        <span id="cepr01030102Form.premiumListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.premiumListDisplay}">
            <form:select path="cepr01030102Form.premiumCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.premiumList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.premiumListIsDisabled}"
                     onchange="document.getElementById( 'cepr01030102Form.premium' ).value = document.getElementById( 'cepr01030102Form.premiumCd' ).value;
                               showFormatCurrency('premiumHelper', this.value);
                               document.getElementById( 'cepr01030102Form.premiumListDisplay' ).focus();
                               document.forms[ 0 ].theEvent.value='onPressButtonCountTotalSumInsured'; checkEvent();
                               "
                     onblur="showForm( 'premiumListDisplayHelper', 'false' );"
                />
        </span>
        </span>
		</td>
		<td style="display: ${cepr01030000HoldingForm.cepr01030102Form.buttonCountPremiumDisplay}">
        <input type="text" id="premiumHelper" disabled="disabled" style="display: none; font-family:Arial; font-size:11px" tabindex="-1"/>
        
			<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonCountPremium'; checkEvent()" onmouseover="changeButtonCountPremiumToGlow();" onmouseout="changeButtonCountPremiumToNormal();">
			<img src="${path}/include/images/bt_hitung.gif"   alt="" style="border: 0;" id="buttonCountPremiumId"> 
			</a> 
       
    </td>
    <td  class="tdFormError">
        <form:errors path="cepr01030102Form.premium"/>
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.premiumCombinationListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.premiumCombination"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.premiumCombinationCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.premiumCombinationList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.premiumCombinationListIsDisabled}" onchange="document.forms[ 0 ].theEvent.value='onPressButtonCountTotalSumInsured'; checkEvent();"
                />
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.premiumCombinationCd"/>
    </td>
</tr> 

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.basePremiumDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.basePremium"/>
        :
    </td>
    <td colspan="2">
        <form:input path="cepr01030102Form.basePremium" readonly="${cepr01030000HoldingForm.cepr01030102Form.basePremiumReadOnly}" onchange="this.value=formatCurrency( this.value ); document.forms[ 0 ].theEvent.value='onPressChangeBasePremium'; checkEvent();"  onblur="document.forms[ 0 ].theEvent.value='onPressChangeBasePremium'; checkEvent();" cssStyle="border-color:white" cssClass="fontForm"/>
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.basePremium"/>
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.topUpPremiumDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.topUpPremium"/>
        :
    </td>
    <td colspan="2">
        <form:input path="cepr01030102Form.topUpPremium" readonly="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.topUpPremiumIsDisabled}" cssStyle="border-color:white" cssClass="fontForm" />
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.topUpPremium"/>
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.buttonRiderAndButtonTopupDisplay}">
    <td class="tdFormLabel">&nbsp;
        
    </td>
    <td width="136">
        <span style="display: ${cepr01030000HoldingForm.cepr01030102Form.buttonRiderDisplay}">
			<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonRider'; checkEvent()" onmouseover="changeButtonRiderToGlow();" onmouseout="changeButtonRiderToNormal();">
			<img src="${path}/include/images/bt_rider.gif"   alt="" style="border: 0;" id="buttonRiderId"> 
			</a> 
        </span>
        
    </td>
    <td width="136">
		<span style="display: ${cepr01030000HoldingForm.cepr01030102Form.buttonTopupDisplay}">
			<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonTopup'; checkEvent()" onmouseover="changeButtonTopupToGlow();" onmouseout="changeButtonTopupToNormal();">
			<img src="${path}/include/images/bt_topuppenarikan.gif"   alt="" style="border: 0;" id="buttonTopupId" />
			</a>
		</span>
	</td>
   
    <td class="tdFormError">&nbsp;
        
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.totalSumInsuredDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.totalSumInsured"/>
        :
    </td>
    <td colspan="2">
        <form:input path="cepr01030102Form.totalSumInsured" cssClass="fontForm" disabled="${cepr01030000HoldingForm.cepr01030102Form.totalSumInsuredIsDisabled}" onfocus="showForm( 'totalSumInsuredHelper', 'true' ); showForm( 'totalSumInsuredListDisplayHelper', 'true' );" onblur="showForm( 'totalSumInsuredHelper', 'false' );" onchange="this.value=formatCurrency( this.value ); document.forms[ 0 ].theEvent.value='onChangeTotalSumInsured'; checkEvent()" onkeyup="showFormatCurrency('totalSumInsuredHelper', this.value);" readonly="${cepr01030000HoldingForm.cepr01030102Form.totalSumInsuredReadOnly}"/>
        <span id="totalSumInsuredListDisplayHelper" style="display: none;">
        <span id="cepr01030102Form.totalSumInsuredListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.totalSumInsuredListDisplay}">
            <form:select path="cepr01030102Form.totalSumInsuredCd"
                     items="${cepr01030000HoldingForm.cepr01030102Form.totalSumInsuredList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.totalSumInsuredListIsDisabled}"
                     onchange="document.getElementById( 'cepr01030102Form.totalSumInsured' ).value = document.getElementById( 'cepr01030102Form.totalSumInsuredCd' ).value;
                               showFormatCurrency('totalSumInsuredHelper', this.value);
                               document.getElementById( 'cepr01030102Form.totalSumInsuredListDisplay' ).focus();
                               "
                     onblur="showForm( 'totalSumInsuredListDisplayHelper', 'false' );"
                />
        </span>
        </span>
        <input type="text" id="totalSumInsuredHelper" disabled="disabled" style="display: none; font-family:Arial; font-size:11px" tabindex="-1"/>
		</td><td>
        <span style="display: ${cepr01030000HoldingForm.cepr01030102Form.buttonCountTotalSumInsuredDisplay}">
            <a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonCountTotalSumInsured'; checkEvent()" onmouseover="changeButtonCountTotalToGlow();" onmouseout="changeButtonCountTotalToNormal();">
			<img src="${path}/include/images/bt_hitung.gif"   alt="" style="border: 0;" id="buttonCountTotalId"> 
			</a> 
			
        </span>
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.totalSumInsured"/>
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.premiumFurloughYearDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.assumptionFurloughYearNo"/>
        :
    </td>
    <td colspan="2" style="font-size: 11px; font-family:Arial;">

        <c:set var="premiumFurloughYearBoxDisplay" value=""/>
        <c:if test="${'' == cepr01030000HoldingForm.cepr01030102Form.premiumFurloughYearListDisplayHelperDisplay}">
            <c:set var="premiumFurloughYearBoxDisplay" value="none"/>
        </c:if>

        <form:input path="cepr01030102Form.premiumFurloughYear" cssClass="fontForm" cssStyle="width: 20px; display: ${premiumFurloughYearBoxDisplay}" disabled="${cepr01030000HoldingForm.cepr01030102Form.premiumFurloughYearIsDisabled}" onfocus="showForm( 'premiumFurloughYearHelper', 'true' ); showForm( 'premiumFurloughYearListDisplayHelper', 'true' );" onblur="showForm( 'premiumFurloughYearHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatInteger('premiumFurloughYearHelper', this.value);"/>
        <span id="premiumFurloughYearListDisplayHelper" style="display: ${cepr01030000HoldingForm.cepr01030102Form.premiumFurloughYearListDisplayHelperDisplay};">
        <span id="cepr01030102Form.premiumFurloughYearListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.premiumFurloughYearListDisplay}">
            <form:select path="cepr01030102Form.premiumFurloughYearCd"
                     items="${cepr01030000HoldingForm.cepr01030102Form.premiumFurloughYearList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.premiumFurloughYearListIsDisabled}"
                     onchange="document.getElementById( 'cepr01030102Form.premiumFurloughYear' ).value = document.getElementById( 'cepr01030102Form.premiumFurloughYearCd' ).value;
                               showFormatInteger('premiumFurloughYearHelper', this.value);
                               document.getElementById( 'cepr01030102Form.premiumFurloughYearListDisplay' ).focus();"
                     onblur="document.getElementById( 'premiumFurloughYearListDisplayHelper' ).style.display = '${cepr01030000HoldingForm.cepr01030102Form.premiumFurloughYearListDisplayHelperDisplay}';"
                    cssStyle="font-family:Arial; font-size:11px;"
                />
        </span>
        </span>
        <input type="text" id="premiumFurloughYearHelper" disabled="disabled" style="display: none; width: 20px; font-family:Arial; font-size:11px" tabindex="-1"/>
    	 tahun
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.premiumFurloughYear"/>
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.paymentModeListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.paymentMode"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.paymentModeCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.paymentModeList}"
                     itemLabel="label" itemValue="value"
                     onchange="document.forms[ 0 ].theEvent.value='onChangePaymentModeCd'; checkEvent()"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.paymentModeListIsDisabled}"
                />
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.paymentModeCd"/>
    </td>
    <td style="display: ${cepr01030000HoldingForm.cepr01030102Form.pesertaEkaSehatStandAloneDisplay}">
       <a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonParticipantEkaSehat'; checkEvent()" >
		<img src="${path}/include/images/bt_peserta.gif"  alt="" style="border: 0;" id="buttonParticipantEkaSehatId"> 
		</a>
    </td>
    <td style="display: ${cepr01030000HoldingForm.cepr01030102Form.pesertaMedicalPlusStandAloneDisplay}">
       <a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonParticipantMedicalPlus'; checkEvent()" >
		<img src="${path}/include/images/bt_peserta.gif"  alt="" style="border: 0;" id="buttonParticipantMedicalPlusId"> 
		</a>
    </td>    
    <td style="display: ${cepr01030000HoldingForm.cepr01030102Form.pesertaHcpProviderDisplayStandAloneDisplay}">
       <a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonParticipantHcpProvider'; checkEvent()" >
		<img src="${path}/include/images/bt_peserta.gif"  alt="" style="border: 0;" id="buttonParticipantHcpProviderId"> 
		</a>
    </td> 
</tr>
<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.termOfContractDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.termOfContract"/>
        :
    </td>
    <td colspan="2" style="font-size: 11px; font-family:Arial;">
        <form:input path="cepr01030102Form.termOfContract" cssClass="fontForm" cssStyle="width: 17px;" maxlength="2" disabled="${cepr01030000HoldingForm.cepr01030102Form.termOfContractIsDisabled}"/>
        tahun
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.termOfContract"/>
    </td>
</tr>
<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.termOfPaymentDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.termOfPayment"/>
        :
    </td>
    <td colspan="2" style="font-size: 11px; font-family:Arial;">
        <form:input path="cepr01030102Form.termOfPayment" cssClass="fontForm" cssStyle="width: 17px;" maxlength="2" disabled="${cepr01030000HoldingForm.cepr01030102Form.termOfPaymentIsDisabled}"/>
        tahun
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.termOfPayment"/>
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.specialOfferListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.specialOffer"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.specialOfferCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.specialOfferList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.specialOfferListIsDisabled}"
                />
    </td>
      <td class="tdFormError">
        <form:errors path="cepr01030102Form.specialOfferCd"/>
    </td>
</tr>
<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.investRateInPercentDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.investment"/>
        :
    </td>
    <td colspan="2" style="font-size: 11px; font-family:Arial;">
        <form:input path="cepr01030102Form.investRateInPercent" cssClass="tdFormPercent" disabled="${cepr01030000HoldingForm.cepr01030102Form.investRateInPercentIsDisabled}" onfocus="showForm( 'investRateInPercentHelper', 'true' );" onblur="showForm( 'investRateInPercentHelper', 'false' );" onchange="this.value=formatCurrency( this.value ); showForm( 'investRateInPercentHelper', 'false' );" onkeyup="showFormatCurrency('investRateInPercentHelper', this.value);" />
        %
        <input type="text" id="investRateInPercentHelper" disabled="disabled" style="display: none; font-family:Arial; font-size:11px" class="tdFormPercent"/>
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.investRateInPercent"/>
    </td>
</tr>

<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.mgiListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.mgi"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.mgiCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.mgiList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.mgiListIsDisabled}"
                />
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.mgiCd"/>
    </td>
</tr>
<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.mtiListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.mti"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.mtiCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.mtiList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.mtiListIsDisabled}"
                />
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.mtiCd"/>
    </td>
</tr>
<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.jenisListDisplay}">
    <td class="tdFormLabel">
     <c:choose>
      <c:when test="${(cepr01030000HoldingForm.cepr01030102Form.leftPartOfBusinessCd == '190' || cepr01030000HoldingForm.cepr01030102Form.leftPartOfBusinessCd == '200') && ( cepr01030000HoldingForm.cepr01030102Form.rightPartOfBusinessCd == '2' || cepr01030000HoldingForm.cepr01030102Form.rightPartOfBusinessCd == '9')}">
      Paket Ditawarkan
        :
      </c:when>   
      <c:otherwise>
     <fmt:message key="words.jenis"/>
        :
      </c:otherwise>
     </c:choose> 
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.jenisCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.jenisList}"
                     itemLabel="label" itemValue="value"
                      onchange="document.forms[ 0 ].theEvent.value='onChangeJenisCd'; checkEvent()"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.jenisListIsDisabled}"
                />   
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.jenisCd"/>
    </td>
</tr>
<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.targetListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.target"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.targetCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.targetList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.targetListIsDisabled}"
                />
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.targetCd"/>
    </td>
</tr>
<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.taxBeforeAfterListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.tax"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.taxBeforeAfterCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.taxBeforeAfterList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.taxBeforeAfterListIsDisabled}"
                />
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.taxBeforeAfterCd"/>
    </td>
</tr>

<!--TODO-->
<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.packetListDisplay}">
    <td class="tdFormLabel">
        <fmt:message key="words.packet"/>
        :
    </td>
    <td colspan="2">
        <form:select path="cepr01030102Form.packetCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.packetList}"
                     itemLabel="label" itemValue="value"
                />
    </td>
    <td class="tdFormError">
        <form:errors path="cepr01030102Form.mgiCd"/>
    </td>
</tr>


<tr>
    <td colspan="4">
        <form:hidden path="cepr01030102Form.bisnisNoPbVersion"/>
        <form:hidden path="cepr01030102Form.downloadFlag"/>
        <form:hidden path="cepr01030102Form.prevAssurancePlanCd"/>
        <form:hidden path="cepr01030102Form.totalSumInsuredOptionIsToRefreshPage"/>
        <form:hidden path="cepr01030102Form.premiumOptionIsToRefreshPage"/>
    </td>
</tr>



</table>
	  
	  </TD>
	</tr>
	
	<tr >
    <td colspan="9" class="fontLabel1" style="display: ${cepr01030000HoldingForm.cepr01030102Form.headerInvestChoiceDisplay}">
        <br/>
        <b>
            <fmt:message key="words.investChoice"/>
        </b>
        
    </td>
</tr>
	<tr>
	  <TD colspan="9" style="display: ${cepr01030000HoldingForm.cepr01030102Form.headerInvestChoiceDisplay}; background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; "><p>&nbsp;</p>
	    <table>
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.fixIdrDisplay}">
        
      		<td class="tdFormLabel" width="150"><fmt:message key="words.fixIncome"/> : </td> 
        
      <td colspan="3" style="font-size: 11px; font-family:Arial;">
      
      <span id="cepr01030102Form.ljiFixListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.ljiFixListDisplay}">
      <form:select path="cepr01030102Form.ljiFixCd" cssClass="fontForm" cssStyle="width: 160px;"
                     items="${cepr01030000HoldingForm.cepr01030102Form.ljiFixList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.ljiFixListIsDisabled}"
                    /></span>      
      <form:input path="cepr01030102Form.fixIdr" cssClass="fontForm" cssStyle="width: 35px;" disabled="${cepr01030000HoldingForm.cepr01030102Form.fixIdrIsDisabled}" onfocus="showForm( 'fixIdrHelper', 'true' ); showForm( 'fixIdrListDisplayHelper', 'true' );" onblur="showForm( 'fixIdrHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('fixIdrHelper', this.value);"/>% <span id="fixIdrListDisplayHelper" style="display: none;"> <span id="cepr01030102Form.fixIdrListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.fixIdrListDisplay}"> <form:select path="cepr01030102Form.fixIdrCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.fixIdrList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.fixIdrListIsDisabled}"
                     onchange="document.getElementById( 'cepr01030102Form.fixIdr' ).value = document.getElementById( 'cepr01030102Form.fixIdrCd' ).value;
                               showFormatCurrency('fixIdrHelper', this.value);
                               document.getElementById( 'cepr01030102Form.fixIdrListDisplay' ).focus();"
                     onblur="showForm( 'fixIdrListDisplayHelper', 'false' );"
                /> </span> </span>
          <input type="text" id="fixIdrHelper" disabled="disabled" style="display: none; width: 35px; font-family:Arial; font-size:11px" tabindex="-1"/>
&nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.fixIdr" cssClass="tdFormError"/> </td>
	  </tr>
	  
	  <c:choose>
     <c:when test="${cepr01030000HoldingForm.cepr01030102Form.leftPartOfBusinessCd == '134' && cepr01030000HoldingForm.cepr01030102Form.rightPartOfBusinessCd == '5'}">
     	  <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.fixIdrDisplay}">
	      	<td class="tdFormLabel" width="150"> Simas Fix Income : </td>
	      	   <td colspan="3" style="font-size: 11px; font-family:Arial;">   
	  		 <form:select path="cepr01030102Form.ljiFixSimasCd" cssClass="fontForm" cssStyle="width: 160px;"
                     items="${cepr01030000HoldingForm.cepr01030102Form.ljiFixSimasList}"
                     itemLabel="label"
                     itemValue="value"                    
                    />	  
	   <form:input path="cepr01030102Form.fixSimasIdr" cssClass="fontForm" cssStyle="width: 35px;"  onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('fixIdrHelper', this.value);" onfocus="showForm( 'fixSimasIdrListDisplayHelper', 'true' );"/>% 
	    <span id="fixSimasIdrListDisplayHelper" style="display: none;">
	    <form:select path="cepr01030102Form.fixSimasIdrCd" cssClass="fontForm" items="${cepr01030000HoldingForm.cepr01030102Form.fixIdrList}"
                     itemLabel="label"
                     itemValue="value"                     
                     onchange="document.getElementById( 'cepr01030102Form.fixSimasIdr' ).value = document.getElementById( 'cepr01030102Form.fixSimasIdrCd' ).value;" onblur="showForm( 'fixSimasIdrListDisplayHelper', 'false' );"/> </span>
         <form:errors path="cepr01030102Form.fixSimasIdr" cssClass="tdFormError"/>
         </td>
	  	</tr>
	  </c:when>
	 </c:choose> 	   
	   
	<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.dynamicIdrDisplay}">
	
    	 <td class="tdFormLabel"><fmt:message key="words.dynamic"/> : </td>   
     
    <td colspan="3" style="font-size: 11px; font-family:Arial;">
    
     <span id="cepr01030102Form.ljiDynamicListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.ljiDynamicListDisplay}">
      <form:select path="cepr01030102Form.ljiDynamicCd" cssClass="fontForm" cssStyle="width: 160px;"
                     items="${cepr01030000HoldingForm.cepr01030102Form.ljiDynamicList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.ljiDynamicListIsDisabled}"
                    /></span> 
    <form:input path="cepr01030102Form.dynamicIdr" cssClass="fontForm" cssStyle="width: 35px;" disabled="${cepr01030000HoldingForm.cepr01030102Form.dynamicIdrIsDisabled}" onfocus="showForm( 'dynamicIdrHelper', 'true' ); showForm( 'dynamicIdrListDisplayHelper', 'true' );" onblur="showForm( 'dynamicIdrHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('dynamicIdrHelper', this.value);"/>% <span id="dynamicIdrListDisplayHelper" style="display: none;"> <span id="cepr01030102Form.dynamicIdrListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.dynamicIdrListDisplay}"> <form:select path="cepr01030102Form.dynamicIdrCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.dynamicIdrList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.dynamicIdrListIsDisabled}"
                     onchange="document.getElementById( 'cepr01030102Form.dynamicIdr' ).value = document.getElementById( 'cepr01030102Form.dynamicIdrCd' ).value;
                               showFormatCurrency('dynamicIdrHelper', this.value);
                               document.getElementById( 'cepr01030102Form.dynamicIdrListDisplay' ).focus();"
                     onblur="showForm( 'dynamicIdrListDisplayHelper', 'false' );"
                /> </span> </span>
        <input type="text" id="dynamicIdrHelper" disabled="disabled" style="display: none; width: 35px; font-family:Arial; font-size:11px" tabindex="-1"/>
&nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.dynamicIdr" cssClass="tdFormError" /> </td>
  </tr>
  
    <c:choose>
     	<c:when test="${cepr01030000HoldingForm.cepr01030102Form.leftPartOfBusinessCd == '134' && cepr01030000HoldingForm.cepr01030102Form.rightPartOfBusinessCd == '5'}">
     	<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.dynamicIdrDisplay}">
	      	<td class="tdFormLabel" width="150"> Simas Dynamic : </td>
	      	   <td colspan="3" style="font-size: 11px; font-family:Arial;">   
	  		 <form:select path="cepr01030102Form.ljiDynamicSimasCd" cssClass="fontForm" cssStyle="width: 160px;"
                     items="${cepr01030000HoldingForm.cepr01030102Form.ljiDynamicSimasList}"
                     itemLabel="label"
                     itemValue="value"                    
                    />	  
	   <form:input path="cepr01030102Form.dynamicSimasIdr" cssClass="fontForm" cssStyle="width: 35px;"  onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('fixIdrHelper', this.value);" onfocus="showForm( 'dynamicSimasIdrListDisplayHelper', 'true' );"/>% 
	    <span id="dynamicSimasIdrListDisplayHelper" style="display: none;">
	    <form:select path="cepr01030102Form.dynamicSimasIdrCd" cssClass="fontForm" items="${cepr01030000HoldingForm.cepr01030102Form.dynamicIdrList}"
                     itemLabel="label"
                     itemValue="value"                     
                     onchange="document.getElementById( 'cepr01030102Form.dynamicSimasIdr' ).value = document.getElementById( 'cepr01030102Form.dynamicSimasIdrCd' ).value;" onblur="showForm( 'dynamicSimasIdrListDisplayHelper', 'false' );"/> </span>
         <form:errors path="cepr01030102Form.dynamicSimasIdr" cssClass="tdFormError"/>
         </td>
	  	</tr>
	  </c:when>
	</c:choose> 	   
  
  <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.aggresiveIdrDisplay}">
 	
     		<td class="tdFormLabel"><fmt:message key="words.aggresive"/> : </td>  
  
    <td colspan="3" style="font-size: 11px; font-family:Arial;">
    
    <span id="cepr01030102Form.ljiAggresiveListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.ljiAggresiveListDisplay}">
      <form:select path="cepr01030102Form.ljiAggresiveCd" cssClass="fontForm" cssStyle="width: 160px;"
                     items="${cepr01030000HoldingForm.cepr01030102Form.ljiAggresiveList}"
                     itemLabel="label" 
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.ljiAggresiveListIsDisabled}"
                    /></span> 
    <form:input path="cepr01030102Form.aggresiveIdr" cssClass="fontForm" cssStyle="width: 35px;" disabled="${cepr01030000HoldingForm.cepr01030102Form.aggresiveIdrIsDisabled}" onfocus="showForm( 'aggresiveIdrHelper', 'true' ); showForm( 'aggresiveIdrListDisplayHelper', 'true' );" onblur="showForm( 'aggresiveIdrHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('aggresiveIdrHelper', this.value);"/>% <span id="aggresiveIdrListDisplayHelper" style="display: none;"> <span id="cepr01030102Form.aggresiveIdrListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.aggresiveIdrListDisplay}"> <form:select path="cepr01030102Form.aggresiveIdrCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.aggresiveIdrList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.aggresiveIdrListIsDisabled}"
                     onchange="document.getElementById( 'cepr01030102Form.aggresiveIdr' ).value = document.getElementById( 'cepr01030102Form.aggresiveIdrCd' ).value;
                               showFormatCurrency('aggresiveIdrHelper', this.value);
                               document.getElementById( 'cepr01030102Form.aggresiveIdrListDisplay' ).focus();"
                     onblur="showForm( 'aggresiveIdrListDisplayHelper', 'false' );"
                /> </span> </span>
        <input type="text" id="aggresiveIdrHelper" disabled="disabled" style="display: none; width: 35px; font-family:Arial; font-size:11px" tabindex="-1"/>
&nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.aggresiveIdr" cssClass="tdFormError"/> </td>
  </tr>
  
   <c:choose>
     	<c:when test="${cepr01030000HoldingForm.cepr01030102Form.leftPartOfBusinessCd == '134' && cepr01030000HoldingForm.cepr01030102Form.rightPartOfBusinessCd == '5'}">
     	<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.aggresiveIdrDisplay}">
	      	<td class="tdFormLabel" width="150"> Simas Aggresive : </td>
	      	   <td colspan="3" style="font-size: 11px; font-family:Arial;">   
	  		 <form:select path="cepr01030102Form.ljiAggresiveSimasCd" cssClass="fontForm" cssStyle="width: 160px;"
                     items="${cepr01030000HoldingForm.cepr01030102Form.ljiAggresiveSimasList}"
                     itemLabel="label"
                     itemValue="value"                    
                    />	  
	   <form:input path="cepr01030102Form.aggresiveSimasIdr" cssClass="fontForm" cssStyle="width: 35px;"  onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('fixIdrHelper', this.value);" onfocus="showForm( 'aggresiveSimasIdrListDisplayHelper', 'true' );"/>% 
	    <span id="aggresiveSimasIdrListDisplayHelper" style="display: none;">
	    <form:select path="cepr01030102Form.aggresiveSimasIdrCd" cssClass="fontForm" items="${cepr01030000HoldingForm.cepr01030102Form.aggresiveIdrList}"
                     itemLabel="label"
                     itemValue="value"                     
                     onchange="document.getElementById( 'cepr01030102Form.aggresiveSimasIdr' ).value = document.getElementById( 'cepr01030102Form.aggresiveSimasIdrCd' ).value;" onblur="showForm( 'aggresiveSimasIdrListDisplayHelper', 'false' );"/> </span>
         <form:errors path="cepr01030102Form.aggresiveSimasIdr" cssClass="tdFormError"/>         
         </td>
	  	</tr>
	  </c:when>
	</c:choose>
	
	<tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.cashFundIdrDisplay}">
 	
     		<td class="tdFormLabel"><fmt:message key="words.cashFund"/> : </td>  
  
    <td colspan="3" style="font-size: 11px; font-family:Arial;">
    
    <span id="cepr01030102Form.ljiCashFundDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.ljiCashFundDisplay}">
      <form:select path="cepr01030102Form.ljiCashFundCd" cssClass="fontForm" cssStyle="width: 160px;"
                     items="${cepr01030000HoldingForm.cepr01030102Form.ljiCashFundList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.ljiCashFundListIsDisabled}"
                    /></span> 
    <form:input path="cepr01030102Form.cashFundIdr" cssClass="fontForm" cssStyle="width: 35px;" disabled="${cepr01030000HoldingForm.cepr01030102Form.cashFundIdrIsDisabled}" onfocus="showForm( 'cashFundIdrHelper', 'true' ); showForm( 'cashFundIdrListDisplayHelper', 'true' );" onblur="showForm( 'cashFundIdrHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('cashFundIdrHelper', this.value);"/>% <span id="cashFundIdrListDisplayHelper" style="display: none;"> <span id="cepr01030102Form.cashFundIdrListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.cashFundIdrListDisplay}"> <form:select path="cepr01030102Form.cashFundIdrCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.cashFundIdrList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.cashFundIdrListIsDisabled}"
                     onchange="document.getElementById( 'cepr01030102Form.cashFundIdr' ).value = document.getElementById( 'cepr01030102Form.cashFundIdrCd' ).value;
                               showFormatCurrency('cashFundHelper', this.value);
                               document.getElementById( 'cepr01030102Form.cashFundIdrListDisplay' ).focus();"
                     onblur="showForm( 'cashFundIdrListDisplayHelper', 'false' );"
                /> </span> </span>
        <input type="text" id="cashFundIdrHelper" disabled="disabled" style="display: none; width: 35px; font-family:Arial; font-size:11px" tabindex="-1"/>
&nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.cashFundIdr" cssClass="tdFormError"/> </td>
  </tr>
	  
  <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.secureUsdDisplay}"> 
    
      <td class="tdFormLabel"><fmt:message key="words.secure"/>$ : </td>  
        
    <td colspan="3" style="font-size: 11px; font-family:Arial;">
     <span id="cepr01030102Form.ljiSecureUsdListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.ljiSecureUsdListDisplay}">
      <form:select path="cepr01030102Form.ljiSecureUsdCd" cssClass="fontForm" cssStyle="width: 160px;"
                     items="${cepr01030000HoldingForm.cepr01030102Form.ljiSecureUsdList}"
                     itemLabel="label"
                     itemValue="value"  
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.ljiSecureUsdListIsDisabled}"                  
                    /></span>      
    <form:input path="cepr01030102Form.secureUsd" cssClass="fontForm" cssStyle="width: 35px;" disabled="${cepr01030000HoldingForm.cepr01030102Form.secureUsdIsDisabled}" onfocus="showForm( 'secureUsdHelper', 'true' ); showForm( 'secureUsdListDisplayHelper', 'true' );" onblur="showForm( 'secureUsdHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('secureUsdHelper', this.value);"/>% <span id="secureUsdListDisplayHelper" style="display: none;"> <span id="cepr01030102Form.secureUsdListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.secureUsdListDisplay}"> <form:select path="cepr01030102Form.secureUsdCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.secureUsdList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.secureUsdListIsDisabled}"
                     onchange="document.getElementById( 'cepr01030102Form.secureUsd' ).value = document.getElementById( 'cepr01030102Form.secureUsdCd' ).value;
                               showFormatCurrency('secureUsdHelper', this.value);
                               document.getElementById( 'cepr01030102Form.secureUsdListDisplay' ).focus();"
                     onblur="showForm( 'secureUsdListDisplayHelper', 'false' );"
                /> </span> </span>
        <input type="text" id="secureUsdHelper" disabled="disabled" style="display: none; width: 35px; font-family:Arial; font-size:11px" tabindex="-1"/>
&nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.secureUsd" cssClass="tdFormError"/> </td>
  </tr>
  <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.dynamicUsdDisplay}">
   
      <td class="tdFormLabel"><fmt:message key="words.dynamic"/>$ : </td>  
      
    <td colspan="3" style="font-size: 11px; font-family:Arial;">    
    <span id="cepr01030102Form.ljiDynamicUsdListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.ljiDynamicUsdListDisplay}">
    <form:select path="cepr01030102Form.ljiDynamicUsdCd" cssClass="fontForm" cssStyle="width: 160px;"
                     items="${cepr01030000HoldingForm.cepr01030102Form.ljiDynamicUsdList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.ljiDynamicUsdListIsDisabled}"                    
                    /></span>
    <form:input path="cepr01030102Form.dynamicUsd" cssClass="fontForm" cssStyle="width: 35px;" disabled="${cepr01030000HoldingForm.cepr01030102Form.dynamicUsdIsDisabled}" onfocus="showForm( 'dynamicUsdHelper', 'true' ); showForm( 'dynamicUsdListDisplayHelper', 'true' );" onblur="showForm( 'dynamicUsdHelper', 'false' );" onchange="this.value=formatCurrency( this.value );" onkeyup="showFormatCurrency('dynamicUsdHelper', this.value);"/>% <span id="dynamicUsdListDisplayHelper" style="display: none;"> <span id="cepr01030102Form.dynamicUsdListDisplay" style="display: ${cepr01030000HoldingForm.cepr01030102Form.dynamicUsdListDisplay}"> <form:select path="cepr01030102Form.dynamicUsdCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.dynamicUsdList}"
                     itemLabel="label"
                     itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.dynamicUsdListIsDisabled}"
                     onchange="document.getElementById( 'cepr01030102Form.dynamicUsd' ).value = document.getElementById( 'cepr01030102Form.dynamicUsdCd' ).value;
                               showFormatCurrency('dynamicUsdHelper', this.value);
                               document.getElementById( 'cepr01030102Form.dynamicUsdListDisplay' ).focus();"
                     onblur="showForm( 'dynamicUsdListDisplayHelper', 'false' );"
                /> </span> </span>
        <input type="text" id="dynamicUsdHelper" disabled="disabled" style="display: none; width: 35px; font-family:Arial; font-size:11px" tabindex="-1"/>
&nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.dynamicUsd" cssClass="tdFormError"/> </td>
  </tr>
      </table>
      <p>&nbsp;</p></TD>
  </tr>
	<tr >
    <td colspan="9" class="fontLabel1" style="display: ${cepr01030000HoldingForm.cepr01030102Form.headerAdditionalAssuranceDisplay}">
        <br/>
        <b>
            <fmt:message key="words.additionalAssurance"/>
        </b>
    </td>
</tr>
	<tr>
	  <TD colspan="9" style="display: ${cepr01030000HoldingForm.cepr01030102Form.headerAdditionalAssuranceDisplay}; background-image:url(${path}/include/images/bg_form3.jpg); background-repeat:repeat-x;  "><p>&nbsp;</p>
	    <table>
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.termRiderFlagDisplay}">
          <td class="tdFormLabel" width="150"><fmt:message key="words.termRider"/> : </td>
          <td colspan="2" style="font-size: 11px; font-family:Arial;"><form:checkbox path="cepr01030102Form.termRiderFlag" cssClass="fontForm" value="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.termRiderFlagIsDisabled}"/> <form:select path="cepr01030102Form.termRiderUnitQtyCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.termRiderUnitQtyList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.termRiderUnitQtyListIsDisabled}"
                /> Unit </td>
          <td><form:errors path="cepr01030102Form.termRiderFlag" cssClass="comErrorColor"/> <form:errors path="cepr01030102Form.termRiderUnitQtyCd" cssClass="comErrorColor"/> </td>
        </tr>
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.personalAccidentFlagDisplay}">
          <td class="tdFormLabel" width="150"><fmt:message key="words.personalAccident"/> : </td>
          <td colspan="3" style="font-size: 11px; font-family:Arial;"><form:checkbox path="cepr01030102Form.personalAccidentFlag" cssClass="fontForm" value="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.personalAccidentFlagIsDisabled}"/> <form:select path="cepr01030102Form.personalAccidentUnitQtyCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.personalAccidentUnitQtyList}"
                     itemLabel="label" itemValue="value"
                     disabled="${cepr01030000HoldingForm.cepr01030102Form.personalAccidentUnitQtyListIsDisabled}"
                /> Unit &nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.personalAccidentFlag" cssClass="comErrorColor"/> <form:errors path="cepr01030102Form.personalAccidentUnitQtyCd" cssClass="comErrorColor"/> </td>
        </tr>
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.criticalIllnessFlagDisplay}">
          <td class="tdFormLabel"><fmt:message key="words.criticalIllness"/> : </td>
          <td colspan="3"><form:checkbox path="cepr01030102Form.criticalIllnessFlag" cssClass="fontForm" value="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.criticalIllnessFlagIsDisabled}"/> &nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.criticalIllnessFlag" cssClass="tdFormError"/> </td>
        </tr>
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.payorsClauseFlagDisplay}">
          <td class="tdFormLabel"><fmt:message key="words.payorsClause"/> : </td>
          <td colspan="3"><form:checkbox path="cepr01030102Form.payorsClauseFlag" cssClass="fontForm" value="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.payorsClauseFlagIsDisabled}"/> &nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.payorsClauseFlag" cssClass="tdFormError"/> </td>
        </tr>
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.waiverPremiumDisabilityFlagDisplay}">
          <td class="tdFormLabel"><fmt:message key="words.waiverPremiumDisability"/> : </td>
          <td colspan="3"><form:checkbox path="cepr01030102Form.waiverPremiumDisabilityFlag" cssClass="fontForm" value="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.waiverPremiumDisabilityFlagIsDisabled}"/> &nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.waiverPremiumDisabilityFlag" cssClass="tdFormError"/> </td>
        </tr>
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.babyDisplay}">
          <td class="tdFormLabel"><fmt:message key="words.baby"/> : </td>
          <td colspan="3"><form:checkbox path="cepr01030102Form.babyFlag" cssClass="fontForm" value="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.babyFlagIsDisabled}"/> &nbsp;&nbsp;&nbsp;&nbsp; 
          	<form:select path="cepr01030102Form.babyCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030102Form.babyList}"
                     itemLabel="label" itemValue="value"/>
            <td class="fontForm">   
		     Bulan Ke-
		 	<form:select path="cepr01030102Form.babyChooseCd" cssClass="fontForm"
		                     items="${cepr01030000HoldingForm.cepr01030102Form.babyChooseList}"
		                     itemLabel="label" itemValue="value"/>
		        </td>
          <form:errors path="cepr01030102Form.babyFlag" cssClass="tdFormError"/> </td>
        </tr>      
        
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.ekaSehatFlagDisplay}">
          <td class="tdFormLabel" width="150"><fmt:message key="words.ekaSehat"/> : </td>
          <td colspan="2" style="font-size: 11px; font-family:Arial;">
          <form:checkbox path="cepr01030102Form.ekaSehatFlag" cssClass="fontForm" id="cepr01030102Form.ekaSehatFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.ekaSehatFlagIsDisabled}" onclick="handleClickEkaSehatFlag(this.checked);"/>
                     <form:select path="cepr01030102Form.ekaSehatCd" cssClass="fontForm"
		                     items="${cepr01030000HoldingForm.cepr01030102Form.ekaSehatList}"
		                     itemLabel="label" itemValue="value"
		                     disabled="${cepr01030000HoldingForm.cepr01030102Form.ekaSehatListIsDisabled}"
		                /> </td>
		        <td>
				<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonParticipantEkaSehat'; checkEvent()" onmouseover="changeButtonParticipantEkaSehatToGlow();" onmouseout="changeButtonParticipantEkaSehatToNormal();">
				<img src="${path}/include/images/bt_peserta.gif"  alt="" style="border: 0;" id="buttonParticipantEkaSehatId"> 
				</a>&nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.ekaSehatFlag" cssClass="comErrorColor"/>  
				</td>
					 
        </tr>
        
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.tpdFlagDisplay}">
          <td class="tdFormLabel"><fmt:message key="words.tpd"/> : </td>
          <td colspan="3"><form:checkbox path="cepr01030102Form.tpdFlag" cssClass="fontForm" value="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.tpdFlagIsDisabled}"/> &nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.tpdFlag" cssClass="tdFormError"/> </td>
        </tr>
        
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.medicalPlusDisplay}">
          <td class="tdFormLabel"><fmt:message key="words.medicalPlusRg"/> : </td>
          <td colspan="3"><form:checkbox path="cepr01030102Form.medicalPlusRgFlag" cssClass="fontForm" value="true"/> &nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.medicalPlusRgFlag" cssClass="tdFormError"/> </td>
        </tr>
        
         <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.medicalPlusDisplay}">
          <td class="tdFormLabel"><fmt:message key="words.medicalPlusRb"/> : </td>
          <td colspan="3"><form:checkbox path="cepr01030102Form.medicalPlusRbFlag" cssClass="fontForm" value="true"/> &nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.medicalPlusRbFlag" cssClass="tdFormError"/> </td>
        </tr>
        
         <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.medicalPlusDisplay}">
          <td class="tdFormLabel"><fmt:message key="words.medicalPlusPk"/> : </td>
          <td colspan="3"><form:checkbox path="cepr01030102Form.medicalPlusPkFlag" cssClass="fontForm" value="true"/> &nbsp;&nbsp;&nbsp;&nbsp; <form:errors path="cepr01030102Form.medicalPlusPkFlag" cssClass="tdFormError"/> </td>
        </tr>
               
         <%--
        <tr style="display: ${cepr01030000HoldingForm.cepr01030102Form.ekaSehatFlagDisplay}">
		    <td class="fontForm">
		        <form:checkbox path="cepr01030102Form.ekaSehatFlag" cssClass="fontForm" id="cepr01030102Form.ekaSehatFlag" value="true" disabled="${cepr01030000HoldingForm.cepr01030102Form.ekaSehatFlagIsDisabled}" onclick="handleClickEkaSehatFlag(this.checked);"/>
		        <label for="cepr01030102Form.ekaSehatFlag">
		            <fmt:message key="words.ekaSehat"/>
		        </label>
		    </td>
		    <td style="text-align: left;">
		        <form:hidden path="cepr01030102Form.ekaSehatListIsDisabled" />
		        <form:hidden path="cepr01030102Form.buttonParticipantEkaSehatIsDisabled" />
		       
		    
		        <form:select path="cepr01030102Form.ekaSehatCd" cssClass="fontForm"
		                     items="${cepr01030000HoldingForm.cepr01030102Form.ekaSehatList}"
		                     itemLabel="label" itemValue="value"
		                     disabled="${cepr01030000HoldingForm.cepr01030102Form.ekaSehatListIsDisabled}"
		                />
		        <c:set var="buttonDisabledAddition" value=""/>
		        <c:if test="${cepr01030000HoldingForm.cepr01030102Form.buttonParticipantEkaSehatIsDisabled=='true'}">
		            <c:set var="buttonDisabledAddition" value="disabled"/>
		        </c:if>
		        
		        
		    </td>
		    <td style="text-align: left;"><div>
				<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonParticipantEkaSehat'; checkEvent()" onmouseover="changeButtonParticipantEkaSehatToGlow();" onmouseout="changeButtonParticipantEkaSehatToNormal();">
				<img src="${path}/include/images/bt_peserta.gif"  alt="" style="border: 0;" id="buttonParticipantEkaSehatId"> 
				</a>
					</div>
		        </td>
		    <td style="text-align: left;">
				<form:errors path="cepr01030102Form.ekaSehatFlag" cssClass="comErrorColor"/>
			
			&nbsp;
			</td>
		    <td>&nbsp;
		        
		    </td>
		</tr> --%>
      </table></TD>
  </tr>
  
  
	<tr>
		
        <input type="hidden" id="targetParam"/>
        <input type="hidden" name="theEvent" id="theEvent"/>
        
		
		<TD  style="display: ${cepr01030000HoldingForm.cepr01030102Form.buttonViewProposalDisplay}"><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonPreviewProposal'; checkEvent()" onmouseover="changeButtonPreviewProposalToGlow();" onmouseout="changeButtonPreviewProposalToNormal();">
		<img src="${path}/include/images/bt_lihatproposal.gif"   alt="" style="border: 0;" id="buttonPreviewProposalId"> 
		</a>
		</div></TD>
		
		<TD COLSPAN=2><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonBack'; checkEvent()" onmouseover="changeButtonBackToGlow();" onmouseout="changeButtonBackToNormal();">
		<img src="${path}/include/images/bt_kembali.gif"   alt="" style="border: 0;" id="buttonBackId">
		</a>
	    </div></TD> 

	    <TD COLSPAN=2><div align="center">
		<input type="button" name="sendFax" value="SEND BY FAX" onclick="document.forms[ 0 ].theEvent.value='onPressButtonFax'; checkEvent()" style="display: ${cepr01030000HoldingForm.cepr01030102Form.faxDisplay}"/>
	    <input type="button" name="sendEmail" value="SEND BY EMAIL" onclick="document.forms[ 0 ].theEvent.value='onPressButtonEmail'; checkEvent()"style="display: ${cepr01030000HoldingForm.cepr01030102Form.emailDisplay}"/>
	    </div></TD>
	    
		<TD><div align="center"></div>
		
		<TD COLSPAN=2><div align="center">
	    </div></TD>
		<TD><div align="center">
		
		</div></TD>
		<TD COLSPAN=4>&nbsp;			</TD>
    </tr>
    <tr>
  	<td colspan="9" style="font-family: arial;font-weight: bold;padding-top: 5px;">
  		<c:if test="${not empty no_proposal}">
  			&nbsp;&nbsp;&nbsp;NO PROPOSAL : ${no_proposal}
  		</c:if>
	</td>
    </tr>
	<tr>
	  <TD colspan="9" style="font-size: 11px; font-family:Arial;">
	  <spring:hasBindErrors name="cepr01030000HoldingForm">
            <b>
                <fmt:message key="error.formNotCompleted"/>
            </b>
        </spring:hasBindErrors>
	  <spring:hasBindErrors name="cepr01030102Form">
            <b>
                <fmt:message key="error.formNotCompleted"/>
            </b>
        </spring:hasBindErrors>
	</TD>
  </tr>
    <c:if test="${cepr01030000HoldingForm.credentialsVO.msagId != '999993'}">
	<tr>
		<TD height="19" style="background-image:url(${path}/include/images/web_proposal_38.jpg); ">&nbsp;			</TD>
		<TD COLSPAN=9 style="background-image:url(${path}/include/images/web_proposal_39.jpg); ">&nbsp;			</TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_40.jpg); ">&nbsp;			</TD>
    </tr>
    </c:if>
	<tr>
		<TD height="1" COLSPAN=13 >			<div align="center" class="fontBot">
		  <c:if test="${cepr01030000HoldingForm.cepr01030102Form.downloadFlag eq 'newPage'}">
  <script type="text/javascript">

  </script>
    <br>
		  </c:if>
		</div></TD>
	</tr>
	  <c:if test="${cepr01030000HoldingForm.credentialsVO.msagId != '999993'}">
	<tr>
		<TD height="100" COLSPAN=13 style="background-image:url(${path}/include/images/web_proposal_41.jpg); ">			<div align="center" class="fontBot">Copyright &copy; 2008 PT. Asuransi Jiwa Sinarmas. All Right Reserved.<br>
		  Head Office: Wisma Eka Jiwa Lantai 8  <br>
		Jl. Mangga Dua Raya, Jakarta 10730  <br>
		Telp: (021) 6257808 (Hunting), Fax: (021) 6257837  <br>
		Customer Service: (021) 6257838, 26508300  <br>
		Website: www.sinarmasmsiglife.com  
		</div></TD>
	</tr>
	</c:if>
	<tr>
		<TD height="1"><img src="${path}/include/images/spacer.gif" alt="" width="144" height="1" /></TD>
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
    </tr>
</TABLE>
</form:form>
<script type="text/javascript">
    var downloadFlag = document.getElementById('cepr01030102Form.downloadFlag').value;
    if( downloadFlag == 'newPage' )
    {
        document.getElementById('cepr01030102Form.downloadFlag').value = '';
        document.getElementById('formpost').target = '_blank';
        document.getElementById('targetParam').name = CEPR00000000DownloadDocumentJSP;
        document.getElementById('formpost').submit();
        document.getElementById('formpost').target = '_self';
    }
    if( downloadFlag == 'samePage' )
    {
        document.getElementById('cepr01030102Form.downloadFlag').value = '';
        document.getElementById('formpost').action='${downloadUrlSession}';
        document.getElementById('formpost').submit();
        document.getElementById('formpost').target = '_self';
    }
</script>
</BODY>


</HTML>