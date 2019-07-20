package faker;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class FakerController {
    @GetMapping("/index")
    public String index(Model model) {
        Faker faker = new Faker();
        FakerField firstName = new FakerField("First name", faker.name().firstName());
        FakerField surname = new FakerField("Surname", faker.name().lastName());
        FakerField address = new FakerField("Address", faker.address().fullAddress());
        FakerField phoneNumber = new FakerField("Phone number", faker.phoneNumber().phoneNumber());

        model.addAttribute("values", Arrays.asList(firstName, surname, address, phoneNumber));

        return "faker.html";
    }

}
