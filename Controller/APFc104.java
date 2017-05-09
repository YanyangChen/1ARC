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
@ACFgFunction(id="APFF104")
@RequestMapping(value=APFtMapping.APFF104)
public class APFc104 extends ACFaAppController {
    
    @RequestMapping(value=APFtMapping.APFF104_MAIN, method=RequestMethod.GET)
    public String index(ModelMap model) throws Exception {
        return view();
    }   
    
    @ACFgTransaction
    @RequestMapping(value=APFtMapping.APFF104_SAVE_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception { 
        ACFgResponseParameters resParam = new ACFgResponseParameters();

        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        
        ass.setCustomSQL(ACFtUtility.getJavaResourceInString("/resource/apff501_get_report.sql"));
        List<ACFgRawModel> report = ass.executeQuery(getConnection("ARCDB"));
        
        //** set numeric display format for report
        java.text.DecimalFormat amountFormat = new java.text.DecimalFormat("####0.00");
        java.text.DecimalFormat totalAmountFormat = new java.text.DecimalFormat("###,##0.00");
        java.text.DecimalFormat pageNumberFormat = new java.text.DecimalFormat("00");

        StringBuilder pageStyle = new StringBuilder(); 
        pageStyle
        .append("<style type=\"text/css\">")
        .append("body {")
        .append("  background: rgb(204,204,204); ")
        .append("}")
        .append("page {")
        .append("  background: white;")
        .append("  display: block;")
        .append("  margin: 0 auto;")
        .append("  margin-bottom: 0.5cm;")
        .append("  box-shadow: 0 0 0.5cm rgba(0,0,0,0.5);")
        .append("}")
        .append("page[size=\"A4\"] {  ")
        .append("  width: 21cm;")
        .append("  height: 29.7cm; ")
        .append("}")
        .append("page[size=\"A4\"][layout=\"landscape\"] {")
        .append("  width: 29.7cm;")
        .append("  height: 21cm;  ")
        .append("}")
        .append("page[size=\"A3\"] {")
        .append("  width: 29.7cm;")
        .append("  height: 42cm;")
        .append("}")
        .append("page[size=\"A3\"][layout=\"landscape\"] {")
        .append("  width: 42cm;")
        .append("  height: 29.7cm;  ")
        .append("}")
        .append("page[size=\"A5\"] {")
        .append("  width: 14.8cm;")
        .append("  height: 21cm;")
        .append("}")
        .append("page[size=\"A5\"][layout=\"landscape\"] {")
        .append("  width: 21cm;")
        .append("  height: 14.8cm;  ")
        .append("}")
        .append("@media print {")
        .append("  body, page {")
        .append("    margin: 0;")
        .append("    box-shadow: 0;")
        .append("  }")
        .append("  html, body {height: 99%;}")
        .append("}")
        .append("</style>");
        
        StringBuilder tableStyle = new StringBuilder();
        tableStyle
        .append("<style type=\"text/css\">")
        /* Column widths are based on these cells */
        .append(".col-po_no {")
        .append("  width: 18mm;")
        .append("}")
        .append(".col-particulars {")
        .append("  width: 50mm;")
        .append("}")
        .append(".col-epi_num {")
        .append("  width: 46mm;")
        .append("}")
        .append(".col-amount {")
        .append("  width: 45mm;")
        .append("}")
        .append(".col-remarks {")
        .append("  width: 48mm;")
        .append("}")
        .append(".section-col-1 {")
        .append("  width: 40mm;")
        .append("}")
        .append(".section-col-2 {")
        .append("  width: 45mm;")
        .append("}")
        .append(".section-col-3 {")
        .append("  width: 80mm;")
        .append("}")
        .append(".section-col-4 {")
        .append("  width: 40mm;")
        .append("}")
        /* Overall table layout */
        .append("table.table-fixed {")
        .append("  table-layout: fixed;")
        .append("}")
        .append("table.table-fixed tr.section {")
        .append("  height: 9mm;")
        .append("}")
        .append("table.table-fixed tr.item {")
        .append("  height: 4mm;")
        .append("}")
        /* Overall table font */
        .append(".table-font {")
        .append("  font-family:\"courier new\",monospace;")
        .append("  font-size:0.8em; <font color='red'>/* Never set font sizes in pixels! */</font>")
        .append("  color:#00f;")
        .append("}")
        /* Empty line CSS */
        .append("p.pg-thin {  margin-top: 0.5em; margin-bottom: 0em; }")
        .append("</style>");
        
        /* 
         * Variable substitution format = {variable_name}. Must be a unique variable name for each piece of HTML code insert.
         */
        String V_PAGE_NUMBER = "{page_number}";
        String V_DEPARTMENT = "{department}";
        String V_DATE = "{date}";
        String V_REFERENCE_NO = "{reference_no}";
        String V_DDS_CODE = "{dds_code}";
        String V_PAYEE = "{payee}";;
        String V_PROGRAMME_NAME = "{programme_name}";
        String V_EPI_NUM = "{epi_num}";
        String V_OTHERS = "{others}";
        String V_PURPOSE = "{purpose}";
        String V_ITEM_PO_NO = "{item_po_no}";
        String V_ITEM_PARTICULARS = "{item_particulars}";
        String V_ITEM_EPI_NUM = "{item_epi_num}";
        String V_ITEM_AMOUNT = "{item_amount}";
        String V_ITEM_AMOUNT_TOTAL = "{item_amount_total}";
        String V_ITEM_ACC_ALLOC = "{item_acc_alloc}";
        
        /* Variable content table header */
        StringBuilder tableHeader = new StringBuilder();
        tableHeader
        .append("<p class=\"pg-thin\"></p>")
        .append("<table class=\"table-fixed table-font\">")
        .append("<tbody>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"section-col-2\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"section-col-3\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"section-col-4\" style=\"text-align: left;\">PAGE"+V_PAGE_NUMBER+"</td>")
            .append("</tr>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"section-col-4\" style=\"text-align: left;\">"+V_DATE+"</td>")
            .append("</tr>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"section-col-4\" style=\"text-align: left;\">"+V_REFERENCE_NO+"</td>")
            .append("</tr>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td style=\"text-align: left;\">"+V_DEPARTMENT+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"section-col-4\" style=\"text-align: left;\">"+V_DDS_CODE+"</td>")
            .append("</tr>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td colspan=\"3\" style=\"word-break: break-all\">"+V_PAYEE+"</td>")
            .append("</tr>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("</tr>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td colspan=\"2\" style=\"text-align: left;\">"+V_PROGRAMME_NAME+"</td>")
            .append("<td class=\"section-col-4\" style=\"text-align: left;\">"+V_EPI_NUM+"</td>")
            .append("</tr>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td colspan=\"2\" style=\"text-align: left;\">"+V_OTHERS+"</td>")
            .append("</tr>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td colspan=\"3\" style=\"word-break: break-all\">"+V_PURPOSE+"</td>")
            .append("</tr>")
            .append("<tr class=\"section\">")
            .append("<td class=\"section-col-1\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td>"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("</tr>")
        .append("</tbody>")
        .append("</table>");
        
        /* Fixed content column header */
        StringBuilder columnHeader = new StringBuilder();
        columnHeader
        .append("<tr class=\"item\">")
        .append("<td class=\"col-po_no\" style=\"text-align: center;\">P.O.NO</td>")
        .append("<td class=\"col-particulars\" style=\"text-align: left;\">PARTICULARS</td>")
        .append("<td class=\"col-epi_num\" style=\"text-align: left;\">EPI#</td>")
        .append("<td class=\"col-amount\" style=\"text-align: right; padding-right: 5px;\">AMOUNT(HK$)</td>")
        .append("<td class=\"col-remarks\" style=\"text-align: left; padding-left: 10px;\">ACCOUNT ALLOCATION</td>")
        .append("</tr>")
        .append("<tr class=\"item\">")
        .append("<td class=\"col-po_no\" style=\"text-align: center;\">------</td>")
        .append("<td class=\"col-particulars\" style=\"text-align: left;\">-----------</td>")
        .append("<td class=\"col-epi_num\" style=\"text-align: left;\">----</td>")
        .append("<td class=\"col-amount\" style=\"text-align: right; padding-right: 5px;\">-----------</td>")
        .append("<td class=\"col-remarks\" style=\"text-align: left; padding-left: 10px;\">------------------</td>")
        .append("</tr>");
        
        /* Fixed content page footer */
        StringBuilder pageFooter = new StringBuilder();
        pageFooter
        .append("<table class=\"table-fixed table-font\">")
        .append("<tbody>")
            .append("<tr class=\"item\">")
            .append("<td class=\"col-po_no\" style=\"text-align: center;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"col-particulars\" style=\"text-align: left;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"col-epi_num\" style=\"text-align: left;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"col-amount\" style=\"text-align: right; padding-right: 5px;\">---------</td>")
            .append("<td class=\"col-remarks\" style=\"text-align: left; padding-left: 10px;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("</tr>")
            .append("<tr class=\"item\">")
            .append("<td class=\"col-po_no\" style=\"text-align: center;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"col-particulars\" style=\"text-align: left;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"col-epi_num\" style=\"text-align: left;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"col-amount\" style=\"text-align: right; padding-right: 5px;\">"+V_ITEM_AMOUNT_TOTAL+"</td>")
            .append("<td class=\"col-remarks\" style=\"text-align: left; padding-left: 10px;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("</tr>")
            .append("<tr class=\"item\">")
            .append("<td class=\"col-po_no\" style=\"text-align: center;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"col-particulars\" style=\"text-align: left;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"col-epi_num\" style=\"text-align: left;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("<td class=\"col-amount\" style=\"text-align: right; padding-right: 5px;\">---------</td>")
            .append("<td class=\"col-remarks\" style=\"text-align: left; padding-left: 10px;\">"+APFtUtilityAndGlobal.HTML_SPACE+"</td>")
            .append("</tr>")
        .append("</tbody>")
        .append("</table>");
        
        /* Variable content item row */
        StringBuilder itemRow = new StringBuilder();
        itemRow
        .append("<tr class=\"item\">")
        .append("<td class=\"col-po_no\" style=\"text-align: center;\">"+V_ITEM_PO_NO+"</td>")
        .append("<td class=\"col-particulars\" style=\"text-align: left;\">"+V_ITEM_PARTICULARS+"</td>")
        .append("<td class=\"col-epi_num\" style=\"text-align: left;\">"+V_ITEM_EPI_NUM+"</td>")
        .append("<td class=\"col-amount\" style=\"text-align: right; padding-right: 5px;\">"+V_ITEM_AMOUNT+"</td>")
        .append("<td class=\"col-remarks\" style=\"text-align: left; padding-left: 10px;\">"+V_ITEM_ACC_ALLOC+"</td>")
        .append("</tr>");
        
        
        //** construct final HTML result codes
        StringBuilder html = new StringBuilder(); 
        html.append(pageStyle);
        html.append(tableStyle);
        html.append("<page size=\"A4\">");
        
        int dataRowPerPage = 25;
        int row_cnt = 1;
        int cur_row = 1;
        int page_num = 1;
        
        //** loop through the data result set to construct list item HTML codes
        for (ACFgRawModel row : report) {
            if (cur_row == 1) {
                html.append(new StringBuilder(tableHeader));
                APFtUtilityAndGlobal.m_replace(V_PAGE_NUMBER, pageNumberFormat.format(page_num), html);
                APFtUtilityAndGlobal.m_replace(V_DATE, ACFtUtility.timestampToString(row.getTimestamp("report_date"),"dd MMMM yyyy"), html);
                APFtUtilityAndGlobal.m_replace(V_REFERENCE_NO, row.getString("report_no"), html);
                APFtUtilityAndGlobal.m_replace(V_DEPARTMENT, "ART ADMIN", html);
                APFtUtilityAndGlobal.m_replace(V_DDS_CODE, "A301/01", html);
                APFtUtilityAndGlobal.m_replace(V_PAYEE, "MAN YUEN FLOWER SHOP", html);
                APFtUtilityAndGlobal.m_replace(V_PROGRAMME_NAME, "MASTER WONG FEI HUNG", html);
                APFtUtilityAndGlobal.m_replace(V_EPI_NUM, "123-456", html);
                APFtUtilityAndGlobal.m_replace(V_OTHERS, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", html);
                APFtUtilityAndGlobal.m_replace(V_PURPOSE, "ART-PROPS & SETTING EXPENSES", html);
                html
                .append("<table class=\"table-fixed table-font\">")
                .append("<tbody>")
                .append("<p class=\"pg-thin\"></p>")
                .append(columnHeader);
            }
            
            /* insert new item row */
            html.append(new StringBuilder(itemRow));
            APFtUtilityAndGlobal.m_replace(V_ITEM_PO_NO, ""+row.getInteger("val_integer"), html);
            APFtUtilityAndGlobal.m_replace(V_ITEM_PARTICULARS, row.getString("report_desc"), html);
            APFtUtilityAndGlobal.m_replace(V_ITEM_EPI_NUM, "176-180", html);
            APFtUtilityAndGlobal.m_replace(V_ITEM_AMOUNT, amountFormat.format(row.getBigDecimal("val_decimal")), html);
            APFtUtilityAndGlobal.m_replace(V_ITEM_ACC_ALLOC, "THE EXORCIST'S METER", html);
            
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
                    .append(new StringBuilder(pageFooter));
                    APFtUtilityAndGlobal.m_replace(V_ITEM_AMOUNT_TOTAL, totalAmountFormat.format(8929.10d), html);
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