
package repositorys;

import models.Model;

//DAO = DATA ACESS OBEJCT  -> Objeto de acesso a dados
public interface IDAO {

	IDAO getInstancia();

	int inserir(Model obj);

	Model atualizar(Model obj);

	Model consultar(Integer id);

	void deletar(Integer id);

	void listar(String tipo);
}
