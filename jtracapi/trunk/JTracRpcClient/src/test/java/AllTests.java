import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
		{ 
			TracTicketTest.class, 
			TracTicketComponentTest.class,
			TracWikiTest.class
		})

public class AllTests {

}
