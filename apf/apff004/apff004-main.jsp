<%@ page import="acf.acf.General.jsp.*"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="acf" uri="/acf/tld/acf-taglib" %> 
	
<!-- PAGE CONTENT BEGINS -->
<div class="col-md-12 nopadding">
	<acf:Region id="reg_div_list" title="LABOUR TYPE SEARCH" type="search">
		<acf:RegionAction>
			<a href="#" onClick="$(this).parents('.widget-box').pForm$clear();">Clear</a>
		</acf:RegionAction>
		<form id="frm_search" class="form-horizontal" data-role="search">
	    	<div class="form-group">
	      		<div class="col-lg-5 col-md-6 col-sm-8 col-xs-10">
	      			<label for=s_mod_id style="display:block">SECTION NO</label>
	      			<acf:ComboBox id="s_section_id" name="section_id" editable="true" multiple="false">
	      			<acf:Bind on="initData"><script>
	 					$(this).acfComboBox("init", ${modules} );
	 				</script></acf:Bind>
	      			</acf:ComboBox>
	        	</div>
	        	
	        	<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
	      			<label for=s_effective_from_date style="display:block">Search Due Date From</label>
	      			<acf:DateTimePicker id="s_last_date_from" name="last_date_from" pickTime="false"/>
	        	</div>
	        	
	        	<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
	      			<label for=s_effective_to_date style="display:block">Search Due Date To</label>
	      			<acf:DateTimePicker id="s_last_date_to" name="last_date_to" pickTime="false"/>
	        	</div>
	        	
	    	</div>
		</form>
	</acf:Region>
</div> 

