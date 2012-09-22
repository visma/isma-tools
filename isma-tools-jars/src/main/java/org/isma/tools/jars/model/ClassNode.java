package org.isma.tools.jars.model;

import java.util.Collections;
import java.util.List;

public class ClassNode extends JavaNode {
    public ClassNode(String name) {
        super(name);
    }


    @Override
    public boolean isClass() {
        return true;
    }

    @Override
    public List<JavaNode> getChildren() {
        return Collections.emptyList();
    }

    public String getPath(boolean includeJarName){
        if (includeJarName){
            return getPath();
        }
        return getPath().split(":")[1];
    }
}
