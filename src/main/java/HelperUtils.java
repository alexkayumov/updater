import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс содержащий общие вспомогательные методы
 *
 * @author kayumov
 *         05.06.19 15:45
 */
public class HelperUtils {

    /**
     * Логгер
     */
    private static Logger log = Logger.getLogger(HelperUtils.class);

    /**
     * @return поиск файла по регулярному выражению
     */
    public File searchFile(String searchDir, String regExp) {
        File[] listFiles = new File(searchDir).listFiles();
        Pattern pattern = Pattern.compile(regExp);
        if (listFiles != null) {
            for (File file : listFiles) {
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.find()) {
                    return new File(file.getAbsolutePath());
                }
            }
        }
        return null;
    }

    /**
     * Распаковщик архивов
     *
     * @param archivePath полный путь до архива
     * @param destination путь куда будут распаковываться файлы
     */
    public void unzipArchive(String archivePath, String destination) {
        File file = new File(archivePath);
        log.info("Начало распаковки архива " + file);
        try {
            net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile(file);
            zipFile.extractAll(destination);
            log.info("Распаковка архива " + file + " в директорию " + destination + " завершена");
        } catch (ZipException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Выполняет поиск файла
     *
     * @param pathDirectory директория где производится поиск файла
     * @param fileName наименование файла или наименование по регулярному выражению
     */
    public static File searchFileTest(String pathDirectory, String fileName) {
        File fileDir = new File(pathDirectory);
        if (fileDir.isDirectory()) {
            return FileUtils.listFiles(new File(pathDirectory), null, true)
                    .stream()
                    .filter(f -> f.getName().matches(fileName))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }

    /**
     * Очищает указаную директорию
     *
     * @param path путь до диретокрии
     */
    public void cleanDir(String path) {
        File[] files = new File(path).listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    cleanDir(f.getPath());
                }
                f.delete();
            }
        }
    }

    /**
     * Считывает данные xml файла в обьект Document
     *
     * @param xmlFile xml файл для дальнейшео парсинга
     * @return обьект Document с данными xml файла
     */
    public Document getXmlDocument(File xmlFile) throws Exception {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = f.newDocumentBuilder();
        return builder.parse(xmlFile.toString());
    }

    /**
     * Выполняет поиск и возвращает значения тегов из файла XML
     *
     * @param document      XML документ
     * @param nameElement   наименание тэга
     * @param nameAttribyte наименовние атрибута
     * @return значение атрибута
     */
    public String getTagValue(Document document,
                              String nameElement,
                              String nameAttribyte) throws Exception {
        Node nodeElement = document.getElementsByTagName(nameElement).item(0);
        if (nodeElement == null) {
            throw new Exception("Не найден тег :" + nameElement);
        }
        Node nodeAttribyte = nodeElement.getAttributes().getNamedItem(nameAttribyte);
        if (nodeAttribyte == null) {
            throw new Exception("Не найден атрибут :" + nameAttribyte);
        }
        return nodeAttribyte.getNodeValue();
    }
}
