<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
	.padtop5 {
	    padding-top: 5px;
	 } 
	.head3 {
	    font-family: 'arial';
	    font-weight: bold;
	    font-size: 18px;
	    color: #545454;
	    font-style: normal;
	    margin-bottom: 10px;
	    margin-top: 10px;
	}
.btn-primary_warn {
	background: #ff7c00 none repeat scroll 0% 0%;
	color: #FFF;
}

.btn-primary_warn:hover {
	background: #F16527;
	color: #FFF;
}
#timeoutCountdown{
	color:red;
	font-weight: bold;
}
</style>
<div id="sessionTimeoutModal" class="modal"  data-backdrop="static" style="z-index: 10000;">
<div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
    <div class="modal-header padtop5">
        <div class="head3"><s:text name="msg.sessiontimeout.warning1"/></div>
    </div>
    <div class="modal-body">
        <p>
            <s:text name="msg.sessiontimeout.warning2"/>&nbsp;<span id="timeoutCountdown"></span>
        </p>
        <p>
           <s:text name="msg.sessiontimeout.warning3"/>
        </p>
    </div>
    <div class="modal-footer">
        <s:form autocomplete="off" id="sessionPopupForm" action="logout_input" namespace="/main" method="get">
        	<s:token></s:token>
            <input id="sessionPopupSubmit" accesskey="l" type="submit" value='<s:text name="msg.sessiontimeout.logout"/>' style="padding:6px 12px;" class="btn btn-primary_warn">
          <!--   methods stopEvent() and refreshSessionTimer() are defined in application.js -->
            <a accesskey="t" href="#" class="btn btn-primary_warn" onclick="(function(evt){stopEvent(evt); refreshSessionTimer();})(event)"> <s:text name="msg.sessiontimeout.continue"/></a>
        </s:form>
    </div>
</div>
</div>
</div> 
<div class="page-id-marker hidden no-page-id">sessionTimeoutModal</div>
