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
    document.getElementById('formpost').action = downloadUrlSession;
    document.getElementById('formpost').submit();
    document.getElementById('formpost').target = '_self';
}
else
{
    document.getElementById( 'cepr01030101Form.policyHolderName' ).focus();
}