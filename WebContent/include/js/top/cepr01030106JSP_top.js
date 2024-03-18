function changeButtonOkToGlow()
{
	document.getElementById( "buttonOkId" ).src = path.concat('/include/images/bt_ok-ro.gif');
}

function changeButtonOkToNormal()
{
    document.getElementById( "buttonOkId" ).src = path.concat('/include/images/bt_ok.gif');
}

function changeButtonCancelToGlow()
{
   document.getElementById( "buttonCancelId" ).src = path.concat('/include/images/bt_batal-ro.gif');
}

function changeButtonCancelToNormal()
{
   document.getElementById( "buttonCancelId" ).src = path.concat('/include/images/bt_batal.gif');
}

function changeButtonBersihkanToGlow( index )
{
   document.getElementById( "buttonBersihkanId".concat(index) ).src = path.concat('/include/images/bt_bersihkan-ro.gif');
}

function changeButtonBersihkanToNormal( index )
{
   document.getElementById( "buttonBersihkanId".concat(index) ).src = path.concat('/include/images/bt_bersihkan.gif');
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
			
function checkEvent()
{
	var theEvent = document.forms[ 0 ].theEvent.value;
            			
    if( theEvent == 'onPressButtonOk' )
    {
        document.getElementById('targetParam').name = CEPR01030103JSP;
        document.getElementById('formpost').submit();
    }
    else if( theEvent == 'onPressButtonCancel' )
    {
        document.getElementById('targetParam').name = CEPR01030103JSP;
        document.getElementById('formpost').submit();
    }			
}
		
function clearRowContent( rowIdx )
{
    document.getElementById( 'cepr01030106Form.participantVOList[' + rowIdx + '].name' ).value = '';
    document.getElementById( 'cepr01030106Form.participantVOList[' + rowIdx + '].birthDay' ).value = '';
    document.getElementById( '_cepr01030106Form.participantVOList[' + rowIdx + '].birthDay' ).value = '__/__/____';
    document.getElementById( 'cepr01030106Form.participantVOList[' + rowIdx + '].age' ).value = '';
    document.getElementById( 'cepr01030106Form.participantVOList[' + rowIdx + '].lsre_id').selectedIndex = 0;
 }