package arc.apf.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;

import acf.acf.Dao.ACFoList;
import acf.acf.Dao.ACFoReport;
import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.annotation.ACFgFunction;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.General.core.ACFgRequestParameters;
import acf.acf.General.core.ACFgResponseParameters;
import acf.acf.Service.ACFsReport;
import acf.acf.Static.ACFtUtility;
import acf.acf.Abstract.ACFaAppController;
import arc.apf.Service.APFsSection;
import arc.apf.Static.APFtMapping;
import arc.apf.Static.APFtUtilityAndGlobal;
import arc.apf.General.report.ARCgPDFGenerator;

@Controller
@Scope("session")
@ACFgFunction(id="APFF501")
@RequestMapping(value=APFtMapping.APFC501)
public class APFc501 extends ACFaAppController {
	
	@Autowired ACFoList listDao;
	@Autowired ACFoReport reportDao;
	
	@Autowired ACFsReport reportService;
	@Autowired APFsSection sectionService;
	
    ARCgPDFGenerator pdf;

    @RequestMapping(value=APFtMapping.APFC501_MAIN, method=RequestMethod.GET)
	public String index(ModelMap model) throws Exception {
		model.addAttribute("report_formats", reportService.getReportAllowExt("APFF501"));
		return view();
	}	
	
