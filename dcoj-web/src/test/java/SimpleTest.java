import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.judge.ResultEnum;
import com.dcoj.util.FileUploadUtils;
import com.dcoj.util.RandomValidateCodeUtil;
import org.junit.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Leon on 2019/2/7.
 */
public class SimpleTest {


    @Test
    public void testJSON() {
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
    @Test
    public void testJSON2(){
        // 测试输出格式
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
        System.out.println(array.toJSONString());
    }

    @Test
    public void testEnum() {
//        LanguageEnum java8 = LanguageEnum.valueOf("Java8");
//        System.out.println(java8);
        System.out.println(ResultEnum.AC.toString());

    }

    @Test
    public void testRandom(){
        System.out.println(RandomValidateCodeUtil.getRandomString());
        try {
            FileUploadUtils.uploadCode("","",LanguageEnum.valueOf("JAVA8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
