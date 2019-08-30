import scipy as sp
import matplotlib.pyplot as plt

arq = open('Dados.txt', 'r')

#Grafico 1
arv1N = []
arv1C = []
arv2N = []
arv2C = []

num = arq.readline()
while num != '\n' :
	arv1N.append(int(num))
	num = arq.readline()
	arv1C.append(int(num))
	num = arq.readline()

num = arq.readline()
while num != '\n' :
	arv2N.append(int(num))
	num = arq.readline()
	arv2C.append(int(num))
	num = arq.readline()

num = arq.readline()

plt.ylabel('Tempo(ns)')
plt.xlabel('Número de elementos')
plt.title('Número de elementos X Tempo(ns)')
plt.scatter(arv1N, arv1C, color='blue')
plt.scatter(arv2N, arv2C, color='orange')
plt.ylim(0, 350000)

plt.plot(arv1N, arv1C, arv2N, arv2C)
arq.close()

plt.show()


