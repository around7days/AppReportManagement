package rms.web.app.tran.reportlist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import rms.common.consts.Const.ReportNmPattern;
import rms.common.consts.MRoleConst;
import rms.common.consts.MessageEnum;
import rms.common.consts.MessageTypeConst;
import rms.common.exception.BusinessException;
import rms.common.utils.RmsBeanUtils;
import rms.common.utils.RmsFileUtils;
import rms.common.utils.SearchResultDto;
import rms.domain.app.shared.dto.SharedFileDto;
import rms.domain.app.shared.dto.SharedSubmitReportFileDto;
import rms.domain.app.shared.service.SharedReportFileService;
import rms.domain.app.tran.reportlist.ReportListDto;
import rms.domain.app.tran.reportlist.ReportListResultEntity;
import rms.domain.app.tran.reportlist.ReportListService;
import rms.web.app.system.menu.MenuController;
import rms.web.app.tran.reportlist.ReportListForm.BulkDownload;
import rms.web.app.tran.reportlist.ReportListForm.Search;

/**
 * 月報一覧画面コントローラー<br>
 * 役割：管理者、閲覧者
 * @author
 */
@Controller
@SessionAttributes(types = ReportListForm.class)
@Secured(value = { MRoleConst.ADMIN, MRoleConst.REFERENCE })
public class ReportListController extends rms.common.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportListController.class);

    /** ページURL */
    private static final String PAGE_URL = "html/reportList";

    /** マッピングURL */
    public static final String MAPPING_URL = "/tran/reportlist";

    /** 画面ID */
    public static final String SCREEN_ID = "T001";

    /** 月報一覧画面サービス */
    @Autowired
    ReportListService service;

    /** 月報ファイル関連共通サービス */
    @Autowired
    SharedReportFileService sharedReportFileService;

    /**
     * 月報一覧画面フォームの初期化
     * @return
     */
    @ModelAttribute
    ReportListForm setupForm() {
        return new ReportListForm();
    }

    /**
     * 初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "init")
    public String init(ReportListForm form,
                       Model model) {
        return PAGE_URL;
    }

    /**
     * 検索処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "search")
    public String search(@Validated(Search.class) ReportListForm form,
                         BindingResult bindingResult,
                         Model model) {
        logger.debug("入力フォーム情報 -> {}", form);

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // ページ情報の初期化
        form.getPageInfo().clear();

        // 検索条件の生成
        ReportListDto condition = RmsBeanUtils.createCopyProperties(form.getCondition(),
                                                                             ReportListDto.class);

        // 検索処理
        SearchResultDto<ReportListResultEntity> resultDto = service.search(condition, form.getPageInfo());

        // 検索結果をフォームに反映
        form.setResultList(resultDto.getResultList());
        form.getPageInfo().setTotalSize(resultDto.getCount());

        if (resultDto.getResultList().isEmpty()) {
            // 「検索結果が見つかりません」
            model.addAttribute(MessageTypeConst.ERROR, message.getMessage(MessageEnum.error006));
            return PAGE_URL;
        }

        return PAGE_URL;
    }

    /**
     * 再検索処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "reSearch")
    public String reSearch(ReportListForm form,
                           Model model) {
        logger.debug("フォーム情報 -> {}", form);

        // 検索条件の生成
        ReportListDto condition = RmsBeanUtils.createCopyProperties(form.getCondition(),
                                                                             ReportListDto.class);

        // 検索処理
        SearchResultDto<ReportListResultEntity> resultDto = service.search(condition, form.getPageInfo());

        // 検索結果をフォームに反映
        form.setResultList(resultDto.getResultList());
        form.getPageInfo().setTotalSize(resultDto.getCount());

        return PAGE_URL;
    }

    /**
     * 前ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pagePrev")
    public String pagePrev(ReportListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return urlHelper.redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 次ページング処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pageNext")
    public String pageNext(ReportListForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().next();

        return urlHelper.redirect(MAPPING_URL, "reSearch");
    }

    /**
     * 月報DL処理
     * @param form
     * @param index
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL + "/{index}", params = "download")
    public String download(ReportListForm form,
                           @PathVariable int index,
                           HttpServletResponse response,
                           Model model) throws IOException {
        logger.debug("選択値 -> {}", index);

        // 選択した月報情報
        ReportListResultEntity entity = form.getResultList().get(index);
        logger.debug("選択月報情報 -> {}", entity);

        try {
            // 月報ファイルダウンロード情報生成
            SharedFileDto dto = sharedReportFileService.getReportFileInfo(entity.getApplyUserId(),
                                                                          entity.getApplyUserNm(),
                                                                          entity.getTargetYm());
            // 月報ダウンロード
            RmsFileUtils.fileDownload(response, dto.getFilePath(), dto.getFileNm());
        } catch (BusinessException e) {
            logger.debug("業務エラー -> {}", e.toString());
            model.addAttribute(MessageTypeConst.ERROR, e.getErrorMessage());
            return PAGE_URL;
        }

        return null;
    }

    /**
     * 月報一括DL処理
     * @param form
     * @param bindingResult
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = MAPPING_URL, params = "bulkDownload")
    public String bulkDownload(@Validated(BulkDownload.class) ReportListForm form,
                               BindingResult bindingResult,
                               HttpServletResponse response,
                               Model model) throws IOException {
        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug("入力チェックエラー -> {}", bindingResult.getAllErrors());
            return PAGE_URL;
        }

        // ダウンロードする月報情報リストの生成
        List<SharedSubmitReportFileDto> list = new ArrayList<>();

        // 選択した月報indexの取得
        Integer[] checks = form.getReportDLCheck();
        for (int i : checks) {
            ReportListResultEntity entity = form.getResultList().get(i);

            SharedSubmitReportFileDto dto = new SharedSubmitReportFileDto();
            dto.setApplyUserId(entity.getApplyUserId());
            dto.setTargetYm(entity.getTargetYm());

            list.add(dto);
        }

        try {
            // 月報一括ダウンロードファイルの生成
            SharedFileDto dto = sharedReportFileService.createReportFileBulk(list, ReportNmPattern.SUBMIT);
            // 月報ダウンロード
            RmsFileUtils.fileDownload(response, dto.getFilePath(), dto.getFileNm());
        } catch (BusinessException e) {
            logger.debug("業務エラー -> {}", e.toString());
            model.addAttribute(MessageTypeConst.ERROR, e.getErrorMessage());
            return PAGE_URL;
        }

        return null;
    }

    /**
     * 戻る処理
     * @param sessionStatus
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "back")
    public String back(SessionStatus sessionStatus) {
        // セッション破棄
        sessionStatus.setComplete();
        return urlHelper.redirect(MenuController.MAPPING_URL);
    }

    /*
     * (非 Javadoc)
     * @see rms.common.abstracts.AbstractController#getScreenId()
     */
    @Override
    protected String getScreenId() {
        return SCREEN_ID;
    }
}
