		<!-- Custom Scripts -->
		<script type="text/javascript" src="${param.path}/include/js/default.js"></script>
		
		<!-- jQuery -->
		<script type="text/javascript" src="${param.path}/include/new_design/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="${param.path}/include/new_design/jquery-ui.js"></script>
		
		<!-- Superfish jQuery Menu Plugin -->
		<script type="text/javascript" src="${param.path}/include/new_design/hoverIntent.js"></script> 
		<script type="text/javascript" src="${param.path}/include/new_design/superfish.js"></script> 
		<script type="text/javascript" src="${param.path}/include/new_design/supersubs.js"></script> 
		
		<!-- featureCarousel -->
		<script type="text/javascript" src="${param.path}/include/new_design/jquery.featureCarousel.min.js"></script>
		
		<!-- timepicker -->
		<script type="text/javascript" src="${param.path}/include/new_design/jquery.timepicker.min.js"></script>
		
		<script type="text/javascript" src="${param.path}/include/js/flowplayer/flowplayer-3.2.6.min.js"></script>
		
		<!-- Slider -->
		<script type="text/javascript" src="${param.path}/include/new_design/jquery.anythingslider.js"></script>
		
		<!-- Tiny Editor -->
		<script type="text/javascript" src="${param.path}/include/new_design/tinyeditor/tiny.editor.packed.js"></script>
		<script type="text/javascript" src="${param.path}/include/new_design/tinyeditor/nicEdit.js"></script>
		
		<script type="text/javascript">
			/* Init Superfish Menu */
			$(document).ready(function(){ 
				$("ul.sf-menu").supersubs({ 
					minWidth: 	1,	// minimum width of sub-menus in em units 
					maxWidth: 	27,	// maximum width of sub-menus in em units 
					extraWidth:	1	// extra width can ensure lines don't sometimes turn over due to slight rounding differences and font-family 
				}).superfish({ 		// call supersubs first, then superfish, so that subs are not display:none when measuring. Call before initialising containing tabs for same reason.
					delay:       300,		// one second delay on mouseout 
					speed:       "fast",	// faster animation speed
					autoArrows: false,
					dropShadows: false
				});	 
				
				$(".datepicker").datepicker({
					changeMonth: true,
					changeYear: true,
					dateFormat: "dd/mm/yy" 
				});
				
				//timepicker
				$(".timepicker").timepicker({ 
					'minTime': '08:00', 
					'maxTime': '20:00',
					'step': 15 ,
					'timeFormat': 'H:i'  
				});
				
				//slide poster kontes
					var carousel = $("#carousel").featureCarousel({
			          // include options like this:
			          // (use quotes only for string values, and no trailing comma after last option)
			          // option: value,
			          // option: value
			        });
			
			        $("#but_prev").click(function () {
			          carousel.prev();
			        });
			        $("#but_pause").click(function () {
			          carousel.pause();
			        });
			        $("#but_start").click(function () {
			          carousel.start();
			        });
			        $("#but_next").click(function () {
			          carousel.next();
			        });
			        
			        //slider
			        $('#slider').anythingSlider({    
						autoPlay       : true,
						autoPlayLocked : true,
						mode           : "fade",
						delay          : 6000,        
						resumeDelay    : 15000
					});
					
					$('#slider2').anythingSlider({    
						autoPlay       : true,
						autoPlayLocked : true,
						mode           : "fade",
						delay          : 6000,        
						resumeDelay    : 15000
					});
			});

			/* Global Links */
			$(".linkFB").click(function() { window.open("http://www.facebook.com/pages/Sinarmas-MSIG-Life/225036777542165"); });
			$(".linkTwitter").click(function() { window.open("http://twitter.com/ajsmsig"); });
			$(".linkSVideo").click(function() { window.open("${param.path}/common/vids.htm"); });
			$(".linkEBook").click(function() { window.open("${param.path}/ehandbook.jsp"); });
			$(".linkEProposal").click(function() { window.open("${param.path}/proposal.jsp"); });
			
		</script>