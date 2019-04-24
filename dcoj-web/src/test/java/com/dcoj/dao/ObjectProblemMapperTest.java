package com.dcoj.dao;

import com.alibaba.fastjson.JSONObject;
import com.dcoj.entity.ObjectProblemEntity;
import com.dcoj.entity.ObjectProblemTagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author WANGQING
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectProblemMapperTest {

    @Autowired
    private ObjectProblemMapper objectProblemMapper;

    @Test
    public void removeByPrimaryKey() {
        System.out.println(objectProblemMapper.removeByPrimaryKey(1));
        System.out.println(objectProblemMapper.removeByPrimaryKey(2));
        System.out.println(objectProblemMapper.removeByPrimaryKey(3));
    }

    @Test
    public void insertSelective() {
        ObjectProblemEntity objectProblemEntity1 = new ObjectProblemEntity();
        ObjectProblemEntity objectProblemEntity2 = new ObjectProblemEntity();
        ObjectProblemEntity objectProblemEntity3 = new ObjectProblemEntity();
        ObjectProblemEntity objectProblemEntity4 = new ObjectProblemEntity();
        ObjectProblemEntity objectProblemEntity5 = new ObjectProblemEntity();
        ObjectProblemEntity objectProblemEntity6 = new ObjectProblemEntity();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("des"," 下列选项中，用于在定义子类时声明父类名的关键字是：( ) ");
        jsonObject1.put("opt1","interface");
        jsonObject1.put("opt2","package");
        jsonObject1.put("opt3","extends");
        jsonObject1.put("opt4","class");
        objectProblemEntity1.setType(0);
        objectProblemEntity1.setDescription(jsonObject1);
        objectProblemEntity1.setAnswer("C");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("des","String str1 = “abc”，“abc”分配在内存哪个区域？ ");
        jsonObject2.put("opt1","堆");
        jsonObject2.put("opt2","栈");
        jsonObject2.put("opt3","字符串常量区");
        jsonObject2.put("opt4","寄存器");
        objectProblemEntity2.setType(0);
        objectProblemEntity2.setDescription(jsonObject2);
        objectProblemEntity2.setAnswer("C");

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("des","<div class=\"question-main\"><span>下列程序段的输出结果是：（</span> <span>）</span> <span> <br> public void complicatedexpression_r(){<br> &nbsp;&nbsp;&nbsp;&nbsp;int x=20, y=30;<br> &nbsp;&nbsp;&nbsp;&nbsp;boolean   b;<br> &nbsp;&nbsp;&nbsp;&nbsp;b = x &gt; 50 &amp;&amp; y &gt; 60 || x &gt; 50 &amp;&amp; y &lt; -60 || x &lt; -50 &amp;&amp; y &gt; 60 || x &lt; -50 &amp;&amp; y &lt; -60;<br> &nbsp;&nbsp;&nbsp;&nbsp;System.out.println(b);<br> }<br> </span></div>");
        objectProblemEntity3.setType(1);
        objectProblemEntity3.setDescription(jsonObject3);
        objectProblemEntity3.setAnswer("false");

        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("des","局部内部类可以用private、abstract、_____ 修饰符修饰");
        objectProblemEntity4.setType(1);
        objectProblemEntity4.setDescription(jsonObject4);
        objectProblemEntity4.setAnswer("final");

        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("des","如果a.equals(b)返回true，那么a,b两个对象的hashcode必须相同");
        objectProblemEntity5.setType(2);
        objectProblemEntity5.setDescription(jsonObject5);
        objectProblemEntity5.setAnswer("true");

        JSONObject jsonObject6 = new JSONObject();
        jsonObject6.put("des","数组越界可以引发异常");
        objectProblemEntity6.setType(2);
        objectProblemEntity6.setDescription(jsonObject6);
        objectProblemEntity6.setAnswer("true");

        objectProblemMapper.insertSelective(objectProblemEntity1);
        objectProblemMapper.insertSelective(objectProblemEntity2);
        objectProblemMapper.insertSelective(objectProblemEntity3);
        objectProblemMapper.insertSelective(objectProblemEntity4);
        objectProblemMapper.insertSelective(objectProblemEntity5);
        objectProblemMapper.insertSelective(objectProblemEntity6);

    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println(objectProblemMapper.getByPrimaryKey(1));
        System.out.println(objectProblemMapper.getByPrimaryKey(2));
        System.out.println(objectProblemMapper.getByPrimaryKey(3));
    }

    @Test
    public void updateByPrimaryKeySelective() {
       ObjectProblemEntity objectProblemEntity =  objectProblemMapper.getByPrimaryKey(1);
       objectProblemEntity.setAnswer("CC");
        System.out.println(objectProblemMapper.updateByPrimaryKeySelective(objectProblemEntity));
    }

    @Test
    public void countObjectProblems() {
        System.out.println(objectProblemMapper.countObjectProblems());
    }

    @Test
    public void countObjectProblemsByType() {
        System.out.println(objectProblemMapper.countObjectProblemsByType(0));
        System.out.println(objectProblemMapper.countObjectProblemsByType(1));
        System.out.println(objectProblemMapper.countObjectProblemsByType(2));
    }

    @Test
    public void listObjectProblemTagsByPrimaryKey() {
        List<Map<String,Object>> list = objectProblemMapper.listObjectProblemTagsByPrimaryKey(2);
        System.out.println("list:"+list);
        for (Map<String,Object>  map:list){
            map.forEach((k,v)-> System.out.println("k:"+k+" v:"+v));
        }
    }

    @Test
    public void listAll(){
        //objectProblemMapper.listAll().forEach(System.out::println);
    }

    @Test
    public void getAnswerByPrimaryKey(){
        System.out.println(objectProblemMapper.getAnswerByPrimaryKey(1));
        System.out.println(objectProblemMapper.getAnswerByPrimaryKey(2));
        System.out.println(objectProblemMapper.getAnswerByPrimaryKey(3));
    }

    @Test
    public void listByStatus(){
        objectProblemMapper.listByStatus(0).forEach(System.out::println);
    }

    @Test
    public void countObjectProblemsByStatus(){
        System.out.println(objectProblemMapper.countObjectProblemsByStatus(0));
    }

    @Test
    public void listByTypeStatus(){
        //objectProblemMapper.listByTypeStatus(0,0).forEach(System.out::println);
        //objectProblemMapper.listByTypeStatus(0,1).forEach(System.out::println);
    }
}