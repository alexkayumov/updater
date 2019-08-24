import org.apache.log4j.Logger;

import java.io.*;
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

    /** Директория где установлен сервисный эмулятор из файла config.properties */
    private String emulatorPath;

    /** Директория расположения платформы интеграции из файла config.properties */
    private String rainbowPath;

    /** Путь к директории где хранятся архивы для обновления из файла config.properties */
    private String archivesPath;

    /** Путь к директории ibank */
    private String ibankPath;

    /** Путь к директории ibank для физических лиц */
    private String ibankPrivatePath;

    /** Путь к JRE */
    private String jrePath;

    /** Кастомный путь к архиву эмулятора */
    private String customEmulatorPath;

    /** Наименование файла с настройками */
    private static final String CONFIG_PROPERTIES = "config.properties";

    /** Вспомогательные обьекты */
    private static Logger log = Logger.getLogger(Configuration.class);
    private HelperUtils helper;

    /**
     * Инициализируем файл конфигурации
     * @param applicationPath местонахождение приложения
     */
    public Configuration(String applicationPath) {
        helper = new HelperUtils();
        initConfig(applicationPath);
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

    public String getIbankPath() {
        return ibankPath;
    }

    public String getIbankPrivatePath() {
        return ibankPrivatePath;
    }

    public String getJrePath() {
        return jrePath;
    }

    public String getCustomEmulatorPath() {
        return customEmulatorPath;
    }

    /**
     * Инициализация данных из файла config.properties
     */
    private void initConfig(String applicationPath) {
        log.info("Начало инициализации файла config.properties");
        try {
            //Path applcationDir = Paths.get(applicationPath).resolve("config");
            Path applcationDir = Paths.get(applicationPath);
            File configFile = helper.searchFile(applcationDir.toString(), CONFIG_PROPERTIES);
            if (configFile == null) {
                throw new FileNotFoundException("Не найден файл конфигурации. ");
            }
            Properties property = new Properties();
            property.load(new FileReader(configFile));
            emulatorPath = property.getProperty("emulatorPath");
            rainbowPath = property.getProperty("rainbowPath");
            archivesPath = property.getProperty("archivesPath");
            ibankPath = property.getProperty("ibankPath");
            ibankPrivatePath = property.getProperty("ibankPrivatePath");
            jrePath = property.getProperty("jrePath");
            customEmulatorPath = property.getProperty("customEmulatorPath");
            log.info("\n" + this.toString() + "\nКонец инициализации.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public String toString() {
        return "emulatorPath :" + emulatorPath + "\n" +
                "rainbowPath :" + rainbowPath + "\n" +
                "archivesPath :" + archivesPath + "\n" +
                "ibankPath :" + ibankPath + "\n" +
                "ibankPrivatePath :" + ibankPrivatePath + "\n" +
                "jrePath :" + jrePath +"\n" +
                "customEmulatorPath :" + customEmulatorPath;
    }
}
