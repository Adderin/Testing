package edu.uopeople.temp.linear;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;

public class PantsAndJackets {
    public static void main(String[] args) {
        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[]{-40, -75}, 0);

        LinearConstraintSet constraints = new LinearConstraintSet(
                new LinearConstraint(new double[]{0.5, 3, 1, 0, 0}, Relationship.LEQ, 1000),
                new LinearConstraint(new double[]{2, 2, 0, 1, 0}, Relationship.LEQ, 950),
                new LinearConstraint(new double[]{1, 1, 0, 0, 1}, Relationship.LEQ, 500));

        SimplexSolver solver = new SimplexSolver();

        PointValuePair solution = solver.optimize(f, constraints, new NonNegativeConstraint(true), PivotSelectionRule.BLAND);

        System.out.println("x1 = " + solution.getPoint()[0]);
        System.out.println("x2 = " + solution.getPoint()[1]);
        System.out.println("P = " + (-1 * solution.getValue()));
    }
}