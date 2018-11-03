package com.qmaker.quizzer.core.utils;

import com.qmaker.core.entities.Exercise;
import com.qmaker.core.entities.Test;
import com.qmaker.core.utils.ArrayListExerciseProvider;
import com.qmaker.quizzer.core.interfaces.QuizProvider;

import java.util.List;

/**
 * Created by istat on 10/12/17.
 */

public class ArrayListQuizProvider extends ArrayListExerciseProvider implements QuizProvider {
    Exercise currentExercise;

    public ArrayListQuizProvider(Test questionnaire) {
        super(questionnaire);
    }

    public ArrayListQuizProvider(List<Exercise> qcms) {
        super(qcms);
    }

    @Override
    public Exercise next() {
        if (currentExercise == null) {
            currentExercise = this.exercises.get(0);
            return currentExercise;
        } else {
            int index = this.exercises.indexOf(currentExercise);
            if (index < this.exercises.size() - 1) {
                this.currentExercise = this.exercises.get(index + 1);
                return currentExercise;
            }
        }
        currentExercise = null;
        return currentExercise;
    }

//    @Override
//    public Exercise previous(Exercise qcm) {
//        int index = this.exercises.indexOf(qcm);
//        if (index > 0) {
//            return this.exercises.getUris(index - 1);
//        }
//        return null;
//    }

    @Override
    public boolean hasNext() {
        if (currentExercise == null && exercises.isEmpty()) {
            return false;
        }
        int index = this.exercises.indexOf(currentExercise);
        if (index < this.exercises.size() - 1) {
            return true;
        }
        return false;
    }

//    @Override
//    public boolean hasPrevious(Exercise qcm) {
//        int index = this.exercises.indexOf(qcm);
//        if (index > 0) {
//            return true;
//        }
//        return false;
//    }
}
