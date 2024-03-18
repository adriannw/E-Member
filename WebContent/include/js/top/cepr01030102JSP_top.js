var CEPR01030107JSP = '_target12';
var CEPR01030113JSP = '_target20';
var CEPR01030114JSP = '_target21';
  
function changeButtonPreviewProposalToGlow()
{
     document.getElementById( "buttonPreviewProposalId" ).src = path.concat('/include/images/bt_lihatproposal-ro.gif');
}

function changeButtonPreviewProposalToNormal()
{
    document.getElementById( "buttonPreviewProposalId" ).src = path.concat('/include/images/bt_lihatproposal.gif');
}

function changeButtonBackToGlow()
{
   document.getElementById( "buttonBackId" ).src = path.concat('/include/images/bt_kembali-ro.gif');
}
	
function changeButtonBackToNormal()
{
   document.getElementById( "buttonBackId" ).src = path.concat('/include/images/bt_kembali.gif');
}

function changeButtonParticipantEkaSehatToGlow()
{
    document.getElementById( "buttonParticipantEkaSehatId" ).src = path.concat('/include/images/bt_peserta-ro.gif');
}
   
function changeButtonParticipantEkaSehatToNormal()
{
    document.getElementById( "buttonParticipantEkaSehatId" ).src = path.concat('/include/images/bt_peserta.gif');
}
	
function changeButtonHomeToGlow()
{
    document.getElementById( "buttonHomeId" ).src = path.concat('/include/images/bt_home-ro.gif');
}
  
function changeButtonHomeToNormal()
{
   document.getElementById( "buttonHomeId" ).src = path.concat('/include/images/bt_home.gif');
}
	
function changeButtonLogOffToGlow()
{
   document.getElementById( "buttonLogOffId" ).src = path.concat('/include/images/bt_logoff-ro.gif');
}
  
function changeButtonLogOffToNormal()
{
   document.getElementById( "buttonLogOffId" ).src = path.concat('/include/images/bt_logoff.gif');
}
	
function changeButtonCountPremiumToGlow()
{
   document.getElementById( "buttonCountPremiumId" ).src = path.concat('/include/images/bt_hitung-ro.gif');
}
  
function changeButtonCountPremiumToNormal()
{
   document.getElementById( "buttonCountPremiumId" ).src = path.concat('/include/images/bt_hitung.gif');
}
	
function changeButtonRiderToGlow()
{
   document.getElementById( "buttonRiderId" ).src = path.concat('/include/images/bt_rider-ro.gif');
}
  
function changeButtonRiderToNormal()
{
   document.getElementById( "buttonRiderId" ).src = path.concat('/include/images/bt_rider.gif');
}
	
function changeButtonTopupToGlow()
{
    document.getElementById( "buttonTopupId" ).src = path.concat('/include/images/bt_topuppenarikan-ro.gif');
}
  
function changeButtonTopupToNormal()
{
    document.getElementById( "buttonTopupId" ).src = path.concat('/include/images/bt_topuppenarikan.gif');
}

function changeButtonCountTotalToGlow()
{
    document.getElementById( "buttonCountTotalId" ).src = path.concat('/include/images/bt_hitung-ro.gif');
}
  
function changeButtonCountTotalToNormal()
{
    document.getElementById( "buttonCountTotalId" ).src = path.concat('/include/images/bt_hitung.gif');
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
  
var no_proposal = no_proposal;
var lead = lead;
var credential = credential;
//var url = 'https://crm.sinarmasmsiglife.co.id/index.php/';
var url = url;
// alert(credential +' '+ lead);
  
if(no_proposal != ''){  
  	//alert(typeof $);  	  
				$.ajax({
	        		url: url+"external/update_proposal_bac",
	        		data:{'no_proposal':no_proposal,'lead_id':lead,'credential':credential},
	        	    dataType : 'json',
	        		success: function(result){
					//	alert(result);
	        		}
	        	});
  	//alert('NO PROPOSAL : '+no_proposal);
}