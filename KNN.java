package knn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * KNN算法主体类
 * @author liuwei
 * */
public class KNN {

	/**
	 * 设置优先级队列的比较函数，距离越大，优先级越高
	 * */
	private Comparator<KNNNode> comparator = new Comparator<KNNNode>(){
		@Override
		public int compare(KNNNode o1,KNNNode o2){
			if(o1.getDistance() >= o2.getDistance()){
				return 1;
			}else{
				return 0;
			}
		}
	};
	
	/**
	 * 获取K个不同的随机数
	 * @param k 随机数的个数
	 * @param maxRange 随机数最大的范围 
	 * @return 生成的随机数数组
	 * */
	public List<Integer> getRandNum(int k,int maxRange){
		List<Integer> rand = new ArrayList<Integer>(k);
		for (int i = 0; i < k; i++) {
			int temp = (int) (Math.random() * maxRange);
			if(!rand.contains(temp)){
				rand.add(temp);
			}else{
				i--;
			}
		}
		return rand;
	}
	
	/**
	 * 计算测试元组与训练元组之间的距离
	 * @param testDis 测试元组
	 * @param tranDis 训练元组
	 * @return 距离值
	 * */
	public double calDistance(List<Double> testDis,List<Double> tranDis){
		double distance = 0.00;//测试元组与训练元组之间的距离
		for (int i = 0; i < testDis.size(); i++) {
			distance += (testDis.get(i) - tranDis.get(i)) * (testDis.get(i) - tranDis.get(i));
		}
		return distance;
	}
	
	/**
	 * 执行KNN算法，获取测试元组的类别
	 * @param datas 训练数据集 
       @param testData 测试元组 
       @param k 设定的K值 
       @return 测试元组的类别
	 * */
	public String knn(List<List<Double>> datas,List<Double> testData,int k){
		PriorityQueue<KNNNode> pq = new PriorityQueue<KNNNode>(k,comparator);// 使用指定的初始容量k创建一个 PriorityQueue，并根据指定的比较器comparator对元素进行排序。
		List<Integer> randNum = getRandNum(k, datas.size());
		for (int i = 0; i < k; i++) {
			int index = randNum.get(i);
			List<Double> currData = datas.get(index);
			String c = currData.get(currData.size() - 1).toString();
			KNNNode node = new KNNNode(index, calDistance(testData, currData), c);
			
			pq.add(node);
		}
		for (int i = 0; i < datas.size(); i++) {
			List<Double> t = datas.get(i);
			double distance = calDistance(testData, t);
			KNNNode topNode = pq.peek();
			if(topNode.getDistance() > distance){
				pq.remove();
				pq.add(new KNNNode(i, distance, t.get(t.size() - 1).toString()));
			}
		}
		
		return getMostClass(pq);
	}
	
	
	
	/**
	 * 获取所得到的k个最近邻元组的种类
	 * @param pq 存储k个最近近邻元组的优先级队列
	 * @return 多数类的名称
	 * */
	private String getMostClass(PriorityQueue<KNNNode> pq){
		Map<String,Integer> classCount = new HashMap<String, Integer>();
		for (int i = 0; i < pq.size(); i++) {
			KNNNode node = pq.remove();//获取并移除此队列的头
			String category = node.getCategory();
			if(classCount.containsKey(category)){
				classCount.put(category, classCount.get(category) + 1);
			}else{
				classCount.put(category, 1);
			}
		}
		int maxIndex = -1;
		int maxCount = 0;
		Object[] classes = classCount.keySet().toArray();
		for (int i = 0; i < classes.length; i++) {
			if(classCount.get(classes[i]) > maxCount){
				maxIndex = i;
				maxCount = classCount.get(classes[i]);
			}
		}
		return classes[maxIndex].toString();
	}
}
