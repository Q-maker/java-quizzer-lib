package com.qmaker.quizzer.core.entities;

import com.qmaker.core.engines.Component;
import com.qmaker.core.engines.ComponentManager;
import com.qmaker.core.engines.QSystem;
import com.qmaker.core.entities.QSummary;
import com.qmaker.core.entities.Qcm;
import com.qmaker.core.entities.Questionnaire;
import com.qmaker.core.io.QPackage;

import java.io.IOException;

/**
 * Created by istat on 22/09/17.
 */

public class Quiz implements QPackage {

    public final static String FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE = "maxPropositionCountPerExercise",
            FIELD_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE = "maxTrueAnswerCountPerExercise";
    public final static String
            FIELD_TIME_PER_EXERCISE = "timePerExercise",
            FIELD_ALLOW_QUESTION_CUSTOM_TIME = "allowQuestionCustomTime",
            FIELD_BEGINNING_SOUND_URI = "beginningSoundUri",
            FIELD_ENDING_SOUND_URI = "endingSoundUri",
            FIELD_SUCCESS_SOUND_URI = "successSoundUri",
            FIELD_PARTIAL_SUCCESS_SOUND_URI = "partialSuccessSoundUri",
            FIELD_FAILURE_SOUND_URI = "failureSoundUri",
            FIELD_ERROR_SOUND_URI = "errorSoundUri";
    public final static String TAG = "quiz";
    public final int DEFAULT_MAX_PROPOSITION_COUNT_PER_EXERCISE = 4,
            DEFAULT_MIN_PROPOSITION_COUNT_PER_MULTIPLE_CHOICE_EXERCISE = 2,
            DEFAULT_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE = 1;
    Component component;

    public final static class DefinitionBuilder {
        Component.Definition definition = new Component.Definition(Quiz.TAG);

        public DefinitionBuilder setTimePerExerise(long time) {
            definition.putSummaryProperty(FIELD_TIME_PER_EXERCISE, time);
            return this;
        }

        public DefinitionBuilder setAllowQuestionCustomTime(boolean value) {
            definition.putSummaryProperty(FIELD_ALLOW_QUESTION_CUSTOM_TIME, value);
            return this;
        }

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
            definition.setBuilderVersionCode(2);
            return definition;
        }

        public Quiz create(QPackage qPackage) throws IOException {
            Component component = ComponentManager.getInstance().apply(definition, qPackage);
            return new Quiz(component);
        }

    }

    Quiz(Component component) {
        this.component = component;
    }

    public static Quiz from(QPackage qp) {
        ComponentManager.ComponentInfos infos = ComponentManager.getInstance().fetch(qp);
        Component component = infos.optComponent(Quiz.TAG);
        if (component == null) {
            return null;
        }
        Quiz quiz = new Quiz(component);
        return quiz;
    }

    public long getTimePerExercise() {
        int time = component.getSummaryProperties().getInt(FIELD_TIME_PER_EXERCISE);
        if (time > 0) {
            return time;
        }
        return getSummary().getConfig().getDuration() / getSummary().getConfig().getMaxQuestionCountPerSession();
    }


    public boolean isAllowQuestionCustomTime() {
        return component.getSummaryProperties().getBoolean(FIELD_ALLOW_QUESTION_CUSTOM_TIME);
    }

    public int getMaxPropositionPerCountExercise() {
        return component.getSummaryProperties().getInt(FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE);
    }

    public int getMaxTrueAnswerCountPerExercise() {
        return component.getSummaryProperties().getInt(FIELD_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE);
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

    public String getSuccessSoundUri() {
        return component.getSummaryProperties().getString(FIELD_SUCCESS_SOUND_URI);
    }

    public String getFailureSoundUri() {
        return component.getSummaryProperties().getString(FIELD_FAILURE_SOUND_URI);
    }

    public String getErrorSoundUri() {
        return component.getSummaryProperties().getString(FIELD_ERROR_SOUND_URI);
    }

    public String getPartialSuccessSoundUri() {
        return component.getSummaryProperties().getString(FIELD_PARTIAL_SUCCESS_SOUND_URI);
    }

    @Override
    public QSummary getSummary() {
        return component.getSummary();
    }

    public boolean isEnable() {
        return component.isEnable();
    }

    public int getBuilderVersion() {
        return component.getBuilderVersionCode();
    }

    public int getPriority() {
        return component.getPriority();
    }

    Questionnaire cachedQuestionnaire;

    //TODO reflechir au bien fondé de mettre l'instance de Quetionnaire en cache.
    @Override
    public Questionnaire getQuestionnaire() throws IOException {
        if (cachedQuestionnaire == null) {
            return fetchQuestionnaire();
        }
        return cachedQuestionnaire;
    }

    public Questionnaire fetchQuestionnaire() throws IOException {
        cachedQuestionnaire = toQuiz(component.getQuestionnaire());
        return cachedQuestionnaire;
    }

    @Override
    public Resource getResource() {
        return component.getQPackage().getResource();
    }

    @Override
    public String getUriString() {
        return component.getQPackage().getUriString();
    }

    @Override
    public String getType() {
        return TAG;//component.getQPackage().getType();
    }

    @Override
    public boolean exists() {
        return component.getQPackage().exists();
    }

    @Override
    public boolean delete() {
        return component.getQPackage().delete();
    }

    @Override
    public boolean rename(String newFileUri) {
        return component.getQPackage().rename(newFileUri);
    }

    @Override
    public String getName() {
        return component.getQPackage().getName();
    }

    @Override
    public QSystem getSystem() {
        return component.getQPackage().getSystem();
    }

    private Questionnaire toQuiz(Questionnaire qcms) {
        int maxAnswer;
        int maxAnswerTrue;
        com.qmaker.core.utils.Bundle bundle = component.getSummaryProperties();
        maxAnswer = bundle.getInt(Quiz.FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE, 4);
        maxAnswerTrue = bundle.getInt(Quiz.FIELD_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE, 1);

        for (Qcm qcm : qcms.getQcms()) {
            if (qcm.getMaxPropositionPerExercise() <= 0) {
                qcm.setMaxPropositionPerExercise(maxAnswer);
            }
            if (qcm.getMaxTruePropositionPerExercise() <= 0) {
                qcm.setMaxTruePropositionPerExercise(maxAnswerTrue);
            }
        }
        return qcms;
    }

    public void reset() {
        this.cachedQuestionnaire = null;
    }
//
//    public void setPartialSuccessSound(String partialSuccessSound) {
//        this.partialSuccessSound = partialSuccessSound;
//    }

}
