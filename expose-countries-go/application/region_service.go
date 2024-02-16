package application

import "expose-countries-go/domain"

type RegionService struct {
	repo domain.RegionRepository
}

func NewRegionService(repo domain.RegionRepository) *RegionService {
	return &RegionService{repo: repo}
}

func (service *RegionService) GetRegionsByCriteria(region string, keyWord int, page, perPage int) ([]domain.Region, int64, error) {
	offset := (page - 1) * perPage
	return service.repo.FindByRegionAndKeyWord(region, keyWord, offset, perPage)
}
