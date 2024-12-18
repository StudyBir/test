package com.swaggy7.licenseweb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.entity.Web;
import com.swaggy7.licenseweb.mapper.WebMapper;
import com.swaggy7.licenseweb.service.WebService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Swaggy7
 * @since 2024-03-11
 */
@Service
public class WebServiceImpl extends ServiceImpl<WebMapper, Web> implements WebService {

}
