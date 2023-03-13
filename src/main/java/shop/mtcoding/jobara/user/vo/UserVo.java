package shop.mtcoding.jobara.user.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.user.model.User;

@Getter
@Setter
@NoArgsConstructor
public class UserVo {
    private Long id;
    private String username;
    private String email;
    private String profile;
    private String role;

    public UserVo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.profile = user.getProfile();
        this.role = user.getRole();
    }
}
