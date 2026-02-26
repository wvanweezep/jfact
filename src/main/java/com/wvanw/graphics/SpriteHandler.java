package com.wvanw.graphics;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class SpriteHandler {

    private static SpriteHandler instance;
    private final HashMap<String, Sprite> registry = new HashMap<>();

    public static SpriteHandler getInstance() {
        if (instance == null) instance = new SpriteHandler();
        return instance;
    }

    public Sprite get(String name) {
        if (!registry.containsKey(name)) throw new NoSuchElementException(
                "Could not find a Sprite registered under the name: " + name);
        return registry.get(name);
    }

    public Sprite register(Sprite sprite) {
        if (sprite == null) throw new IllegalArgumentException(
                "Cannot register null to the SpriteHandler");
        if (registry.containsKey(sprite.name())) throw new IllegalArgumentException(
                "Cannot register Sprite under the name '" + sprite.name() + "' since it is already in use");
        registry.put(sprite.name(), sprite);
        return sprite;
    }
}
