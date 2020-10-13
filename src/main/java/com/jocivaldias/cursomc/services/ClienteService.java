package com.jocivaldias.cursomc.services;

import com.jocivaldias.cursomc.domain.Cidade;
import com.jocivaldias.cursomc.domain.Cliente;
import com.jocivaldias.cursomc.domain.Endereco;
import com.jocivaldias.cursomc.domain.enums.Perfil;
import com.jocivaldias.cursomc.domain.enums.TipoCliente;
import com.jocivaldias.cursomc.dto.ClienteDTO;
import com.jocivaldias.cursomc.dto.ClienteNewDTO;
import com.jocivaldias.cursomc.repositories.ClienteRepository;
import com.jocivaldias.cursomc.repositories.EnderecoRepository;
import com.jocivaldias.cursomc.security.UserSS;
import com.jocivaldias.cursomc.services.exception.AuthorizationException;
import com.jocivaldias.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Cliente find(Integer id){
        UserSS user = UserService.authenticated();

        if(user==null || (!user.hasRole(Perfil.ADMIN) && !id.equals(user.getId()))){
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()
        ));
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        repo.deleteById(id);
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto){
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
                TipoCliente.toEnum(objDto.getTipo()), bCryptPasswordEncoder.encode(objDto.getSenha()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
                objDto.getBairro(), objDto.getCep(), cli, cid);

        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if( objDto.getTelefone2() != null){
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if( objDto.getTelefone3() != null){
            cli.getTelefones().add(objDto.getTelefone3());
        }

        return cli;
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
