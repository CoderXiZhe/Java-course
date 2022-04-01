import com.bjpowernode.entity.Province;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJson {
    public static void main(String[] args) throws JsonProcessingException {
        Province province = new Province(1,"河北","冀","石家庄");

        //用jackson把 province 转为json
        ObjectMapper om = new ObjectMapper();
        //writeValueAsString：把参数的java对象转换为json格式的字符串
        String json = om.writeValueAsString(province);
        System.out.println(json);
    }
}
