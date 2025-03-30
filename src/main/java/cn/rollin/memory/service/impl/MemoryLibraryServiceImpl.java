package cn.rollin.memory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.rollin.memory.enums.ReviewDifficulty;
import cn.rollin.memory.mapper.MemoryLibraryMapper;
import cn.rollin.memory.pojo.Article;
import cn.rollin.memory.pojo.ArticleMemory;
import cn.rollin.memory.pojo.MemoryLibrary;
import cn.rollin.memory.service.IArticleMemoryService;
import cn.rollin.memory.service.IMemoryLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 记忆库服务实现类
 *
 * @author rollin
 * @date 2025-03-29 16:11:32
 */
@Service
@RequiredArgsConstructor
public class MemoryLibraryServiceImpl extends ServiceImpl<MemoryLibraryMapper, MemoryLibrary> implements IMemoryLibraryService {

    private final IArticleMemoryService articleMemoryService;

    /**
     * 更新或插入文章记忆
     *
     * @param articleId 文章ID
     * @param userId 用户ID
     * @param difficulty 复习难度
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateOrInsertArticleMemory(Long articleId, Long userId, String difficulty) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        
        // 查询是否存在文章记忆记录
        LambdaQueryWrapper<ArticleMemory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleMemory::getArticleId, articleId)
                .eq(ArticleMemory::getUserId, userId);
        ArticleMemory articleMemory = articleMemoryService.getOne(queryWrapper);
        
        // 将难度字符串转换为枚举
        ReviewDifficulty reviewDifficulty = ReviewDifficulty.fromDescription(difficulty);
        
        if (articleMemory == null) {
            // 如果不存在，创建新记录
            articleMemory = new ArticleMemory();
            articleMemory.setArticleId(articleId);
            articleMemory.setUserId(userId);
            articleMemory.setNextReviewAt(now);
            articleMemory.setIntervalDay(1);
            articleMemory.setEaseFactor(2.5f);
            articleMemory.setConsecutiveCorrect(0);
            articleMemoryService.save(articleMemory);
        } else {
            // 根据难度更新记录
            switch (reviewDifficulty) {
                case AGAIN:
                    // 重来：重置所有参数
                    articleMemory.setNextReviewAt(now);
                    articleMemory.setIntervalDay(1);
                    articleMemory.setEaseFactor(2.5f);
                    articleMemory.setConsecutiveCorrect(0);
                    break;
                    
                case NORMAL:
                    // 一般：适度增加间隔和熟练度
                    articleMemory.setNextReviewAt(articleMemory.getNextReviewAt().plusDays(articleMemory.getIntervalDay()));
                    articleMemory.setIntervalDay((int) (articleMemory.getIntervalDay() * 1.2));
                    articleMemory.setEaseFactor(articleMemory.getEaseFactor() + 0.1f);
                    articleMemory.setConsecutiveCorrect(articleMemory.getConsecutiveCorrect() + 1);
                    break;
                    
                case HARD:
                    // 复杂：大幅增加间隔和熟练度
                    articleMemory.setNextReviewAt(articleMemory.getNextReviewAt().plusDays(articleMemory.getIntervalDay() * 2));
                    articleMemory.setIntervalDay((int) (articleMemory.getIntervalDay() * 2.5));
                    articleMemory.setEaseFactor(articleMemory.getEaseFactor() + 0.2f);
                    articleMemory.setConsecutiveCorrect(articleMemory.getConsecutiveCorrect() + 1);
                    break;
            }
            
            // 更新记录
            articleMemoryService.updateById(articleMemory);
        }
    }
} 
