package com.example.newsclient_project1_myimageview_intent.enumeration;

public enum SourceType {/*枚举  数值类型 里面每一个都是常量*/
    SOURCE_FROM_SERVER("SOURCE_FROM_SERVER"),
    SOURCE_FROM_DB("SOURCE_FROM_DB"),
    SOURCE_FROM_ASSETSS("SOURCE_FROM_ASSETSS"),
    SOURCE_INTENT("SOURCE_INTENT");

    private  String type;


    SourceType(String type){/*根据字符串找到类型*/
        this.type=type;
    }

    public  String getType(){

        return type;
    }
}
