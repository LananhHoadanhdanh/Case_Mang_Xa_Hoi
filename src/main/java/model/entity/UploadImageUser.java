package model.entity;

import org.springframework.web.multipart.MultipartFile;

public class UploadImageUser {
    private MultipartFile[] files;

    public UploadImageUser() {
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
