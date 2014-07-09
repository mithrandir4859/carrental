package carrental.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DefaultGenericCrudDao<E extends Serializable> implements GenericCrudDao<E> {

	private Class<E> clazz;

	@PersistenceContext
	private EntityManager entityManager;

	public void setClazz(Class<E> clazzToSet) {
		this.clazz = clazzToSet;
	}

	@Override
	public E find(Integer id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public List<E> list(Integer start, Integer howMany) {
		throw new UnsupportedOperationException("Method isn't yet implemented");
	}

	@Override
	public List<E> list() {
		return entityManager.createQuery("FROM " + clazz.getName(), clazz).getResultList();
	}

	@Override
	public Integer getCounter() {
		return (Integer) entityManager.createQuery("SELECT COUNT(e) FROM " + clazz.getName() + " e").getSingleResult();
	}

	@Override
	public void create(E entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(E entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(E entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Integer entityId) {
		Query query = entityManager.createQuery("DELETE FROM " + clazz.getName() + " e WHERE e.id = :id");
		query.setParameter("id", entityId).executeUpdate();
	}

}