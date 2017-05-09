package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "CPL_PGM_BASIC", enableTableStatistics = false)
public class ARCmCPLPgmBasic extends ACFaAppModel {

	public ARCmCPLPgmBasic() throws Exception {
		super();
	}

	@Id
	@Column(name = "co_code")
	public String co_code;
	
	@Id
	@Column(name = "pgm_num")
	public BigDecimal pgm_num;
	
	@Column(name = "title")
	public String title;
	
	@Column(name = "chinese_title")
	public String chinese_title;
	
	@Column(name = "title_uppercase")
	public String title_uppercase;
	
	@Column(name = "old_pgm_num")
	public BigDecimal old_pgm_num;
	
	@Column(name = "num_of_stroke")
	public BigDecimal num_of_stroke;
	
	@Column(name = "num_of_epi")
	public BigDecimal num_of_epi;
	
	@Column(name = "cat_code")
	public String cat_code;
	
	@Column(name = "cat_sub_code")
	public String cat_sub_code;
	
	@Column(name = "class_code_1")
	public String class_code_1;
	
	@Column(name = "class_code_2")
	public String class_code_2;
	
	@Column(name = "class_code_3")
	public String class_code_3;
	
	@Column(name = "class_code_4")
	public String class_code_4;
	
	@Column(name = "class_code_5")
	public String class_code_5;
	
	@Column(name = "transm_type")
	public String transm_type;
	
	@Column(name = "transm_sub_type")
	public String transm_sub_type;
	
	@Column(name = "country_code")
	public String country_code;
	
	@Column(name = "nominal_dur")
	public BigDecimal nominal_dur;
	
	@Column(name = "prod_yr")
	public BigDecimal prod_yr;
	
	@Column(name = "award")
	public String award;
	
	@Column(name = "rmk")
	public String rmk;
	
	@Column(name = "cre_user_id")
	public String cre_user_id;
	
	@Column(name = "cre_dt")
	public Timestamp cre_dt;
	
	@Column(name = "amd_user_id")
	public String amd_user_id;
	
	@Column(name = "amd_dt")
	public Timestamp amd_dt;
	
	@Column(name = "start_epi")
	public BigDecimal start_epi;
	
	@Column(name = "rmk_pgm")
	public String rmk_pgm;
	
	@Column(name = "runs_per_day")
	public BigDecimal runs_per_day;
	
	@Column(name = "hrs_per_run")
	public BigDecimal hrs_per_run;
	
	@Column(name = "run_restrict")
	public BigDecimal run_restrict;
	
	@Column(name = "runs_remain")
	public BigDecimal runs_remain;
	
	@Column(name = "title_full")
	public String title_full;
	
	@Column(name = "title_full_uppercase")
	public String title_full_uppercase;
	
	@Column(name = "clean_eng_title")
	public String clean_eng_title;
	
	@Column(name = "sponsor_eng_title")
	public String sponsor_eng_title;
	
	@Column(name = "clean_chi_title")
	public String clean_chi_title;
	
	@Column(name = "sponsor_chi_title")
	public String sponsor_chi_title;
	
	@Column(name = "title_eng_option")
	public String title_eng_option;
	
	@Column(name = "title_chi_option")
	public String title_chi_option;
	
	@Column(name = "title_alert_ind")
	public String title_alert_ind;
	
	@Column(name = "pgm_type")
	public String pgm_type;
}
