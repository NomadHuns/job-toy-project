package shop.mtcoding.jobara.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespDto<T> {
    private Integer stateCode;
    private Integer code;
    private String msg;
    private T data;
}
