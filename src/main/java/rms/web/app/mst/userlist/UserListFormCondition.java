package rms.web.app.mst.userlist;

import javax.validation.constraints.Size;

/**
 * ユーザ一覧画面（検索条件）フォーム
 * @author
 */
public class UserListFormCondition extends rms.common.abstracts.AbstractForm {

    /** ユーザID */
    @Size(max = 10, message = "ユーザIDは{Size.message}")
    private String userId;

    /** ユーザ名 */
    @Size(max = 10, message = "ユーザ名は{Size.message}")
    private String userNm;

    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定します。
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * ユーザ名を設定します。
     * @param userNm ユーザ名
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
}