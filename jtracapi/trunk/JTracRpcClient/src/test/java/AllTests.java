import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
		{ 
			TracSearchTest.class,
			TracTicketTest.class, 
			TracTicketComponentTest.class,
			TracTicketMilestoneTest.class,
			TracWikiTest.class
		})

public class AllTests {

}
