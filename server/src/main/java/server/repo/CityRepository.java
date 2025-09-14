package server.repo;

import com.google.common.collect.Iterables;
import common.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.App;
import server.managers.LoadManager;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Оперирует коллекцией.
 */
public class CityRepository {
    private Queue<City> collection = new PriorityQueue<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final LoadManager loadManager;
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Instantiates a new City being repository.
     * @param loadManager the dump manager
     */
    public CityRepository(LoadManager loadManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.loadManager = loadManager;

        load();
    }

    /**
     * Validate all boolean.
     * @return the boolean
     */
    public boolean validateAll() {
        for (var person : new ArrayList<>(get())) {
            if (!person.validate()) {
                logger.warn("Объект с id=" + person.getId() + " имеет невалидные поля.");
                return false;
            }
        }
        logger.info("Все загруженные объекты валидны.");
        return true;
    }

    /**
     * Get queue.
     * @return the queue
     */
    public Queue<City> get() {
        return collection;
    }

    /**
     * Gets last init time.
     * @return the last init time
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * Gets last save time.
     * @return the last save time
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Type string.
     * @return the string
     */
    public String type() {
        return collection.getClass().getName();
    }

    /**
     * Size int.
     * @return the int
     */
    public int size() {
        return collection.size();
    }

    /**
     * First city.
     * @return the city
     */
    public City first() {
        if (collection.isEmpty()) return null;
        return sorted().get(0);
    }

    /**
     * Last city.
     * @return the city
     */
    public City last() {
        if (collection.isEmpty()) return null;
        return Iterables.getLast(sorted());
    }

    /**
     * Sorted list.
     * @return the list
     */
    public List<City> sorted() {
        logger.debug("Сортировка городов");

        return collection.stream()
                .sorted(Comparator.comparing(City::getId))
                .collect(Collectors.toList());
    }

    /**
     * Gets by id.
     * @param id the id
     * @return the by id
     */
    public City getById(int id) {
        for (City element : collection) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    /**
     * Check exist boolean.
     * @param id the id
     * @return the boolean
     */
    public boolean checkExist(int id) {
        return getById(id) != null;
    }

    /**
     * Gets by value.
     * @param elementToFind the element to find
     * @return the by value
     */
    public City getByValue(City elementToFind) {
        if (elementToFind == null) {return null;}
        for (City element : collection) {
            if (element.equals(elementToFind)) {return element;}
        }
        return null;
    }

    /**
     * Add int.
     * @param element the element
     * @return the int
     */
    public int add(City element) {
        var maxId = collection.stream().filter(Objects::nonNull)
                .map(City::getId)
                .mapToInt(Integer::intValue).max().orElse(0);
        var newId = maxId + 1;
        element.setId(newId);
        collection.add(element);

        return newId;
    }

    /**
     * Remove.
     * @param id the id
     */
    public void remove(int id) {
        collection.removeIf(person -> person.getId() == id);
    }

    /**
     * Clear.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Save.
     */
    public void save() {
        loadManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
        logger.info("Коллекция успешно сохранена.");
    }

    private void load() {
        collection = (PriorityQueue<City>) loadManager.readCollection();
        lastInitTime = LocalDateTime.now();
        logger.info("Коллекция успешно загружена.");
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        var info = new StringBuilder();
        for (City person : collection) {
            info.append(person);
            info.append("\n\n");
        }
        return info.substring(0, info.length() - 2);
    }
}
