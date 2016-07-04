package mms.uniq.tran.report.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import mms.com.consts.PageIdConst;

/**
 * 月報状況一覧画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(types = ReportSearchForm.class)
public class ReportSearchController extends mms.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportSearchController.class);

    /** デフォルトマッピングURL */
    public static final String DEFAULT_URL = "/tran/report/search";

    /** デフォルトページID */
    private static final String DEFAULT_PAGE = PageIdConst.TRAN_REPROT_SEARCH;

    /** 月報状況一覧画面サービス */
    @Autowired
    ReportSearchService reportSearchService;

    /** 月報状況一覧画面フォーム */
    @ModelAttribute
    ReportSearchForm setupForm() {
        return new ReportSearchForm();
    }

    /**
     * 初期処理
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "init")
    public String initInsert(Model model) {
        // 初期値設定
        ReportSearchForm form = new ReportSearchForm();
        reportSearchService.init(form);
        // 格納
        model.addAttribute(form);

        return DEFAULT_PAGE;
    }

    /**
     * 検索処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "search")
    public String search(@Validated ReportSearchForm form,
                         BindingResult bindingResult,
                         Model model) {
        logger.debug("フォーム情報：{}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return DEFAULT_PAGE;
        }

        // 検索処理
        reportSearchService.search(form);
        if (form.getResultList().isEmpty()) {
            bindingResult.reject("", "検索結果は存在しません");
            return DEFAULT_PAGE;
        }

        return DEFAULT_PAGE;
    }

    /**
     * 再検索処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "reSearch")
    public String reSearch(ReportSearchForm form,
                           Model model) {
        logger.debug("フォーム情報：{}", form.toString());

        // 検索処理
        reportSearchService.search(form);

        return DEFAULT_PAGE;
    }

    /**
     * 前ページング処理<br>
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "pagePrev")
    public String pagePrev(ReportSearchForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return redirect(DEFAULT_URL, "reSearch");
    }

    /**
     * 次ページング処理<br>
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "pageNext")
    public String pageNext(ReportSearchForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().next();

        return redirect(DEFAULT_URL, "reSearch");
    }

    /**
     * ユーザ新規処理
     * @return
     */
    @RequestMapping(value = DEFAULT_URL, params = "insert")
    public String selectInsert() {
        return redirect("/mst/user/regist", "initInsert");
    }

    /**
     * 月報選択処理
     * @param form
     * @param index
     * @param model
     * @return
     */
    @RequestMapping(value = DEFAULT_URL + "/{index}", params = "select")
    public String select(ReportSearchForm form,
                         @PathVariable int index,
                         Model model) {
        logger.debug("選択値：{}", index);

        // 選択した月報情報
        ReportSearchResultForm resultForm = form.getResultList().get(index);
        logger.debug("選択月報情報：{}", resultForm.toString());

        return "";
    }

}
