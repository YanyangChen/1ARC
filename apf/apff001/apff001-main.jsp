<%@ page import="acf.acf.General.jsp.*"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="acf" uri="/acf/tld/acf-taglib" %> 
<%
	ACFgJspHelper helper = new ACFgJspHelper(session);
	helper.setConnection("ARCDB");
%>

<div class="col-xs-12 nopadding">
	<acf:Region id="reg_func_search" type="search" title="SECTION SEARCH">
		<acf:RegionAction>
			<a href="#" onClick="$(this).parents('.widget-box').pForm$clear();">Clear</a>
		</acf:RegionAction>
		<form id="frm_search" class="form-horizontal" data-role="search" >
	    	<div class="form-group">
	      		<div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
	      			<label for=s_func_id style="display:block">DDS CODE</label>
	      			<acf:TextBox id="s_dds_code" name="dds_code" maxlength="7"/>  
	      		</div>
	      		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-6">
	      			<label for=s_sec_no style="display:block">Section ID.</label>
	      			<acf:ComboBox id="s_section_id" name="section_id" editable="true" multiple="true">
	      			<acf:Bind on="initData"><script>
	 					$(this).acfComboBox("init", ${modules} );
	 				</script></acf:Bind>
	      			</acf:ComboBox>
	        	</div>
	      		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-6">
	      			<label for=s_func_gp_name style="display:block">Sub Section ID.</label>
	      			<!--<acf:ComboBox id="s_func_gp_name" name="func_gp_name" editable="true" multiple="false">
	      			</acf:ComboBox>-->
	      			<acf:TextBox id="s_sub_section_id" name="sub_section_id" maxlength="60" />
	      		</div>
	      		<div class="col-lg-2 col-md-3 col-sm-3 col-xs-4">
	      			<label for=s_func_name style="display:block">Section Name</label>
	      			<acf:TextBox id="s_section_name" name="section_name" maxlength="60" />  
	      		</div>
	    	</div>
		</form>

	</acf:Region>
	
	<form id="frm_main" class="form-horizontal" data-role="form" >
		<acf:Region id="reg_div_list" type="list" title="SECTION LIST">
   		<acf:RegionAction>
			<a href="javascript:$('#grid_browse').pGrid$prevRecord();">Previous</a>
			&nbsp;
			<a href="javascript:$('#grid_browse').pGrid$nextRecord();">Next</a>
		</acf:RegionAction>
		<div class="col-xs-12">
			<acf:Grid id="grid_browse" url="apff001-search.ajax" readonly="true" autoLoad="false">
			<acf:Column name="dds_code" caption="DDS CODE" width="150"></acf:Column>
				<acf:Column name="section_id" caption="Section Id" width="100"></acf:Column>
				
				<acf:Column name="sub_section_id" caption="Sub section Id" width="75"></acf:Column>
				<acf:Column name="section_name" caption="Section name" width="350"></acf:Column>
				<acf:Column name="report_caption" caption="Report caption" width="200" ></acf:Column>
			</acf:Grid>
	    </div>
	</acf:Region>
	
		<acf:Region id="reg_func_main" type="form" title="SECTION MAINTENANCE">
    	<div class="col-xs-12 form-padding">
     		<label class="control-label col-md-2" for="section_id">Section Id:</label>
      		<div class="col-md-4">
      			<acf:TextBox id="section_id" name="section_id" editable="false" multiple="false" format="[0]{1}[1-9]{1}" formatInstruction="Invalid Number, only 0X (X = 1~9) allowed" checkMandatory="true"/>
      				
      			   
        	</div>
     		<label class="control-label col-md-2" for="sub_section_id">Sub section Id:</label>
      		<div class="col-md-4">    
      			<acf:TextBox id="sub_section_id" name="sub_section_id" maxlength="7" format="[0-9]{1}" formatInstruction="Invalid Number, only X (X = 0~9) allowed"/><!-- allowKey="[0-9A-Za-z]" format="[A-Z]{3}[F][0-9]{3}" checkMandatory="true" forceCase="upper" -->
      		</div>
    	</div>  
    	<div class="col-xs-12 form-padding">
     		<label class="control-label col-md-2" for="dds_code">DDS CODE:</label>
      		<div class="col-md-4">
      			<acf:TextBox id="dds_code" name="dds_code" maxlength="60"/> 
      		</div>
     		<label class="control-label col-md-2" for="section_name">Section Name:</label>
      		<div class="col-md-4">       
      			<acf:TextBox id="section_name" name="section_name" maxlength="60"/>
      			   
      		</div>
    	</div>  
    	<div class="col-xs-12 form-padding">
     		<label class="control-label col-md-2" for="report_caption">Report caption:</label>
      		<div class="col-md-4">          
      			<acf:TextBox id="report_caption" name="report_caption" maxlength="64" checkMandatory="true"/>
      		</div>
     		<!--<label class="control-label col-md-2" for="func_seq">Sequence:</label>
      		<div class="col-md-4">    
      			<acf:TextBox id="func_seq" name="func_seq" maxlength="4" allowKey="[0-9]"/>
      		</div>-->
    	</div>  
    	
		<!-- <div class="col-xs-12 form-padding">
			<label class="control-label col-md-2" for="func_icon_name">Icon Name:</label>
    		 <div class="col-md-4">
    		    <acf:TextBox id="func_icon_name" name="func_icon_name" maxlength="16" button="fa-search">
	   		    	<acf:Bind target="button" on="click"><script>
	   		    		Dialog
							.create()
							.setCaption("Select icon")
							.setWidth(1250)
							.addDismissButton("Cancel")
							.setResultCallback(function(icon) {
								$("#func_icon_name").acfTextBox('setValue', icon);
								if($('#func_icon_name').acfTextBox('isModified'))
									$(document).trigger('modify');
							})
							.showUrl("../acf-select-icon-dialog");
	   		    	</script></acf:Bind>
    		    </acf:TextBox>
    		</div>
     		<label class="control-label col-md-2" for="func_status">Function Status:</label>
      		<div class="col-md-4">
	    		<acf:CheckBox id="func_status" name="func_status" label="Enable" choice="1,0"/>	
      		</div>
    	</div> -->
		<!--<div class="col-xs-12 form-padding">
			<label class="control-label col-md-2" for="allow_new">Allow Privileges:</label>
    		<div class="col-md-10">
    			<acf:CheckBox id="allow_view" name="allow_view" label="View" choice="1,0"/>
    			&nbsp;
    			<acf:CheckBox id="allow_new" name="allow_new" label="New" choice="1,0"/>
    			&nbsp;
    			<acf:CheckBox id="allow_clone" name="allow_clone" label="Clone" choice="1,0"/>
    			&nbsp;
    			<acf:CheckBox id="allow_amend" name="allow_amend" label="Amend" choice="1,0"/>
    			&nbsp;
    			<acf:CheckBox id="allow_delete" name="allow_delete" label="Delete" choice="1,0"/>
    		</div>
		</div>
		<div class="col-xs-12 form-padding">
			<label class="control-label col-md-2" for="is_menu">Show Menu:</label>
    		<div class="col-md-4">
    			<acf:CheckBox id="is_menu" name="is_menu" label="Menu" choice="1,0"/>
    		</div>
			<label class="control-label col-md-2" for="is_tran_log">Transaction Log:</label>
    		<div class="col-md-4">
    			<acf:CheckBox id="is_tran_log" name="is_tran_log" label="Enable" choice="1,0"/>
    		</div>
		</div>-->
	</acf:Region>
	<acf:Region id="reg_stat" title="UPDATE STATISTICS">
		<div class="col-xs-12 form-padding">
     		<label class="control-label col-md-2" for="modified_at">Modified At:</label>
      		<div class="col-md-4">          
        		<acf:DateTime id="modified_at" name="modified_at" readonly="true" useSeconds="true"/>  	
        	</div>
     		<label class="control-label col-md-2" for="created_at">Created At:</label>
      		<div class="col-md-4"> 
      			<acf:DateTime id="created_at" name="created_at" readonly="true" useSeconds="true"/>           
      		</div>
    	</div>
    	<div class="col-xs-12 form-padding">
     		<label class="control-label col-md-2" for="modified_by">Modified By:</label>
      		<div class="col-md-4">          
        		<acf:TextBox id="modified_by" name="modified_by" readonly="true"/>  	
        	</div>
     		<label class="control-label col-md-2" for="created_by">Created By:</label>
      		<div class="col-md-4"> 
      			<acf:TextBox id="created_by" name="created_by" readonly="true"/>           
      		</div>
    	</div>
    	<input type="hidden" id="allow_print" name="allow_print" value="1"/>
	</acf:Region>
	</form>
	
	
</div>

<script>
/*
$("#frm_search").pForm$setRelatedComboBox(${moduleGroups}, [$("#frm_search #s_sec_no"), $("#frm_search #s_func_gp_name")]);
$("#frm_main").pForm$setRelatedComboBox(${moduleGroups}, [$("#frm_main #mod_id"), $("#frm_main #func_gp_name")]);
*/



Controller.setOption({
	searchForm: $("#frm_search"),
	browseGrid: $("#grid_browse"),
	browseKey: "section_id,sub_section_id",
	//redirect: "acff012-form",
	
	//initMode: "${mode}",
	recordForm: $("#frm_main"),
	recordKey: {
		section_id: "${section_id}",
		sub_section_id: "${sub_section_id}",
	},
	getUrl: "apff001-get-form.ajax",
	saveUrl: "apff001-save.ajax",
ready: function() { Action.setMode("search"); }	
}).executeSearchBrowserForm();

$(document).on('new', function() {
	//$("#frm_main #dds").setValue("22");
});

</script>
<%
	helper.close();
%>