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
    	
    if( theEvent == 'onPressButtonOk')
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
    document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].name' ).value = '';
    document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].birthDay' ).value = '';
   // document.getElementById( '_cepr01030113Form.participantVOList[' + rowIdx + '].birthDay' ).value = '__/__/____';
    document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].age' ).value = '';
    document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].lsre_id').selectedIndex = 0;
    document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].sexCd').selectedIndex = 0;
    document.getElementById( 'cepr01030113Form.participantVOList[' + rowIdx + '].medicalPlusRbFlag').checked = '';
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
	date_of_birth = document.getElementById(formSource).value;
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
	var li_month = 0;
	var li_add = 0;	
	
	document.getElementById(formTarget).value = '';
	if (Dob_year != year)
	{
		if (month >= Dob_month)
		{
			age = year - Dob_year;
		}else{
			age = (year - Dob_year) - 1;
			li_add = 12;
		}
		li_month = month + li_add - Dob_month;
		if (li_month >= 6)
		{
			if (li_month==6)
			{
				if ((day  - Dob_day) >= 0)
				{
					age = age+1;
				}
			}else{
				age = age+1;
			}
			
		}
	}
	if (age<0){
		age=0;
	}	
	
	/* var agem_flag = agem;
	if(agem < 0){
		agem = (12-Dob_month) + month;
	}
	
	if(aged > 0 ) // kasus : dob:16 nov ; now:15 dec (< 1 bulan)
	{
		
	}else if(aged <= 0 ){ // kasus : dob:15 nov ; now:16 dec (=> 1 bulan)
		// dilewatkan
		agem = agem - 1;
	}
	
	if(agem_flag < 0)
	{
		agey = agey - 1;	
	}else if(agem_flag > 0)
	{
		// dilewatkan
	}
	if(agem < fix_number)
		{
			agey = agey;
	}else if(agem >= fix_number)
		{
			agey = agey + 1;
		}
	
	if(agey < 0) agey = 0;
	age = agey;	 */
	// validasi
	if(isNaN(age)){
		alert('format tanggal salah');	
	}else{
		//result
		document.getElementById(formTarget).value = age;
	}
//tambahan(optional)
	if( document.getElementById(formTarget).value == '0' )
    {
    	if(!isNaN(age)){
       		document.getElementById(formTarget).value = age;
       	}
    }
}