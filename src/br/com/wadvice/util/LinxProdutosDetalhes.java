package br.com.wadvice.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import br.com.wadvice.rest.model.DadosXml;
import br.com.wadvice.rest.model.Microvix;
import br.com.wadvice.rest.model.ProdutosDetalhes;
import br.com.wadvice.rest.model.ResultXml;

public class LinxProdutosDetalhes {
	
	public static List<ProdutosDetalhes> convertStringXmlToObjects(String xml) {
		List<ProdutosDetalhes> produtos = new ArrayList<>();
		xml = xml.replaceAll("﻿", "");
		StringReader reader = new StringReader(xml);
		try {
			JAXBContext context = JAXBContext.newInstance(ResultXml.class);
			Microvix result = (Microvix) context.createUnmarshaller().unmarshal(reader);
			
			String[] cabecalhos = result.getResponseData().getChave().getValor();
			List<DadosXml> registros = result.getResponseData().getResultados();
			
			for (DadosXml dadosXml : registros) {
				ProdutosDetalhes produto = new ProdutosDetalhes();
				for (int i = 0; i < cabecalhos.length; i++) {
					switch (cabecalhos[i]) {
					case "portal":
						produto.setPortal(dadosXml.getValor()[i]);
						continue;
					case "cnpj_emp":
						produto.setCnpjEmpresa(dadosXml.getValor()[i]);
						continue;
					case "cod_produto":
						produto.setCodProduto(dadosXml.getValor()[i]);
						continue;
					case "cod_barra":
						produto.setCodBarra(dadosXml.getValor()[i]);
						continue;
					case "quantidade":
						produto.setQuantidade(dadosXml.getValor()[i]);
						continue;
					case "preco_custo":
						produto.setPrecoCusto(dadosXml.getValor()[i]);
						continue;
					case "preco_venda":
						produto.setPrecoVenda(dadosXml.getValor()[i]);
						continue;
					case "custo_medio":
						produto.setCustoMedio(dadosXml.getValor()[i]);
						continue;
					case "id_config_tributaria":
						produto.setIdConfigTributaria(dadosXml.getValor()[i]);
						continue;
					case "desc_config_tributaria":
						produto.setDescConfigTributaria(dadosXml.getValor()[i]);
						continue;
					case "despesas1":
						produto.setDespesas1(dadosXml.getValor()[i]);
						continue;
					default:
						break;
					}
				}
				produtos.add(produto);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return produtos;
	}
	
	public static void gravarProdutosDetalhes(List<ProdutosDetalhes> produtos) {
		System.out.println(produtos);
		
	}

}
