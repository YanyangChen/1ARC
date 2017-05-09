package arc.apf.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;


@Service
public class ARCsItemInventoryImpl extends ARCsItemInventory {

    public ARCsItemInventoryImpl() throws Exception {
        super();
        // TODO Auto-generated constructor stub
    }
   
    public  String getInventory(String po_no) throws Exception //for apwf005 grid objects collection, AC, 2017/04/11
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       
        ass.setCustomSQL(
                "SELECT * from arc_item_inventory i "+
                "WHERE i.purchase_order_no = '%s'"
                ,po_no);
        
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        return result.size()>0 ? result.get(0).getString("purchase_order_no") : "";
      //  return result.size()>0 ? result.get(0).getString("supplier_desc") : "";
        
    }
    
    public  List<ACFgRawModel> getItemUnits(String item_no) throws Exception//for apwf005 grid column ajax retrieve, AC, 2017/04/11
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       
        ass.setCustomSQL(
                "SELECT * from arc_item_inventory i "+
                "WHERE i.item_no= '%s'"
                ,item_no);
     
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        return result;
      //  return result.size()>0 ? result.get(0).getString("supplier_desc") : "";
        
    }
    
    public List<ACFgRawModel> getItemNos() throws Exception {//for apwf005 grid column combobox, AC, 2017/04/11
        /*
        List<ACFmModule> modules = moduleDao.selectCachedItems();
        Collections.sort(modules);

        List<ACFgRawModel> results = new LinkedList<ACFgRawModel>();
        for(ACFmModule item: modules) {
            ACFgRawModel m = new ACFgRawModel();
            m.set("id", item.mod_id).set("text", item.mod_id + "-" + item.mod_name);
            results.add(m);
        }
        return results;
        */

        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select item_no as id, item_no as text from arc_item_inventory order by item_no");
        return ass.executeQuery();

    }

}