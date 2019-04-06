package com.qmaker.quizzer.core.entities;

import com.qmaker.core.engines.Component;
import com.qmaker.core.engines.ComponentManager;
import com.qmaker.core.engines.QSystem;
import com.qmaker.core.entities.QSummary;
import com.qmaker.core.entities.Questionnaire;
import com.qmaker.core.io.QPackage;

import java.io.IOException;

/**
 * Created by istat on 22/09/17.
 */

public class Quiz implements QPackage {
    public final static String
            FIELD_TIME_PER_EXERCISE = "timePerExercise",
            FIELD_ALLOW_QUESTION_CUSTOM_TIME = "allowQuestionCustomTime",
            FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE = "maxPropositionCountPerExercise",
            FIELD_MIN_PROPOSITION_COUNT_PER_MULTIPLE_CHOICE_EXERCISE = "minPropositionCountPerMultipleChoiceExercise",
            FIELD_MIN_TRUE_ANSWER_COUNT_PER_EXERCISE = "minTrueAnswerCountPerExercise",
            FIELD_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE = "maxTrueAnswerCountPerExercise",
            FIELD_BEGINNING_SOUND = "beginningSound",
            FIELD_ENDING_SOUND = "endingSound",
            FIELD_SUCCESS_SOUND = "successSound",
            FIELD_PARTIAL_SUCCESS_SOUND = "partialSuccessSound",
            FIELD_ERROR_SOUND = "errorSound";
    public final static String TAG = "quiz";
    public final int DEFAULT_MAX_PROPOSITION_COUNT_PER_EXERCISE = 4,
            DEFAULT_MIN_PROPOSITION_COUNT_PER_MULTIPLE_CHOICE_EXERCISE = 2,
            DEFAULT_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE = 1;
    Component component;

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

//    public void setAllowQuestionCustomTime(boolean allowQuestionCustomTime) {
//        this.allowQuestionCustomTime = allowQuestionCustomTime;
//    }

    public int getMaxPropositionPerCountExercise() {
        return component.getSummaryProperties().getInt(FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE);
    }

//    public void setMaxPropositionPerExercise(int maxPropositionPerExercise) {
//        this.maxPropositionPerExercise = maxPropositionPerExercise;
//    }

    public int getMinPropositionCountPerMultipleChoiceExercise() {
        return component.getSummaryProperties().getInt(FIELD_MIN_PROPOSITION_COUNT_PER_MULTIPLE_CHOICE_EXERCISE);
    }

//    public void setMinPropositionPerMultipleChoiceExercise(int minPropositionPerMultipleChoiceExercise) {
//        this.minPropositionPerMultipleChoiceExercise = minPropositionPerMultipleChoiceExercise;
//    }

    public int getMaxTrueAnswerCountPerExercise() {
        return component.getSummaryProperties().getInt(FIELD_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE);
    }

//    public void setMaxTrueAnswerPerExercise(int maxTrueAnswerPerExercise) {
//        this.maxTrueAnswerPerExercise = maxTrueAnswerPerExercise;
//    }

    public String getBackgroundSound() {
        return component.getSummary().getSoundUri();
    }

//    public void setBackgroundSound(String backgroundSound) {
//        this.backgroundSound = backgroundSound;
//    }

    public String getBeginningSound() {
        return component.getSummaryProperties().getString(FIELD_BEGINNING_SOUND);
    }


    //    public void setTimePerExercise(long timePerExercise) {
//        this.timePerExercise = timePerExercise;
//    }
//
//    public void setBeginningSound(String beginningSound) {
//        this.beginningSound = beginningSound;
//    }
//
    public String getEndingSound() {
        return component.getSummaryProperties().getString(FIELD_ENDING_SOUND);
    }

    //    public void setEndingSound(String endingSound) {
//        this.endingSound = endingSound;
//    }
//
    public String getSuccessSound() {
        return component.getSummaryProperties().getString(FIELD_SUCCESS_SOUND);
    }

    //
//    public void setSuccessSound(String successSound) {
//        this.successSound = successSound;
//    }
//
    public String getErrorSound() {
        return component.getSummaryProperties().getString(FIELD_ERROR_SOUND);
    }

    //
//    public void setErrorSound(String errorSound) {
//        this.errorSound = errorSound;
//    }
//
    public String getPartialSuccessSound() {
        return component.getSummaryProperties().getString(FIELD_PARTIAL_SUCCESS_SOUND);
    }

    @Override
    public QSummary getSummary() {
        return component.getSummary();
    }

    Questionnaire cachedQuestionnaire;

    @Override
    public Questionnaire getQuestionnaire() throws IOException {
        if (cachedQuestionnaire == null) {
            cachedQuestionnaire = toQuiz(component.getQuestionnaire());
        }
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
    public boolean exist() {
        return component.getQPackage().exist();
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
        int minAnswer;
        int maxAnswer;
        int minAnswerTrue;
        int maxAnswerTrue;
        com.qmaker.core.utils.Bundle bundle = component.getSummaryProperties();
        minAnswer = bundle.getInt(Quiz.FIELD_MIN_PROPOSITION_COUNT_PER_MULTIPLE_CHOICE_EXERCISE, 1);
        maxAnswer = bundle.getInt(Quiz.FIELD_MAX_PROPOSITION_COUNT_PER_EXERCISE, 4);
        minAnswerTrue = bundle.getInt(Quiz.FIELD_MIN_TRUE_ANSWER_COUNT_PER_EXERCISE, 1);
        maxAnswerTrue = bundle.getInt(Quiz.FIELD_MAX_TRUE_ANSWER_COUNT_PER_EXERCISE, 1);

        qcms = com.qmaker.core.utils.QuestionnaireUtils.customiseQuestionnaire(qcms, qcms.getConfig().isRandomEnable(), qcms.getConfig().isRandomEnable(),
                qcms.getConfig().getMaxQuestionCountPerSession(),
                minAnswer > 0 ? minAnswer : 1,
                maxAnswer > 0 ? maxAnswer : 4,
                minAnswerTrue > 0 ? minAnswerTrue : 1,
                maxAnswerTrue > 0 ? maxAnswerTrue : 1);
        return qcms;
    }
//
//    public void setPartialSuccessSound(String partialSuccessSound) {
//        this.partialSuccessSound = partialSuccessSound;
//    }

}
