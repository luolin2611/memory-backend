package cn.rollin.memory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.rollin.memory.pojo.ArticleMemory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章记忆Mapper接口
 *
 * @author rollin
 * @date 2024-03-29 15:28:55
 */
@Mapper
public interface ArticleMemoryMapper extends BaseMapper<ArticleMemory> {
}
