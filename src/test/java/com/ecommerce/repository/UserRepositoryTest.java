package com.ecommerce.repository;

import com.ecommerce.model.User;
import com.ecommerce.model.request.UserLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.StringJoiner;

import static com.ecommerce.helper.EncryptionHelper.md5;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO users(")
                .add("id, email, password, name, phone, created_datetime, updated_datetime)")
                .add("VALUES (1, 'test@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 'KT', '0996217524', now(), now());");

        jdbcTemplate.execute(sql.toString());
        System.out.println(md5("1234"));
    }

    private User mockUser() {
        return (new User())
                .setPassword("12345")
                .setName("KT")
                .setPhone("099123566")
                .setEmail("test@gmail.com");
    }

    private UserLoginRequest mockUserLoginRequest() {
        return (new UserLoginRequest())
                .setEmail("test@gmail.com")
                .setPassword("1234");
    }

    @Test
    public void createUserSuccess() {
        User mockUser = this.mockUser();
        String userId = userRepository.createUser(mockUser);
        assertNotNull(userId);
    }

    @Test
    public void createUserFail() {
        String userId = userRepository.createUser(new User().setPassword("12345"));
        assertNull(userId);
    }

    @Test
    public void findUserByIdSuccess() {
        User user = userRepository.findUserById("1");
        assertEquals("1", user.getId());
        assertEquals("test@gmail.com", user.getEmail());
        assertEquals("KT", user.getName());
        assertEquals("0996217524", user.getPhone());
    }

    @Test
    public void findUserByIdNotFoundUser() {
        User user = userRepository.findUserById("testIDX");
        assertNull(user);
    }

    @Test
    public void loginSuccess() {
        UserLoginRequest request = this.mockUserLoginRequest();
        User user = userRepository.login(request);
        assertEquals("test@gmail.com", user.getEmail());
        assertEquals("KT", user.getName());
        assertEquals("0996217524", user.getPhone());
    }

    @Test
    public void loginFail() {
        User user = userRepository.login(new UserLoginRequest().setPassword("12345"));
        assertNull(user);
    }

    @Test
    public void updateLastLoginSuccess() {
        Object user = userRepository.updateLastLogin("1");
        assertNotNull(user);
    }

    @Test
    public void updateLastLoginFail() {
        Object user = userRepository.updateLastLogin("testIDX");
        assertNull(user);
    }
}