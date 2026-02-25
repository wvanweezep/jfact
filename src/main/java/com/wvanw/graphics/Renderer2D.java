package com.wvanw.graphics;

public class Renderer2D {

    private final SpriteBatch batch;

    public Renderer2D() {
        batch = new SpriteBatch();
    }

    public void begin() {
        batch.begin();
    }

    public void draw(Sprite sprite, int x, int y) {
        batch.draw(x, y, sprite.width(), sprite.height(), sprite.u0(), sprite.v0(), sprite.u1(), sprite.v1());
    }

    public void end() {
        batch.end();
    }

}
