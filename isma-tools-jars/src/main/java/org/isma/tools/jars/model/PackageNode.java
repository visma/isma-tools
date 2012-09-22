package org.isma.tools.jars.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.apache.commons.lang.StringUtils.join;

public class PackageNode extends JavaNode {
    private final List<JavaNode> children = new ArrayList<JavaNode>();

    public PackageNode(String name) {
        super(name);
    }

    public PackageNode addPackage(PackageNode subPackage) {
        if (getSubPackage(subPackage.getName()) != null) {
            return null;
        }
        return addJavaNode(subPackage);
    }


    public PackageNode getSubPackage(String name) {
        for (JavaNode child : children) {
            if (child instanceof PackageNode) {
                if (child.name.equals(name)) {
                    return (PackageNode) child;
                }
            }
        }
        return null;
    }

    public ClassNode addClass(ClassNode classNode) {
        return addJavaNode(classNode);
    }

    public <N extends JavaNode> N addJavaNode(N classNode) {
        children.add(classNode);
        classNode.parent = this;
        return classNode;
    }

    public List<JavaNode> getChildren() {
        return children;
    }

    public boolean isRoot() {
        return false;
    }


    @Override
    public boolean isClass() {
        return false;
    }

    public JavaNode getChild(String childName) {
        if (!childName.contains(PACKAGE_SEPARATOR)) {
            for (JavaNode currentChild : children) {
                if (currentChild.name.equals(childName)) {
                    return currentChild;
                }
            }
            return null;
        }
        String[] path = childName.split(Pattern.quote(PACKAGE_SEPARATOR));
        for (JavaNode currentChild : children) {
            String firstChildName = path[0];
            if (currentChild.name.equals(firstChildName)) {
                path = Arrays.copyOfRange(path, 1, path.length);
                return ((PackageNode) currentChild).getChild(join(path, PACKAGE_SEPARATOR));
            }
        }
        return null;
    }

    public List<String> getPaths() {
        List<String> paths = new ArrayList<String>();
        for (ClassNode classNode : getClasses()) {
            paths.add(classNode.getPath());
        }
        for (PackageNode packageNode : getPackages()) {
            paths.addAll(packageNode.getPaths());
        }
        return paths;
    }

    public List<ClassNode> getClasses() {
        List<ClassNode> classNodes = new ArrayList<ClassNode>();
        for (JavaNode child : children) {
            if (child.isClass()) {
                classNodes.add((ClassNode) child);
            }
        }
        return classNodes;
    }

    public List<PackageNode> getPackages() {
        List<PackageNode> packageNodes = new ArrayList<PackageNode>();
        for (JavaNode child : children) {
            if (!child.isClass()) {
                packageNodes.add((PackageNode) child);
            }
        }
        return packageNodes;
    }

    public int getClassCount(final String className) {
        return CollectionUtils.countMatches(getClasses(), new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                return ((ClassNode) o).getName().equals(className);
            }
        });
    }

}
