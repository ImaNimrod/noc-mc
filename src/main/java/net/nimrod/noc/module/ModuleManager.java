package net.nimrod.noc.module;

import java.util.ArrayList;
import net.nimrod.noc.module.render.*;
import org.lwjgl.glfw.GLFW;

public class ModuleManager {

    private ArrayList<Module> modules = new ArrayList<Module>();
    
    public ModuleManager() {
        this.modules.add(new Fullbright());
        this.modules.add(new XRay());
    }

    public void onTick() {
        for (Module module : getEnabledModules()) {
            module.onTick();
        }
    }

    public void onKeyPress(int key, int action) {
        if (action == GLFW.GLFW_RELEASE) {
            for (Module module : this.modules) {
                if (module.mc.currentScreen == null && module.mc.getOverlay() == null &&
                    key == module.getKey()) module.toggle();
            }
        }
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabledModules = new ArrayList<Module>();

        for (Module module : this.modules) {
            if (module.getEnabled()) enabledModules.add(module);
        }

        return enabledModules;
    }

    public Module getModule(String name) {
        Module module = null;

        for (Module m : this.modules) {
            if (m.getName().equalsIgnoreCase(name)) module = m;
        }

        return module;
    }

}
