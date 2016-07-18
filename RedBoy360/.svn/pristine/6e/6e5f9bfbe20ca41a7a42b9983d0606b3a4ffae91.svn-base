package com.shopping.redboy.domain;

import java.util.Arrays;

import com.shopping.redboy.annotation.JSON;

/**
 * 商品详情
 */
public class Product {

	/**
	 * 商品ID
	 */
	@JSON(name = "id", type = Integer.class)
	private int id;
	/**
	 * 商品名称
	 */
	@JSON(name = "name", type = String.class)
	private String name;

	/**
	 * 商品图片URL
	 */
	@JSON(name = "pic", type = String.class)
	private String pic;

	/**
	 * 商品市场价格
	 */
	@JSON(name = "marketprice", type = Double.class)
	private double marketprice;

	/**
	 * 会员价
	 */
	@JSON(name = "price", type = Double.class)
	private double price;

	/**
	 * 商品评论数
	 */
	private int comment_count;

	/**
	 * 商品详情需要的图片URL集合
	 */
	private String[] picList;

	/**
	 * 商品详情需要限时抢购价
	 */
	@JSON(name = "limitprice", type = Integer.class)
	private int limitPrice;

	/**
	 * 商品详情需要 限时抢购剩余时间
	 */
	@JSON(name = "lefttime", type = Long.class)
	private long leftTime;

	/**
	 * 商品详情需要 评分
	 */
	@JSON(name = "score", type = Float.class)
	private float score;

	/**
	 * 商品详情需要 是否可售
	 */
	private String available;

	/**
	 * 商品详情大图浏览 URL列表
	 */
	private String[] bigPic;

	/**
	 * 商品详情需要 单品购买上限
	 */
	private int buyLimit;

	/**
	 * 促销信息数组
	 */
	private String[] productProm;

	/**
	 * 商品数量，0为缺货或下架
	 */
	private int number;

	/**
	 * 商品销量
	 */
	@JSON(name = "saleNumber", type = Integer.class)
	private int saleNmuber;

	/**
	 * 商品的上架时间
	 */
	@JSON(name = "timeGrounding", type = Long.class)
	private long timeGrounding;

	/**
	 * 是否赠品
	 */
	private boolean idGift;

	/**
	 * 多张小图的url
	 */
	private String[] pics;

	/**
	 * 商品详情需要 配货说明
	 */
	private String inventoryArea;
	/**
	 * 商品属性
	 */
	public ProductProperties theProductProperties;
	/**
	 * 商品描述
	 */
	public ProductDescription theProductDescription;
	/**
	 * 商品评论
	 */
	public ProductComment theProductComment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTimeGrounding() {
		return timeGrounding;
	}

	public void setTimeGrounding(long timeGrounding) {
		this.timeGrounding = timeGrounding;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public double getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(double marketprice) {
		this.marketprice = marketprice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getComment_count() {
		return comment_count;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

	public String[] getPicList() {
		return picList;
	}

	public void setPicList(String[] picList) {
		this.picList = picList;
	}

	public int getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(int limitPrice) {
		this.limitPrice = limitPrice;
	}

	public long getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(long leftTime) {
		this.leftTime = leftTime;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String[] getBigPic() {
		return bigPic;
	}

	public void setBigPic(String[] bigPic) {
		this.bigPic = bigPic;
	}

	public int getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(int buyLimit) {
		this.buyLimit = buyLimit;
	}

	public String[] getProductProm() {
		return productProm;
	}

	public void setProductProm(String[] productProm) {
		this.productProm = productProm;
	}

	public String getInventoryArea() {
		return inventoryArea;
	}

	public void setInventoryArea(String inventoryArea) {
		this.inventoryArea = inventoryArea;
	}

	public ProductProperties getTheProductProperties() {
		return theProductProperties;
	}

	public void setTheProductProperties(ProductProperties theProductProperties) {
		this.theProductProperties = theProductProperties;
	}

	public ProductDescription getTheProductDescription() {
		return theProductDescription;
	}

	public void setTheProductDescription(
			ProductDescription theProductDescription) {
		this.theProductDescription = theProductDescription;
	}

	public ProductComment getTheProductComment() {
		return theProductComment;
	}

	public void setTheProductComment(ProductComment theProductComment) {
		this.theProductComment = theProductComment;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSaleNmuber() {
		return saleNmuber;
	}

	public void setSaleNmuber(int saleNmuber) {
		this.saleNmuber = saleNmuber;
	}

	public boolean isIdGift() {
		return idGift;
	}

	public void setIdGift(boolean idGift) {
		this.idGift = idGift;
	}

	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}

	/**
	 * @roseuid 534E295B032F
	 */
	public Product() {

	}

	@Override
	public String toString() {
		return "Product [ID=" + id + ", name=" + name + ", pic=" + pic
				+ ", marketprice=" + marketprice + ", price=" + price
				+ ", commentCount=" + comment_count + ", picList="
				+ Arrays.toString(picList) + ", limitPrice=" + limitPrice
				+ ", leftTime=" + leftTime + ", score=" + score
				+ ", available=" + available + ", bigPic="
				+ Arrays.toString(bigPic) + ", buyLimit=" + buyLimit
				+ ", productProm=" + Arrays.toString(productProm) + ", number="
				+ number + ", idGift=" + idGift + ", inventoryArea="
				+ inventoryArea + ", theProductProperties="
				+ theProductProperties + ", theProductDescription="
				+ theProductDescription + ", theProductComment="
				+ theProductComment + "]";
	}

}
