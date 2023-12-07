package com.example.librovermosite.utilites;

public class SendObject {
    private Object sendObject;
    private String sendClass;
    private String tableName;
    private Object conditionMap;

    public SendObject(){}

    public SendObject(Object sendObject, String tableName, Object conditionMap) {
        this.sendObject = sendObject;
        this.sendClass = sendObject.getClass().getSimpleName();
        this.tableName = tableName;
        this.conditionMap = conditionMap;
    }

    public Object getSendObject() {
        return sendObject;
    }

    public void setSendObject(Object sendObject) {
        this.sendObject = sendObject;
    }

    public Object getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(Object conditionMap) {
        this.conditionMap = conditionMap;
    }

    public String getSendClass() {
        return sendClass;
    }

    public void setSendClass(String sendClass) {
        this.sendClass = sendClass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
