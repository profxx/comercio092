package br.com.senai.comercio092.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.comercio092.entity.Produto;
import br.com.senai.comercio092.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }
    // Mostrar produto pelo ID
    public Produto findById(Long id){
        return produtoRepository.findById(id).orElse(null);
    }
    // Insere um novo produto
    public Produto insertNew(Produto produto){
        return produtoRepository.save(produto);
    }
    // Alterar um produto
    public Produto update(Long id, Produto produtoAlterado){
        Produto produtoAtual = findById(id);
        produtoAtual.setNome(produtoAlterado.getNome());
        produtoAtual.setDescricao(produtoAlterado.getDescricao());
        produtoAtual.setPreco(produtoAlterado.getPreco());
        produtoAtual.setQuantidade(produtoAlterado.getQuantidade());
        return produtoRepository.save(produtoAtual);
    }
    // Deleta um produto pelo ID
    public Boolean deleteById(Long id){
        Produto produto = findById(id);
        if (produto == null){
            return false;
        }else{
            produtoRepository.deleteById(id);
            return true;
        }
    }
}
