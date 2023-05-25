package com.example.dfs;

public class GenerateBinaryNum {
	public static void  main(String[] args) {
		GenerateBinaryNum ge = new GenerateBinaryNum();
		ge.generate(3, "");
	}
	private void generate(int n, String track) {
		if(n == 0) {
			System.out.println(track);
			return;
		}
		for(int i = 0; i < 2; i++) {
			track += i;
			generate(n - 1, track);
			track = track.substring(0,track.length()-1);
			
		}
	}
}
