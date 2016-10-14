package knn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * KNN�㷨������
 * @author liuwei
 * */
public class KNN {

	/**
	 * �������ȼ����еıȽϺ���������Խ�����ȼ�Խ��
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
	 * ��ȡK����ͬ�������
	 * @param k ������ĸ���
	 * @param maxRange ��������ķ�Χ 
	 * @return ���ɵ����������
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
	 * �������Ԫ����ѵ��Ԫ��֮��ľ���
	 * @param testDis ����Ԫ��
	 * @param tranDis ѵ��Ԫ��
	 * @return ����ֵ
	 * */
	public double calDistance(List<Double> testDis,List<Double> tranDis){
		double distance = 0.00;//����Ԫ����ѵ��Ԫ��֮��ľ���
		for (int i = 0; i < testDis.size(); i++) {
			distance += (testDis.get(i) - tranDis.get(i)) * (testDis.get(i) - tranDis.get(i));
		}
		return distance;
	}
	
	/**
	 * ִ��KNN�㷨����ȡ����Ԫ������
	 * @param datas ѵ�����ݼ� 
       @param testData ����Ԫ�� 
       @param k �趨��Kֵ 
       @return ����Ԫ������
	 * */
	public String knn(List<List<Double>> datas,List<Double> testData,int k){
		PriorityQueue<KNNNode> pq = new PriorityQueue<KNNNode>(k,comparator);// ʹ��ָ���ĳ�ʼ����k����һ�� PriorityQueue��������ָ���ıȽ���comparator��Ԫ�ؽ�������
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
	 * ��ȡ���õ���k�������Ԫ�������
	 * @param pq �洢k���������Ԫ������ȼ�����
	 * @return �����������
	 * */
	private String getMostClass(PriorityQueue<KNNNode> pq){
		Map<String,Integer> classCount = new HashMap<String, Integer>();
		for (int i = 0; i < pq.size(); i++) {
			KNNNode node = pq.remove();//��ȡ���Ƴ��˶��е�ͷ
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
