var pageSubmit = false;
function setActionAndSubmit(formId, actionValue) {
	var form = document.getElementById(formId);
	
	try {

		if (form && pageSubmit == false) {
			form.formId.value = formId;
			var originalAction = form.action;
			if ('' != trim(actionValue)) {
				var index = form.action.indexOf(".action");
				if (index != -1) {
					form.action = form.action.substring(0, index);
				}
				form.action = form.action + '_' + actionValue;
			}
			pageSubmit = true;
			form.submit();
		}
	} catch (e) {
		alert(e);
	}
	return false;
}

function setActionAndSubmitGetInNewWindow(formId, actionValue) {
	var form = document.getElementById(formId);
	
	try {

		if (form && pageSubmit == false) {
			form.formId.value = formId;
			var originalAction = form.action;
			if ('' != trim(actionValue)) {
				var index = form.action.indexOf(".action");
				if (index != -1) {
					form.action = form.action.substring(0, index);
				}
				form.action = form.action + '_' + actionValue;
			}
			pageSubmit = true;
			form.method = "get";
			form.target = "_blank";
			form.submit();
			form.action = originalAction;
			form.target = "";
			form.method = "post";
		}
	} catch (e) {
		alert(e);
	}
	return false;
}

$(document).ready(function() {
	bindUI();
	activateTimeoutModal();
});


function activateTimeoutModal() {
	// Run JS on Document Load.
	if (keepSessionAlive == false) {
		startSessionTimer(); // start session timeout clock
	} else {
		// keeps session alive by intermittantly pinging the server if
		// keepSessionAlive === true
		setSessionKeeper();
	}
}
function bindUIInScope(scopeId) {
	
	
	$(scopeId + " input:disabled, select:disabled, textarea:disabled").css("background-color","#eee !important");
	
	$(scopeId + " button").button();
	$(scopeId + " input[type=submit]").button();
	$(scopeId + " .ssn").mask('999-99-9999');
	$(scopeId + " .date").mask('99/99/9999');
	$(scopeId + " .agetype").numeric({
		allowMinus : false,
		allowThouSep : false,
		allowDecSep : false, // Allow the decimal separator, default is the
								// fullstop eg 3.141
		allowLeadingSpaces : false,
		maxDigits : 3, // The max number of digits
		maxDecimalPlaces : 0, // The max number of decimal places
		maxPreDecimalPlaces : 3, // The max number digits before the decimal
									// point
		max : 120, // The max numeric value allowed
		min : 0
	});

	$(scopeId + " form").bind("keypress", function(e) {
		if (e.keyCode == 13) {
			return false;
		}
	});
	$(scopeId + ' [data-toggle="tooltip"]').tooltip({placement:"bottom", delay:{show:"200",hide:"500"}});
	$(scopeId).tooltip({
	    selector: '.tooltipAnchor'
	});
	
	$( scopeId + ' .datetype' ).datepicker({});
	
}

function bindUI() {
	
	//$("input:disabled, select:disabled, textarea:disabled").css("font-weight","bold");
	//$("input, select, textarea").css("font-weight","500");
	//$("input:disabled, select:disabled, textarea:disabled").css("color","rgb(152, 142, 142)");
	$("input:disabled, select:disabled, textarea:disabled").css("background-color","#eee !important");
	//$("input:disabled, select:disabled, textarea:disabled").css("font-weight","300");
	
	
	$("button").button();
	$("input[type=submit]").button();
	$(".ssn").mask('999-99-9999');
	$(".date").mask('99/99/9999');
	$('.phone').mask('000-000-0000');
	$('.zip').mask('00000-0000');
	$(".agetype").numeric({
		allowMinus : false,
		allowThouSep : false,
		allowDecSep : false, // Allow the decimal separator, default is the
								// fullstop eg 3.141
		allowLeadingSpaces : false,
		maxDigits : 3, // The max number of digits
		maxDecimalPlaces : 0, // The max number of decimal places
		maxPreDecimalPlaces : 3, // The max number digits before the decimal
									// point
		max : 120, // The max numeric value allowed
		min : 0
	});
	
	$(".integertype").numeric({
		allowMinus : false,
		allowThouSep : false,
		allowDecSep : false, // Allow the decimal separator, default is the
								// fullstop eg 3.141
		allowLeadingSpaces : false,
		maxDigits : 13, // The max number of digits
		maxDecimalPlaces : 0, // The max number of decimal places
		maxPreDecimalPlaces : 13, // The max number digits before the decimal
									// point
		max : 9999999999999, // The max numeric value allowed
		min : 0
	});

	$('.alphabets').keypress(function (e) {
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (str.match(/^[a-zA-Z ]*$/)) {
            return true;
        } else {
        	return false;
        }
    });

	$("form").bind("keypress", function(e) {
		if (e.keyCode == 13) {
			return false;
		}
	});
	
	$('[data-toggle="tooltip"]').tooltip({placement:"bottom", delay:{show:"200",hide:"500"}});
	$('body').tooltip({
	    selector: '.tooltipAnchor'
	});

	$('.datetype' ).datepicker({});
}

