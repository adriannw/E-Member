<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%--
/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: ${NAME}
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 19, 2007 1:49:51 PM
 *
 * Update history   Re-fix date      Person in charge      Description
 *
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/
--%>

<%@ include file="/include/taglibs.jsp" %>

<html>

<head>
</head>

<body>

<br/>
<br/>
Loading Proposal PDF, Please Wait

<form:form id="formdownload" commandName="cepr01030000HoldingForm">
</form:form>

<c:forEach var="message" items="${messageBoxList}" varStatus="xt">
    <script type="text/javascript">
        alert( '${message}' );
    </script>
</c:forEach>

<c:forEach var="messageEkaSehat" items="${messageEkaSehatList}" varStatus="xt">
    <script type="text/javascript">
        alert( '${messageEkaSehat}' );
    </script>
</c:forEach>

<script type="text/javascript">
    document.getElementById('formdownload').action='${downloadUrlSession}';
    document.getElementById('formdownload').submit();
    document.getElementById('formdownload').target = '_self';
</script>

</body>

</html>