#include <stdio.h>
#include <string.h>

#define MAX_PRODUTOS 50

typedef struct {
    int id;
    char nome[50];
    int quantidade;
    float valor;
}produto;

void adicionarProduto(produto estoque[], int *quant_estoque);
void listarProdutos(produto estoque[], int quant_estoque);
void excluirProduto(produto estoque[], int *quant_estoque);
float valorTotalEstoque(produto estoque[], int quant_estoque);
void buscarPorId(produto estoque[], int quant_estoque);
void editarProduto(produto estoque[], int quant_estoque);

int main() {
    produto estoque[MAX_PRODUTOS];

    int opcao;
    int quantidade_estoque = 0;

    do {
        printf("===== Menu =====\n");
        printf("1. Adicionar produto\n");
        printf("2. Listar produtos\n");
        printf("3. Remover produto\n");
        printf("4. Calcular valor total do estoque\n");
        printf("5. Buscar produto por id\n");
        printf("6. Editar produto\n");
        printf("0. Encerrar programa\n");
        printf("===== Escolha: ");
        scanf("%d", &opcao);
        getchar();
        switch (opcao) {
            case 1:
                adicionarProduto(estoque, &quantidade_estoque);
                break;
            case 2:
                listarProdutos(estoque, quantidade_estoque);
                break;
            case 3:
                excluirProduto(estoque, &quantidade_estoque);
                break;
            case 4:
                printf("Valor total do estoque: R$%.2f\n", valorTotalEstoque(estoque, quantidade_estoque));
                break;
            case 5:
                buscarPorId(estoque, quantidade_estoque);
                break;
            case 6:
                editarProduto(estoque, quantidade_estoque);
                break;
            case 0:
                break;
            default:
                printf("Função não encontrada. Tente novamente!\n");
                break;
        }
        
    } while(opcao!=0);

    printf("Encerrando programa...\n");
    printf("Programa finalizado!");

    return 0;
}

void adicionarProduto(produto estoque[], int *quant_estoque) {
    if(*quant_estoque>=MAX_PRODUTOS) {
        printf("Estoque cheio!");
        return;
    }

    estoque[*quant_estoque].id = *quant_estoque + 1;

    printf("Insira o nome do produto: ");
    fgets(estoque[*quant_estoque].nome, 50, stdin);
    estoque[*quant_estoque].nome[strcspn(estoque[*quant_estoque].nome, "\n")] = '\0';

    printf("\nInsira a quantidade de produtos: ");
    scanf("%d", &estoque[*quant_estoque].quantidade);

    do { 
        printf("\nInsira o valor do produto: ");
        scanf("%f", &estoque[*quant_estoque].valor);
        getchar();
        if(estoque[*quant_estoque].valor<0) {
            printf("\nValor invalido. Tente novamente!");
        }
    } while(estoque[*quant_estoque].valor < 0);

    printf("\nProduto adicionado com sucesso!\n");
    (*quant_estoque)++;
    return;
}

void listarProdutos(produto estoque[], int quant_estoque) {
    int id;
    if(quant_estoque==0) {
        printf("Estoque vazio!");
        return;
    }
    for(id=0; id<quant_estoque; id++) {
        printf("----- Produto -----\n");
        printf("Nome: %s\n", estoque[id].nome);
        printf("Id: %d\n", estoque[id].id);
        printf("Quantidade: %d\n", estoque[id].quantidade);
        printf("Valor unitário: R$%.2f\n", estoque[id].valor);
        printf("=-=-=-=-=-=-=-=-=-=-=-=\n");
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
    for(int move=item; move<*quant_estoque; move++) {
        estoque[move] = estoque[move+1];
    }
    (*quant_estoque)--;
    for(int new_id=0; new_id<*quant_estoque; new_id++) {
        estoque[new_id].id = new_id + 1;
    }
    printf("O produto foi excluido com sucesso!\n");

    return;
}

float valorTotalEstoque(produto estoque[], int quant_estoque) {
    float total = 0.0;
    int id;

    for(id=0; id<quant_estoque; id++) {
        total += estoque[id].valor * estoque[id].quantidade;
    }

    return total;
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
        printf("Nome: %s\n", estoque[buscar_id].nome);
        printf("Id: %d\n", estoque[buscar_id].id);
        printf("Quantidade: %d\n", estoque[buscar_id].quantidade);
        printf("Valor unitário: R$%.2f\n", estoque[buscar_id].valor);
        printf("=-=-=-=-=-=-=-=-=-=-=\n");
    }
    return;
} 

void editarProduto(produto estoque[], int quant_estoque) {
    int id, p;
    printf("Digite o id do produto: ");
    scanf("%d", &id);

    for(p=0; p<quant_estoque; p++) {
        if(estoque[p].id==id) {
            getchar();
            printf("Novo nome: ");
            fgets(estoque[p].nome, 50, stdin);
            estoque[p].nome[strcspn(estoque[p].nome, "\n")] = '\0';

            printf("Nova quantidade: ");
            scanf("%d", &estoque[p].quantidade);

            printf("Novo valor: ");
            scanf("%f", &estoque[p].valor);
        }
    }
    printf("\nProduto atualizado com sucesso!\n");
    return;
}