function showDialog(text) {
	$("#noteContent").dialog({
		title : "Notice",
		modal : true,
		width : 'auto',
		height : 'auto',
		resizable : false,

		open : function() {
			$("#alertNotice").html(text);
		}
	});

}

function trim(str, chars) {
	return ltrim(rtrim(str, chars), chars);
}

function ltrim(str, chars) {
	chars = chars || "\\s";
	return str.replace(new RegExp("^[" + chars + "]+", "g"), "");
}

function rtrim(str, chars) {
	chars = chars || "\\s";
	return str.replace(new RegExp("[" + chars + "]+$", "g"), "");
}

function console_log(e) {
	if (typeof console == "object") {
		console.log(e);
	}
	//alert(e);
}
function ajaxSubmit(params) {
	// {formId: 'id_form_home2', actionPath: 'ajaxPost',
	// targetId:'id_div_ajaxResult', blockId:'id_div_ajaxForm'}
	formId = params.formId;
	var form = document.getElementById(formId);

	try {

		if (form) {
			form.ajaxRequest.value = "true";
			form.formId.value = formId;
			var newAction = form.action;
			if ('' != trim(params.actionPath)) {
				var index = newAction.indexOf(".action");
				if (index != -1) {
					newAction = newAction.substring(0, index);
				}
				newAction = newAction + '_' + params.actionPath;
			}
			console_log('Submitting to :' + newAction);

			var options = {
				target : $("#" + params.targetId),
				url : newAction,
				beforeSubmit : function() {
					if (params.blockId && params.blockId != '') {
						$('#' + params.blockId).block({
							message : $("#blockDiv").html(),
						});
					}
				},
				success : function() {
					if (params.blockId && params.blockId != '') {
						$('#' + params.blockId).unblock();
					}
					
					bindUIInScope("#" + params.targetId);
//					$("#" + params.targetId).find("script").each(function(i) {
//						console_log($(this).text());
//						eval($(this).text());
//					});
					$("#" + params.targetId).show();
					
					//Update struts token throughout the page
					var updatedToken = $("#"+params.targetId+" [name=token]").val();
					console_log(updatedToken);
					$("input[name=token]").val(updatedToken);
				},
				error: function() {
					if (params.blockId && params.blockId != '') {
						$('#' + params.blockId).unblock();
					}
					$("#" + params.targetId).show();
					
				}
			};
			$("#" + formId).ajaxSubmit(options);
		}
	} catch (e) {
		console_log('Failed form submit: ' + e);
		alert('Error Occured!');
	}
	return false;
}

