package com.devup.qcm.quizer.core.entities;

import com.devup.qcm.core.entities.Questionnaire;
import com.devup.qcm.core.entities.Test;

/**
 * Created by istat on 22/09/17.
 */

public class Quiz extends Test {
    public Quiz(Questionnaire questionnaire) {
        super(questionnaire);
    }
}
