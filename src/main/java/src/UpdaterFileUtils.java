package src;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Класс содержащий общие методы для работы с файлами
 */
public class UpdaterFileUtils {

    /** Логгер */
    private static Logger log = Logger.getLogger(UpdaterFileUtils.class);

    /**
     * Распаковщик архивов
     *
     * @param archive полный путь до архива
     * @param destination путь куда будут распаковываться файлы
     */
    public static void unzipArchive(String archive, String destination) {
        File file = new File(archive);
        try {
            ZipFile zipFile = new ZipFile(file);
            zipFile.extractAll(destination);
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
    public static File searchFile(String pathDirectory, String fileName) {
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
     * Считывает данные xml файла в обьект Document
     *
     * @param xmlFile xml файл для дальнейшео парсинга
     * @return обьект Document с данными xml файла
     */
    public static Document getXmlDocument(File xmlFile) throws Exception {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = f.newDocumentBuilder();
        return builder.parse(xmlFile.toString());
    }

    /**
     * Выполняет поиск и возвращает значения тегов из файла XML
     *
     * @param document XML документ
     * @param nameElement наименание тэга
     * @param nameAttribyte наименовние атрибута
     * @return значение атрибута
     */
    public static String getTagValue(Document document,
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
