package domain

type Region struct {
	ID         uint
	Region     string
	KeyWord    int
	Country    string
	Population int
}

func (Region) TableName() string {
	return "region"
}

type RegionRepository interface {
	FindByRegionAndKeyWord(region string, keyWord int, offset, limit int) ([]Region, int64, error)
}
