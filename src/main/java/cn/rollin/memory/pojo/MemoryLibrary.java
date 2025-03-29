package cn.rollin.memory.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 记忆库实体类
 *
 * @author rollin
 * @date 2025-03-29 16:11:32
 */
@Data
@TableName("t_memory_library")
public class MemoryLibrary {
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 所属用户ID
     */
    private Long userId;
    
    /**
     * 记忆库名称
     */
    private String name;
    
    /**
     * 记忆库描述
     */
    private String description;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
} 
