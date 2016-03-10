<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <style type="text/css"> --%>
	
<%-- </style> --%>
<div id="sessionTimeoutModal" class="modal"  data-backdrop="static" style="z-index: 10000">
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
        <s:form id="sessionPopupForm" action="disclaimer_logout" namespace="/eligibility" method="get">
        <div>
        	<s:token></s:token>
            <input id="sessionPopupSubmit" accesskey="l" type="submit" value='<s:text name="msg.sessiontimeout.logout"/>' class="btn btn-active btnpad"/>
          <!--   methods stopEvent() and refreshSessionTimer() are defined in application.js -->
            <a accesskey="t" href="#" class="btn btn-primary bold" onclick="(function(evt){stopEvent(evt); refreshSessionTimer();})(event)"> <s:text name="msg.sessiontimeout.continue"/></a>
       </div>
        </s:form>
    </div>
</div>
</div>
</div> 

<%-- <div id="sessionTimeoutModal" class="modal" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><s:text name="hix.header.sessiontimeout.warning1"/></h4>
      </div>
      <div class="modal-body">
           <p>
            Warning 2&nbsp;<span id="timeoutCountdown"></span>
       		</p>
      </div>
      <div class="modal-footer">
         <form id="sessionPopupForm" action="/cds/eligibility/disclaimer_" method="post">
        	<s:token></s:token>
            <input id="sessionPopupSubmit" accesskey="l" type="submit" value='Log Out' style="padding:6px 12px;" class="btn btn-active">
           <!--  methods stopEvent() and refreshSessionTimer() are defined in application.js -->
            <a accesskey="t" href="#" class="btn btn-primary bold" onclick="(function(evt){stopEvent(evt); refreshSessionTimer();})(event)"> Continue</a>
        </form>
      </div>
    </div>

  </div>
</div> --%>

<div class="page-id-marker hidden no-page-id">sessionTimeoutModal</div>
