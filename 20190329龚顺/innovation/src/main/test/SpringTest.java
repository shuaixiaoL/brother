import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by lan_jiaxing on 2018/4/5 0005.
 */
public class SpringTest {

    private ApplicationContext ctx = null;

    {
        ctx = new ClassPathXmlApplicationContext(
                "spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml",
                "spring/applicationContext-trans.xml");
    }

    /**
     * 测试数据库是否连通
     * @throws SQLException
     */
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource= ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
}
