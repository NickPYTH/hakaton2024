package de401t.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {
    @ApiModelProperty
    private Long id;
    @ApiModelProperty(position = 1)
    private String name;
}
