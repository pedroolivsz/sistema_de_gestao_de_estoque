#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ARQUIVO "estoque.txt"
#define USER "Admin"
#define PASSWORD "1234"
#define MAX_TENTATIVAS 3
typedef struct {
    int id;
    char nome[50];
    int quantidade;
    float valor;
} produto;

typedef struct {
    char usuario[32];
    char senha[8];
} User;

void salvarEstoque(produto estoque[], int quant_estoque);
void carregarEstoque(produto **estoque, int *quant_estoque, int *capacidade_estoque, int *ultimo_id);
void adicionarProduto(produto **estoque, int *quant_estoque, int *capacidade_estoque, int *ultimo_id);
void listarProdutos(produto estoque[], int quant_estoque);
void excluirProduto(produto estoque[], int *quant_estoque);
void clonarProduto(produto **estoque, int *quant_estoque, int *capacidade_estoque, int *ultimo_id);
float valorTotalEstoque(produto estoque[], int quant_estoque);
void buscarPorId(produto estoque[], int quant_estoque);
void buscarPorNome(produto estoque[], int quant_estoque);
void editarProduto(produto estoque[], int quant_estoque);
void ordenarPorNome(produto estoque[], int quant_estoque);
void ordenarPorQuantidade(produto estoque[], int quant_estoque);
void ordenarPorValorUnitario(produto estoque[], int quant_estoque);
void relatorioDeEstoque(produto estoque[], int quant_estoque);

int login() {
    int quantidadeTentavivas = 0;
    User administrador;

    while(quantidadeTentavivas<MAX_TENTATIVAS) {
        printf("Insira seu usuário: ");
        fgets(administrador.usuario, sizeof(administrador.usuario), stdin);
        administrador.usuario[strcspn(administrador.usuario, "\n")] = '\0';

        printf("Insira sua senha: ");
        fgets(administrador.senha, sizeof(administrador.senha), stdin);
        administrador.senha[strcspn(administrador.senha, "\n")] = '\0';

        if(strcmp(administrador.usuario, USER) == 0 && strcmp(administrador.senha, PASSWORD) == 0) {
            printf("Login bem sucedido!\n");
            return 1;
        }
        else{
            quantidadeTentavivas++;
            printf("Credenciais inválidas, tentativa número %d.", quantidadeTentavivas);
        }
    }
    printf("O número de tentativas se esgotou, tente novamente mais tarde!\n");
    return 0;
}

