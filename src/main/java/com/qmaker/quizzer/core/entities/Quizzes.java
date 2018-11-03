package com.qmaker.quizzer.core.entities;

import com.qmaker.core.io.QPackage;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import istat.android.base.tools.ToolKits;

public class Quizzes {
    final static String RES_SECTION = "x-quizzer";
    final static String RES_CONFIG_NAME = "config";
    Config config;
    QPackage qPackage;

    private Quizzes() {

    }

    public final static Quizzes from(QPackage qPackage) throws InvalidQuizzesException {
        Quizzes survey = new Quizzes();
        survey.qPackage = qPackage;
        try {
            Gson gson = new Gson();
            InputStream configStream = survey.qPackage.getResource().getEntry(RES_SECTION, RES_CONFIG_NAME).openInputStream();
            String content = ToolKits.Stream.streamToString(configStream);
            survey.config = gson.fromJson(content, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidQuizzesException(e);
        }
        return survey;
    }

    public Config getConfig() {
        return config;
    }

    public static class Config {

    }

    public static class InvalidQuizzesException extends Exception {
        public InvalidQuizzesException(Throwable e) {
            super(e);
        }

        public InvalidQuizzesException(String message) {
            super(message);
        }
    }

}
