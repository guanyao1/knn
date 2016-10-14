package knn;

/**
 * KNN结点类，用来存储最近邻的K个元组的相关信息
 * 
 * */
public class KNNNode {

	private int index;//元组标号
	private double distance;//与测试元组的距离
	private String category;//所属类别
	
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
