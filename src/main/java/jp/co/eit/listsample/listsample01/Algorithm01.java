package jp.co.eit.listsample.listsample01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Algorithm01 {

	/**
	 * 対象のラベル一覧情報のMap：Keyはラベルコード
	 * A-1
	 */
	private Map<String,String> targetMap;

	/**
	 * 対象のラベル一覧情報のMap:Keyは、表示順の５桁文字がprefixについている。
	 * 11000_A-1
	 */
	private Map<String,String> targetOrderMap = new TreeMap<String,String>();

	/**
	 * ラベル一覧情報のList表現
	 */
	private List<String> targetList;

	/**
	 * すでにMapの状態で渡ってきた場合
	 * @param targetMap
	 */
	public Algorithm01(Map<String,String> targetMap){
		this.targetMap = targetMap;
		getRecordList();
	}

	/**
	 * 二次元配列で渡ってきた場合（CSV形式）
	 * @param dataArray
	 */
	public Algorithm01(String[][] dataArray){
		dataArray = addOrderNoInArray(dataArray);
		this.targetMap = Arrays.asList(dataArray).stream().collect(Collectors.toMap(
				s -> s[2],
				s -> s[0] + "/" + s[1] + "/" + s[2] + "/" + s[3] + "/" + s[4] + "/" + s[5] + "/" + s[6] + "/" + s[7] + "/" + s[8],
				(s1, s2) -> s1));
		getRecordList();
	}

	/**
	 * targetMapからtargetListを導く
	 * @return
	 */
	public List<String> getRecordList(){
//		System.out.println(this.targetMap.size());
//		this.targetMap.entrySet().stream().forEach(action ->
//			{
//				System.out.println(action.getValue());
//				// ラムダ式は外部のスコープの変数にアクセスできない。
////				this.targetList.add(action.getValue());
//			}
//		);
		targetList = this.targetMap.entrySet().stream()
				.map(mapper -> mapper.getValue())
				.collect(Collectors.toList());

		return targetList;
	}

	/**
	 * 表示用に階層構造を現したラベル名を加工し、Mapに変換して埋め込む
	 * @return
	 */
 	public Map<String,String> modifiyLabelName() {
		this.targetMap.keySet().stream().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = this.targetMap.get(key);
			String[] params = obj.split("/");
			String labelName = params[2];
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
			System.out.printf("%4s:%s\n",key.split("_")[0],labelName);
			params[2] = labelName;
			String value = getConcatParams(params);
			this.targetMap.put(key, value);
		});
		return this.targetMap;
	}

	/**
	 * 表示順を算出し、Mapに埋め込む
	 * @return
	 */
	public Map<String,String> addOrderNoInMap() {
		this.targetMap.keySet().stream().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = this.targetMap.get(key);
			String[] params = obj.split("/");
			String labelName = params[2];
			int kaisou = Integer.parseInt(params[5]);
			int totalOrder =0;
			if(kaisou == 10000){
				totalOrder = Integer.parseInt(params[3]) * 10000;
			}else if(kaisou == 1000){
				totalOrder = Integer.parseInt(params[3]) * 1000;
			}else if(kaisou == 100){
				totalOrder = Integer.parseInt(params[3]) * 100;
			}else if(kaisou == 10){
				totalOrder = Integer.parseInt(params[3]) * 10;
			}else if(kaisou == 1){
				totalOrder = Integer.parseInt(params[3]) * 1;
			}
			System.out.printf("%4d:%s\n",totalOrder,labelName);
			params[8] = "" + totalOrder;
			String value = getConcatParams(params);
			this.targetMap.put(key, value);
		});
		return this.targetMap;
	}

	/**
	 *
	 * @return
	 */
	public Map<String,String> modifyMapAfterMove(Map<String,String> inputMap) {
		inputMap.keySet().stream().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = inputMap.get(key);
			String[] params = obj.split("/");
			String labelName = params[4];
			int kaisou = Integer.parseInt(params[5]);
			int totalOrder =0;
			if(kaisou == 10000){
				totalOrder = Integer.parseInt(params[3]) * 10000;
			}else if(kaisou == 1000){
				totalOrder = Integer.parseInt(params[3]) * 1000;
				labelName = "_" + labelName;
			}else if(kaisou == 100){
				totalOrder = Integer.parseInt(params[3]) * 100;
				labelName = "__" + labelName;
			}else if(kaisou == 10){
				totalOrder = Integer.parseInt(params[3]) * 10;
				labelName = "___" + labelName;
			}else if(kaisou == 1){
				totalOrder = Integer.parseInt(params[3]) * 1;
				labelName = "____" + labelName;
			}
			System.out.printf("%5d:%s\n",totalOrder,labelName);
			params[8] = "" + totalOrder;
			params[4] = labelName;
			String value = getConcatParams(params);
			this.targetOrderMap.replace(key, value);
		});
		//
		this.targetOrderMap.keySet().stream().sorted().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = this.targetOrderMap.get(key);
			String[] params = obj.split("/");
			String labelName = params[4];//key.split("_")[1];
			String parent = params[1];
			params[8] = key.split("_")[0];
			int totalOrder = Integer.parseInt(params[8]);
			//
			int totalOrderP = 0;
			for(int i = 0 ; i < this.targetList.size() ; i++){
				String own = this.targetList.get(i).split("/")[2];
				if(parent.equals(own)){
					totalOrderP = Integer.parseInt(this.targetList.get(i).split("/")[8]);
				}
			}
			totalOrder += totalOrderP;
			params[8] = "" + totalOrder;
			System.out.printf("%5s:%s\n",params[8],labelName);
			String value = getConcatParams(params);
			this.targetOrderMap.replace(key, value);
		});
		return this.targetOrderMap;

	}
	/**
	 * 表示順,表示用に階層構造を算出し、Mapに埋め込む
	 * @return
	 */
	public Map<String,String> modifyMap() {
		// 事前にtargetListに値が詰め込まれていること。
		for(String record : this.targetList){
			String[] params = record.split("/");
			String key = params[8] + "_" + params[4];
			this.targetOrderMap.put(key, record);
		}

		this.targetOrderMap.keySet().stream().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = this.targetOrderMap.get(key);
			String[] params = obj.split("/");
			String labelName = params[4];
			int kaisou = Integer.parseInt(params[5]);
			int totalOrder =0;
			if(kaisou == 10000){
				totalOrder = Integer.parseInt(params[3]) * 10000;
			}else if(kaisou == 1000){
				totalOrder = Integer.parseInt(params[3]) * 1000;
				labelName = "_" + labelName;
			}else if(kaisou == 100){
				totalOrder = Integer.parseInt(params[3]) * 100;
				labelName = "__" + labelName;
			}else if(kaisou == 10){
				totalOrder = Integer.parseInt(params[3]) * 10;
				labelName = "___" + labelName;
			}else if(kaisou == 1){
				totalOrder = Integer.parseInt(params[3]) * 1;
				labelName = "____" + labelName;
			}
//			System.out.printf("%5d:%s\n",totalOrder,labelName);
			params[8] = "" + totalOrder;
			params[4] = labelName;
			String value = getConcatParams(params);
			this.targetOrderMap.replace(key, value);
		});
		//
		this.targetOrderMap.keySet().stream().sorted().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = this.targetOrderMap.get(key);
			String[] params = obj.split("/");
			String labelName = params[4];//key.split("_")[1];
			params[8] = key.split("_")[0];
			String value = getConcatParams(params);
