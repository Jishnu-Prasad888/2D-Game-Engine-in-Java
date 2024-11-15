package jade;


import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;

public class Window {
    private int width ,height;
    private String title;

    private static Window window = null;

    private  Window() {
        // private so that no outside class can create windows and only the window class can create windows
        this.width = 1920;
        this.height = 1080;
        this.title = "Mario";
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
        // Means that this will be created only once when we call it the first time in the main class and everytime the same object runs
    }

    public void run () {
        System.out.println("Hello LWJGl " + Version.getVersion() + "!");
        init();
        loop();
    }

    public void init() {
        // Setting up an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialising GLFW
    }
    public void loop() {}
}

