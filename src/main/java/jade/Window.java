package jade;


import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import util.Time;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {
    private int width, height;
    private String title;
    private static Window window = null;
    private long glfwWindow;
    float r;
    float g;
    float b;
    float a;
    private boolean FadeToBlack;

    private static Scene CurrentScene;

    private Window() {
        // private so that no outside class can create windows and only the window class can create windows
        this.width = 1920;
        this.height = 1080;
        this.title = "Mario";
        this.r = 1;
        this.b = 1;
        this.g = 1;
        this.a = 1;
        this.FadeToBlack = false;
    }

    public static void ChangeScene(int newScene) {
        switch (newScene) {
            case 0:
                CurrentScene = new LevelEditorScene();
                CurrentScene.init(); // Making sure to start the screen before starting we start the run method
                break;
            case 1:
                CurrentScene = new LevelScene();
                CurrentScene.init();
                break;
            default:
                assert false : "Unknown Scene !! " + newScene + " ";
                break;
        }
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
        // Means that this will be created only once when we call it the first time in the main class and everytime the same object runs
    }

    public void run() {
        System.out.println("Hello LWJGl " + Version.getVersion() + "!");
        init();
        loop();

        // Free the memory for better resource management
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and free the error callback
        glfwTerminate(); // This function is called to terminate GLFW. It should be called when your application is done using GLFW and is ready to clean up any resources it allocated during its lifetime.
        glfwSetErrorCallback(null).free(); // glfwSetErrorCallback is used to set the callback function that will be called in the event of an error. The argument passed to glfwSetErrorCallback is usually a function pointer to a custom error handler.
    }

    public void init() {
        // Setting up an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialising GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // configure GLFW

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // make it visible when we want it to
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Creating the window

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, 0L, 0L);
        // 0L which stands for NULL TO SAY THAT WE DO NOT CARE ABOUT THE MONITOR OR ABOUT THE SHARING

        if (glfwWindow == 0L) {
            throw new IllegalStateException("Unable to create the GLFW window");
        }

        // whenever there is a change in the cursor or button or scroll we run our function we created in the MouseListener class
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallBack);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);

        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallBack);

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // enable v-sync (to synchronize the frame rate of a video game or application with the refresh rate of the display monitor. Its primary goal is to prevent screen tearing and ensure smoother visual performance, especially during fast-moving or graphically intensive scenes.)
        glfwSwapInterval(1);

        // Since the window is now created we will make it visible
        glfwShowWindow(glfwWindow);

        // from the lgjw website
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        window.ChangeScene(0);
        // Making sure we are in a scene before we start the game
    }

    public void loop() {

        float beginTime = Time.getTime();
        float endTime = Time.getTime();
        float dt = -1.0f;


        while (!glfwWindowShouldClose(glfwWindow)) {
            // poll events
            glfwPollEvents(); // used to process events such as keyboard and mouse input, window resizing, and other system-related events.

            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (dt >= 0) {
                CurrentScene.update(dt);
            }
//            if(FadeToBlack) {
//                r = Math.max(r - 0.01f, 0);
//                g = Math.max(g - 0.01f, 0);
//                b = Math.max(b - 0.01f, 0);
//                a = 1;
//            }
//
//            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
//                System.out.println("Space key pressed");
//                FadeToBlack = true;
//            }

            MouseListener.endFrame();  // Reset mouse states at the end of each frame

            glfwSwapBuffers(glfwWindow);

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
            // reson why we are doing this at the end is :
            // if the code goes on and then the OS calls another function we would get a lag as the time is recorded only fo the java code written here and not the OS function calls

        }
        // Free memory and terminate GLFW here
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate(); // Properly terminate GLFW
    }
}