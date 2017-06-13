package jp.co.eit.loop4sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Loop4Sample {


	private static String Delim = " : ";

	/**
	 * 売上伝票情報
	 */
	private static String[][] table01 = {
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_01","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_02","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_01","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_02","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_01","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_02","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_01","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_02","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_01","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_02","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_03","受付番号_01","赤黒状態区分_00","見積日_20170401","受注日_20170401","売上計上日_20170401","売上実績計上組織コード_01","売上請求計上組織コード_01","前受金入金合計金額_500","整備主作業区分_01","整備車検・一般区分_01"},

	};

	/**
	 * 売上伝票明細情報
	 */
	private static String[][] table02 = {
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_01","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1000","整備請求売上金額(税抜き)_2000","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_01","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1010","整備請求売上金額(税抜き)_2001","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_01","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1020","整備請求売上金額(税抜き)_2002","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_02","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1030","整備請求売上金額(税抜き)_2003","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_02","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1040","整備請求売上金額(税抜き)_2004","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_02","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1050","整備請求売上金額(税抜き)_2005","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_01","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1000","整備請求売上金額(税抜き)_2000","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_01","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1010","整備請求売上金額(税抜き)_2001","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_01","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1020","整備請求売上金額(税抜き)_2002","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_02","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1030","整備請求売上金額(税抜き)_2003","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_02","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1040","整備請求売上金額(税抜き)_2004","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_02","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1050","整備請求売上金額(税抜き)_2005","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_01","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1000","整備請求売上金額(税抜き)_2000","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_01","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1010","整備請求売上金額(税抜き)_2001","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_01","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1020","整備請求売上金額(税抜き)_2002","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_02","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1030","整備請求売上金額(税抜き)_2003","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_02","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1040","整備請求売上金額(税抜き)_2004","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_02","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1050","整備請求売上金額(税抜き)_2005","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_01","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1000","整備請求売上金額(税抜き)_2000","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_01","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1010","整備請求売上金額(税抜き)_2001","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_01","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1020","整備請求売上金額(税抜き)_2002","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_02","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1030","整備請求売上金額(税抜き)_2003","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_02","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1040","整備請求売上金額(税抜き)_2004","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_02","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1050","整備請求売上金額(税抜き)_2005","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_01","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1000","整備請求売上金額(税抜き)_2000","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_01","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1010","整備請求売上金額(税抜き)_2001","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_01","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1020","整備請求売上金額(税抜き)_2002","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_02","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1030","整備請求売上金額(税抜き)_2003","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_02","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1040","整備請求売上金額(税抜き)_2004","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_02","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1050","整備請求売上金額(税抜き)_2005","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},

			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_03","伝票明細番号_01","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1030","整備請求売上金額(税抜き)_2003","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_03","伝票明細番号_02","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1040","整備請求売上金額(税抜き)_2004","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_03","伝票明細番号_03","有料無料区分_01","伝票明細表示順位_01","伝票明細金額(税抜き)_1050","整備請求売上金額(税抜き)_2005","整備伝票明細種別_01","整備サービス区分_01","BL作業コード_01","BL部品コード_01"},

	};

	/**
	 * 売上伝票諸費用明細情報
	 */
	private static String[][] table03 = {
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_01","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_100"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_01","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_110"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_01","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_120"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_02","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_130"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_02","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_140"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_02","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_150"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_01","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_100"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_01","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_110"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_01","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_120"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_02","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_130"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_02","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_140"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_02","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_150"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_01","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_100"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_01","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_110"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_01","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_120"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_02","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_130"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_02","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_140"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_02","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_150"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_01","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_100"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_01","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_110"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_01","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_120"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_02","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_130"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_02","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_140"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_02","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_150"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_01","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_100"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_01","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_110"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_01","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_120"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_02","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_130"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_02","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_140"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_02","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_150"},

			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_03","伝票明細番号_01","整備集計区分_01","諸費用金額(税抜き)_130"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_03","伝票明細番号_02","整備集計区分_01","諸費用金額(税抜き)_140"},
			{"BLテナントID_01","プロダクトコード_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_03","伝票明細番号_03","整備集計区分_01","諸費用金額(税抜き)_150"},

	};

	/**
	 * 売上伝票集計区分値引情報(整備)
	 * 請求基準時に集計する金額なので、今回のベータ開発では集計対象外
	 */
	private static String[][] table04 = {
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_01","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_100"},
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_01","売上伝票枝番_02","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_200"},
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_01","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_100"},
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_02","売上伝票枝番_02","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_200"},
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_01","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_100"},
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_03","売上伝票枝番_02","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_200"},
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_01","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_100"},
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_04","売上伝票枝番_02","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_200"},
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_01","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_100"},
			{"BLテナントID_01","売上伝票管理組織コード_01","売上伝票番号_05","売上伝票枝番_02","整備主作業区分_01","整備集計区分_01","集計値引金額(税抜き)_200"},
	};


	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Loop4Sample my = new Loop4Sample();
		my.execute01();
	}

	private void execute01() {
		// TODO 自動生成されたメソッド・スタブ
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("■[methodName] start : " + methodName);

		// アプリ側で集計するということは、骨組みは、こんなかんじになる。
//		List<Map<String,String>> dataList = new ArrayList<>();
		List<String> dataList = new ArrayList<>();
		int cnt = 0;
		for(String[] rec01 : table01){
			int sum = 0;
			for(String[] rec02 : table02){
				// 伝票明細番号は３つ商品があると想定
				// 結合条件
				boolean joinConditions2 = getJoinConditions2(rec01,rec02);
				if(joinConditions2){
					// ここは単純な詳細データの加算
					sum += getPrice("伝票明細金額(税抜き)",rec02[8]) + getPrice("整備請求売上金額(税抜き)",rec02[9]);
					// もしもう少しブレークダウンした分類集計したかったらここでやる
					//
					for(String[] rec03 : table03){
						// 結合条件
						boolean joinConditions3 = getJoinConditions3(rec01,rec02,rec03);
						if(joinConditions3){
							//
							sum += getPrice("諸費用金額(税抜き)",rec03[7]);
						}
					}
				}
			}
			// table01のレコード単位で集計出力
			String dispRet = getRecordResult(rec01,sum);
			dataList.add(dispRet);
			System.out.println("record:[" + cnt + "]" + dispRet);
			cnt++;
		}
		// 売上伝票枝番はマージ
		int cnt2 = 0;
		List<String[]> dataList2 = mergeDenpyou(dataList);
		for(String[] rec : dataList2){
			String one = getRecordResult(rec);
			System.out.println("record:[" + cnt2 + "]" + one);
			cnt2++;
		}
		System.out.println("■[methodName] end   : " + methodName);

	}

	/**
	 * 売上伝票枝番はマージしてひとつに
	 * 売上伝票番号単位のレコード
	 * @param dataList
	 * @return
	 */
	private List<String[]> mergeDenpyou(List<String> dataList) {

		String[] pool = new String[6];
		List<String[]> dataList2 = new ArrayList<>();
		boolean startFlag = false;
		Loop1:for (String data : dataList) {
			String[] rec02 = data.split(Delim);
//			System.out.println("rec02:" + data);
			if(pool[0] == null){
				// 初回の場合のデータをキャッシュ
				pool = rec02;// copy保持したい
			}else{
				if(hikaku(pool,rec02)){
					// 金額のフィールドのみサマリーする。
					pool[5] = "" + (Integer.parseInt(pool[5]) + Integer.parseInt(rec02[5]));
					//String[] workRec = new String[6];
					//workRec = pool;
					// ここで追加すると2件の時以降延々と追加することとなる。
					//dataList2.add(pool);
					startFlag = false;
				}else{
					// 前のpoolデータが残っている時にデータ保存？
					if(!startFlag){
						dataList2.add(pool);
					}
					// 次の新規データのデータをpoolする
					startFlag = true;
					pool = rec02;
				}
			}
		}
		// listの最後のレコードを追加する。
		// 売上伝票番号単位のレコードとなる。
		dataList2.add(pool);
		return dataList2;
	}

	private boolean hikaku(String[] pool, String[] rec02) {
		boolean ret = false;
		if(	   pool[0].equals(rec02[0])
			&& pool[1].equals(rec02[1])
			&& pool[2].equals(rec02[2])
			&& pool[3].equals(rec02[3])
					){
			ret = true;
		}
		return ret;
	}

	private String getRecordResult(String[] rec01) {
		// TODO 自動生成されたメソッド・スタブ
		String ret = "";
		ret = rec01[0] + Delim + rec01[1] + Delim + rec01[2] + Delim + rec01[3] + Delim + rec01[4] + Delim + rec01[5];
		return ret;
	}

	private String getRecordResult(String[] rec01, int sum) {
		// TODO 自動生成されたメソッド・スタブ
		String ret = "";
		ret = rec01[0] + Delim + rec01[1] + Delim + rec01[2] + Delim + rec01[3] + Delim + rec01[4] + Delim + sum;
		return ret;
	}

	private boolean getJoinConditions2(String[] rec01, String[] rec02) {
		boolean ret = false;
		if(	   rec01[0].equals(rec02[0])
			&& rec01[1].equals(rec02[1])
			&& rec01[2].equals(rec02[2])
			&& rec01[3].equals(rec02[3])
			&& rec01[4].equals(rec02[4])
					){
			ret = true;
		}
		return ret;
	}

	private boolean getJoinConditions3(String[] rec01, String[] rec02, String[] rec03) {
		boolean ret = false;
		if(	       rec02[0].equals(rec03[0])
				&& rec02[1].equals(rec03[1])
				&& rec02[2].equals(rec03[2])
				&& rec02[3].equals(rec03[3])
				&& rec02[4].equals(rec03[4])
				&& rec02[5].equals(rec03[5])
						){
				ret = true;
			}
		return ret;
	}



	private void execute01_org() {
		// TODO 自動生成されたメソッド・スタブ
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("■[methodName] start : " + methodName);

		// アプリ側で集計するということは、骨組みは、こんなかんじになる。
		List<Map<String,String>> dataList = new ArrayList<>();
		for(String[] rec01 : table01){
			for(String[] rec04 : table04){
				// 結合条件
				boolean joinConditions = true;
				if(joinConditions){
					// ここ共通処理にすべきか
					int sum = 0;
					for(String[] rec02 : table02){
						// 伝票明細番号は３つ商品があると想定
						// 結合条件
						boolean joinConditions2 = true;
						if(joinConditions2){
							//
							sum += getPrice("伝票明細金額(税抜き)",rec02[8]) + getPrice("整備請求売上金額(税抜き)",rec02[9]);
							//
							for(String[] rec03 : table03){
								// 結合条件
								boolean joinConditions3 = true;
								if(joinConditions3){
									//
									sum += getPrice("諸費用金額(税抜き)",rec03[7]);
								}
							}
						}
					}
				}else{
					// 値引きがない場合だってあるはず。
				}
			}
		}

		System.out.println("■[methodName] end   : " + methodName);

	}

	private int getPrice(String labelStr, String valueStr) {
		// TODO 自動生成されたメソッド・スタブ
		int ret = 0;
		String[] val = valueStr.split("_");
		if("伝票明細金額(税抜き)".equals(labelStr)){
			ret = Integer.parseInt(val[1]);
		}else if("整備請求売上金額(税抜き)".equals(labelStr)){
			ret = Integer.parseInt(val[1]);
		}else{
			ret = Integer.parseInt(val[1]);
		}
		return ret;
	}

}
