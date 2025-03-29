package cn.rollin.memory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.rollin.memory.mapper.ArticleMemoryMapper;
import cn.rollin.memory.pojo.ArticleMemory;
import cn.rollin.memory.service.IArticleMemoryService;
import org.springframework.stereotype.Service;

/**
 * 文章记忆服务实现类
 *
 * @author rollin
 * @date 2024-03-29 15:28:55
 */
@Service
public class ArticleMemoryServiceImpl extends ServiceImpl<ArticleMemoryMapper, ArticleMemory> implements IArticleMemoryService {
}
