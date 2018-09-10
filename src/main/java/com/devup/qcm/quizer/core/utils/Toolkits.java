package com.devup.qcm.quizer.core.utils;

import com.devup.qcm.core.entities.Qcm;
import com.devup.qcm.core.entities.Questionnaire;
import com.devup.qcm.core.utils.QcmUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by istat on 11/04/18.
 */

public class Toolkits {

    public static Questionnaire quizefy(Questionnaire questionnaire, int maxProposition) {
        return quizefy(new Random(), questionnaire, maxProposition);
    }

    public static Questionnaire quizefy(Random random, Questionnaire questionnaire, int maxProposition) {
        for (Qcm qcm : questionnaire.getQcms()) {
            quizefy(random, qcm, maxProposition);
        }
        return questionnaire;
    }

    public static Qcm quizefy(Qcm qcm, int maxProposition) {
        return quizefy(new Random(), qcm, maxProposition);
    }

    public static Qcm quizefy(Random random, Qcm qcm, int maxProposition) {
        List<Qcm.Proposition> quizPropositions = new ArrayList<>();
        List<Qcm.Proposition> propositionTrue;
        List<Qcm.Proposition> propositionFalse;
        propositionTrue = QcmUtils.getPropositionsWithTruth(qcm, true);
        propositionFalse = QcmUtils.getPropositionsWithTruth(qcm, false);
        Collections.shuffle(propositionFalse);
        int truePositionToPick = random.nextInt(propositionTrue.size());
        quizPropositions.add(propositionTrue.get(truePositionToPick));
        for (int i = 0; i < propositionFalse.size(); i++) {
            quizPropositions.add(propositionFalse.get(i));
            if (quizPropositions.size() >= maxProposition) {
                break;
            }
        }
        qcm.setPropositions(quizPropositions);
        return qcm;
    }
}
