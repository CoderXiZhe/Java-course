import com.bjpowernode.service.HelloService;
import com.bjpowernode.service.imp.HelloServiceImp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

//        HelloService service = new HelloServiceImp();
//        service.SayHello("张三");

        //使用反射执行sayHello 核心Method
        HelloService target = new HelloServiceImp();

        Method method = HelloService.class.getMethod("SayHello", String.class);
        /*
            invoke 是Method类中的一个方法 表示执行方法的调用
            参数:
                1.Object 表示对象的 要执行这个对象的方法
                2.Object... args 方法执行时的参数值
                返回值 ： 方法执行后的返回值
         */
        Object ret = method.invoke(target,"李四");

    }
}
