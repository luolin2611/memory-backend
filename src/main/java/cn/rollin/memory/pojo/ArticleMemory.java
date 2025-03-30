package cn.rollin.memory.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文章记忆实体类
 *
 * @author rollin
 * @date 2025-03-29 17:11:32
 */
@Data
@TableName("t_article_memory")
public class ArticleMemory {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 下次复习时间
     */
    private LocalDateTime nextReviewAt;

    /**
     * 复习间隔（天）
     */
    private Integer intervalDay;

    /**
     * 熟练度因子
     */
    private Float easeFactor;

    /**
     * 连续正确次数
     */
    private Integer consecutiveCorrect;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}
