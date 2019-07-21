package faker;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Locale;

import static faker.FakerUtil.getFakerObjects;

@Controller
public class FakerController {
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String index(@RequestParam(defaultValue="US") String locale, Model model) {
        model.addAttribute("locales", Arrays.asList(Locale.US.getCountry(),
                Locale.FRANCE.getCountry(),
                Locale.GERMANY.getCountry()));

        Faker faker = new Faker(new Locale(locale));
        model.addAttribute("values", getFakerObjects(faker));
        return "faker.html";
    }
}
