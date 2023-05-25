package com.example.dfs;

public class Permutation {
	public static void main(String[] args) {
		
		Permutation p = new Permutation();
		//p.dfs("abc", new StringBuilder());
		//p.dfs("abc","");
		String str = "abc";
		appendStr(str);
		System.out.print(str);
	}
	static String appendStr(String str) {
		str += "bbb";
		System.out.print(str);
		return str;
	}
	private void dfs(String str, StringBuilder sb) {
		if(str.length() == 0) {
			System.out.println(sb.toString() + ",");
			return;
		}
		for(int i = 0; i < str.length(); i++) {
			String rem = str.substring(0,i) + str.substring(i + 1);
			
			sb.append(str.charAt(i));
			//System.out.println("rem:"+rem);
			//System.out.println(sb.toString());
			dfs(rem, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	private void dfs(String str, String prefix) {
		if(str.length() == 0) {
			System.out.println(prefix + ",");
			return;
		}
		for(int i = 0; i < str.length(); i++) {
			String rem = str.substring(0,i) + str.substring(i + 1);
			dfs(rem, prefix + str.charAt(i));
		}
	}
}
