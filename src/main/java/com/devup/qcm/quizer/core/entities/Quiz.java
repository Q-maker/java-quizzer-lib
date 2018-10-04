package com.devup.qcm.quizer.core.entities;

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
            FIELD_MAX_TRUE_ANSWER_PER_EXERCISE = "maxTrueAnswerPerExercise";
    public final static String TAG = "quiz";
    public final int DEFAULT_MAX_PROPOSITION_PER_EXERCISE = 4,
            DEFAULT_MIN_PROPOSITIN_PER_MULTIPLE_CHOICE_EXERCISE = 2,
            DEFAULT_MAX_TRUE_ANSWER_PER_EXERCISE = 1;
    long timePerExercise;
    boolean allowQuestionCustomTime = true;
    int maxPropositionPerExercise = DEFAULT_MAX_PROPOSITION_PER_EXERCISE;
    int minPropositionPerMultipleChoiceExercise = DEFAULT_MIN_PROPOSITION_PER_MULTIPLE_CHOICE_EXERCISE;
    int maxTrueAnswerPerExercise = DEFAULT_MAX_TRUE_ANSWER_PER_EXERCISE;
    String backgroundSound,
            beginningSound,
            endingSound,
            successSound,
            errorSound,
            partialSuccessSound;

    public long getTimePerExercise() {
        return timePerExercise;
    }

    public void setTimePerExercise(long timePerExercise) {
        this.timePerExercise = timePerExercise;
    }

    public boolean isAllowQuestionCustomTime() {
        return allowQuestionCustomTime;
    }

    public void setAllowQuestionCustomTime(boolean allowQuestionCustomTime) {
        this.allowQuestionCustomTime = allowQuestionCustomTime;
    }

    public int getMaxPropositionPerExercise() {
        return maxPropositionPerExercise;
    }

    public void setMaxPropositionPerExercise(int maxPropositionPerExercise) {
        this.maxPropositionPerExercise = maxPropositionPerExercise;
    }

    public int getMinPropositionPerMultipleChoiceExercise() {
        return minPropositionPerMultipleChoiceExercise;
    }

    public void setMinPropositionPerMultipleChoiceExercise(int minPropositionPerMultipleChoiceExercise) {
        this.minPropositionPerMultipleChoiceExercise = minPropositionPerMultipleChoiceExercise;
    }

    public int getMaxTrueAnswerPerExercise() {
        return maxTrueAnswerPerExercise;
    }

    public void setMaxTrueAnswerPerExercise(int maxTrueAnswerPerExercise) {
        this.maxTrueAnswerPerExercise = maxTrueAnswerPerExercise;
    }

    public String getBackgroundSound() {
        return backgroundSound;
    }

    public void setBackgroundSound(String backgroundSound) {
        this.backgroundSound = backgroundSound;
    }

    public String getBeginningSound() {
        return beginningSound;
    }

    public void setBeginningSound(String beginningSound) {
        this.beginningSound = beginningSound;
    }

    public String getEndingSound() {
        return endingSound;
    }

    public void setEndingSound(String endingSound) {
        this.endingSound = endingSound;
    }

    public String getSuccessSound() {
        return successSound;
    }

    public void setSuccessSound(String successSound) {
        this.successSound = successSound;
    }

    public String getErrorSound() {
        return errorSound;
    }

    public void setErrorSound(String errorSound) {
        this.errorSound = errorSound;
    }

    public String getPartialSuccessSound() {
        return partialSuccessSound;
    }

    public void setPartialSuccessSound(String partialSuccessSound) {
        this.partialSuccessSound = partialSuccessSound;
    }

    public static Quiz create(QPackage qp) {
        return null;
    }
}
