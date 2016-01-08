package cn.segment;

public class HMM {

	// 所有分词序列概率的最大概率
	private double pro;
	// 所有序列的状态转移概率
	private double[] stp;
	// 观察值概率，一维长度为所有可能的序列数，二维为该序列的每个元素观察值概率
	private double[][] op;
	//最大概率序列的序号
	private int num;

	public HMM(double[] stp, double[][] op) {
		super();
		this.stp = stp;
		this.op = op;
	}

	public void culpro() {
		if (stp != null && op != null) {
			double[] pros = new double[stp.length];
			for (int i = 0; i < stp.length; i++) {
				double m = stp[i];
				for (int j = 0; j < op[i].length; j++) {
					m *= op[i][j];
				}
				pros[i] = m;
				System.out.println(pros[i]);
			}
			double m = pros[0];
			int n =0;
			for(int k = 0;k<pros.length;k++){
				if(pros[k]>m){
					m=pros[k];
					n=k;
				}
			}
			pro = m;
			num = n;
		} else
			System.out.println("参数为空！");
		
	}

	public double getPro() {
		return pro;
	}

	public void setPro(int pro) {
		this.pro = pro;
	}

	public double[] getStp() {
		return stp;
	}

	public void setStp(double[] stp) {
		this.stp = stp;
	}

	public double[][] getOp() {
		return op;
	}

	public void setOp(double[][] op) {
		this.op = op;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
}
