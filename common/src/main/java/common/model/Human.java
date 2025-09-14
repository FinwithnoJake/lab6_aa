package common.model;

import common.util.Validator;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс кар.
 *
 */
public class Human implements Validator, Serializable {
    private final String name;//Поле может быть null
    /**
     * Instantiates a new Human.
     * @param zipCode the zip code
     */
    public Human(String zipCode) {
        this.name = zipCode;
    }

    /**
     * Валидирует правильность полей.
     * @return true, если все верно, иначе false
     */
    @Override
    public boolean validate() {
        return name == null;
    }

    /**
     * Gets name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        common.model.Human address = (common.model.Human) o;
        return Objects.equals(name, address.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Челик " + (name == null ? "" : ", " + name);
    }
}