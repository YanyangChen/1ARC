<!-- source_code.jsp BEG -->

<hr>
<button id="showJsp" class="btn btn-sm btn-primary" title="Show Jsp codes">
	<i class="ace-icon fa fa-pencil bigger-110 icon-only"></i>
	<span class="action-label">JSP</span>
</button>
<button id="showJava" class="btn btn-sm btn-primary" title="Show Java codes">
	<i class="ace-icon fa fa-pencil bigger-110 icon-only"></i>
	<span class="action-label">JAVA</span>
</button>

<script>

$("#showJsp").click(function() {	
	$.ajax({
		headers: {
			'Accept'       : 'application/json',
			'Content-Type' : 'application/json; charset=utf-8',
		},
		type : "POST",
		url : "${ctx}/cal/exe/exe-showJsp.ajax",
		data: JSON.stringify({
			'url': document.URL
		}),
		success: function(result,status,xhr) {
			Dialog.create()
				.setWidth(1250)
				.setCaption(result.filepath)
				.addDismissButton("OK")
				.showHtml("<pre class='prettyprint'>" + result.filebody + "</pre>");
			prettyPrint();
		}
	});
});

$("#showJava").click(function() {
	$.ajax({
		headers: {
			'Accept'       : 'application/json',
			'Content-Type' : 'application/json; charset=utf-8'
		},
		type : "POST",
		url : "${ctx}/cal/exe/exe-showJava.ajax",
		data: JSON.stringify({
			'func_id': document.URL.replace(/.*(exef...).*/,"$1") 
		}),
		success: function(result,status,xhr) {
			Dialog.create()
				.setWidth(1250)
				.setCaption(result.filepath)
				.addDismissButton("OK")
				.showHtml("<pre class='prettyprint'>" + result.filebody + "</pre>");
			prettyPrint();
		}
	});
});

</script>
<!-- source_code.jsp END -->
