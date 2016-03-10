package com.hixapi.web.framework.ui.tag;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hixapi.web.framework.common.UserMessage;
import com.hixapi.web.framework.common.ValidationMessageFormatter;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author addepup
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ErrorMessageTag extends BaseCustomTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3849958076270725277L;

	private boolean hasMessages;

	private boolean showHelpButtonMessage;

	private boolean hideTable;

	private boolean leftMenuEnabled;

	private static final Logger LOG = LogManager
			.getLogger(ErrorMessageTag.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public StringBuffer startHTML() {
		hasMessages = false;
		StringBuilder content = new StringBuilder();
		Map fieldErrors = (Map) ActionContext.getContext().getValueStack()
				.findValue("fieldErrors");
		Collection actionErrors = (Collection) ActionContext.getContext()
				.getValueStack().findValue("actionErrors");
		Collection actionMessages = (Collection) ActionContext.getContext()
				.getValueStack().findValue("actionMessages");
		Collection<UserMessage> businessMessages = (Collection<UserMessage>) ActionContext
				.getContext().getValueStack().findValue("businessMessages");
		Collection<String> unclassifiedMessages = (Collection<String>) pageContext
				.getRequest().getAttribute("randomMessages");
		LOG.debug("Checking presence of any error message");
		if ((fieldErrors != null && fieldErrors.size() > 0)
				|| (actionErrors != null && actionErrors.size() > 0)
				|| (actionMessages != null && actionMessages.size() > 0)
				|| (businessMessages != null && businessMessages.size() > 0)
				|| unclassifiedMessages != null
				&& unclassifiedMessages.size() > 0) {
			LOG.debug("Messages present. Setting hasMessages=true");
			hasMessages = true;
		}
		if (!hideTable) {
			content.append(startTable());
			content.append("\n \t \t \t \t<TR>");
			content.append("\n \t \t \t \t \t<TD style=\"padding-left:5px;background-color:#FFCCCC;\">");
			content.append("<SPAN ID=\"").append(getPortletNameSpace())
					.append("errorSpanClient\">");
		}
		if (fieldErrors != null && fieldErrors.size() > 0) {
			LOG.debug("Field Error Messages present. Adding..");
			for (Iterator<String> iter = fieldErrors.keySet().iterator(); iter
					.hasNext();) {
				content.append(addStringErrors(((Collection) fieldErrors
						.get(iter.next()))));
			}
		}
		if (actionErrors != null && actionErrors.size() > 0) {
			LOG.debug("Action Error Messages present. Adding..");
			content.append(addStringErrors(actionErrors));
		}
		if (actionMessages != null && actionMessages.size() > 0) {
			LOG.debug("Acton Messages present. Adding..");
			content.append(addStringErrors(actionMessages));
		}
		if (businessMessages != null && businessMessages.size() > 0) {
			LOG.debug("Acton Messages present. Adding..");
			content.append(addBusinessErrors(businessMessages));
		}
		if (showHelpButtonMessage && hasMessages) {
			content.append(showHelpButtonMessage());
		}
		if (!hideTable) {
			content.append("</SPAN>");
			content.append("</TD>");
			content.append("\n \t \t \t \t</TR>");
			content.append("\n \t \t \t</TABLE>");
		}

		return new StringBuffer(content.toString());
	}

	private String showHelpButtonMessage() {
		StringBuilder message = new StringBuilder();
		message.append("<IMG SRC=\"").append(
				getImage(ValidationMessageFormatter.getImage(3)));
		message.append("\" ALT=\"");
		message.append(getI18NText(ValidationMessageFormatter.getImageAlt(3)));
		message.append("\" ");
		message.append("CLASS=\"imgPrp1\">&#160;");
		message.append(getI18NText("msg.global.useHelpButton")).append("<br/>");
		return message.toString();

	}

	private String addBusinessErrors(Collection<UserMessage> businessMessages) {
		StringBuilder message = new StringBuilder();
		if (businessMessages != null) {
			for (UserMessage aMsg : businessMessages) {
				message.append("<IMG SRC=\"").append(
						getImage(ValidationMessageFormatter.getImage(aMsg
								.getSeverity())));
				message.append("\" ALT=\"");
				message.append(getI18NText(ValidationMessageFormatter
						.getImageAlt(aMsg.getSeverity())));
				message.append("\" ");
				message.append("CLASS=\"imgPrp1\">&#160;");
				message.append("<SPAN STYLE=\"color:");
				if (aMsg.getSeverity() == 1 || aMsg.getSeverity() == 2) {
//					message.append("red");
					message.append("black");

				} else {
					message.append("#46b6cc");
				}
//				message.append(";\"><B>");
				message.append(";\">");
				message.append(ValidationMessageFormatter.getText(aMsg));
//				message.append("</B></SPAN><br/>");
				message.append("</SPAN><br/>");

			}
		}
		return message.toString();

	}

	@SuppressWarnings("rawtypes")
	private String addStringErrors(Collection errors) {
		StringBuilder message = new StringBuilder();
		if (errors != null) {
			for (Iterator iter = errors.iterator(); iter.hasNext();) {
				message.append("<IMG SRC=\"").append(
						getImage(ValidationMessageFormatter.getImage(1)));
				message.append("\" ALT=\"");
				message.append(getI18NText(ValidationMessageFormatter
						.getImageAlt(1)));
				message.append("\" ");
				message.append("CLASS=\"imgPrp1\">&#160;");
//				message.append("<SPAN STYLE=\"color:red;\"><B>");
				message.append("<SPAN STYLE=\"color:black;\">");

				message.append(iter.next());
//				message.append("</B></SPAN><br/>");
				message.append("</SPAN><br/>");


			}
		}
		return message.toString();

	}

	private String startTable() {
		StringBuilder content = new StringBuilder();
		if (!leftMenuEnabled) {
			content.append("\n \t \t \t<TABLE ");// commented width - 03142011 -
													// admin
		} else {
			content.append("\n \t \t \t<TABLE ");// commented width - 03142011 -
													// admin
		}
		content.append("ID=\"").append(getPortletNameSpace())
				.append("errorTableClient\" class=\"errorTableClient\" ");
		content.append("BORDER=\"0\" CELLSPACING=\"0\" ");
		content.append(" style=\"width:100%;");
		if (!hasMessages) {
			content.append("display:none;");
		}
		content.append("\"");

		content.append("CELLPADDING=\"0\" CLASS=\"ErrorMessageTable\" ");
		content.append("SUMMARY=\"Table to display Messages\" >");
		content.append("\n \t \t \t \t<TR>");
		content.append("\n \t \t \t \t \t<TD CLASS=\"ErrorMessageHeader\">");
		// content.append("\n \t \t \t \t \t<SPAN STYLE=\"cursor:default\">")
		// .append("<INPUT TYPE=\"image\" ALT=\"Error Message Image\" NAME=\"errMsgImg\" ID=\"errMsgImg\" tabindex=\"20\" ")
		// .append("SRC=\"").append(getImageDirectoryPath()).append("spacer.gif\" ");
		// content.append(" WIDTH=\"1\" HEIGHT=\"1\"></SPAN>");
		content.append("<SPAN ID=\"").append(getPortletNameSpace())
				.append("errorHeader\">");
		content.append(getHeader());
		content.append("</SPAN>");
		content.append("</TD>");
		content.append("\n \t \t \t \t</TR>");
		return content.toString();

	}

	private String getHeader() {
		String message = "";
		if (hasMessages) {
			message = getI18NText("txt.global.header_error_messages");
		}
		return message;
	}

	/**
	 * @return the showHelpButtonMessage
	 * @author admin Feb 23, 2009
	 */
	public boolean isShowHelpButtonMessage() {
		return showHelpButtonMessage;
	}

	/**
	 * @param showHelpButtonMessage
	 *            the showHelpButtonMessage to set
	 * @author admin Feb 23, 2009
	 */
	public void setShowHelpButtonMessage(boolean showHelpButtonMessage) {
		this.showHelpButtonMessage = showHelpButtonMessage;
	}

	public boolean isHideTable() {
		return hideTable;
	}

	public void setHideTable(boolean hideTable) {
		this.hideTable = hideTable;
	}

	/**
	 * @return the leftMenuEnabled
	 */
	public boolean isLeftMenuEnabled() {
		return leftMenuEnabled;
	}

	/**
	 * @param leftMenuEnabled
	 *            the leftMenuEnabled to set
	 */
	public void setLeftMenuEnabled(boolean leftMenuEnabled) {
		this.leftMenuEnabled = leftMenuEnabled;
	}

	public void clearInstanceFields() {
		showHelpButtonMessage = false;
	}

}
