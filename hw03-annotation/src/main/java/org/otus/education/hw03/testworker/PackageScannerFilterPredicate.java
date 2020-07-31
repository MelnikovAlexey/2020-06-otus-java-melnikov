package org.otus.education.hw03.testworker;

import java.util.function.Predicate;

public class PackageScannerFilterPredicate implements Predicate<Class<?>> {
    private String filter = null;

    public PackageScannerFilterPredicate(String filter) {
        this.filter = filter;
    }

    public PackageScannerFilterPredicate() {
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public boolean test(Class<?> clazz) {

        return filter == null || clazz.getSimpleName().startsWith(filter);
    }
}