	@RequestMapping(value=APFtMapping.APFC501_REPORT, method=RequestMethod.POST)
	@ResponseBody
	public ACFgResponseParameters generateReport(@RequestBody ACFgRequestParameters param) throws Exception {
		
		ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
		ass.setCustomSQL(ACFtUtility.getJavaResourceInString("/resource/apff501_get_section_id.sql"));
		List<ACFgRawModel> result = ass.executeQuery(getConnection("ARCDB"));
		
        ass.setCustomSQL(ACFtUtility.getJavaResourceInString("/resource/apff501_get_report.sql"));
        List<ACFgRawModel> report = ass.executeQuery(getConnection("ARCDB"));
		
        try {
            //** set fonts for report
            Font fontbold = ARCgPDFGenerator.createFont(ARCgPDFGenerator.PdfFontFamily.COURIER, 5, Font.BOLD, new BaseColor(0, 0, 0));
            Font font = ARCgPDFGenerator.createFont(ARCgPDFGenerator.PdfFontFamily.COURIER, 5, Font.NORMAL);
            Font chinesefont = ARCgPDFGenerator.createFont(ARCgPDFGenerator.PdfFontFamily.MING_LIU, 5, Font.NORMAL);
            //** set numeric display format for report
            java.text.DecimalFormat df = new java.text.DecimalFormat("###,##0");
            java.text.DecimalFormat pf = new java.text.DecimalFormat("000");

            Rectangle cellpadding = new Rectangle(1f,3f,0f,0f);

            pdf = new ARCgPDFGenerator(reportDao.selectItem("APFF501").rpt_path+"/"+reportDao.selectItem("APFF501").filename + ".pdf", ARCgPDFGenerator.PdfPageSize.A4_LANDSCAPE);
            
            List<ARCgPDFGenerator.ARCgPdfTable> page = new ArrayList<ARCgPDFGenerator.ARCgPdfTable>();
            ARCgPDFGenerator.ARCgPdfTable table = new ARCgPDFGenerator.ARCgPdfTable();
            List<List<ARCgPDFGenerator.ARCgPdfCell>> tableContent = new ArrayList<List<ARCgPDFGenerator.ARCgPdfCell>>();
            List<ARCgPDFGenerator.ARCgPdfCell> tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();

            //** set page header (optional)
            setPageHeader();
            
            //** set data rows per page to print
            int dataRowsPerPage = 2;
            //** incremental counter for page number printing
            int page_num = 1;
            //** incremental counter for tracking rows printed
            int curRow = 1;
            
            //** calculate character width according to font settings
            float charWidth = font.getCalculatedBaseFont(true).getWidthPoint("W", font.getCalculatedSize());
            float chineseCharWidth = chinesefont.getCalculatedBaseFont(true).getWidthPoint("\u96fb", chinesefont.getCalculatedSize());
            
            //** calculate column width according to maximum number of characters of each column
            float[] absoluteWidths = {"DD/MM/CCYY".length()*charWidth+charWidth, 
                                      "XXXXXXXXXX".length()*charWidth+charWidth, 
                                      APFtUtilityAndGlobal.getUTFStringSize("XXXXXXXXXXXXXXX\u96fbXXXXXXXXXXXXXX")*chineseCharWidth+chineseCharWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth, 
                                      "XXXX,XXX".length()*charWidth+charWidth};

            //### start page loop ###
            HttpServletRequest request = ACFtUtility.getRequest();
            ARCgPDFGenerator.PdfCellBorder cellborder = ARCgPDFGenerator.PdfCellBorder.LEFT_RIGHT;
            //** incremental counter to track total number of rows printed
            int row_cnt = 1;
            //** loop through the data result set
            for (ACFgRawModel row : report) {
                //** if it is the first row of page, initialize page variables and print page section detail
                if (curRow == 1) {
                    cellpadding = new Rectangle(1f,0f,0f,0f);
                    cellborder = ARCgPDFGenerator.PdfCellBorder.LEFT_RIGHT;
   
                    page = new ArrayList<ARCgPDFGenerator.ARCgPdfTable>();
                    table = new ARCgPDFGenerator.ARCgPdfTable();
                    tableContent = new ArrayList<List<ARCgPDFGenerator.ARCgPdfCell>>();
                    tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
   
                    tr.add(pdf.createPdfCell("Date: "+ACFtUtility.timestampToString(param.get("p_report_date", Timestamp.class), "yyyy-MM-dd"), ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 21, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
                    tr.add(pdf.createPdfCell("Page: "+pf.format(page_num), ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
                    tableContent.add(tr);
   
                    // Add Function ID and name as title if HTTP request
                    if(request!=null) {
                        Object attr = null;
                        if ((attr=request.getAttribute("info.func_id"))!=null) {
                            tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
                            tr.add(pdf.createPdfCell((String)attr, ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 22, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
                            tableContent.add(tr);
                        }
                        if ((attr=request.getAttribute("info.func_name"))!=null) {
                            tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
                            tr.add(pdf.createPdfCell((String)attr, ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 22, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
                            tableContent.add(tr);
                        }
                    }
   
                    //** insert new line
                    tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
                    tr.add(pdf.createPdfCell("", ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 22, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
                    tableContent.add(tr);

                    printColumnTitle(tableContent);
                    
                    //TODO ** print budget line **

                }

                //** add bottom border line if it is last data row on page or report
                if (curRow == dataRowsPerPage || row_cnt == report.size()) {
                    cellpadding = new Rectangle(1f,2f,0f,0f);
                    cellborder = ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT;
                }

                //** insert data row
                tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
            	tr.add(pdf.createPdfCell(ACFtUtility.timestampToString(row.getTimestamp("report_date"),"dd/MM/yyyy"), cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell(row.getString("report_no"), cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell(row.getString("report_desc"), cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, chinesefont, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("$"+APFtUtilityAndGlobal.m_Lpad(df.format(row.getInteger("val_integer"))," ",7), cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("$"+APFtUtilityAndGlobal.m_Lpad(df.format(row.getBigDecimal("val_decimal"))," ",7), cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("$"+APFtUtilityAndGlobal.m_Lpad(df.format(row.getBigDecimal("val_decimal"))," ",7), cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tr.add(pdf.createPdfCell("XXXX,XXX", cellborder, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
            	tableContent.add(tr);
            	
            	//** if it is the last data row of page or report, finalize the page and insert into PDF
            	if (curRow == dataRowsPerPage || row_cnt == report.size()) {
            	    curRow = 1;
            	    page_num++;

            	    //TODO ** print balance lines **
            	    
                    //** print end-of-report
                    if (row_cnt == report.size()) {
                        cellpadding = new Rectangle(1f,1f,1f,1f);
                        
                        //** insert new line
                        tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
                        tr.add(pdf.createPdfCell("", ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 22, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_CENTER));
                        tableContent.add(tr);

                        //** print end-of-report line
                        tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
                        tr.add(pdf.createPdfCell(APFtUtilityAndGlobal.APF_END_OF_REPORT, ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 22, 1, fontbold, Element.ALIGN_CENTER, Element.ALIGN_CENTER));
                        tableContent.add(tr);
                    }

                    table.setTableContent(tableContent);
                    table.setColumns(22);
                    table.setAbsoluteWidths(absoluteWidths);
                    
                    page.add(table);
                    
                    pdf.insertPageContent(page);
                    
                    pdf.insertPageContent(pdf.createPdfNewPage(null, null));
            	} else {
            	    curRow++;
            	}
            	
            	row_cnt++;
            	
            }
            
            //** generate the PDF
            pdf.generatePDF();
            
        } catch (DocumentException e) {
            throw exceptionService.error("APF001S", e);
        } catch (Exception e) {
            throw e;
        }
		
		return new ACFgResponseParameters();
	}		
	
	private void setPageHeader() throws DocumentException, IOException {
        Rectangle cellpadding = new Rectangle(1f,3f,0f,0f);
        Font font = ARCgPDFGenerator.createFont(ARCgPDFGenerator.PdfFontFamily.COURIER, 6, Font.NORMAL);
        Font chinesefont = ARCgPDFGenerator.createFont(ARCgPDFGenerator.PdfFontFamily.MING_LIU, 6, Font.NORMAL);

        ARCgPDFGenerator.ARCgPdfTable table = new ARCgPDFGenerator.ARCgPdfTable();
        List<List<ARCgPDFGenerator.ARCgPdfCell>> tableContent = new ArrayList<List<ARCgPDFGenerator.ARCgPdfCell>>();
        List<ARCgPDFGenerator.ARCgPdfCell> tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
        
        tr.add(pdf.createPdfCell(pdf.createImage(APFtUtilityAndGlobal.APF_TVB_LOGO_PATH), ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 2, font, Element.ALIGN_RIGHT, Element.ALIGN_CENTER, true));
        cellpadding = new Rectangle(1f,3f,2f,0f);
        tr.add(pdf.createPdfCell("Television Broadcasts Limited", ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, font, Element.ALIGN_LEFT, Element.ALIGN_TOP));
        //** use Unicode for typing Chinese characters (@link http://www.ifreesite.com/unicode-ascii-ansi.htm)
        tr.add(pdf.createPdfCell("\u96fb\u8996\u5ee3\u64ad\u6709\u9650\u516c\u53f8", ARCgPDFGenerator.PdfCellBorder.NO_BORDER, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, chinesefont, Element.ALIGN_LEFT, Element.ALIGN_TOP));
        tableContent.add(tr);
        table.setTableContent(tableContent);
        table.setColumns(2);
        table.setAbsoluteWidths(new float[]{32f, 100f});
        pdf.setPageHeader(table);
	    
	}
		
    @SuppressWarnings("unused")
    private void setPageFooter() throws DocumentException, IOException {
        /* Pending to implement */
    }
    
    private void printColumnTitle(List<List<ARCgPDFGenerator.ARCgPdfCell>> tableContent) throws DocumentException, IOException {
        Font fontbold = ARCgPDFGenerator.createFont(ARCgPDFGenerator.PdfFontFamily.COURIER, 5, Font.BOLD, new BaseColor(0, 0, 0));
        List<ARCgPDFGenerator.ARCgPdfCell> tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
        Rectangle cellpadding = new Rectangle(1f,3f,0f,0f);

        //** first column title line
        tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
        tr.add(pdf.createPdfCell("", ARCgPDFGenerator.PdfCellBorder.BOX, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 4, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("PAINT", ARCgPDFGenerator.PdfCellBorder.BOX, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 2, 1, fontbold, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("SET", ARCgPDFGenerator.PdfCellBorder.LEFT_TOP_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("FURNI-", ARCgPDFGenerator.PdfCellBorder.LEFT_TOP_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("DECORAT.", ARCgPDFGenerator.PdfCellBorder.LEFT_TOP_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("OUTSIDE", ARCgPDFGenerator.PdfCellBorder.LEFT_TOP_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("", ARCgPDFGenerator.PdfCellBorder.BOX, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 3, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("ACTION", ARCgPDFGenerator.PdfCellBorder.LEFT_TOP_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("SPECIAL", ARCgPDFGenerator.PdfCellBorder.LEFT_TOP_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("PROP", ARCgPDFGenerator.PdfCellBorder.BOX, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 2, 1, fontbold, Element.ALIGN_CENTER, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("OUTDOOR", ARCgPDFGenerator.PdfCellBorder.LEFT_TOP_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("", ARCgPDFGenerator.PdfCellBorder.BOX, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 4, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tableContent.add(tr);

        //** second column title line
        tr = new ArrayList<ARCgPDFGenerator.ARCgPdfCell>();
        tr.add(pdf.createPdfCell("DATE", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("EPI. NO.", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("PARTICULAR", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("CARP.", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("VTR", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("EFP", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("MATERIAL", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("TURE", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("PROP", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("CONTRACT", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("LIGHT", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("PLANTS", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("MEAL", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("EXPENSE", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("EFFECT", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("ARTS", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("PROD", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("SETTING", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("TRANSPORT PHOTO", ARCgPDFGenerator.PdfCellBorder.BOX, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 2, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("MISC", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tr.add(pdf.createPdfCell("TOTAL", ARCgPDFGenerator.PdfCellBorder.LEFT_BOTTOM_RIGHT, ARCgPDFGenerator.PdfCellBorderStyle.SOLID, ARCgPDFGenerator.PdfCellBorderWidth.THIN, cellpadding, 1, 1, fontbold, Element.ALIGN_LEFT, Element.ALIGN_BOTTOM));
        tableContent.add(tr);
        
    }
    
	@RequestMapping(value=APFtMapping.APFC501_GET_PDF, method={RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<byte[]> getPDF(HttpServletResponse response) throws Exception {
		try {
			byte[] body;
			try {
				body = pdf.getPDFFile();
			} catch(javax.crypto.BadPaddingException e) {
				throw exceptionService.error("ACF045E", e);
			} catch(FileNotFoundException e) {
				throw exceptionService.error("ACF046E", e);
			}
			if(body==null)
				throw exceptionService.error("ACF046E");

		    final HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.parseMediaType( listDao.selectItem("ACF_MIME_TYPE", "PDF").list_desc ));
		    headers.set("Content-Disposition", "attachment; filename=\"" + reportDao.selectItem("APFF501").filename + ".pdf" + "\"");
		    headers.set("Content-Transfer-Encoding", "binary");
		    headers.setContentLength(body.length);
		    
		    return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
		} catch(Exception e) {			
			e.printStackTrace(System.err);
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<byte[]>(e.getMessage().getBytes(), headers, HttpStatus.BAD_REQUEST);
		}
	}
	
}