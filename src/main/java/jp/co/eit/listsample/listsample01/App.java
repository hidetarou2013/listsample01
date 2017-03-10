package jp.co.eit.listsample.listsample01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ラベル情報の一覧表示と移動実験
 *
 */
public class App {

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		method01();
		method02();

	}

	/**
	 *
	 * @param dataArray ラベル一覧：全データ（もしくは削除フラグが無効のもの）
	 * @param parent 親ラベルID
	 * @param own 移動対象のラベルID：対象は、このラベルIDのみで一意に識別可能
	 * @param orderdiv 対象ラベルの座している階層上での順番
	 * @param name ラベル名
	 * @param levels 対象ラベルの座している階層を識別するためのコード
	 * @param analizecd 分析コード
	 * @param deleteflg 論理削除フラグ
	 * @param order 最終表示順序：テーブルには持たせていないが、Viewでは必要になる。サーバサイドで算出しなくともフロントでは最低限必要になる。
	 * @return
	 */
	private static String[][] updateOneData(String[][] dataArray,String parent,String own,String orderdiv,String name,String levels,String analizecd,String deleteflg,String order){
		for(int i = 0 ; i < dataArray.length ; i++){
			// データの一意識別は、あくまでも「ラベルID」のみで構わない。
			if( dataArray[i][2].equals(own)){
				dataArray[i][1] = parent;
				dataArray[i][3] = orderdiv;
				dataArray[i][4] = name;
				dataArray[i][5] = levels;
				dataArray[i][6] = analizecd;
				dataArray[i][7] = order;
			}
		}
		return dataArray;
	}

	/**
	 * やり方１
	 * ラベル一覧のリストをCSV形式で準備した場合
	 * 表示順序を算出し
	 * 表示
	 * 移動サンプル（B-1 をA-2の下に配置する場合）
	 */
	private static void method01() {
		// TODO 自動生成されたメソッド・スタブ
		// 8番目インデックスの要素に表示順序の決め手となる数字を算出し格納する列を設ける（テーブルには存在しない）
		String[][] dataArray = {
				{ "1", "0000", "0001", "1", "A",    "10000", "0", "0" ,""},
				{ "2", "0001", "0002", "1", "A-1",  "01000", "0", "0" ,""},
				{ "3", "0001", "0003", "2", "A-2",  "01000", "0", "0" ,""},
				{ "4", "0000", "0004", "2", "B",    "10000", "0", "0" ,""},
				{ "5", "0004", "0005", "1", "B-1",  "01000", "0", "0" ,""},
				{ "6", "0004", "0006", "2", "B-2",  "01000", "0", "0" ,""},
				{ "7", "0006", "0007", "1", "B-2-1","00100", "0", "0" ,""},
				{ "8", "0000", "0008", "3", "C",    "10000", "0", "0" ,""},
		};
		// createOrderNo(dataArray)にて、表示順序を算出して埋め込む
		System.out.println("初期データ");
		dataArray = createOrderNo(dataArray);
		initDeploy(dataArray);

		// 移動：B-1 が Aの三男になる場合
		// 削除→挿入ではなく、あくまでも対象データの更新処理
		System.out.println("移動：B-1 が Aの三男に");
		dataArray = updateOneData(dataArray,"0001","0005","3","B-1","01000","0","0","");
		dataArray = createOrderNo(dataArray);
		initDeploy(dataArray);

		// それに付随してB-2が長男になる
		// この処理をしなくてもとりあえず表示順は正しく出る
		// しかし、次に入れ替える時に、対象階層の順序に歯抜けができてしまうため、後々問題が生じるかもしれない。
		// 補正は手間だが、やっておけば安全で丁寧な処理
		System.out.println("移動：それに付随してB-2が長男になる");
		dataArray = updateOneData(dataArray,"0004","0006","1","B-2","01000","0","0","");
		dataArray = createOrderNo(dataArray);
		initDeploy(dataArray);

	}

	/**
	 * 表示順序の算出
	 *
	 * @param dataArray
	 * @return
	 */
	private static String[][] createOrderNo(String[][] dataArray) {
		// dataArray[5].equals("10000")
		int totalOrder = 0;
		for(int i = 0 ; i < dataArray.length ; i++){
			if(dataArray[i][5].equals("10000")){
				totalOrder = Integer.parseInt(dataArray[i][3]) * 10000;
			}else if(dataArray[i][5].equals("01000")){
				totalOrder = Integer.parseInt(dataArray[i][3]) * 1000;
			}else if(dataArray[i][5].equals("00100")){
				totalOrder = Integer.parseInt(dataArray[i][3]) * 100;
			}else if(dataArray[i][5].equals("00010")){
				totalOrder = Integer.parseInt(dataArray[i][3]) * 10;
			}else if(dataArray[i][5].equals("00001")){
				totalOrder = Integer.parseInt(dataArray[i][3]) * 1;
			}
			dataArray[i][8] = "" + totalOrder;
//			System.out.println(dataArray[i][8]);
		}
		//
		totalOrder = 0;
		for(int i = 0 ; i < dataArray.length ; i++){
			String parent = dataArray[i][1];
			totalOrder = Integer.parseInt(dataArray[i][8]);
			int totalOrderP = 0;
			for(int j = 0 ; j < dataArray.length ; j++){
				String own = dataArray[j][2];
				if(parent.equals(own)){
					totalOrderP = Integer.parseInt(dataArray[j][8]);
				}
			}
			totalOrder += totalOrderP;
			dataArray[i][8] = "" + totalOrder;
//			System.out.println(dataArray[i][8]);
		}
		return dataArray;
	}

	/**
	 * やり方２
	 */
	private static void method02() {
		// TODO この配列は順序がランダム、これでも sort できることを確認するため
		String[][] dataArray = {
				{ "3", "0001", "0003", "2", "A-2",  "01000", "0", "0" ,""},
				{ "4", "0000", "0004", "2", "B",    "10000", "0", "0" ,""},
				{ "6", "0004", "0006", "2", "B-2",  "01000", "0", "0" ,""},
				{ "7", "0006", "0007", "1", "B-2-1","00100", "0", "0" ,""},
				{ "5", "0004", "0005", "1", "B-1",  "01000", "0", "0" ,""},
				{ "8", "0000", "0008", "3", "C",    "10000", "0", "0" ,""},
				{ "1", "0000", "0001", "1", "A",    "10000", "0", "0" ,""},
				{ "2", "0001", "0002", "1", "A-1",  "01000", "0", "0" ,""},
		};
		// createOrderNo(dataArray)にて、表示順序を算出して埋め込む
		System.out.println("初期データ");
		dataArray = createOrderNo(dataArray);
		initDeploy(dataArray);

		// 移動：B-1 が Aの三男になる場合
		// 削除→挿入ではなく、あくまでも対象データの更新処理
		System.out.println("移動：B-1 が Aの三男に");
		dataArray = updateOneData(dataArray,"0001","0005","3","B-1","01000","0","0","");
		dataArray = createOrderNo(dataArray);
		initDeploy(dataArray);

		// それに付随してB-2が長男になる
		// この処理をしなくてもとりあえず表示順は正しく出る
		// しかし、次に入れ替える時に、対象階層の順序に歯抜けができてしまうため、後々問題が生じるかもしれない。
		// 補正は手間だが、やっておけば安全で丁寧な処理
		System.out.println("移動：それに付随してB-2が長男に");
		dataArray = updateOneData(dataArray,"0004","0006","1","B-2","01000","0","0","");
		dataArray = createOrderNo(dataArray);
		initDeploy(dataArray);

	}

	/**
	 * ラベルを整理ソートし、配置し、表示
	 * @param dataArray
	 */
	private static void initDeploy(String[][] dataArray) {
		// TODO 自動生成されたメソッド・スタブ
		Map<String, String> paramMap = null;
//		paramMap = subMethod01(dataArray);
//		System.out.println();
//		paramMap = subMethod02(dataArray);
//		System.out.println();
		paramMap = subMethod03(dataArray);
//		System.out.println();
//		paramMap = subMethod04(dataArray,"451_B-1","121_A-1");
	}

	/**
	 * 表示順を表す列が存在した場合の例
	 * ソートなし
	 *
	 * @param dataArray
	 * @return
	 */
	private static Map<String, String> subMethod01(String[][] dataArray) {
		// 2次元配列を最終表示順とラベル名をキーにしたStringプロパティ値のMapに変換する
		// しかし、現在のテーブル設計では、最終表示順　というカラムが存在しない
		Map<String, String> paramMap = Arrays.asList(dataArray).stream().collect(Collectors.toMap(
				s -> s[0] + "_" + s[4],
				s -> s[0] + "/" + s[1] + "/" + s[2] + "/" + s[3] + "/" + s[4] + "/" + s[5] + "/" + s[6] + "/" + s[7],
				(s1, s2) -> s1));

		// そのまま変換しただけでは、表示順は考慮されない。
		paramMap.forEach((key, value) -> System.out.println(key + ":" + value));
		return paramMap;
	}

	/**
	 * 表示順を表す列が存在した場合の例
	 * ソートあり
	 *
	 * @param dataArray
	 * @return
	 */
	private static Map<String, String> subMethod02(String[][] dataArray) {
		// 2次元配列を最終表示順とラベル名をキーにしたStringプロパティ値のMapに変換する
		// しかし、現在のテーブル設計では、最終表示順　というカラムが存在しない
		Map<String, String> paramMap = Arrays.asList(dataArray).stream().collect(Collectors.toMap(
				s -> s[0] + "_" + s[4],
				s -> s[0] + "/" + s[1] + "/" + s[2] + "/" + s[3] + "/" + s[4] + "/" + s[5] + "/" + s[6] + "/" + s[7],
				(s1, s2) -> s1));

		// そのまま変換しただけでは、表示順は考慮されない。
		// 並び替えはここで実現可能
		paramMap.keySet().stream().sorted().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = paramMap.get(key);
			String labelName = key.split("_")[1];
			String[] params = obj.split("/");
			int kaisou = Integer.parseInt(params[5]);
			if(kaisou == 1000){
				labelName = "_" + labelName;
			}else if(kaisou == 100){
				labelName = "__" + labelName;
			}else if(kaisou == 10){
				labelName = "___" + labelName;
			}else if(kaisou == 1){
				labelName = "____" + labelName;
			}
			System.out.println(key.split("_")[0] + ":" + labelName);
		});

		return paramMap;
	}

	/**
	 * 詰め直し：失敗
	 * @param dataArray
	 * @param from
	 * @param to
	 * @return
	 */
	private static Map<String, String> subMethod04(String[][] dataArray,String from,String to){
		Map<String, String> paramMap = Arrays.asList(dataArray).stream().collect(Collectors.toMap(
				s ->  "" + (  Integer.parseInt(s[1]) * 100
							+ Integer.parseInt(s[2]) * 10
							+ Integer.parseInt(s[3])) + "_" + s[4],
				s -> s[0] + "/" + s[1] + "/" + s[2] + "/" + s[3] + "/" + s[4] + "/" + s[5] + "/" + s[6] + "/" + s[7],
				(s1, s2) -> s1));

		List<String> list = new ArrayList<String>();
		paramMap.keySet().stream().sorted().forEach((key) -> {
			list.add(paramMap.get(key));
		});

		// 入れ替え:NG
		int indexFrom = Integer.parseInt(from.split("_")[0]);
		int indexTo   = Integer.parseInt(to.split("_")[0]);
		String strFrom = paramMap.get(from);
		String strTo = paramMap.get(to);
//		list.remove(indexFrom);
//		list.add(indexTo, paramMap.get(from));
		paramMap.remove(from);
		paramMap.put(to,strFrom);


//		list.forEach(action ->
//			{
//				String[] s = action.split("/");
//				String key = "" + (  Integer.parseInt(s[1]) * 100
//						+ Integer.parseInt(s[2]) * 10
//						+ Integer.parseInt(s[3])) + "_" + s[4];
//				String labelName = s[4];
//				System.out.printf("%4s:%s\n",key.split("_")[0],labelName);
//
//			}
//		);

		paramMap.keySet().stream().sorted().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = paramMap.get(key);
			String labelName = key.split("_")[1];
			String[] params = obj.split("/");
			int kaisou = Integer.parseInt(params[5]);
			if(kaisou == 1000){
				labelName = "_" + labelName;
			}else if(kaisou == 100){
				labelName = "__" + labelName;
			}else if(kaisou == 10){
				labelName = "___" + labelName;
			}else if(kaisou == 1){
				labelName = "____" + labelName;
			}
//			System.out.println(key.split("_")[0] + ":" + labelName);
			System.out.printf("%4s:%s\n",key.split("_")[0],labelName);

		});


		return paramMap;
	}

	/**
	 * 親ラベルコードとラベルコードと順序カラムが int 型であれば、最終表示順序を導ける。
	 * しかし、親なしの場合の処理に不正があり、正確性に欠ける（桁が１つ小さくなってしまう）
	 * @param dataArray
	 * @return
	 */
	private static Map<String, String> subMethod03_0(String[][] dataArray) {
		// TODO 自動生成されたメソッド・スタブ
		// 親ラベルコードとラベルコードと順序カラムが int 型であれば、最終表示順序を導ける。
		// 最終表示順序は必要ない
		Map<String, String> paramMap = Arrays.asList(dataArray).stream().collect(Collectors.toMap(
				s ->  "" + (  Integer.parseInt(s[1]) * 100
							+ Integer.parseInt(s[2]) * 10
							+ Integer.parseInt(s[3])) + "_" + s[4],
				s -> s[0] + "/" + s[1] + "/" + s[2] + "/" + s[3] + "/" + s[4] + "/" + s[5] + "/" + s[6] + "/" + s[7],
				(s1, s2) -> s1));

		paramMap.keySet().stream().sorted().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = paramMap.get(key);
			String labelName = key.split("_")[1];
			String[] params = obj.split("/");
			int kaisou = Integer.parseInt(params[5]);
			if(kaisou == 1000){
				labelName = "_" + labelName;
			}else if(kaisou == 100){
				labelName = "__" + labelName;
			}else if(kaisou == 10){
				labelName = "___" + labelName;
			}else if(kaisou == 1){
				labelName = "____" + labelName;
			}
//			System.out.println(key.split("_")[0] + ":" + labelName);
			System.out.printf("%4s:%s\n",key.split("_")[0],labelName);

		});
		return paramMap;
	}

	/**
	 * ソートあり表示
	 *
	 * @param dataArray
	 * @return
	 */
	private static Map<String, String> subMethod03(String[][] dataArray) {
		// TODO 自動生成されたメソッド・スタブ
		// 事前算出した順序列が、最終表示順序を導ける。
		// 最終表示順序は必要ない
		Map<String, String> paramMap = Arrays.asList(dataArray).stream().collect(Collectors.toMap(
				s ->  "" + (  Integer.parseInt(s[8])) + "_" + s[4],
				s -> s[0] + "/" + s[1] + "/" + s[2] + "/" + s[3] + "/" + s[4] + "/" + s[5] + "/" + s[6] + "/" + s[7],
				(s1, s2) -> s1));

		paramMap.keySet().stream().sorted().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = paramMap.get(key);
			String labelName = key.split("_")[1];
			String[] params = obj.split("/");
			int kaisou = Integer.parseInt(params[5]);
			if(kaisou == 1000){
				labelName = "_" + labelName;
			}else if(kaisou == 100){
				labelName = "__" + labelName;
			}else if(kaisou == 10){
				labelName = "___" + labelName;
			}else if(kaisou == 1){
				labelName = "____" + labelName;
			}
//			System.out.println(key.split("_")[0] + ":" + labelName);
			System.out.printf("%4s:%s\n",key.split("_")[0],labelName);

		});
		return paramMap;
	}
}
