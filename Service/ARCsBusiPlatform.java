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

public abstract class ARCsBusiPlatform extends ACFaAppService {

	public ARCsBusiPlatform() throws Exception {
		super();
	}

	public abstract String getBusiPlatform(String busi_platform, int length) throws Exception;
	public abstract List<ACFgRawModel> getAllBusiPlatform() throws Exception; //For apff011 combobox, AC, 2017/03/20

}
