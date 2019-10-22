package com.wsmhz.common.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created By tangbj On 2019/10/22
 * Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdForm {

    @NotNull(message = "id不能为空")
    private Integer id;
}
