<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<HEAD>
<link href="${path}/include/css/general.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
.style2 {font-size: 20px}
.style3 {color: #FF9900; font-size: 12px;}
.style5 {color: #FFFFFF}
-->
</style>

 <script type="text/javascript">
        function changeButtonResetToGlow()
        {
            document.getElementById( "buttonResetId" ).src = '${path}/include/images/bt_reset-ro.gif';
        }
        function changeButtonResetToNormal()
        {
            document.getElementById( "buttonResetId" ).src = '${path}/include/images/bt_reset.gif';
        }
		function changeButtonTutupToGlow()
        {
            document.getElementById( "buttonTutupId" ).src = '${path}/include/images/bt_tutup-ro.gif';
        }
        function changeButtonTutupToNormal()
        {
            document.getElementById( "buttonTutupId" ).src = '${path}/include/images/bt_tutup.gif';
        }
		function changeButtonBerikutToGlow()
        {
            document.getElementById( "buttonBerikutId" ).src = '${path}/include/images/bt_berikut-ro.gif';
        }
        function changeButtonBerikutToNormal()
        {
            document.getElementById( "buttonBerikutId" ).src = '${path}/include/images/bt_berikut.gif';
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
            if( document.forms[ 0 ].theEvent.value == 'onPressButtonNext' )
            {
                document.getElementById('targetParam').name = CEPR01030102JSP;
                document.getElementById('formpost').submit();
            }
//            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonCreateLetter' )
//            {
//                document.getElementById('targetParam').name = CEPR01030301JSP;
//                document.getElementById('formpost').submit();
//            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonPreviewLetter' )
            {
                document.getElementById('targetParam').name = CEPR01030101JSP;
                document.getElementById('formpost').submit();
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonCreateCover' )
            {
                document.getElementById('targetParam').name = CEPR01030401JSP;
                document.getElementById('formpost').submit();
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonCreateCover' )
            {
                document.getElementById('targetParam').name = CEPR01030401JSP;
                document.getElementById('formpost').submit();
            }
			else if( document.forms[ 0 ].theEvent.value == 'onPressButtonLookUpProvider' )
            {
                document.getElementById('targetParam').name = CEPR01030501JSP;
                document.getElementById('formpost').submit();
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonDownloadMedical' )
            {
                document.getElementById('targetParam').name = CEPR01030101JSP;
                document.getElementById('formpost').submit();
            }
            else if( document.forms[ 0 ].theEvent.value == 'onPressButtonLookUpSwineFluProvider' )
            {
                document.getElementById('targetParam').name = CEPR01030701JSP;
                document.getElementById('formpost').submit();
            }
             else if( document.forms[ 0 ].theEvent.value == 'onPressButtonCreateDPLK' )
            {
                document.getElementById('targetParam').name = CEPR01030901JSP;
                document.getElementById('formpost').submit();
            }
        }
        
        function calculateAge(formSource,formTarget){
        // untuk format dd/mm/yyyy
        	var form = document.forms[ 0 ];
        	var date_of_birth;
        	var age = 0;
        	var pass = 1;
        	var fix_number = 6;// untuk bulan
        	var currentDate = new Date();
        	// inputan
        	date_of_birth = form[formSource].value;
        	// split nilai
        	var day = currentDate.getDate();
			var month = currentDate.getMonth()+1;
			var year = currentDate.getFullYear();
			var Dob=date_of_birth.split('/');// split dengan '/'
			var Dob_day=Dob[0];
			var Dob_month=Dob[1];
			var Dob_year =Dob[2];
			// logic
			var agey = year - Dob_year;//+
			var agem = month - Dob_month;//+-
			var aged = day - Dob_day;//+-
			if(aged < 0) // kasus : dob:16 nov ; now:15 dec (< 1 bulan)
			{
				agem = agem - 1;
			}else if(aged > 0){ // kasus : dob:15 nov ; now:16 dec (=> 1 bulan)
				// dilewatkan
			}
			
			if(agem < 0)
			{
				agey = agey - 1;	
			}else if(agem > 0)
			{
				if(agem < fix_number)
				{
					agey = agey;
				}else if(agem >= fix_number)
				{
					agey = agey + 1;
				}
			}
			if(agey < 0) agey = 0;
			age = agey;	
			// validasi
			if(isNaN(age)){
				alert('format tanggal salah');
			}else{
				//result
        		form[formTarget].value = age;
        	}
        	//tambahan(optional)
        	if( form['cepr01030101Form.insuredAge'].value == '0' )
            {
            	if(!isNaN(age)){
               		form['cepr01030101Form.insuredAge'].value = age;
               	}
            }
        }
        
        function buttonLinks(p){
			if(p=='hp'){//hitung umur pemegang
				document.getElementById('flag').value='4';
			}else if(p=='ht'){//hitung umur tertanggung
				document.getElementById('flag').value='5';
			}
			
			document.getElementById('formpost').submit();
		}
        
        function cekKetProduk()
        {
            if( document.getElementById('assurancePlanCd1').value == '173==>EKA SARJANA MANDIRI (new)' ||
            	document.getElementById('assurancePlanCd2').value == '173==>EKA SARJANA MANDIRI (new)' ||
            	document.getElementById('assurancePlanCd3').value == '173==>EKA SARJANA MANDIRI (new)' ||
            	document.getElementById('assurancePlanCd4').value == '173==>EKA SARJANA MANDIRI (new)')
            	{
            	document.getElementById('keteranganPemegangPolis').innerHTML = 'untuk produk EKA SARJANA MANDIRI, PEMEGANG POLIS dianggap sebagai TERTANGGUNG.';	
            	document.getElementById('keteranganTertanggung').innerHTML = 'untuk produk EKA SARJANA MANDIRI, TERTANGGUNG dianggap sebagai ANAK.';	
            	}
        }

        function copyToInsured()
        {
            var form = document.forms[ 0 ];
            if( form[ 'cepr01030101Form.insuredName' ].value.trim() == '' )
            {
                form['cepr01030101Form.insuredName'].value = form[ 'cepr01030101Form.policyHolderName' ].value;
            }
            if( form['cepr01030101Form.insuredBirthDay'].value.trim() == '' )
            {
                form['cepr01030101Form.insuredBirthDay'].value = form[ 'cepr01030101Form.policyHolderBirthDay' ].value;
            }
            //if( form['_cepr01030101Form.insuredBirthDay'].value.trim() == '__/__/____' )
            //{
                //form['_cepr01030101Form.insuredBirthDay'].value = form[ '_cepr01030101Form.policyHolderBirthDay' ].value;
                //form['cepr01030101Form.insuredBirthDay'].value = form[ 'cepr01030101Form.policyHolderBirthDay' ].value;
            //}
            if( form['cepr01030101Form.insuredSexCd'].value.trim() == '' )
            {
                form['cepr01030101Form.insuredSexCd'].value = form[ 'cepr01030101Form.policyHolderSexCd' ].value;
            }
        }
		
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="formpost" commandName="cepr01030000HoldingForm" name="">
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
	  <TD height="220" valign="top" style="background-image:url(${path}/include/images/bg_out.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;</TD>
		<TD COLSPAN=11>
			<IMG SRC="${path}/include/images/web_proposal_08.jpg" WIDTH=925 HEIGHT=220 ALT=""></TD>
        <TD valign="top" style="background-image:url(${path}/include/images/bg_out.jpg); background-repeat:repeat-x; background-position:bottom;"><!--DWLayoutEmptyCell-->&nbsp;</TD>
	</TR>
	<TR>
	  <TD ROWSPAN=14>&nbsp;</TD>
	  <TD height="19" style="background-image:url(${path}/include/images/web_proposal_09.jpg); ">&nbsp;			</TD>
		<TD COLSPAN=9 style="background-image:url(${path}/include/images/web_proposal_10.jpg); background-repeat:repeat-x; ">&nbsp;			</TD>
		<TD style="background-image:url(${path}/include/images/web_proposal_11.jpg); ">&nbsp;			</TD>
        <TD ROWSPAN=14>&nbsp;</TD>
	</TR>
	<TR>
		<TD ROWSPAN=12 style="background-image:url(${path}/include/images/web_proposal_12.jpg); ">&nbsp;			</TD>
		<TD height="75" COLSPAN=5 class="fontLink" ><p class="style3">&nbsp;</p>
		  <p class="style3"><a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonPreviewLetter'; checkEvent();"><fmt:message key="main.createLetter"/></a></p>
		  <p class="style3"><a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonCreateCover'; checkEvent();"><fmt:message key="main.createCover"/></a></p>
		  <p class="style3"><a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonLookUpProvider'; checkEvent();"><fmt:message key="main.seeProvider"/></a></p>
		  <p class="style3"><a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonLookUpSwineFluProvider'; checkEvent();"><fmt:message key="main.seeSwineFluProvider"/></a></p>
		  <p class="style3"><a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonDownloadMedical'; checkEvent();"><fmt:message key="main.seeMedical"/></a></p>
	      <p class="style3"><a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonDownloadViewMedical'; checkEvent();"><fmt:message key="main.viewMedical"/></a></p>
		  <p class="style3"><a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonCreateDPLK'; checkEvent();"><fmt:message key="main.createDPLK"/></a></p>	    
	    <p style="font-size: 12px;"><fmt:message key="main.createProposal"/></p>
		<p>&nbsp;</p>
      </TD>
		<TD COLSPAN=4>&nbsp;			</TD>
		<TD ROWSPAN=12 style="background-image:url(${path}/include/images/web_proposal_15.jpg); ">&nbsp;			</TD>
    </TR>
	<TR>
		<TD height="48" class="fontLabel1 style2"><fmt:message key="words.proposalData"/></TD>
		<TD COLSPAN=8>&nbsp;			</TD>
    </TR>
	<TR>
		<TD height="24" class="fontLabel1"><fmt:message key="words.policyHolder"/></TD>
		<TD COLSPAN=8><label id="keteranganPemegangPolis" style="font-size: 12px;"/></TD>
    </TR>
	<TR>
		<TD height="170" colspan="9" style="background-image:url(${path}/include/images/bg_form1.jpg); background-repeat:repeat-x; ">
	      <p>&nbsp;</p>
	      <table width="600" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.name"/> :</div></td>
              <td width="1" class="fontForm">&nbsp;</td>
              <td width="213" height="33" >              <div align="left" >
                <form:input path="cepr01030101Form.policyHolderName" onchange="copyToInsured();" cssClass="fontForm"/>
              </div></td>
			  <td width="257" class="tdFormError">
        		<form:errors path="cepr01030101Form.policyHolderName"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.birthday"/> :</div></td>
              <td class="fontForm">&nbsp;</td>
              <td width="213" height="33">              <div align="left">
                <form:input path="cepr01030101Form.policyHolderBirthDay" onchange="copyToInsured();" size="15" maxlength="10"  title="Tanggal Lahir Pemegang Polis [format: dd/mm/yyyy (d=date, m=month, y=year)]"  />
              	dd/mm/yyyy
              </div></td>
			  <td class="tdFormError"><!--buttonLinks('hp'); calculateAge('cepr01030101Form.policyHolderBirthDay','cepr01030101Form.policyHolderAge'); -->
			  	<input type="button" value="Hit Umur" name="hit_umur_pp" onClick=" calculateAge('cepr01030101Form.policyHolderBirthDay','cepr01030101Form.policyHolderAge'); " />
        		<form:errors path="cepr01030101Form.policyHolderBirthDay"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.age"/> :</div></td>
              <td class="fontForm">&nbsp;</td>
              <td width="213" height="33">              <div align="left">
                <form:input path="cepr01030101Form.policyHolderAge" onchange="copyToInsured()" cssClass="fontForm" readonly="true"/>
              </div></td>
			  <td class="tdFormError">
        		<form:errors path="cepr01030101Form.policyHolderAge"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.sex"/> :</div></td>
              <td class="fontForm">&nbsp;</td>
              <td width="213" height="33">              <div align="left">
                <form:select path="cepr01030101Form.policyHolderSexCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030101Form.genderList}" itemLabel="label"
                     itemValue="value" onchange="copyToInsured()"/>
              </div></td>
			  <td class="tdFormError">
        		<form:errors path="cepr01030101Form.policyHolderSexCd"/>
    		  </td>
            </tr>
          </table>
      </TD>
    </TR>
	<TR>
		<TD height="24" class="fontLabel1"><fmt:message key="words.assured"/></TD>
		<TD COLSPAN=8><label id="keteranganTertanggung" style="font-size: 12px;"/></TD>
    </TR>
	<TR>
		<TD height="170" colspan="9" style="background-image:url(${path}/include/images/bg_form2.jpg); background-repeat:repeat-x; ">
          <p>&nbsp;</p>
          <table width="600" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.name"/> :</div></td>
              <td width="1" class="fontForm">&nbsp;</td>
              <td width="213" height="33"><div align="left">
                  <form:input path="cepr01030101Form.insuredName" cssClass="fontForm"/>
              </div></td>
			  <td width="257" class="tdFormError">
        		<form:errors path="cepr01030101Form.insuredName"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.birthday"/> :</div></td>
              <td class="fontForm">&nbsp;</td>
              <td height="33"><div align="left">
              	<form:input path="cepr01030101Form.insuredBirthDay"  size="15" maxlength="10"  title="Tanggal Lahir Tertanggung [format: dd/mm/yyyy (d=date, m=month, y=year)]"  />
              	dd/mm/yyyy
              </div></td>
			  <td width="257" class="tdFormError">
			  	<input type="button" value="Hit Umur" name="hit_umur_tt" onClick="calculateAge('cepr01030101Form.insuredBirthDay','cepr01030101Form.insuredAge');" />
        		<form:errors path="cepr01030101Form.insuredBirthDay"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.age"/> :</div></td>
              <td class="fontForm">&nbsp;</td>
              <td height="30"><div align="left">
                  <form:input path="cepr01030101Form.insuredAge" cssClass="fontForm" readonly="true"/>
              </div></td>
			  <td width="257" class="tdFormError">
        		<form:errors path="cepr01030101Form.insuredAge"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.sex"/> :</div></td>
              <td class="fontForm">&nbsp;</td>
              <td height="30"><div align="left">
                  <form:select path="cepr01030101Form.insuredSexCd" cssClass="fontForm"
                     items="${cepr01030000HoldingForm.cepr01030101Form.genderList}" itemLabel="label"
                     itemValue="value"/>
              </div></td>
			  <td width="257" class="tdFormError">
        		<form:errors path="cepr01030101Form.insuredSexCd"/>
    		  </td>
            </tr>
          </table>
	    </TD>
	</TR>
	<TR>
		<TD height="24" class="fontLabel1"><fmt:message key="words.plans"/></TD>
		<TD COLSPAN=8>&nbsp;			</TD>
    </TR>
	<TR>
		<TD height="170" colspan="9" style="background-image:url(${path}/include/images/bg_form3.jpg); background-repeat:repeat-x; ">
          <p>&nbsp;</p>
          <table width="600" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right">1.</div></td>
              <td width="1" class="fontForm">&nbsp;</td>
              <td width="213" height="33"><div align="left">
                  <form:select path="cepr01030101Form.assurancePlanCd1" cssClass="fontForm" id="assurancePlanCd1" onchange="cekKetProduk();"
                     items="${cepr01030000HoldingForm.cepr01030101Form.assurancePlanList}" itemLabel="label"
                     itemValue="value"/>
              </div></td>
			  <td width="257" class="tdFormError">
        		<form:errors path="cepr01030101Form.assurancePlanCd1"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right">2.</div></td>
              <td width="1" class="fontForm">&nbsp;</td>
              <td height="33"><div align="left">
                  <form:select path="cepr01030101Form.assurancePlanCd2" cssClass="fontForm" id="assurancePlanCd2" onchange="cekKetProduk();"
                     items="${cepr01030000HoldingForm.cepr01030101Form.assurancePlanList}" itemLabel="label"
                     itemValue="value"/>
              </div></td>
			  <td class="tdFormError">
        		<form:errors path="cepr01030101Form.assurancePlanCd2"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right">3.</div></td>
              <td width="1" class="fontForm">&nbsp;</td>
              <td height="30"><div align="left">
                  <form:select path="cepr01030101Form.assurancePlanCd3" cssClass="fontForm" id="assurancePlanCd3" onchange="cekKetProduk();"
                     items="${cepr01030000HoldingForm.cepr01030101Form.assurancePlanList}" itemLabel="label"
                     itemValue="value"/>
              </div></td>
			  <td class="tdFormError">
        		<form:errors path="cepr01030101Form.assurancePlanCd3"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right">4.</div></td>
              <td width="1" class="fontForm">&nbsp;</td>
              <td height="30"><div align="left">
                  <form:select path="cepr01030101Form.assurancePlanCd4" cssClass="fontForm" id="assurancePlanCd4" onchange="cekKetProduk();"
                     items="${cepr01030000HoldingForm.cepr01030101Form.assurancePlanList}" itemLabel="label"
                     itemValue="value"/>
              </div></td>
			  <td class="tdFormError">
        		<form:errors path="cepr01030101Form.assurancePlanCd4"/>
    		  </td>
            </tr>
          </table>
	    </TD>
	</TR>
	<TR>
		<TD height="25" class="fontLabel1"><fmt:message key="words.presentedBy"/></TD>
		<TD COLSPAN=8>&nbsp;			</TD>
    </TR>
	<TR>
		<TD height="137" colspan="9" style="background-image:url(${path}/include/images/bg_form4.jpg); background-repeat:repeat-x; ">
          <p>&nbsp;</p>
          <table width="600" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.name"/> :</div></td>
              <td width="1" class="fontForm">&nbsp;</td>
              <td width="213" height="33"><div align="left">
                  <form:input path="cepr01030101Form.proposalUser" cssClass="fontForm"/>
              </div></td>
			  <td width="257" class="tdFormError">
        		<form:errors path="cepr01030101Form.proposalUser"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.code"/> :</div></td>
              <td class="fontForm">&nbsp;</td>
              <td width="213" height="33"><div align="left">
                  <form:input path="cepr01030101Form.proposalUserCd" cssClass="fontForm"/>
              </div></td>
			  <td class="tdFormError">
        		<form:errors path="cepr01030101Form.proposalUserCd"/>
    		  </td>
            </tr>
            <tr>
              <td width="130" height="33" class="fontForm"><div align="right"><fmt:message key="words.proposalDate"/> :</div></td>
              <td class="fontForm">&nbsp;</td>
              <td width="213" height="30"><div align="left">
                  <spring:bind path="cepr01030000HoldingForm.cepr01030101Form.proposalDate">
            		<script type="text/javascript">inputDate('${status.expression}', '${status.value}', false, '', 9);</script>
        		  </spring:bind>
              </div></td>
			  <td class="tdFormError">
        		<form:errors path="cepr01030101Form.proposalDate"/>
   		      </td>
            </tr>
              <tr>
                  <td colspan="4">&nbsp;</td>
              </tr>
          </table>
	  </TD>
	</TR>
	<TR>
		
        <input type="hidden" id="targetParam"/>
        <input type="hidden" name="theEvent" id="theEvent"/>
        
		<TD ><div align="center">
		<a href="#" onclick="document.forms[ 0 ].theEvent.value='onPressButtonNext'; checkEvent()" onmouseover="changeButtonBerikutToGlow();" onmouseout="changeButtonBerikutToNormal();">
		<img src="${path}/include/images/bt_berikut.gif"   alt="" style="border: 0;" id="buttonBerikutId"> 
		</a>
		</div></TD>
		<TD COLSPAN=2><div align="center">
		<a href="#" onmouseover="changeButtonResetToGlow();" onmouseout="changeButtonResetToNormal();">
		<img src="${path}/include/images/bt_reset.gif"   alt="" style="border: 0;" id="buttonResetId">
		</a>
	    </div></TD>
		<TD><div align="center">
		<a href="#" onclick="window.close()" onmouseover="changeButtonTutupToGlow();" onmouseout="changeButtonTutupToNormal();">
		<img src="${path}/include/images/bt_tutup.gif"   alt="" style="border: 0;" id="buttonTutupId"> 
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
        </spring:hasBindErrors></TD>
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

<form:hidden path="cepr01030101Form.downloadFlag"/>
<input type="hidden" name="flag" value="0" />
</form:form>
<!-- End ImageReady Slices -->

<script type="text/javascript">
    var downloadFlag = document.getElementById('cepr01030101Form.downloadFlag').value;
    if( downloadFlag == 'newPage' )
    {
        document.getElementById('cepr01030101Form.downloadFlag').value = '';
        document.getElementById('formpost').target = '_blank';
        document.getElementById('targetParam').name = CEPR00000000DownloadDocumentJSP;
        document.getElementById('formpost').submit();
        document.getElementById('formpost').target = '_self';
        document.getElementById('targetParam').name = CEPR01030101JSP;
        document.getElementById('formpost').submit();
    }
    else if( downloadFlag == 'samePage' )
    {
        document.getElementById('cepr01030101Form.downloadFlag').value = '';
        document.getElementById('formpost').action='${downloadUrlSession}';
        document.getElementById('formpost').submit();
        document.getElementById('formpost').target = '_self';
    }
    else
    {
        document.getElementById( 'cepr01030101Form.policyHolderName' ).focus();
    }
</script>

</BODY>
</HTML>