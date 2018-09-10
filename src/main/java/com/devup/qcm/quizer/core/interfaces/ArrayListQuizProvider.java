package com.devup.qcm.quizer.core.interfaces;

import com.devup.qcm.core.entities.Exercise;
import com.devup.qcm.core.entities.Test;
import com.devup.qcm.core.utils.ArrayListExerciseProvider;
import com.devup.qcm.quizer.interfaces.QuizProvider;

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