<div class="col-xs-12 nopadding">
	<form id="frm_main" class="form-horizontal" data-role="form" >
	<acf:Region id="reg_div_list" type="list" title="LABOUR TYPE LIST">
   		<acf:RegionAction>
			<a href="javascript:$('#grid_browse').pGrid$prevRecord();">Previous</a>
			&nbsp;
			<a href="javascript:$('#grid_browse').pGrid$nextRecord();">Next</a>
		</acf:RegionAction>
		<div class="col-xs-12">
			<acf:Grid id="grid_browse" url="apff004-search.ajax" readonly="true" autoLoad="false">
				<acf:Column name="labour_type" caption="Labour type" width="75"></acf:Column>
				<acf:Column name="labour_type_description" caption="Labour type description" width="100" forceCase = "upper"></acf:Column>
				<acf:Column name="section_id" caption="Section No" width="100"></acf:Column>
				<acf:Column name="effective_from_date" caption="Effective From Date" type="date" width="200"></acf:Column>
				<acf:Column name="effective_to_date" caption="Effective To Date" type="date" width="200"></acf:Column>
				<acf:Column name="hourly_rate" caption="Manhour rate" width="100" type="number" useNumberFormat="true"  align="right"></acf:Column>
			
			</acf:Grid>
	    </div>
	</acf:Region>
	
	<acf:Region id="reg_mod_main" title="LABOUR TYPE MAINTENANCE" type="form">
    	<div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="sys_id">Labour Type:</label>
      		<div class="col-md-4">    
      			<acf:TextBox id="labour_type" name="labour_type" readonly="false" disabled="true"/>
      			     
      		</div>
      		
      		<label class="control-label col-md-2" for="sys_id">Sub Section ID:</label>
      		<div class="col-md-4">    
      			<acf:TextBox id="sub_section_id" name="sub_section_id" readonly="false" disabled="true"/>
      			     
      		</div>
      		
    	</div> 
    	<div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="mod_id">Section No:</label>
      		<div class="col-md-4">    
      			<acf:TextBox id="section_id" name="section_id" format="[0]{1}[1-9]{1}" formatInstruction="Invalid Number, only 0X (X = 1~9) allowed">     
				<acf:Bind on="change"><script>
   						section_id = $(this).getValue(); //this bloack is the same as onLoadSucess except $this value
   						
   						if (section_id == ""){
   							//$("#frm_main #supplier_desc").setValue("");	
   						}
   						else {					
	   						$.ajax({
								headers: {
									'Accept'       : 'application/json',
									'Content-Type' : 'application/json; charset=utf-8'
								},
								async  : false,
								type   : "POST",
								url    : "${ctx}/arc/apf/apf-get-section-name.ajax",
								data   : JSON.stringify({
									'section_id'	: section_id,
								}),
								success: function(data) {
									if (data.section_name != null) {
										$("#frm_main #section_name").setValue(data.section_name);
									}
									else {
										//$("#frm_main #supplier_desc").setValue("");
									}
								}
							});
   						}
					</script></acf:Bind> 
					</acf:TextBox>   		
      		</div>
      		<label class="control-label col-md-2" for="section_name">Section Name:</label>
      		<div class="col-md-4">          
      			<acf:TextBox id="section_name" name="section_name" maxlength="60" checkMandatory="false" />  
      			
      						
      		</div>
      	</div> 	
      	<div class="col-xs-12 form-padding oneline">
      		<label class="control-label col-md-2" for="mod_name">Labour Type Description:</label>
      		<div class="col-md-4">          
      			<acf:TextBox id="labour_type_description" name="labour_type_description" maxlength="60" checkMandatory="true" forceCase="upper"/>
      		</div>
      		<label class="control-label col-md-2" for="mod_name">Manhour Rate:</label>
      		<div class="col-md-4">          
      			<acf:TextBox id="hourly_rate" name="hourly_rate" type="number" useNumberFormat="true"  maxlength="60" checkMandatory="false">
      			<acf:Bind on="change"><script>
      			if(!$(this).getValue().match("^[0-9]{1,3}(\\\\\\.[0-9]{1,2})?$")){
      				$(this).setError(ACF.getQtipHint("APF004V"), "APF004V");
			   	}
			   	else {
			   		$(this).setError("", "APF004V"); //clear the error message
			   		//"Invalid rate, only XXX.XX allowed"
			   	}
			   	</script></acf:Bind>
			   	</acf:TextBox>
			   			
      		</div>
    	</div>  
    	
    	
    	
    	<div class="col-xs-12 form-padding oneline">
     		<label class="control-label col-md-2" for="mod_id">Effective From Date:</label>
      		<div class="col-md-4">    
      			<!--<acf:DateTime id="effective_from_date" name="effective_from_date" readonly="true" useSeconds="true"/>-->
      			<acf:DateTimePicker id="effective_from_date" name="effective_from_date" pickDate="true" pickTime="false" checkMandatory="false">
			   			<acf:Bind on="validate"><script>
			   				//var ts = $(this).getValue();
			   				//if (ts != "" && ts > 1420113600000) { // 2015/01/01 20:00
			   			//		$(this).setError(ACF.getQtipHint("EXE003V"), "EXE003V");
			   			//	}
			   			//	else {
			   			//		$(this).setError("", "EXE003V");
			   			//	} displayformat="YYYY/MM/DD HH:mm" display format for the following fifth line.
			   			</script></acf:Bind>
			   		</acf:DateTimePicker>
      			   
      		</div>
      		<label class="control-label col-md-2" for="mod_name">Effective To Date:</label>
      		<div class="col-md-4">         
      			<acf:DateTimePicker id="effective_to_date" name="effective_to_date" pickDate="true" pickTime="false" checkMandatory="false">
			   			<acf:Bind on="validate"><script>
			   				var ts = $(this).getValue();
			   				if (ts != "" && ts < $("#effective_from_date").getValue()) { // 2015/01/01 20:00
			   					//$(this).setError(ACF.getQtipHint("Earlier than start date!"), "EXE003");
			   					$(this).setError(ACF.getQtipHint("APFF004E"), "APFF004E");
			   					//ACF.getQtipHint("Earlier than start date!");
			   					//Alert.showError(ACF.getDialogMessage("ACFF004E"), callback);
			   				}
			   				else {
			   				if($(this).isError() == true)
			   					{
			   					$(this).disable();
			   					$(this).enable();
			   					// disable + enable = clearerror...
			   					}
			   				}
			   			</script></acf:Bind>
			   		</acf:DateTimePicker>
			   		</div>
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
	searchKey: "labour_type,effective_to_date",
	browseKey: "labour_type,effective_to_date",
	//searchForm: $("#frm_search"),
	//browseKey: "section_id",
	
	
	//initMode: "${mode}",
	recordForm: $("#frm_main"),
	recordKey: {
		labour_type: "${labour_type}", //labout_type on the left is the key of the table, the value on the right is redundant(design problem).
		effective_to_date: "${effective_to_date}",
	},
	getUrl: "apff004-get-form.ajax",
	saveUrl: "apff004-save.ajax",
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
						