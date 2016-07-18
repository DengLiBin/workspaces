package com.shopping.redboy.domain;

public class OrderProd {
	//商品数量
	private int goodsnum;
	//尺寸
	private String goodsscal;
	//名字
	private String goodsname;
	//颜色
	private String goodsclour;
	//图片
	private String goodsimag;
	//是否赠品
	private String isgift;
	//购买上限
	private int buyLimit;
	//价格
	private double price;	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIsgift() {
		return isgift;
	}

	public void setIsgift(String isgift) {
		this.isgift = isgift;
	}

	public int getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(int buyLimit) {
		this.buyLimit = buyLimit;
	}

	public int getGoodsnum() {
		return goodsnum;
	}

	public void setGoodsnum(int goodsnum) {
		this.goodsnum = goodsnum;
	}


	public String getGoodsscal() {
		return goodsscal;
	}

	public void setGoodsscal(String goodsscal) {
		this.goodsscal = goodsscal;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodsclour() {
		return goodsclour;
	}

	public void setGoodsclour(String goodsclour) {
		this.goodsclour = goodsclour;
	}

	public String getGoodsimag() {
		return goodsimag;
	}

	public void setGoodsimag(String goodsimag) {
		this.goodsimag = goodsimag;
	}

}
