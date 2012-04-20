package choco.proba;

import solver.Solver;
import solver.constraints.Constraint;
import solver.constraints.ConstraintFactory;
import solver.constraints.nary.alldifferent.AllDifferent;
import solver.constraints.unary.Relation;
import solver.search.strategy.StrategyFactory;
import solver.variables.IntVar;
import solver.variables.VariableFactory;
import solver.variables.view.Views;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: chameau
 * Date: 29/02/12
 */
public class LangfordBenchProbas extends AbstractBenchProbas {

    IntVar[] position;
    int k;
    int n;

    public LangfordBenchProbas(int k, int n, AllDifferent.Type type, int nbTests, int seed, boolean isProba) throws IOException {
        super(new Solver(), k * n, type, nbTests, seed, isProba);
        this.k = k;
        this.n = n;
    }

    @Override
    void configSearchStrategy() {
        solver.set(StrategyFactory.inputOrderMinVal(position, solver.getEnvironment()));
    }

    @Override
    void buildProblem(int size, boolean proba) {
        Collection<Constraint> allCstrs = new ArrayList<Constraint>();
        position = VariableFactory.enumeratedArray("p", n * k, 0, k * n - 1, solver);
        this.vars = this.allVars = position;
        for (int i = 0; i < k - 1; i++) {
            for (int j = 0; j < n; j++) {
                allCstrs.add(new Relation(
                        Views.sum(position[j + (i + 1) * n], Views.minus(position[j + i * n])),
                        Relation.R.EQ, j + 2, solver));
            }
        }
        allCstrs.add(ConstraintFactory.lt(position[0], position[n * k - 1], solver));
        allCstrs.add(new AllDifferent(position, solver, type));

        this.cstrs = allCstrs.toArray(new Constraint[allCstrs.size()]);
    }
}
