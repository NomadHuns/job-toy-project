package shop.mtcoding.jobara.user.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.user.dto.UserReq.JoinReqDto;
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
}
