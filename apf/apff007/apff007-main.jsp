<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acf" uri="/acf/tld/acf-taglib" %> 

<acf:Region id="reg_main" title="BUSINESS PLATFORM SEARCH" type="search">
	<div class="widget-main no-padding">
		<form id="frm_busisearch" class="form-horizontal" data-role="search" >
	    	<div class="form-group">
	    	
	      		<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
	      			<label for=s_busi_platform style="display:block">Busi. Platform No.</label>
	      			<acf:ComboBox id="s_busi_platform" name="busi_platform" editable="false" multiple="false"></acf:ComboBox>
	        	</div>
	        	
	    	</div>
		</form>
	</div>
</acf:Region>

<acf:Region id="reg_list" title="BUSINESS PLATFORM LIST" type="form">
	<div class="widget-main no-padding">
		<div class="col-md-12 form-padding">
			<acf:Grid id="grid_browse" url="apff007-search.ajax" readonly="true">
				<acf:Column name="busi_platform" caption="Busi. Platform" width="200"></acf:Column>
				<acf:Column name="busi_platform_desc" caption="Busi. Platform Desc." width="500"></acf:Column>
				<acf:Column name="prognat_dept" caption="Prog Nat./ Dept." width="200"></acf:Column>
				<acf:Column name="prognat_dept_desc" caption="Desc." width="500"></acf:Column>
			</acf:Grid>
	    </div>
	</div>
</acf:Region>

<acf:Region id="reg_form" title="BUSINESS PLATFORM FORM">
	<form id="frm_main" class="form-horizontal" data-role="form" >
		<div class="form-group">		
			<div class="col-xs-12 form-padding oneline">	
			
				<label class="control-label col-md-2" for="busi_platform">Busi. Platform No.</label>
		 		<div class="col-md-2">
		 			<acf:ComboBox id="f_busi_platform" name="busi_platform" editable="true" multiple="false"> 
						<acf:Bind on="initData"><script>
		 					$(this).acfComboBox("init", ${busiPlatform} );
		 				</script></acf:Bind>		 			
		 			</acf:ComboBox>
		 		</div>
			   	<%-- <acf:TextBox id="busi_platform" name="busi_platform"/>  --%>
	 		</div>

			<div class="col-xs-12 form-padding oneline">  	
				<label class="control-label col-md-2" for="busi_platform_desc">Busi. Platform Desc. </label>
		 		<div class="col-md-4">
			   		<acf:TextBox id="busi_platform_desc" name="busi_platform_desc"/> 
		 		</div>
	 		</div>
	
			<div class="col-xs-12 form-padding oneline">  	
				<label class="control-label col-md-2" for="prognat_dept">Prog. Nature/ Dept. No. </label>
		 		<div class="col-md-2">
			   		<acf:TextBox id="prognat_dept" name="prognat_dept"/> 
		 		</div>
	 		</div>

			<div class="col-xs-12 form-padding oneline">  	
				<label class="control-label col-md-2" for="prognat_dept_desc">Prog. Nature/ Dept. Desc. </label>
		 		<div class="col-md-4">
			   		<acf:TextBox id="prognat_dept_desc" name="prognat_dept_desc"/> 
		 		</div>
	 		</div>

	
			<div class="col-xs-12 form-padding oneline">  	
				<label class="control-label col-md-2" for="modified_at">Modified Date:</label>
		 		<div class="col-md-4">
			   		<acf:DateTime id="modified_at" name="modified_at" readonly="true"/>   
		 		</div>
				<label class="control-label col-md-2" for="created_at">Created Date:</label>
		 		<div class="col-md-4">
			   		<acf:DateTime id="created_at" name="created_at" readonly="true"/>  
		 		</div>
	 		</div>
		</div>
	</form>
</acf:Region>


<script>
// abc
Controller.setOption({
	searchForm:$("#frm_busisearch"),
	searchKey : "busi_platform",
	
	browseGrid: $("#grid_browse"),
	browseKey: "busi_platform",

	initMode: "",
	recordForm: $("#frm_main"),
	recordKey: {
		busi_platform: "${busi_platform}"
	},

	getUrl: "apff007-get-form.ajax",
	saveUrl: "apff007-save.ajax",

}).executeSearchBrowserForm();

</script>