import pandas as pd
import matplotlib.pyplot as plt

data = pd.read_csv("highPData.csv")

x = data['p']
E_N = data['E[N]']
E_T = data['E[T]']


plt.plot(x, E_N, label = "E[N] vs. p")
plt.plot(x, E_T, label = "E[T] vs. p")


'''
#Use this code to plot the theoretical graphs

verifyP = [a/100 for a in range (1, 50)]

ven = [a*(.5)/(.5-a) for a in verifyP]
vet = [(1-a)/(.5-a) for a in verifyP]

plt.plot(verifyP, ven)
plt.plot(verifyP, vet)
'''

plt.legend(loc = "best")
plt.title("Response time and Jobs in System versus p")
plt.xlabel("p")
plt.ylabel("Expected Values")

plt.show()

