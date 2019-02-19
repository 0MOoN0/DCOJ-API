import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Leon on 2019/2/7.
 */
public class SimpleTest {

    public static void main(String[] args) {
        try {
            SimpleTest simpleTest = new SimpleTest();
            SimpleTest t = simpleTest.getT(SimpleTest.class);
        }catch (Exception e){}
    }

    public <T> T getT(Class<T> clazz){
        return (T) new Object();
    }

    public <T> T parseJsonToObj(String json, Class<T> c) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            return JSON.toJavaObject(jsonObject, c);
        } catch (Exception e) {
        }
        return null;
    }

}
