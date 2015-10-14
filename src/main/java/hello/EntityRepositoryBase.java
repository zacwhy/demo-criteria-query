package hello;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class EntityRepositoryBase<T> implements EntityRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    @Override
    public List<Map<String, Object>> findAllSelectingColumns(String[] columnNames) {

        CriteriaQuery<Object> criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
        Root<T> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.multiselect(Stream.of(columnNames).map(root::get).collect(Collectors.toList()));
        List<Object> results = entityManager.createQuery(criteriaQuery).getResultList();

        List<Map<String, Object>> maps = new ArrayList<>();
        for (Object result : results) {
            Map<String, Object> map = new HashMap<>();

            Object[] values = result instanceof Object[] ? (Object[]) result : new Object[]{result};

            for (int i = 0; i < columnNames.length; i++)
                map.put(columnNames[i], values[i]);

            maps.add(map);
        }

        return maps;
    }

}
