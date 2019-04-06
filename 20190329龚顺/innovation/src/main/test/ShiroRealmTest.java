import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroRealmTest {
//    public static void main(String[] args) {
//        //所需加密的参数  即  密码
//        String source = "123456";
//        //[盐] 一般为用户名 或 随机数
//        String salt = "201410803013";
//        //加密次数
//        int hashIterations = 1;
//
//        //调用 org.apache.shiro.crypto.hash.Md5Hash.Md5Hash(Object source, Object salt, int hashIterations)构造方法实现MD5盐值加密
//        Md5Hash mh = new Md5Hash(source, salt, hashIterations);
//        //打印最终结果
//        System.out.println(mh.toString());
//
//
//        /*调用org.apache.shiro.crypto.hash.SimpleHash.SimpleHash(String algorithmName, Object source, Object salt, int hashIterations)
//         * 构造方法实现盐值加密  String algorithmName 为加密算法 支持md5 base64 等*/
//        SimpleHash sh = new SimpleHash("md5", source, salt, hashIterations);
//        //打印最终结果
//        System.out.println(sh.toString());
//    }


    public static void main(String[] args) {
        //以下代码就不给予注释了！有不懂的可看前面的
        Factory<SecurityManager> fs = new IniSecurityManagerFactory("classpath:shiroRealmSalt.ini");
        SecurityManager sm = fs.getInstance();
        SecurityUtils.setSecurityManager(sm);
        Subject sj = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("201410803013", "123456");
        try {
            sj.login(token);
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(sj.isAuthenticated());
    }
}
