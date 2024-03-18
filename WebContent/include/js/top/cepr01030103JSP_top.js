var CEPR01030107JSP = '_target12';
var CEPR01030108JSP = '_target13';
var CEPR01030109JSP = '_target14';
var CEPR01030110JSP = '_target15';
var CEPR01030111JSP = '_target16';
var CEPR01030112JSP = '_target17';
var CEPR01030113JSP = '_target20';
var CEPR01030114JSP = '_target21';

function changeButtonOkToGlow()
{
    document.getElementById( "buttonOkId" ).src = path.concat('/include/images/bt_ok-ro.gif');
}

function changeButtonOkToNormal()
{
    document.getElementById( "buttonOkId" ).src = path.concat('/include/images/bt_ok.gif');
}

function changeButtonResetToGlow()
{
    document.getElementById( "buttonResetId" ).src = path.concat('/include/images/bt_reset-ro.gif');
}

function changeButtonResetToNormal()
{
    document.getElementById( "buttonResetId" ).src = path.concat('/include/images/bt_reset.gif');
}

function changeButtonParticipantToGlow()
{
    document.getElementById( "buttonParticipantId" ).src = path.concat('/include/images/bt_peserta-ro.gif');
}

function changeButtonParticipantToNormal()
{
    document.getElementById( "buttonParticipantId" ).src = path.concat('/include/images/bt_peserta.gif');
}

function changeButtonParticipantHcpProviderToGlow()
{
    document.getElementById( "buttonParticipantHcpProviderId" ).src = path.concat('/include/images/bt_peserta-ro.gif');
}

function changeButtonParticipantHcpProviderToNormal()
{
    document.getElementById( "buttonParticipantHcpProviderId" ).src = path.concat('/include/images/bt_peserta.gif');
}

function changeButtonParticipantEkaSehatToGlow()
{
    document.getElementById( "buttonParticipantEkaSehatId" ).src = path.concat('/include/images/bt_peserta-ro.gif');
}

function changeButtonParticipantEkaSehatToNormal()
{
    document.getElementById( "buttonParticipantEkaSehatId" ).src = path.concat('/include/images/bt_peserta.gif');
}

function changeButtonParticipantEkaSehatInnerLimitToGlow()
{
    document.getElementById( "buttonParticipantEkaSehatInnerLimitId" ).src = path.concat('/include/images/bt_peserta-ro.gif');
}

function changeButtonParticipantEkaSehatInnerLimitToNormal()
{
    document.getElementById( "buttonParticipantEkaSehatInnerLimitId" ).src = path.concat('/include/images/bt_peserta.gif');
}

function changeButtonParticipantLadiesMedExpenseToGlow()
{
    document.getElementById( "buttonParticipantLadiesMedExpenseId" ).src = path.concat('/include/images/bt_peserta-ro.gif');
}
function changeButtonParticipantLadiesMedExpenseToNormal()
{
    document.getElementById( "buttonParticipantLadiesMedExpenseId" ).src = path.concat('/include/images/bt_peserta.gif');
}

 function changeButtonParticipantLadiesMedExpenseInnerLimitToGlow()
{
    document.getElementById( "buttonParticipantLadiesMedExpenseInnerLimitId" ).src = path.concat('/include/images/bt_peserta-ro.gif');
}
 
function changeButtonParticipantLadiesMedExpenseInnerLimitToNormal()
{
    document.getElementById( "buttonParticipantLadiesMedExpenseInnerLimitId" ).src = path.concat('/include/images/bt_peserta.gif');
}

function changeButtonParticipantMedicalPlusToGlow()
{
    document.getElementById( "buttonParticipantMedicalPlusId" ).src = path.concat('/include/images/bt_peserta-ro.gif');
}

function changeButtonParticipantMedicalPlusToNormal()
{
    document.getElementById( "buttonParticipantMedicalPlusId" ).src = path.concat('/include/images/bt_peserta.gif');
}

function changeButtonClassToGlow()
{
    document.getElementById( "buttonClassId" ).src = path.concat('/include/images/bt_kelas-ro.gif');
}

