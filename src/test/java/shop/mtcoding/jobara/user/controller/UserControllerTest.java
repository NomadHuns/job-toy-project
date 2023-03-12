package shop.mtcoding.jobara.user.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.user.dto.UserReq.JoinReqDto;
import shop.mtcoding.jobara.user.dto.UserReq.LoginReqDto;
import shop.mtcoding.jobara.user.model.User;
import shop.mtcoding.jobara.user.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private UserService userService;

    @Test
    public void join_sussess_test() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto("ssar", "1234", "ssar@naver.com");
        User user = new User();
        user.setId(4L);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@naver.com");
        given(userService.createUser(joinReqDto)).willReturn(user);
        String reqBody = om.writeValueAsString(joinReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/join").content(reqBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        String resp = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(resp);

        // verify
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.stateCode").value(1));
        resultActions.andExpect(jsonPath("$.code").value(0));
        resultActions.andExpect(jsonPath("$.msg").value("회원 가입 성공"));
    }

    @Test
    public void join_fail_test_1() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto("김괄괄", "1234", "ssar@naver.com");
        User user = new User();
        user.setId(4L);
        user.setUsername("김괄괄");
        user.setPassword("1234");
        user.setEmail("ssar@naver.com");
        given(userService.createUser(joinReqDto)).willReturn(user);
        String reqBody = om.writeValueAsString(joinReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/join").content(reqBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        String resp = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(resp);

        // verify
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void join_fail_test_2() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto("", "1234", "ssar@naver.com");
        User user = new User();
        user.setId(4L);
        user.setUsername("");
        user.setPassword("1234");
        user.setEmail("ssar@naver.com");
        given(userService.createUser(joinReqDto)).willReturn(user);
        String reqBody = om.writeValueAsString(joinReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/join").content(reqBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        String resp = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(resp);

        // verify
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void join_fail_test_3() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto("ssar", "1234", "ssar");
        User user = new User();
        user.setId(4L);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar");
        given(userService.createUser(joinReqDto)).willReturn(user);
        String reqBody = om.writeValueAsString(joinReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/join").content(reqBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        String resp = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(resp);

        // verify
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void join_fail_test_4() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto("1", "1234", "ssar@naver.com");
        User user = new User();
        user.setId(4L);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar");
        given(userService.createUser(joinReqDto)).willReturn(user);
        String reqBody = om.writeValueAsString(joinReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/join").content(reqBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        String resp = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(resp);

        // verify
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void login_sussess_test() throws Exception {
        // given
        LoginReqDto loginReqDto = new LoginReqDto("ssar", "1234");
        User user = new User();
        user.setId(4L);
        user.setUsername("ssar");
        user.setPassword("1234");
        given(userService.login(loginReqDto)).willReturn(user);
        String reqBody = om.writeValueAsString(loginReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/login").content(reqBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // verify
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.stateCode").value(1));
        resultActions.andExpect(jsonPath("$.code").value(0));
        resultActions.andExpect(jsonPath("$.msg").value("로그인 성공"));
    }

    @Test
    public void login_fail_test_1() throws Exception {
        // given
        LoginReqDto loginReqDto = new LoginReqDto("한글", "1234");
        User user = new User();
        user.setId(4L);
        user.setUsername("한글");
        user.setPassword("1234");
        given(userService.login(loginReqDto)).willReturn(user);
        String reqBody = om.writeValueAsString(loginReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/login").content(reqBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // verify
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void login_fail_test_2() throws Exception {
        // given
        LoginReqDto loginReqDto = new LoginReqDto("", "1234");
        User user = new User();
        user.setId(4L);
        user.setUsername("");
        user.setPassword("1234");
        given(userService.login(loginReqDto)).willReturn(user);
        String reqBody = om.writeValueAsString(loginReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/login").content(reqBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // verify
        resultActions.andExpect(status().is4xxClientError());
    }
}
