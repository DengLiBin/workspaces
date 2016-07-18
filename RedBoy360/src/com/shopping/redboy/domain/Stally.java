package com.shopping.redboy.domain;

/**
 * 总计
 * @author Admin
 *
 */
public class Stally {
	/**
	 * 商品数量总计
	 */
	private int totalCount;
	/**
	 * 商品金额总计
	 */
	private long totalPrice;
	/**
	 * 商品积分总计
	 */
	private long totalPoint;
	/**
	 * 运费
	 */
	private long freight;
	/**
	 * 促销减钱
	 */
	private long promCut;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public long getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(long totalPoint) {
		this.totalPoint = totalPoint;
	}
	public long getFreight() {
		return freight;
	}
	public void setFreight(long freight) {
		this.freight = freight;
	}
	public long getPromCut() {
		return promCut;
	}
	public void setPromCut(long promCut) {
		this.promCut = promCut;
	}
	
}
