package com.qmaker.quizzer.core.entities;

import com.qmaker.core.engines.Component;
import com.qmaker.core.engines.ComponentManager;
import com.qmaker.core.engines.QSystem;
import com.qmaker.core.entities.QSummary;
import com.qmaker.core.entities.Qcm;
import com.qmaker.core.entities.Questionnaire;
import com.qmaker.core.io.IOInterface;
import com.qmaker.core.io.QPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by istat on 22/09/17.
 */

public class Quiz implements QPackage {
    public static int VERSION = 3;

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
    int forceCompatVersion = 0;

    public final static class DefinitionBuilder {
        Component.Definition definition = new Component.Definition(Quiz.TAG);

        @Deprecated
        public DefinitionBuilder setMaxPropositionPerCountExercise(int value) {
            definition.putSummaryProperty(FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE, value);
            return this;
        }

        @Deprecated
        public DefinitionBuilder setMaxTrueAnswerCountPerExercise(int value) {
            definition.putSummaryProperty(FIELD_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE, value);
            return this;
        }

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

        public DefinitionBuilder setSuccessSoundUri(String uri) {
            definition.putSummaryProperty(FIELD_SUCCESS_SOUND_URI, uri);
            return this;
        }

        public DefinitionBuilder setPartialSuccessSoundUri(String uri) {
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

    public void setForceCompat(int forceCompat) {
        this.forceCompatVersion = forceCompat;
    }

    public long getTimePerExercise() {
        int time = component.getSummaryProperties().getInt(FIELD_TIME_PER_EXERCISE);
        if (time > 0) {
            return time;
        }
        long testDuration = getSummary().getConfig().getDuration();
        long maxQuestionCountPerSession = getSummary().getConfig().getMaxQuestionCountPerSession();
        return testDuration == 0 || maxQuestionCountPerSession == 0 ? 0 : testDuration / maxQuestionCountPerSession;
    }


    public boolean isAllowQuestionCustomTime() {
        return component.getSummaryProperties().getBoolean(FIELD_ALLOW_QUESTION_CUSTOM_TIME);
    }

    @Deprecated
    public int getMaxPropositionPerCountExercise() {
        return component.getSummaryProperties().getInt(FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE);
    }

    @Deprecated
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
        int version = component.getBuilderVersionCode();
        if (version > 0) {
            return version;
        }
        if (getSummary().getConfig().getBuildToolsVersion() < 4) {
            return 2;
        }
        return VERSION;
    }

    public int getPriority() {
        return component.getPriority();
    }

    Questionnaire cachedQuestionnaire;
    boolean useCache = false;

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    public boolean isUseCache() {
        return useCache;
    }

    //TODO reflechir au bien fondé de mettre l'instance de Quetionnaire en cache.
    @Override
    public Questionnaire getQuestionnaire() throws IOException {
        boolean shouldQuizify = shouldQuizify();
        boolean compatV3 = shouldForceCompatV3();
        if (useCache || compatV3 || shouldQuizify) {
            if (cachedQuestionnaire == null) {
                cachedQuestionnaire = shouldQuizify || compatV3 ? toQuiz(component.getQuestionnaire()) : component.getQuestionnaire();
            }
            return cachedQuestionnaire;
        } else {
            return component.getQuestionnaire();
        }
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
    public long length() {
        return component.getQPackage().length();
    }

    @Override
    public long lastModifiedAt() {
        return component.getQPackage().lastModifiedAt();
    }

    @Override
    public boolean moveTo(String destinationUri) {
        return component.getQPackage().moveTo(destinationUri);
    }

    @Override
    @Deprecated
    public boolean isEditable() {
        return component.getQPackage().isEditable();
    }

    @Override
    @Deprecated
    public boolean isOperationSupported(IOInterface.Operation operation) {
        return component.getQPackage().isOperationSupported(operation);
    }

    @Override
    public int getSupportedOperationFlags() {
        return component.getQPackage().getSupportedOperationFlags();
    }

    @Override
    public String getName() {
        return component.getQPackage().getName();
    }

    @Override
    public QSystem getSystem() {
        return component.getQPackage().getSystem();
    }

    private boolean shouldForceCompatV3() {
        return (forceCompatVersion > 0 && forceCompatVersion <= 2) || getBuilderVersion() <= 2;
    }

    private boolean shouldQuizify() {
        com.qmaker.core.utils.Bundle bundle = component.getSummaryProperties();
        return bundle.getInt(Quiz.FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE) > 0 || bundle.getInt(Quiz.FIELD_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE) > 0;
    }

    private Questionnaire toQuiz(Questionnaire qcms) {
        int maxAnswer;
        int maxAnswerTrue;
        com.qmaker.core.utils.Bundle bundle = component.getSummaryProperties();
        maxAnswer = bundle.getInt(Quiz.FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE, 4);
        maxAnswerTrue = bundle.getInt(Quiz.FIELD_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE, 1);
        List<Qcm> toRemove = new ArrayList();
        int propositionWithTrueTruth;
        String qcmType;
        for (Qcm qcm : qcms.getQcms()) {
            if (qcm == null) {
                continue;
            }
            qcmType = qcm.getType();
            if (qcmType == null ||
                    Qcm.TYPE_AUTO.equals(qcmType) ||
                    Qcm.TYPE_SELECT_EACH.equals(qcmType) ||
                    Qcm.TYPE_SELECT_ALL.equals(qcmType)) {
                if ((propositionWithTrueTruth = qcm.getPropositions(true).size()) > 1) {
                    qcm.setPropositionRandomizationType(Qcm.PROPOSITION_RANDOMIZATION_TYPE_ALWAYS);
                }
                if (propositionWithTrueTruth > 1 && propositionWithTrueTruth == qcm.getPropositionCount()) {
                    toRemove.add(qcm);
                } else {
                    if (qcm.getMaxPropositionPerExercise() <= 0) {
                        qcm.setMaxPropositionPerExercise(maxAnswer);
                    }
                    if (qcm.getMaxTruePropositionPerExercise() <= 0) {
                        qcm.setMaxTruePropositionPerExercise(maxAnswerTrue);
                    }
                }
            }
        }
        if (!toRemove.isEmpty()) {
            for (Qcm qcm : toRemove) {
                qcms.getQcms().remove(qcm);
            }
        }
        return qcms;
    }

    public void reset() {
        this.cachedQuestionnaire = null;
    }

}
