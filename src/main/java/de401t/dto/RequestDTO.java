package de401t.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RequestDTO {
    @ApiModelProperty
    private Long id;
    @ApiModelProperty(position = 1)
    private String name;
    @ApiModelProperty(position = 2)
    private String description;
    @ApiModelProperty(position = 3)
    private String solution;
    @ApiModelProperty(position = 4)
    private StatusDTO status;
    @ApiModelProperty(position = 5)
    private PriorityDTO priority;
    @ApiModelProperty(position = 6)
    private TypeDTO type;
    @ApiModelProperty(position = 7)
    private SubTypeDTO subType;
    @ApiModelProperty(position = 8)
    private UserDTO client;
    @ApiModelProperty(position = 9)
    private UserDTO assistant;
    @ApiModelProperty(position = 10)
    private UserDTO executor;
    @ApiModelProperty(position = 11)
    private Date createDate;
    @ApiModelProperty(position = 12)
    private Date deadlineDate;
    @ApiModelProperty(position = 13)
    private Date updateDate;
    @ApiModelProperty(position = 14)
    private String comment;
    @ApiModelProperty(position = 14)
    private GroupDTO group;
}
