package OpinionRetrieval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class QueryExpansion {

	/**
	 * @param args
	 */
	private ArrayList<String> hierarchys = new ArrayList<String>();
	private HashMap<String,Integer> prefixtabnum = new HashMap<String,Integer>();
	public String queryexpansion(String query){
		HashSet<String> hypernyms = getHypernym( query );
		HashSet<String> hyponyms = getHyponym( query );
		StringBuffer sb = new StringBuffer();
		for(String s : hypernyms)
			sb.append(s+"||");
		for(String s : hyponyms)
			sb.append(s+"||");
		return sb.toString();
	}
	public HashSet<String> getHypernym(String word){//ȡ����λ��
		word = word.trim().toLowerCase();
		HashSet<String> hypernyms = new HashSet<String>();
		int prefixtab_tmp = 0;
		int line_num = 0;
		int i = 0;
		for( i=0; i < hierarchys.size(); ++i){
			if(word.equalsIgnoreCase(hierarchys.get(i))){
				prefixtab_tmp = prefixtabnum.get(word);
				line_num = i;
				break;
			}
		}
		if(i == hierarchys.size()){
			System.out.println("��λ�ʲ��ҹ��̣��Ҳ���ƥ�����λ�ʽ��Ϊ�գ�");
			return hypernyms;
		}
		int prefixtab_update = prefixtab_tmp;
		for( i = line_num-1; i>=1; i--){
			if(prefixtabnum.get(hierarchys.get(i)) < prefixtab_update){
				hypernyms.add( hierarchys.get(i) );
				prefixtab_update = prefixtabnum.get(hierarchys.get(i));
			}
		}
		
		return hypernyms;
	}
	public HashSet<String> getHyponym(String word){//ȡ����λ��
		word = word.trim().toLowerCase();
		HashSet<String> hyponyms = new HashSet<String>();
		int prefixtab_tmp = 0;
		int line_num = 0;
		int i=0;
		for( i=0; i < hierarchys.size(); ++i){
			if(word.equalsIgnoreCase(hierarchys.get(i))){
				prefixtab_tmp = prefixtabnum.get(word);
				line_num = i;
				break;
			}
		}
		if(i == hierarchys.size()){
			System.out.println("��λ�ʲ��ҹ��̣��Ҳ���ƥ�����λ�ʽ��Ϊ�գ�");
			return hyponyms;
		}
		for( i=line_num+1; i < hierarchys.size(); ++i){
			if(prefixtabnum.get(hierarchys.get(i)) > prefixtab_tmp){
				hyponyms.add(hierarchys.get(i));
			}else{
				System.out.println("---");
				break;
			}
		}

		return hyponyms;
	}
	public int counttabchar(String line){//����aspect�������м���tab��
		int count = 0;
		for(int i = 0; i < line.length(); i++)
			if(line.charAt(i) == '\t')
				count ++;
			else break;
		return count;
				
	}
	public QueryExpansion(String domain, String productname) throws IOException{
		File f = new File("./AspectHierarchy/"+domain+"/"+productname+"/hierarchy.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = null;
		while((line = br.readLine()) != null){
			hierarchys.add(line.trim().toLowerCase());
			prefixtabnum.put(line.trim().toLowerCase(), counttabchar(line));
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String domain = "phone";
		String productname = "Nokia_N95";
		QueryExpansion qe = new QueryExpansion(domain, productname);
		
		System.out.println(qe.getHypernym("nokia_n95"));
	}

}
