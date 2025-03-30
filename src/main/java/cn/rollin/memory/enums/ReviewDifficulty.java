package cn.rollin.memory.enums;

/**
 * 复习难度枚举
 *
 * @author rollin
 * @date 2025-03-29 17:11:32
 */
public enum ReviewDifficulty {
    
    /**
     * 重来
     */
    AGAIN("重来"),
    
    /**
     * 一般
     */
    NORMAL("一般"),
    
    /**
     * 复杂
     */
    HARD("复杂");
    
    private final String description;
    
    ReviewDifficulty(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static ReviewDifficulty fromDescription(String description) {
        for (ReviewDifficulty difficulty : values()) {
            if (difficulty.getDescription().equals(description)) {
                return difficulty;
            }
        }
        throw new IllegalArgumentException("Unknown review difficulty: " + description);
    }
} 
