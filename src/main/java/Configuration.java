import org.apache.log4j.Logger;

import java.io.File;
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

    /** Наименование файла с настройками */
    private static final String CONFIG_PROPERTIES = "config.properties";

    /** Вспомогательные обьекты */
    private static Logger log = Logger.getLogger(Configuration.class);

    private HelperUtils helper;

    /**
     * Инициализируем файл конфигурации
     */
    public Configuration() {
        helper = new HelperUtils();
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
        log.info("Начало инициализации файла c настройками приложения");
        try {
            Properties property = new Properties();
            Path workDir = Paths.get(System.getProperty("user.dir")).getParent().resolve("config");
            File configFile = helper.searchFile(workDir.toString(), CONFIG_PROPERTIES);
            if (configFile == null) {
                throw new FileNotFoundException();
            }
            emulatorPath = property.getProperty("emulatorPath");
            rainbowPath = property.getProperty("rainbowPath");
            archivesPath = property.getProperty("archivesPath");
            ibankPath = property.getProperty("ibankPath");
            ibankPrivatePath = property.getProperty("ibankPrivatePath");
            jrePath = property.getProperty("jrePath");
            log.info("Файл с настройками проинициализирован :\n" + this.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error(ex);
        }
    }

    @Override
    public String toString() {
        return "emulatorPath :" + emulatorPath + "\n" +
                "rainbowPath :" + rainbowPath + "\n" +
                "archivesPath :" + archivesPath + "\n" +
                "ibankPath :" + ibankPath + "\n" +
                "ibankPrivatePath :" + ibankPrivatePath + "\n" +
                "jrePath :" + jrePath;
    }
}
