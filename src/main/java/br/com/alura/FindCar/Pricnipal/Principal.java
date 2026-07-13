package br.com.alura.FindCar.Pricnipal;

import br.com.alura.FindCar.model.Dados;
import br.com.alura.FindCar.model.DadosVeiculo;
import br.com.alura.FindCar.model.modelos;
import br.com.alura.FindCar.service.ConsumoAPI;
import br.com.alura.FindCar.service.ConverterDadosApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
        private final static Scanner sc = new Scanner(System.in);
        private final static ConsumoAPI consumo = new ConsumoAPI();
        private final static ConverterDadosApi converter = new ConverterDadosApi();
        private final String urlBASE = "https://parallelum.com.br/fipe/api/v1/";


    public void exibirMenu() throws IOException, InterruptedException {




        /// 1 - Busca Inicial para Tipo de Veiculo.
        ///URL final deste processo: https://parallelum.com.br/fipe/api/v1/carros
        var menu = """
            ==================================
                      Menu de Opções 
            ==================================
            Digite o tipo de veículo desejado: 
            -> Carro
            -> Moto
            -> Caminhão
            ==================================
            """;
        System.out.println(menu);
        var opcao = sc.nextLine();
        String endereco = null;

        if(opcao.toLowerCase().contains("carr")){
            endereco = urlBASE+"carros/marcas";
        }else if(opcao.toLowerCase().contains("mot")){
            endereco = urlBASE+"motos/marcas";
        }else if(opcao.toLowerCase().contains("camin")){
            endereco = urlBASE+"caminhoes/marcas";
        }

        var json = consumo.obterDadosCarro(endereco);
        System.out.println(json);
        var marcas = converter.obterLista(json, Dados.class);


        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);


        ///2 - Inicio da busca por marcas,
        ///usuario digita o codigo da marca especifica para que a api exiba os modelos existentes daquela marca
        ///URL AO FINAL DESTA ETAPA: https://parallelum.com.br/fipe/api/v1/motos/marcas/60/modelos

        System.out.println("Digite o código da marca desejada para consulta: ");
        var codigoMarca = sc.nextLine();

        endereco += "/"+codigoMarca+"/modelos";
        json = consumo.obterDadosCarro(endereco);
        var modelosMarca = converter.obterDados(json, modelos.class);

        System.out.println("Modelos dessa Marca: ");
        modelosMarca.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        ///3 - Busca de modelo especifico filtrado por uma stream da mesma URL da etapa passadas

        System.out.println("\n Digite um trecho do nome carro que você esta procurando:  ");
        var nome = sc.nextLine();

        List<Dados> carrosModelo = modelosMarca.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nome.toLowerCase()))
                .toList();


        /// 4 - Exibição de lista dos modelos selecionados pelo filtro + com os anos de cada modelo
        ///URL FINAL NESTA ETAPA: https://parallelum.com.br/fipe/api/v1/motos/marcas/60/modelos/2576/anos
        System.out.println("\nModelos Filtrados");
        carrosModelo.forEach(System.out::println);




        ///5 - Requisição dos dados de cada veiculo a partir do codigo digitado pelo usuário
        ///Exibição desses dados e URL final do Projeto: https://parallelum.com.br/fipe/api/v1/motos/marcas/60/modelos/2576/anos/2000-1



        System.out.println("Digite o código do modelo desejado para : ");
        var code = sc.nextLine();

        endereco = endereco+"/"+code+"/anos";
        json = consumo.obterDadosCarro(endereco);
        List<Dados> modeloAnos = converter.obterLista(json, Dados.class);
        List<DadosVeiculo> veiculos = new ArrayList<>();

        for (int i = 0 ; i < modeloAnos.size(); i++){
            var urlAnos = endereco+"/"+modeloAnos.get(i).codigo();
            json = consumo.obterDadosCarro(urlAnos);
            DadosVeiculo veiculo = converter.obterDados(json, DadosVeiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("Dados do veiculo selecionado: ");
        veiculos.forEach(System.out::println);




    }

}
