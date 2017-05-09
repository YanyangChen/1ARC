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
public abstract class ARCsItemInventory extends ACFaAppService{
    
    public ARCsItemInventory() throws Exception {
        super();
        // TODO Auto-generated constructor stub
    }

    public abstract String getInventory(String po_no) throws Exception;//for apwf005 grid objects collection, AC, 2017/04/11
    public abstract List<ACFgRawModel> getItemUnits(String item_no) throws Exception;//for apwf005 grid column ajax retrieve, AC, 2017/04/11
    public abstract List<ACFgRawModel> getItemNos() throws Exception;//for apwf005 grid column combobox, AC, 2017/04/11
}