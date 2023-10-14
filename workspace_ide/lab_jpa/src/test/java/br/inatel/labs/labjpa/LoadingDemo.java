package br.inatel.labs.labjpa;

import br.inatel.labs.entity.NotaCompra;
import br.inatel.labs.entity.NotaCompraItem;
import br.inatel.labs.service.NotaCompraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class LoadingDemo {
    @Autowired
    private NotaCompraService service;

    @Test
    public void demoPlanejandoConsulta() {
        try {
            NotaCompra nota = service.buscarPeloIdComListaItem(1L);
            List<NotaCompraItem> listaNotaCompraItem = nota.getListaNotaCompraItem();
            for (NotaCompraItem item : listaNotaCompraItem) {
                System.out.println(item);
            }
            System.out.println("Se chegou até aqui, o planejamento da consulta funcionou");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demoLazyLoading() {
        try {
            NotaCompra nota = service.buscarPeloId(1L);
            int tamanho = nota.getListaNotaCompraItem().size();
            System.out.println(tamanho);
        } catch (Exception e) {
            System.out.println("O carregamento foi LAZY e por isso lançou exception");
            e.printStackTrace();
        }
    }

    @Test
    public void demoEagerLoading() {
        try {
            NotaCompraItem item = service.buscarItemPeloId(1L);
            LocalDate dataEmissao = item.getNotaCompra().getDataEmissao();
            System.out.println(dataEmissao);
            System.out.println("Aconteceu o carregamento EAGER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
