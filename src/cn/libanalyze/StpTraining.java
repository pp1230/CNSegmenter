package cn.libanalyze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author pi 训练state transition probabilities
 * @version 07/01/2015
 */

public class StpTraining {

	public String stopword;

	public String resultStr[];
	
	public int resultInt[];

	public String sign;
	
	public String stpdata;

	public StpTraining() {
		super();
	}

	public void readStpw(String stopwordfile) {
		String lineTxt = "";
		try {
			String encoding = "UTF-8";
			File file = new File(stopwordfile);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					lineTxt += line;
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

		stopword = lineTxt;

	}

	public void makesign(String cnlibfile, String stopwordfile) {
		String lineTxt = "";
		readStpw(stopwordfile);
		try {
			String encoding = "UTF-8";
			File file = new File(cnlibfile);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String line = null;
				int k = 0;
				while ((line = bufferedReader.readLine()) != null) {
					String chara[] = line.split("  ");
					for (int i = 0; i < chara.length; i++) {
						if (stopword.contains(chara[i])) {
							chara[i] = "|";
						} else if (chara[i].length() == 1) {
							chara[i] = "S";
						} else if (chara[i].length() == 2) {
							chara[i] = "BE";
						} else if (chara[i].length() == 3) {
							chara[i] = "BME";
						} else if (chara[i].length() == 4) {
							chara[i] = "BMME";
						} else if (chara[i].length() > 4) {
							String c = "B";
							for (int j = 0; j < chara[i].length() - 2; j++) {
								c += "M";
							}
							c += "E";
							chara[i] = c;
						}
					}
					for (int i = 0; i < chara.length; i++)
						lineTxt += chara[i];
					k++;
					System.out.println(k);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		sign = lineTxt;

	}

	public String readdata(String filePath){
		String data = "";
		// readStpw(stopwordfile);
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					data += line;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return data;
	}
	public String culculatestp(String data) {

		String[] signword = data.split("\\|");
		String[] signs = new String[signword.length];
		// String[][] stpresult = new String[signword.length][1] ;
		int[] count = new int[signword.length];
		count[0] = 1;
		signs[0] = signword[0];
		for (int i = 1; i < signword.length; i++) {
			if(signword[i]!=null&&!signword[i].equals(""))
			for (int j = 0; j <= i; j++) {
				if (signs[j] == null) {
					count[j] += 1;
					signs[j] = signword[i];
					break;
				}
				else if (signs[j].equals(signword[i])) {
					count[j] += 1;
					break;
				}
			}
			System.out.println(i+signword[i]);
		}

		resultStr = signs;
		resultInt = count;

		String dataStr = "";
		for(int i=0;i<signs.length;i++){
			if(signs[i]!=null){
				if(!signs[i].equals("")&&!signs[i].equals(""))
			dataStr += signs[i] + " " +resultInt[i] +"|" ;
			}
			else break;
		}
		stpdata = dataStr;
		return dataStr;
	}
	

	public String getStopword() {
		return stopword;
	}


	public String getSign() {
		return sign;
	}

	public String[] getResultStr() {
		return resultStr;
	}


	public int[] getResultInt() {
		return resultInt;
	}

	public String getStpdata() {
		return stpdata;
	}


	
}
