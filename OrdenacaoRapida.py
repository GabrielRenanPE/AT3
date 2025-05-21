import time
import tracemalloc

def ler_numeros(arquivo):
    """
    Lê linhas de 'arquivo', tenta converter em float e retorna uma lista de floats.
    Linhas vazias ou não numéricas são ignoradas.
    """
    numeros = []
    with open(arquivo, 'r') as f:
        for linha in f:
            linha = linha.strip()
            if not linha:
                continue
            try:
                numeros.append(float(linha))
            except ValueError:
                # ignora linhas que não são números válidos
                pass
    return numeros

def escrever_numeros(arr, arquivo):
    """
    Grava cada valor de arr em uma linha no arquivo de saída.
    """
    with open(arquivo, 'w') as f:
        for num in arr:
            f.write(f"{num}\n")

def main():
    entrada = "arq.txt"
    saida   = "arq-saida-python-OR.txt"

    # Leitura
    numeros = ler_numeros(entrada)

    # Inicia medição de memória
    tracemalloc.start()

    # Inicia medição de tempo
    inicio = time.perf_counter()

    # Ordenação nativa (Timsort em CPython)
    numeros.sort()

    # Finaliza medição de tempo
    fim = time.perf_counter()
    tempo_ms = (fim - inicio) * 1000  # em milissegundos

    # Pega pico de memória em bytes e converte para KB
    _, pico_bytes = tracemalloc.get_traced_memory()
    tracemalloc.stop()
    memoria_kb = pico_bytes / 1024

    # Grava saída
    escrever_numeros(numeros, saida)

    # Saída de status
    print(f"Tempo de execução: {tempo_ms:.2f} ms")
    print(f"Memória utilizada (pico): {memoria_kb:.2f} KB")
    print("Informações do PC: Intel i5-13, RAM 16GB DDR5, RTX 4050, SSD M2")
    print(f"Arquivo de saída '{saida}' gerado com sucesso.")

if __name__ == "__main__":
    main()
