package cn.rollin.memory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.rollin.memory.mapper.ArticleMapper;
import cn.rollin.memory.pojo.Article;
import cn.rollin.memory.service.IArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章服务实现类
 *
 * @author rollin
 * @date 2025-03-29 17:11:32
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
}
