package com.inetum.mydocements.Web;

 import com.inetum.mydocements.Dto.DocumentVariables;
 import com.inetum.mydocements.Entity.Template;
 import com.inetum.mydocements.Repository.TemplateDao;
import com.inetum.mydocements.Services.ITemplate;
 import com.itextpdf.text.DocumentException;
 import org.apache.tomcat.util.http.fileupload.FileUploadException;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.MediaType;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.*;

 import java.io.IOException;
 import java.util.Map;

 import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/template")
public class TemlateController {


    @Autowired
    ITemplate services;


@Autowired
TemplateDao repo;

    @PostMapping("implementing/{nom}")
    public void Hello(@PathVariable String nom , @RequestBody Map<String , Object> variables) throws IOException, DocumentException {
     this.services.ImplementTheDocument(nom,variables);
     System.out.println("doneeeeeeeeeeeeeeeeeeeeeeeeeee");
    }
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Template Uploadfile(@RequestParam("file") MultipartFile file)throws IOException, FileUploadException
    {
       return this.services.UploadeFile(file);
    }











}
