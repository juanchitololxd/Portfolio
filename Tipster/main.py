
import tkinter as tk
from calculos import *

root = tk.Tk()
pad = 3
_geom = '200x200+0+0'
root.geometry("{0}x{1}+0+0".format(
    root.winfo_screenwidth() - pad, root.winfo_screenheight() - pad))
root.title("Tipster")


fData = tk.Frame()
fData.pack(padx=120, pady=100, side=tk.LEFT)

#filas

titulos = ["GF ult 3 partidos","GF Loc/Visit","GF vs cats", "GF *", "GF +", "GC ult 3 partidos",
           "GC Loc/Visit", "GC vs cats","GC +"]
for i in range(len(titulos)):
    tk.Label(master=fData, text=titulos[i], font=("arial italic", 16)).grid(row=i+2, column=0, sticky='w')

#Titulos fila 0
lColumn = tk.Label(master=fData, text="Probabilidad de gol",font=("arial italic", 16))
lColumn.grid(row=0, column=1, columnspan=2,padx=40, pady=3)
lColumn2 = tk.Label(master=fData, text=" Goles esperados ",font=("arial italic", 16))
lColumn2.grid(row=0, column=4, columnspan=2,padx=40, pady=3)

#"Subtitulos"


lColumn3 = tk.Label(master=fData, text="A",font=("arial italic", 16))
lColumn3.grid(row=1, column=1, padx=5, pady=3)
lColumn4 = tk.Label(master=fData, text="B",font=("arial italic", 16))
lColumn4.grid(row=1, column=2, padx=5, pady=3)
lColumn3 = tk.Label(master=fData, text="A",font=("arial italic", 16))
lColumn3.grid(row=1, column=4, padx=5, pady=3)
lColumn4 = tk.Label(master=fData, text="B",font=("arial italic", 16))
lColumn4.grid(row=1, column=5, padx=5, pady=3)

#data
id1 = 40
id2 = 9
f1t1 =GolesUlt3Partidos(id1, req)
f1t2 =GolesUlt3Partidos(id2, req)
data21 = tk.Label(master=fData, text=round(f1t1["probGol"],3),font=("arial italic", 14))
data21.grid(row=2, column=1, padx=5, pady=3)
data22 = tk.Label(master=fData, text=round(f1t2["probGol"],3),font=("arial italic", 14))
data22.grid(row=2, column=2, padx=5, pady=3)
data24 = tk.Label(master=fData, text=round(f1t1["golesProm"],3),font=("arial italic", 14))
data24.grid(row=2, column=4, padx=5, pady=3)
data25 = tk.Label(master=fData, text=round(f1t2["golesProm"],3),font=("arial italic", 14))
data25.grid(row=2, column=5, padx=5, pady=3)

f2t1 = golesLV(local=True, partidos=req, id=id1)
f2t2 = golesLV(local=False, partidos=req, id=id2)
data31 = tk.Label(master=fData, text=round(f2t1["probGol"],3),font=("arial italic", 14))
data31.grid(row=3, column=1, padx=5, pady=3)
data32 = tk.Label(master=fData, text=round(f2t2["probGol"],3),font=("arial italic", 14))
data32.grid(row=3, column=2, padx=5, pady=3)
data34 = tk.Label(master=fData, text=round(f2t1["golesProm"],3),font=("arial italic", 14))
data34.grid(row=3, column=4, padx=5, pady=3)
data35 = tk.Label(master=fData, text=round(f2t2["golesProm"],3),font=("arial italic", 14))
data35.grid(row=3, column=5, padx=5, pady=3)



f8t1 = golesCLV(local=True, partidos=req, id=id1)
f8t2 = golesCLV(local=False, partidos=req, id=id2)
data31 = tk.Label(master=fData, text=round(f8t1["probGol"],3),font=("arial italic", 14))
data31.grid(row=8, column=1, padx=5, pady=3)
data32 = tk.Label(master=fData, text=round(f8t2["probGol"],3),font=("arial italic", 14))
data32.grid(row=8, column=2, padx=5, pady=3)
data34 = tk.Label(master=fData, text=round(f8t1["golesProm"],3),font=("arial italic", 14))
data34.grid(row=8, column=4, padx=5, pady=3)
data35 = tk.Label(master=fData, text=round(f8t2["golesProm"],3),font=("arial italic", 14))
data35.grid(row=8, column=5, padx=5, pady=3)
root.mainloop()