package com.aaa.chp.controller;

import com.aaa.chp.common.Result;
import com.aaa.chp.utils.UploadFileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

/**
 * Created by zts on 2020/3/23.
 */
@RestController
public class FileController {

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, String filename){
        Result s = new Result("1", "success");
        String usrHome = System.getProperty("user.home");
        try {
            String path = usrHome+"/image/";
            path = path.replace("\\","/");
            System.out.println("path="+path);
            File f = new File(path);
            if(!f.exists()){
                f.mkdirs();
            }
            UploadFileUtils.uploadFileTest(file,path,filename);
        }catch (Exception e){
            s.setError("0","失败");
        }
        return s ;
    }

}
