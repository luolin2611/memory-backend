package cn.rollin.memory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.rollin.memory.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章Mapper接口
 *
 * @author rollin
 * @date 2025-03-29 17:11:32
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
