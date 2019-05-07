import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Leon on 2019/2/7.
 */
public class SimpleTest {


    @Test
    public void testJSON(){
        ArrayList arrayList = new ArrayList();
        HashMap hashMap1 = new HashMap();
        hashMap1.put("key1", "value1");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("key2", "value2");
        HashMap hashMap3 = new HashMap();
        hashMap3.put("key3", "value3");
        arrayList.add(hashMap1);
        arrayList.add(hashMap2);
        arrayList.add(hashMap3);
        JSONArray array = new JSONArray(arrayList);
        JSONObject jsonObject = array.getJSONObject(0);
        Integer integer = jsonObject.getInteger("11");
        System.out.println(integer);
    }


}
