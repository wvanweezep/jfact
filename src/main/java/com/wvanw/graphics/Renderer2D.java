package com.wvanw.graphics;

public class Renderer2D {

    private final SpriteBatch batch;

    public Renderer2D() {
        batch = new SpriteBatch();
    }

    public void begin() {
        batch.begin();
    }

    public void draw(int x, int y, int width, int height) {
        batch.draw(x, y, width, height);
    }

    public void end() {
        batch.end();
    }

}
