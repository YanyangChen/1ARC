<%@ taglib prefix="acf" uri="/acf/tld/acf-taglib" %> 

<!-- PAGE CONTENT BEGINS -->
<form id="frm_main" class="form-horizontal" data-role="form" >
<div class="col-md-12 nopadding">
	<acf:Region id="reg_sect_main" title="GENERATE REPORT IN PDF">
		<div class="col-xs-12 form-padding oneline">
        	<label class="control-label col-md-2" for="p_report_date">Report Date</label>
      		<div class="col-md-4">      
      			<acf:DateTimePicker id="p_report_date" name="p_report_date" pickDate="true" pickTime="false" checkMandatory="true"/>
        	</div>
    	</div>
    	
     </acf:Region>
</div>
</form>

<script>
Controller.setOption({
	form: 	  	 $("#frm_main"),
	reportID: 	 "APFF501",
	saveUrl:  	 "apff501-report.ajax",
	downloadUrl: "apff501-get-pdf",
}).executeReportBrowser();
</script>
