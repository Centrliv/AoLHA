Лабораторная работа №2
===========
### Задание:
Реализовать параллельный поиск строки в нескольких файлах на диске. Сравнить производительность с последовательным поиском: 
1. Любым удобным способом
2. С использованием неблокирующего ввода-вывода
### Тестирование:
В демонстрационной папке folder_0 находятся (0..n-1) файлов и (0..n) папок, в каждой из которых находятся (0..n-1) файлов и (0..n) папок и т.д. N=6. В каждой конечной папке находятся только файлы. В каждом файле, название которого оканчивается четной цифрой, находится искомая фраза "Write Once and Run Anywhere". В остальных файлах находится произвольная строка. Итого: 5040 файлов и 872 папки.

![Test 1](https://github.com/Centrliv/AoLHA/blob/main/Lab2/readmeImg/test_1.png)
![Test 2](https://github.com/Centrliv/AoLHA/blob/main/Lab2/readmeImg/test_2.png)
![Test 3](https://github.com/Centrliv/AoLHA/blob/main/Lab2/readmeImg/test_3.png)
