<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@include file="/includes/container.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="timeoutoccured" class="dspNon"></div>
<style>
.disclaimer-content{
	list-style-type: none;
}

.disclaimer-content li:before {
  content: "-";
  display: inline-block;
  margin-left: -1.3em; 
  width: 1.3em;
}
</style>

<%--To avoid session timeout display on disclaimer page --%>
<script>
	maxInactiveInterval = 0; 
</script>
<s:form method="get" action="disclaimer" id="id_form_disclaimer"
	namespace="/eligibility">
	<div>
	
	<%-- <s:token /> --%>
	<s:hidden name="ajaxRequest" value="false" />
	<s:hidden name="formId" />
	</div>
	<div class="col-md-12 marginmaintop">
		<div class="container maincont">
			<div class="row">
				<div class="heading">
					<h2><s:text name="txt.disclaimer.header" /></h2>
				</div>
			</div>
		
			<br />
			<div class="textcontent">
				<s:text name="txt.disclaimer.content1" />
				<br /> <br />
				<s:text name="txt.disclaimer.content2" />
				<br />
				<ul class="disclaimer-content">
					<li><s:text name="txt.disclaimer.content3" /></li>
				<%-- 	<li><s:text name="txt.disclaimer.content7" /></li> --%>
					<li><s:text name="txt.disclaimer.content4" /></li>
					<li><s:text name="txt.disclaimer.content5" /></li>
					<li><s:text name="txt.disclaimer.content6" /></li>
				</ul>
			</div>
			<br /><br />
			<table align="right">
				<tr>
					<td>
						<button
							onclick="return setActionAndSubmit('id_form_disclaimer', '');">
							<img alt="iagree" class="image firer ie-background commentable"
								src="<s:property value='rootPath'/>static/custom/images/iagree.png"
								width="14" height="14" />
							<s:text name="txt.disclaimer.button1" /> 
						</button>
					</td>
					<td>
					&nbsp;&nbsp;
						<button 
							onclick="return CloseWindow();" >
							<img alt="dontagree"
								class="image firer ie-background commentable"
								src="<s:property value='rootPath'/>static/custom/images/dontagree.png"
								width="14" height="14" />
							<s:text name="txt.disclaimer.button2" />
						</button>


					</td>
				</tr>
			</table>
			</div>
</div>
</s:form>
<script type="text/javascript">
function CloseWindow()
{
	window.close();
	return false;
}
</script>
<%@include file="/includes/footer.jsp"%>