package com.shopping.redboy.domain;


/**
 * 商品分类条目
 */
public class ProductCategory 
{
   
   public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getIsleafnode() {
		return isleafnode;
	}

	public void setIsleafnode(Boolean isleafnode) {
		this.isleafnode = isleafnode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Product getTheProduct() {
		return theProduct;
	}

	public void setTheProduct(Product theProduct) {
		this.theProduct = theProduct;
	}

/**
    * 分类条目的ID
    */
   private long id;
   
   /**
    * 当isleafnode为true时，表示是树状结构的叶子节点，可点击进入商品列表。
    */
   private Boolean isleafnode;
   
   /**
    * 条目名称
    */
   private String name;
   
   /**
    * 父条目的id，没有则为0
    */
   private long parentId;
   
   /**
    * 商品展示图片的URL
    */
   private String pic;
   
   /**
    * 条目描述信息的标签
    */
   private String tag;
   public Product theProduct;
   
   /**
    * @roseuid 534E295B019A
    */
   public ProductCategory() 
   {
    
   }
}
