package arc.apf.Service;
//package acf.acf.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acf.acf.Abstract.ACFaAppService;
import acf.acf.Abstract.ACFaSQLAss;
import acf.acf.Dao.ACFoModule;
import acf.acf.Dao.ACFoModuleOwner;
import acf.acf.Database.ACFdSQLAssDelete;
import acf.acf.Database.ACFdSQLAssInsert;
import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.Database.ACFdSQLAssUpdate;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Interface.ACFiCallback;
import acf.acf.Interface.ACFiSQLAssInterface;
import acf.acf.Interface.ACFiSQLAssWriteInterface;
import acf.acf.Model.ACFmModule;
import acf.acf.Static.ACFtDBUtility;
import acf.acf.Static.ACFtUtility;


@Service
public abstract class ARCsProgrammeMaster extends ACFaAppService{
    
    public ARCsProgrammeMaster() throws Exception {
        super();
        // TODO Auto-generated constructor stub
    }

   
    public abstract List<ACFgRawModel> getProgrammeNo() throws Exception; //for apff011 combobox, AC 2017/03/20
    public abstract List<ACFgRawModel> getBusinessPlatform() throws Exception;//for apff011 combobox, AC 2017/03/20
    public abstract List<ACFgRawModel> getDepartment() throws Exception;//for apff011 combobox, AC 2017/03/20
    public abstract String getProgrammeName(String programme_no) throws Exception; //for apff011 ajax retrieve, AC 2017/03/20
    public abstract String getProgrammePlatform(String programme_no) throws Exception; //for apff011 ajax retrieve, AC 2017/03/20
    public abstract String getProgrammeDepartment(String programme_no) throws Exception; //for apff011 ajax retrieve, AC 2017/03/20
    
}