import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {


    @Test
    public void test(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(new Date()));
    }
}
