package qa.tools.ikeeper.junit.test.base;

import org.junit.Rule;
import org.junit.rules.TestRule;
import qa.tools.ikeeper.client.JiraClient;
import qa.tools.ikeeper.junit.test.IKeeperJUnitConnector;

public class JiraTestBase {

    @Rule
    public TestRule issueKeeper;

    public JiraTestBase() {
        issueKeeper = new IKeeperJUnitConnector(new JiraClient("https://issues.jboss.org"));
    }

}
