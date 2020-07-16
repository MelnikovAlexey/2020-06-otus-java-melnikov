package org.otus.education;

import java.util.*;
import java.util.function.Predicate;

/**
 * Кастомная учебная реализация интерфейса List
 *
 * NotSupportMethod
 * <ul>
 *     <li>{@link DIYarrayList#toArray(Object[])}</li>
 *     <li>{@link DIYarrayList#retainAll(Collection)}</li>
 *     <li>{@link DIYarrayList#subList(int, int)}</li>
 * </ul>
 *
 */

public class DIYarrayList<E> implements List<E> {
    private int size = 0;

    private final int MAX_VALUE = Integer.MAX_VALUE;

    private E[] arrayElements;

    public DIYarrayList() {
        this(10);
    }

    public DIYarrayList(int size) {
        if (size < 0 || size > MAX_VALUE) {
            throw new IllegalArgumentException("Размер массива задан не верно: " + size + ". Размер не может быть меньше 1 и не больше " + MAX_VALUE);
        }
        initReSizeArray(size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Object[] containsArray = arrayElements;
        if (Objects.isNull(o)) {
            for (Object value : containsArray) {
                if (value == null) {
                    return true;
                }
            }
        } else {
            for (Object element : containsArray) {
                if (o.equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new DIYarrayIterator<>(arrayElements, size);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayElements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        checkSizeAndReSizeIfNeeded(size + 1);
        arrayElements[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.parallelStream().filter(Predicate.not(this::contains)).findAny().isEmpty();
      //  throw notSupport();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        int newSize = size + c.size();
        checkSizeAndReSizeIfNeeded(newSize);
        System.arraycopy((E[]) c.toArray(), 0, arrayElements, size, c.size());
        size += c.size();
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);
        if (c.isEmpty()) {
            return false;
        }
        int newSize = size + c.size();
        checkSizeAndReSizeIfNeeded(newSize);
        // расширяем массив от индекса на размер коллекции
        System.arraycopy(arrayElements, index, arrayElements, index + c.size(), size - index);
        System.arraycopy((E[]) c.toArray(), 0, arrayElements, index, c.size());
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return c.stream().filter(this::removeAllElements).count() > 0;

    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw notSupport();
    }

    @Override
    public void clear() {
        size = 0;
        Arrays.fill(arrayElements, null);
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return getElement(index);
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E previousElement = getElement(index);
        arrayElements[index] = element;
        return previousElement;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        checkSizeAndReSizeIfNeeded(size + 1);
        System.arraycopy(arrayElements, index,
                arrayElements, index + 1,
                size - index);
        size++;
        arrayElements[index] = element;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedElement = getElement(index);
        System.arraycopy(arrayElements, index + 1, arrayElements, index, size - index);
        size--;
        arrayElements[size] = null;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        if (Objects.isNull(o)) {
            for (int i = 0; i < size; i++) {
                if (arrayElements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (arrayElements[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (Objects.isNull(o)) {
            for (int i = size - 1; i >= 0; i--) {
                if (arrayElements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (arrayElements[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DIYarrayIterator<>(arrayElements, size);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw notSupport();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw notSupport();
    }

    private UnsupportedOperationException notSupport() {
        return new UnsupportedOperationException();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    private E getElement(int index) {
        return (E) arrayElements[index];
    }

    private void checkSizeAndReSizeIfNeeded(int size) {
        if (size > MAX_VALUE) {
            throw new IllegalArgumentException("Превышен размер коллекции. Коллекция не может быть больше чем " + MAX_VALUE + " элементов.");
        }
        if (this.arrayElements.length < size) {
            initReSizeArray(size + 10);
        }
    }

    private boolean removeAllElements(Object o) {
        boolean isRemoved = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()){
            if (Objects.equals(o,iterator.next())){
                iterator.remove();
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @SuppressWarnings("unchecked")
    private void initReSizeArray(int newSize) {
        if (Objects.isNull(arrayElements)) {
            arrayElements = (E[]) new Object[newSize];
        } else {
            arrayElements = Arrays.copyOf(arrayElements, newSize);
        }
    }

    private class DIYarrayIterator<I> implements ListIterator<I> {

        private int currentPosition = -1;
        private final I[] arrayElement;
        private final int size;

        public DIYarrayIterator(I[] arrayElement, int size) {
            this.arrayElement = arrayElement;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return (currentPosition + 1) < size;
        }

        @Override
        public I next() {
            if (hasNext()) {
                return arrayElement[++currentPosition];
            }
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return (currentPosition - 1) >= 0;
        }

        @Override
        public I previous() {
            if (hasPrevious()) {
                return arrayElement[--currentPosition];
            }
            throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            if (hasNext()) {
                return currentPosition + 1;
            }
            return size;
        }

        @Override
        public int previousIndex() {
            if (hasPrevious()) {
                return currentPosition - 1;
            }
            return -1;
        }

        @Override
        public void remove() {
            DIYarrayList.this.remove(currentPosition--);
        }

        @Override
        @SuppressWarnings("unchecked")
        public void set(I i) {
            DIYarrayList.this.set(currentPosition, (E) i);

        }

        @Override
        @SuppressWarnings("unchecked")
        public void add(I i) {
            DIYarrayList.this.add(++currentPosition, (E) i);
        }
    }
}
