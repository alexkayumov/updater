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
 * ����� ���������� ����� ������ ��� ������ � �������
 */
public class UpdaterFileUtils {

    /** ������ */
    Logger log = Logger.getLogger(UpdaterFileUtils.class);

    /**
     * ����������� �������
     *
     * @param archive ������ ���� �� ������
     * @param destination ���� ���� ����� ��������������� �����
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
     * ��������� ����� �����
     *
     * @param pathDirectory ���������� ��� ������������ ����� �����
     * @param fileName ������������ ����� ��� ������������ �� ����������� ���������
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
     * ��������� ������ xml ����� � ������ Document
     *
     * @param xmlFile xml ���� ��� ���������� ��������
     * @return ������ Document � ������� xml �����
     */
    public static Document getXmlDocument(File xmlFile) throws Exception {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = f.newDocumentBuilder();
        return builder.parse(xmlFile.toString());
    }

    /**
     * ��������� ����� � ���������� �������� ����� �� ����� XML
     *
     * @param document XML ��������
     * @param nameElement ���������� ����
     * @param nameAttribyte ����������� ��������
     * @return �������� ��������
     */
    public static String getTagValue(Document document,
                                     String nameElement,
                                     String nameAttribyte) throws Exception {
        Node nodeElement = document.getElementsByTagName(nameElement).item(0);
        if (nodeElement == null) {
            throw new Exception("�� ������ ��� :" + nameElement);
        }
        Node nodeAttribyte = nodeElement.getAttributes().getNamedItem(nameAttribyte);
        if (nodeAttribyte == null) {
            throw new Exception("�� ������ ������� :" + nameAttribyte);
        }
        return nodeAttribyte.getNodeValue();
    }
}
