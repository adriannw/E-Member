<%@ include file="/include/new_design/taglibs.jsp"%><c:set var="param.path" value="${pageContext.request.contextPath}" />
	<!-- Header -->
	<div id="header">
		<!-- header background -->
		<div id="headerBG"></div>
		
		<!-- menu background (merah) -->
		<div id="menuBG"></div>
		
		<!-- image link to appstore -->
		<a href="#" title="Link to Appstore">
			<img id="imgAppstore" alt="Link to Appstore" src="${param.path}/include/new_image/link_appstore.png" width="132" height="132">
		</a>
		
	</div>

	<!-- Upper Section -->
	<div id="upperSection2" class="alignCenter">
		<!-- image logo eAgency -->
		<a href="${param.path}/common/home.htm" title="Home Page">
			<img id="imgLogo" alt="SMiLe eAgency" src="${param.path}/include/new_image/logo_e-agency.png" width="232" height="48">			
		</a>

		<!-- image logo ajsmsig -->
		<img id="imgAjs" alt="PT Asuransi Jiwa Sinarmas MSIG Tbk." src="${param.path}/include/new_image/logo_ajsm.png" width="231" height="52" />

		<!-- social links -->
		
		<button id="btnFB" class="linkFB" type="button" title="Like us on Facebook"></button>
		<button id="btnTwitter" class="linkTwitter" type="button" title="Follow us on Twitter"></button>

		<!-- image logo A -->
		<img id="imgA" alt="SMiLe eAgency" src="${param.path}/include/new_image/logo_a_menu.png" width="160" height="160" />

		<!-- Menu Box (Merah) (http://stackoverflow.com/questions/9100344/pure-css-multi-level-drop-down-best-practice) -->
		<div id="menuBox">
		
			<!-- IE7 Hack untuk vertical align tengah -->
			<!--[if lt IE 8]>
			<style>
			.bubble { width: 30px; }
			.bubble div { position: absolute; top:50%; }
			.bubble div a { position: relative; top: -50%; }
			</style>
			<![endif]-->
			
			${sessionScope.currentUser.menu}
				
		</div>

	</div>
	
	<!-- Form Admin -->
	<div id="admin">
		<c:if test="${sessionScope.currentUser.admin}">
		<table id="conTable">
			<tr>
				<td class="center">
					
						<form method="post" name="formAdmin" id=formAdmin action="${param.path}/common/admin_new.htm">
							<font class="fontMenu">Masukan No Identitas : </font>
							<input readOnly="true" size="45" type="text" name="noId" id="noId" value="${noId}" >
							<input type="hidden" name="id_agent" id="id_agent" value="${id_agent}"> 
							<input onClick="popWin('${param.path}/viewer/cariId_new.htm',300,800)" 
								type="button" name="cariIdentitias" value="Cari ID" class="button">
								<input type="submit" name="login" value="Ganti Agen" class="button">
						</form>
														
				</td>
			</tr>
		</table>
		</c:if>
	</div>