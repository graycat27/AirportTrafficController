package com.github.graycat27.atc.defines.atc;

/**
 * ATC bot が応答するために解析した、受信メッセージ情報
 * */
public class AtcMessageData {

    private String frequency;
    private String sender;
    private String receiver;
    private boolean needResponse = false;
    private String msgBody;
    private String responseBody;

    public void setFrequency(final String frequency){
        if(this.frequency == null){
            this.frequency = frequency;
        }else{
            throw new IllegalStateException("frequency is already set");
        }
    }
    public void setSender(final String sender){
        if(this.sender == null) {
            this.sender = sender;
        }else{
            throw new IllegalStateException("sender is already set");
        }
    }
    public void setReceiver(final String receiver){
        if(this.receiver == null){
            this.receiver = receiver;
        }else{
            throw new IllegalStateException("receiver is already set");
        }
    }
    public void setMsgBody(final String messageBody){
        if(this.msgBody == null){
            this.msgBody = messageBody;
        }else{
            throw new IllegalStateException("message body is already set");
        }
    }
    public void setResponseBody(final String responseBody){
        if(this.responseBody == null){
            if(responseBody == null){
                return;
            }
            this.responseBody = responseBody;
            this.needResponse = true;
        }else{
            throw new IllegalStateException("response message is already set");
        }
    }

    public String getFrequency(){
        return frequency;
    }
    public String getSender(){
        return sender;
    }
    public String getReceiver(){
        return receiver;
    }
    public boolean isNeedResponse(){
        return needResponse;
    }
    public String getMessageBody(){
        return msgBody;
    }
    public String getResponseBody(){
        return responseBody;
    }

}
