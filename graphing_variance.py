import pandas as pd
import matplotlib.pyplot as plt
import re


#https://stackoverflow.com/questions/14262405/loop-through-all-csv-files-in-a-folder
import glob
path = "*variance.csv"
print (glob.glob(path))
sublist = [file for file in glob.glob(path)]
table_data = pd.DataFrame(columns=['Policy','Variability'])
for fname in sublist:
    
    data = pd.read_csv(fname,squeeze=True)
    mean = data.mean()
    sum = 0
    for i in range(0,299):
        sum += (data[i] - mean)**2
    emp = sum / (299 - 1)
    #squared = emp / 
    table_data = table_data.append({'Policy':re.sub('_variance.csv','',fname),'Variability':emp},ignore_index=True)

print(table_data)
fig,ax = plt.subplots()
fig.patch.set_visible(False)
ax.axis('off')
ax.axis('tight')
table = ax.table(cellText = table_data.values,loc='center')
table.scale(1,4)
plt.title("Empirical Variability")


fig.tight_layout()
plt.show()

