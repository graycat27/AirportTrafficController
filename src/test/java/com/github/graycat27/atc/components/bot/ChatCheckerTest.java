package com.github.graycat27.atc.components.bot;

import com.github.graycat27.atc.utils.AtcDictionary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ChatCheckerTest {

    private String wordConverter(String original){
        try {
            Properties dic = new Properties();
            try {
                String dicFile = getClass().getClassLoader().getResource("test_atc_dic.prop").getPath();
                dicFile = dicFile.substring(1);
                dic.load(Files.newBufferedReader(Path.of(dicFile), StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Field dicField = AtcDictionary.class.getDeclaredField("dic");
            dicField.setAccessible(true);
            dicField.set(null, dic);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        ChatChecker targetClass = new ChatChecker();
        String result;
        try {
            Method method = ChatChecker.class.getDeclaredMethod("wordConverter", String.class);
            method.setAccessible(true);

            result = (String) method.invoke(targetClass, original);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Test
    void testSmall(){
        assertEquals("Airport", wordConverter("ap"));
    }

}