<%@ page import="acf.acf.General.jsp.*"%>
<%@ page import="acf.acf.General.core.ACFgRawModel" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="acf" uri="/acf/tld/acf-taglib" %> 

 
<!-- PAGE CONTENT BEGINS -->
<form id="frm_programme_search" class="form-horizontal" data-role="form" >
   	<div class="form-group">
   		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
   			<label for=prog_no style="display:block">Programme No.</label>
   			<acf:TextBox id="pgm_num" name="pgm_num">		
				<acf:Bind target="textBox" on="enter"><script>
					programmeSearch();
				</script></acf:Bind>
   			</acf:TextBox>
       	</div> 
       	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
   			<label for=prog_name style="display:block">Clean Eng Title</label>
   			<acf:TextBox id="clean_eng_title" name="clean_eng_title">
   				<acf:Bind target="textBox" on="enter"><script>
					programmeSearch();
				</script></acf:Bind>
   			</acf:TextBox>
       	</div>  
       	<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
   			<label for=prog_name style="display:block">Clean Chi Title</label>
   			<acf:TextBox id="clean_chi_title" name="clean_chi_title">
   				<acf:Bind target="textBox" on="enter"><script>
					programmeSearch();
				</script></acf:Bind>
   			</acf:TextBox>
       	</div>   
       	<div class="search-button col-lg-2 col-md-3 col-sm-6 col-xs-12">
			<label style="display:block">&nbsp;</label>
            <span class="btn-group">
				<acf:Button id="dialog_search_btn" title="Search" icon="fa-search">
					<acf:Bind on="click"><script>
						programmeSearch();
					</script></acf:Bind>
				</acf:Button>
				<acf:Button id="dialog_clear_btn" title="" icon="fa-eraser">
					<acf:Bind on="click"><script>
						$('#frm_programme_search').pSearch$clear();
						programmeSearch();
					</script></acf:Bind>
				</acf:Button>
			</span>
		</div>    	
   	</div>	    	
    <input type="hidden" id="dialog_search" name="dialog_search" value="1"/>
</form>
	
<div class="col-md-12 float-none">
	<acf:Grid id="grid_cpl_programme_browse" url="../../apf/apff011/apff011-cpl-prog-search.ajax" rowNum="12" readonly="true" autoLoad="false">
		<acf:Column name="pgm_num" caption="Programme No." width="150"></acf:Column>
		<acf:Column name="clean_eng_title" caption="Clean English Title" width="300"></acf:Column>
		<acf:Column name="clean_chi_title" caption="Clean Chinese Title" width="200"></acf:Column>
		<acf:Bind on="selectRow"><script>
			record = $(this).pGrid$getSelectedRecord();
			result = record;
			Dialog.get().setResult(result);
		</script></acf:Bind>
		<acf:Bind on="init"><script>
			Dialog.get().resize();
			$(window).trigger('resize');
		</script></acf:Bind>
	</acf:Grid>
   </div>

<script>
function programmeSearch() {
	var parameters = $("#frm_programme_search").pSearch$getCriteria();
	$("#grid_cpl_programme_browse").pGrid$addCriteria(parameters);
	$("#grid_cpl_programme_browse").pGrid$loadRecord();
};

programmeSearch();

</script>
