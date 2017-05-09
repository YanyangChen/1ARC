package arc.apf.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "apf_busi_platform")
public class APFmBusiPlatform extends ACFaAppModel {

	public APFmBusiPlatform() throws Exception {
		super();
	}

	@Id
	@Column(name = "busi_platform")
	public String busi_platform;

	@Id
	@Column(name = "prognat_dept")
	public String prognat_dept;
	
	@Column(name = "busi_platform_desc")
	public String busi_platform_desc;

	@Column(name = "prognat_dept_desc")
	public String prognat_dept_desc;


}


