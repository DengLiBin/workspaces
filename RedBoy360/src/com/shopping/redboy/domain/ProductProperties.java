package com.shopping.redboy.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性
 */
public class ProductProperties {

	/**
	 * 商品属性id
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 商品属性的类型
	 */
	private String key;

	/**
	 * 商品属性的值
	 */
	private String value;

	private List<shaixuanValue> valueList = new ArrayList<ProductProperties.shaixuanValue>();

	public List<shaixuanValue> getValueList() {
		return valueList;
	}

	public class shaixuanValue {
		private String id;
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	/**
	 * @roseuid 534E295A0203
	 */
	public ProductProperties() {

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(shaixuanValue value : valueList){
			sb.append(",name = "+value.getName()+", id = " + value.getId());
		}
		return "ProductProperties [id=" + id + ", key=" + key + ", value="
				+ value + "]" + sb.toString();
	}
	
}
