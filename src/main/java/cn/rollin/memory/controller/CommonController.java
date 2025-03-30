package cn.rollin.memory.controller;

import cn.rollin.memory.common.enums.ResStatusEnum;
import cn.rollin.memory.common.res.Response;
import cn.rollin.memory.common.utils.MinioUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 通用Controller
 *
 * @author rollin
 * @date 2024-03-29 15:28:55
 */
@Slf4j
@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonController {

    private final MinioUtil minioUtil;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件访问URL
     */
    @PostMapping("/upload")
    public Response<Object> upload(@RequestParam("file") MultipartFile file) {
        try {
            String url = minioUtil.uploadFile(file);
            return Response.buildSuccess(url);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Response.buildError(ResStatusEnum.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名
     * @param response HTTP响应
     */
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) {
        try {
            minioUtil.downloadFile(fileName, response);
        } catch (IOException e) {
            log.error("文件下载失败", e);
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "文件下载失败");
            } catch (IOException ex) {
                log.error("发送错误响应失败", ex);
            }
        }
    }

    /**
     * 获取文件访问URL
     *
     * @param fileName 文件名
     * @return 文件访问URL
     */
    @GetMapping("/url/{fileName}")
    public Response<Object> getFileUrl(@PathVariable String fileName) {
        try {
            String url = minioUtil.getFileUrl(fileName);
            return Response.buildSuccess(url);
        } catch (Exception e) {
            log.error("获取文件URL失败", e);
            return Response.buildError(ResStatusEnum.FILE_DOWNLOAD_ERROR);
        }
    }
}
