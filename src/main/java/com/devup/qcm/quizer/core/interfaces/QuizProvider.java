package com.devup.qcm.quizer.core.interfaces;

import com.devup.qcm.core.entities.Exercise;
import com.devup.qcm.core.interfaces.ExerciseProvider;

/**
 * Created by istat on 10/12/17.
 */

public interface QuizProvider extends ExerciseProvider {
    Exercise next();

    boolean hasNext();
}
