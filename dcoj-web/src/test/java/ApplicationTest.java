import com.dcoj.Application;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testSave(){
        UserEntity userEntity = new UserEntity();
        UserEntity save = mongoTemplate.save(userEntity);
        System.out.println(save.getUid());
    }

    @Test
    public void testUpdate(){
        Query query = new Query();
        List<UserEntity> userEntities = mongoTemplate.find(query, UserEntity.class);
        userEntities.get(0).setPassword("123");
        mongoTemplate.save(userEntities.get(0));
    }

    @Autowired
    MailService mailService;

    @Test
    public void testValue(){
        mailService.sendMail("549654065@qq.com","你好","HelloWorld");
    }

}