function ajaxGet(params) {
//	$('#' + params.blockId).block({
//		message : $("#blockDiv").html(),
//	});
	try {
		var url = params.resource;
		var target = params.targetId;
//		$("#" + target).html("");
		var urlparams = params.urlparams;
		urlparams.ajaxRequest = "true";
		//alert(url + " " + target + " " + urlparams);
		$.get(url, urlparams).done(function(data) {
			//Update struts token throughout the page
			bindUIInScope("#" + params.targetId);
			var updatedToken = $("#"+params.targetId+" [name=token]").val();
			console_log(updatedToken);
			if(updatedToken){
				$("input[name=token]").val(updatedToken);
			}
			$("#" + target).html(data);
//			$('#' + params.blockId).unblock();
			$("#" + target).show();
		}).fail(function(xhr, status, error) {
//			$('#' + params.blockId).unblock();
			console_log('Failed get:' + error);
			alert("Error Occured!");
			$("#" + target).html(xhr.responseText);
			$("#" + target).show();
		});
	} catch (e) {
		console_log('Failed get: ' + e);
		alert('Error Occured!');
	}
}
// SESSION TIME OUT 



/* #######################################################################################################################
															 __      __        _       _     _           
															 \ \    / /       (_)     | |   | |          
															  \ \  / /_ _ _ __ _  __ _| |__ | | ___  ___ 
															   \ \/ / _` | '__| |/ _` | '_ \| |/ _ \/ __|
															    \  / (_| | |  | | (_| | |_) | |  __/\__ \
															     \/ \__,_|_|  |_|\__,_|_.__/|_|\___||___/
                                             
  
// #######################################################################################################################
*/
var pageLoadTimestamp = new Date().getTime(); // timestamp from which to start counting towards session timeout
var timoutInterval; // used with setInterval() to display seconds counting down to session timeout 
var isLoggedIn; // this is set to true or false in the header based on whether a user is logged in
var SESSION_TIMEOUT_IN_SECONDS = 15 * 60; // 15 minutes * 60 seconds

var keepSessionAlive = false; // set this to true to enable sessions that never time-out
var keepAliveIntervalVar; // used in setSessionKeeper() to ping session at regular intervals to keep sessions alive
var keepAliveMinutes = 9; // automatically ping server every X minutes to keep session alive

/* ########################################################################################################################
                                           _____               _               _______ _                            _   
                                          / ____|             (_)             |__   __(_)                          | |  
                                         | (___   ___  ___ ___ _  ___  _ __      | |   _ _ __ ___   ___  ___  _   _| |_ 
                                          \___ \ / _ \/ __/ __| |/ _ \| '_ \     | |  | | '_ ` _ \ / _ \/ _ \| | | | __|
                                          ____) |  __/\__ \__ \ | (_) | | | |    | |  | | | | | | |  __/ (_) | |_| | |_ 
                                         |_____/ \___||___/___/_|\___/|_| |_|    |_|  |_|_| |_| |_|\___|\___/ \__,_|\__|
                                         
   ########################################################################################################################
 */
function stopEvent(e) {
	try {
		// if event object was passed in the parameter, run whichever method is
		// available
		if (e) {
			if (e.preventDefault) {
				e.preventDefault();
			}
			if (e.stopPropagation) {
				e.stopPropagation();
			}
			// if event was not passed into the parameter, check for IE's global
			// event object
		} else if (window.event) {
			window.event.cancelBubble = true;
			window.event.returnValue = false;
		}
		return false;
	} catch (err) {
		// uncomment to debug
		// debug(err);
		return false;
	}
}
function startSessionTimer() {
	// alert(maxInactiveInterval);
	var triggerTimeoutModal = 0; // 1000-millisecconds *
	// 60-seconds * 13 = 13 minutes
	try {
		if (typeof maxInactiveInterval !== "undefined") {
			triggerTimeoutModal = (maxInactiveInterval - (60 * 2)) * 1000; // max
			// timeout
			// in
			// seconds
			// minus
			// 2
			// minute,
		}
	} catch (err) {
		triggerTimeoutModal = 0; // if error, default to showing
		// popup after 12 minutes idle
		// time
	}
	// debug("maxInactiveInterval = "+maxInactiveInterval);
	// debug("triggerTimeoutModal = "+triggerTimeoutModal);
	// after one minute, display the modal
	// if timoutInterval is less than or equal to zero, there is no session
	// timeout
	if (triggerTimeoutModal > 0) {
		// debug("User login detected... startSessionTimer invoked");
		setTimeout(function() {
			displaySessionModal();
		}, triggerTimeoutModal);
	}
}

