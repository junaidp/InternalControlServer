package com.internal.control.helper;


import com.google.gson.Gson;
import com.internal.control.model.GLCodes;
import com.internal.control.repository.GLCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Component
public class GLcodesHelper {
    @Autowired
    MongoOperations mongoOperation;

    @Autowired
    GLCodeRepository glcoderepository;

    Gson gson = new Gson();
    public String uploadFile(MultipartFile excel) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(excel)) {
            try {
                List<GLCodes> glcodes = ExcelHelper.excelToglcodes(excel.getInputStream());
                glcoderepository.saveAll(glcodes);
                message = "Uploaded the file successfully: " + excel.getOriginalFilename();
                return message;
            } catch (Exception e) {
                message = "Could not upload the file: " + excel.getOriginalFilename() + e;
                return message;
            }
        }

        message = "Please upload an excel file!";
        return message;
    }
    public String deleteAllrecords(){
        try {
            glcoderepository.deleteAll();
            return "all records Deleted";
        }catch(Exception e)
        {
            return "could not delete records " + e;
        }
    }

    public String readFile() {

        try {
            List<GLCodes> glcodes = glcoderepository.findAll();

            if (glcodes.isEmpty()) {
                return "No content found";
            }
            String json = gson.toJson(glcodes);

            return json;
        } catch (Exception e) {
            return "Error " + e;
        }
    }

}
