package utils;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Utility class to load environment variables using the Dotenv library.
 */
public class EnvLoader {

    // Loads the .env file into a Dotenv instance
    private static final Dotenv dotenv = Dotenv.load();

    /**
     * Retrieves the value of an environment variable by its key.
     *
     * @param key The key of the environment variable to retrieve.
     * @return The value of the environment variable, or null if not found.
     */
    public static String getEnv(String key) {
        return dotenv.get(key);
    }
}
