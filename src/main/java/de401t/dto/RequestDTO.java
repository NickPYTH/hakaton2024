package de401t.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long statusId;
    @ApiModelProperty(position = 5)
    private Long priorityId;
    @ApiModelProperty(position = 6)
    private Long typeId;
    @ApiModelProperty(position = 7)
    private Long clientId;
    @ApiModelProperty(position = 8)
    private Long assistantId;
    @ApiModelProperty(position = 9)
    private Long executorId;
    @ApiModelProperty(position = 10)
    private String createDate;
    @ApiModelProperty(position = 11)
    private String deadlineDate;
}
