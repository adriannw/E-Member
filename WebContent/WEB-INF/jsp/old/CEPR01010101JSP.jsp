<%--
/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01010101
 * Program Name   		:
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 26, 2008 2:37:36 PM
 *
 * Update history   Re-fix date      Person in charge      Description
 *
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/
--%>

<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core" %>
<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Welcome to E-Proposal</title>

    <script type="text/javascript">
        function changeButtonOkToOrange()
        {
            document.getElementById( "buttonOkId" ).src = '${path}/include/images/button/ok-ro.gif';
        }
        function changeButtonOkToNormal()
        {
            document.getElementById( "buttonOkId" ).src = '${path}/include/images/button/ok.gif';
        }
        function doLogin()
        {
            document.forms[0].submit();
        }
    </script>
</head>

<body>

<center>

<form:form id="formpost" commandName="cepr01010101Form">
    <table>
        <tr>
            <td style="text-align: center;">
                <img src="${path}/include/images/logo_sinarmas_msig_life.png" alt="" style=""/>
            </td>
        </tr>
        <tr>
            <td style="text-align: center;">
                <table cellspacing="0">
                    <tr style="height: 40px;">
                        <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-top-lef.gif');">&nbsp;</td>
                        <td style="padding: 0; width: 700px; background-image: url('${path}/include/images/accessories/bor-log-top-cen.gif');">&nbsp;</td>
                        <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-top-rig.gif');">&nbsp;</td>
                    </tr>
                    <tr style="height: 50px;">
                        <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-cen-lef.gif');">&nbsp;</td>
                        <td style="padding: 0; width: 700px; background-image: url('${path}/include/images/accessories/blank.gif'); text-align: right;">&nbsp;</td>
                        <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-cen-rig.gif'); text-align: left;">&nbsp;</td>
                    </tr>
                    <tr>
                        <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-cen-lef.gif');">&nbsp;</td>
                        <td colspan="2" style="padding: 0; width: 800px;">
                            <table cellspacing="0">
                                <tr>
                                    <td style="padding: 0; width: 450px; vertical-align: top; padding-top: 35px;">
                                        <img src="${path}/include/images/logo_login.gif" alt="" style=""/>
                                    </td>
                                    <td style="padding: 0; width: 350px;">
                                        <table cellspacing="0">
                                            <tr style="height: 30px;">
                                                <td style="padding: 0; width: 10px; background-image: url('${path}/include/images/accessories/blank.gif');">&nbsp;</td>
                                                <td style="padding: 0; width: 240px; background-image: url('${path}/include/images/accessories/blank.gif'); text-align: right;">&nbsp;</td>
                                                <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-cen-rig.gif');">&nbsp;</td>
                                            </tr>
                                            <tr style="height: 30px;">
                                                <td style="padding: 0; width: 10px; background-image: url('${path}/include/images/accessories/blank.gif');"></td>
                                                <td style="padding: 0; width: 240px; background-image: url('${path}/include/images/accessories/blank.gif'); text-align: right;">
                                                    <span style="font-family: sans-serif; font-variant: normal; font-size: 22px; font-weight: 300; color: #9A9A9A;">user</span>
                                                </td>
                                                <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-cen-rig.gif'); text-align: left;">
                                                    <span style="font-family: sans-serif; font-variant: normal; font-size: 22px; font-weight: 300; color: #676767;">login</span>
                                                </td>
                                            </tr>
                                            <tr style="height: 10px;">
                                                <td style="padding: 0; width: 10px; background-image: url('${path}/include/images/accessories/blank.gif');"></td>
                                                <td style="padding: 0; width: 240px; background-image: url('${path}/include/images/accessories/bor-log-top-cen-box.gif');"></td>
                                                <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-top-rig-box.gif');"></td>
                                            </tr>
                                            <tr style="height: 100px;">
                                                <td style="padding: 0; width: 10px; background-image: url('${path}/include/images/accessories/bor-log-cen-lef-box.gif');"></td>
                                                <td style="padding: 0; width: 340px; background-image: url('${path}/include/images/accessories/bor-log-in-the-box.gif'); " colspan="2">
                                                    <table cellspacing="0">
                                                        <tr>
                                                            <td>
                                                                <table cellspacing="0">

                                                                    <!--START FILL LOGIN-->
                                                                    <tr style="display: ${cepr01010101Form.loginFormDisplay}; height: 35px; vertical-align: middle;">
                                                                        <td style="width: 80px; text-align: right;">
                                                                            <span style="font-family: sans-serif; font-variant: normal; font-size: 18px; font-weight: 300; color: #737373; text-align: right;">
                                                                                account
                                                                            </span>
                                                                        </td>
                                                                        <td style="padding-left: 15px;" align="left">
                                                                            <form:input path="account" cssStyle="border-style: groove; width:145px; border-width: 3px;" onkeypress="checkEnter(event, 'formpost');"/>
                                                                        </td>
                                                                    </tr>
                                                                    <tr style="display: ${cepr01010101Form.loginFormDisplay}; height: 35px; vertical-align: middle;">
                                                                        <td style="width: 80px; text-align: right;">
                                                                            <span style="font-family: sans-serif; font-variant: normal; font-size: 18px; font-weight: 300; color: #737373; text-align: right;">
                                                                                password
                                                                            </span>
                                                                        </td>
                                                                        <td style="padding-left: 15px;" align="left">
                                                                            <form:password path="password" cssStyle="border-style: groove; width:145px; border-width: 3px;" onkeypress="checkEnter(event, 'formpost');"/>
                                                                        </td>
                                                                    </tr>

                                                                    <!--START FILL NEW PASSWORD-->
                                                                    <tr style="display: ${cepr01010101Form.newPasswordFormDisplay}; height: 35px; vertical-align: middle;">
                                                                        <td colspan="2" style="text-align: left;">
                                                                            <span style="font-family: sans-serif; font-variant: normal; font-size: 18px; font-weight: 300; color: #737373; text-align: right;">
                                                                                Please fill new password:
                                                                            </span>
                                                                        </td>
                                                                    </tr>
                                                                    <tr style="display: ${cepr01010101Form.newPasswordFormDisplay}; height: 35px; vertical-align: middle;">
                                                                        <td style="width: 80px; text-align: right;">
                                                                            <span style="font-family: sans-serif; font-variant: normal; font-size: 18px; font-weight: 300; color: #737373; text-align: right;">
                                                                                New Pwd
                                                                            </span>
                                                                        </td>
                                                                        <td style="padding-left: 15px;">
                                                                            <form:password path="newPassword1" cssStyle="border-style: groove; border-width: 3px;" onkeypress="checkEnter(event, 'formpost');"/>
                                                                        </td>
                                                                    </tr>
                                                                    <tr style="display: ${cepr01010101Form.newPasswordFormDisplay}; height: 35px; vertical-align: middle;">
                                                                        <td style="width: 80px; text-align: right;">
                                                                            <span style="font-family: sans-serif; font-variant: normal; font-size: 18px; font-weight: 300; color: #737373; text-align: right;">
                                                                                Confirm
                                                                            </span>
                                                                        </td>
                                                                        <td style="padding-left: 15px;">
                                                                            <form:password path="newPassword2" cssStyle="border-style: groove; border-width: 3px;" onkeypress="checkEnter(event, 'formpost');"/>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                            <td style="padding-left: 15px; padding-top: 4px;">
                                                                <a href="#" onclick="doLogin();" onmouseover="changeButtonOkToOrange();" onmouseout="changeButtonOkToNormal();">
                                                                    <img src="${path}/include/images/button/ok.gif" alt="" style="border: 0;" id="buttonOkId"/>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr style="height: 10px;">
                                                <td style="padding: 0; width: 10px; background-image: url('${path}/include/images/accessories/bor-log-bot-lef-box.gif');"></td>
                                                <td style="padding: 0; width: 240px; background-image: url('${path}/include/images/accessories/bor-log-bot-cen-box.gif');"></td>
                                                <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-bot-rig-box.gif');"></td>
                                            </tr>
                                            <tr style="height: 20px;">
                                                <td style="padding: 0; width: 350px; background-image: url('${path}/include/images/accessories/bor-log-ajs-info.gif'); text-align: right;" colspan="3"><div align="left">
                                                	<span style="font-family: sans-serif; font-variant: normal; font-size: 12px; font-weight: 300; color: #737373; text-align: right; padding-right: 30px;">
                                                		&nbsp&nbsp&nbsp&nbsp Sinarmas MSIG Life IT Department [${sessionScope.deebee}]
                                                	</span>
                                                </div></td>
                                            </tr>
											
                                            <spring:hasBindErrors name="cepr01010101Form">
                                                <c:forEach items="${errors.allErrors}" var="error">
                                                    <tr style="height: 15px;">
                                                        <td style="padding-left: 14px; width: 350px; background-image: url('${path}/include/images/accessories/bor-log-ajs-info.gif'); text-align: left;" colspan="3">
                                                            <span style="font-family: sans-serif; font-variant: normal; font-size: 12px; font-weight: 300; color: red; text-align: right; padding-right: 30px;">
                                                                <spring:message code="${error.code}" text="${error.defaultMessage}"/>
                                                            </span>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </spring:hasBindErrors>
                                            ${loginErr}
                                            <tr style="height: 80px;">
                                                <td style="padding: 0; width: 350px; background-image: url('${path}/include/images/accessories/bor-log-ajs-info.gif'); text-align: right;" colspan="3">&nbsp;
                                                    
                                                </td>
                                            </tr>
                                            <tr style="height: 15px;">
                                                <td style="padding: 0; width: 350px; background-image: url('${path}/include/images/accessories/bor-log-ajs-info.gif'); text-align: right;" colspan="3">
                                                    <span style="font-family: sans-serif; font-variant: normal; font-size: 12px; font-weight: 300; color: #737373; text-align: right; padding-right: 30px;">
                                                        Head Office: Wisma Eka Jiwa Lantai 8
                                                    </span>
                                                </td>
                                            </tr>
                                            <tr style="height: 15px;">
                                                <td style="padding: 0; width: 350px; background-image: url('${path}/include/images/accessories/bor-log-ajs-info.gif'); text-align: right;" colspan="3">
                                                    <span style="font-family: sans-serif; font-variant: normal; font-size: 12px; font-weight: 300; color: #737373; text-align: right; padding-right: 30px;">
                                                        Jl. Mangga Dua Raya, Jakarta 10730
                                                    </span>
                                                </td>
                                            </tr>
                                            <tr style="height: 15px;">
                                                <td style="padding: 0; width: 350px; background-image: url('${path}/include/images/accessories/bor-log-ajs-info.gif'); text-align: right;" colspan="3">
                                                    <span style="font-family: sans-serif; font-variant: normal; font-size: 12px; font-weight: 300; color: #737373; text-align: right; padding-right: 30px;">
                                                        Telp: (021) 6257808 (Hunting), Fax: (021) 6257837
                                                    </span>
                                                </td>
                                            </tr>
                                            <tr style="height: 15px;">
                                                <td style="padding: 0; width: 350px; background-image: url('${path}/include/images/accessories/bor-log-ajs-info.gif'); text-align: right;" colspan="3">
                                                    <span style="font-family: sans-serif; font-variant: normal; font-size: 12px; font-weight: 300; color: #737373; text-align: right; padding-right: 30px;">
                                                        Customer Service: (021) 6257838, 26508300
                                                    </span>
                                                </td>
                                            </tr>
                                            <tr style="height: 15px;">
                                                <td style="padding: 0; width: 350px; background-image: url('${path}/include/images/accessories/bor-log-ajs-info.gif'); text-align: right;" colspan="3">
                                                    <span style="font-family: sans-serif; font-variant: normal; font-size: 12px; font-weight: 300; color: #737373; text-align: right; padding-right: 30px;">
                                                        Website: www.sinarmasmsiglife.com
                                                    </span>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr style="height: 20px;">
                        <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-bot-lef.gif');">&nbsp;</td>
                        <td style="padding: 0; width: 700px; background-image: url('${path}/include/images/accessories/bor-log-bot-cen.gif');">&nbsp;</td>
                        <td style="padding: 0; width: 100px; background-image: url('${path}/include/images/accessories/bor-log-bot-rig.gif');">&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td style="text-align: center; vertical-align: top; padding-top: 10px;">
                <span style="font-family: sans-serif; font-variant: normal; font-size: 11px; font-weight: 300; color: #737373; text-align: right; padding-right: 30px;">
                    Copyright &copy 2008 PT. Asuransi Jiwa Sinarmas MSIG. All Right Reserved.
                </span>
            </td>
        </tr>
    </table>
</form:form>
</center>

<script type="text/javascript">
     document.getElementById( 'account' ).focus();
     var user = '${user}';
     var pass = '${pass}';
     if(user != '' && pass != ''){
     	document.getElementById( "account" ).value = user;
     	document.getElementById( "password" ).value = pass;
     	doLogin();
     }
</script>
</body>
</html>