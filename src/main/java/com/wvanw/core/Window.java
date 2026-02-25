package com.wvanw.core;

import com.wvanw.graphics.Renderer2D;
import com.wvanw.graphics.Shader;
import com.wvanw.graphics.SpriteBatch;
import org.joml.Matrix4f;
import org.joml.Vector2i;
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
        Vector2i mpos = input.getMousePosition();
        renderer.draw(mpos.x, mpos.y, 100, 100);
        renderer.draw(mpos.x+200, mpos.y, 100, 100);
        renderer.end();

        glfwSwapBuffers(handle);
    }

    public void destroy() {
        input.destroy();
        glfwDestroyWindow(handle);
    }
}
