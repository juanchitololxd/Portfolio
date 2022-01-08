import math
import requests
import numpy as np
from connection import *
con = Connection()
json = con.execSelectPd("SELECT IDTEAM, CAT FROM CATEGORIAS").to_dict()
req = requests.request(
    "GET",
"https://api.openligadb.de/getmatchdata/bl1/2020").json()

"""
def GolesUlt3Partidos(id=0,partidos=None):
    partidosId = []
    partidosUbic = []
    #Traer todos los partidos que tengan como local o visitante ese id
    for partido in partidos:
        if partido["team1"]["teamId"] == id:
            partidosId.insert(0, partido)
        if partido["team2"]["teamId"] == id:
            partidosId.insert(0, partido)

    gols = []
    i = 0
    numceros = 0
    for partido in partidosId:
        if i < 4:
            if partido["team1"]["teamId"] == id:
                gols.append(int(partido["matchResults"][0]["pointsTeam1"]))
                if int(partido["matchResults"][0]["pointsTeam1"]) == 0:
                    numceros += 1
            elif partido["team2"]["teamId"] == id:
                gols.append(int(partido["matchResults"][0]["pointsTeam2"]))
                if int(partido["matchResults"][0]["pointsTeam2"]) == 0:
                    numceros += 1
        else:
            break
        i += 1
    gols = menorVarianza(gols,int,3,4)
    goles = np.array(gols, int)

    return {"golesProm": goles.mean(), "probGol": 1 - (numceros/len(goles))}



def golesLV(local=True, partidos=None, id= 0):
    partidosId = []
    gols = []
    for partido in partidos:
        if partido["team1"]["teamId"] == id and local:
            partidosId.insert(0, partido)
        elif partido["team2"]["teamId"] == id and not(local):
            partidosId.insert(0, partido)

    numceros = 0
    for i in partidosId:
        if i["team1"]["teamId"] == id and local:
            gols.append(int(i["matchResults"][0]["pointsTeam1"]))
            if int(partido["matchResults"][0]["pointsTeam1"]) == 0:
                numceros += 1
        elif i["team2"]["teamId"] == id and not(local):
            gols.append(int(i["matchResults"][0]["pointsTeam2"]))
            if int(partido["matchResults"][0]["pointsTeam2"]) == 0:
                numceros += 1
    goles = np.array(gols, int)
    return {"golesProm": goles.mean(), "probGol": 1 - (numceros/len(goles))}

def golesCLV(local=True, partidos=None, id= 0):
    partidosId = []
    gols = []
    for partido in partidos:
        if partido["team1"]["teamId"] == id and local:
            partidosId.insert(0, partido)
        elif partido["team2"]["teamId"] == id and not(local):
            partidosId.insert(0, partido)

    numceros = 0
    for i in partidosId:
        if i["team1"]["teamId"] == id and local:
            gols.append(int(i["matchResults"][0]["pointsTeam2"]))
            if int(partido["matchResults"][0]["pointsTeam2"]) == 0:
                numceros += 1
        elif i["team2"]["teamId"] == id and not(local):
            gols.append(int(i["matchResults"][0]["pointsTeam1"]))
            if int(partido["matchResults"][0]["pointsTeam1"]) == 0:
                numceros += 1
    goles = np.array(gols, int)
    return {"golesProm": goles.mean(), "probGol": 1 - (numceros/len(goles))}
"""
def menorVarianza(arr, tipo, inicio, fin):
    limit = 0
    var = math.inf
    for i in range(inicio, fin):
        aux =np.array(arr[0:i], tipo).var()
        if  aux < var:
            var = aux
            limit = i
    return np.array(arr[0:limit], int)


def funcionDefinitiva(id,id2,local,partidos, cats):
    ult3p = {"GF": [], "GC": []}
    partidosLV = {"GF": [], "GC": []}
    partidosVsCat = {"GF": [], "GC": []}

    rta = {"team1": {}, "team2":{}}
    for partido in partidos:
        equipos = crearDicEquiposCat()
        if partido["team1"]["teamId"] == id:
            if local:
                partidosLV["GF"].insert(0,partido["matchResults"][0]["pointsTeam1"])
                partidosLV["GC"].insert(0, partido["matchResults"][0]["pointsTeam2"])

            for cat in equipos[id2]:
                if cat in cats:
                    partidosVsCat["GF"][cat].append(partido["matchResults"][0]["pointsTeam1"])
                    partidosVsCat["GC"][cat].append(partido["matchResults"][0]["pointsTeam2"])

            ult3p["GF"].insert(0, partido["matchResults"][0]["pointsTeam1"])
            ult3p["GC"].insert(0, partido["matchResults"][0]["pointsTeam2"])
        elif partido["team2"]["teamId"] == id:
            if not(local):
                partidosLV["GF"].insert(0,partido["matchResults"][0]["pointsTeam2"])
                partidosLV["GC"].insert(0, partido["matchResults"][0]["pointsTeam1"])
            for cat in equipos[id2]:
                if cat in cats:
                    partidosVsCat["GF"][cat].append(partido["matchResults"][0]["pointsTeam2"])
                    partidosVsCat["GC"][cat].append(partido["matchResults"][0]["pointsTeam1"])
            ult3p["GF"].insert(0, partido["matchResults"][0]["pointsTeam2"])
            ult3p["GC"].insert(0, partido["matchResults"][0]["pointsTeam1"])
    #teoricamente la recoleccion de datos está bien

    #modelado de datos
    goles = menorVarianza(ult3p["GF"],int,3,4)
    rta["team1"]["GFUlt3P"] = goles.mean()
    goles = menorVarianza(ult3p["GC"], int, 3, 4)
    rta["team1"]["GCUlt3P"] = goles.mean()
    goles = np.array(partidosLV["GF"], int)
    rta["team1"]["GFLV"] = goles.mean()
    goles = np.array(partidosLV["GC"], int)
    rta["team1"]["GCLV"] = goles.mean()
    # Ingreso de categorias al diccionario
    # "GFvs" = [{ N°cat : promedio de esos goles}, ...] por cada categoria



def crearDicEquiposCat():

    #Identificar todos los ids de los equipos
    ids = getIdsTeams()
    dic = {}
    for id in ids:
        aux = getCats(id)
        dic[id] = aux

def getIdsTeams():
    ids = []
    for partido in req:
        if partido["team1"]["teamId"] not in ids:
            ids.append(partido["team1"]["teamId"])
    return ids

def getCats(teamId):
    cats = []
    for i in range(len(json["IDTEAM"])):
        if json["IDTEAM"][i] == teamId and json["CAT"][i] not in cats:
            cats += [json["CAT"][i]]
    return cats