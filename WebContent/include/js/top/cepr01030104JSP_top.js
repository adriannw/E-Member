function changeButtonOkToGlow()
{
    document.getElementById( "buttonOkId" ).src = path.concat('/include/images/bt_ok-ro.gif');
}

function changeButtonOkToNormal()
{
    document.getElementById( "buttonOkId" ).src = path.concat('/include/images/bt_ok.gif');
}

function changeButtonComputeTotalToGlow()
{
    document.getElementById( "buttonComputeTotalId" ).src = path.concat('/include/images/bt_hitungtotal-ro.gif');
}

function changeButtonComputeTotalToNormal()
{
    document.getElementById( "buttonComputeTotalId" ).src = path.concat('/include/images/bt_hitungtotal.gif');
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
		
function handleCheckBox( checkBoxId, isChecked, idx )
{
   var topupAmountId = 'cepr01030104Form.topupDrawVOList[' + idx + '].topupAmount';
   var drawAmountId = 'cepr01030104Form.topupDrawVOList[' + idx + '].drawAmount';

   document.getElementById(checkBoxId).value = isChecked ? 'true' : null;

   disableForm(topupAmountId, !isChecked);
   disableForm(drawAmountId, !isChecked);

   if( !isChecked )
   {        
      document.getElementById(topupAmountId).value = '0.00';
      document.getElementById(drawAmountId).value = '0.00';
                
      checkForm( 'cepr01030104Form.checkAllFlag', false );
  }
}

function checkAll( isChecked )
{
    var id;
    var topupAmountId;
    var drawAmountId;
    var topupDefaultAmount;
    var drawDefaultAmount;

    if( isChecked )
    {
        topupDefaultAmount = document.getElementById( 'cepr01030104Form.topupDefaultAmount' ).value;
        drawDefaultAmount = document.getElementById( 'cepr01030104Form.drawDefaultAmount' ).value;
    }
    else
    {
        topupDefaultAmount = '0.00';
        drawDefaultAmount = '0.00';
    }

    for( var idx = 0; idx < 50; idx++ )
    {
        id = 'cepr01030104Form.topupDrawVOList[' + idx + '].yearFlag';
        topupAmountId = 'cepr01030104Form.topupDrawVOList[' + idx + '].topupAmount';
        drawAmountId = 'cepr01030104Form.topupDrawVOList[' + idx + '].drawAmount';

        checkForm(id, isChecked);

        document.getElementById( topupAmountId ).value = topupDefaultAmount;
        if(document.getElementById('namaPlan').value != 'Eduvest')
        {
        	document.getElementById( drawAmountId ).value = drawDefaultAmount;
        }
        document.getElementById( topupAmountId ).disabled = !isChecked;
        document.getElementById( drawAmountId ).disabled = !isChecked;
    }
}

function checkEvent()
{    
	var theEvent = document.forms[ 0 ].theEvent.value;
    
    if( theEvent == 'onPressButtonOk' )
    {
        document.getElementById('targetParam').name = CEPR01030102JSP;
        document.getElementById('formpost').submit();
    }
    else if( theEvent == 'onPressButtonComputeTotal' )
    {
        document.getElementById('targetParam').name = CEPR01030104JSP;
        document.getElementById('formpost').submit();
    }	
}