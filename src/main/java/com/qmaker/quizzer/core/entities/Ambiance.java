package com.qmaker.quizzer.core.entities;

import com.qmaker.core.engines.Component;
import com.qmaker.core.engines.ComponentManager;
import com.qmaker.core.io.QPackage;

import java.io.IOException;

public class Ambiance {
    static String TAG = "ambiance";
    public static int VERSION = 1;
    public final static String
            FIELD_BEGINNING_SOUND_URI = "beginningSoundUri",
            FIELD_ENDING_SOUND_URI = "endingSoundUri",
            FIELD_SUCCESS_SOUND_URI = "successSoundUri",
            FIELD_PARTIAL_SUCCESS_SOUND_URI = "partialSuccessSoundUri",
            FIELD_FAILURE_SOUND_URI = "failureSoundUri",
            FIELD_ERROR_SOUND_URI = "errorSoundUri";

    Component component;

    Ambiance(Component component) {
        this.component = component;
    }

    public static Ambiance from(QPackage qp) {
        ComponentManager.ComponentInfos infos = ComponentManager.getInstance().fetch(qp);
        Component component = infos.optComponent(TAG);
        if (component == null) {
            return null;
        }
        return new Ambiance(component);
    }

    public String getBackgroundSoundUri() {
        return component.getSummary().getSoundUri();
    }

    public String getBeginningSoundUri() {
        return component.getSummaryProperties().getString(FIELD_BEGINNING_SOUND_URI);
    }

    public String getEndingSoundUri() {
        return component.getSummaryProperties().getString(FIELD_ENDING_SOUND_URI);
    }

    public String getSuccessSoundEffectUri() {
        return component.getSummaryProperties().getString(FIELD_SUCCESS_SOUND_URI);
    }

    public String getFailureSoundEffectUri() {
        return component.getSummaryProperties().getString(FIELD_FAILURE_SOUND_URI);
    }

    public String getErrorSoundEffetUri() {
        return component.getSummaryProperties().getString(FIELD_ERROR_SOUND_URI);
    }

    public String getPartialSuccessSoundEffetUri() {
        return component.getSummaryProperties().getString(FIELD_PARTIAL_SUCCESS_SOUND_URI);
    }

    public final static class DefinitionBuilder {
        Component.Definition definition = new Component.Definition(Quiz.TAG);

        public DefinitionBuilder setBeginSoundUri(String uri) {
            definition.putSummaryProperty(FIELD_BEGINNING_SOUND_URI, uri);
            return this;
        }

        public DefinitionBuilder setEndingSoundUri(String uri) {
            definition.putSummaryProperty(FIELD_ENDING_SOUND_URI, uri);
            return this;
        }

        public DefinitionBuilder setSuccedSoundUri(String uri) {
            definition.putSummaryProperty(FIELD_SUCCESS_SOUND_URI, uri);
            return this;
        }

        public DefinitionBuilder setPartialSuccedSoundUri(String uri) {
            definition.putSummaryProperty(FIELD_SUCCESS_SOUND_URI, uri);
            return this;
        }

        public DefinitionBuilder setFailureSoundUri(String uri) {
            definition.putSummaryProperty(FIELD_FAILURE_SOUND_URI, uri);
            return this;
        }

        public DefinitionBuilder setErrorSoundUri(String uri) {
            definition.putSummaryProperty(FIELD_ERROR_SOUND_URI, uri);
            return this;
        }

        public DefinitionBuilder setPriority(int priority) {
            definition.setPriority(priority);
            return this;
        }

        public DefinitionBuilder setEnable(boolean state) {
            definition.setEnable(state);
            return this;
        }

        public Component.Definition create() {
            definition.setName("quizzer");
            definition.setBuilderVersionCode(VERSION);
            return definition;
        }

        public Ambiance create(QPackage qPackage) throws IOException {
            Component component = ComponentManager.getInstance().apply(definition, qPackage);
            return new Ambiance(component);
        }

    }
}
