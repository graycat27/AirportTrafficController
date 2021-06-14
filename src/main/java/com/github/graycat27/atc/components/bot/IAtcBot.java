package com.github.graycat27.atc.components.bot;

public interface IAtcBot {

    String analyzeMessage(String receivedMessage);

    default String nonIdentifiedSender() {
        return "say your call-sign.";
    }
}
