package com.ufc.br.util;


import org.apache.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImagemFile {

    public static void salvarImagem(String caminho, MultipartFile imagem) {

    	try{
    		FileOutputStream fileOutputStream = new FileOutputStream(new File(caminho));
			fileOutputStream.write(imagem.getBytes());
			fileOutputStream.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}

    }

    public static void deleteImagem(String caminho) {

        File file = new File(caminho);
        file.delete();

    }

}