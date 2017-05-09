package arc.apf.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "apf_item")
public class APFmItem extends ACFaAppModel {

	public APFmItem() throws Exception {
		super();
	}

	@Id
	@Column(name = "item_cat")
	public String item_cat;

	@Id
	@Column(name = "item_no")
	public String item_no;
	
	@Column(name = "item_desc")
	public String item_desc;

	@Column(name = "material_rpt_type")
	public String material_rpt_type;

	@Column(name = "location_id")
	public String location_id;

	@Column(name = "unit")
	public String unit;

	@Column(name = "unit_cost")
	public BigDecimal unit_cost;

	@Column(name = "safe_qty")
	public int safe_qty;

	

}
