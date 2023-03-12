package shop.mtcoding.jobara.common.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.user.model.User;
import shop.mtcoding.jobara.user.model.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User dummyUser = new User();
        dummyUser.setUsername("ssar");
        dummyUser.setPassword("1234");
        dummyUser.setEmail("ssar@naver.com");
        dummyUser.setTel("01012341234");
        dummyUser.setRole("user");
        userRepository.save(dummyUser);
    }

}
