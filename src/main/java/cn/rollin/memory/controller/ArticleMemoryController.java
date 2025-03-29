package cn.rollin.memory.controller;

import cn.rollin.memory.common.res.Response;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.rollin.memory.pojo.ArticleMemory;
import cn.rollin.memory.service.IArticleMemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 文章记忆Controller
 *
 * @author rollin
 * @date 2024-03-29 15:28:55
 */
@RestController
@RequestMapping("/article-memory")
@RequiredArgsConstructor
public class ArticleMemoryController {

    private final IArticleMemoryService articleMemoryService;

    /**
     * 创建文章记忆
     *
     * @param articleMemory 文章记忆对象
     * @return 创建结果
     */
    @PostMapping
    public Response<ArticleMemory> create(@RequestBody ArticleMemory articleMemory) {
        articleMemoryService.save(articleMemory);
        return Response.buildSuccess(articleMemory);
    }

    /**
     * 更新文章记忆
     *
     * @param articleMemory 文章记忆对象
     * @return 更新结果
     */
    @PutMapping
    public Response<ArticleMemory> update(@RequestBody ArticleMemory articleMemory) {
        articleMemoryService.updateById(articleMemory);
        return Response.buildSuccess(articleMemory);
    }

    /**
     * 删除文章记忆
     *
     * @param id 文章记忆ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> delete(@PathVariable Long id) {
        articleMemoryService.removeById(id);
        return Response.buildSuccess();
    }

    /**
     * 获取文章记忆详情
     *
     * @param id 文章记忆ID
     * @return 文章记忆详情
     */
    @GetMapping("/{id}")
    public Response<ArticleMemory> getById(@PathVariable Long id) {
        return Response.buildSuccess(articleMemoryService.getById(id));
    }

    /**
     * 分页查询文章记忆列表
     *
     * @param page 页码
     * @param size 每页大小
     * @param userId 用户ID
     * @return 分页结果
     */
    @GetMapping("/page")
    public Response<Page<ArticleMemory>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId) {
        Page<ArticleMemory> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<ArticleMemory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleMemory::getUserId, userId);
        return Response.buildSuccess(articleMemoryService.page(pageInfo, queryWrapper));
    }

    /**
     * 获取待复习的文章记忆列表
     *
     * @param userId 用户ID
     * @return 待复习列表
     */
    @GetMapping("/review")
    public Response<Page<ArticleMemory>> getReviewList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId) {
        Page<ArticleMemory> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<ArticleMemory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleMemory::getUserId, userId)
                .le(ArticleMemory::getNextReviewAt, System.currentTimeMillis());
        return Response.buildSuccess(articleMemoryService.page(pageInfo, queryWrapper));
    }
}
