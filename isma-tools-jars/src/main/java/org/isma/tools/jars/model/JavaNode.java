package org.isma.tools.jars.model;

import java.util.List;

public abstract class JavaNode {
    public static final String PACKAGE_SEPARATOR = "/";

    protected final String name;
    protected PackageNode parent;

    protected JavaNode(String name) {
        this.name = name;
    }

    public abstract List<JavaNode> getChildren();


    public String getPath() {
        if (parent.isRoot()) {
            return String.format("%s:%s", parent.getPath(), name);
        }
        return String.format("%s%s%s", parent.getPath(), PACKAGE_SEPARATOR, name);
    }

    public abstract boolean isClass();


    public static JarNode buildRoot(String jarName) {
        return new JarNode(jarName);
    }


    public PackageNode getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return getPath();
    }

    public String getName() {
        return name;
    }

    public JarNode getRoot() {
        PackageNode theParent = getParent();
        if (theParent.isRoot()) {
            return (JarNode)theParent;
        }
        while (!theParent.isRoot()) {
            return theParent.getRoot();
        }
        throw new RuntimeException("no root found");
    }
}
