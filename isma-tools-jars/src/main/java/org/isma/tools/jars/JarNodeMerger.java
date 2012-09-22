package org.isma.tools.jars;

import org.isma.tools.jars.model.ClassCache;
import org.isma.tools.jars.model.ClassNode;
import org.isma.tools.jars.model.JarNode;
import org.isma.tools.jars.model.PackageNode;

public class JarNodeMerger {

    public ClassCache merge(JarNode... roots) {
        ClassCache classCache = new ClassCache();
        for (PackageNode root : roots) {
            addClasses(classCache, root);
        }
        return classCache;
    }

    private void addClasses(ClassCache classCache, PackageNode root) {
        for (ClassNode classNode : root.getClasses()) {
            classCache.addClass(classNode);
        }
        for (PackageNode packageNode : root.getPackages()) {
            addClasses(classCache, packageNode);
        }
    }

}
