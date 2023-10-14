package homeworkstream.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import homeworkstream.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StreamUtil {
    public static List<Person> getPersons() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(
                StreamUtil.class.getClassLoader().getResourceAsStream("persons.yaml"),
                mapper.getTypeFactory().constructCollectionType(List.class,Person.class)
        );
    }

    public static List<Date> getDates(int count){
        List<Date> dates = new ArrayList<>(count);
        for (int i = 0; i < count; i++){
            dates.add(new Date(ThreadLocalRandom.current().nextInt() * 1000L));
        }
        return dates;
    }
}
