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
 * @since 2024-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Web implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "web_id", type = IdType.AUTO)
    private Integer webId;

    private String webName;

    private String webAddr;


}
