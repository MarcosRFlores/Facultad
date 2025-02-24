import sys
import datetime
import configparser
import requests
from requests.structures import CaseInsensitiveDict
from datetime import datetime, timedelta

# Variables globales para verificación
archivo_config = "ConfigFile.properties"

# Variables para las URLs
api_personas_url_listar = None
api_personas_url_crear = None
api_personas_url_actualizar = None
api_personas_url_eliminar = None


def cargar_variables():
    config = configparser.RawConfigParser()
    config.read(archivo_config)

    global api_personas_url_listar, api_personas_url_crear, api_personas_url_actualizar, api_personas_url_eliminar
    api_personas_url_listar = config.get("SeccionApi", "api_personas_url_listar")
    api_personas_url_crear = config.get("SeccionApi", "api_personas_url_crear")
    api_personas_url_actualizar = config.get(
        "SeccionApi", "api_personas_url_actualizar"
    )
    api_personas_url_eliminar = config.get("SeccionApi", "api_personas_url_eliminar")


def listar():
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    r = requests.get(api_personas_url_listar, headers=headers)
    if r.status_code == 200:
        listado = r.json()
        for item in listado:
            print("      " + str(item))
    else:
        print("Error " + str(r.status_code))


def crear(cedula: int, nombre: str, apellido: str):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = {"cedula": cedula, "nombre": nombre, "apellido": apellido}

    r = requests.post(api_personas_url_crear, headers=headers, json=datos)
    if 200 <= r.status_code < 300:
        print("Persona creada de manera exitosa.")
    else:
        print("Error " + str(r.status_code))
        print(str(r.json()))


def actualizar(cedula: int, nombre: str, apellido: str):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    datos = {"cedula": cedula, "nombre": nombre, "apellido": apellido}

    r = requests.put(
        f"{api_personas_url_actualizar}/{cedula}", headers=headers, json=datos
    )
    if 200 <= r.status_code < 300:
        print("Persona correctamente actualizada.")
    else:
        print("Error " + str(r.status_code))
        print(str(r.json()))


def eliminar(cedula: int):
    headers = CaseInsensitiveDict()
    headers["Accept"] = "application/json"
    headers["Content-Type"] = "application/json"

    r = requests.delete(f"{api_personas_url_eliminar}/{cedula}", headers=headers)
    if 200 <= r.status_code < 300:
        print("Persona eliminada con éxito.")
    else:
        print("Error " + str(r.status_code))
        print(str(r.json()))


#######################################################
######  Procesamiento principal
#######################################################
print("Iniciando " + datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
cargar_variables()

print("Primer listado de personas:")
listar()
print("________________")

# Crear una nueva persona
print("Crear nueva persona:")
cedula = int(input("Ingrese cédula: "))
nombre = input("Ingrese nombre: ")
apellido = input("Ingrese apellido: ")
crear(cedula, nombre, apellido)

print("________________")
print("Segundo listado de personas:")
listar()

# Actualizar persona
print("________________")
print("Actualizar persona:")
cedula = int(input("Ingrese el nro de cedula a actualizar: "))
nombre = input("Ingrese nuevo nombre: ")
apellido = input("Ingrese nuevo apellido: ")
actualizar(cedula, nombre, apellido)

print("________________")
print("Tercer listado de personas:")
listar()

# Eliminar persona
print("________________")
print("Eliminar persona:")
cedula = int(input("Ingrese el nro de cedular a eliminar: "))
eliminar(cedula)

print("________________")
print("Último listado de personas:")
listar()

print("Finalizando " + datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
