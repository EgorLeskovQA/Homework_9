import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZipTest {
    private ClassLoader cl = JsonTest.class.getClassLoader();
    PDF pdfFile = null;
    XLS excelFile = null;
    CSVReader csvFile = null;

    @Test
    void pdfCheckTest() throws Exception {
        try (ZipInputStream zipFile = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;

            while ((entry = zipFile.getNextEntry()) != null) {
                if (entry.getName().endsWith("pdf.pdf")) {
                    pdfFile = new PDF(zipFile);
                    break;
                }
            }

            assertThat(pdfFile.numberOfPages).isEqualTo(3);
        }
    }

    @Test
    void csvCheckTest() throws Exception {
        try (ZipInputStream zipFile = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;

            while ((entry = zipFile.getNextEntry()) != null) {
                if (entry.getName().endsWith("csv.csv")) {
                    csvFile = new CSVReader(new InputStreamReader(zipFile));
                    break;
                }
            }

            List<String[]> csvData = csvFile.readAll();

            assertThat(csvData.get(0)).contains("Иванов Иван 54");
            assertThat(csvData.get(1)).contains("Васильев Николай 90");
            assertThat(csvData.get(2)).contains("Сидоров Алексей 25");
        }
    }

    @Test
    void xlsxCheckTest() throws Exception {
        try (ZipInputStream zipFile = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;

            while ((entry = zipFile.getNextEntry()) != null) {
                if (entry.getName().equals("xlsx.xlsx")) {
                    excelFile = new XLS(zipFile);
                    break;
                }
            }

            String firstActualValue = excelFile.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
            assertThat(firstActualValue).contains("Фамилия");
            String secondActualValue = excelFile.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
            assertThat(secondActualValue).contains("Имя");

        }
    }
}
