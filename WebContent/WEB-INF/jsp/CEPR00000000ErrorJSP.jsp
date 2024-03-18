<%--
/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		:
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 23, 2008 2:57:11 PM
 *
 * Update history   Re-fix date      Person in charge      Description
 *
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/
--%>

<%@ include file="/include/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
</head>

<body>
<br/>

<br/>
<table>

<c:forEach var="message" items="${errorMessageList}" varStatus="xt">
    <tr>
        <td style="color: darkred;">
            ${message}
        </td>
    </tr>
</c:forEach>
</table>
</body>

</html>