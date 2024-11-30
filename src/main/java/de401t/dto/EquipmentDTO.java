package de401t.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipmentDTO {
    @ApiModelProperty
    private Long id;
    @ApiModelProperty(position = 1)
    private String code;
    @ApiModelProperty(position = 2)
    private String name;
    @ApiModelProperty(position = 3)
    private UserDTO user;
    @ApiModelProperty(position = 4)
    private String note;
}