function changeButtonClassToNormal()
{
    document.getElementById( "buttonClassId" ).src = path.concat('/include/images/bt_kelas.gif');
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

function changeButtonResetToGlow()
{
    document.getElementById( "buttonResetId" ).src = path.concat('/include/images/bt_reset-ro.gif');
}

function changeButtonResetToNormal()
{
    document.getElementById( "buttonResetId" ).src = path.concat('/include/images/bt_reset.gif');
}

function changeButtonParticipantEkaSehatExtraToGlow()
{
    document.getElementById( "buttonParticipantEkaSehatExtraId" ).src = path.concat('/include/images/bt_peserta-ro.gif');
}

function changeButtonParticipantEkaSehatExtraToNormal()
{
    document.getElementById( "buttonParticipantEkaSehatExtraId" ).src = path.concat('/include/images/bt_peserta.gif');
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
    else if( theEvent == 'onPressButtonParticipantHcpProvider' )
    {
        document.getElementById('targetParam').name = CEPR01030109JSP;
        document.getElementById('formpost').submit();
    }
    else if( theEvent == 'onPressButtonParticipantHcpLadies' )
    {
        document.getElementById('targetParam').name = CEPR01030110JSP;
        document.getElementById('formpost').submit();
    }
    else if( theEvent == 'onPressButtonParticipantEkaSehat' )
    {
        document.getElementById('targetParam').name = CEPR01030107JSP;
        document.getElementById('formpost').submit();
    }
    else if( theEvent == 'onPressButtonParticipantEkaSehatInnerLimit' )
    {
        document.getElementById('targetParam').name = CEPR01030108JSP;
        document.getElementById('formpost').submit();
    }
    else if( theEvent == 'onPressButtonParticipantLadiesMedExpense' )
    {
        document.getElementById('targetParam').name = CEPR01030111JSP;
        document.getElementById('formpost').submit();
    }
    else if( theEvent == 'onPressButtonParticipantLadiesMedExpenseInnerLimit' )
    {
        document.getElementById('targetParam').name = CEPR01030112JSP;
        document.getElementById('formpost').submit();
    }
     else if( theEvent == 'onPressButtonParticipantMedicalPlus' )
    {
        document.getElementById('targetParam').name = CEPR01030113JSP;
        document.getElementById('formpost').submit();
    }
     else if( theEvent == 'onPressButtonParticipantEkaSehatExtra' )
    {
        // document.getElementById('targetParam').name = CEPR01030107JSP;
         document.getElementById('targetParam').name = CEPR01030114JSP;
         document.getElementById('formpost').submit();
    }
    else if( theEvent == 'onPressButtonReset' )
    {
        document.getElementById('formpost').reset();
    }
    else if( theEvent == 'onPressButtonBack' )
    {
        document.getElementById('targetParam').name = CEPR01030101JSP;
        document.getElementById('formpost').submit();
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

function handleClickHcpLadiesFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.hcpLadiesCd', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.hcpLadiesListIsDisabled').value = stat;
}

function handleClickHcpFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.hcpCd', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.hcpListIsDisabled').value = stat;
}

function handleClickLadiesInsFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.ladiesInsCd', isDisabled );
    disableForm( 'cepr01030103Form.ladiesInsChooseCd', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.ladiesInsListIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.ladiesInsChooseListIsDisabled').value = stat;
}

function handleClickScholarshipFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.scholarshipCd', isDisabled );
    disableForm( 'cepr01030103Form.scholarshipChooseCd', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.scholarshipListIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.scholarshipChooseListIsDisabled').value = stat;
}

function handleClickCiFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.ciRiderAmount', isDisabled );
//    alert('ci');
    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.ciRiderAmountIsDisabled').value = stat;
}

function handleClickWaiverTpdCiFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.waiverTpdCiChooseCd', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.waiverTpdCiChooseListIsDisabled').value = stat;
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

function handleClickHcpProviderFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.hcpProviderCd', isDisabled );
    disableForm( 'buttonParticipantHcpProviderId', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.hcpProviderListIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.buttonParticipantHcpProviderIsDisabled').value = stat;
}

function handleClickTermRiderFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.termRiderAmount', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.termRiderAmountIsDisabled').value = stat;
}

function handleClickEkaSehatFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.ekaSehatCd', isDisabled );
    disableForm( 'buttonParticipantEkaSehatId', isDisabled );
   // disableForm( 'cepr01030103Form.ekaSehatProviderCd', isDisabled );            

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.ekaSehatListIsDisabled').value = stat;
    //document.getElementById('cepr01030103Form.ekaSehatProviderIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.buttonParticipantEkaSehatIsDisabled').value = stat;
}

function handleClickEkaSehatInnerLimitFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.ekaSehatInnerLimitCd', isDisabled );
    disableForm( 'buttonParticipantEkaSehatInnerLimitId', isDisabled );
   // disableForm( 'cepr01030103Form.ekaSehatProviderCd', isDisabled );            

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.ekaSehatInnerLimitListIsDisabled').value = stat;
    //document.getElementById('cepr01030103Form.ekaSehatProviderIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.buttonParticipantEkaSehatInnerLimitIsDisabled').value = stat;
}

function handleClickLadiesMedExpenseFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.ladiesMedExpenseCd', isDisabled );
    disableForm( 'buttonParticipantLadiesMedExpenseId', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.ladiesMedExpenseListIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.buttonParticipantLadiesMedExpenseIsDisabled').value = stat;
}

function handleClickLadiesMedExpenseInnerLimitFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.ladiesMedExpenseInnerLimitCd', isDisabled );
    disableForm( 'buttonParticipantLadiesMedExpenseInnerLimitId', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.ladiesMedExpenseInnerLimitListIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.buttonParticipantLadiesMedExpenseInnerLimitIsDisabled').value = stat;
}

function handleClickBabyFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.babyChooseCd', isDisabled );
    disableForm( 'cepr01030103Form.babyCd', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.babyChooseListIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.babyListIsDisabled').value = stat;
}

function handleClickEsci99Flag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.esci99RiderAmount', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.esci99RiderAmountIsDisabled').value = stat;
}

function handleClickMedicalPlusFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.medicalPlusChooseCd', isDisabled );
	disableForm( 'cepr01030103Form.medicalPlusRjFlag', isDisabled );
	disableForm( 'cepr01030103Form.medicalPlusRgFlag', isDisabled );
	disableForm( 'cepr01030103Form.medicalPlusRbFlag', isDisabled );
	disableForm( 'cepr01030103Form.medicalPlusPkFlag', isDisabled );
	
    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.medicalPlusChooseListIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.medicalPlusRjFlagIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.medicalPlusRgFlagIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.medicalPlusRbFlagIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.medicalPlusPkFlagIsDisabled').value = stat; 
    
    if( !isChecked ) handleClickMedicalPlusRjFlag(isChecked);
    if( isChecked ) handleClickMedicalPlusRjFlag(!isChecked);
    var medicalPlusRjFlagisChecked = document.getElementById('cepr01030103Form.medicalPlusRjFlag').checked;
    var medicalPlusRjFlagIsDisabled =  document.getElementById('cepr01030103Form.medicalPlusRjFlagIsDisabled').value;
            
   if(medicalPlusRjFlagisChecked){
   		if(medicalPlusRjFlagIsDisabled == 'false'){
  	  		handleClickMedicalPlusRjFlag('false');
  		}
   }
}

function handleClickMedicalPlusRjFlag( isChecked )
{
  	var isDisabled = !isChecked;
	disableForm( 'cepr01030103Form.medicalPlusRgFlag', isDisabled );
	disableForm( 'cepr01030103Form.medicalPlusRbFlag', isDisabled );
	disableForm( 'cepr01030103Form.medicalPlusPkFlag', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';           
    document.getElementById('cepr01030103Form.medicalPlusRgFlagIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.medicalPlusRbFlagIsDisabled').value = stat;
	document.getElementById('cepr01030103Form.medicalPlusPkFlagIsDisabled').value = stat;

	if( !isChecked ){
	  var medicalPlusRjFlagisChecked = document.getElementById('cepr01030103Form.medicalPlusRjFlag').checked;       
	  if(!medicalPlusRjFlagisChecked){
	  	document.getElementById('cepr01030103Form.medicalPlusRgFlag').checked = '';
		document.getElementById('cepr01030103Form.medicalPlusRbFlag').checked = '';
		document.getElementById('cepr01030103Form.medicalPlusPkFlag').checked = ''; 
	  }
	}
}

function handleClickEkaSehatExtraFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.ekaSehatExtraCd', isDisabled );
    disableForm( 'buttonParticipantEkaSehatExtraId', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.ekaSehatExtraListIsDisabled').value = stat;
    //document.getElementById('cepr01030103Form.ekaSehatProviderIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.buttonParticipantEkaSehatExtraIsDisabled').value = stat;
}

function handleClickTerm5575RiderFlag( isChecked )
{
    var isDisabled = !isChecked;
    disableForm( 'cepr01030103Form.term5575RiderAmount', isDisabled );
    disableForm( 'cepr01030103Form.term5575RiderChooseCd', isDisabled );

    var stat = 'true';
    if( isChecked ) stat = 'false';
    document.getElementById('cepr01030103Form.term5575RiderAmountIsDisabled').value = stat;
    document.getElementById('cepr01030103Form.term5575RiderChooseListIsDisabled').value = stat;

}
