<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/jsp/includes/container.jsp"%>
 <div class="body-con">
    	<div class="container maincont">
        	<div class="row">
            	<div id="form_block_id" class="body-bg">
            	<div class="body-leftbg">
<div class="leftcon-bg" id="notifications">
<img class="centeredLoading" src="<s:property value='contextRoot'/>static/custom/images/small_loader.gif" />
</div>

<div  class="leftcon-bg mytask" id="workItems">
<img class="centeredLoading" src="<s:property value='contextRoot'/>static/custom/images/small_loader.gif" />
</div>         
</div>         
                <div class="rightcon-bg">
                	<h2><s:text name="msg.homepage.welcome"/></h2>
                	<div class="home-logo" align="center">
                		<img src="<s:property value='contextRoot'/>static/custom/images/home-logo-2.png" />
                	</div>
                   <%--  <h3>PMP allows you to:</h3>
                    <ul>
                    <br>
                    	<li><s:a action="carrier-search_input" namespace="/usermgmt">Create/Update Carrier's Profile</s:a></li>
                       <li><s:a action="plan-search_input" namespace="/planmgmt">View Plans</s:a></li>
                       <li><s:a action="plan-search_input" namespace="/planmgmt">Edit Plans</s:a></li>
                       <li><s:a action="carrier-search_input" namespace="/usermgmt">Manage Tasks</s:a></li>
                    </ul> --%>
                </div>
                <div class="clearfix"></div>
               
            </div>
            </div>        
        </div>
        </div>
<%@include file="/jsp/includes/footer.jsp"%>
<script type="text/javascript">
$(document).ready(
function() {
loadNotifications();
loadWorkItems();

});

function loadNotifications()
{
var notificationUrl = "home_ajaxLoadNotificationsWidget";
ajaxGet({resource:notificationUrl,targetId:'notifications',urlparams:{}});

}
function loadWorkItems()
{
var workItemUrl = "home_ajaxLoadWorkItemsWidget";
ajaxGet({resource:workItemUrl,targetId:'workItems',urlparams:{}});
}
</script>


