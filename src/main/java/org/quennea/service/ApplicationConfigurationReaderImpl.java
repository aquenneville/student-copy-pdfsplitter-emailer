package org.quennea.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfigurationReaderImpl {

    public Properties read() {
        try (InputStream input = new FileInputStream(getClass().getClassLoader().getResource("application.properties").getFile())) {

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
