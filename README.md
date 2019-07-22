<h1 align="center"> Projeto Final de Computação Evolutiva :computer:  :chart_with_upwards_trend: </h2>
<h2 align="center"> AG para o Problema da Mochila Quadrática 0-1 </h2>



Algoritmo Genético desenvolvido para tratar o Problema da Mochila Quadrática 0-1. 
Algoritmo desenvolvido como projeto final da matéria Computação Evolutiva ministrada pelo professor Matheus Haddad. 


## Introdução


O Problema da Mochila Quadrática 0-1 é um problema desafiador e que tem atraído consideravelmente a atenção na literatura nas ultimas três décadas. O nome “mochila” decorre da semelhança com o que se faz na prática quando se deseja acomodar em uma mochila certo número de itens (roupas, acessórios, etc.).
O Problema da Mochila Quadrático (PMQ) visa maximizar a função objetivo quadrática sujeita a uma restrição de mochila onde todos os coeficientes são considerados não negativos e todas as variáveis de decisão são binárias e/ou inteiras. O PMQ é um problema no qual um item tem um lucro correspondente e um lucro adicional é resgatado se o item é selecionado junto a outro item. O problema da mochila é um dos 21 problemas NP-completos de Richard Karp, exposto em 1972.




### Alguns Problemas Aplicados


- Problemas de empacotamento.

- Alocação de tarefas.

- Orçamento ou alocação de recursos financeiros.

- Seleção de portfólios.



## Descrição do Algoritmo

- [Representação](#Representação)
- [Geração da População Inicial](#Geração-da-População-Inicial)
- [Avaliação dos Indivíduos](#Avaliação-dos-Indivíduos)
- [Seleção para Cruzamento](#Seleção-para-Cruzamento)
- [Cruzamento](#Cruzamento)
- [Mutação](#Mutação)
- [Substituição](#Substituição)

### **Representação**
Cada individuo será representado por uma cadeia de bits assumindo valores 0 ou 1. Cada bit significa um objeto. Caso possua 1 ele entrará na mochila, 0 se não. 

```sh
 Indivíduo 1 = 10000101
```
Neste caso entrará na mochila os objetos de índice 1, 6 e 8.

Os valores e os pesos serão atribuídos em dois vetores de inteiros distintos: “**valoresObjeto**” e “**pesoObjeto**”.  
População será guardada em um Array de Strings denominado “**_POPULACAO**”. Junto a capacidade da mochila e os valores adicionais serão representados por uma variável do tipo inteira “**capacidadeMochila**” e uma matriz quadrada representada por  “**valoresADC**”.

### **Geração da População Inicial**
A população inicial foi gerada pelo método: **populacaoInicial()**.
De forma aleatória gera valores entre 0 e 255. Depois realiza uma verificação se o numero é acima de 128. Caso seja, o algoritmo transforma o numero para binário e atribui a um individuo. Dessa forma indivíduos serão do tamanho de 128 bits. Esse procedimento é realizado 20 vezes para gerar 20 indivíduos.

### **Avaliação dos Indivíduos**
A Avaliação para os indivíduos é feita através de dois métodos: **validaCromossomo(String Fenotipo)** e **calculaAptidao(String Fenotipo)**. Esses dois métodos recebem como parâmetro uma String contendo o valor (código) do individuo. 

A primeira função do tipo booleano avalia se o indivíduo está apto a entrar na mochila verificando se o somatório dos pesos dos objetos com índice 1 é menor ou igual  que a capacidade da mochila. Assim retornando verdadeiro caso seja. 

A Função **calculaAptidao** calcula a aptidão do indivíduo aplicando a função objetivo do problema que é a forma de avaliação do indivíduos. Primeiramente ela soma os valores dos objetos e posteriormente adiciona os valores adicionais em conjunto com outro objeto. Essa função retorna a  aptidão de cada indivíduo.

### **Seleção para Cruzamento**

A seleção para o cruzamento é feita através de uma avaliação de aptidão dos individuos **(seleçãoPorAptdão())** e depois é feita uma roleta **selecaoporRoleta** em cima desses, organizando os individuos por melhor aptidão. 
### **Cruzamento**
O Cruzamento é aplicado a partir de dois metodos: **Crossover(String Pai, String Pai2)** e **aplicaCrossover(ArrayList<string> individuos)**. O primeiro Seleciona dois pais e por meio de um numero randomico gera um ponto de corte e seleciona as partes dos pais e faz a troca atribuindo a uma cariável filho. O Segundo chama o primeiro metodo e aplica o crossover à lista de pais, caso a taxa seja até 75% . 
### **Mutação**
A Mutação é feita a partir dos metodos **Mutacao(String Fenotipo)** e **aplicaMutacao(ArrayList<String> idvs)**.  O primeiro recebe a string com o fenótipo do indivíduo e realiza operação em uma posição random. O Segundo chama o primeiro método com todos os indivíduos, porém antes faz a verificação dos 9% de taxa. 
### **Substituição**

A partir do array retornado pelo método de **AplicaMutação**, este é copiado para o array de população inicial ArrayList<String> população através do método GeraNovaPopulacao() que aplica todos os métodos acima. 




##  🗯 Links Úteis
[Universidade Federal de Viçosa - Campus Rio Paranaíba](http://www.crp.ufv.br)


