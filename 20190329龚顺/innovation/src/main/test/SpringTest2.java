import com.lan.innovation.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-*.xml"})
public class SpringTest2 {

    @Resource
    private UserMapper userMapper;


    @Test
    public void getItemById() throws Exception {
    }

    @Test
    public void getUser() throws Exception {
    }

    @Test
    public void getUser2() throws Exception {
    }

}
