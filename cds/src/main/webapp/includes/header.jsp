<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<%
	if (!response.isCommitted()) { // close curly brace at end of this file

		response.setHeader("X-UA-Compatible", "IE=edge");
		response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
		response.addHeader("Cache-Control", "no-store"); //HTTP 1.1
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0
		response.setDateHeader("Expires", 0); //prevents caching at the proxy server
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		
		response.setHeader("X-Content-Type-Options", "nosniff");
		response.setHeader("X-XSS-Protection", "1; mode=block");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "private");
	}
%>
<title><s:text name="txt.global.title" /></title>
<s:set var="rootPath" value="contextRoot" />

<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="Content-Script-Type" content="text/javascript"/>
<script type="text/javascript">
	var maxInactiveInterval = <%=session.getMaxInactiveInterval()%>;
</script>
<script type="text/javascript" src="<s:property value='rootPath'/>static/custom/js/jquery-1.11.2.min.js?v=<s:property value="buildId"/>"></script>
<script type="text/javascript" src="<s:property value='rootPath'/>static/custom/js/jquery-mask.js?v=<s:property value="buildId"/>"></script>
<script type="text/javascript" src="<s:property value='rootPath'/>static/custom/js/jquery-form.js?v=<s:property value="buildId"/>"></script>
<script type="text/javascript" src="<s:property value='rootPath'/>static/custom/js/jquery-blockui.js?v=<s:property value="buildId"/>"></script>
<script src="<s:property value='rootPath'/>static/custom/js/i18n/grid.locale-en.js?v=<s:property value="buildId"/>" type="text/javascript"></script>
<script src="<s:property value='rootPath'/>static/custom/js/hixapi.js?v=<s:property value="buildId"/>" type="text/javascript"></script>
<script src="<s:property value='rootPath'/>static/custom/js/jquery.alphanum.js?v=<s:property value="buildId"/>" type="text/javascript"></script>
<script src="<s:property value='rootPath'/>static/custom/js/jquery-ui.js?v=<s:property value="buildId"/>" type="text/javascript" ></script>
<script src="<s:property value='rootPath'/>static/custom/js/jquery.accordion.source.js?v=<s:property value="buildId"/>" type="text/javascript" ></script>
<script type="text/javascript" src="<s:property value='rootPath'/>static/custom/js/tablesorter/jquery.tablesorter.js?v=<s:property value="buildId"/>"></script>
<script type="text/javascript" src="<s:property value='rootPath'/>static/custom/js/tablesorter/jquery.tablesorter.min.js?v=<s:property value="buildId"/>"></script>

<script src="<s:property value='rootPath'/>static/custom/js/modernizr.custom.js?v=<s:property value="buildId"/>" type="text/javascript"></script>
<script type="text/javascript" src="<s:property value='rootPath'/>static/custom/js/bootstrap.min.js?v=<s:property value="buildId"/>"></script>
<script type="text/javascript" src="<s:property value='rootPath'/>static/custom/js/jquery.selectbox-0.2.js?v=<s:property value="buildId"/>"></script>
<link href="<s:property value='rootPath'/>static/custom/css/bootstrap.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css" />
<link href="<s:property value='rootPath'/>static/custom/css/bootstrap.min.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css" />
<link href="<s:property value='rootPath'/>static/custom/css/style.css?v=<s:property value="buildId"/>" type="text/css" rel="stylesheet" />
<link href="<s:property value='rootPath'/>static/custom/css/responsive.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css" />
<link href="<s:property value='rootPath'/>static/custom/css/font-awesome.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css" />
<link href="<s:property value='rootPath'/>static/custom/css/font-awesome.min.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="<s:property value='rootPath'/>static/custom/css/jquery-ui.css?v=<s:property value="buildId"/>" />
<!-- Table Sorter -->
<link href="<s:property value='rootPath'/>static/custom/css/style_tableSorter.css?v=<s:property value="buildId"/>" type="text/css" rel="stylesheet" />


<!--Selection Api -->
<link href="<s:property value='rootPath'/>static/custom/css/jquery.selectbox.css?v=<s:property value="buildId"/>" type="text/css" rel="stylesheet" />
<!-- Formatting Values for costs -->
<script
	src="<s:property value='rootPath'/>static/custom/js/accounting.js?v=<s:property value="buildId"/>"></script>

<%-- <script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script> --%>
<script>
  $(document).ready(function(){
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

	 /*  ga('create', '<s:property value="getAnalyticsAPIKey()"/>', {'cookieDomain':'none'}); */
	  ga('create', '<s:property value="getAnalyticsAPIKey()"/>', 'auto');
	  ga('send', 'pageview');
	  
	  if(isIE()){
		 	$('.modal-lg').css('width','900px');
			$('.modal-lg').addClass('modal-center'); 
		}
  });
 
  function isIE () {
	  var myNav = navigator.userAgent.toLowerCase();
	 
	  return (myNav.indexOf('msie') != -1) ? parseInt(myNav.split('msie')[1]) : false;
	}
	
</script>

</head>
