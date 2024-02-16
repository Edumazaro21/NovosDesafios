package repository

import (
	"expose-countries-go/domain"
	"gorm.io/gorm"
)

type RegionRepositoryDB struct {
	DB *gorm.DB
}

func NewRegionRepository(db *gorm.DB) *RegionRepositoryDB {
	return &RegionRepositoryDB{DB: db}
}

func (repo *RegionRepositoryDB) FindByRegionAndKeyWord(region string, keyWord int, offset, limit int) ([]domain.Region, int64, error) {
	var regions []domain.Region
	var totalRows int64

	repo.DB.Where("region = ? AND key_word = ?", region, keyWord).Find(&regions).Count(&totalRows)

	result := repo.DB.Where("region = ? AND key_word = ?", region, keyWord).Offset(offset).Limit(limit).Find(&regions)

	if result.Error != nil {
		return nil, 0, result.Error
	}

	return regions, totalRows, nil
}
