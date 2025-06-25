package main

import (
	"context"
	"fmt"
	"log"
	"time"

	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

type Estudiante struct {
	Nombre        string    `json:"nombre" bson:"nombre"`
	Edad          int       `json:"edad" bson:"edad"`
	Carrera       string    `json:"carrera" bson:"carrera"`
	Semestre      int       `json:"semestre" bson:"semestre"`
	Matriculado   bool      `json:"matriculado" bson:"matriculado"`
	Contacto      Contacto  `json:"contacto" bson:"contacto"`
	CursosActuales []Curso   `json:"cursos_actuales" bson:"cursos_actuales"`
}

type Contacto struct {
	Email    string `json:"email" bson:"email"`
	Telefono string `json:"telefono" bson:"telefono"`
}

type Curso struct {
	NombreCurso string `json:"nombre_curso" bson:"nombre_curso"`
	Creditos    int    `json:"creditos" bson:"creditos"`
}

func main() {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)

	client, err := mongo.Connect(ctx, options.Client().ApplyURI("mongodb://localhost:27017"))
	if err != nil {
		log.Fatalf("Error al conectar a MongoDB: %v", err)
	}
	defer func() {
		if err = client.Disconnect(ctx); err != nil {
			log.Fatalf("Error al desconectar de MongoDB: %v", err)
		}
		fmt.Println("Conexión MongoDB cerrada.")
	}()

	err = client.Ping(ctx, nil)
	if err != nil {
		log.Fatalf("Error al hacer ping al servidor: %v", err)
	}
	fmt.Println("Conectado exitosamente al servidor MongoDB.")

	collection := client.Database("universidad").Collection("estudiantes")

	leonKennedy := Estudiante{
		Nombre:      "Ging Freecss",
		Edad:        24,
		Carrera:     "Ciencias de la Computación",
		Semestre:    9,
		Matriculado: true,
		Contacto: Contacto{
			Email:    "ging.freecss@example.com",
			Telefono: "9822254321",
		},
		CursosActuales: []Curso{
			{
				NombreCurso: "Inteligencia Artificial",
				Creditos:    5,
			},
			{
				NombreCurso: "Seguridad Informática",
				Creditos:    4,
			},
		},
	}

	result, err := collection.InsertOne(ctx, leonKennedy)
	if err != nil {
		log.Fatalf("Error al insertar documento: %v", err)
	}

	fmt.Printf("Base de datos 'universidad' y colección 'estudiantes' creadas/utilizadas exitosamente.\n")
	fmt.Printf("Documento insertado con ID: %v\n", result.InsertedID)
}