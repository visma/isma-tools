package org.isma.tools.jars;

import org.isma.tools.jars.model.ClassCache;
import org.isma.tools.jars.model.JarNode;

import java.util.ArrayList;
import java.util.List;

public class DuplicatesAnalyzer {
    private final JarNodeMerger merger = new JarNodeMerger();

    public ClassCache getDuplicates(JarNode... roots) {
        ClassCache classCache = merger.merge(roots);
        purgeSingletons(classCache);
        return classCache;
    }

    private void purgeSingletons(ClassCache classCache) {
        List<String> paths = new ArrayList<String>(classCache.getPaths());
        for (int i = 0; i < paths.size(); i++) {
            String path = paths.get(i);
            if (classCache.getClasses(path).size() == 1) {
                classCache.removePath(path);
            }
        }
    }

}

