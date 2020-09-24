package org.otus.education;

import org.otus.education.api.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogAnnotationCreator {

    public static Object createTestLogging(Class<?> clazz) {
        MyInvocationHandler handler = new MyInvocationHandler(clazz);
        return Proxy.newProxyInstance(LogAnnotationCreator.class.getClassLoader(), clazz.getInterfaces(), handler);
    }

    private LogAnnotationCreator() {
    }

    static class MyInvocationHandler implements InvocationHandler {

        private final Class<?> clazz;

        private final Set<MethodInfo> annotateMethods = new HashSet<>();

        MyInvocationHandler(Class<?> clazz) {
            this.clazz = clazz;
            scanMethods();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            MethodInfo methodInfo = new MethodInfo(method);
            if (annotateMethods.contains(methodInfo)) {
                String params = Stream.of(args).map(String::valueOf).collect(Collectors.joining(", "));
                System.out.printf("executed method: %s, params: %s", method.getName(), params).println();
            }
            return method.invoke(clazz.getDeclaredConstructor().newInstance(), args);
        }

        private void scanMethods() {
            Stream.of(clazz.getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .forEach(m -> annotateMethods.add(new MethodInfo(m)));
        }

        static class MethodInfo {

            private final String name;

            private final Class<?>[] params;

            public MethodInfo(Method method) {
                this.name = method.getName();
                this.params = method.getParameterTypes();
            }

            public String getName() {
                return name;
            }

            public Class<?>[] getParams() {
                return params;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof MethodInfo)) return false;
                MethodInfo that = (MethodInfo) o;
                return Objects.equals(getName(), that.getName()) &&
                        Arrays.equals(getParams(), that.getParams());
            }

            @Override
            public int hashCode() {
                int result = Objects.hash(getName());
                result = 31 * result + Arrays.hashCode(getParams());
                return result;
            }
        }

    }
}
