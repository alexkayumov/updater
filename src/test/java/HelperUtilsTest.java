import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelperUtilsTest {

    HelperUtils helperUtils;

    public HelperUtilsTest() {
        this.helperUtils = new HelperUtils();
    }

    @Test
    public void unzipArchiveTest() throws Exception {
        Path archive = Paths.get(System.getProperty("base.dir"));
        System.out.println(archive.toString());
    }
}
