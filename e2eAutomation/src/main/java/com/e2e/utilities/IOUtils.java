package com.e2e.utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class IOUtils {

    public IOUtils(){}

    /**
     * Save string variable to file
     * @param strToSave		String content for saving
     * @param fileName		Just file name. Make sure to pass full path to the file
     * @throws IOException	Ex
     */
    public static void saveStringToFile(String strToSave, String fileName){
        try {
            FileUtils.writeStringToFile(new File(fileName), strToSave,  Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load content of required file into string variable
     * @param fileName	Just file name. Make sure to pass full path to the file
     * @return
     */
    public static String loadFileToString(String fileName){
        String content = "";
        try {
            content = FileUtils.readFileToString(new File(fileName), Charset.defaultCharset());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return content;
    }

}
