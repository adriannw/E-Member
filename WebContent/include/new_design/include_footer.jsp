<%@ include file="/include/new_design/taglibs.jsp"%><c:set var="path" value="${pageContext.request.contextPath}" />
			<div id="footerContainer" class="alignCenter">

				<!-- Column 1 -->
				<div class="footerColumn flexHeight">
					<h4 class="helvetica20 white">Main Office</h4>
					<p class="verdana11 lightGrey">
						<span class="white">PT Asuransi Jiwa Sinarmas MSIG Tbk.</span>
						<br/>Wisma Eka Jiwa Lt. 8, 
						<br/>Jl. Mangga Dua Raya, 
						<br/>Jakarta 10730 - Indonesia
					</p>
					<p class="verdana11 lightGrey">
						Telp: (021) 6257808
						<br/>Fax: 62301711, 6257837
						<br/>Customer Service: (021) 6257838
						<br/>Telp Bebas Pulsa: 0-800-1401217
					</p>
					<p class="verdana11 lightGrey">
						Website: <a href="http://www.sinarmasmsiglife.co.id" class="white" title="Company website">www.sinarmasmsiglife.co.id</a>
						<br/>Email: <span class="white">cs @ sinarmasmsiglife.co.id</span> 
					</p>
				</div>
	
				<!-- Column 2 -->
				<div class="footerColumn flexHeight">
					<h4 class="helvetica20 white">Socialize With Us</h4>
					<ul class="verdana11 italic">
						<li><img alt="Facebook" src="${param.path}/include/new_image/bt_fb.png" width="26" height="26" />&nbsp;&nbsp;&nbsp;<a href="http://www.facebook.com/pages/Sinarmas-MSIG-Life/225036777542165?ref=hl" target="_blank" title="Like us on Facebook" class="linkFB white noUnderline">Like us on Facebook</a></li>
						<li><img alt="Twitter" src="${param.path}/include/new_image/bt_twitter.png" width="26" height="26" />&nbsp;&nbsp;&nbsp;<a href="http://twitter.com/@ajsmsig" target="_blank" title="Follow us on Twitter" class="linkTwitter white noUnderline">Follow us on Twitter</a></li>
					</ul>
				</div>
	
				<!-- Column 3 -->
				<div class="footerColumn flexHeight">
					<h4 class="helvetica20 white">Training Schedule</h4>
					
					<c:forEach items="${sessionScope.currentUser.training }" var="t" begin="0" end="4">
						<p class="verdana11 lightGrey">
							${t.date }
							<br/><a class="white noUnderline" href="${param.path }/viewer/view_schedule.htm?id=${t.id}" title="View">${t.judul}</a>
						</p>
					</c:forEach>
					
					<!-- <p class="verdana11 lightGrey">
						27 Februari 2011
						<br/><a class="white noUnderline" href="#" title="View">Jadwal Agency Nasional 2011</a>
					</p>
					
					<p class="verdana11 lightGrey">
						27 Februari 2011
						<br/><a class="white noUnderline" href="#" title="View">Jadwal Agency Nasional 2011</a>
					</p>
					
					<p class="verdana11 lightGrey">
						27 Februari 2011
						<br/><a class="white noUnderline" href="#" title="View">Jadwal Agency Nasional 2011</a>
					</p>
					
					<p class="verdana11 lightGrey">
						27 Februari 2011
						<br/><a class="white noUnderline" href="#" title="View">Jadwal Agency Nasional 2011</a>
					</p> -->
					
					<a class="verdana11 white noUnderline" href="${param.path }/viewer/all_schedule.htm" title="View All Training Schedule">View All Training Schedule</a>
					
				</div>
	
				<!-- Column 4 -->
				<div class="footerColumn flexHeight">
					<h4 class="helvetica20 white">News Archive</h4>

					<br/>
	
					<!-- News Archive -->
					<c:forEach items="${sessionScope.currentUser.bln_news}" var="n" begin="0" end="4">
						<a class="lightGrey verdana11 noUnderline" href="${param.path }/viewer/news_archieve.htm?bln=${n.bulan}" title="${n.bulan }">${n.bulan }</a>
						<div class="horizontalDivider horizontalDivider2"></div>
					</c:forEach>
				</div>
				
				<!-- Garis -->
				<div class="footerLine">&nbsp;</div>
				
				<!-- Copyright line -->
				<div class="footerCopyright verdana11 lightGrey">
					<span class="white">PT Asuransi Jiwa Sinarmas MSIG Tbk.</span> © 2012 | All Right Reserved
				</div>
			</div>