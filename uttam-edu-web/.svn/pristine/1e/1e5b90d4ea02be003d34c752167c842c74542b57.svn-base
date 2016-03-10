<%@taglib uri="/struts-tags" prefix="s"%>

<link href="<s:property value='rootPath'/>static/custom/css/ColReorder.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css">
<%-- <link href="<s:property value='rootPath'/>static/custom/css/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css"> --%>
<link href="<s:property value='rootPath'/>static/custom/css/jquery.dataTables.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css">
<link href="<s:property value='rootPath'/>static/custom/css/TableTools.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css">
<%-- <link
	href="<s:property value='rootPath'/>static/custom/css/complete.css"
	rel="stylesheet" type="text/css"> --%>
<link href="<s:property value='rootPath'/>static/custom/css/dataTables.tableTools.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css">

<link href="<s:property value='rootPath'/>static/custom/css/dataTables.colVis.css?v=<s:property value="buildId"/>" rel="stylesheet" type="text/css">


<script type="text/javascript" charset="utf-8" src="<s:property value='rootPath'/>static/custom/js/jquery.dataTables.js?v=<s:property value="buildId"/>"></script>
<script type="text/javascript" charset="utf-8" src="<s:property value='rootPath'/>static/custom/js/dataTableMethod.js?v=<s:property value="buildId"/>"></script>
<script type="text/javascript" charset="utf-8" src="<s:property value='rootPath'/>static/custom/js/ColReorder.js?v=<s:property value="buildId"/>"></script>
<%-- <script type="text/javascript" charset="utf-8"
	src="<s:property value='rootPath'/>static/custom/js/ColVis.js"></script> --%>
<script type="text/javascript" charset="utf-8" src="<s:property value='rootPath'/>static/custom/js/ZeroClipboard.js?v=<s:property value="buildId"/>"></script>
<script type="text/javascript" charset="utf-8" src="<s:property value='rootPath'/>static/custom/js/TableTools.js?v=<s:property value="buildId"/>"></script>
<script type="text/javascript" charset="utf-8"
	src="<s:property value='rootPath'/>static/custom/js/dataTables.tableTools.js?v=<s:property value="buildId"/>"></script>


<script type="text/javascript" charset="utf-8" src="<s:property value='rootPath'/>static/custom/js/dataTables.colVis.js?v=<s:property value="buildId"/>"></script>
<%-- <script type="text/javascript" charset="utf-8"
	src="<s:property value='rootPath'/>static/custom/js/dataTables.bootstrap.js"></script> --%>


<script type="text/javascript" charset="utf-8">
	jQuery.extend(jQuery.fn.dataTableExt.oSort, {
		"currency-pre" : function(a) {
			a = (a === "-") ? 0 : a.replace(/[^\d\-\.]/g, "");
			return parseFloat(a);
		},

		"currency-asc" : function(a, b) {
			return a - b;
		},

		"currency-desc" : function(a, b) {
			return b - a;
		}
	});
	var print_text = "<div id='aaa' align='left' style='padding-left: 20px;' width='30px'><a href='javascript:printTableFormate();'> [print] </a></div>\n\
                                                <div align='center'><h2></h2></div>";

	var oTable;
	$(document)
			.ready(
					function() {
						$('.searchtabel tbody tr td input[type="checkbox"]')
								.change(
										function() {
											$(
													'.searchtabel tbody tr td input[type="checkbox"]')
													.each(
															function() {
																$(this)
																		.parent()
																		.parent()
																		.removeClass(
																				'selected');
																if ($(this)
																		.is(
																				':checked') == true) {
																	$(this)
																			.parent()
																			.parent()
																			.addClass(
																					"selected")
																}
															});
										});
						$('.searchtabel tbody tr input[type=radio]')
								.change(
										function() {
											$(
													'.searchtabel tbody tr input[type=radio]')
													.each(
															function() {
																$(this)
																		.parent()
																		.parent()
																		.removeClass(
																				'selected');
																if ($(this)
																		.is(
																				':checked') == true) {
																	$(this)
																			.parent()
																			.parent()
																			.addClass(
																					"selected")
																}
															});
										});

						var dontSort = [];
						$('#datatable thead th').each(function() {
							if ($(this).hasClass('no_sort')) {
								dontSort.push({
									"bSortable" : false
								});
							} else {
								dontSort.push(null);
							}
						});
						$("#datatable").addClass("cell-border");
						$("#datatable").addClass("display");
						var datatable = $('#datatable')
								.DataTable(
										{
											"aaSorting" : [],
											"sPaginationType" : "full_numbers",
											"sDom" : 'TC<"clear">lfrtip',
											"iDisplayLength" : 20,
											"lengthMenu" : [
													[ 20, 100, 1000, -1 ],
													[ 20, 100, 1000, "All" ] ],
											"oColVis" : {
												"aiExclude" : []
											},
											"oTableTools" : {
												"sSwfPath" : '<s:property value='rootPath'/>static/custom/swf/copy_csv_xls_pdf.swf',
												"aButtons" : [
														{
															'sExtends' : 'xls',
															"sFileName" : "pmp-export-excel.xls",
															"oSelectorOpts" : {
																filter : 'applied',
																order : 'current'
															},
														},
														{
															'sExtends' : 'pdf',
															"sTitle" : "pmp-export-pdf",
															"oSelectorOpts" : {
																filter : 'applied',
																order : 'current'
															},
														} ]
											},
											"oLanguage" : {
												"sZeroRecords" : '<img style="width: 33px;" src="<s:property value='rootPath'/>static/custom/images/alert.png"/> <s:text name='msg.global.no_data_found'/>'
											},
											"language" : {
												"emptyTable" : '<img style="width: 33px;" src="<s:property value='rootPath'/>static/custom/images/alert.png"/> <s:text name='msg.global.no_data_found'/>'
											},
											"fnInitComplete": function(oSettings, json) {
										      if (typeof postTableInit == 'function') { 
												  postTableInit(oSettings, json); 
											  }
										    }

										});
						oTable = datatable;

					});
	function printTableFormate() {
		window.print();
	}
</script>
<style>
table.dataTable.display tbody tr:hover {
	background-color: #F7DF8D;
}

table.dataTable.display thead th {
	text-align: center;
}

div.dataTables_length label {
	font-weight: bold;
}

div.dataTables_filter label {
	font-weight: bold;
}

.plan-table .carrier-data {
	position: relative;
	padding-top: 10px;
}

.carrier-data {
	position: relative;
	padding-top: 10px;
}

.dataTables_wrapper .dataTables_paginate .paginate_button {
	padding: 0.5em .5em;
	margin-left: 0px;
	border: none !important;
}

.dataTables_wrapper .dataTables_paginate .paginate_button:hover {
	background-color: #c64323;
	background-image: none;
	border-radius: 5px;
	border: none !important;
	color: #fff;
}
</style>

