package com.swaggy7.licenseweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Soft implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "soft_id", type = IdType.AUTO)
    private Long softId;

    private String softName;

    private String softVersion;

    private String softPath;


}
