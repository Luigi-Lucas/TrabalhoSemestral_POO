package Repository;

import java.util.List;

public interface IObjectDAO <T>{
	
	void salvar(T entidade);
	List<T> lerTodos();
	List<T> pesquisarNome(String nome);
}
