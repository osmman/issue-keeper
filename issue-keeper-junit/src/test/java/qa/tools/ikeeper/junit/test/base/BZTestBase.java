package qa.tools.ikeeper.junit.test.base;

import org.junit.Rule;

import qa.tools.ikeeper.client.BugzillaClient;
import qa.tools.ikeeper.junit.test.IKeeperJUnitConnector;

public class BZTestBase {

    @Rule
    public IKeeperJUnitConnector issueKeeper;

    public BZTestBase() {
        issueKeeper = new IKeeperJUnitConnector(new BugzillaClient("https://bugzilla.redhat.com"));
    }

}
