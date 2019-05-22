package com.example.wu.com.example.department.bean;

import com.example.wu.R;

import java.util.ArrayList;

/**
 * 实体类
 */

public class GoodsInfo {
	public int pic_id;
	public String title;
	public String desc;
	public boolean bPressed;
	public int id;
	private static int seq = 0;
	
	public GoodsInfo(int pic_id, String title, String desc) {
		this.pic_id = pic_id;
		this.title = title;
		this.desc = desc;
		this.bPressed = false;
		this.id = this.seq;
		this.seq++;
	}

	private static int[] listImageArray = {R.drawable.public_01, R.drawable.public_02
			, R.drawable.public_03, R.drawable.public_04, R.drawable.public_05};
	private static String[] listTitleArray = {
			"一世兵王", "法医狂妃", "崇祯十三年", "争霸天下", "无上神帝"};
	private static String[] listDescArray = {
			"雇佣兵王回归都市，只为保护战友的女神妹妹",
			"明明娘亲，却要叫爹",
			"看政坛老干部与商界女精英携手共闯乱世",
			"解决最好问题最好的两个方法，钱和刀",
			"蒙内铁路建成通车，中国标准再下一城",};
	public static ArrayList<GoodsInfo> getDefaultList() {
		ArrayList<GoodsInfo> listArray = new ArrayList<GoodsInfo>();
		for (int i=0; i<listImageArray.length; i++) {
			listArray.add(new GoodsInfo(listImageArray[i], listTitleArray[i], listDescArray[i]));
		}
		return listArray;
	}
}
