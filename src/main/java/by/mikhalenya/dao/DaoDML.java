package by.mikhalenya.dao;

public interface DaoDML<T> {
    void create(T t);
    void update(T t, int id);
    void delete(int id);
}
