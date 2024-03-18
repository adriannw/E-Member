var downloadFlag = document.getElementById('cepr01030102Form.downloadFlag').value;

if( downloadFlag == 'newPage' )
{
    document.getElementById('cepr01030102Form.downloadFlag').value = '';
    document.getElementById('formpost').target = '_blank';
    document.getElementById('targetParam').name = CEPR00000000DownloadDocumentJSP;
    document.getElementById('formpost').submit();
    document.getElementById('formpost').target = '_self';
}
if( downloadFlag == 'samePage' )
{
    document.getElementById('cepr01030102Form.downloadFlag').value = '';
    document.getElementById('formpost').action = downloadUrlSession;
    document.getElementById('formpost').submit();
    document.getElementById('formpost').target = '_self';
}
