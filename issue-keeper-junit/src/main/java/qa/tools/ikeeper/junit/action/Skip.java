package qa.tools.ikeeper.junit.action;

import org.junit.Assume;
import qa.tools.ikeeper.IssueDetails;
import qa.tools.ikeeper.action.IAction;

import java.util.List;

public class Skip implements IAction {

    @Override
    public void perform(String testName, List<IssueDetails> details) {
        String message = ActionMessage.generate(testName, details, "is skipped");
        Assume.assumeTrue(message, false);
    }
}
