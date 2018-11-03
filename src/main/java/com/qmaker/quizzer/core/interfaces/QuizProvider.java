package com.qmaker.quizzer.core.interfaces;

import com.qmaker.core.entities.Exercise;
import com.qmaker.core.interfaces.ExerciseProvider;

/**
 * Created by istat on 10/12/17.
 */

public interface QuizProvider extends ExerciseProvider {
    Exercise next();

    boolean hasNext();
}
