package br.com.senai.comercio092.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senai.comercio092.entity.Produto;
import br.com.senai.comercio092.entity.Venda;
import br.com.senai.comercio092.repository.ProdutoRepository;
import br.com.senai.comercio092.repository.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    // Retorna a lista de todas as vendas
    public List<Venda> findAll(){
        return vendaRepository.findAll(); 
    }

    // Retorna uma venda específica
    public Venda findById(Long id){
        return vendaRepository.findById(id).orElse(null);
    }

    // Realizar uma venda
    public Venda realizarVenda(Venda venda){
        // Define a data de hoje como a data da venda
        venda.setDataVenda(LocalDate.now());

        // Reduzir a quantidade de produto no estoque
        Produto produto = produtoRepository.findById(venda.getProdutoId()).orElse(null);
        if (produto == null){
            return null;
        }else{
            int quantidadeVendida = venda.getQuantidade();
            int quantidadeAtualizada = produto.getQuantidade() - quantidadeVendida;
            produto.setQuantidade(quantidadeAtualizada);
            produtoRepository.save(produto);

            // Incluir o valor total da venda
            venda.setPrecoTotal(quantidadeVendida * produto.getPreco());

            // Salvar a venda no banco de dados
            return vendaRepository.save(venda);
        }
    }
}
