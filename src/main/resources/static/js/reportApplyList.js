/**
 * 月報申請状況一覧画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = "/tran/report/apply/list";

	/**
	 * 選択ボタン押下<br>
	 * (テーブル明細内)
	 */
	$("#resultTable button[name='select']").on("click", function() {
		var index = $(this).val();
		var url = defaultUrl + "/" + index + "?select";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 月報DLボタン押下<br>
	 * (テーブル明細内)
	 */
	$("#resultTable button[name='download']").on("click", function() {
		var index = $(this).val();
		var url = defaultUrl + "/" + index + "?download";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * Prevアンカー押下
	 */
	$("#pagePrev").on("click", function() {
		var url = defaultUrl + "?pagePrev";
		fmMain.attr("action", url);
		fmMain.submit();
	});

	/**
	 * Nextアンカー押下
	 */
	$("#pageNext").on("click", function() {
		var url = defaultUrl + "?pageNext";
		fmMain.attr("action", url);
		fmMain.submit();
	});
});