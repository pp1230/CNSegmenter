package cn.libanalyze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class OpTraining {

	public String stopword;

	public String result;

	public OpTraining() {
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

	public void getobs(String cnlibfile, String stopwordfile) {
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
						}
						// else if(chara[i].length()==1){
						// chara[i] += "|";
						// }
						// else if(chara[i].length()>=2){
						// char[] strChar = chara[i].toCharArray();
						// String b = "";
						// for(int m=0;m<strChar.length;m++){
						// b += strChar[m]+"|";
						// }
						// chara[i] = b;
						// }
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

		result = lineTxt;

	}

	public String optraining(String signfilePath, String pofilePath) {
		String[] signdata = readdata(signfilePath).split("");
		String[] podata = readdata(pofilePath).split("");
		String[][] spdata = new String[5][signdata.length];
		String data = "";
		for (int i = 0; i < signdata.length; i++) {
			if (!signdata[i].equals("|") && podata[i] != null) {
				//System.out.println(i + " " + signdata[i] + podata[i]);
				if (signdata[i].equals("B")) {
					if (spdata[0][0] == null) {
						spdata[0][0] = podata[i];
						spdata[1][0] = "1";
					} else {
						for (int j = 0; j < podata.length; j++) {
							if (spdata[0][j] == null) {
								spdata[0][j] = podata[i];
								spdata[1][j] = "1";
								break;
							} else if (podata[i].equals(spdata[0][j])) {
								if(spdata[1][j] ==null)
								spdata[1][j] = "1";
								else{
								spdata[1][j] = Integer.valueOf(spdata[1][j])
										+ 1 + "";
								}
								break;
							}
						}
					}
				} else if (signdata[i].equals("M")) {
					for (int j = 0; j < podata.length; j++) {
						if (spdata[0][j] == null) {
							spdata[0][j] = podata[i];
							spdata[2][j] = "1";
							break;
						} else if (podata[i].equals(spdata[0][j])) {
							if(spdata[2][j] ==null)
							spdata[2][j] = "1";
							else{
							spdata[2][j] = Integer.valueOf(spdata[2][j])
									+ 1 + "";
							}
							break;
						}
					}
				} else if (signdata[i].equals("E")) {
					if (spdata[0][0] == null) {
						spdata[0][0] = podata[i];
						spdata[3][0] = "1";
					} else {
						for (int j = 0; j < podata.length; j++) {
							if (spdata[0][j] == null) {
								spdata[0][j] = podata[i];
								spdata[3][j] = "1";
								break;
							}
							else if (podata[i].equals(spdata[0][j])) {
								if(spdata[3][j] ==null)
								spdata[3][j] = "1";
								else{
								spdata[3][j] = Integer.valueOf(spdata[3][j])
										+ 1 + "";
								}
								break;
							}
						}
					}
				} else if (signdata[i].equals("S")) {
					if (spdata[0][0] == null) {
						spdata[0][0] = podata[i];
						spdata[4][0] = "1";
					} else {
						for (int j = 0; j < podata.length; j++) {
							if (spdata[0][j] == null) {
								spdata[0][j] = podata[i];
								spdata[4][j] = "1";
								break;
							} else if (podata[i].equals(spdata[0][j])) {
								if(spdata[4][j] ==null)
								spdata[4][j] = "1";
								else{
								spdata[4][j] = Integer.valueOf(spdata[4][j])
										+ 1 + "";
								}
								break;
							}
						}
					}
				}
			}
		}

		for (int m = 0; m < spdata[0].length; m++) {
			if (spdata[0][m] != null) {
				data += spdata[0][m] + "B" + spdata[1][m] + "|";
				data += spdata[0][m] + "M" + spdata[2][m] + "|";
				data += spdata[0][m] + "E" + spdata[3][m] + "|";
				data += spdata[0][m] + "S" + spdata[4][m] + "|";
			} else
				break;
		}
		return data;

	}

	public String readdata(String filePath) {
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

	public String getStopword() {
		return stopword;
	}

	public String getResult() {
		return result;
	}

}
