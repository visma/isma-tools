package org.isma.tools.jars.model;

public class JarNode extends PackageNode {
    private final String jarName;

    public JarNode(String jarName) {
        super("");
        this.jarName = jarName;
    }

    public String getJarName() {
        return jarName;
    }

    public boolean isRoot() {
        return true;
    }


    @Override
    public String getPath() {
        return jarName.replace(".jar", "");
    }
}
