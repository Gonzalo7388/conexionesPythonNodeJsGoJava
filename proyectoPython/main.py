from pymongo import MongoClient

def crear_base_de_datos():
    uri = "mongodb://localhost:27017/"
    client = MongoClient(uri)

    try:
        db = client["universidad"]

        nuevo_estudiante = {
            "nombre": "Leon Scott Kennedy",
            "edad": 24,
            "carrera": "Ciencias de la Computación",
            "semestre": 9,
            "matriculado": True, 
            "contacto": {
                "email": "leon.kennedy@example.com",
                "telefono": "987654321"
            },
            "cursos_actuales": [
                {
                    "nombre_curso": "Inteligencia Artificial",
                    "creditos": 5
                },
                {
                    "nombre_curso": "Seguridad Informática",
                    "creditos": 4
                }
            ]
        }

        resultado = db.estudiantes.insert_one(nuevo_estudiante)

        print("Base de datos 'universidad' y colección 'estudiantes' creadas/utilizadas exitosamente.")
        print("Documento insertado con ID:", resultado.inserted_id)

    except Exception as e:
        print("Ocurrió un error:", e)
    finally:
        client.close()
        print("Conexión cerrada.")

if __name__ == "__main__":
    crear_base_de_datos()