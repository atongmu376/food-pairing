import com.phj.FoodApplication;
import com.phj.common.util.SHA256Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: food-pairing
 * @description:
 * @author: Mr.Pan
 * @create: 2021-08-30 09:43
 **/
@SpringBootTest(classes = FoodApplication.class)
public class Main {


    @Test
    public void test(){
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String s = SHA256Util.sha256("123456", salt);
        System.out.println(salt);
        System.out.println(s);
    }

}