function displaySessionModal() {
	// debug("displaySessionModal invoked");
	timoutInterval = setInterval(function() {
		processTimeoutInterval();
	}, 1000); // every second update the timeout clock
	$("#sessionTimeoutModal").modal();
}

function processTimeoutInterval() {
	var currentTime = new Date().getTime();
	var timeLapsed = parseInt((currentTime - pageLoadTimestamp) / 1000);
	var timeLeft = 0;
	// debug("timeLapsed: "+timeLapsed);
	// The variable maxInactiveInterval should have been set, but default to
	// SESSION_TIMEOUT_IN_SECONDS if it is "undefined"
	if (typeof maxInactiveInterval !== "undefined") {
		timeLeft = parseInt(maxInactiveInterval - timeLapsed); // one minute -
		// time lapsed
		// for
	} else {
		timeLeft = parseInt(SESSION_TIMEOUT_IN_SECONDS - timeLapsed); // one
		// minute
		// -
		// time
		// lapsed
		// for
	}
	if (timeLeft < 1) {
		clearInterval(timoutInterval); // stop the countdown showing seconds
		// left in the modal
		$("#sessionPopupSubmit").click();
	}
	$("#timeoutCountdown").text(secondsToTime(timeLeft));
}

function secondsToTime(secs) {
	var minutes = Math.floor(secs / 60);
	var seconds = secs % 60;
	if (seconds < 10) {
		seconds = "0" + seconds;
	}
	var textTime = (minutes > 0) ? minutes + " minutes " + seconds
			+ " seconds." : seconds + " seconds.";
	if ($("html").hasClass("language_es")) {
		textTime = (minutes > 0) ? minutes + " minutos y " + seconds
				+ " segundos." : seconds + " segundos.";
	}
	return textTime;
}

function pingServer() {
	$.ajax('/pmp/main/ping_input?ajaxRequest=true'); // ping the server to
	// keep session alive
}

/*
 * This fires if user clicks "stay logged in" on session timeout modal
 */
function refreshSessionTimer() {
	$("#sessionTimeoutModal").modal("hide");
	clearInterval(timoutInterval); // stop the countdown showing seconds left
	// in the modal
	pageLoadTimestamp = new Date().getTime(); // reset pageload timestamp
	pingServer();
	startSessionTimer();
}

function setSessionKeeper() {
	if (keepSessionAlive === true) {
		keepAliveIntervalVar = setInterval(function() {
			pingServer();
		}, 1000 * 60 * keepAliveMinutes); // milisecond * seconds * minutes:
		// every 9 minutes ping the server
	}
}


function setCurrentTab(tabId){
	createCookie("currentTab", tabId);
}

function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function eraseCookie(name) {
    createCookie(name,"",-1);
}


function setWebsite(externalUrl1){
	$("#externalUrl1").html($('#'+externalUrl1).attr('href'));
	$("#"+"Dcert_modal").modal("show");
	return false;
}

function openWebsite(){
	var testURL = $("#externalUrl1").text();
	$("#"+"Dcert_modal").modal("hide");
	window.open(testURL, '_blank');
	//.moveTo(0, 0).resizeTo(screen.width,screen.height);
}
	
function closeAwayDialog(){
	$("#"+"Dcert_modal").modal("hide");
	return false;
}

function getFormattedCurrentDate() {
	var today = new Date(); 
	var dd = today.getDate(); 
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear(); 
	var hour = today.getHours();
	var min = today.getMinutes();
	if(min < 10){min = '0' + min}
	var seconds = today.getSeconds();
	if(seconds < 10){seconds = '0' + seconds}
	if(dd<10){dd='0'+dd} 
	if(mm<10){mm='0'+mm} 
	return yyyy+'-'+mm+'-'+dd +"-" + hour +"_" + min + "_" + seconds;
}