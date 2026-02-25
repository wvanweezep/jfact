package com.wvanw.graphics;

import org.joml.Vector2i;
import org.joml.Vector4i;

public class Sprite {

    private final String name;
    private final Texture texture;
    private final Vector4i region;
    private final Vector2i size;

    public Sprite(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
        this.region = new Vector4i(0, 0, texture.width(), texture.height());
        this.size = new Vector2i(region.z, region.w);
    }

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
