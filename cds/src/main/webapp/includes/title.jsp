<%@ taglib prefix="s" uri="/struts-tags"%>
<div>

	<div class="container maincont">
		<div class="row">
			<div class="col-md-7 logopart">
				<a href="/cds/eligibility/household_input" tabindex="1" id="id_title_logo"><img
					alt="<s:text name="txt.global.title"/>"
					src="<s:property value='rootPath'/>static/custom/images/logo.png" id="logo" /> 
				</a>

			</div>

			<div class="iconpart">
				<ul>
					<li><a href="https://www.ahctmobile.com" target="_blank">
					<!-- <i class="fa fa-mobile"></i> --><b> ahCT Mobile</b></a></li>
					<li><span>|</span>
					<s:if test="currentLanguage == 'en_US'">
					<s:a href="#" onclick="javascript:switchLanguage('es_US')" class="boldFont">
					
						Espa�ol
						</s:a>
					</s:if><s:else>
					<s:a href="#" onclick="javascript:switchLanguage('en_US')" ><b>
					
						English
						</b></s:a>
						
					</s:else>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--//header-->



<div class="clearfix"></div>
