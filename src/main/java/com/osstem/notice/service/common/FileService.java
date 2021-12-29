package com.osstem.notice.service.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    String saveFile(MultipartFile file) throws IOException;

    void deleteFile(String fileName);
}