//			System.out.printf("%5s:%s\n",params[8],labelName);
			this.targetOrderMap.replace(key, value);
		});
		return this.targetOrderMap;
	}

	/**
	 * e1/e2/e3 形式の文字列返す
	 *
	 * @param params
	 * @return
	 */
	public String getConcatParams(String[] params) {
		// TODO 自動生成されたメソッド・スタブ
		Optional<String> a =Arrays.asList(params).stream().reduce((value1, value2) -> value1.concat("/").concat(value2));
		return a.get();
	}

	/**
	 * 表示順序の算出
	 * dataArray[i][8]:表示順列
	 *
	 * @param dataArray
	 * @return
	 */
	public String[][] addOrderNoInArray(String[][] dataArray) {
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
	 * 検索対象のラベルが親の場合、子供の人数を調べて返す。
	 * @param dataArray
	 * @param targetLabel
	 * @return
	 */
	public int getLevelOrder(String targetLabel){
		int levelOrder = this.targetList.stream()
				.map(s -> s)
				.filter(s -> (s.split("/")[1].equals(targetLabel)))
				.collect(Collectors.toList()).size();
		return levelOrder;
	}

	/**
	 * 検索対象のラベルの階層を取得し、１階層下の階層値を取得する
	 * @param dataArray
	 * @param targetLabel
	 * @return
	 */
	public String getNextLevel(String targetLabel){
		String nextLevel = this.targetList.stream()
				.map(s -> s)
				.filter(s -> (s.split("/")[2].equals(targetLabel)))
				.map(s -> s.split("/")[5])
			    .collect(Collectors.joining());
		System.out.println(nextLevel);
		nextLevel = "0".concat(nextLevel).substring(0, 5);
		return nextLevel;
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
	public Map<String,String> updateOneData(String parent,String own,String orderdiv,String name,String levels,String analizecd,String deleteflg,String order){
		for(int i = 0 ; i < this.targetList.size() ; i++){
			// データの一意識別は、あくまでも「ラベルID」のみで構わない。
			String[] params = this.targetList.get(i).split("/");
			if( params[2].equals(own)){
				params[1] = parent;
				params[3] = orderdiv;
				params[4] = name;
				params[5] = levels;
				params[6] = analizecd;
				params[7] = deleteflg;
				params[8] = order;
			}
			String value = getConcatParams(params);
			System.out.println(value);// B-2の８番目がおかしい。
			this.targetMap.replace(params[2], value);
			this.targetOrderMap.replace(params[8] + "_" + params[4], value);
		}
		return this.targetOrderMap;
	}



}
