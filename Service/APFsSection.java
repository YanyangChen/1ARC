package arc.apf.Service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.Abstract.ACFaAppService;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Dao.APFoSection;

@Service
public class APFsSection extends ACFaAppService {

	@Autowired APFoSection sectionDao;
	
	public APFsSection() throws Exception {
		super();
	}

		public String getSectionId(String section_id, int length) throws Exception {
		return section_id;
	}
	
	public List<ACFgRawModel> getAllSectionValue() throws Exception {
			ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
			ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
			ass.setCustomSQL("select section_id as id, section_id  || ' - ' || section_desc as text from apf_section");
			return ass.executeQuery();

	}



}
