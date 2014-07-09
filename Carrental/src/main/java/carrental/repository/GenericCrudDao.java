package carrental.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericCrudDao<E extends Serializable> {
	 
	   E find(Integer id);
	   
	   List<E> list(Integer start, Integer howMany);
	   
	   List<E> list();
	 
	   Integer getCounter();
	 
	   void create(E entity);
	 
	   void update(E entity);
	 
	   void delete(E entity);
	 
	   void deleteById(Integer entityId);
	}