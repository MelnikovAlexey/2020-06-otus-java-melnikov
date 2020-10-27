package org.otus.education.hw13.services;


import org.otus.education.hw13.model.Equation;

import java.util.List;

public interface EquationPreparer {
    List<Equation> prepareEquationsFor(int base);
}
