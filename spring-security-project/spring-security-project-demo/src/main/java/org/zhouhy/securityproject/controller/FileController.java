package org.zhouhy.securityproject.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zhouhy.securityproject.dto.FileInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
public class FileController {

    @PostMapping(value="/file")
    public FileInfo uploadFile(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String path = "D:\\github";

        File localFile = new File(path,new Date().getTime()+".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }


    @GetMapping(value="/file/{id}")
    public void downFile(@PathVariable String id, HttpServletRequest request,HttpServletResponse response) throws IOException {
        String path = "D:\\github";
        try (
                InputStream inputStream = new FileInputStream(new File(path,id+".txt"));
                OutputStream outputStream = response.getOutputStream();
                ){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=text.txt");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();

        }
    }
}
