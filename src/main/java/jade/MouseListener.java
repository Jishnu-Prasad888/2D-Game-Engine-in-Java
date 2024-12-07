package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double Xpos, Ypos, lastY , lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;


    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.Xpos = 0.0;
        this.Ypos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }
        return MouseListener.instance;
    }

    public static void mousePosCallback(long window,double xpos, double ypos) {
        get().lastX = get().Xpos;
        get().lastY = get().Ypos;
        get().Xpos= xpos;
        get().Ypos = ypos;
        // to see if the user is dragging the mouse we will see if the user has clicked any of the mouse button and simultaneously moved the mouse
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2] ;
    }

    public static void mouseButtonCallBack(long window,int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            // to prevent errors when the mouse buttons are more than 3 like in gaming mouses
            if(button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if(button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window,double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {

        get().scrollY = 0;
        get().scrollX = 0;
        // By setting them to 0, the game world is either centered on a particular point (e.g., the player’s position) or reset to its default position (like the origin).

        get().lastX = get().Xpos;
        get().lastY = get().Ypos;
        // By saving the previous position of the player (or camera), you can perform actions like determining how far the player has moved, or transitioning smoothly between positions.
        // so that we can calculate the displacement between the mouse in the curent fram and the next frame
    }

    public static float getX() {
      return (float)get().Xpos;
    }

    public static float getY() {
        return (float)get().Ypos;
    }

    public static float getdX() {
        return (float)(get().lastX - get().Xpos);
    }

    public static float getdY() {
        return (float)(get().lastY - get().Ypos);
    }

    public static float getScrollx() {
        return (float)get().scrollX;
    }

    public static float getScrolly() {
        return (float)get().scrollY;
    }

    public static boolean isDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if(button < get().mouseButtonPressed.length ) {
            return get().mouseButtonPressed[button];
        }
        else {
            return false;
        }
    }
}

