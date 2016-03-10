package com.uttam.web.framework.ui.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.uttam.web.framework.common.MessageFormatter;

public abstract class BaseCustomTag extends BodyTagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7556867058657196327L;
	
	Logger log= LogManager.getLogger(BaseCustomTag.class);

	public int doStartTag()throws JspException{ 
		try{
		    writeToPage(startHTML());		
		} catch (Exception e) {
			log.error("ERROR in BaseCustomTag.doStartTag().", e);
  			throw new JspException("Error rendering jsp",e);
  		}
		return EVAL_BODY_INCLUDE;
	}
	
	public abstract StringBuffer startHTML() throws Exception;

	public int doEndTag() throws JspException{ 
      	StringBuffer content= buildHTML(); 
      	writeToPage(content);
      	clearInstanceFields();
	  	return EVAL_PAGE;  
 	}
 			
	public StringBuffer buildHTML(){
		return new StringBuffer();
		
	} 
  
    
  	protected void writeToPage(StringBuffer aContent)throws JspException {
  		try {  			
  			JspWriter out = pageContext.getOut();
  	 		if(aContent!=null){
  			String actualContent = aContent.toString();
  	 		out.println(actualContent);
  	 		}
  		} catch (Exception e) {
			log.error("ERROR in BaseCustomTag.writeToPage().", e);
  			throw new JspException("Error rendering jsp",e);
  		}
  	}  
  	
  	protected String getServletContextName() {
  		return pageContext.getServletContext().getServletContextName();
  		
  	}
  	
  	protected String getImage(String fileName) {
  		String path = ServletActionContext.getResponse().encodeURL(ServletActionContext.getRequest().getContextPath()+"/static/custom/images/"+fileName);
  		return path;
  		
  	}
  	
  	protected String getHTMLFile(String fileName) {
  		String path =ServletActionContext.getResponse().encodeURL(ServletActionContext.getRequest().getContextPath()+"/static/custom/html/"+fileName);
  		return path;
  		
  	}
  	
  	/**
  	 * If param is integer gets the value from rb or else same value
  	 * @param string
  	 * @return
  	 * @author admin
  	 * Feb 18, 2009
  	 */
  	protected String getI18NText(String string) {
		return MessageFormatter.getText(string);
	}
  	protected String getI18NText(String string, String substitute) {
		return MessageFormatter.getText(string, substitute);
	}
  	protected String getI18NText(String string, String[] substituteArray) {
		return MessageFormatter.getText(string, substituteArray);
	}
  	
  	protected String getPortletNameSpace(){
  		String value = "";
//  		if(ServletActionContext.getResponse() != null){
//  			value = ServletActionContext.getResponse().getNamespace();
//  		}
  		return value;
  	}
  	
//  	protected String getURL(String actionName, String method){
//  		Object action = ActionContext.getContext().getActionInvocation().getAction();
//  		if(action instanceof BaseAction){
//  			String url = null;
//  			BaseAction hixAction = (BaseAction) action;
//  			String path = getActionName(actionName);
//
//  			if(StringUtils.isNotEmpty(method)){
//  				path = path+"_"+method;
//  			}
//  				String queryString = getQueryString(actionName);
//  				Map<String,String[]> params = getRenderParameters(queryString);
//  				BaseURL renderUrl = hixLiferayPortletUrlHelperJSR286.
//  					getLiferayRenderURL((MimeResponse) PortletActionContext.getResponse());
//  				params.put(PortletConstants.ACTION_PARAM, new String[]{path});
//  				params.put(PortletConstants.MODE_PARAM,new String[]{PortletMode.VIEW.toString()});
//  				renderUrl.setParameters(params);
//  				
//  				url = renderUrl.toString();
//  				
//
//  			else if(ActionRequest.class.equals(phase)){
//  				url = hixAction.getPortletActionURL(path);
//
//  			}else if(ResourceRequest.class.equals(phase)){
//  				url = hixAction.getPortletRenderURL(path);
//  			}
//  			else{
//  				throw new RuntimeException("Unknown Portlet Phase URL requested: "+phase);
//  			}
//  			return url;
//  		}
//  		throw new RuntimeException("Current Action not an instance of hixBaseAction");
//
//  	}
  	
  	
//  	private String getActionName(String actionName){
//
//  		int indx = actionName.indexOf('?');
//  		String action = actionName;
//  		if (indx > 0){
//
//  			action= actionName.substring(0,indx);
//  		}
//  		return action;
//  	}
//  	
//  	
//
//  	private  String getQueryString(String action) {
//  		int idx = action.lastIndexOf('?');
//  		String queryString = "";
//  		if (idx >= 0) {
//  			queryString = action.substring(idx + 1);
//  		}
//  		return queryString;
//  	}
//
//  	private Map<String, String[]> getRenderParameters (String queryURL){
//  		Map<String, String[]> map =  new HashMap<String, String[]>(); 
//  		String[] params = queryURL.split("&");  
//  		if(params != null && params.length > 0){
//
//  			String[] split = null;
//  			for (String param : params)   
//  			{   
//  				split = param.split("=");
//  				if(split.length > 1){
//  					map.put(split[0],new String[]{split[1]});
//  				}
//  			} 
//  		}
//  		return map;
//  	}
//    
//    protected String getPortletViewNameSpace(){
//  		return PortletActionContext.getPortletConfig().getInitParameter("viewNamespace");
//  	}	
    
    /**
     * Used to clear the instance level fields so that the container re-use of tag instances
     * does not affect the render. Override this method and reset the instance fields to default.
     * This method is called at the end of doEndTag();
     * @since 04/12/2011 - tm9982
     */
    protected abstract void clearInstanceFields();
  
}
