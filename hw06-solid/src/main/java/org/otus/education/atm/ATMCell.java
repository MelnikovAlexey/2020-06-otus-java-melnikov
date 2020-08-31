package org.otus.education.atm;

public interface ATMCell {
    /**
     * @return тип купюры в ячейке
     */
    CashNominal getCashNominal();

    /**
     * Помещение купюры в ячейку
     * @param count количество купюр
     */
    void put(int count);

    /**
     *  Купюры на выдачу
     * @param count количество купюр
     */
    void cashOut(int count);

    /**
     * @return количество купюр в ячейке
     */
    int getCount();

    /**
     * @return Сумма денежных сревдств в ячейке
     */
    long getSum();

    /**
     * @return Максимальный лимит купюр в ячейке
     */
    int getLimit();

    /**
     * @return количество свободного места в ячейке
     */
    int getFree();

    /**
     * @return пуста ли ячейка
     */
    boolean isEmpty();
}
