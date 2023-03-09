package shop.mtcoding.jobara.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserReq {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JoinReqDto {
        @NotEmpty(message = "유저네임을 입력하세요.")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "영문이나 숫자 2-20자 내에서 가능합니다.")
        @Size(min = 4, max = 20, message = "영문이나 숫자 2-20자 내에서 가능합니다.")
        private String username;
        @NotEmpty(message = "패스워드를 입력하세요.")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "영문이나 숫자 2-20자 내에서 가능합니다.")
        @Size(min = 4, max = 20, message = "영문이나 숫자 2-20자 내에서 가능합니다.")
        private String password;
        @Email(message = "이메일 양식이 올바르지 않습니다.")
        private String email;
    }
}
