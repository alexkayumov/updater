import org.junit.Assert;
import org.junit.Test;
import src.UpdaterFileUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Junit ����� ������ FileUtilsTests
 */
public class FileUtilsTests extends TestClassHelper {

    /** ���������� ��� ����� ����� ��� ������ */
    private static final Path FILES =
            Paths.get(System.getProperty("user.dir")).resolve("target/test-classes/");

    /** ������������ ����� ������ */
    private static final String ZIP_FILE_NAME = "Upd20x-2.0.24.580.zip";

    /**
     * ����� ����� � ����������� zip
     */
    @Test
    public void searchZipTest() {
        String fileName = "Upd.+zip";
        File file = UpdaterFileUtils.searchFile(FILES.toString(), fileName);
        Assert.assertNotNull(file);
        Assert.assertEquals(ZIP_FILE_NAME, file.getName());
    }

    /**
     * ����� ����� xml
     */
    @Test
    public void searchXmlTest() {
        String fileName = "connection.xml";
        File file = UpdaterFileUtils.searchFile(FILES.toString(), fileName);
        Assert.assertNotNull(file);
        Assert.assertEquals(fileName, file.getName());
    }

    /**
     * ���������� ������
     */
    @Test
    public void unzipArchiveTest() throws Exception {
        String destinationPath = FILES.resolve("UNZIP").toString();
        UpdaterFileUtils.unzipArchive(FILES.resolve(ZIP_FILE_NAME).toString(), destinationPath);
    }
}
