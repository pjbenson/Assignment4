package com.spring.strategy;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.swing.JOptionPane;

import com.spring.model.Account;
@Entity
@Table(name = "creditcard")
public  class CreditCard implements PaymentStrategy{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique=true, nullable = false)
	private Integer id;
	private String cardNumber;
	private String cardType;
	private int expiryMonth, expiryYear;
	@ManyToOne(fetch = FetchType.EAGER)
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public CreditCard(){};

	public CreditCard(String number, String month, String year) {
		cardNumber = number;
		try {
			expiryMonth = Integer.parseInt(month);
		}
		catch (NumberFormatException e1) {
			expiryMonth = 1;
		}
		try {
			expiryYear = Integer.parseInt(year);
		}
		catch (NumberFormatException e2) {
			expiryYear = 2014;
		}
	}
	
	@Override
	public void pay(Double amount) {
		System.out.println("YOU HAVE NOW PAID €"+amount +" USING YOUR CREDIT CARD!!");
	}

	public boolean validate() {
		if (!checkExpiryDateValid()) {
			JOptionPane.showMessageDialog(null, "Invalid expiry date", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!checkAllCharsDigits()) {
			JOptionPane.showMessageDialog(null, "Invalid character in credit card number", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!checkCheckSumDigit()) {
			JOptionPane.showMessageDialog(null, "Invalid credit card number", "Error", JOptionPane.ERROR_MESSAGE);
			return false;			
		}
		return true;
	}

	private boolean checkExpiryDateValid() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		int currentYear = cal.get(Calendar.YEAR);

		if (currentYear > expiryYear) {
			return false;
		}
		else if (currentYear == expiryYear && currentMonth > expiryMonth) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean checkAllCharsDigits() {
		String validDigits = "0123456789";	
		boolean result = true;

		for (int i = 0; i < cardNumber.length(); i++) {
			if (validDigits.indexOf(cardNumber.substring(i, i + 1)) < 0) {
				result = false;
				break;
			}
		}

		return result;
	}
	
	private boolean checkCheckSumDigit() {
		boolean result = true;
		int sum = 0;
		int multiplier = 1;
		int stringLength = cardNumber.length();

		for (int i = 0; i < stringLength; i++) {
			String currentDigit = cardNumber.substring(stringLength - i - 1, stringLength - i);
			int currentProduct = new Integer(currentDigit).intValue() * multiplier;
			if (currentProduct >= 10)
				sum += (currentProduct % 10) + 1;
			else
				sum += currentProduct;
			if (multiplier == 1)
				multiplier++;
			else
				multiplier--;
		}
		if ((sum % 10) != 0)
			result = false;

		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}
}
