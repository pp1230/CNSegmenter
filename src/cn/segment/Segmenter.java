package cn.segment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Segmenter {

	private String sentence;

	private String stpfilePath;

	private String opfilePath;

	private String signresult;

	public Segmenter(String sentence, String stpfilePath, String opfilePath) {
		super();
		this.sentence = sentence;
		this.stpfilePath = stpfilePath;
		this.opfilePath = opfilePath;
	}

	public void segment() {
		if (sentence != null && stpfilePath != null && opfilePath != null) {

			int stplen = sentence.length();
			String lineText = "";
			String[] allstp;
			String[] stp;
			int[] stpnum;
			int stparrlen = 0;
			try {
				String encoding = "UTF-8";
				File file = new File(stpfilePath);
				if (file.isFile() && file.exists()) { // 判断文件是否存在
					InputStreamReader read = new InputStreamReader(
							new FileInputStream(file), encoding);// 考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);
					String line = null;
					while ((line = bufferedReader.readLine()) != null) {
						lineText += line;
						// System.out.println(lineTxt);
					}
					read.close();
				} else {
					System.out.println("找不到指定的文件");
				}
			} catch (Exception e) {
				System.out.println("读取文件内容出错");
				e.printStackTrace();
			}

			allstp = lineText.split("\\|");
			stp = new String[allstp.length];
			stpnum = new int[allstp.length];
			for (int i = 0; i < allstp.length; i++) {
				if (allstp[i].split(" ")[0].length() == stplen) {
					for (int j = 0; j < stp.length; j++) {
						if (stp[j] == null) {
							stp[j] = allstp[i].split(" ")[0];
							stpnum[j] = Integer
									.valueOf(allstp[i].split(" ")[1]);
							stparrlen++;
							//System.out.println(stp[j] + " " + stpnum[j]);
							break;
						}
					}
				}
			}

			String[][] ops;
			String[] charas;
			lineText = "";
			try {
				String encoding = "UTF-8";
				File file = new File(opfilePath);
				if (file.isFile() && file.exists()) { // 判断文件是否存在
					InputStreamReader read = new InputStreamReader(
							new FileInputStream(file), encoding);// 考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);
					String line = null;
					while ((line = bufferedReader.readLine()) != null) {
						lineText += line;
						// System.out.println(lineTxt);
					}
					read.close();
				} else {
					System.out.println("找不到指定的文件");
				}
			} catch (Exception e) {
				System.out.println("读取文件内容出错");
				e.printStackTrace();
			}
			charas = lineText.split("\\|");
			ops = new String[charas.length][5];
			for (int i = 0; i < charas.length; i++) {
				String[] s1 = null;
				if (charas[i].contains("B")) {
					s1 = charas[i].split("B");
					for (int m = 0; m < ops.length; m++) {
						if (ops[m][0] == null) {
							ops[m][0] = s1[0];
							ops[m][1] = s1[1];
							break;
						} else if (ops[m][0].equals(s1[0])) {
							ops[m][1] = s1[1];
							break;
						}
					}
				} else if (charas[i].contains("M")) {
					s1 = charas[i].split("M");
					for (int m = 0; m < ops.length; m++) {
						if (ops[m][0] == null) {
							ops[m][0] = s1[0];
							ops[m][2] = s1[1];
							break;
						} else if (ops[m][0].equals(s1[0])) {
							ops[m][2] = s1[1];
							break;
						}
					}
				} else if (charas[i].contains("E")) {
					s1 = charas[i].split("E");
					for (int m = 0; m < ops.length; m++) {
						if (ops[m][0] == null) {
							ops[m][0] = s1[0];
							ops[m][3] = s1[1];
							break;
						} else if (ops[m][0].equals(s1[0])) {
							ops[m][3] = s1[1];
							break;
						}
					}
				} else if (charas[i].contains("S")) {
					s1 = charas[i].split("S");
					for (int m = 0; m < ops.length; m++) {
						if (ops[m][0] == null) {
							ops[m][0] = s1[0];
							ops[m][4] = s1[1];
							break;
						} else if (ops[m][0].equals(s1[0])) {
							ops[m][4] = s1[1];
							break;
						}
					}
				}
//				System.out.println(ops[i / 4][0] + " " + ops[i / 4][1] + " "
//						+ ops[i / 4][2] + " " + ops[i / 4][3] + " "
//						+ ops[i / 4][4]);
			}

			int[] stpresult;
			int[][] opresult;
			int n1 = 0;
			for (int x1 = 0; x1 < stpnum.length; x1++) {
				if (stpnum[x1] != 0)
					n1++;
				else
					break;
			}
			//System.out.println(n1);
			stpresult = new int[n1];
			for (int x2 = 0; x2 < n1; x2++) {
				stpresult[x2] = stpnum[x2];
				System.out.println(stpresult[x2]);
			}

			opresult = new int[n1][stplen];
			String[] word = sentence.split("");
			for (int y1 = 0; y1 < n1; y1++) {
				String[] signs = stp[y1].split("");
				for (int y2 = 0; y2 < stplen; y2++) {
					if (signs[y2+1].equals("B")) {
						for (int y3 = 0; y3 < ops.length; y3++) {
							if (ops[y3][0].equals(word[y2+1])) {
								if (!ops[y3][1].equals("null"))
									opresult[y1][y2] = Integer
											.valueOf(ops[y3][1]);
								else
									opresult[y1][y2] = 0;
								break;
							}
						}
					} else if (signs[y2+1].equals("M")) {
						for (int y3 = 0; y3 < ops.length; y3++) {
							if (ops[y3][0].equals(word[y2+1])) {
								if (!ops[y3][2].equals("null"))
									opresult[y1][y2] = Integer
											.valueOf(ops[y3][2]);
								else
									opresult[y1][y2] = 0;
								break;
							}
						}
					} else if (signs[y2+1].equals("E")) {
						for (int y3 = 0; y3 < ops.length; y3++) {
							if (ops[y3][0].equals(word[y2+1])) {
								if (!ops[y3][3].equals("null"))
									opresult[y1][y2] = Integer
											.valueOf(ops[y3][3]);
								else
									opresult[y1][y2] = 0;
								break;
							}
						}
					} else if (signs[y2+1].equals("S")) {
						for (int y3 = 0; y3 < ops.length; y3++) {
							if (ops[y3][0].equals(word[y2+1])) {
								if (!ops[y3][4].equals("null"))
									opresult[y1][y2] = Integer
											.valueOf(ops[y3][4]);
								else
									opresult[y1][y2] = 0;
								break;
							}
						}
					}
				}
			}
			
//			for(int t = 0;t<opresult.length;t++)
//				System.out.println(opresult[t][0]+" "+opresult[t][1]+" "+opresult[t][2]+" "+opresult[t][3]);

//			HMM hmm = new HMM(stpresult, opresult);
//			hmm.culpro();
//			System.out.println(hmm.getNum()+" "+hmm.getPro()+" "+stp[hmm.getNum()]);
			
			double[] stpdo = new double[stpresult.length];
			for(int i=0;i<stpresult.length;i++){
				stpdo[i] = stpresult[i]/100.0;
				System.out.println(stpdo[i]);
			}
			
			double[][] opdo = new double[n1][stplen];
			for(int i = 0;i<opresult.length;i++){
				for(int j = 0;j<opresult[0].length;j++){
					opdo[i][j] = opresult[i][j];
					System.out.println(opdo[i][j]);
				}
			}
			
			HMM hmm = new HMM(stpdo, opdo);
			hmm.culpro();
			System.out.println(hmm.getNum()+" "+hmm.getPro()+" "+stp[hmm.getNum()]);
			
			char[] word1 = sentence.toCharArray();
			char[] sign1 = stp[hmm.getNum()].toCharArray();
			for(int i = 0;i<sentence.length();i++){
				if(sign1[i] == 'E' || sign1[i] == 'S')
				System.out.print(word1[i]+"  ");
				else
					System.out.print(word1[i]);
			}
		}
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getStpfilePath() {
		return stpfilePath;
	}

	public void setStpfilePath(String stpfilePath) {
		this.stpfilePath = stpfilePath;
	}

	public String getOpfilePath() {
		return opfilePath;
	}

	public void setOpfilePath(String opfilePath) {
		this.opfilePath = opfilePath;
	}

}
