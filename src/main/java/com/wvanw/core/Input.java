package com.wvanw.core;

import org.joml.Vector2i;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    private final boolean[] keys = new boolean[GLFW_KEY_LAST + 1];
    private final boolean[] mouseButtons = new boolean[GLFW_MOUSE_BUTTON_LAST + 1];

    private final Vector2i mousePosition = new Vector2i();
    private final Vector2i mouseDelta = new Vector2i();
    private double mouseScrollDelta = 0;

    private final GLFWKeyCallback keyCallback;
    private final GLFWMouseButtonCallback mouseButtonCallback;
    private final GLFWCursorPosCallback cursorPosCallback;
    private final GLFWScrollCallback scrollCallback;

    public Input(long window) {
        this.keyCallback = GLFWKeyCallback.create((_, key, _, action, _) -> {
            if (key >= 0 && key < keys.length)
                keys[key] = (action != GLFW_RELEASE); });
        glfwSetKeyCallback(window, this.keyCallback);

        this.mouseButtonCallback = GLFWMouseButtonCallback.create((_, button, action, _) -> {
            if (button >= 0 && button < mouseButtons.length)
                mouseButtons[button] = (action != GLFW_RELEASE); });
        glfwSetMouseButtonCallback(window, this.mouseButtonCallback);

        this.cursorPosCallback = GLFWCursorPosCallback.create((_, mx, my) -> {
            mouseDelta.set((int) mx - mousePosition.x, (int) my - mousePosition.y);
            mousePosition.set((int) mx, (int) my);});
        glfwSetCursorPosCallback(window, this.cursorPosCallback);

        this.scrollCallback = GLFWScrollCallback.create((_, x, y) -> mouseScrollDelta += y);
        glfwSetScrollCallback(window, this.scrollCallback);
    }

    public boolean getKeyDown(int key) {
        return key >= 0 && key < keys.length && keys[key];
    }

    public boolean getKeyUp(int key) {
        return key >= 0 && key < keys.length && !keys[key];
    }

    public boolean getMouseButtonDown(int button) {
        return button >= 0 && button < mouseButtons.length && mouseButtons[button];
    }

    public boolean getMouseButtonUp(int button) {
        return button >= 0 && button < mouseButtons.length && !mouseButtons[button];
    }

    public Vector2i getMousePosition() {
        return new Vector2i(mousePosition);
    }

    public Vector2i getMouseDelta() {
        return new Vector2i(mouseDelta);
    }

    public double getMouseScrollDelta() {
        return mouseScrollDelta;
    }

    public void resetDelta() {
        mouseDelta.zero();
        mouseScrollDelta = 0;
    }

    public void destroy() {
        keyCallback.free();
        mouseButtonCallback.free();
        cursorPosCallback.free();
        scrollCallback.free();
    }
}
