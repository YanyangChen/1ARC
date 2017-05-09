package arc.apf.Controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import acf.acf.Abstract.ACFaAppController;
import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.annotation.ACFgFunction;
import acf.acf.General.annotation.ACFgTransaction;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.General.core.ACFgRequestParameters;
import acf.acf.General.core.ACFgResponseParameters;
import acf.acf.Static.ACFtUtility;
import arc.apf.Static.APFtMapping;
import arc.apf.Static.APFtUtilityAndGlobal;

@Controller
@Scope("session")
@ACFgFunction(id="APFF601")
@RequestMapping(value=APFtMapping.APFF601)
public class APFc601 extends ACFaAppController {
    
    @RequestMapping(value=APFtMapping.APFF601_MAIN, method=RequestMethod.GET)
    public String index(ModelMap model) throws Exception {
        return view();
    }   
    
	@ACFgTransaction
	@RequestMapping(value=APFtMapping.APFF601_SAVE_AJAX, method=RequestMethod.POST)
	@ResponseBody
	public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception {	
		ACFgResponseParameters resParam = new ACFgResponseParameters();

        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        
        ass.setCustomSQL(ACFtUtility.getJavaResourceInString("/resource/apff601_get_report.sql"));
        List<ACFgRawModel> report = ass.executeQuery(getConnection("ARCDB"));
        
        //** set numeric display format for report
        java.text.DecimalFormat quantityFormat = new java.text.DecimalFormat("##,##0");
        java.text.DecimalFormat unitPriceFormat = new java.text.DecimalFormat("##,##0.00");
        java.text.DecimalFormat totalPriceFormat = new java.text.DecimalFormat("###,##0.00");
        java.text.DecimalFormat pageNumberFormat = new java.text.DecimalFormat("00");

        /*
         * We used CSS (Cascade Style Sheet) to control the layout of HTML page elements.
         * Therefore, besides being used to build HTML pages at server side, it is used solely for web browser such as JSP.
         * @link CSS Tutorial     --- http://www.w3schools.com/css/default.asp
         * @link CSS3 @media Rule --- http://www.w3schools.com/cssref/css3_pr_mediaquery.asp
         *                        --- http://www.w3schools.com/css/css_rwd_mediaqueries.asp
         */
        
        /* 
         * Define CSS for table border and font. This CSS affect all tables in HTML that used it.
         * @link CSS Paged Media - @page Rule --- https://www.tutorialspoint.com/css/css_paged_media.htm
         *                                    --- https://www.w3.org/TR/CSS2/page.html
         *                                    --- https://www.w3.org/TR/css3-page/
         * @link CSS - Measurement Units      --- http://www.w3schools.com/cssref/css_units.asp
         *                                    --- https://www.tutorialspoint.com/css/css_measurement_units.htm
         */
        StringBuilder pageStyle = new StringBuilder(); 
        pageStyle
        .append("<style type=\"text/css\">")
        .append("body {")
        .append("  background: rgb(204,204,204); ")
        .append("}")
        /* define overall page attributes */
        .append("page {")
        .append("  background: white;")
        .append("  display: block;")
        .append("  margin: 0 auto;")
        .append("  margin-bottom: 0.5cm;")
        .append("  box-shadow: 0 0 0.5cm rgba(0,0,0,0.5);")
        .append("}")
        /* define page size for A4 portrait */
        .append("page[size=\"A4\"] {  ")
        .append("  width: 21cm;")
        .append("  height: 29.7cm; ")
        .append("}")
        /* define page size for A4 landscape */
        .append("page[size=\"A4\"][layout=\"landscape\"] {")
        .append("  width: 29.7cm;")
        .append("  height: 21cm;  ")
        .append("}")
        /* define page size for A3 portrait */
        .append("page[size=\"A3\"] {")
        .append("  width: 29.7cm;")
        .append("  height: 42cm;")
        .append("}")
        /* define page size for A3 landscape */
        .append("page[size=\"A3\"][layout=\"landscape\"] {")
        .append("  width: 42cm;")
        .append("  height: 29.7cm;  ")
        .append("}")
        /* define page size for A5 portrait */
        .append("page[size=\"A5\"] {")
        .append("  width: 14.8cm;")
        .append("  height: 21cm;")
        .append("}")
        /* define page size for A5 landscape */
        .append("page[size=\"A5\"][layout=\"landscape\"] {")
        .append("  width: 21cm;")
        .append("  height: 14.8cm;  ")
        .append("}")
        /* define print attributes using above definition */
        .append("@media print {")
        .append("  body, page {")
        .append("    margin: 0;")
        .append("    box-shadow: 0;")
        .append("  }")
        .append("}")
        .append("</style>");
        
        /* 
         * Define CSS for table border and font. This CSS affect all tables in HTML that used it.
         * # CSS can be used stand alone in each HTML elements that support CSS.
         */
		StringBuilder tableStyle = new StringBuilder();
		tableStyle
		.append("<style type=\"text/css\">")
		/* Overall table font used */
    	.append(".table-font {")
        .append("  font-family:Arial,Verdana,sans-serif;")
        .append("  font-size:0.8em; <font color='red'>/* Never set font sizes in pixels! */</font>")
        .append("  color:#00f;")
        .append("}")
        /* Normal table */
        .append("table.table-normal {")
        .append("  border-width: 1px;")
        .append("  border-spacing: 0px;")
        .append("  border-style: solid;")
        .append("  border-color: black;")
        .append("  border-collapse: separate;")
        .append("  background-color: white;")
        .append("}")
        /* Table header */
        .append("table.table-normal th {")
        .append("  border-width: 1px;")
        .append("  padding: 1px;")
        .append("  border-style: solid;")
        .append("  border-color: black;")
        .append("  background-color: white;")
        .append("  -moz-border-radius: ;")
        .append("}")
        /* Table column */
        .append("table.table-normal td {")
        .append("  border-width: 1px;")
        .append("  padding: 1px;")
        .append("  border-style: solid;")
        .append("  border-color: black;")
        .append("  background-color: white;")
        .append("  -moz-border-radius: ;")
        .append("}")
        .append("")
        /* Outline table */
        .append("table.table-outline {")
        .append("  border-width: 1px;")
        .append("  border-spacing: 0px;")
        .append("  border-style: solid;")
        .append("  border-color: black;")
        .append("  border-collapse: separate;")
        .append("  background-color: white;")
        .append("}")
        /* row top border */
        .append("table.table-outline tr.top td {")
        .append("  border-top: thin solid black;")
        .append("}")
        /* row bottom border */
        .append("table.table-outline tr.bottom td {")
        .append("  border-bottom: thin solid black;")
        .append("}")
        /* row first column border */
        .append("table.table-outline tr.row td:first-child {")
        .append("  border-left: thin solid black;")
        .append("}")
        /* row last column border */
        .append("table.table-outline tr.row td:last-child {")
        .append("  border-right: thin solid black;")
        .append("}")
        /* Empty line using Paragraph tag */
        .append("p.pg-thin {  margin-top: 0.5em; margin-bottom: 0em; }")
        .append("</style>");
		
		/* 
		 * Since HTML is implemented using plain text, you can use variable substitution to dynamically change the HTML content.
		 * Recommended variable substitution format = {variable name} / $(variable name) 
		 * Must be a unique variable name for each piece of HTML code insert since finally they will stick together into one string.
		 * DO NOT use variable name that easily confuse with HTML tag !
		 */
		String V_PO_NUMBER = "{po_number}";
		String V_SECTION = "{section}";
		String V_RECOMMENDED_SUPPLIER = "{recommended_supplier}";
		String V_DELIVERY_ADDRESS = "{delivery_address}";
        String V_OTHERS = "{others}";
        String V_REFERENCE_NO = "{reference_no}";
        String V_PAGE_NUMBER = "{page_number}";
        String V_ITEM_NO = "{item_no}";
        String V_ITEM_DESC = "{item_desc}";
        String V_ITEM_PRICE = "{item_price}";
        String V_ITEM_QUANTITY = "{item_quantity}";
        String V_ITEM_UNIT = "{item_unit}";
        String V_ITEM_TOTAL = "{item_total}";
		
		/* Variable content table header */
		StringBuilder tableHeader = new StringBuilder();
		tableHeader
		/* Table begin tags */
        .append("<table class=\"table-outline table-font\" style=\"width: 100%; margin-left: auto; margin-right: auto;\">")
        .append("<tbody>")
            /* insert row using CSS class top & row */
            .append("<tr class=\"top row\">")
            /* insert 1 column that merged 4 columns */
            .append("<td style=\"text-align: right; vertical-align:top; padding: 5px\" colspan=\"4\"><img src=\""+APFtUtilityAndGlobal.APF_TVB_LOGO_URL+"\" alt=\"TVB Logo\" width=\"30\" height=\"30\" />Television Broadcasts Limited<br />\u96fb\u8996\u5ee3\u64ad\u6709\u9650\u516c\u53f8</td>")
            .append("</tr>")
            /* insert row using CSS class row */
            .append("<tr class=\"row\">")
            .append("<td colspan=\"2\">\u7f8e\u8853\u5206\u90e8\u5b58\u5009\u8ca8\u8acb\u8cfc\u8868</td>")
            /* P.O. No. display using font different from default font */
            .append("<td style=\"text-align: right; font-family:\"Times New Roman\"\"> P.O. No.:</td>")
            .append("<td style=\"font-family:\"Times New Roman\"\">"+V_PO_NUMBER+"</td>")
            .append("</tr>")
            /* insert row using CSS class row */
            .append("<tr class=\"row\">")
            .append("<td style=\"text-align: right;\"> \u7d44\u5225 :</td>")
            .append("<td colspan=\"3\">"+V_SECTION+"</td>")
            .append("</tr>")
            /* insert row using CSS class row */
            .append("<tr class=\"row\">")
            .append("<td colspan=\"4\">      </td>")
            .append("</tr>")
            /* insert row using CSS class row */
            .append("<tr class=\"row\">")
            .append("<td style=\"text-align: right;\"> \u5efa\u8b70\u4f9b\u61c9\u5546 : <br />     </td>")
            /* force break a lengthy word even no space character in between */
            .append("<td colspan=\"3\" style=\"word-break: break-all\">"+V_RECOMMENDED_SUPPLIER+"</td>")
            .append("</tr>")
            /* insert row using CSS class row */
            .append("<tr class=\"row\">")
            .append("<td style=\"text-align: right;\"> \u6536\u8ca8\u5730\u9ede :</td>")
            .append("<td colspan=\"3\">"+V_DELIVERY_ADDRESS+"</td>")
            .append("</tr>")
            /* insert row using CSS class row */
            .append("<tr class=\"row\">")
            .append("<td> </td>")
            .append("<td colspan=\"3\"> \u5176\u4ed6 "+V_OTHERS+"</td>")
            .append("</tr>")
            /* insert row using CSS class row */
            .append("<tr class=\"row\">")
            .append("<td style=\"width: 20%;\"> </td>")
            .append("<td style=\"width: 60%;\"> </td>")
            .append("<td style=\"text-align: right; width: 5%;\"> Ref. No. :</td>")
            .append("<td style=\"width: 10%;\">"+V_REFERENCE_NO+"</td>")
            .append("</tr>")
            /* insert row using CSS class row */
            .append("<tr class=\"row\">")
            .append("<td> </td>")
            .append("<td> </td>")
            .append("<td style=\"text-align: right;\">Page :</td>")
            .append("<td>"+V_PAGE_NUMBER+"</td>")
            .append("</tr>")
        /* Table end tags */
        .append("</tbody>")
        .append("</table>");
		
		/* Fixed content page footer */
		StringBuilder pageFooter = new StringBuilder();
		pageFooter
		.append("<table class=\"table-font\" style=\"width: 100%; margin-left: auto; margin-right: auto;\">")
		.append("<tbody>")
    		.append("<tr>")
    		.append("<td style=\"width: 10%;\">\u8acb\u8cfc\u8005</td>")
    		.append("<td style=\"width: 1%;\">:</td>")
    		.append("<td style=\"width: 39%;\">______________________________</td>")
    		.append("<td style=\"width: 10%;\">\u8acb\u8cfc\u65e5\u671f</td>")
    		.append("<td style=\"width: 1%;\">:</td>")
    		.append("<td style=\"width: 39%;\">______________________________</td>")
    		.append("</tr>")
    		.append("<tr>")
    		.append("<td>\u8a02\u8ca8\u4eba</td>")
    		.append("<td>:</td>")
    		.append("<td>______________________________</td>")
    		.append("<td>\u6aa2\u6838</td>")
    		.append("<td>:</td>")
    		.append("<td>______________________________</td>")
    		.append("</tr>")
    		.append("<tr>")
    		.append("<td>\u8a02\u8ca8\u65e5\u671f</td>")
    		.append("<td>:</td>")
    		.append("<td>______________________________</td>")
    		.append("<td>\u6279\u51c6</td>")
    		.append("<td>:</td>")
    		.append("<td>______________________________</td>")
    		.append("</tr>")
		.append("</tbody>")
		.append("</table>");
		
		/* Fixed content column header */
		StringBuilder columnHeader = new StringBuilder();
		columnHeader
		.append("<tr>")
		.append("<td> </td>")
		.append("<td style=\"text-align: center;\">\u8ca8\u54c1\u540d\u7a31</td>")
		.append("<td style=\"text-align: center;\">\u55ae\u50f9</td>")
		.append("<td style=\"text-align: center;\">\u6578\u91cf</td>")
		.append("<td style=\"text-align: center;\">\u55ae\u4f4d</td>")
		.append("<td style=\"text-align: center;\">\u5408\u8a08(HK$)</td>")
		.append("</tr>");
		
		/* Variable content item row */
		StringBuilder itemRow = new StringBuilder();
		itemRow
        .append("<tr>")
        .append("<td style=\"text-align: center;\">"+V_ITEM_NO+"</td>")
        .append("<td>"+V_ITEM_DESC+"</td>")
        .append("<td style=\"text-align: right;\">"+V_ITEM_PRICE+"</td>")
        .append("<td style=\"text-align: right;\">"+V_ITEM_QUANTITY+"</td>")
        .append("<td style=\"text-align: center;\">"+V_ITEM_UNIT+"</td>")
        .append("<td style=\"text-align: right;\">"+V_ITEM_TOTAL+"</td>")
        .append("</tr>");
		
		
        //** construct final HTML result codes
        StringBuilder html = new StringBuilder(); 
        html.append(pageStyle);
        html.append(tableStyle);
        html.append("<page size=\"A4\">");
        
		int dataRowPerPage = 3;
		int row_cnt = 1;
		int cur_row = 1;
		int page_num = 1;
		
        //** loop through the data result set to construct list item HTML codes
        for (ACFgRawModel row : report) {
            if (cur_row == 1) {
                html.append(new StringBuilder(tableHeader));
                APFtUtilityAndGlobal.m_replace(V_PO_NUMBER, "XXXXXXXXXXXXX", html);
                APFtUtilityAndGlobal.m_replace(V_REFERENCE_NO, "XXXXXXXXXXXXX", html);
                APFtUtilityAndGlobal.m_replace(V_SECTION, "XXXXXXXXXXXXX", html);
                APFtUtilityAndGlobal.m_replace(V_RECOMMENDED_SUPPLIER, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", html);
                APFtUtilityAndGlobal.m_replace(V_DELIVERY_ADDRESS, "\u5c07\u8ecd\u6fb3", html);
                APFtUtilityAndGlobal.m_replace(V_OTHERS, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", html);
                APFtUtilityAndGlobal.m_replace(V_PAGE_NUMBER, pageNumberFormat.format(page_num), html);
                html
                .append("<table class=\"table-normal table-font\" style=\"width: 100%; border: 1px solid black;\">")
                .append("<tbody>")
                .append(columnHeader);
            }
            
            /* insert new item row */
            html.append(new StringBuilder(itemRow));
            APFtUtilityAndGlobal.m_replace(V_ITEM_NO, pageNumberFormat.format(row_cnt), html);
            APFtUtilityAndGlobal.m_replace(V_ITEM_DESC, row.getString("item_desc"), html);
            APFtUtilityAndGlobal.m_replace(V_ITEM_PRICE, "$"+APFtUtilityAndGlobal.m_Lpad(unitPriceFormat.format(row.getBigDecimal("unit_cost"))," ",9), html);
            APFtUtilityAndGlobal.m_replace(V_ITEM_QUANTITY, APFtUtilityAndGlobal.m_Lpad(quantityFormat.format(row.getBigDecimal("order_qty"))," ",5), html);
            APFtUtilityAndGlobal.m_replace(V_ITEM_UNIT, row.getString("un_it"), html);
            APFtUtilityAndGlobal.m_replace(V_ITEM_TOTAL, "$"+APFtUtilityAndGlobal.m_Lpad(totalPriceFormat.format(row.getBigDecimal("ttl_cost"))," ",9), html);
            
            //** if it is the last data row of page or report, finalize the page and insert into HTML
            if (cur_row == dataRowPerPage || row_cnt == report.size()) {
                cur_row = 1;
                page_num++;
                
                html
                .append(APFtUtilityAndGlobal.HTML_TBODY_END)
                .append(APFtUtilityAndGlobal.HTML_TABLE_END);

                //** insert page footer
                if (row_cnt == report.size()) {
                    html
                    .append("<p class=\"pg-thin\"></p>")
                    .append(new StringBuilder(pageFooter));
                } else {
                    html.append(APFtUtilityAndGlobal.HTML_PAGE_BREAK);
                }
            } else {
                cur_row++;
            }
            
            row_cnt++;
        }
        
        html.append("</page>");
    
		String report_date = "Date: "+param.get("p_report_date", String.class).trim();
		
		resParam.put("report", html.toString());
		
		return resParam;
	}
}