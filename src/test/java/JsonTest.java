import com.fasterxml.jackson.databind.ObjectMapper;
import model.Items;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonTest {

    private ClassLoader cl = JsonTest.class.getClassLoader();


    @Test
    void jsonTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("test.json")
        )) {
            ObjectMapper mapper = new ObjectMapper();
            Items items = mapper.readValue(reader, Items.class);

            assertThat(items.getItems().get(0).getNumber()).isEqualTo(1);
            assertThat(items.getItems().get(0).getName()).isEqualTo("Андрей");
            assertThat(items.getItems().get(0).getAge()).isEqualTo(25);

            assertThat(items.getItems().get(1).getNumber()).isEqualTo(2);
            assertThat(items.getItems().get(1).getName()).isEqualTo("Саша");
            assertThat(items.getItems().get(1).getAge()).isEqualTo(90);

            assertThat(items.getItems().get(2).getNumber()).isEqualTo(3);
            assertThat(items.getItems().get(2).getName()).isEqualTo("Алексей");
            assertThat(items.getItems().get(2).getAge()).isEqualTo(30);
        }
    }
}
