package jp.co.eit.listsample.listsample01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Algorithm01 {

	/**
	 * 対象のラベル一覧情報のMap：Keyはラベルコード
	 * A-1
	 */
	private Map<String,String> targetMap;

	public Map<String, String> getTargetMap() {
		return targetMap;
	}

	public void setTargetMap(Map<String, String> targetMap) {
		this.targetMap = targetMap;
	}

	public Map<String, String> getTargetOrderMap() {
		return targetOrderMap;
	}

	public void setTargetOrderMap(Map<String, String> targetOrderMap) {
		this.targetOrderMap = targetOrderMap;
	}

	public List<String> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<String> targetList) {
		this.targetList = targetList;
	}

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
			params[8] = "" + totalOrder;
			String value = getConcatParams(params);
			System.out.printf("%5d:%s:%s\n",totalOrder,key,value);
			this.targetMap.put(key, value);
		});
		// もう一回親の順序を組み込む必要がある。addOrderNoInArrayを参考にすること。
		Set<String> keyset = this.targetMap.keySet();
		Iterator<String> ite = keyset.iterator();
		int totalOrder = 0;
		List<String> tList = new ArrayList<String>();
		while(ite.hasNext()){
			String key = ite.next();
			String value1 = this.targetMap.get(key);
			String[] params = value1.split("/");
			String parent = params[1];
			totalOrder = Integer.parseInt(params[8]);
			int totalOrderP = 0;
			Set<String> keyset2 = this.targetMap.keySet();
			Iterator<String> ite2 = keyset2.iterator();
			while(ite2.hasNext()){
				String key2 = ite2.next();
				String value2 = this.targetMap.get(key2);
				String[] params2 = value2.split("/");
				String own = params2[2];
				if(parent.equals(own)){
					totalOrderP = Integer.parseInt(params2[8]);
				}
			}
			totalOrder += totalOrderP;
			params[8] = "" + totalOrder;
			String value = getConcatParams(params);
			tList.add(value);
			System.out.printf("%5d:%s:%s\n",totalOrder,key,value);
			this.targetMap.put(key, value);
		}
		// this.targetListも詰めなおす。
		this.targetList.clear();
		this.targetList.addAll(tList);
		createTargetOrderMapByTargetList();// targetOrderMap
		return this.targetMap;
	}

	public Map<String,String> modifyMapAfterMove() {
		System.out.println("変換後のはず：");
		this.targetMap.keySet().stream().forEach((key) -> {
//			System.out.println(key + ":" );
			String obj = this.targetMap.get(key);
//			System.out.println("変換後のはず：" + obj);
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
			params[8] = "" + totalOrder;
			params[4] = labelName;
			String value = getConcatParams(params);
			System.out.printf("%5d:%s:%s\n",totalOrder,labelName,value);
			this.targetMap.replace(key, value);
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
//		this.targetOrderMap.clear();
//		this.targetOrderMap.putAll(map1);
		return this.targetOrderMap;

	}
	/**
	 * 表示順,表示用に階層構造を算出し、Mapに埋め込む
	 * @return
	 */
	public Map<String,String> modifyMap() {
		// 事前にtargetListに値が詰め込まれていること。
//		System.out.println("1:" + this.targetOrderMap.size());
//		System.out.println("1:" + this.targetList.size());
		createTargetOrderMapByTargetList();
//		System.out.println("2:" + this.targetOrderMap.size());
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
//		System.out.println("3:" + this.targetOrderMap.size());
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
//		System.out.println("4:" + this.targetOrderMap.size());
		return this.targetOrderMap;
	}

	private void createTargetOrderMapByTargetList() {
		this.targetOrderMap.clear();
		for(String record : this.targetList){
			String[] params = record.split("/");
			String key = params[8] + "_" + params[4];
			this.targetOrderMap.put(key, record);
		}
//		System.out.println(this.targetList.size());
//		System.out.println(this.targetOrderMap.size());
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
	 * 検索対象のラベルが親の場合、新しい子供を作る場合の順番を返す。
	 * @param targetLabel
	 * @return
	 */
	public int getNextLevelOrder(String targetLabel){
		return getLevelOrder(targetLabel) + 1;
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

		if(nextLevel.isEmpty()){
			nextLevel = "10000";
		}else{
			nextLevel = "0".concat(nextLevel).substring(0, 5);
		}
		System.out.println(nextLevel);
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
		List<String> tList = new ArrayList<String>();
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
				params[8] = order;//"0000";// 引数で渡さずにダミー値を入れておく
			}
//			params[8] = order;// input ではわたってこない。全部イニシャライズする。
			String value = getConcatParams(params);
			System.out.println("変換後：" + value);
			tList.add(value);
			this.targetMap.replace(params[2], value);
//			this.targetOrderMap.replace(params[8] + "_" + params[2], value);
		}
		addOrderNoInMap();// ここで第一段階での８番目の要素が入る。まだ親を考慮していない。
//		addOrderNoInMap();
//		System.out.println("targetOrderMap");
//		dispOut(targetOrderMap);
//		this.targetList.clear();
//		this.targetList.addAll(tList);// 詰め直し
		return this.targetMap;
	}

	/**
	 * 指定されたMap表示
	 * @param map
	 */
	public void dispOut(Map<String, String> map) {
		map.keySet().stream().sorted().forEach((key) -> {
			String e = map.get(key);
			String[] params = e.split("/");
			String labelName = params[4];//key.split("_")[1];
			params[8] = key.split("_")[0];
			String value = getConcatParams(params);
			System.out.printf("[%5s]:%s:%s\n",key,labelName,value);
		});
	}

	/**
	 * targetMap表示
	 */
	public void dispOut() {
		this.targetMap.keySet().stream().sorted().forEach((key) -> {
			String e = this.targetMap.get(key);
			String[] params = e.split("/");
			String labelName = params[4];//key.split("_")[1];
			params[8] = key.split("_")[0];
			String value = getConcatParams(params);
			System.out.printf("[%5s]:%s:%s\n",key,labelName,value);
		});
	}

	/**
	 * 対象ラベルの指定プロパティの値の取得
	 * @param key 対象ラベルコード
	 * @param index プロパティのインデックス
	 * @return
	 */
	public String getParam(String key,int index) {
		// TODO 自動生成されたメソッド・スタブ
		String data = this.targetMap.get(key);
		String[] params = data.split("/");
//		int order = Integer.parseInt(params[3]);
		return params[index];
	}

	/**
	 * 最終的な表示順の更新
	 * @param param8 移動後の親ラベルの順位
	 * @param nextLevel 対象ラベルの移動後の階層
	 * @param nextLevelOrder 対象ラベルの移動後の階層順位
	 * @return
	 */
	public String getNextDispOrder(String param8, String nextLevel, int nextLevelOrder) {
		// TODO 自動生成されたメソッド・スタブ
		int intParam8 = Integer.parseInt(param8) + Integer.parseInt(nextLevel) * nextLevelOrder;
		return "" + intParam8;
	}

	/**
	 * 移動後の親のラベルコードを指定して、対象のデータを更新する
	 * params[1]:移動後の親のラベルコード
	 * params[3]:移動後の自分の階層における順位
	 * params[5]:移動後の自分の階層
	 * params[8]:最終的な表示順
	 *
	 * @param moveToAsParent 移動後の親のラベルコード
	 * @param target 対象のラベルコード
	 * @return
	 */
	public String getUpdateParam(String moveToAsParent, String target) {
		// TODO 自動生成されたメソッド・スタブ
		// 移動先の情報の取得
		int levelOrder = getLevelOrder(moveToAsParent);
		int nextLevelOrder = levelOrder + 1;
//		System.out.println("移動後の親の子供数：" + levelOrder);
		String nextLevel = getNextLevel(moveToAsParent);
//		System.out.println("nextLevel：" + nextLevel);
		String param8 = "";//getParam(moveToAsParent,8);
		if(moveToAsParent.equals("0000")){
			param8 = "00000";
		}else{
			param8 = getParam(moveToAsParent,8);
		}
//		System.out.println("param8：" + param8);
		String nextDispOrder = getNextDispOrder(param8,nextLevel,nextLevelOrder);
//		System.out.println("nextDispOrder：" + nextDispOrder);
		String data = this.targetMap.get(target);
		String[] params = data.split("/");
		//
		params[1] = moveToAsParent;
		params[3] = "" + nextLevelOrder;
		params[5] = nextLevel;
		params[8] = nextDispOrder;
		String value = getConcatParams(params);
		return value;
	}

	/**
	 * 対象のラベルコードを親とする子供のラベルコードのリストを「_」区切りで数珠つなぎにして返す
	 * @param targetLabel
	 * @return
	 */
	public String getChilds(String targetLabel) {
		// TODO 自動生成されたメソッド・スタブ
		String ret = this.targetList.stream()
				.map(s -> s)
				.filter(s -> (s.split("/")[1].equals(targetLabel)))
				.map(s -> s.split("/")[2])
			    .collect(Collectors.joining("_"));
		return ret;
	}

	/**
	 * 変更後のデータに詰め替える
	 * @param toParent 移動先の親ラベルコード
	 * @param target   移動対象のラベルコード
	 */
	public void updateData(String toParent, String target) {
		// TODO 自動生成されたメソッド・スタブ
		String updateRecord = getUpdateParam(toParent,target);
//		System.out.println("updateRecord：" + updateRecord);
		updateDataSet(updateRecord);
		// 移動対象のラベルに子供がいる場合は、子供も一緒に移動するため、子供のラベルコードリストを取得する
		String childs = getChilds(target);
//		System.out.println("childs：" + childs);
		String[] childArr = childs.split("_");
		for(String e : childArr){
			// 影響範囲のある子供の情報を変更する
			// 再帰
			if(!e.isEmpty()){
				System.out.println("to：" + target + " from:" + e);
				updateData(target,e);
			}
//			String record = getUpdateParam(target,e);
//			System.out.println("updateRecord：" + record);
//			updateDataSet(record);
		}
		// for debug
//		System.out.println(this.targetList.size());
//		System.out.println(this.targetMap.size());
//		System.out.println(this.targetOrderMap.size());
	}

	/**
	 * 更新されたvalueを受け取り、this.targetMap、this.targetOrderMap、this.targetListすべて更新する
	 * @param updateRecord 更新されたvalue
	 */
	public void updateDataSet(String updateRecord) {
		// TODO 自動生成されたメソッド・スタブ
		String[] params = updateRecord.split("/");
		String key = params[2];
		this.targetMap.replace(key,updateRecord);
		String key2 = params[8] + "_" + params[4];
		this.targetOrderMap.replace(key2, updateRecord);
		int index = 0;
		for(int i = 0 ; i < this.targetList.size() ; i++){
			String[] params2 = this.targetList.get(i).split("/");
			if(params2[2].equals(key)){
				index = i;
				break;
			}
		}
		this.targetList.remove(index);
		this.targetList.add(index, updateRecord);
		for(int i = 0 ; i < this.targetList.size() ; i++){
//			System.out.println("[]" + this.targetList.get(i));
		}
	}

}
