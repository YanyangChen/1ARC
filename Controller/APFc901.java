package arc.apf.Controller;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
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

import acf.acf.Dao.ACFoList;
import acf.acf.Dao.ACFoReport;
import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.annotation.ACFgFunction;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.General.core.ACFgRequestParameters;
import acf.acf.General.core.ACFgResponseParameters;
import acf.acf.General.report.ACFgExcelGenerator;
import acf.acf.Service.ACFsReport;
import acf.acf.Static.ACFtUtility;
import acf.acf.Abstract.ACFaAppController;

import arc.apf.Service.APFsSection;
import arc.apf.Static.APFtMapping;

@Controller
@Scope("session")
@ACFgFunction(id="APFF901")
@RequestMapping(value=APFtMapping.APFC901)
public class APFc901 extends ACFaAppController {
	
	@Autowired ACFoList listDao;
	@Autowired ACFoReport reportDao;
	
	@Autowired ACFsReport reportService;
	@Autowired APFsSection sectionService;
	
	CellStyle CS_TITLE;
	ACFgExcelGenerator excel;
			
	@RequestMapping(value=APFtMapping.APFC901_MAIN, method=RequestMethod.GET)
	public String index(ModelMap model) throws Exception {
		model.addAttribute("report_formats", reportService.getReportAllowExt("APFF901"));
		return view();
	}	
	
	@RequestMapping(value=APFtMapping.APFC901_REPORT, method=RequestMethod.POST)
	@ResponseBody
	public ACFgResponseParameters generateReport(@RequestBody ACFgRequestParameters param) throws Exception {
		
		ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
		ass.setCustomSQL(ACFtUtility.getJavaResourceInString("/resource/apff901_get_section_id.sql"));
		List<ACFgRawModel> result = ass.executeQuery(getConnection("ARCDB"));

		excel = new ACFgExcelGenerator(reportDao.selectItem("APFF901").rpt_path+"/"+reportDao.selectItem("APFF901").filename + ".xlsx");
		initCellStyle();
		
		int rIndex = 0;
		excel.setCellValue(rIndex, 0, "Test EXCEL Section Report", excel.getCellStyle(ACFgExcelGenerator.CELL_STYLE_REPORT_TITLE));
		excel.setMergedRegion(rIndex, rIndex, 0, 2);
		rIndex++;
		
		excel.setCellValue(rIndex, 0, "Date: "+ACFtUtility.timestampToString(param.get("p_report_date", Timestamp.class), "yyyy-MM-dd"));
    	rIndex++;
		
		excel.setCellValue(rIndex, 0, "Section ID.", CS_TITLE);
		excel.setCellValue(rIndex, 1, "Section Desc", CS_TITLE);
		rIndex++;
		
		for (ACFgRawModel row : result) {
			excel.setCellValue(rIndex, 0, row.getString("section_id"));
			excel.setCellValue(rIndex, 1, row.getString("section_desc"));
			rIndex++;
		}
		
		excel.generateExcel();
		
		return new ACFgResponseParameters();
	}		
		
	private void initCellStyle() {
		Font f = excel.getWorkbook().createFont();
		
		f.setUnderline(HSSFFont.U_SINGLE);
		f.setFontHeightInPoints((short)14);
		
		CS_TITLE = excel.getWorkbook().createCellStyle();
		CS_TITLE.setFont(f);
		
		excel.setColumnWidth(0,	5000);
		excel.setColumnWidth(1,	5000);
		excel.setColumnWidth(2,	9000);
	}
	
	@RequestMapping(value=APFtMapping.APFC901_GET_EXCEL, method={RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<byte[]> getExcel(HttpServletResponse response) throws Exception {
		try {
			byte[] body;
			try {
				body = excel.getExcelFile();
			} catch(javax.crypto.BadPaddingException e) {
				throw exceptionService.error("ACF045E", e);
			} catch(FileNotFoundException e) {
				throw exceptionService.error("ACF046E", e);
			}
			if(body==null)
				throw exceptionService.error("ACF046E");

		    final HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.parseMediaType( listDao.selectItem("ACF_MIME_TYPE", "XLSX").list_desc ));
		    headers.set("Content-Disposition", "attachment; filename=\"" + reportDao.selectItem("APFF901").filename + ".xlsx" + "\"");
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