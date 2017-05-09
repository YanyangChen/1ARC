<%@ page import="acf.acf.General.jsp.*"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="acf" uri="/acf/tld/acf-taglib" %> 
	
<!-- PAGE CONTENT BEGINS -->
<div class="col-md-12 nopadding">
	<acf:Region id="reg_div_list" title="LOCATION SEARCH" type="search">
		<acf:RegionAction>
			<a href="#" onClick="$(this).parents('.widget-box').pForm$clear();">Clear</a>
		</acf:RegionAction>
		<form id="frm_search" class="form-horizontal" data-role="search">
	    	<div class="form-group">
	      		<div class="col-lg-5 col-md-6 col-sm-8 col-xs-10">
	      			<label for=s_mod_id style="display:block">Location code</label>
	      			<acf:ComboBox id="s_location_code" name="location_code" editable="true" multiple="false">
	      			<acf:Bind on="initData"><script>
	 					$(this).acfComboBox("init", ${modules} );
	 				</script></acf:Bind>
	      			</acf:ComboBox>
	        	</div>
	    	</div>
		</form>
	</acf:Region>
</div> 

<div class="col-xs-12 nopadding">
	<form id="frm_main" class="form-horizontal" data-role="form" >
	<acf:Region id="reg_div_list" type="list" title="LOCATION LIST">
   		<acf:RegionAction>
			<a href="javascript:$('#grid_browse').pGrid$prevRecord();">Previous</a>
			&nbsp;
			<a href="javascript:$('#grid_browse').pGrid$nextRecord();">Next</a>
		</acf:RegionAction>
		<div class="col-xs-12">
			<acf:Grid id="grid_browse" url="apff003-search.ajax" readonly="true" autoLoad="false">
				<acf:Column name="location_code" caption="Location code" width="375"></acf:Column>
				<acf:Column name="location_name" caption="Location name" width="500"></acf:Column>
			
			</acf:Grid>
	    </div>
	</acf:Region>
	
	<acf:Region id="reg_mod_main" title="LOCATION MAINTENANCE" type="form">
    	<div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="sys_id">Location code:</label>
      		<div class="col-md-1">    
      			<acf:TextBox id="location_code" name="location_code" />
      			     
      		</div>
    	</div> 
    	<div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="mod_id">Location name:</label>
      		<div class="col-md-3">    
      			<acf:TextBox id="location_name" name="location_name" />     
      		</div>
      		<!--<label class="control-label col-md-2" for="mod_name">Module Name:</label>
      		<div class="col-md-4">          
      			<acf:TextBox id="mod_name" name="mod_name" maxlength="60" checkMandatory="true"/>
      		</div>-->
    	</div>  
    	
    	<!--  <div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="mod_seq">Sequence:</label>
      		<div class="col-md-4">       
      			<acf:TextBox id="mod_seq" name="mod_seq" allowKey="[0-9]" maxlength="4" checkMandatory="true">
      			   	<acf:Bind on="validate"><script>
	 					var val = $(this).acfTextBox("getValue");
	 					if (parseInt(val) == 0) {			 					
	 						$("#frm_main #mod_seq").acfTextBox("setError", val + " is not allowed");
	 					}
	 					else {
	 						$("#frm_main #mod_seq").acfTextBox("setError", "");
	 					}
 					</script></acf:Bind>
 					</acf:TextBox>
      		</div>
      		<label class="control-label col-md-2" for="mod_icon_name">Icon Name:</label>
      		<div class="col-md-4">          
    		    <acf:TextBox id="mod_icon_name" name="mod_icon_name" button="fa-search" maxlength="16">
	   		    	<acf:Bind target="button" on="click"><script>
	   		    		Dialog
							.create()
							.setCaption("Select icon")
							.setWidth(1250)
							.addDismissButton("Cancel")
							.setResultCallback(function(icon) {
								$("#mod_icon_name").acfTextBox('setValue', icon);
								if($("#mod_icon_name").acfTextBox('isModified'))
									Action.modify();
							})
							.showUrl("../acf-select-icon-dialog");
	   		    	</script></acf:Bind>
    		    </acf:TextBox>
    		</div>
    	</div>  -->

		<!--<div class="col-xs-12 form-padding oneline">
			<label class="control-label col-md-2" for="is_menu">Show Menu:</label>
    		<div class="col-md-4">
    			<acf:CheckBox id="is_menu" name="is_menu" label="Menu" choice="1,0"/>
    		</div>
   		</div>-->
    	
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
	searchKey: "location_code",
	browseKey: "location_code",
	//searchForm: $("#frm_search"),
	//browseKey: "section_no",
	
	
	//initMode: "${mode}",
	recordForm: $("#frm_main"),
	recordKey: {
		mod_id: "${location_code}"
	},
	getUrl: "apff003-get-form.ajax",
	saveUrl: "apff003-save.ajax",
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
						