package arc.apf.Service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.Abstract.ACFaAppService;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;

import arc.apf.Dao.APFoBusiPlatform;

@Service
public class ARCsBusiPlatformImpl extends ARCsBusiPlatform{

	@Autowired APFoBusiPlatform busiPlatformDao;
	
	public ARCsBusiPlatformImpl() throws Exception {
		super();
	}

		public String getBusiPlatform(String busi_platform, int length) throws Exception {
		return busi_platform;
	}
	
	public List<ACFgRawModel> getAllBusiPlatform() throws Exception {
			ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
			ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
			ass.setCustomSQL("select distinct busi_platform as id, busi_platform as text from apf_busi_platform");
			return ass.executeQuery();

	}



}
