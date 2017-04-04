package jp.co.eit.listsample.listsample01;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * ラベル情報の一覧表示と移動実験
 *
 */
public class App {


	/**
	 * 入力用のラベル情報一覧（孫までいる場合）
	 */
	private static String[][] static_input_dataArray2 = {
			// No  親ﾗﾍﾞﾙ  ﾗﾍﾞﾙｺｰﾄﾞﾗﾍﾞﾙ表示順 ﾗﾍﾞﾙ名    階層ｺｰﾄﾞ ｶﾗｰｺｰﾄﾞ  論理削除フラグ 表示順算出用列
			{ "1", "0000", "0001", "1",       "A",      "10000", "0",    "0" ,            ""},
			{ "2", "0001", "0002", "1",       "A-1",    "01000", "0",    "0" ,            ""},
			{ "3", "0001", "0003", "2",       "A-2",    "01000", "0",    "0" ,            ""},
			{ "4", "0000", "0004", "2",       "B",      "10000", "0",    "0" ,            ""},
			{ "5", "0004", "0005", "1",       "B-1",    "01000", "0",    "0" ,            ""},
			{ "6", "0004", "0006", "2",       "B-2",    "01000", "0",    "0" ,            ""},
			{ "7", "0006", "0007", "1",       "B-2-1",  "00100", "0",    "0" ,            ""},
			{ "8", "0007", "0008", "1",       "B-2-1-1","00010", "0",    "0" ,            ""},
			{ "9", "0000", "0009", "3",       "C",      "10000", "0",    "0" ,            ""},
	};

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// 移動対象のラベル：B-2 (0006)
		String target_label = "0006";

		// refs #1 B-2の移動の全パターンのテスト
		// A ~ B-1の範囲で移動する場合
		IntStream.range(1, 6).forEach(a -> {
			String tmp = "" + (10000 + a);
			tmp = tmp.substring(1,tmp.length());
			move(static_input_dataArray2,tmp,target_label);
		});
		// C の下に移動する場合
		IntStream.range(static_input_dataArray2.length, (static_input_dataArray2.length+1)).forEach(a -> {
			String tmp = "" + (10000 + a);
			tmp = tmp.substring(1,tmp.length());
			move(static_input_dataArray2,tmp,target_label);
		});
		// Top階層の最終に移動する場合
		String tmp = "" + (10000);
		tmp = tmp.substring(1,tmp.length());
		move(static_input_dataArray2,tmp,target_label);

	}


	/**
	 * ラベルの移動処理
	 *
	 * @param dataArray ラベル一覧
	 * @param to 移動先の親ラベルコード
	 * @param from 対象のラベルコード
	 */
	private static void move(String[][] dataArray,String to,String from) {

		Algorithm01 obj = new Algorithm01(dataArray);
		String fromLabelName = obj.getParam(from,4);
		//
		String toLabelName   = "";
		if("0000".equals(to)){
			toLabelName = "最下部";
		}else{
			toLabelName   = obj.getParam(to,4);
		}

		System.out.println("◆◆ラベルコード" + from
				+ "("   + fromLabelName + ")を ラベルコード" + to
				+ "("   + toLabelName + ")の子供に移動");

		Map<String,String> map = obj.modifyMap();
		System.out.println("◆初期状態の表示");
		dispOut(map);
		System.out.println("◆ラベルコード" + from
				+ "("   + fromLabelName + ")を ラベルコード" + to
				+ "("   + toLabelName + ")の子供に移動");
		obj.updateData(to,from);
		Map<String,String> map2 = obj.modifyMap();
		System.out.println("◆変換後の表示");
		dispOut(map2);
		System.out.println("");
	}



	private static void dispOut(Map<String, String> map) {
		map.keySet().stream().sorted().forEach((key) -> {
			String e = map.get(key);
			String[] params = e.split("/");
			String labelName = params[4];//key.split("_")[1];
			params[8] = key.split("_")[0];
			String value = getConcatParams(params);
			System.out.printf("%5s:%s:%s\n",params[8],labelName,value);
		});
	}


	private static String getConcatParams(String[] params) {
		// TODO 自動生成されたメソッド・スタブ
		Optional<String> a =Arrays.asList(params).stream().reduce((value1, value2) -> value1.concat("/").concat(value2));
		return a.get();
	}
}
