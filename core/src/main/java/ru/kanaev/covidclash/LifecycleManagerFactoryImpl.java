package ru.kanaev.covidclash;

import ru.kanaev.covidclash.lifecycle.LifecycleManagerImpl;
import ru.kanaev.covidclash.lifecycle.api.LifecycleConfiguration;
import ru.kanaev.covidclash.lifecycle.api.LifecycleManager;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LifecycleManagerFactoryImpl implements LifecycleManagerFactory {

    private static final String LIFECYCLE_MAPPING_PROPERTIES = "lifecycle-mapping.properties";

    private final Properties properties;
    private final Map<String, LifecycleManager> lifecycleManagers;

    public LifecycleManagerFactoryImpl() {
        properties = new Properties();
        lifecycleManagers = new HashMap<>();
        init();
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private void init() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream resourceStream = loader.getResourceAsStream(LIFECYCLE_MAPPING_PROPERTIES)) {
            properties.load(resourceStream);
        }
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            LifecycleConfiguration config = (LifecycleConfiguration)Class
                    .forName((String) entry.getValue())
                    .getConstructor()
                    .newInstance();

            lifecycleManagers.put((String)entry.getKey(), new LifecycleManagerImpl(config));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, S, E> LifecycleManager<S, E> newInstance(Class<T> forClass) {
        return lifecycleManagers.get(forClass.getCanonicalName());
    }
}
