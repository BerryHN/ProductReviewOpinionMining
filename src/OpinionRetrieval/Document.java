package OpinionRetrieval;

import java.util.HashMap;

public class Document {

	/**
	 * @param args
	 */
	
	public String documentname = null;
	public HashMap<String,Double> documentTF = null;
	public HashMap<String,Integer> documentWC = null;
	public String documentContent = null;
	public int documentlength = 0;
	public double ModelSimilarity = 0;
	public double manualAnnotationScore = 0.0;
	public double star = 0.0;
	public double helpfulness = 0.0;//SVR�õ���helpfulness
	public double Scorehelpful = 0.0;//Shelpful��ʽ�õ������query��helpfulness
	public static double allhelpfulness = 0.0;//��������������ĵ���SVR helpfulness֮��
	public static double maxhelpfulness = 0.0;//������������Shelpful
	public static double minhelpfulness = 0.0;//�����������СShelpful
	
	public Document(String adocumentname){
		documentname = adocumentname;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
