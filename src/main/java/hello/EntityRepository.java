package hello;

import java.util.List;
import java.util.Map;

public interface EntityRepository<T> {

    List<Map<String, Object>> findAllSelectingColumns(String[] columnNames);

}
