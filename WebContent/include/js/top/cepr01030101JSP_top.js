var CEPR01030901JSP = '_target18';
var CEPR01030904JSP = '_target19';

function changeButtonResetToGlow()
{
   document.getElementById( "buttonResetId" ).src =  path.concat('/include/images/bt_reset-ro.gif');
}

function changeButtonResetToNormal()
{
   document.getElementById( "buttonResetId" ).src = path.concat('/include/images/bt_reset.gif');
}

function changeButtonTutupToGlow()
{
   document.getElementById( "buttonTutupId" ).src = path.concat('/include/images/bt_tutup-ro.gif');
}

function changeButtonTutupToNormal()
{
   document.getElementById( "buttonTutupId" ).src = path.concat('/include/images/bt_tutup.gif');
}

function changeButtonBerikutToGlow()
{
   document.getElementById( "buttonBerikutId" ).src = path.concat('/include/images/bt_berikut-ro.gif');
}

function changeButtonBerikutToNormal()
{
   document.getElementById( "buttonBerikutId" ).src = path.concat('/include/images/bt_berikut.gif');
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
    if( document.getElementById('theEvent').value == 'onPressButtonNext' )
    {
   		document.body.style.cursor = "progress";
        document.getElementById('targetParam').name = CEPR01030102JSP;
        document.getElementById('formpost').submit();
    }
//    else if( document.getElementById('theEvent').value == 'onPressButtonCreateLetter' )
//    {
//        document.getElementById('targetParam').name = CEPR01030301JSP;
//        document.getElementById('formpost').submit();
//    }
    else if( document.getElementById('theEvent').value == 'onPressButtonPreviewLetter' || document.getElementById('theEvent').value == 'onPressButtonDowloadRiskProfil' || document.getElementById('theEvent').value == 'onPressButtonDownloadGuideRiskProfil')
    {
        document.getElementById('targetParam').name = CEPR01030101JSP;
        document.getElementById('formpost').submit();
    }
    else if( document.getElementById('theEvent').value == 'onPressButtonCreateCover' )
    {
        document.getElementById('targetParam').name = CEPR01030401JSP;
        document.getElementById('formpost').submit();
    }
    else if( document.getElementById('theEvent').value == 'onPressButtonCreateCover' )
    {
        document.getElementById('targetParam').name = CEPR01030401JSP;
        document.getElementById('formpost').submit();
    }
	else if( document.getElementById('theEvent').value == 'onPressButtonLookUpProvider' )
    {
        document.getElementById('targetParam').name = CEPR01030501JSP;
        document.getElementById('formpost').submit();
    }
    else if( document.getElementById('theEvent').value == 'onPressButtonDownloadMedical' || document.getElementById('theEvent').value == 'onPressButtonDownloadViewMedical')
    {
        document.getElementById('targetParam').name = CEPR01030101JSP;
        document.getElementById('formpost').submit();
    }
    else if( document.getElementById('theEvent').value == 'onPressButtonLookUpSwineFluProvider' )
    {
        document.getElementById('targetParam').name = CEPR01030701JSP;
        document.getElementById('formpost').submit();
    }
      else if( document.getElementById('theEvent').value == 'onPressButtonCreateDPLK' )
    {
        document.getElementById('targetParam').name = CEPR01030901JSP;
        document.getElementById('formpost').submit();
    } 
      else if( document.getElementById('theEvent').value == 'onPressButtonCari' )
    {
   
     //   document.getElementById('targetParam').name = CEPR01030101JSP;
      // document.getElementById('formpost').submit();
   //  while(document.getElementById("namaAdminBCList").childNodes.length>0) 
	//{
	//  document.getElementById("namaAdminBCList").removeChild(document.getElementById("namaAdminBCList").childNodes[0]);
	//}
	//var nm = $("#cepr01030101Form.cariNamaAdminBC").val();
	var nm = document.getElementById('cariNamaAdminBC').value;
		ajax(nm, "nama", jn_bank);              
                      
    }
      else if( document.getElementById('theEvent').value == 'onPressButtonCari2' )
    {           
     //   document.getElementById('targetParam').name = CEPR01030101JSP;
      // document.getElementById('formpost').submit();
   //  while(document.getElementById("namaAdminBCList").childNodes.length>0) 
	//{
	//  document.getElementById("namaAdminBCList").removeChild(document.getElementById("namaAdminBCList").childNodes[0]);
	//}
	//var nm = $("#cepr01030101Form.cariNamaAdminBC").val();

	var kd = document.getElementById('cariKodeAdminBC').value;	
    ajax(kd, "kode", jn_bank);                      
    }     
}

function calculateAge(formSource,formTarget){
    // untuk format dd/mm/yyyy
    	var form = document.getElementById('formpost');
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
		var li_month = 0;
		var li_add = 0;	
		
		form[formTarget].value = '';
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
    var form = document.getElementById('formpost');
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


//var url = 'https://crm.sinarmasmsiglife.co.id/index.php/';
var url = '${hostname}';

$(document).ready(function(){
	/*$.ajax({
		url: url+"main/authenticate_user/",
		data:{'username':'${login}','credential':'${credential}','apptype':'${apptype}'},
	    dataType : 'json',
	    type :'post',
		success: function(authenticate){
	        if(authenticate.state == 'success'){
		        $.ajax({
		        	url: authenticate.url+'/ajax/',
		            dataType : 'text',
		        	success: function(login){
		        	}
		        });
	        }
		}
	});*/
	var cred = '${credential}';
	//alert(cred);
	$.ajax({
		//url: url+"main/module/lead/get_data/",
		url: url+"external/get_lead_bac/",
		data:{'lead_id':'${lead}','agent_id':'${agen}','credential':'${credential}'},
	    dataType : 'json',
		success: function(lead){
			var date = new Date(lead[0].birth_date);
			var newDate = date.getDate()+'/'+(date.getMonth() + 1)+'/'+date.getFullYear();														        			
			document.getElementById('cepr01030101Form.policyHolderName').value=lead[0].lead_name;
			document.getElementById('cepr01030101Form.policyHolderBirthDay').value=newDate;
		}
	});
	
	
	if('${cepr01030000HoldingForm.cepr01030101Form.namaAdminBC}' != "" ){
	
	ajax('${cepr01030000HoldingForm.cepr01030101Form.namaAdminBC}', "kode", '${sessionScope.jn_bank}');
	
				
	}
	
});

