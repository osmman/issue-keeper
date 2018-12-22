package qa.tools.ikeeper.junit.test;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import ch.qos.logback.core.net.server.Client;
import qa.tools.ikeeper.annotation.BZ;
import qa.tools.ikeeper.client.BugzillaClient;
import qa.tools.ikeeper.test.IKeeperConnector;

public class BZVersionTest {

    @Rule
    public TestRule issueKeeper;

    private static final List<String> executed = new ArrayList<String>();

    public BZVersionTest() {
        issueKeeper = new IKeeperJUnitConnector("6.0.2", new BugzillaClient("https://bugzilla.redhat.com"));
    }

    @AfterClass
    public static void checkExecutions() {
        Assertions.assertThat(executed).hasSize(1);
        Assertions.assertThat(executed).contains("runSameVersionVerifiedTest");
    }

    @AfterClass
    public static void resetTestVersion() {
        //test version is static - shared among all tests
        //needs to be reset
        new IKeeperConnector((String) null, new BugzillaClient[0]);
    }

    @BZ("1079380")
    @Test
    public void runSameVersionVerifiedTest() {
        executed.add("runSameVersionVerifiedTest");
    }

    @BZ("1155593")
    @Test
    public void ignoreNewVerified61Test() {
        executed.add("ignoreNewVerified61Test");
        System.out.println("ignoreNewVerified61Test");
    }

}
