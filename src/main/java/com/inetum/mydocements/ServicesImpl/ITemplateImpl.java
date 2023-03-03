package com.inetum.mydocements.ServicesImpl;

import com.deepoove.poi.XWPFTemplate;
import com.inetum.mydocements.Entity.Template;
import com.inetum.mydocements.Entity.Type;
import com.inetum.mydocements.Repository.TemplateDao;
import com.inetum.mydocements.Services.ITemplate;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.*;

@Service
public class ITemplateImpl implements ITemplate {
    private final String IMAGE_DIR = "C:/xampp/htdocs/img/";

    @Autowired
    TemplateDao repo;


    @Override
    public Template UploadeFile(MultipartFile file) throws IOException {
        Template template = new Template();
        template.setNom(file.getOriginalFilename());
        template.setPath(IMAGE_DIR+template.getNom());
        template.setDateDeCreation(new Date());
        if (template.getNom().contains("pdf"))
            template.setTypeDoc(Type.pdf);
        else
            template.setTypeDoc(Type.docx);

        byte[] fileContent = file.getBytes();

        // check if file size is greater than 5000 bytes
        if (fileContent.length > 5000) {
            // resize the byte array to 5000 bytes
            fileContent = Arrays.copyOf(fileContent, 5000);
        }

        template.setContenu(fileContent);



          File dest = new File(IMAGE_DIR + template.getNom());
        try {
            file.transferTo(dest);

            return  this.repo.save(template);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean DownloadFileName(String path) {



        return null;
    }

    @Override
    public void ImplementTheDocument(String nom, Map<String , Object> variables ) throws IOException, DocumentException {
        Template template = this.repo.findByNom(nom);
        String filePath = template.getPath();
        //Convert the dto to a Map (this map will be the input of the Document)
        //Implementing with the Map
        XWPFTemplate xwpfTemplate = XWPFTemplate.compile(filePath);
        xwpfTemplate.render(variables);
       xwpfTemplate.writeToFile(IMAGE_DIR+"new "+template.getNom());
        Document pdfDocument = new Document();
        PdfWriter.getInstance(pdfDocument, new FileOutputStream(IMAGE_DIR + "new " + template.getNom() + ".pdf"));
        pdfDocument.open();
        // Iterate over the paragraphs in the XWPFTemplate and add them to the PDF document
        for (XWPFParagraph paragraph : xwpfTemplate.getXWPFDocument().getParagraphs()) {
            pdfDocument.add(new Paragraph(paragraph.getText()));
        }
        // Close the PDF document and XWPFTemplate
        pdfDocument.close();
        xwpfTemplate.close();
    }









}
