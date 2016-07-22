package rms.domain.com.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * MRoleクラス
 */
@Entity(listener = MRoleListener.class)
@Table(name = "m_role")
public class MRole {

    /** 役割ID */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /** 役割名 */
    @Column(name = "role_nm")
    private String roleNm;

    /** 役割 */
    @Column(name = "role")
    private String role;

    /** 説明 */
    @Column(name = "description")
    private String description;

    /** バージョン */
    @Version
    @Column(name = "version")
    private Integer version;

    /** 削除フラグ */
    @Column(name = "del_flg")
    private Integer delFlg;

    /** 登録日時 */
    @Column(name = "ins_date")
    private LocalDateTime insDate;

    /** 登録ID */
    @Column(name = "ins_id")
    private String insId;

    /** 更新日時 */
    @Column(name = "upd_date")
    private LocalDateTime updDate;

    /** 更新ID */
    @Column(name = "upd_id")
    private String updId;

    /**
     * 役割IDを取得します.
     * @return 役割ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 役割IDを設定します.
     * @param roleId 役割ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 役割名を取得します.
     * @return 役割名
     */
    public String getRoleNm() {
        return roleNm;
    }

    /**
     * 役割名を設定します.
     * @param roleNm 役割名
     */
    public void setRoleNm(String roleNm) {
        this.roleNm = roleNm;
    }

    /**
     * 役割を取得します.
     * @return 役割
     */
    public String getRole() {
        return role;
    }

    /**
     * 役割を設定します.
     * @param role 役割
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 説明を取得します.
     * @return 説明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明を設定します.
     * @param description 説明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * バージョンを取得します.
     * @return バージョン
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * バージョンを設定します.
     * @param version バージョン
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 削除フラグを取得します.
     * @return 削除フラグ
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * 削除フラグを設定します.
     * @param delFlg 削除フラグ
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 登録日時を取得します.
     * @return 登録日時
     */
    public LocalDateTime getInsDate() {
        return insDate;
    }

    /**
     * 登録日時を設定します.
     * @param insDate 登録日時
     */
    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    /**
     * 登録IDを取得します.
     * @return 登録ID
     */
    public String getInsId() {
        return insId;
    }

    /**
     * 登録IDを設定します.
     * @param insId 登録ID
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 更新日時を取得します.
     * @return 更新日時
     */
    public LocalDateTime getUpdDate() {
        return updDate;
    }

    /**
     * 更新日時を設定します.
     * @param updDate 更新日時
     */
    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    /**
     * 更新IDを取得します.
     * @return 更新ID
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 更新IDを設定します.
     * @param updId 更新ID
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}