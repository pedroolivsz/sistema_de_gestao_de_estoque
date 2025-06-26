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
void buscarPorNome(produto estoque[], int quant_estoque);
void editarProduto(produto estoque[], int quant_estoque);
void ordenarPorNome(produto estoque[], int quant_estoque);
void ordenarPorQuantidade(produto estoque[], int quant_estoque);
void ordenarPorValorUnitario(produto estoque[], int quant_estoque);
void relatorioDeEstoque(produto estoque[], int quant_estoque);

int main() {
    produto estoque[MAX_PRODUTOS];

    int opcao;
    int quantidade_estoque = 0;

    do {
        printf("====== Menu ======\n");
        printf("1. Adicionar produto\n");
        printf("2. Listar produtos\n");
        printf("3. Remover produto\n");
        printf("4. Calcular valor total do estoque\n");
        printf("5. Buscar produto por id\n");
        printf("6. Buscar produto por nome\n");
        printf("7. Editar produto\n");
        printf("8. Ordenar produtos por nome\n");
        printf("9. Ordenar produtos por quantidade em ordem crescente\n");
        printf("10. Ordenar produtos do menor para o maior valor\n");
        printf("11. Gerar relatório de estoque\n");
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
                printf("Valor total em estoque: R$%.2f\n", valorTotalEstoque(estoque, quantidade_estoque));
                break;
            case 5:
                buscarPorId(estoque, quantidade_estoque);
                break;
            case 6:
                buscarPorNome(estoque, quantidade_estoque);
                break;
            case 7:
                editarProduto(estoque, quantidade_estoque);
                break;
            case 8:
                ordenarPorNome(estoque, quantidade_estoque);
                break;
            case 9:
                ordenarPorQuantidade(estoque, quantidade_estoque);
                break;
            case 10:
                ordenarPorValorUnitario(estoque, quantidade_estoque);
                break;
            case 11:
                relatorioDeEstoque(estoque, quantidade_estoque);
                break;
            case 0:
                break;
            default:
                printf("Função não encontrada. Tente novamente!\n");
                break;
        }
        
    } while(opcao!=0);

    printf("Encerrando programa...\n");
    printf("Programa finalizado!\n");

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

    printf("Insira a quantidade de produtos: ");
    scanf("%d", &estoque[*quant_estoque].quantidade);

    do { 
        printf("Insira o valor do produto: ");
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
    for(int new_id=0; new_id<*quant_estoque; new_id++) {
        estoque[new_id].id = new_id + 1;
    }
    printf("O produto foi excluido com sucesso!\n");

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
        if(encontrado == 0) {
            printf("\nNenhum produto com esse nome foi encontrado!\n");
        }
    }
    return;    
}

void editarProduto(produto estoque[], int quant_estoque) {
    int id, item;
    printf("Digite o id do produto: ");
    scanf("%d", &id);

    for(item=0; item<quant_estoque; item++) {
        if(estoque[item].id==id) {
            getchar();
            printf("Novo nome: ");
            fgets(estoque[item].nome, 50, stdin);
            estoque[item].nome[strcspn(estoque[item].nome, "\n")] = '\0';

            printf("Nova quantidade: ");
            scanf("%d", &estoque[item].quantidade);

            printf("Novo valor: ");
            scanf("%f", &estoque[item].valor);
        }
    }
    printf("\nProduto atualizado com sucesso!\n");
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
    return;
}
void relatorioDeEstoque(produto estoque[], int quant_estoque) {
    int item;
    printf("\n========== Relatório de Estoque ==========\n");
    printf("| ID |Nome                |Qtde |Valor Unit. |\n");
    for(item=0; item<quant_estoque-1; item++) {
        printf("| %-3d |%-20s| %5d | R$ %10.2f |\n", 
        estoque[item].id,
        estoque[item].nome,
        estoque[item].quantidade,
        estoque[item].valor);
    }
    printf("------------------------------------------\n");
    printf("Quantidade de itens em estoque: %d\n", quant_estoque);
    printf("Valor total em estoque: R$%.2f\n", valorTotalEstoque(estoque, quant_estoque));
    printf("==========================================\n");
    return;
}