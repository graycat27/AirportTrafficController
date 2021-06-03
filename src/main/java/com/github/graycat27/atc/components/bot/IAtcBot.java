package com.github.graycat27.atc.components.bot;

public interface IAtcBot {

    String analyzeMessage(String receivedMessage);

    String nonIdentifiedSender();
}
