package com.devup.qcm.quizer.core.entities;

import com.devup.qcm.core.engines.Component;
import com.devup.qcm.core.engines.ComponentManager;
import com.devup.qcm.core.io.QPackage;

/**
 * Created by istat on 22/09/17.
 */

public class Quiz {
    public final static String
            FIELD_TIME_PER_EXERCISE = "timePerExercise",
            FIELD_ALLOW_QUESTION_CUSTOM_TIME = "allowQuestionCustomTime",
            FIELD_MAX_PROPOSITION_PER_EXERCISE = "maxPropositionPerExercise",
            FIELD_MIN_PROPOSITION_PER_MULTIPLE_CHOICE_EXERCISE = "minPropositionPerMultipleChoiceExercise",
            FIELD_MIN_TRUE_ANSWER_PER_EXERCISE = "minTrueAnswerPerExercise",
            FIELD_MAX_TRUE_ANSWER_PER_EXERCISE = "maxTrueAnswerPerExercise",
            FIELD_BEGINNING_SOUND = "beginningSound",
            FIELD_ENDING_SOUND = "endingSound",
            FIELD_SUCCESS_SOUND = "successSound",
            FIELD_PARTIAL_SUCCESS_SOUND = "partialSuccessSound",
            FIELD_ERROR_SOUND = "errorSound";
    public final static String TAG = "quiz";
    public final int DEFAULT_MAX_PROPOSITION_PER_EXERCISE = 4,
            DEFAULT_MIN_PROPOSITION_PER_MULTIPLE_CHOICE_EXERCISE = 2,
            DEFAULT_MAX_TRUE_ANSWER_PER_EXERCISE = 1;
    Component component;

    Quiz(Component component) {
        this.component = component;
    }

    public Quiz from(QPackage qp) {
        Component component = ComponentManager.getInstance().fetch(qp).getComponent(Quiz.TAG);
        if (component == null) {
            return null;
        }
        Quiz quiz = new Quiz(component);
        return quiz;
    }

    public long getTimePerExercise() {
        return component.getSummaryProperties().getInt(FIELD_TIME_PER_EXERCISE);
    }


    public boolean isAllowQuestionCustomTime() {
        return component.getSummaryProperties().getBoolean(FIELD_ALLOW_QUESTION_CUSTOM_TIME);
    }

//    public void setAllowQuestionCustomTime(boolean allowQuestionCustomTime) {
//        this.allowQuestionCustomTime = allowQuestionCustomTime;
//    }

    public int getMaxPropositionPerExercise() {
        return component.getSummaryProperties().getInt(FIELD_MAX_PROPOSITION_PER_EXERCISE);
    }

//    public void setMaxPropositionPerExercise(int maxPropositionPerExercise) {
//        this.maxPropositionPerExercise = maxPropositionPerExercise;
//    }

    public int getMinPropositionPerMultipleChoiceExercise() {
        return component.getSummaryProperties().getInt(FIELD_MIN_PROPOSITION_PER_MULTIPLE_CHOICE_EXERCISE);
    }

//    public void setMinPropositionPerMultipleChoiceExercise(int minPropositionPerMultipleChoiceExercise) {
//        this.minPropositionPerMultipleChoiceExercise = minPropositionPerMultipleChoiceExercise;
//    }

    public int getMaxTrueAnswerPerExercise() {
        return component.getSummaryProperties().getInt(FIELD_MAX_TRUE_ANSWER_PER_EXERCISE);
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
//
//    public void setPartialSuccessSound(String partialSuccessSound) {
//        this.partialSuccessSound = partialSuccessSound;
//    }

}
