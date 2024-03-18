document.getElementById( 'account' ).focus();
     
if(user != '' && pass != ''){
    document.getElementById( "account" ).value = user;
    document.getElementById( "password" ).value = pass;
    doLogin();
}

if(clickForLife == 'clickForLife'){
    document.getElementById( "account" ).value = 'clickforlife';
   	document.getElementById( "password" ).value = 'clickforlife';
    doLogin();
}