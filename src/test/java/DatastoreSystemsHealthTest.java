import com.douglaskaminski.www.SpringBootCRUDWebAppApplication;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCRUDWebAppApplication.class)
public class DatastoreSystemsHealthTest {

	@Autowired
	DataSource dataSource;
	
	@Test
	public void dbPrimaryIsOk() {
		try {
			DatabaseMetaData metadata = dataSource.getConnection().getMetaData();
			String catalogName = dataSource.getConnection().getCatalog();
			
			MatcherAssert.assertThat(metadata, is(notNullValue()));
			MatcherAssert.assertThat(catalogName, is(equalTo("demo")));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
