package src;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static src.UpdaterFileUtils.getTagValue;
import static src.UpdaterFileUtils.getXmlDocument;

/**
 * Класс инициализирующий файл с настройками приложения
 */
public class SettingsXmlInit {

    /** Логгер */
    private Logger log = Logger.getLogger(SettingsXmlInit.class);

    /** Путь к серверу приложений */
    private String pathIbank;

    /** Путь к директории где лежит архив обновления */
    private String pathUpdater;

    /** Путь к JRE */
    private String pathJava;

    /** Текущая директория приложения */
    private static final Path APPLICATION_DIR = Paths.get(System.getProperty("user.dir")).toAbsolutePath().getParent();

    /**
     * Инициализация файла settings.xml
     */
    SettingsXmlInit(){
        parseSettingsXml();
    }

    /**
     * Парсит файл settings.xml
     */
    private void parseSettingsXml() {
        String attribyteName = "path";
        log.debug("Начинаем разбор файла settings.xml");
        try {
            File settingsXml = getSettingXml();
            Document document = getXmlDocument(settingsXml);
            pathIbank = getTagValue(document, "path-ibank", attribyteName);
            pathUpdater = getTagValue(document, "path-update", attribyteName);
            pathJava = getTagValue(document, "path-jre", attribyteName);
        } catch (Exception ex) {
            log.error("При разборе файла возникли ошибки \n" + ex);
        }
        log.debug("Разбор файла окончен (path-ibank = " + pathIbank + " path-update = " + pathUpdater + " path-jre = " + pathJava + ")");
    }

    /**
     * @return файл settings.xml
     */
    private File getSettingXml() throws Exception {
        File settingsFile = Paths.get(APPLICATION_DIR.toString(), "resources", "config", "settings.xml").toFile();
        if (settingsFile == null) {
            throw new FileNotFoundException("Файл settings.xml не найден (\" + settingsFile + )");
        }
        return settingsFile;
    }

    public String getPathIbank() {
        return pathIbank;
    }

    public void setPathIbank(String pathIbank) {
        this.pathIbank = pathIbank;
    }

    public String getPathUpdater() {
        return pathUpdater;
    }

    public void setPathUpdater(String pathUpdater) {
        this.pathUpdater = pathUpdater;
    }

    public String getPathJava() {
        return pathJava;
    }

    public void setPathJava(String pathJava) {
        this.pathJava = pathJava;
    }
}
