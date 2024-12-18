package com.swaggy7.licenseweb.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Swaggy7
 * @since 2024-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Soft对象", description="")
public class Soft implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer softId;

    private String softName;

    private String softVersion;


}
