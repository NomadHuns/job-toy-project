package shop.mtcoding.jobara.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.user.dto.UserReq.JoinReqDto;
import shop.mtcoding.jobara.user.dto.UserReq.LoginReqDto;
import shop.mtcoding.jobara.user.model.User;
import shop.mtcoding.jobara.user.model.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(JoinReqDto joinReqDto) {
        User user = new User();
        user.setUsername(joinReqDto.getUsername());
        user.setPassword(joinReqDto.getPassword());
        user.setEmail(joinReqDto.getEmail());
        user.setRole("user");
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User login(LoginReqDto loginReqDto) {
        User userPS = userRepository.findByUsername(loginReqDto.getUsername());
        if (userPS == null) {
            throw new CustomException("유저네임을 확인해 주세요.");
        }
        if (!userPS.getPassword().equals(loginReqDto.getPassword())) {
            throw new CustomException("비밀번호를 확인해 주세요.");
        }
        return userPS;
    }
}
