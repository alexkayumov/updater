package src;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import java.io.File;
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
    SettingsXmlInit() {
        parseSettingsXml();
    }

    /**
     * Парсит файл settings.xml
     */
    private void parseSettingsXml() {
        String attribyteName = "path";
        log.debug("Начинаем разбор файла settings.xml");
        try {
            File settingsXml = Paths.get(APPLICATION_DIR.toString(), "config", "settings.xml").toFile();
            Document document = getXmlDocument(settingsXml);
            pathIbank = getTagValue(document, "path-ibank", attribyteName);
            checkTag(pathIbank);

            pathUpdater = getTagValue(document, "path-update", attribyteName);
            checkTag(pathUpdater);

            pathJava = getTagValue(document, "path-jre", attribyteName);
            checkTag(pathJava);

            log.debug("Разбор файла окончен (path-ibank = " + pathIbank + " path-update = " + pathUpdater + " path-jre = " + pathJava + ")");
        } catch (Exception ex) {
            log.error("При разборе файла возникли ошибки:" + ex);
        }
    }

    /**
     * Проверка заполнения тегов
     *
     * @param name наименование тега
     */
    private void checkTag(String name) throws Exception {
        if (name.equals("")) {
            throw new Exception("Не заполнен тег " + name);
        }
    }

    public String getPathIbank() {
        return pathIbank;
    }

    public String getPathUpdater() {
        return pathUpdater;
    }

    public String getPathJava() {
        return pathJava;
    }
}
