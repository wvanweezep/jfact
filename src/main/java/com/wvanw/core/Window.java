package com.wvanw.core;

import com.wvanw.graphics.*;
import org.joml.Matrix4f;
import org.joml.Vector2i;
import org.joml.Vector4i;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final long handle;
    private final Input input;

    // TEMP
    public Renderer2D renderer;
    public Shader shader;
    public Sprite sprite;


    public Window(String title, int x, int y) {
        handle = glfwCreateWindow(x, y, title, NULL, NULL);
        if (handle == NULL) throw new RuntimeException(
                "Failed to create a new Window.");

        glfwMakeContextCurrent(handle);
        glfwSwapInterval(1);
        GL.createCapabilities();

        input = new Input(handle);
        renderer = new Renderer2D();
        shader = new Shader("src/main/resources/shaders/default.vert",
                "src/main/resources/shaders/default.frag");
        sprite = new Sprite("testSprite", new Texture(
                "testTex", "src/main/resources/textures/test.jpg"), new Vector4i(0, 0, 256, 256));
        shader.setUniform("uTexture", 1);

        shader.bind();
    }

    public boolean isActive() {
        return handle == glfwGetCurrentContext();
    }

    public void setActive() {
        glfwMakeContextCurrent(handle);
        glfwSwapInterval(1);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    public void update() {
        glfwPollEvents();
        // TODO: Update logic
        input.resetDelta();
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // TODO: Render logic
        shader.setUniformMat4("uProjection", new Matrix4f().ortho(0f, 1600, 900, 0f, -1f, 1f));
        renderer.begin();
        renderer.draw(sprite, input.getMousePosition().x, input.getMousePosition().y);
        renderer.end();

        glfwSwapBuffers(handle);
    }

    public void destroy() {
        input.destroy();
        glfwDestroyWindow(handle);
    }
}
