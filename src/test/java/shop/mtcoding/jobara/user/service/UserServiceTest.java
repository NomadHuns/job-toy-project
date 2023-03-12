package shop.mtcoding.jobara.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import shop.mtcoding.jobara.user.dto.UserReq.JoinReqDto;
import shop.mtcoding.jobara.user.dto.UserReq.LoginReqDto;
import shop.mtcoding.jobara.user.model.User;
import shop.mtcoding.jobara.user.model.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void createUser_test() {
        // given
        JoinReqDto joinReqDto = new JoinReqDto("ssar", "1234", "ssar@gmaii.com");
        User user = new User();
        user.setId(4L);
        user.setUsername(joinReqDto.getUsername());
        user.setPassword(joinReqDto.getPassword());
        user.setEmail(joinReqDto.getEmail());
        user.setRole("user");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // when
        User userPS = userService.createUser(joinReqDto);

        // verify
        assertThat(userPS.getUsername()).isEqualTo("ssar");
    }

    @Test
    public void login_test() {
        // given
        LoginReqDto loginReqDto = new LoginReqDto("ssar", "1234");
        User dummyUser = new User();
        dummyUser.setUsername("ssar");
        dummyUser.setPassword("1234");
        dummyUser.setEmail("ssar@naver.com");
        dummyUser.setTel("01012341234");
        dummyUser.setRole("user");
        when(userRepository.findByUsername(loginReqDto.getUsername())).thenReturn(dummyUser);

        // when
        User userPS = userService.login(loginReqDto);

        // verify
        assertThat(userPS.getUsername()).isEqualTo("ssar");
        assertThat(userPS.getPassword()).isEqualTo("1234");
    }

}
