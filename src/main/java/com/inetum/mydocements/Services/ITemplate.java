package com.inetum.mydocements.Services;

import com.inetum.mydocements.Dto.DocumentVariables;
import com.inetum.mydocements.Entity.Template;
import com.itextpdf.text.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ITemplate {

    public Template UploadeFile(MultipartFile file) throws IOException;

    public Boolean DownloadFileName(String path);


    public void ImplementTheDocument(String nom ,  Map<String , Object> variables) throws IOException, DocumentException;



}
