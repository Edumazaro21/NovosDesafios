package main

import (
	"expose-countries-go/adapters/web"
	"expose-countries-go/application"
	"expose-countries-go/repository"
	"github.com/gin-gonic/gin"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func main() {
	// Inicializa o banco de dados
	dsn := "host=localhost user=postgres password=postgres dbname=exposecountries port=5432 sslmode=disable TimeZone=Asia/Shanghai"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}

	// Inicializa o repositório
	regionRepo := repository.NewRegionRepository(db)

	// Inicializa o serviço
	regionService := application.NewRegionService(regionRepo)

	// Inicializa o Gin e registra as rotas
	router := gin.Default()

	// Configura os handlers
	web.RegisterRegionHandlers(router, regionService)

	// Inicia o servidor
	router.Run(":8080")
}
