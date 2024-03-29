package com.wkprojects.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DemandItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private DemandItemPK id = new DemandItemPK();

	private Double discount;
	private Integer amount;
	private Double price;

	public DemandItem() {
	}

	public DemandItem(Demand demand, Product product, Integer amount, Double price, Double discount) {
		super();
		this.id.setDemand(demand);
		this.id.setProduct(product);
		this.discount = discount;
		this.amount = amount;
		this.price = price;
	}
	
	@JsonIgnore
	public Demand getDemand() {
		return id.getDemand();
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public DemandItemPK getId() {
		return id;
	}

	public void setId(DemandItemPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemandItem other = (DemandItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
