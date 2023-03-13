package shop.mtcoding.jobara.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.user.dto.UserReq.JoinReqDto;
import shop.mtcoding.jobara.user.dto.UserReq.LoginReqDto;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void join_test() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto("ssar", "1234", "ssar@naver.com");
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
        // resultActions.andExpect(jsonPath("$.data[0].username").value("Alice"));
    }

    @Test
    public void login_test() throws Exception {
        // given
        LoginReqDto loginReqDto = new LoginReqDto("ssar", "1234");
        String reqBody = om.writeValueAsString(loginReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/login").content(reqBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        String resp = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(resp);

        // verify
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(jsonPath("$.stateCode").value(1));
        resultActions.andExpect(jsonPath("$.code").value(0));
        resultActions.andExpect(jsonPath("$.msg").value("로그인 성공"));
        // resultActions.andExpect(jsonPath("$.data[0].username").value("Alice"));
    }
}
