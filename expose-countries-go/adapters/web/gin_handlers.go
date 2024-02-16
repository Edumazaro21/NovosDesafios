package web

import (
	"expose-countries-go/application"
	"github.com/gin-gonic/gin"
	"strconv"
)

// Assume que você tem uma função chamada PaginatedSearch implementada aqui que usa o regionService

func RegisterRegionHandlers(router *gin.Engine, regionService *application.RegionService) {
	router.GET("/regions/search", func(c *gin.Context) {
		PaginatedSearch(c, regionService)
	})
}

func PaginatedSearch(c *gin.Context, regionService *application.RegionService) {
	// Extraia os parâmetros da requisição
	page, _ := strconv.Atoi(c.DefaultQuery("page", "1"))
	perPage, _ := strconv.Atoi(c.DefaultQuery("per_page", "3"))
	regionQuery := c.Query("region")
	keyWordQuery, _ := strconv.Atoi(c.Query("keyWord"))

	// Chame o serviço com os parâmetros
	regions, total, err := regionService.GetRegionsByCriteria(regionQuery, keyWordQuery, page, perPage)
	if err != nil {
		c.JSON(500, gin.H{"error": err.Error()})
		return
	}

	// Responda com os dados paginados
	c.JSON(200, gin.H{
		"page":        page,
		"per_page":    perPage,
		"total":       total,
		"total_pages": total / int64(perPage),
		"data":        regions,
	})
}
