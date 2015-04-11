package com.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.spring.strategy.CreditCard;

@Entity
@Table(name = "Account")
public class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique=true, nullable = false)
	private Integer id;
	private String address;
	@OneToOne
    @PrimaryKeyJoinColumn
	private User user;
	@OneToMany(mappedBy = "account", fetch=FetchType.EAGER)
	private List<CreditCard> creditCards;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}
	
	public void setCreditCards(List<CreditCard> creditCards){
		this.creditCards = creditCards;
	}

	public void addToCreditCardList(CreditCard creditCard) {
		this.creditCards.add(creditCard);
	}
	
}
