package faker;

import com.github.javafaker.Faker;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class FakerUtil {
    private static final Set<String> METHODS_TO_EXCLUDE = new HashSet<>(Arrays.asList("fakeValuesService"));

    public static List<FakerObject> getFakerObjects(Faker faker) {
        Method[] declaredMethods = faker.getClass().getDeclaredMethods();
        return Arrays.stream(declaredMethods).sorted(Comparator.comparing(Method::getName)).filter(m -> {
            boolean isGetterMethod = m.getName().startsWith("get");
            boolean hasNoArguments = m.getParameterCount() == 0;
            return !isGetterMethod && hasNoArguments && !METHODS_TO_EXCLUDE.contains(m.getName());
        }).map(m -> {
            try {
                return m.invoke(faker, null);
            } catch (Exception e) {
                System.out.println(m);
                e.printStackTrace();
            }
            return null;
        }).map(FakerUtil::convertTo).collect(Collectors.toList());
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
