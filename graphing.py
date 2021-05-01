import pandas as pd
import matplotlib.pyplot as plt



import glob
path = "*.csv"
print (glob.glob(path))
for fname in glob.glob(path):
    print(fname)
        
    data = pd.read_csv(fname)

    x = data['p']
    E_N = data['E[N]']
    E_T = data['E[T]']

    labelN = "E[N] vs. p for " + fname
    labelT = "E[T] vs. p for " + fname

    plt.plot(x, E_N, label = labelN)
    plt.plot(x, E_T, label = labelT)


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


#this line for more focus on the upper end where the data change
#plt.xlim(.47, .50)

plt.show()

