package cursoJava.homeBanking;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@RepositoryRestResource(collectionResourceRel = "cliente", path = "cliente")
@Component
public interface  ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

}
