package cn.segment;

import org.junit.Test;

public class HMMTest {
	
	public HMM hs;

	@Test
	public void testHMM(){
//		double[] stp = {109/100,3490/100,718/100,277/100,1};
//		double[][] op = {{1707/100,151/100,1655/100, 466/100},{1707/100, 1655/100, 926/100, 686/100},
//				{1707/100, 1655/100, 565/100, 36/100},{42/100, 565/100, 565/100, 36/100},{0,0,0,1}};
		double[] stp ={1.0,2.0,3.0};
		double[][] op ={{1.0,2.0},{1.0,2.0},{1.0,2.0}};
		hs = new HMM(stp, op);
		hs.culpro();
		System.out.println(stp[hs.getNum()]+"  "+hs.getPro());
		System.out.println(3490/100*1707/100*1655/100*926/100*686/100);
	}
	
	@Test
	public void testIntDouble(){
		int[] stp = {1,2,3};
		double[] stpdo = new double[stp.length];
		for(int i=0;i<stp.length;i++){
			stpdo[i] = stp[i]/100.0;
			System.out.println(stpdo[i]);
		}
	}
}
