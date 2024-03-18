function changeButtonOkToOrange()
{
	document.getElementById( "buttonOkId" ).src = path.concat('/include/images/button/ok-ro.gif');
}
        
function changeButtonOkToNormal()
{
	document.getElementById( "buttonOkId" ).src = path.concat('/include/images/button/ok.gif');
}
       
function doLogin()
{
	document.getElementById('formpost').submit();
}