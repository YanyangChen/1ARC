<%@ page import="acf.acf.General.jsp.*"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="acf" uri="/acf/tld/acf-taglib" %> 
	
<!-- PAGE CONTENT BEGINS -->
<div class="col-md-12 nopadding">
	<acf:Region id="reg_div_list" title="MODULE SEARCH" type="search">
		<acf:RegionAction>
			<a href="#" onClick="$(this).parents('.widget-box').pForm$clear();">Clear</a>
		</acf:RegionAction>
		<form id="frm_search" class="form-horizontal" data-role="search">
	    	<div class="form-group">
	      		<div class="col-lg-5 col-md-6 col-sm-8 col-xs-10">
	      			<label for=s_mod_id style="display:block">Supplier code</label>
	      			<acf:TextBox id="s_supplier_code" name="supplier_code" editable="true" multiple="false"></acf:TextBox>
	      			<label for=s_mod_id style="display:block">Supplier name</label>
	      			<acf:TextBox id="s_supplier_name" name="supplier_name" editable="true" multiple="false"></acf:TextBox>
	      			
	        	</div>
	    	</div>
		</form>
	</acf:Region>
</div> 

<div class="col-xs-12 nopadding">
	<form id="frm_main" class="form-horizontal" data-role="form" >
	<acf:Region id="reg_div_list" type="list" title="SECTION LIST">
   		<acf:RegionAction>
			<a href="javascript:$('#grid_browse').pGrid$prevRecord();">Previous</a>
			&nbsp;
			<a href="javascript:$('#grid_browse').pGrid$nextRecord();">Next</a>
		</acf:RegionAction>
		<div class="col-xs-12">
			<acf:Grid id="grid_browse" url="apff002-search.ajax" readonly="true" autoLoad="false">
				<acf:Column name="supplier_code" caption="Supplier code" width="375"></acf:Column>
				<acf:Column name="supplier_name" caption="Supplier name" width="500"></acf:Column>
			
			</acf:Grid>
	    </div>
	</acf:Region>
	
	<acf:Region id="reg_mod_main" title="MODULE MAINTENANCE" type="form">
    	<div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="sys_id">Supplier code:</label>
      		<div class="col-md-4">    
      			<acf:TextBox id="supplier_code" name="supplier_code" />
      			     
      		</div>
    	</div> 
    	<div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="mod_id">Supplier name:</label>
      		<div class="col-md-4">    
      			<acf:TextBox id="supplier_name" name="supplier_name" />     
      		</div>

    	
	</acf:Region>
	

	<acf:Region id="reg_stat" title="UPDATE STATISTICS">
		<div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="modified_at">Modified At:</label>
      		<div class="col-md-4">          
        		<acf:DateTime id="modified_at" name="modified_at" readonly="true" useSeconds="true"/>  	
        	</div>
     		<label class="control-label col-md-2" for="created_at">Created At:</label>
      		<div class="col-md-4"> 
      			<acf:DateTime id="created_at" name="created_at" readonly="true" useSeconds="true"/>           
      		</div>
    	</div>
    	<div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="modified_by">Modified By:</label>
      		<div class="col-md-4">          
        		<acf:TextBox id="modified_by" name="modified_by" readonly="true"/>  	
        	</div>
     		<label class="control-label col-md-2" for="created_by">Created By:</label>
      		<div class="col-md-4"> 
      			<acf:TextBox id="created_by" name="created_by" readonly="true"/>           
      		</div>
    	</div>
	</acf:Region>	

   	</form>
	
</div>

<script>

Controller.setOption({
	searchForm: $("#frm_search"),
	browseGrid: $("#grid_browse"),
	searchKey: "supplier_code",
	browseKey: "supplier_code",
	//searchForm: $("#frm_search"),
	//browseKey: "section_no", testing 2
	
	
	//initMode: "${mode}",
	recordForm: $("#frm_main"),
	recordKey: {
		mod_id: "${supplier_code}"
	},
	getUrl: "apff002-get-form.ajax",
	saveUrl: "apff002-save.ajax",
ready: function() { Action.setMode("search"); }
}).executeSearchBrowserForm(); 

$(document).on("beforeSave", function() {
	if (Action.getMode() == 'amend') {
		var mod = $('#frm_main').pForm$getModifiedRecord();
		delete mod[0].version;
		delete mod[1].version;
		if(mod[0].equals(mod[1])) {
			throw ACF.getDialogMessage("ACF027E");
			return false;
		}
	}
});

</script>
						