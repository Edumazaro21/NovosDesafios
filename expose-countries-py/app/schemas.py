# schemas.py
from pydantic import BaseModel

class RegionBase(BaseModel):
    region: str
    key_word: int
    country: str
    population: int

class RegionCreate(RegionBase):
    pass

class Region(RegionBase):
    id: int

    class Config:
        orm_mode = True