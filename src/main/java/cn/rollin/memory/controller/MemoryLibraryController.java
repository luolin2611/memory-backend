package cn.rollin.memory.controller;

import cn.rollin.memory.common.res.Response;
import cn.rollin.memory.common.utils.UserContext;
import cn.rollin.memory.service.IMemoryLibraryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.rollin.memory.pojo.MemoryLibrary;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 记忆库Controller
 *
 * @author rollin
 * @date 2025-03-29 16:11:32
 */
@RestController
@RequestMapping("/memory-library")
@RequiredArgsConstructor
public class MemoryLibraryController {

    private final IMemoryLibraryService memoryLibraryService;

    /**
     * 创建记忆库
     *
     * @param memoryLibrary 记忆库对象
     * @return 创建结果
     */
    @PostMapping
    public Response<MemoryLibrary> create(@RequestBody MemoryLibrary memoryLibrary) {
        memoryLibrary.setUserId(UserContext.getUser().getId());
        memoryLibraryService.save(memoryLibrary);
        return Response.buildSuccess(memoryLibrary);
    }

    /**
     * 更新记忆库
     *
     * @param memoryLibrary 记忆库对象
     * @return 更新结果
     */
    @PutMapping
    public Response<MemoryLibrary> update(@RequestBody MemoryLibrary memoryLibrary) {
        memoryLibraryService.updateById(memoryLibrary);
        return Response.buildSuccess(memoryLibrary);
    }

    /**
     * 删除记忆库
     *
     * @param id 记忆库ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> delete(@PathVariable Long id) {
        memoryLibraryService.removeById(id);
        return Response.buildSuccess();
    }

    /**
     * 获取记忆库详情
     *
     * @param id 记忆库ID
     * @return 记忆库详情
     */
    @GetMapping("/{id}")
    public Response<MemoryLibrary> getById(@PathVariable Long id) {
        return Response.buildSuccess(memoryLibraryService.getById(id));
    }

    /**
     * 分页查询记忆库列表
     *
     * @param page 页码
     * @param size 每页大小
     * @param userId 用户ID
     * @return 分页结果
     */
    @GetMapping("/page")
    public Response<Page<MemoryLibrary>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId) {
        Page<MemoryLibrary> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<MemoryLibrary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MemoryLibrary::getUserId, userId);
        return Response.buildSuccess(memoryLibraryService.page(pageInfo, queryWrapper));
    }

    /**
     * 查询记忆库列表
     *
     * @return 查询结果
     */
    @GetMapping("/list")
    public Response<List<MemoryLibrary>> list() {
        LambdaQueryWrapper<MemoryLibrary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MemoryLibrary::getUserId, UserContext.getUser().getId());
        return Response.buildSuccess(memoryLibraryService.list(queryWrapper));
    }
}
