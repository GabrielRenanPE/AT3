import time
import tracemalloc

def bubble_sort(arr):
    for i in range(len(arr)):
        for j in range(len(arr)-i-1):
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]
    return arr

def read_numbers(file):
    nums = []
    with open(file) as f:
        for line in f:
            line = line.strip()
            if line:
                try: nums.append(float(line))
                except ValueError: pass
    return nums

def main():
    nums = read_numbers("arq.txt")
    
    tracemalloc.start()
    inicio = time.time()
    
    nums_ordenados = bubble_sort(nums)
    
    tempo = round((time.time() - inicio) * 1000, 2)
    memoria = tracemalloc.get_traced_memory()[1]
    tracemalloc.stop()
    
    with open("arq-saida-python.txt", "w") as f:
        f.writelines(f"{n}\n" for n in nums_ordenados)
    
    print(f"""Tempo de execução: {tempo} ms
Memória utilizada: {memoria} KB
Arquivo de saída 'arq-saida-python.txt' gerado com sucesso.""")

main()