package arc.apf.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "apf_item_cat")
public class APFmItemCat extends ACFaAppModel {

	public APFmItemCat() throws Exception {
		super();
	}

	@Id
	@Column(name = "item_cat")
	public String item_cat;

	@Column(name = "item_cat_desc")
	public String item_cat_desc;

}
