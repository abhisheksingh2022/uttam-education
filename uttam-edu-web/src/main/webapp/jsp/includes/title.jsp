<%@page import="com.uttam.framework.common.env.IEnvironmentProvider"%>
<%@page import="com.uttam.framework.service.ServiceLocator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<header>
	<div class="container maincont border_bottom">
		<div class="row">
			<div class="logopart">
				<a href="/pmp/main/home_input" tabindex="1"><img alt="<s:text name="txt.global.title"/>"
					src="<s:property value='rootPath'/>static/custom/images/logo.png" id="logo" /> </a>
				<br>
				<%if(!ServiceLocator.getInstance().getService(IEnvironmentProvider.class).isProduction()) {%>
					<b>TEST ENVIRONMENT</b>
				<%} %>
			</div>

			<div class="top-right">
				<s:if test="user != null">
					<p>
						<s:text name="txt.global.welcome"/> <span><s:property value="user.fullName" /> <s:if test="user.orgName.length() > 0">
						(<s:property value="user.orgName" />)</s:if></span>
					</p>
					<s:a action="logout_input" namespace="/main" class="logout"><s:text name="txt.global.logout"/></s:a>
				</s:if>
			</div>
		</div>
	</div>
</header>
<s:if test="user != null">
	<div class="container maincont">
		<div class="header-bottom">
			<ul>
				<%-- <li class="workicon"><s:a action="work-items_input" namespace="/myaccount">Work Item</s:a></li>
					<li class="notification"><s:a action="notifications_input" namespace="/myaccount">Notification</s:a></li>
					<li class="preferences"><s:a action="preferences_input" namespace="/myaccount">Preferences</s:a></li> --%>
			</ul>
		</div>
	</div>

	<%--Menu TODO Access Based--%>
	<div class="menu">
		<div class="container maincont">
			<div class="row">
				<!-- <a class="toggleMenu" href="#">Menu</a>

				<ul class="nav"> -->
				<div class="navbar-collapse collapse">
                 <ul class="nav navbar-nav">
					<li><s:a action="home_input" namespace="/main"><s:text name="txt.global.home"/></s:a></li>
					<s:if
						test="isUserAllowedToAccessAction('/usermgmt/carrier-search_input') || isUserAllowedToAccessAction('/usermgmt/carrieruser-search_input')">
						<li><a href="#"><s:text name="txt.global.carriermgmt"/></a>
							<ul class="dropdown-menu">
								<s:if test="isUserAllowedToAccessAction('/usermgmt/carrier-search_input')">
									<li><s:a action="carrier-search_input" namespace="/usermgmt"><s:text name="txt.global.carrierprofile"/></s:a></li>
								</s:if>
								<s:if test="isUserAllowedToAccessAction('/usermgmt/carrieruser-search_input')">
									<li><s:a action="carrieruser-search_input" namespace="/usermgmt"><s:text name="txt.global.carrieraccesst"/></s:a></li>
								</s:if>
							</ul></li>
					</s:if>
					<s:if
						test="isUserAllowedToAccessAction('/planmgmt/plan-upload_input') || isUserAllowedToAccessAction('/planmgmt/plan-search_input')">
					
					<li><a href="#"><s:text name="txt.global.plandatamgmt"/></a>
						<ul class="dropdown-menu">
							<s:if
								test="isUserAllowedToAccessAction('/planmgmt/plan-search_input')">
							<li><s:a action="plan-search_input" namespace="/planmgmt"><s:text name="txt.global.manageplan"/></s:a></li>
							</s:if>
							<s:if
								test="isUserAllowedToAccessAction('/planmgmt/plan-upload_input')">
							<li><s:a action="plan-upload_input" namespace="/planmgmt"><s:text name="txt.global.uploadplan"/></s:a></li>
							</s:if>
							<s:if
								test="isUserAllowedToAccessAction('/prescreen/prescreening_input')">
							<li><s:a action="prescreening_input" namespace="/prescreen"><s:text name="txt.global.prescreening"/></s:a></li>
							</s:if>
							<s:if
								test="isUserAllowedToAccessFunction('PLAN_TRANSLATED_UPLOAD')">
							<li><s:a action="plan-excel_input" namespace="/planmgmt"><s:text name="txt.global.uploadplantranslation"/></s:a></li>
							</s:if>
						</ul></li>
					</s:if>
					<s:if
								test="isUserAllowedToAccessAction('/report/loginhistory-report_input') || 
								isUserAllowedToAccessAction('/report/planhistory-report_input')"
								> 
					<li><a href="#"><s:text name="txt.global.reports"/></a>
							<ul class="dropdown-menu"><s:if
								test="isUserAllowedToAccessAction('/report/loginhistory-report_input')"> 
								<li><s:a action="loginhistory-report_input" namespace="/report"><s:text name="txt.global.loginhistoryreport"/></s:a></li>
								</s:if>
								<s:if
								test="isUserAllowedToAccessAction('/report/planhistory-report_input')"> 
								<li><s:a action="planhistory-report_input" namespace="/report"><s:text name="txt.global.planhistoryreports"/></s:a></li>
								 </s:if> 
							</ul></li>
						</s:if>
						<%--<li><a href="#">Administration</a>
							<ul class="dropdown-menu">
								<li><li><s:a action="notifications_input" namespace="/admin">Notifications Configuration</s:a></li>

							</ul></li> --%>
				</ul>
				</div>
			</div>
		</div>
	</div>
</s:if>