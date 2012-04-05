package in.haeg.stablemarriage.test;

import static org.junit.Assert.*;

import yeti.Yeti;
import yeti.YetiLog;


import org.junit.Test;

public class RunYETI {

    @Test
    public void test() {
        String [] args = { "-java", "-time=5s", "-yetiPath=.", "-testModules=in.haeg.stablemarriage.Coordinator" };
        Yeti.main(args);
        assertEquals(0, YetiLog.proc.getNumberOfUniqueFaults());
    }

}
