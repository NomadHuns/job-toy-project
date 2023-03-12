package shop.mtcoding.jobara.user.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.common.dto.RespDto;
import shop.mtcoding.jobara.user.dto.UserReq.JoinReqDto;
import shop.mtcoding.jobara.user.model.User;
import shop.mtcoding.jobara.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@Valid @RequestBody JoinReqDto joinReqDto) {
        User user = userService.createUser(joinReqDto);
        return new ResponseEntity<>(new RespDto<>(1, 0, "회원 가입 성공", user), HttpStatus.CREATED);
    }
}
