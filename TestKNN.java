package knn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * KNN算法测试类
 * */
public class TestKNN {

	/**
	 * 程序执行入口
	 * @param args
	 */
	public static void main(String[] args) {
		TestKNN testKnn = new TestKNN();
		String dataFile = "C:\\Users\\wang4\\Desktop\\data.txt";
		String testFile = "C:\\Users\\wang4\\Desktop\\test.txt";
		
		try {
			List<List<Double>> datas = new ArrayList<List<Double>>();
			List<List<Double>> testDatas = new ArrayList<List<Double>>();
		
			testKnn.read(datas, dataFile);
			testKnn.read(testDatas, testFile);
			
			KNN knn = new KNN();
			for (int i = 0; i < testDatas.size(); i++) {
				List<Double> test = testDatas.get(i);
				System.out.print("测试元组：");
				for (int j = 0; j < test.size(); j++) {
					System.out.print(test.get(j) + " ");
				}
				
				System.out.print("类别为：");
				System.out.println(Math.round(Float.parseFloat((knn.knn(datas, test, 3)))));  
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 从数据文件中读取数据
	 * @param datas 存储数据的集合对象
	 * @param path 数据文件的路径
	 * @throws IOException 
	 * */
	public void read(List<List<Double>> datas,String path) throws IOException{
		BufferedReader br = null;
		List<Double> list = null;
		try {
			br = new BufferedReader(new FileReader(new File(path)));
			
			String data = br.readLine();//读取一个文本行
			while(data != null){
				String t[] = data.split(" ");
				list = new ArrayList<Double>();
				for (int i = 0; i < t.length; i++) {
					list.add(Double.parseDouble(t[i]));
				}
				datas.add(list);
				data = br.readLine();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
