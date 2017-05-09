<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acf" uri="/acf/tld/acf-taglib" %> 

<acf:Region id="reg_main" title="ITEM SEARCH" type="search">
	<div class="widget-main no-padding">
		<form id="frm_itemsearch" class="form-horizontal" data-role="search" >
	    	<div class="form-group">
	    	
	      		<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
	      			<label for=s_item_cat style="display:block">Item Category</label>
	      			<acf:ComboBox id="s_item_cat" name="item_cat" editable="true" multiple="false">
//	      				<acf:Bind on="initData"><script>
//		 					$(this).acfComboBox("init", ${itemcat} );
//		 				</script></acf:Bind>
		 			</acf:ComboBox>
	        	</div>
	        	
	      		<div class="col-lg-2 col-md-6 col-sm-6 col-xs-6">
	      			<label for=s_item_no style="display:block">Item Number</label>
	      			<acf:TextBox id="s_item_no" name="item_no"/>  
	      		</div>
	      		
	    	</div>
		</form>
	</div>
</acf:Region>

<acf:Region id="reg_list" title="ITEM LIST" type="form">
	<div class="widget-main no-padding">
		<div class="col-md-12 form-padding">
			<acf:Grid id="grid_browse" url="apff005-search.ajax" readonly="true">
				<acf:Column name="item_cat" caption="Item Cat." width="200"></acf:Column>
				<acf:Column name="item_no" caption="Item No." width="200"></acf:Column>
				<acf:Column name="item_desc" caption="Item Desc." width="500"></acf:Column>
			</acf:Grid>
	    </div>
	</div>
</acf:Region>

<acf:Region id="reg_form" title="ITEM FORM">
	<form id="frm_main" class="form-horizontal" data-role="form" >
		<div class="form-group">		
			<div class="col-xs-12 form-padding oneline">
				<label class="control-label col-md-2" for="item_cat">Item Category :</label>
		 		<div class="col-md-4">
		 			<acf:ComboBox id="f_item_cat" name="item_cat" editable="false" multiple="false">
	      				<acf:Bind on="initData"><script>
		 					$(this).acfComboBox("init", ${itemcat} );
		 				</script></acf:Bind>
		 			</acf:ComboBox>
		 		</div>
				<label class="control-label col-md-2" for="item_no">Item No. :</label>
		 		<div class="col-md-4">
			   		<acf:TextBox id="item_no" name="item_no"/>  
		 		</div>
	 		</div>
	
			<div class="col-xs-12 form-padding oneline">  	
				<label class="control-label col-md-2" for="func_name">Item Desc. :</label>
		 		<div class="col-md-4">
			   		<acf:TextBox id="item_desc" name="item_desc"/> 
		 		</div>
	 		</div>
	
			<div class="col-xs-12 form-padding oneline">  	
				<label class="control-label col-md-2" for="func_url">Unit :</label>
		 		<div class="col-md-4">
			   		<acf:TextBox id="unit" name="unit"/>
		 		</div>
				<label class="control-label col-md-2" for="func_seq">Unit Price :</label>
		 		<div class="col-md-4">
			   		<acf:TextBox id="unit_cost" name="unit_cost"/>
		 		</div>
	 		</div>
	
			<div class="col-xs-12 form-padding oneline">  	
				<label class="control-label col-md-2" for="func_icon_name">Safe Quantity:</label>
		   		<div class="col-md-4">
		   		    <acf:TextBox id="safe_qty" name="safe_qty"/>
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

<acf:Region id="reg_details" title="ITEM RECEIPTS JOURNAL">   	  	
    	<div class="col-md-12">	
	   		<acf:Grid id="grid_details" url="apff005-get-details.ajax" addable="true" editable="true" deletable="true" rowNum="9999" autoLoad="false" multiLineHeader="true">
	   			<acf:Column name="po_no" caption="PO No." type="text" width="120" editable="true" readonlyIfOld="true" checkMandatory="true" columnKey="true" forceCase="upper"></acf:Column>
				<acf:Column name="po_date" caption="PO Date" width="100" type="date" editable="true" checkMandatory="true"></acf:Column>
				<acf:Column name="order_qty" caption="Order Qty" width="80" editable="true" checkNumeric="true" allowKey="[0-9.]" checkMandatory="true" type="number" align="right" format="^[0-9]{1,4}(.[0-9]{1,2})?$"></acf:Column>
				<acf:Column name="receive_qty" caption="Receive Qty" width="80" editable="true" checkNumeric="true" allowKey="[0-9.]" checkMandatory="true" type="number" align="right" format="^[0-9]{1,4}(.[0-9]{1,2})?$"></acf:Column>
				<acf:Column name="modified_at" caption="Last Amend Date" width="100" type="date"></acf:Column>
			</acf:Grid>
		</div>		
</acf:Region>

<script>

Controller.setOption({
	searchForm:$("#frm_itemsearch"),
	searchKey : "item_cat",
	
	browseGrid: $("#grid_browse"),
	browseKey: "item_cat",

	initMode: "",
	recordForm: $("#frm_main"),
	recordKey: {
		item_cat: "${item_cat}"
	},

	getUrl: "apff005-get-form.ajax",
	saveUrl: "apff005-save.ajax",

}).executeSearchBrowserForm();

</script>
