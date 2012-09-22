package org.isma.tools.jars;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.io.FileUtils;
import org.isma.tools.jars.model.ClassCache;
import org.isma.tools.jars.model.JarNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DuplicateLocalizerMain {
    public static final File JAR_DIR = new File("C:\\Users\\toto\\.m2\\repository\\");

    private static DuplicatesAnalyzer analyzer = new DuplicatesAnalyzer();
    private JarLoader loader = new JarLoader();

    public static void main(String[] args) throws IOException {
        DuplicateLocalizerMain main = new DuplicateLocalizerMain();
        main.exec(JAR_DIR);
    }

    private void exec(File jarDir) throws IOException {
        List<File> jars = getJars(jarDir);
        JarNode[] nodes = new JarNode[jars.size()];
        int i = 0;
        for (File jar : jars) {
            nodes[i++] = loader.load(jar);
        }
        ClassCache classCache = analyzer.getDuplicates(nodes);
        classCache.print();
    }

    private List<File> getJars(File directory) {
        List<File> files = new ArrayList<File>(FileUtils.listFiles(directory, new String[]{"jar"}, true));
        CollectionUtils.filter(files, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                return !((File)o).getName().startsWith("isma-");
            }
        });
        return files;
    }
}
