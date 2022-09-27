package com.e2e.utilities;

import com.e2e.config.ConfigurationManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * JSON test data handling utility.
 * Serialize and deserialize object to and from json string
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static ObjectMapper mapper = new ObjectMapper();
    static PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType("com.baeldung.jackson.inheritance")
            .allowIfSubType("java.util.ArrayList")
            .allowIfSubType("java.util.Arrays$ArrayList")
            .build();

    public JsonUtil(){}

    public static Object getObjectFromJsonString(String json){
        return Configuration.defaultConfiguration().jsonProvider().parse(json);
    }

    /**
     * Return value as object from Json document using Json path
     * @param doc		(Json) Object
     * @param jsonpath	Json path to required value/object
     * @return			Required value as object
     */
    public static Object readObjectFromJsonObject(Object doc, String jsonpath){
        return JsonPath.read(doc, jsonpath);
    }

    /**
     * Return object of generic class type from valid input json string!!!
     * @param input     Valid json string that contains object
     * @param typeKey   Class of some type
     * @return          Object of required type filled with values
     */
    public static <T> T getObjectFromJsonString(String input, Class<T> typeKey) {
        mapper.activateDefaultTyping(ptv);
        T obj = null;
        try{
            obj = mapper.readValue(input, typeKey);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * Return object of generic class type from valid input json string using JsonPath to parse info out of file
     * @param input     Valid json string that contains object
     * @param jpath     JsonPath expression for parsing json string that contains required json object/array
     * @param typeKey   Class of some type
     * @return          Object of required type filled with values
     */
    public static <T> T getObjectFromJsonStringAndPath(String input, String jpath, Class<T> typeKey){
        T obj = null;

        try{
            obj = JsonPath
                    .parse(input)
                    .read(jpath, typeKey);
        } catch (Exception e) { e.printStackTrace();	}

        return obj;
    }

    /**
     * Converts POJO class into json string
     * @param javaO Plain Old Java Object
     * @return  Object as json string
     */
    public static String convertPojoToJsonString(Object javaO) {
        String output="";
        mapper.activateDefaultTyping(ptv);

        try {
            output = mapper.writeValueAsString(javaO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Converts POJO class into json string
     * @param javaO Plain Old Java Object
     * @param ptv   PolymorphicTypeValidator requested by the library
     * @return      Object as json string
     */
    public static String convertPojoToJsonString(Object javaO, PolymorphicTypeValidator ptv) {
        String output="";

        mapper.activateDefaultTyping(ptv/*, ObjectMapper.DefaultTyping.NON_FINAL*/);

        try {
            output = mapper.writeValueAsString(javaO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Load json test data from json file with the same name as test-method name
     * @param methodName    Method name used as to load test data json with the same name
     * @return              Returns json test data as string
     */
    public static String loadJsonData(Method methodName) {

        String OS = System.getProperty("os.name").toLowerCase();
        String separator = (OS.indexOf("win")>=0) ? "\\" : "/";
        String path = (OS.indexOf("win")>=0)
                ? ConfigurationManager.configuration().path2testData()
                : ConfigurationManager.configuration().path2testData().replaceAll("\\\\", "/");

        String currentWorkingDir = System.getProperty("user.dir");
        String fileName = currentWorkingDir + separator +
                path + methodName.getName() + ".json";
        String jsonData = IOUtils.loadFileToString(fileName);

        return jsonData;
    }

}
