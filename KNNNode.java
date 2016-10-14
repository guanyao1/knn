package knn;

/**
 * KNN����࣬�����洢����ڵ�K��Ԫ��������Ϣ
 * 
 * */
public class KNNNode {

	private int index;//Ԫ����
	private double distance;//�����Ԫ��ľ���
	private String category;//�������
	
	public KNNNode(int index, double distance, String category) {  
        this.index = index;  
        this.distance = distance;  
        this.category = category;  
    }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
