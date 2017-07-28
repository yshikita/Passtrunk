/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passtrunk;

/**
 *
 * @author Yendy
 */
public class Settings {
    int comMethod; //1 = email; 2 = sms. 
    int twoStepDelivery; //1 = email; 2 = sms.
    int timeOut; // in seconds.
    boolean visibilityAuth; // 1 = yes or 0 = no.
    boolean twoStepEnabled; // 1 = yes or 2 = no.

    public void setComMethod(int comMethod) {
        this.comMethod = comMethod;
    }

    public void setTwoStepDelivery(int twoStepDelivery) {
        this.twoStepDelivery = twoStepDelivery;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public void setVisibilityAuth(boolean visibilityAuth) {
        this.visibilityAuth = visibilityAuth;
    }

    public void setTwoStepEnabled(boolean twoStepEnabled) {
        this.twoStepEnabled = twoStepEnabled;
    }

    public int getComMethod() {
        return comMethod;
    }

    public int getTwoStepDelivery() {
        return twoStepDelivery;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public boolean isVisibilityAuth() {
        return visibilityAuth;
    }

    public boolean isTwoStepEnabled() {
        return twoStepEnabled;
    }
}
