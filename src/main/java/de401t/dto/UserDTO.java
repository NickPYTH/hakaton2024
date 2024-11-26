package de401t.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    @ApiModelProperty
    private Long id;
    @ApiModelProperty(position = 1)
    private String username;
    @ApiModelProperty(position = 2)
    private String password;
    @ApiModelProperty(position = 3)
    private String name;
    @ApiModelProperty(position = 4)
    private String surname;
    @ApiModelProperty(position = 5)
    private String secondName;
    @ApiModelProperty(position = 6)
    private String email;
    @ApiModelProperty(position = 7)
    private String phone;
    @ApiModelProperty(position = 8)
    private Integer role;
}
