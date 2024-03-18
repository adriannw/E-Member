<%@ include file="/include/taglibs.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<HEAD>
<link href="${path}/include/css/general.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
.style2 {font-size: 20px}
.style3 {color: #FF9900}
.style5 {color: #FFFFFF}
-->
</style>

 <script type="text/javascript">
    </script>

</HEAD>
<BODY style="background-color: #FFFFFF ; margin-left: 0; margin-bottom: 0; margin-right: 0; margin-top: 0;">

<form:form id="formpost" commandName="cepr01050000HoldingForm" name="">
<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>
<tr>
<th>Tahun</th>
<th>Nilai Polis Rendah</th>
<th>Nilai Polis Sedang</th>
<th>Nilai Polis Tinggi</th>
</tr>	
<tr>
<th>1</th>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisRendah1}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisSedang1}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisTinggi1}</td>
</tr>	
<tr>
<th>5</th>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisRendah5}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisSedang5}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisTinggi5}</td>
</tr>
<tr>
<th>10</th>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisRendah10}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisSedang10}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisTinggi10}</td>
</tr>
<tr>
<th>15</th>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisRendah15}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisSedang15}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.polisTinggi15}</td>
</tr>	
<tr>		
<th>Fund Persen</th>
<td>${cepr01050000HoldingForm.cepr01050000Form.fixIncome}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.dynamic}</td>
<td>${cepr01050000HoldingForm.cepr01050000Form.aggresive}</td>
</tr>	
</TABLE>
</form:form>

<script type="text/javascript">
   
</script>
</BODY>
</HTML>