package qa.tools.ikeeper.junit.test.query;

import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import qa.tools.ikeeper.annotation.BZ;
import qa.tools.ikeeper.client.JiraClient;
import qa.tools.ikeeper.interceptor.QueryInterceptor;
import qa.tools.ikeeper.junit.test.IKeeperJUnitConnector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class BZRuntimeConstraintsTest {

    private static final List<String> executed = new ArrayList<String>();

    private RemoteAPI controller;

    @Rule
    public IKeeperJUnitConnector issueKeeper;

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> remoteController() {
        List<Object[]> controllerList = new ArrayList<Object[]>();
        controllerList.add(new Object[]{new RESTAPI()});
        controllerList.add(new Object[]{new SOAPAPI()});
        controllerList.add(new Object[]{new JMSAPI()});
        return controllerList;
    }

    public BZRuntimeConstraintsTest(RemoteAPI controller) {
        this.controller = controller;
        issueKeeper = new IKeeperJUnitConnector(new QueryInterceptor(), new JiraClient("https://issues.jboss.org", "KEY=${id} AND STATUS != CLOSED AND STATUS != RESOLVED"));
        issueKeeper.setEnvironmentProperty("remoteAPI", controller.toString());
    }

    @AfterClass
    public static void checkExecutions() {
        for (String ex : executed) {
            System.out.println("check:" + ex);
        }
        Assertions.assertThat(executed).contains("runVerifiedRESTIssueTest-RESTAPI", "runVerifiedRESTIssueTest-SOAPAPI", "runVerifiedRESTIssueTest-JMSAPI", "ignoreNewJMSIssueTest-RESTAPI", "ignoreNewJMSIssueTest-SOAPAPI", "ignoreJMSNewAndVerifiedRESTIssuesTest-RESTAPI", "ignoreJMSNewAndVerifiedRESTIssuesTest-SOAPAPI");
    }

    @BZ("1145046")
    @Test
    public void runVerifiedRESTIssueTest() {
        controller.newOrder();
        executed.add("runVerifiedRESTIssueTest-" + controller);
    }

    @BZ("1217371")
    @Test
    public void ignoreNewJMSIssueTest() {
        controller.newOrder();
        executed.add("ignoreNewJMSIssueTest-" + controller);
    }

    @BZ({"1217371", "1145046"})
    @Test
    public void ignoreJMSNewAndVerifiedRESTIssuesTest() {
        controller.newOrder();
        executed.add("ignoreJMSNewAndVerifiedRESTIssuesTest-" + controller);
    }

    interface RemoteAPI {

        public void newOrder();
    }

    static class RESTAPI implements RemoteAPI {

        @Override
        public void newOrder() {
            System.out.println("Order through REST");
        }

        @Override
        public String toString() {
            return "RESTAPI";
        }
    }

    static class SOAPAPI implements RemoteAPI {

        @Override
        public void newOrder() {
            System.out.println("Order through SOAP");
        }

        @Override
        public String toString() {
            return "SOAPAPI";
        }
    }

    static class JMSAPI implements RemoteAPI {

        @Override
        public void newOrder() {
            System.out.println("Order through JMS");
        }

        @Override
        public String toString() {
            return "JMSAPI";
        }
    }

}
