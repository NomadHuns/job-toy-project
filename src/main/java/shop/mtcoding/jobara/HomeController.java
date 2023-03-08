package shop.mtcoding.jobara;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.mtcoding.jobara.common.dto.RespDto;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>(new RespDto<>(1, 1, "테스트 완료", null), HttpStatus.OK);
    }
}
