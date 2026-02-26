package com.wvanw.graphics;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Class handling the organized storage and retrieval of {@link Texture} to ensure proper resource management.
 */
public class TextureHandler {

    /**
     * Singleton instance of the handler class.
     */
    private static TextureHandler instance;
    /**
     * Hashmap mapping the texture alias to its instance.
     */
    private final HashMap<String, Texture> registry = new HashMap<>();

    /**
     * Getter for the instance of the Handler.
     * @return instance of the TextureHandler.
     */
    public static TextureHandler getInstance() {
        if (instance == null) instance = new TextureHandler();
        return instance;
    }

    /**
     * Retrieves the {@link Texture} registered to the provided alias.
     * @param name alias the Texture is registered to
     * @return Texture with the provided alias.
     * @throws NoSuchElementException if no Texture is registered with the provided alias.
     */
    public Texture get(String name) {
        if (!registry.containsKey(name)) throw new NoSuchElementException(
                "Could not find a Texture registered under the name: " + name);
        return registry.get(name);
    }

    /**
     * Registers a new {@link Texture} under a provided alias.
     * @param texture instance of a Texture to register
     * @return Texture registered to the registry.
     * @throws IllegalArgumentException when the provided Texture instance is null,
     * or another Texture is already registered to the provided alias.
     */
    public Texture register(Texture texture) {
        if (texture == null) throw new IllegalArgumentException(
                "Cannot register null to the TextureHandler");
        if (registry.containsKey(texture.name())) throw new IllegalArgumentException(
                "Cannot register Texture under the name '" + texture.name() + "' since it is already in use");
        registry.put(texture.name(), texture);
        return texture;
    }

    /**
     * Destroys all registered Textures and clears the registry, freeing all resources.
     */
    public void destroy() {
        registry.forEach((_, v) -> v.destroy());
        registry.clear();
    }
}
