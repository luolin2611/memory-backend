package cn.rollin.memory.controller;

import cn.rollin.memory.common.res.Response;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.rollin.memory.pojo.Article;
import cn.rollin.memory.service.IArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 文章Controller
 *
 * @author rollin
 * @date 2024-03-29 15:28:55
 */
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final IArticleService articleService;

    /**
     * 创建文章
     *
     * @param article 文章对象
     * @return 创建结果
     */
    @PostMapping
    public Response<Article> create(@RequestBody Article article) {
        articleService.save(article);
        return Response.buildSuccess(article);
    }

    /**
     * 更新文章
     *
     * @param article 文章对象
     * @return 更新结果
     */
    @PutMapping
    public Response<Article> update(@RequestBody Article article) {
        articleService.updateById(article);
        return Response.buildSuccess(article);
    }

    /**
     * 删除文章
     *
     * @param id 文章ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> delete(@PathVariable Long id) {
        articleService.removeById(id);
        return Response.buildSuccess();
    }

    /**
     * 获取文章详情
     *
     * @param id 文章ID
     * @return 文章详情
     */
    @GetMapping("/{id}")
    public Response<Article> getById(@PathVariable Long id) {
        return Response.buildSuccess(articleService.getById(id));
    }

    /**
     * 分页查询文章列表
     *
     * @param page 页码
     * @param size 每页大小
     * @param userId 用户ID
     * @return 分页结果
     */
    @GetMapping("/page")
    public Response<Page<Article>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId) {
        Page<Article> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getUserId, userId);
        return Response.buildSuccess(articleService.page(pageInfo, queryWrapper));
    }
}
