/*
 * Thanks to http://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
 * for this great example
 */
package com.nicelogics.hellojetty.jackson;

/**
 * 6.3 To enable the @JsonView features, use the following methods :
 * mapper.writerWithView(“view class”).writeValue() mapper.readerWithView(“view
 * class”).readValue()
 *
 * @author Developer
 */
import com.nicelogics.hellojetty.structures.Staff;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson2Example {

    private String addTextLine(String text, String line)
    {
        return text + line + System.lineSeparator();
    }

    public String run() {

        ObjectMapper mapper = new ObjectMapper();
        Staff staff = createDummyObject();

        String retText = new String();

        try {

            // Salary will be hidden
            retText = addTextLine(retText, "Normal View");
            String normalView = mapper.writerWithView(Views.Normal.class).writeValueAsString(staff);
            retText = addTextLine(retText, normalView);

            String jsonInString = "{\"name\":\"mkyong\",\"age\":33,\"position\":\"Developer\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
            Staff normalStaff = mapper.readerWithView(Views.Normal.class).forType(Staff.class).readValue(jsonInString);
            retText = addTextLine(retText, normalStaff.toString());

            // Display everything
            retText = addTextLine(retText, "\nManager View");
            String managerView = mapper.writerWithView(Views.Manager.class).writeValueAsString(staff);
            retText = addTextLine(retText, managerView);

            Staff managerStaff = mapper.readerWithView(Views.Manager.class).forType(Staff.class).readValue(jsonInString);
            retText = addTextLine(retText, managerStaff.toString());

        } catch (JsonGenerationException e) {
            e.printStackTrace();
            retText = addTextLine(retText, e.getMessage());
        } catch (JsonMappingException e) {
            e.printStackTrace();
            retText = addTextLine(retText, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            retText = addTextLine(retText, e.getMessage());
        }
        
        return retText;
    }

    private Staff createDummyObject() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(33);
        staff.setPosition("Developer");
        staff.setSalary(new BigDecimal("7500"));

        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");

        staff.setSkills(skills);
        return staff;

    }
}
