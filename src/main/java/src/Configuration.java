package src;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Класс содержащий пути и настройки сервера интеграции, эмулятора и т.д
 *
 * @author kayumov
 *         05.06.19 15:35
 */
public class Configuration {

    /** Директория где установлен сервисный эмулятор из файла config.properties*/
    private String emulatorPath;

    /** Директория расположения платформы интеграции из файла config.properties*/
    private String rainbowPath;

    /** Путь к директории где хранятся архивы для обновления из файла config.properties*/
    private String archivesPath;

    /** Путь к директории ibank*/
    private String ibankPath;

    /** Путь к директории ibank для физических лиц*/
    private String ibankPrivatePath;

    /** Путь к JRE */
    private String jrePath;

    /** Логгер */
    private static Logger log = Logger.getLogger(Configuration.class);

    /**
     * Инициализируем файл конфигурации
     */
    public Configuration() {
        initConfig();
    }

    public String getEmulatorPath() {
        return emulatorPath;
    }

    public String getRainbowPath() {
        return rainbowPath;
    }

    public String getArchivesPath() {
        return archivesPath;
    }

    public String getIbankPath(){
        return ibankPath;
    }

    public String getIbankPrivatePath() {
        return ibankPrivatePath;
    }

    public String getJrePath() {
        return jrePath;
    }

    /**
     * Инициализация данных из файла config.properties
     */
    private void initConfig() {
        log.info("Начало инициализации файла config.properties");
        try {
            Properties properties = new Properties();
            Path path = Paths.get("config.properties");
            if (!path.toFile().exists()) {
                throw new FileNotFoundException(path.toString());
            }
            properties.load(Configuration.class.getClassLoader().getResourceAsStream("config.properties"));
            emulatorPath = properties.getProperty("emulatorPath");
            rainbowPath = properties.getProperty("rainbowPath");
            archivesPath = properties.getProperty("downloadPath");
            ibankPath = properties.getProperty("ibankPath");
            ibankPrivatePath = properties.getProperty("ibankPrivatePath");
            jrePath = properties.getProperty("jrePath");
        } catch (IOException ex) {
            System.out.println("Ошибка при инициализации файла  : config.properties " + ex);
        }
    }

    @Override
    public String toString() {
        return "Configuration : \n" +
                "emulatorPath :" + emulatorPath + "\n" +
                "rainbowPath :" + rainbowPath + "\n" +
                "archivesPath :" + archivesPath + "\n" +
                "ibankPath :" + ibankPath + "\n" +
                "ibankPrivatePath :" + ibankPrivatePath + "\n" +
                "jrePath :" + jrePath;
    }
}
