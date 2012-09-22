package org.isma.tools.jars.model;

import java.util.*;

public class ClassCache {
    private final Map<String, List<ClassNode>> map = new HashMap<String, List<ClassNode>>();

    public void addClass(ClassNode clazz) {
        String path = clazz.getPath(false);
        if (map.get(path) == null) {
            map.put(path, new ArrayList<ClassNode>());
        }
        map.get(path).add(clazz);
    }

    public Set<String> getPaths() {
        return map.keySet();
    }

    public List<ClassNode> getClasses(String path) {
        return map.get(path);
    }

    public void removePath(String path) {
        map.remove(path);
    }

    public void print() {
        for (String path : map.keySet()) {
            printPath(path);
        }
    }

    private void printPath(String path) {
        System.out.println("------------------------------------------------------------");
        System.out.println(path);
        for (ClassNode classNode : map.get(path)) {
            System.out.println("\t-" + classNode.getRoot().getJarName());
        }
        System.out.println("------------------------------------------------------------");
    }
}
