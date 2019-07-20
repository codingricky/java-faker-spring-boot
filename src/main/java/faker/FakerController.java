package faker;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class FakerController {
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String index(@RequestParam(defaultValue="US") String locale, Model model) {
        model.addAttribute("locales", Arrays.asList(Locale.US.getCountry(),
                Locale.FRANCE.getCountry(),
                Locale.GERMANY.getCountry()));

        Faker faker = new Faker(new Locale(locale));
        List<FakerObject> fakerObjects = Arrays.asList(faker.name(),
                faker.address(),
                faker.phoneNumber(),
                faker.ancient(),
                faker.app(),
                faker.artist(),
                faker.avatar(),
                faker.chuckNorris(),
                faker.harryPotter(),
                faker.superhero()).stream().map(FakerController::convertTo).collect(Collectors.toList());
        model.addAttribute("values", fakerObjects);
        return "faker.html";
    }

    private static FakerObject convertTo(Object fakerObjectWithValues) {
        List<FakerField> fields = Arrays.stream(fakerObjectWithValues.getClass().getDeclaredMethods()).map(m -> {
            if (m.getReturnType().equals(String.class)) {
                try {
                    String value = m.invoke(fakerObjectWithValues, null).toString();
                    return new FakerField(fakerObjectWithValues.getClass().getSimpleName() + "." + m.getName(), value);
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        }).filter(field -> field != null).collect(Collectors.toList());
        return new FakerObject(fakerObjectWithValues.getClass().getSimpleName(), fields);
    }
}