int main() {

    if(!login()) {
        return 1;
    }

    produto *estoque;
    int opcao;
    int quantidade_estoque = 0;
    int capacidade_estoque = 5;
    int ultimo_id = 0;

    estoque = malloc(capacidade_estoque*sizeof(produto));

    if(estoque == NULL) {
        printf("Erro. Não foi possivel alocar a memoria inicial!\n");
        return 1;
    }

    carregarEstoque(&estoque, &quantidade_estoque, &capacidade_estoque, &ultimo_id);

    do {
        printf("========================================================\n");
        printf("                    Menu de funções                     \n");
        printf("========================================================\n");
        printf("1. Adicionar produto\n");
        printf("2. Listar produtos\n");
        printf("3. Remover produto\n");
        printf("4. Clonar produto\n");
        printf("5. Calcular valor total do estoque\n");
        printf("6. Buscar produto por id\n");
        printf("7. Buscar produto por nome\n");
        printf("8. Editar produto\n");
        printf("9. Ordenar produtos por nome\n");
        printf("10. Ordenar produtos por quantidade em ordem crescente\n");
        printf("11. Ordenar produtos do menor para o maior valor\n");
        printf("12. Gerar relatório de estoque\n");
        printf("0. Encerrar programa\n");
        printf("--------------------------------------------------------\n");
        printf("Escolha: ");
        if(scanf("%d", &opcao) != 1) {
            printf("Entrada invalida!\n");
            while(getchar() != '\n');
            continue;
        }
        getchar();
        printf("--------------------------------------------------------\n");
        switch (opcao) {
            case 1:
                adicionarProduto(&estoque, &quantidade_estoque, &capacidade_estoque, &ultimo_id);
                break;
            case 2:
                listarProdutos(estoque, quantidade_estoque);
                break;
            case 3:
                excluirProduto(estoque, &quantidade_estoque);
                break;
            case 4:
                clonarProduto(&estoque, &quantidade_estoque, &capacidade_estoque, &ultimo_id);
                break;
            case 5:
                printf("Valor total em estoque: R$%.2f\n", valorTotalEstoque(estoque, quantidade_estoque));
                break;
            case 6:
                buscarPorId(estoque, quantidade_estoque);
                break;
            case 7:
                buscarPorNome(estoque, quantidade_estoque);
                break;
            case 8:
                editarProduto(estoque, quantidade_estoque);
                break;
            case 9:
                ordenarPorNome(estoque, quantidade_estoque);
                break;
            case 10:
                ordenarPorQuantidade(estoque, quantidade_estoque);
                break;
            case 11:
                ordenarPorValorUnitario(estoque, quantidade_estoque);
                break;
            case 12:
                relatorioDeEstoque(estoque, quantidade_estoque);
                break;
            case 0:
                salvarEstoque(estoque, quantidade_estoque);
                break;
            default:
                printf("Função não encontrada. Tente novamente!\n");
                break;
        }
    } while(opcao!=0);
    printf("Encerrando programa...\n");
    free(estoque);
    printf("Programa finalizado!\n");
    return 0;
}
void salvarEstoque(produto estoque[], int quant_estoque) {
    FILE *arquivo = fopen(ARQUIVO, "w");
    int item;
    if(arquivo == NULL) {
        printf("Erro. O arquivo %s não foi encontado, impossível salvar o arquivo!\n", ARQUIVO);
        return;
    }
    for(item=0; item<quant_estoque; item++) {
        fprintf(arquivo, "%d;%s;%d;%.2f\n", 
        estoque[item].id,
        estoque[item].nome,
        estoque[item].quantidade,
        estoque[item].valor);
    }
    fclose(arquivo);
    printf("Os dados foram salvos com sucesso em %s\n", ARQUIVO);
    return;
}
void carregarEstoque(produto **estoque, int *quant_estoque, int *capacidade_estoque, int *ultimo_id) {
    FILE *arquivo = fopen(ARQUIVO, "r");
    *quant_estoque=0;
    if(arquivo == NULL) {
        printf("Erro. O arquivo %s não encontrado, nenhum dado encontrado.\n", ARQUIVO);
        return;
    }
    *quant_estoque = 0;
    while(fscanf(arquivo, "%d;%49[^;];%d;%f\n", 
            &(*estoque)[*quant_estoque].id,
            (*estoque)[*quant_estoque].nome,
            &(*estoque)[*quant_estoque].quantidade,
            &(*estoque)[*quant_estoque].valor)==4) {
        if(*quant_estoque>=*capacidade_estoque) {
            *capacidade_estoque *= 2;
            produto *cap_temporaria = realloc(*estoque, (*capacidade_estoque)*sizeof(produto));
            if(!cap_temporaria) {
                printf("Erro ao realocar memória!\n");
                fclose(arquivo);
                return;
            }
            *estoque = cap_temporaria;
        }
        if((*estoque)[*quant_estoque].id >= *ultimo_id) {
            (*ultimo_id) = (*estoque)[*quant_estoque].id;
        }
        (*quant_estoque)++;
    }
    fclose(arquivo);
}
void adicionarProduto(produto **estoque, int *quant_estoque, int *capacidade_estoque, int *ultimo_id) {
    if(*quant_estoque>=*capacidade_estoque) {
            *capacidade_estoque *= 2;
            produto *cap_temporaria = realloc(*estoque, (*capacidade_estoque)*sizeof(produto));
            if(!cap_temporaria) {
                printf("Erro ao realocar memória!\n");
                return;
            }
            *estoque = cap_temporaria;
    }
    (*ultimo_id)++;
    (*estoque)[*quant_estoque].id = *ultimo_id;
    printf("Insira o nome do produto: ");
    fgets((*estoque)[*quant_estoque].nome, sizeof((*estoque)[*quant_estoque].nome), stdin);
    (*estoque)[*quant_estoque].nome[strcspn((*estoque)[*quant_estoque].nome, "\n")] = '\0';
    printf("Insira a quantidade de produtos: ");
    if(scanf("%d", &(*estoque)[*quant_estoque].quantidade) != 1 || (*estoque)[*quant_estoque].quantidade < 0) {
        printf("Quantidade invalida!\n");
        while(getchar() != '\n');
        return;
    }
    while(getchar() != '\n');
    do { 
        printf("Insira o valor do produto: ");
        scanf("%f", &(*estoque)[*quant_estoque].valor);
        getchar();
        if((*estoque)[*quant_estoque].valor<0) {
            printf("\nValor invalido. Tente novamente!");
        }
    } while((*estoque)[*quant_estoque].valor < 0);
    printf("\nProduto adicionado com sucesso!\n");
    (*quant_estoque)++;
    salvarEstoque(*estoque, *quant_estoque);
    return;
}
void listarProdutos(produto *estoque, int quant_estoque) {
    int id;
    if(quant_estoque==0) {
        printf("Estoque vazio!\n");
        return;
    }
    for(id=0; id<quant_estoque; id++) {

        printf("===== Produto =====\n");
        printf("Nome: %s\n", estoque[id].nome);
        printf("Id: %d\n", estoque[id].id);
        printf("Quantidade em estoque: %d\n", estoque[id].quantidade);
        printf("Valor unitário: R$%.2f\n", estoque[id].valor);
        printf("===================\n");
    }  
    return;
}
void excluirProduto(produto estoque[], int *quant_estoque) {
    int id, item, encontrado=0;
    printf("Digite o id que deseja excluir: ");
    scanf("%d", &id);
    getchar();
    for(item=0; item<*quant_estoque; item++) {
        if(id==estoque[item].id) {
            encontrado = 1;
            break;
        }
    }
    if(encontrado==0) {
        printf("O id informado não foi encontrado!\n");
        return;
    }
    for(int mover=item; mover<*quant_estoque-1; mover++) {
        estoque[mover] = estoque[mover+1];
    }
    (*quant_estoque)--;
    printf("O produto foi excluido com sucesso!\n");
    salvarEstoque(estoque, *quant_estoque);
    return;
}
float valorTotalEstoque(produto estoque[], int quant_estoque) {
    float valor_total = 0.0;
    int id;
    for(id=0; id<quant_estoque; id++) {
        valor_total += estoque[id].valor * estoque[id].quantidade;
    }
    return valor_total;
}
void buscarPorId(produto estoque[], int quant_estoque) {
    int buscar_id, verificar_id, encontrado = 0;
    printf("Insira o id do produto: ");
    scanf("%d", &buscar_id);
    for(verificar_id=0; verificar_id<quant_estoque; verificar_id++) {
        if(estoque[verificar_id].id==buscar_id) {
            encontrado = 1;
            break;
        }
    }
    if(encontrado==0) {
        printf("O id não foi encontrado! Tente novamente.");
    }
    else if(encontrado==1) {
        printf("\n===== Produto =====\n");
        printf("Nome: %s\n", estoque[verificar_id].nome);
        printf("Id: %d\n", estoque[verificar_id].id);
        printf("Quantidade: %d\n", estoque[verificar_id].quantidade);
        printf("Valor unitário: R$%.2f\n", estoque[verificar_id].valor);
        printf("=====================\n");
    }
    return;
}
void buscarPorNome(produto estoque[], int quant_estoque) {
    char nome[50];
    int encontrado = 0;
    int indice;
    printf("Insira o nome do produto: ");
    fgets(nome, sizeof(nome), stdin);
    nome[strcspn(nome, "\n")] = '\0';
    for(indice=0; indice<quant_estoque; indice++) {
        if(strcmp(estoque[indice].nome, nome) == 0) {
            printf("\n===== Produto ID: %d =====\n", estoque[indice].id);
            printf("Nome: %s\n", estoque[indice].nome);
            printf("Quantidade: %d\n", estoque[indice].quantidade);
            printf("Valor unitário: R$%.2f\n", estoque[indice].valor);
            printf("=========================\n\n");
            encontrado++;
        }
    }
    if(encontrado == 0) {
        printf("\nNenhum produto com esse nome foi encontrado!\n");
    }
    return;    
}
void editarProduto(produto estoque[], int quant_estoque) {
    int id, item, encontrado=0;
    printf("Digite o id do produto: ");
    scanf("%d", &id);
    for(item=0; item<quant_estoque; item++) {
        if(estoque[item].id==id) {
            encontrado = 1;
            break;
        }
    }
    if(encontrado==0) {
        printf("Nenhum produto encontrado. Tente novamente!\n");
        return;
    }
    if(encontrado==1) {
        getchar();
        printf("Novo nome: ");
        fgets(estoque[item].nome, 50, stdin);
        estoque[item].nome[strcspn(estoque[item].nome, "\n")] = '\0';
        

        printf("Nova quantidade: ");
        scanf("%d", &estoque[item].quantidade);

        printf("Novo valor: ");
        scanf("%f", &estoque[item].valor);
        
    }

    printf("\nProduto atualizado com sucesso!\n");
    salvarEstoque(estoque, quant_estoque);
    return;
}
void ordenarPorNome(produto estoque[], int quant_estoque) {
    produto temporario;
    for(int verificar=0; verificar<quant_estoque-1; verificar++) {
        for(int trocar=0; trocar<quant_estoque-verificar-1; trocar++) {
            if(strcmp(estoque[trocar].nome, estoque[trocar+1].nome)>0) {
                temporario = estoque[trocar];
                estoque[trocar] = estoque[trocar+1];
                estoque[trocar+1] = temporario;
            }
        }
    }
    printf("\nProdutos ordenados por nome!\n\n");
    salvarEstoque(estoque, quant_estoque);
    return;
}
void ordenarPorQuantidade(produto estoque[], int quant_estoque) {
    produto temporario;
    for(int verificar=0; verificar<quant_estoque-1; verificar++) {
        for(int trocar=0; trocar<quant_estoque-verificar-1; trocar++) {
            if(estoque[trocar].quantidade > estoque[trocar+1].quantidade) {
                temporario = estoque[trocar];
                estoque[trocar] = estoque[trocar+1];
                estoque[trocar+1] = temporario;
            }
        }
    }
    printf("\nProdutos ordenados com sucesso!\n\n");
    salvarEstoque(estoque, quant_estoque);
    return;
}
void ordenarPorValorUnitario(produto estoque[], int quant_estoque) {
    produto temporario;
    for(int verificar=0; verificar<quant_estoque-1; verificar++) {
        for(int trocar=0; trocar<quant_estoque-verificar-1; trocar++) {
            if(estoque[trocar].valor>estoque[trocar+1].valor) {
                temporario = estoque[trocar];
                estoque[trocar] = estoque[trocar+1];
                estoque[trocar+1] = temporario;
            }
        }
    }
    printf("\nProdutos ordenados com sucesso!\n\n");
    salvarEstoque(estoque, quant_estoque);
    return;
}
void relatorioDeEstoque(produto estoque[], int quant_estoque) {
    int item;
    float valor_total;
    printf("\n================== Relatório de Estoque ==================\n");
    printf("| ID |Nome                |Qtde |Valor Unit. |Valor total |\n");
    printf("|----|--------------------|-----|------------|------------|\n");
    for(item=0; item<quant_estoque; item++) {
        valor_total = estoque[item].quantidade*estoque[item].valor;
        printf("| %-2d |%-20s| %3d | R$ %7.2f | R$ %7.2f |\n", 
        estoque[item].id,
        estoque[item].nome,
        estoque[item].quantidade,
        estoque[item].valor,
        valor_total);
    }
    printf("-----------------------------------------------------------\n");
    printf("Quantidade de itens em estoque: %d\n", quant_estoque);
    printf("Valor total em estoque: R$%.2f\n", valorTotalEstoque(estoque, quant_estoque));
    printf("===========================================================\n");
    return;
}
void clonarProduto(produto **estoque, int *quant_estoque, int *capacidade_estoque, int *ultimo_id) {
    int id_original, id_copia;
    if((*quant_estoque)>=(*capacidade_estoque)) {
        *capacidade_estoque *= 2;
        produto *estoque_temprario = realloc(*estoque, (*capacidade_estoque)*sizeof(produto));
        if(!estoque_temprario) {
            printf("Erro ao realocar memoria!\n");
            return;
        }
        *estoque = estoque_temprario;
    }
    printf("Insira o ID do produto que deseja copiar: ");
    scanf("%d", &id_original);
    getchar();
    int encontrado = 0;
    for(id_copia=0; id_copia<*quant_estoque; id_copia++) {
        if((*estoque)[id_copia].id == id_original) {
            (*estoque)[*quant_estoque] = (*estoque)[id_copia];
            (*ultimo_id)++;
            (*estoque)[*quant_estoque].id = *ultimo_id;
            (*quant_estoque)++;

            printf("Produto clonado com sucesso. Novo ID: %d\n", *quant_estoque);
            printf("|Nome: %s |Qtde: %d |Valor: R$%.2f |\n",
                (*estoque)[*quant_estoque-1].nome,
                (*estoque)[*quant_estoque-1].quantidade, 
                (*estoque)[*quant_estoque-1].valor);
            encontrado = 1;
            break;
        }
    }
    if(!encontrado) {
        printf("ID não encontrado. Não foi possivel clonar o produto!\n");
    }
    salvarEstoque(*estoque, *quant_estoque);
    return;
}