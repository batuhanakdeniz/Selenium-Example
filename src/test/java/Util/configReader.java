package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
    public static Properties getPropertyObject() throws IOException {
        String projectPath = System.getProperty("user.dir");
        FileInputStream fp = new FileInputStream(projectPath +"/src/test/resources/config/config.properties");

        Properties prop = new Properties();
        prop.load(fp);
        return prop;
    }

    public static String getUserName() throws IOException{
        return getPropertyObject().getProperty("username");
    }

    public static String getPassword() throws IOException{
        return getPropertyObject().getProperty("password");
    }
}
