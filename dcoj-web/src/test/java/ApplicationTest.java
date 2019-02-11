import com.dcoj.Application;
import com.dcoj.entity.PermissionEntity;
import com.dcoj.entity.RoleEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.MailService;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test(){
        //Permission，创建5个权限，对应5个资源
        PermissionEntity permissionEntity1 = new PermissionEntity();
        permissionEntity1.setPermissionId("1");
        permissionEntity1.setURI("/permissiontest/login");
        permissionEntity1.setMethod("POST");
        PermissionEntity permissionEntity2 = new PermissionEntity();
        permissionEntity2.setPermissionId("2");
        permissionEntity2.setURI("/permissiontest/article");
        permissionEntity2.setMethod("GET");
        PermissionEntity permissionEntity3 = new PermissionEntity();
        permissionEntity3.setPermissionId("3");
        permissionEntity3.setURI("/permissiontest/require_auth");
        permissionEntity3.setMethod("GET");
        PermissionEntity permissionEntity4 = new PermissionEntity();
        permissionEntity4.setPermissionId("4");
        permissionEntity4.setURI("/permissiontest/require_role");
        permissionEntity4.setMethod("GET");
        PermissionEntity permissionEntity5 = new PermissionEntity();
        permissionEntity5.setPermissionId("5");
        permissionEntity5.setURI("/permissiontest/require_permission");
        permissionEntity5.setMethod("GET");
        //添加到数据库中
        mongoTemplate.save(permissionEntity1);
        mongoTemplate.save(permissionEntity2);
        mongoTemplate.save(permissionEntity3);
        mongoTemplate.save(permissionEntity4);
        mongoTemplate.save(permissionEntity5);

        //创建三个角色，角色1拥有普通权限，可以查看/login article require_auth,但是不能查看require_role require_permission
        //              角色2拥有所有权限
        //              角色3没有任何权限
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setRoleId("1");
        Set<String> role1Permission = new HashSet<>();
        role1Permission.add("1");
        role1Permission.add("2");
        role1Permission.add("3");
        roleEntity1.setPermissionId(role1Permission);
        RoleEntity roleEntity2 = new RoleEntity();
        roleEntity2.setRoleId("2");
        Set<String> role2Permission = new HashSet<>();
        role2Permission.add("1");
        role2Permission.add("2");
        role2Permission.add("3");
        role2Permission.add("4");
        role2Permission.add("5");
        roleEntity2.setPermissionId(role2Permission);
        RoleEntity roleEntity3 = new RoleEntity();
        roleEntity3.setRoleId("3");
        mongoTemplate.save(roleEntity1);
        mongoTemplate.save(roleEntity2);
        mongoTemplate.save(roleEntity3);

        // 新建三个用户
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUid("1");
        userEntity1.setRoles(new HashSet<>(Arrays.asList("1")));
        userEntity1.setPassword("1");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setPassword("2");
        userEntity2.setUid("2");
        userEntity2.setRoles(new HashSet<>(Arrays.asList("2")));
        UserEntity userEntity3 = new UserEntity();
        userEntity3.setPassword("3");
        userEntity3.setUid("3");
        userEntity3.setRoles(new HashSet<>(Arrays.asList("3")));
        mongoTemplate.save(userEntity1);
        mongoTemplate.save(userEntity2);
        mongoTemplate.save(userEntity3);

        //访客
        UserEntity guest = new UserEntity();
        guest.setUid("GUEST");
        guest.setRoles(new HashSet<>(Arrays.asList("4")));
        RoleEntity guestRole = new RoleEntity();
        guestRole.setPermissionId(new HashSet<>(Arrays.asList("1")));
        guestRole.setRoleId("4");
        mongoTemplate.save(guestRole);
        mongoTemplate.save(guest);

    }

    @Test
    public void testMongo(){
        Update update = new Update();
        update.set("password","4");
        UserEntity id = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is("3")), update, UserEntity.class);
        System.out.println(id);

    }

    @Autowired
    MailService mailService;

    @Test
    public void testValue(){
        mailService.sendMail("549654065@qq.com","你好","HelloWorld");
    }

}
