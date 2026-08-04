package org.litkoit.dcw.gui;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class WorkstationWindow {
    private long window;
    private final int width;
    private final int height;
    private final String title;

    private Thread windowThread;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public WorkstationWindow(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void open() {
        if (running.get()) return; // уже открыто

        windowThread = new Thread(this::runInternal, "WorkstationWindow-" + title);
        windowThread.setDaemon(true);
        windowThread.start();
    }

    public void close() {
        running.set(false);
    }

    public void awaitClose() throws InterruptedException {
        if (windowThread != null) {
            windowThread.join();
        }
    }

    public boolean isOpen() {
        return running.get();
    }

    private void runInternal() {
        try {
            init();
            running.set(true);
            loop();
        } finally {
            if (window != NULL) {
                glfwFreeCallbacks(window);
                glfwDestroyWindow(window);
                window = NULL;
            }
            running.set(false);
        }
    }

    private void init() {
        if (!glfwInit()) {
            throw new IllegalStateException("Не удалось инициализировать GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Не удалось создать GLFW окно");
        }

        glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(win, true);
            }
        });

        glfwSetWindowCloseCallback(window, win -> running.set(false));

        try (var stack = org.lwjgl.system.MemoryStack.stackPush()) {
            var pWidth = stack.mallocInt(1);
            var pHeight = stack.mallocInt(1);
            glfwGetWindowSize(window, pWidth, pHeight);

            var vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            if (vidmode != null) {
                glfwSetWindowPos(
                        window,
                        (vidmode.width() - pWidth.get(0)) / 2,
                        (vidmode.height() - pHeight.get(0)) / 2
                );
            }
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();
        glClearColor(0.1f, 0.1f, 0.15f, 1.0f);

        while (running.get() && !glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }
}