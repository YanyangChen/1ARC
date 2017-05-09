package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "PPR_PGM_BASIC_HIST", enableTableStatistics = false)
public class ARCmPPRPgmBasicHist extends ACFaAppModel {

	public ARCmPPRPgmBasicHist() throws Exception {
		super();
	}

	@Id
	@Column(name = "co_code")
	public String co_code;
	
	@Id
	@Column(name = "pgm_num")
	public BigDecimal pgm_num;
	
	@Column(name = "ver_num")
	public BigDecimal ver_num;
	
	@Column(name = "title")
	public String title;
	
	@Column(name = "chinese_title")
	public String chinese_title;
	
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
	
	@Column(name = "first_air_nw")
	public String first_air_nw;
	
	@Column(name = "first_air_dt")
	public Date first_air_dt;
	
	@Column(name = "last_air_nw")
	public String last_air_nw;
	
	@Column(name = "last_air_dt")
	public Date last_air_dt;
	
	@Column(name = "cre_user_id")
	public String cre_user_id;
	
	@Column(name = "cre_dt")
	public Timestamp cre_dt;
	
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
	
	@Column(name = "nw_code")
	public String nw_code;
	
	@Column(name = "tx_mode")
	public String tx_mode;
	
	@Column(name = "lang1")
	public String lang1;
	
	@Column(name = "lang2")
	public String lang2;
	
	@Column(name = "video_def")
	public String video_def;
	
	@Column(name = "video_ratio_mode")
	public String video_ratio_mode;
	
	@Column(name = "protect_mode")
	public String protect_mode;
	
	@Column(name = "sound_mode")
	public String sound_mode;
	
	@Column(name = "subtitle")
	public String subtitle;
	
	@Column(name = "close_caption_1")
	public String close_caption_1;
	
	@Column(name = "close_caption_2")
	public String close_caption_2;
	
	@Column(name = "close_caption_3")
	public String close_caption_3;
	
	@Column(name = "close_caption_4")
	public String close_caption_4;
	
	@Column(name = "close_caption_5")
	public String close_caption_5;
	
	@Column(name = "close_caption_6")
	public String close_caption_6;
	
	@Column(name = "close_caption_7")
	public String close_caption_7;
	
	@Column(name = "close_caption_8")
	public String close_caption_8;
	
	@Column(name = "close_caption_9")
	public String close_caption_9;
	
	@Column(name = "pgm_sub_type")
	public String pgm_sub_type;
}
