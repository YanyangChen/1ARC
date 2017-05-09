package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "PPR_PGM_CASTING", enableTableStatistics = false)
public class ARCmPPRPgmCasting extends ACFaAppModel {

    public ARCmPPRPgmCasting() throws Exception {
        super();
    }

    @Id
    @Column(name = "co_code")
    public String co_code;
    
    @Id
    @Column(name = "pgm_num")
    public BigDecimal pgm_num;
    
    @Column(name = "cast_level")
    public String cast_level;
    
    @Column(name = "music_theme_num")
    public BigDecimal music_theme_num;
    
    @Column(name = "start_epi_num")
    public BigDecimal start_epi_num;
    
    @Column(name = "item_num")
    public BigDecimal item_num;
    
    @Column(name = "cast_role_code")
    public String cast_role_code;
    
    @Column(name = "ep_name")
    public String ep_name;
    
    @Column(name = "end_epi_num")
    public BigDecimal end_epi_num;
    
    @Column(name = "name_uppercase")
    public String name_uppercase;
    
    @Column(name = "cast_class_code")
    public String cast_class_code;
    
    @Column(name = "cre_user_id")
    public String cre_user_id;
    
    @Column(name = "cre_dt")
    public Timestamp cre_dt;
    
    @Column(name = "amd_user_id")
    public String amd_user_id;
    
    @Column(name = "amd_dt")
    public Timestamp amd_dt;
    
    
}