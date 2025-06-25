const { MongoClient } = require('mongodb');
const uri = "mongodb://localhost:27017";
const client = new MongoClient(uri);
async function crearBaseDeDatos() {
  try {
    await client.connect();
    console.log("Conectado exitosamente al servidor MongoDB");
    const db = client.db("universidad");
    const nuevoEstudiante = {
      "nombre": "Chris Redfield",
      "edad": 27,
      "carrera": "Ciencias de la Computación",
      "semestre": 10,
      "matriculado": true,
      "contacto": {
        "email": "chris.redfield@example.com",
        "telefono": "937654321"
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
    };

    const result = await db.collection("estudiantes").insertOne(nuevoEstudiante);

    console.log("Base de datos 'universidad' y colección 'estudiantes' creadas/utilizadas exitosamente.");
    console.log("Documento insertado con ID:", result.insertedId);

  } catch (e) {
    console.error("Ocurrió un error:", e);
  } finally {
    await client.close();
    console.log("Conexión MongoDB cerrada.");
  }
}
crearBaseDeDatos();