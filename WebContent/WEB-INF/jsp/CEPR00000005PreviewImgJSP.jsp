<%@ include file="/include/taglibs.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<html>
  <head>
      <title></title>

      <script type="text/javascript">
          function gotoInputProposal()
          {
              parent.document.getElementById('theFrameSet').rows='60,*,0';
              var lastProductId = top.menuBawahFrame.lastProductId;
              top.location = '${path}/wepr01030000.htm?productId=' + lastProductId;
          }
      </script>
  </head>

  <body>


  <form action="" >
      <input type="hidden" name="productId" id="productId">
      
      <div style="margin-top: 0; margin-left: 25px;">
          <a href="#">
              <img id="previewImgId" src="${path}/include/images/products/default.png" alt="Klik disini untuk input simulasi proposal" style="width: 200px; height: 400px; border:0;" onmousedown="gotoInputProposal();"/>
          </a>
      </div>
  </form>
  
  </body>

</html>