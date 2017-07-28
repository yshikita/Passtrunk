/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passtrunk;

import java.util.Date;

/**
 *
 * @author Yendy
 */
public class CreditCardCredential extends Credential {
    private String bankName;
    private String cardNumber;
    private String expDate;
    private String ccvNumber;
    private String nameOnCard;
    private String type;

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setCcvNumber(String ccvNumber) {
        this.ccvNumber = ccvNumber;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getBankName() {
        return bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getCcvNumber() {
        return ccvNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getType() {
        return type;
    }
}
