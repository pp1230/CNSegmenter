package cn.libanalyze;

import org.junit.Test;

public class TestStp {
	
	public StpTraining stp;
	public WriteFile wf;
	public OpTraining op;
	
	@Test
	public void testStp(){
    	String cnlibfile = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/cnlib/pku_training.utf8";
        String stopwordfile = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/cnlib/stopwords.txt";
        String writePath = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/hmmpara/stsign.txt";
        stp = new StpTraining();
        wf = new WriteFile();
        stp.makesign(cnlibfile, stopwordfile);
        System.out.println(stp.getStopword());
        System.out.println(stp.getSign());
        wf.write(stp.getSign(), writePath);
	}
	
	@Test
	public void testpo(){
    	String cnlibfile = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/cnlib/pku_training.utf8";
        String stopwordfile = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/cnlib/stopwords.txt";
        String writePath = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/hmmpara/po.txt";
        op = new OpTraining();
        wf = new WriteFile();
        op.getobs(cnlibfile, stopwordfile);
        System.out.println(op.getStopword());
        System.out.println(op.getResult());
        wf.write(op.getResult(), writePath);
	}
	
	@Test
	public void teststpresult(){
        String stpresultfile = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/hmmpara/stpresult.txt";
        String stsignfile = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/hmmpara/stsign.txt";
        stp = new StpTraining();
        wf = new WriteFile();
        String data = stp.readdata(stsignfile);
        String data1 = stp.culculatestp(data);
        System.out.println(stp.getResultInt().length);
//        System.out.println(data1);
        wf.write(data1, stpresultfile);
	}
	
	@Test
	public void testoptraining(){
		String stsignfile = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/hmmpara/stsign.txt";
		String poPath = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/hmmpara/po.txt";
		String writePath = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/hmmpara/op.txt";
		op = new OpTraining();
		wf = new WriteFile();
		String data = op.optraining(stsignfile, poPath);
		wf.write(data, writePath);
		
	}
	
	@Test
	public void testsplit(){
		String[] data = "BEBEBESSBEBEBMMMEBEBE|SBESS|BMMEBME|BEBESBE|BMMMEBMEBMME|BMEBME|BMMEBME|BEBESBEBEBMMMEBEBE|BEBEBESSBE|||BMEBESBES|BES|BES|BES|BES|SBMMMEBEBE|".split("\\|");
		for(int i=0;i<data.length;i++){
			System.out.println(data[i]);
	}
		
	}
	@Test
	public void testInt(){
		String a = Integer.valueOf("1")+1+"";
		System.out.println(a);
	}
}
