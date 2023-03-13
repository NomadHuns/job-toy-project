package shop.mtcoding.jobara.common.radis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.jobara.user.vo.UserVo;

@Component
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper om;

    public void setValue(String key, Object value) {
        String principalJson = null;
        try {
            principalJson = om.writeValueAsString(value);
        } catch (Exception e) {
            System.out.println("파싱 오류");
        }
        redisTemplate.opsForValue().set(key, principalJson);
    }

    public UserVo getValue(String key) {
        UserVo principal = null;
        try {
            String principalJson = redisTemplate.opsForValue().get("principal");
            principal = om.readValue(principalJson, UserVo.class);
        } catch (Exception e) {
            System.out.println("파싱 실패");
        }
        return principal;
    }

    public void logout(String sessionId) {
        redisTemplate.delete(sessionId);
        UserVo UserVo = new UserVo();
        try {
            String principalJson = om.writeValueAsString(UserVo);
            setValue("principal", principalJson);
        } catch (Exception e) {
            System.out.println("파싱 오류");
        }
    }
}