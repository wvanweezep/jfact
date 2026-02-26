package com.wvanw.graphics;

import org.joml.Vector2i;
import org.joml.Vector4i;

/**
 * Class storing a view to an initialized {@link Texture} linked to a unique name.
 */
public class Sprite {

    /**
     * Alias for the Sprite under which it is registered.
     */
    private final String name;
    /**
     * Source Texture used by the Sprite.
     */
    private final Texture texture;
    /**
     * Subsection of the Texture defining the Sprite.
     */
    private final Vector4i region;
    /**
     * Size of the Sprite in pixels.
     */
    private final Vector2i size;

    /**
     * Creates a new Sprite of the full {@link Texture}.
     * @param name alias for the Sprite
     * @param texture source texture of the Sprite
     */
    public Sprite(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
        this.region = new Vector4i(0, 0, texture.width(), texture.height());
        this.size = new Vector2i(region.z, region.w);
    }

    /**
     * Creates a new Sprite as a subsection of a {@link Texture}.
     * @param name alias for the Sprite
     * @param texture source texture of the Sprite
     * @param region subsection of the Texture defining the Sprite
     */
    public Sprite(String name, Texture texture, Vector4i region) {
        this.name = name;
        this.texture = texture;
        this.region = region;
        this.size = new Vector2i(region.z, region.w);
    }

    public String name() {
        return name;
    }

    public Texture texture() {
        return texture;
    }

    public Vector2i size() {
        return size;
    }

    public int width() {
        return region.z;
    }

    public int height() {
        return region.w;
    }

    public float u0() {
        return region.x / (float) texture.width();
    }

    public float v0() {
        return region.y / (float) texture.height();
    }

    public float u1() {
        return (region.x + region.z) / (float) texture.width();
    }

    public float v1() {
        return (region.y + region.w) / (float) texture.height();
    }

    @Override
    public String toString() {
        return "Sprite(" + name + ")";
    }
}
