package com.wvanw.graphics;

import org.joml.Vector2i;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Class storing an initialized texture linked to a unique name.
 */
public class Texture {

    /**
     * Unique handle from OpenGL of the Texture.
     */
    private final int handle;
    /**
     * Alias for the Texture under which it is registered.
     */
    private final String name;
    /**
     * Size of the Texture in pixels.
     */
    private final Vector2i size;

    /**
     * Creates a new Texture from an image at the provided path.
     * @param name alias for the Texture
     * @param path path of the image used for the Texture
     * @throws RuntimeException when stb_image failed to load the image ath the provided path.
     */
    public Texture(String name, String path) {
        this.name = name;

        ByteBuffer buffer;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            buffer = STBImage.stbi_load(new File(path).getAbsolutePath(), w, h, channels, 4);
            if (buffer == null)
                throw new RuntimeException("Unable to load file " + path + " " + STBImage.stbi_failure_reason());
            size = new Vector2i(w.get(), h.get());

            handle = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, size.x, size.y, 0,
                    GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
            STBImage.stbi_image_free(buffer);
        } bind(1);
    }

    /**
     * Binds the Texture to one of the 32 available slots.
     * @param slot index of the slot to bind the Texture to (between 0 and 31)
     */
    public void bind(int slot) {
        if (GL13.GL_TEXTURE0 + slot < GL13.GL_TEXTURE0 || GL13.GL_TEXTURE0 + slot > GL13.GL_TEXTURE31)
            throw new IllegalArgumentException("Slot outside the accepted range of 0 to 31: " + slot);
        GL13.glActiveTexture(GL13.GL_TEXTURE0 + slot);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, handle);
    }

    /**
     * Getter for the name of the Texture.
     * @return name of the Texture.
     */
    public String name() {
        return name;
    }

    /**
     * Getter for the size of the Texture.
     * @return size of the Texture in pixels for read-only purposes.
     */
    public Vector2i size() {
        return size;
    }

    /**
     * Getter for the width of the Texture.
     * @return width of the Texture in pixels.
     */
    public int width() {
        return size.x;
    }

    /**
     * Getter for the height of the Texture
     * @return height of the Texture in pixels.
     */
    public int height() {
        return size.y;
    }

    /**
     * Frees the resources used by the Texture.
     */
    public void destroy() {
        GL11.glDeleteTextures(handle);
    }

    @Override
    public String toString() {
        return "Texture(" + name + ")";
    }
}
