package qa.tools.ikeeper.junit.action;

import org.junit.Assert;

import java.util.List;

import qa.tools.ikeeper.IssueDetails;
import qa.tools.ikeeper.action.IAction;

public class Fail implements IAction {

    @Override
    public void perform(String testName, List<IssueDetails> details) {
        String message = ActionMessage.generate(testName, details, "failed");
        Assert.fail(message);
    }

}
