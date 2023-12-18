package by.bsuir.lab3.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends Serializable> {
	void create(T entity);

	T read(int id);

	void update(T entity);

	void delete(T entity);

	List<T> readAll();

	List<T> readAll(String sortingColumn);
}
