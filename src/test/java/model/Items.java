package model;

import java.util.List;

public class Items {
    private List<Items.items> items;

    public List<Items.items> getItems() {
        return items;
    }

    public static class items {
        private Integer number;
        private String name;
        private Integer age;

        public Integer getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
}
