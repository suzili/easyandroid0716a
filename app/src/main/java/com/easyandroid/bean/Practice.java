package com.easyandroid.bean;

/**
 * Created by asus on 2017/7/10.
 */

public class Practice {
    private String practice_online_item;
    private String number;

    public String getPractice_online_item() {
        return practice_online_item;
    }

    public String getNumber() {
        return number;
    }

    public Practice(String practice_online_item, String number) {
        this.practice_online_item = practice_online_item;
        this.number = number;
    }
//    public Practice(String practice_online_item ,String number) {
//        this.practice_online_item = practice_online_item;
//        this.number = number;
//    }
//
//    public String getPractice_online_item() {
//        return practice_online_item;
//    }
//    public String getNumber(){
//        return getNumber();
//    }
}
