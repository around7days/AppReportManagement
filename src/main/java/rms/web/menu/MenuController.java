package rms.web.menu;

import java.util.Enumeration;

import rms.web.mst.user.regist.UserRegistController;
import rms.web.mst.user.search.UserSearchController;
import rms.web.tran.report.applicantion.ReportApplicantionController;
import rms.web.tran.report.approval.ReportApprovalController;
import rms.web.tran.report.search.ReportSearchController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * メニュー登録画面コントローラー
 * @author
 */
@Controller
@SessionAttributes(types = MenuForm.class)
public class MenuController extends rms.com.abstracts.AbstractController {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/menu";

    /** マッピングURL */
    public static final String MAPPING_URL = "/menu";

    /** メニュー画面フォーム */
    @ModelAttribute
    MenuForm setupForm() {
        return new MenuForm();
    }

    /**
     * メニュー画面初期表示
     * @param form
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL)
    public String init(MenuForm form,
                       HttpSession session,
                       Model model) {

        // TODO どっかのタイミングできれいにする
        // 個別セッションの破棄
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            if (!"SPRING_SECURITY_CONTEXT".equals(key)
                && !"org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN".equals(key)) {
                session.removeAttribute(key);
            }
        }

        return PAGE_URL;
    }

    /**
     * ユーザ一覧画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "m001")
    public String M001(MenuForm form,
                       Model model) {
        return redirect(UserSearchController.MAPPING_URL, "init");
    }

    /**
     * ユーザ登録画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "m002")
    public String M002(MenuForm form,
                       Model model) {
        return redirect(UserRegistController.MAPPING_URL, "initInsert");
    }

    /**
     * 月報状況一覧画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "t001")
    public String T001(MenuForm form,
                       Model model) {
        return redirect(ReportSearchController.MAPPING_URL, "init");
    }

    /**
     * 月報申請画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "t002")
    public String T002(MenuForm form,
                       Model model) {
        return redirect(ReportApplicantionController.MAPPING_URL, "initInsert");
    }

    /**
     * 月報承認画面に遷移
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "t003")
    public String T003(MenuForm form,
                       Model model) {
        return redirect(ReportApprovalController.MAPPING_URL + "/user01/201606", "init");
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = MAPPING_URL, params = "e001")
    public String T004(MenuForm form,
                       Model model) throws Exception {
        if (true) {
            throw new Exception("意図的な強制Exception");
        }
        return "";
    }

}