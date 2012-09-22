package org.isma.tools.jars;

import org.isma.tools.jars.model.ClassNode;
import org.isma.tools.jars.model.JarNode;
import org.isma.tools.jars.model.JavaNode;
import org.isma.tools.jars.model.PackageNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static java.lang.String.CASE_INSENSITIVE_ORDER;

public class JarLoader {
    public static final String PACKAGE_SEPARATOR = "/";
    public static final String CLASS_EXTENSION = ".class";

    public JarNode load(File jar) throws IOException {
        List<String> classes = loadClasses(jar);
        Collections.sort(classes, CASE_INSENSITIVE_ORDER);
        JarNode root = JavaNode.buildRoot(jar.getName());
        for (String aClass : classes) {
            String[] path = aClass.split(PACKAGE_SEPARATOR);
            PackageNode currentPackage = root;
            for (int i = 0; i < path.length - 1; i++) {
                PackageNode subPackage = currentPackage.getSubPackage(path[i]);
                if (subPackage == null) {
                    subPackage = new PackageNode(path[i]);
                }
                currentPackage.addPackage(subPackage);
                currentPackage = subPackage;
            }
            ClassNode newClass = new ClassNode(path[path.length - 1]);
            currentPackage.addClass(newClass);
        }
        return root;
    }


    private List<String> loadClasses(File jar) throws IOException {
        List<String> classes = new ArrayList<String>();
        JarFile jarFile = new JarFile(jar);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            if (name.endsWith(CLASS_EXTENSION)) {
                classes.add(name/*.split(".class")[0]*/);
            }
        }
        return classes;
    }
}
