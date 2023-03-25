package com.aua.softwaretesting;

public class BankAccount {
    double bal;
    double prevTrans;
    String customerName;
    String customerId;

    BankAccount(String customerName,String customerId){
        this.customerName=customerName;
        this.customerId=customerId;
    }


    double deposit(double amount){
        if(amount!=0){
            bal+=amount;
            prevTrans=amount;
        }

        return bal;
    }

    double withdraw(double amt) throws Exception{
        if(amt!=0 && bal>=amt){
            bal-=amt;
            prevTrans=-amt;
            return bal;
        }
        else if(bal<amt){
            throw new Exception("Bank balance insufficient");
        }
        return bal;
    }

    double getPreviousTrans() throws Exception{
        if(prevTrans>0){
            return prevTrans;
        }
        else if(prevTrans<0){
            return Math.abs(prevTrans);
        }
        else{
            throw new Exception("No transaction occured");
        }
    }

    String menu() throws Exception{
        if(customerId.equals("12341234")){
            throw new Exception("Your user is Block.");
        }else {
            return "Welcome to our Bank application";
        }
    }
}
