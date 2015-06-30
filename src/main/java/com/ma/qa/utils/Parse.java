package com.ma.qa.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Anson on 16/12/14.
 */
public class Parse {

    public static HashMap Yaml(String yamlFilePath) throws IOException {
        //check yaml file path string null or empty
        if ((yamlFilePath == null) || yamlFilePath.trim().equals(""))
            throw new IOException("YAML file path should not be empty or null.");
        //check yaml file exist or not
        if (!(new File(yamlFilePath)).exists())
            throw new IOException("YAML file path was not found. [" + yamlFilePath + "]");

        return (HashMap) ((new Yaml()).load(new FileInputStream(new File(yamlFilePath))));
    }
}
