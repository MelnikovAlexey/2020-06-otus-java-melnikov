package org.otus.education.hw03.testworker;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.otus.education.hw03.testworker.api.PackageScanner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PackageScannerImpl implements PackageScanner {

    private final String packageName;
    private final Predicate<Class<?>> filter;
    private final ClassLoader classLoader;

    private PackageScannerImpl(String packageName, Predicate<Class<?>> filter) {
        this(packageName, filter,
                Objects.isNull(Thread.currentThread().getContextClassLoader()) ?
                        PackageScannerImpl.class.getClassLoader() :
                        Thread.currentThread().getContextClassLoader());
    }

    private PackageScannerImpl(String packageName, Predicate<Class<?>> filter, ClassLoader classLoader) {
        this.packageName = packageName;
        this.filter = filter;
        this.classLoader = classLoader;
    }

    public static PackageScannerImpl build(String packageName, Predicate<Class<?>> filter, ClassLoader classLoader) {
        return new PackageScannerImpl(packageName, filter, classLoader);
    }

    public static PackageScannerImpl build(String packageName, Predicate<Class<?>> filter) {
        return new PackageScannerImpl(packageName, filter);
    }

    @Override
    public Collection<Class<?>> scan() {
        try {
            ClassPath classPath = ClassPath.from(classLoader);
            ImmutableSet<ClassPath.ClassInfo> classes = classPath.getTopLevelClassesRecursive(packageName);
            return classes.stream()
                    .map(ClassPath.ClassInfo::load)
                    .filter(clazz -> filter == null ||
                            filter.test(clazz)
                    )
                    .collect(Collectors.toCollection(() ->
                            new ArrayList<>(classes.size())
                    ));
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }
}
