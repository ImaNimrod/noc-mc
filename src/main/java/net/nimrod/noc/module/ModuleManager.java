package net.nimrod.noc.module;

import java.util.ArrayList;
import java.util.List;
import net.nimrod.noc.module.render.*;
import org.lwjgl.glfw.GLFW;

public class ModuleManager {

    private List<Module> modules = new ArrayList<>();
    
    public ModuleManager() {
        modules.add(new Fullbright());
    }

    public void onTick() {
        for (Module module : getEnabledModules()) {
            module.onTick();
        }
    }

    public void onKeyPress(int key, int action) {
        if (action == GLFW.GLFW_PRESS) {
            for (Module module : getModules()) {
                if (key == module.getKey()) module.toggle();
            }
        }
    }

    public List<Module> getModules() {
        return this.modules;
    }

    public List<Module> getEnabledModules() {
        List<Module> enabledModules = new ArrayList();

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
