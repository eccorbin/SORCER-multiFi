package sorcer.arithmetic.tester.requestor;

import sorcer.arithmetic.tester.provider.Adder;
import sorcer.arithmetic.tester.provider.Averager;
import sorcer.arithmetic.tester.provider.Multiplier;
import sorcer.arithmetic.tester.provider.Subtractor;
import sorcer.arithmetic.tester.provider.impl.AdderImpl;
import sorcer.arithmetic.tester.provider.impl.AveragerImpl;
import sorcer.arithmetic.tester.provider.impl.MultiplierImpl;
import sorcer.arithmetic.tester.provider.impl.SubtractorImpl;
import sorcer.co.operator;
import sorcer.core.provider.Jobber;
import sorcer.core.provider.rendezvous.ServiceJobber;
import sorcer.core.requestor.SorcerRequestor;
import sorcer.service.*;

import static sorcer.co.operator.*;
import static sorcer.eo.operator.*;
import static sorcer.so.operator.exert;

public class ArithmeticSorcerRequestor extends SorcerRequestor {

    public Routine getExertion(String... args) throws RoutineException, ContextException, SignatureException {

        Task t3 = task("t3", sigFi("object/subtract", sig("subtract", SubtractorImpl.class)),
                sigFi("object/average", sig("average", AveragerImpl.class)),
                sigFi("net/subtract", sig("subtract", Subtractor.class)),
                sigFi("net/average", sig("average", Averager.class)),
                context("t3-cxt", operator.inVal("arg/x1"), operator.inVal("arg/x2"),
                        outVal("outDispatcher/y")));

        Task t4 = task("t4", sigFi("object", sig("multiply", MultiplierImpl.class)),
                sigFi("net", sig("multiply", Multiplier.class)),
                context("multiply", operator.inVal("arg/x1", 10.0), operator.inVal("arg/x2", 50.0),
                        outVal("outDispatcher/y")));

        Task t5 = task("t5", sigFi("object", sig("add", AdderImpl.class)),
                sigFi("net", sig("add", Adder.class)),
                context("add", operator.inVal("arg/x1", 20.0), operator.inVal("arg/x2", 80.0),
                        outVal("outDispatcher/y")));

        Job job = job("j1", sigFi("object", sig("exert", ServiceJobber.class)),
                sigFi("net", sig("exert", Jobber.class)),
                job("j2", sig("exert", ServiceJobber.class), t4, t5),
                t3,
                pipe(outPoint(t4, "outDispatcher/y"), inPoint(t3, "arg/x1")),
                pipe(outPoint(t5, "outDispatcher/y"), inPoint(t3, "arg/x2")),
                metaFi("job1", fi("net", "j1"), fi("net", "j1/j2/t4")),
                metaFi("job2", fi("net", "j1"), fi("net", "j1/j2/t4"), fi("net", "j1/j2/t5")));

        return job;

    }

    @Override
    public void run(String... args) throws Exception {
        Routine exertion = exert(getExertion());
        logger.info("<<<<<<<<<< f5 context: \n" + upcontext(exertion));

    }
}