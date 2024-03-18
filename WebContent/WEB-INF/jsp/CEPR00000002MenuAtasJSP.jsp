<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>

<head>

<link href="${path}/include/css/imgbubbles.css" rel="stylesheet" type="text/css">

<style type="text/css">
</style>

<script type="text/javascript">

    function isThisIPad()
    {
        return navigator.platform == 'iPad';
    }

    lastImageId = 'img1';

    function doAction( imageId )
    {
        if(!(lastImageId==''))
        {
            document.getElementById( lastImageId ).style.width = '30px';
            document.getElementById( lastImageId ).style.height = '30px';
        }
        document.getElementById( imageId ).style.width = '40px';
        document.getElementById( imageId ).style.height = '40px';
        lastImageId = imageId;
        return false;
    }
    
        function onMouseOver( imageId )
    {
        document.getElementById( imageId ).style.width = '40px';
        document.getElementById( imageId ).style.height = '40px';
        return false;
    }
    
         function onMouseOut( imageId )
    {
            document.getElementById( imageId ).style.width = '30px';
            document.getElementById( imageId ).style.height = '30px';
        return false;
    }

    function playSpeaker()
    {
        var productId = parent.frames['menuBawahFrame'].document.forms['menuBawahForm'].elements['productId'].value;
        var mp3FileName = 'profile_ajs.mp3';

        if( productId != null )
        {
            if( productId == '163' )
            {
                mp3FileName ='product_163.mp3';
            }
        }

        if( isThisIPad() )
        {
            document.getElementById('speakerSpanId').innerHTML=
            '<audio src="${path}/include/sound/' + mp3FileName + '" autoplay="true" controls="true" autobuffer="true"></audio>';
        }
        else
        {
            document.getElementById('speakerSpanId').innerHTML=
            '<embed src="${path}/include/sound/' + mp3FileName + '" autostart="true" loop="false" >';
        }
    }

</script>


</head>

<body style="background-color: #FFFFFF;">

    <div style="background-color: #FFFFFF;">

        <div style="background-color:  #FFFFFF;">
            <div style="margin-top: 20px; margin-left: 75px;">

                <ul id="thesmallicon" class="bubblewrap" style="float: right;">
                    <div id="speakerSpanId" style="margin-top: 10px; float: left;">
                    </div>
                    <!--  <li><a href="#"><img id="img3" src="${path}/include/images/button/speaker.png" alt="Voice" style="width: 30px; height: 30px;" onmousedown="doAction( 'img3' ); playSpeaker();" /></a></li>-->
                    <li><a href="${path}/wepr00000001.htm" target="_top"><img id="img1" src="${path}/include/images/button/home_bt2.png" alt="Home" style="width: 30px; height: 30px;" onmouseover="onMouseOver(this.id);" onmouseout="onMouseOut(this.id);"/></a></li>
                    <li><a href="${path}/wepr01030000.htm?productId='${productId}'" target="_top"><img id="img2" src="${path}/include/images/button/calculator_bt2.png" alt="Input Simulasi Produk" style="width: 30px; height: 30px;" onmousedown="parent.document.getElementById('theFrameSet').rows='60,*,0';" onmouseover="onMouseOver(this.id);" onmouseout="onMouseOut(this.id);" /></a></li>
                    <li><a href="${path}/wepr01010102.htm" target="_top"><img id="im3" src="${path}/include/images/button/logout2.png" alt="Log Off" style="width: 30px; height: 30px;" onmouseover="onMouseOver(this.id);" onmouseout="onMouseOut(this.id);"/></a></li>
                </ul>
            </div>
        </div>

    </div>

</body>

</html>