import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import re


#https://stackoverflow.com/questions/14262405/loop-through-all-csv-files-in-a-folder
import glob
path = "*variance.csv"
print (glob.glob(path))
sublist = [file for file in glob.glob(path)]
table_data = pd.DataFrame(columns=['Policy','Variance (*100,000)'])
for fname in sublist:
    
    data = pd.read_csv(fname,squeeze=True)
    #var = (data.var()/100000).round(3)
    cv = lambda x: np.std(x, ddof=1) / np.mean(x) * 100 
    
    a = cv(data)

    table_data = table_data.append({'Policy':re.sub('_variance.csv','',fname),'Variance (*100,000)':a},ignore_index=True)

print(table_data)
fig,ax = plt.subplots()
fig.patch.set_visible(True)
ax.axis('off')
ax.axis('tight')
table = ax.table(cellText = table_data.values,loc='center')
table.scale(1,4)
plt.title("Empirical Variability")


fig.tight_layout()
plt.show()

