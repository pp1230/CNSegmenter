package cn.segment;

import org.junit.Test;

public class SegmenterTest {
	
	public Segmenter segmenter;

	@Test
	public void testStringLen(){
		String s = " 1";
		System.out.println(s.length());
	}
	
	@Test
	public void testSegment(){
		String sentence = "中国永远是维护世界和平与稳定的重要力量";
		String stpfilePath = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/hmmpara/stpresult.txt";
		String opfilePath = "/home/pi/Workspaces/MyEclipse Spring 2014/CNHMMSegment/hmmpara/op.txt";
		segmenter = new Segmenter(sentence, stpfilePath, opfilePath);
		segmenter.segment();
	}
	
	@Test
	public void testSentence(){
		String sentence = "今天的天气真好";
		String[] word = sentence.split("");
		for(int i=0;i<word.length;i++)
			System.out.println(i+word[i]);
	}
}
