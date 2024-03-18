<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

    <link href="${path}/include/css/imgbubbles.css" rel="stylesheet" type="text/css">

    <script type="text/javascript">

        lastImageId = '';
        lastImageFile = 'default.png';
        lastProductId = '';

        function doAction( imageId, pdfFile, productId, imageFile )
        {
            if( !(lastImageId == '') )
            {
                document.getElementById(lastImageId).style.width = '50px';
                document.getElementById(lastImageId).style.height = '100px';
            }
            document.getElementById(imageId).style.width = '60px';
            document.getElementById(imageId).style.height = '120px';
            lastImageId = imageId;
            lastImageFile = imageFile;
            lastProductId = productId;

            top.menuAtasFrame.location = '${path}/wepr00000002.htm?productId=' + productId;
            top.mainFrame.location = '${path}/wepr00000000.htm?fileName=' + pdfFile;

            return false;
        }

        function previewImg( imgFile )
        {
            top.previewImgFrame.document.getElementById( 'previewImgId' ).setAttribute( 'src', '${path}/include/images/products/' + imgFile );
        }

        function restoreImg()
        {
            top.previewImgFrame.document.getElementById( 'previewImgId' ).setAttribute( 'src', '${path}/include/images/products/' + lastImageFile );
        }

</script>

</head>

<body>

<form id="menuBawahForm" action="">
</form>

<div>
    <c:forEach items="${lsbsIdList}" var="lsbsList" varStatus="xt">
        <%--<c:if test="${'163' == lsbsList}">--%>
            <%--<a href="#" style="margin-left: 20px;"><img id="img1" src="${path}/include/images/products/dana_sejahtera.png" alt="Dana Sejahtera" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'dana_sejahtera.pdf', '163==>DANA SEJAHTERA (NEW)', 'dana_sejahtera.png' );" onmouseover="previewImg( 'dana_sejahtera.png' );" onmouseout="restoreImg();"/></a>--%>
        <%--</c:if>--%>
        <%--<c:if test="${'153' == lsbsList}">--%>
            <%--<a href="#" style="margin-left: 20px;"><img id="img3" src="${path}/include/images/products/ekalink80plus_syariah.png" alt="Ekalink 80 Plus Syariah" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'ekalink_80p_syariah.pdf', '153==>EXCELLINK 80 PLUS SYARIAH', 'ekalink80plus_syariah.png' );" onmouseover="previewImg( 'ekalink80plus_syariah.png' );" onmouseout="restoreImg();"/></a>--%>
        <%--</c:if>--%>
        <%--<c:if test="${'159' == lsbsList}">--%>
            <%--<a href="#" style="margin-left: 20px;"><img id="img4" src="${path}/include/images/products/ekalink_family.png" alt="Eka Link Family" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'ekalink_family.pdf', '159==>EKALINK FAMILY', 'ekalink_family.png' );" onmouseover="previewImg( 'ekalink_family.png' );" onmouseout="restoreImg();"/></a>--%>
        <%--</c:if>--%>
        <%--<c:if test="${'183' == lsbsList}">--%>
            <%--<a href="#" style="margin-left: 20px;"><img id="img5" src="${path}/include/images/products/ekasehat.png" alt="Eka Sehat" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'eka_sehat.pdf', '183' );"/></a>--%>
        <%--</c:if>--%>
        <%--<c:if test="${'184' == lsbsList}">--%>
            <%--<a href="#" style="margin-left: 20px;"><img id="img8" src="${path}/include/images/products/stablesave.png" alt="Stable Save" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'stable_save.pdf', '184' );"/></a>--%>
        <%--</c:if>--%>
        <%--<c:if test="${'134' == lsbsList}">--%>
            <%--<a href="#" style="margin-left: 20px;"><img id="img9" src="${path}/include/images/products/belum_ada.png" alt="Platinum Link" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'kosong.pdf', '134' );"/></a>--%>
        <%--</c:if>--%>
        <c:if test="${'140' == lsbsList}">
            <a href="#" style="margin-left: 20px;"><img id="img10" src="${path}/include/images/products/medivest.png" alt="Medivest" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'medivest.pdf', '140==>MEDIVEST', 'medivest.png' );" onmouseover="previewImg( 'medivest.png' );" onmouseout="restoreImg();"/></a>
        </c:if>
        <c:if test="${'141' == lsbsList}">
            <a href="#" style="margin-left: 20px;"><img id="img11" src="${path}/include/images/products/eduvest.png" alt="Eduvest" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'eduvest.pdf', '141==>EDUVEST', 'eduvest.png' );" onmouseover="previewImg( 'eduvest.png' );" onmouseout="restoreImg();"/></a>
        </c:if>
        <c:if test="${'164' == lsbsList}">
            <a href="#" style="margin-left: 20px;"><img id="img7" src="${path}/include/images/products/stablelink.png" alt="Stabil Link" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'stable_link.pdf', '164==>STABLE LINK', 'stablelink.png' );" onmouseover="previewImg( 'stablelink.png' );" onmouseout="restoreImg();"/></a>
        </c:if>
        <c:if test="${'96' == lsbsList}">
            <a href="#" style="margin-left: 20px;"><img id="img12" src="${path}/include/images/products/multi_invest.png" alt="Multi Invest" style="width: 50px; height: 100px; border-color: black; border-width: 1px;" onmousedown="doAction( this.id, 'multi_invest_iii.pdf', '96==>MULTI INVEST', 'multi_invest.png' );" onmouseover="previewImg( 'multi_invest.png' );" onmouseout="restoreImg();"/></a>
        </c:if>
        
        
        
        
        
 </c:forEach>                
</div>

</body>

</html>