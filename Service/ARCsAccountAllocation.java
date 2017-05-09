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
public abstract class ARCsAccountAllocation extends ACFaAppService{
    
    public ARCsAccountAllocation() throws Exception {
        super();
        // TODO Auto-generated constructor stub
    }

    public abstract String getSectionNameById(String supplier_code) throws Exception;// a useless demo sample, AC, 2017/02
    public abstract String getBudgetAccountDescription(String supplier_code) throws Exception;//for budget grid browse column ajax retrive in apff011, AC, 2017/03/15
    public abstract List<ACFgRawModel> getBudgetAccountAllocation() throws Exception;//for budget grid browse combobox in apff011, AC, 2017/03/15
    public abstract List<ACFgRawModel> getActualAccountAllocation() throws Exception;//for account allocation grid browse combobox in apwf005, AC, 2017/05/02
}