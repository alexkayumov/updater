import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс содержащий общие вспомогательные методы
 *
 * @author kayumov
 *         05.06.19 15:45
 */
public class HelperUtils {

    /** Логгер */
    private static Logger log = Logger.getLogger(HelperUtils.class);

    /**
     * @return поиск файла по регулярному выражению
     */
    public File searchFile(String searchDir, String regExp) {
        File[] listFiles = new File(searchDir).listFiles();
        RegexFileFilter fileFilter = new RegexFileFilter(regExp);
        if (listFiles != null) {
            for (File file : listFiles) {
                if (fileFilter.accept(file)) {
                    return new File(file.getAbsolutePath());
                }
            }
        }
        return null;
    }

    /**
     * Поиск файлов по регулярному выражению
     *
     * @param searchDir директория в которой ведется поиск
     * @param regExp выражению о которому ищутся файлы
     * @return список найденных файлов
     */
    public List<File> searchFiles(String searchDir, String regExp) {
        System.out.println("===================" + searchDir);
        File[] listFiles = new File(searchDir).listFiles();
        List<File> searchFiles = new ArrayList<>();
        RegexFileFilter fileFilter = new RegexFileFilter(regExp);
        if (listFiles != null) {
            for (File file : listFiles) {
                if (fileFilter.accept(file)) {
                    searchFiles.add(file);
                }
            }
        }
        return searchFiles;
    }

    /**
     * Поиск архивов в указанной директории. Сортировка и возврат последней версии.
     *
     * @param archiveDirectory директория в которой ведется поиск архивов
     * @param regExp выражение для поиска архива
     * @return последняя версия архивного update
     */
    public File getArchive(String archiveDirectory, String regExp) throws FileNotFoundException {
        List<File> fileList = searchFiles(archiveDirectory, regExp);
        if (fileList.isEmpty()) {
            throw new FileNotFoundException("Отсутсвуют архивы для обновления.  " + archiveDirectory);
        }
        log.debug("Найдено файлов :" + fileList);
        Collections.reverse(fileList);
        return fileList.get(0);
    }


    /**
     * Распаковщик архивов
     *
     * @param archivePath полный путь до архива
     * @param destination путь куда будут распаковываться файлы
     */
    public void unzipArchive(String archivePath, String destination) {
        File file = new File(archivePath);
        log.debug("Распаковка архива " + file.getName());
        try {
            ZipFile zipFile = new ZipFile(file);
            zipFile.extractAll(destination);
            log.debug("Распаковка архива " + file.getName() + " в директорию " + destination + " завершена");
        } catch (ZipException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Очищает указаную директорию
     *
     * @param path путь до диретокрии
     */
    public void cleanDir(String path) {
        File[] files = new File(path).listFiles();
        log.debug("Очистка директории : " + path);
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    cleanDir(f.getPath());
                }
                f.delete();
            }
        }
        log.debug("Очистка завершена");
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
     * @param document XML документ
     * @param nameElement  наименание тэга
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
